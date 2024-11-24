using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Web.Script.Serialization;
using System.Web.UI;
using System.Web.UI.WebControls;
using Newtonsoft.Json;
using System.Web.Services;
using System.IO;
using System.Web;

namespace CreditoMovilWA
{
    public partial class VisualizarCreditos : System.Web.UI.Page
    {

        private CreditoWSClient daoCredito = new CreditoWSClient();
        private TransaccionWSClient daoTransaccion = new TransaccionWSClient();
        private BancoWSClient daoBanco = new BancoWSClient();
        private BilleteraWSClient daoBilletera = new BilleteraWSClient();

        protected void Page_Init(object sender, EventArgs e)
        {
            cliente cli = (cliente)Session["Cliente"];
            if (cli == null)
            {
                Response.Redirect("Login.aspx");
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarBancos();
                CargarBilleteras();
                lblError.Text = "";
                lblErrorModal.Text = "";
            }
        }
        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            cliente cli = (cliente)Session["Cliente"];

            DateTime fechaInicio, fechaFin;
            bool isFechaInicio = DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
            bool isFechaFin = DateTime.TryParse(txtFechaFin.Text, out fechaFin);
            string estado;
            estado = ddlEstado.SelectedValue;
            if (estado == "") estado = null;

            if (isFechaInicio && isFechaFin)
            {
                credito[] resultados = daoCredito.listarCreditosFiltro(cli.codigoCliente, fechaInicio, fechaFin, estado);

                if (resultados != null)
                {
                    Dictionary<string, int> ordenPersonalizado = new Dictionary<string, int>
                    {
                        { "Retrasado", 1 },
                        { "Activo", 2 },
                        { "Aprobado", 3 },
                        { "Desembolsado", 4 },
                        { "Solicitado", 5 },
                        { "Cancelado", 6 },
                        { "Anulado", 7 }
                    };

                    List<object> dataOrdenada = resultados
                     .OrderBy(x => ordenPersonalizado[x.estado.ToString()]) // Ordenar por el estado personalizado
                     .Select(x => new
                     {
                         numCredito = x.numCredito,
                         Monto = x.monto,
                         NumCuotas = x.numCuotas,
                         TasaInteres = x.tasaInteres,
                         FechaOtorgamiento = x.fechaOtorgamientoSpecified ? x.fechaOtorgamiento : (DateTime?)null,
                         Estado = x.estado.ToString(),
                         EstadoOriginal = x.estado // Incluye el estado original
                     })
                     .Cast<object>()
                     .ToList();

                    dynamic cred1 = dataOrdenada[0];
                    if(cred1.EstadoOriginal == CreditoMovil.estado.Retrasado)
                    {
                        lblRetrasado.Text = "Usted tiene créditos atrasados";
                    }
                    else
                    {
                        lblRetrasado.Text = "";
                    }

                    gvCreditos.DataSource = dataOrdenada;
                    gvCreditos.DataBind();
                    lblError.Text = "";

                }
                else
                {
                    gvCreditos.DataSource = null;
                    gvCreditos.DataBind();
                    lblError.Text = "No se encontraron créditos en el rango de fechas especificado.";
                }
            }
            else
            {
                lblError.Text = "Por favor, seleccione ambas fechas.";
            }
        }

        protected void btnPagar_Click(object sender, EventArgs e)
        {
            string idCredito = (sender as Button).CommandArgument;
            Session["idCredito"] = idCredito;
            ViewState["ModalAbierto"] = true; // Almacena el estado del modal en ViewState
            ClientScript.RegisterStartupScript(this.GetType(), "OpenModal", "openModal();", true);
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
            {
                lblErrorModal.Text = "Su sesión ha expirado.";
                // Mantener el modal abierto
                ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                return;
            }

            if (Session["MetodoPago"] == null)
            {
                lblErrorModal.Text = "Método de pago no seleccionado.";
                // Mantener el modal abierto
                ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                return;
            }

            if (Session["idCredito"] == null)
            {
                lblErrorModal.Text = "No se encuentra el credito.";
                // Mantener el modal abierto
                ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                return;
            }

            // Continuar con la lógica de negocio
            transaccion trans = new transaccion();
            trans.usuarioRegistrado = (usuarioInstancia)Session["Usuario"];
            int idUsuario = trans.usuarioRegistrado.idUsuario;
            trans.fecha = DateTime.Now;
            int idMetodoPago = int.Parse(Session["MetodoPago"].ToString());
            //trans.metodoPago = new metodoPagoInstancia();
            //trans.metodoPago.idMetodoPago = idMetodoPago;

            trans.metodoPago = null;

            int idCredito = int.Parse((string)Session["idCredito"]);
            credito cred = daoCredito.obtenerPorIDCredito(idCredito);

            cred.cantCuotasPagadas++;
            trans.concepto = "Cuota número " + cred.cantCuotasPagadas;

            if (cred.numCuotas == cred.cantCuotasPagadas)
                cred.estado = estado.Cancelado;

            daoCredito.modificarCredito(cred);

            trans.credito = new credito1(); 
            trans.credito.numCredito = idCredito;

            if (fileUpload.HasFile)
            {
                int maxFileSize = 5 * 1024 * 1024; // 5 MB
                if (fileUpload.PostedFile.ContentLength > maxFileSize)
                {
                    lblErrorModal.Text = "El archivo es demasiado grande. El tamaño máximo permitido es 5 MB.";
                    // Mantener el modal abierto
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                    return;
                }

                string fileExtension = Path.GetExtension(fileUpload.FileName).ToLower();
                if (fileExtension != ".jpg" && fileExtension != ".jpeg" && fileExtension != ".png" && fileExtension != ".pdf")
                {
                    lblErrorModal.Text = "Solo se permiten archivos de tipo JPG, JPEG, PNG o PDF.";
                    // Mantener el modal abierto
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                    return;
                }

                // Leer el archivo y convertirlo en un arreglo de bytes
                using (BinaryReader br = new BinaryReader(fileUpload.PostedFile.InputStream))
                {
                    trans.foto = br.ReadBytes(fileUpload.PostedFile.ContentLength);
                }

                // Intentar insertar la transacción
                if (!daoTransaccion.insertarTransaccion(trans, idUsuario, idCredito, idMetodoPago))
                {
                    lblErrorModal.Text = "Error al insertar la transacción.";
                    // Mantener el modal abierto
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                }
                else
                {
                    // Transacción exitosa, cerrar el modal
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "CloseModal", "closeModal();", true);
                    lblErrorModal.Text = "";
                }
            }
            else
            {
                // No se ha subido ningún archivo
                lblErrorModal.Text = "Por favor, suba un archivo de comprobante.";
                // Mantener el modal abierto
                ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal();", true);
                return;
            }
        }

        protected void btnVerDetalles_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int idCredito = int.Parse(btn.CommandArgument);
            Session["idCredito"] = idCredito;
            Response.Redirect("DetalleCredito.aspx");
        }

        private void CargarBancos()
        {
            try
            {
                banco1 [] bancos = daoBanco.listarTodosBancos();
                ViewState["ListaBancos"] = bancos;

                ddlBancoElegido.Items.Clear();
                ddlBancoElegido.Items.Add(new ListItem("Seleccione un banco", ""));

                if (bancos != null)
                {
                    foreach (banco1 banco in bancos)
                    {
                        ListItem listItem = new ListItem(banco.nombreBanco, banco.nombreBanco);
                        ddlBancoElegido.Items.Add(listItem);
                    }
                }
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al cargar bancos: " + ex.Message;
            }
        }

        private void CargarBilleteras()
        {
            try
            {
                billetera1[] billeteras = daoBilletera.listarTodosBilleteras();
                ViewState["ListaBilleteras"] = billeteras;

                ddlBilleteraElegida.Items.Clear();
                ddlBilleteraElegida.Items.Add(new ListItem("Seleccione una billetera", ""));

                if (billeteras != null)
                {
                    foreach (var billetera in billeteras)
                    {
                        System.Diagnostics.Debug.WriteLine("Agregando billetera: " + billetera.nombreBilletera);
                        ListItem listItem = new ListItem(billetera.nombreBilletera, billetera.nombreBilletera);
                        ddlBilleteraElegida.Items.Add(listItem);
                    }
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine("La lista de billeteras es nula.");
                }
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error al cargar billeteras: " + ex.Message;
            }
        }

        [WebMethod]
        public static banco1 ObtenerDatosBanco(string nombreBanco)
        {
            BancoWSClient daoBanco = new BancoWSClient();
            banco1 banco = daoBanco.obtenerPorNombreBanco(nombreBanco);
            HttpContext.Current.Session["MetodoPago"] = banco.idMetodoPago;
            return banco;
        }

        [WebMethod]
        public static billetera1 ObtenerDatosBilletera(string nombreBilletera)
        {
            BilleteraWSClient daoBilletera = new BilleteraWSClient();
            billetera1 billetera = daoBilletera.obtenerPorNombreBilletera(nombreBilletera);
            HttpContext.Current.Session["MetodoPago"] = billetera.idMetodoPago;
            return billetera;
        }
    }
}
