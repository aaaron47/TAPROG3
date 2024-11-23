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


                //var resultados = ObtenerCreditosPorFecha(fechaInicio, fechaFin);
                var resultados = daoCredito.listarCreditosFiltro(cli.codigoCliente, fechaInicio, fechaFin, estado);

                if (resultados != null)
                {
                    var ordenPersonalizado = new Dictionary<string, int>
                    {
                        { "Retrasado", 1 },
                        { "Activo", 2 },
                        { "Solicitado", 3 },
                        { "Finalizado", 4 }
                    };

                    var dataOrdenada = resultados.OrderBy(x => ordenPersonalizado[x.estado]).ToList();

                    var cred1 = dataOrdenada[0];
                    if(cred1.estado == "Retrasado")
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
            ViewState["ModalAbierto"] = true; // Almacena el estado del modal en ViewState
            ClientScript.RegisterStartupScript(this.GetType(), "OpenModal", "openModal();", true);
        }

        protected void btnSave_Click(object sender, EventArgs e)
        {
            transaccion trans = new transaccion();
            trans.usuarioRegistrado = (usuario1)Session["Cliente"];
            int idUsuario = trans.usuarioRegistrado.idUsuario;
            trans.fecha = DateTime.Now;
            int idMetodoPago = int.Parse(Session["MetodoPago"].ToString());
            trans.metodoPago.idMetodoPago = idMetodoPago;

            credito cred = new credito();
            int idCredito = int.Parse(Session["idCredito"].ToString());
            cred = daoCredito.obtenerPorIDCredito(idCredito);


            cred.cantCuotasPagadas++;
            trans.concepto = "Cuota numero " + cred.cantCuotasPagadas;

            if (cred.numCuotas == cred.cantCuotasPagadas)
                cred.estado = "Finalizado";

            cred.cantCuotasPagadas++;
            if (cred.numCuotas == cred.cantCuotasPagadas) cred.estado = "Finalizado";

            daoCredito.modificarCredito(cred);

            trans.credito.numCredito = idCredito;

            if (fileUpload.HasFile)
            {
                int maxFileSize = 5 * 1024 * 1024; // 5 MB
                if (fileUpload.PostedFile.ContentLength > maxFileSize)
                {
                    lblError.Text = "El archivo es demasiado grande. El tamaño máximo permitido es 5 MB.";
                    return;
                }

                string fileExtension = Path.GetExtension(fileUpload.FileName).ToLower();
                if (fileExtension != ".jpg" && fileExtension != ".jpeg" && fileExtension != ".png" && fileExtension != ".pdf")
                {
                    lblError.Text = "Solo se permiten archivos de tipo JPG, JPEG, PNG o PDF.";
                    return;
                }

                // Leer el archivo y convertirlo en un arreglo de bytes
                byte[] fileData = null;
                using (BinaryReader br = new BinaryReader(fileUpload.PostedFile.InputStream))
                {
                    fileData = br.ReadBytes(fileUpload.PostedFile.ContentLength);
                }

                trans.foto = fileData;

                if(!daoTransaccion.insertarTransaccion(trans, idUsuario, idCredito, idMetodoPago))
                {
                    lblError.Text = "Error en insertar la transacción.";
                    return;
                }
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
                var bancos = daoBanco.listarTodosBancos();
                ViewState["ListaBancos"] = bancos;

                ddlBancoElegido.Items.Clear();
                ddlBancoElegido.Items.Add(new ListItem("Seleccione un banco", ""));

                if (bancos != null)
                {
                    foreach (var banco in bancos)
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
                var billeteras = daoBilletera.listarTodosBilleteras();
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
        public static banco ObtenerDatosBanco(string nombreBanco)
        {
            BancoWSClient daoBanco = new BancoWSClient();
            var banco = daoBanco.obtenerPorNombreBanco(nombreBanco);
            HttpContext.Current.Session["MetodoPago"] = banco.idMetodoPago;
            return banco;
        }

        [WebMethod]
        public static billetera ObtenerDatosBilletera(string nombreBilletera)
        {
            BilleteraWSClient daoBilletera = new BilleteraWSClient();
            var billetera = daoBilletera.obtenerPorNombreBilletera(nombreBilletera);
            HttpContext.Current.Session["MetodoPago"] = billetera.idMetodoPago;
            return billetera;
        }
    }
}
