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
                lblError.Text = "";
            }

        
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            cliente cli = (cliente) Session["Cliente"];
            
            DateTime fechaInicio, fechaFin;
            bool isFechaInicio = DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
            bool isFechaFin = DateTime.TryParse(txtFechaFin.Text, out fechaFin);
            string estado;
            estado = ddlEstado.SelectedValue;
            if (estado == "") estado = null;

            if (isFechaInicio && isFechaFin)
            {

                
                //var resultados = ObtenerCreditosPorFecha(fechaInicio, fechaFin);
                var resultados = daoCredito.listarCreditosFiltro(cli.codigoCliente, fechaInicio, fechaFin, estado);//corregir

                if (resultados != null)
                {
                    gvCreditos.DataSource = resultados;
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
           /* transaccion trans = new transaccion();
            if (fileUpload.HasFile)
            {
                // Guarda el archivo en Session para uso posterior
                Session["ImagenPago"] = fileUpload.FileBytes;

                string metodoP = metodoPago.Value;
                if(metodoP == "banco")
                {
                    banco bank = new banco();
                    bank.CCI = txtCCI.Text;
                    bank.nombreTitular = txtTitularBanco.Text;
                    bank.tipoCuenta = txtTipoCuenta.Text;
                    bank.foto = (Byte[])Session["ImagenPago"];
                    string banco1 = bancoElegido.Value;

                    //daoBanco.insertarBanco(bank);

                    trans.agencia = banco1;
                    //trans.metodoPago = bank;

                }
                else if(metodoP == "billetera")
                {
                    billetera bill = new billetera();
                    bill.nombreTitular = txtTitularBilletera.Text;
                    bill.foto = (Byte[])Session["ImagenPago"];
                    bill.numeroTelefono = txtNumeroBilletera.Text;

                    //daoBilletera.insertarBilletera(bill);

                    trans.agencia = metodoP;
                }

                trans.fecha = DateTime.Now;
                trans.foto = (Byte[])Session["ImagenPago"];
                trans.concepto = "Pago de Crédito";
                trans.monto = 123; // FALTA EL MONTO
                trans.anulado = false;
                trans.credito = (credito1)Session["Credito"];
                


                lblError.Text = "Archivo subido correctamente y pago registrado.";
                lblError.ForeColor = System.Drawing.Color.Green;
            }
            else
            {
                lblError.Text = "Por favor, selecciona un archivo para subir.";
                lblError.ForeColor = System.Drawing.Color.Red;
            }

            // Cierra el modal después de grabar
            ViewState["ModalAbierto"] = false;
            ScriptManager.RegisterStartupScript(this, this.GetType(), "CloseModal", "closeModal();", true);*/
        }

        protected void btnVerDetalles_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idCredito = btn.CommandArgument;
            Session["idCredito"] = idCredito;
            Response.Redirect("DetalleCredito.aspx");
        }

        private void CargarBancos()
        {
            try
            {

                var bancos = daoBanco.listarTodosBancos();
                ViewState["ListaBancos"] = bancos;

                // Limpiar el dropdown por si ya tiene datos
                ddlBancoElegido.Items.Clear();
                ddlBancoElegido.Items.Add(new ListItem("Seleccione un banco", "")); // Opción por defecto

                // Ahora puedes usar la lista de bancos en tu lógica ASP.NET
                if (bancos!=null)
                {
                    foreach (var banco in bancos)
                    {
                        ListItem listItem = new ListItem(banco.nombreBanco, banco.CCI); 
                        ddlBancoElegido.Items.Add(listItem);
                    }
                }
            }
            catch (Exception ex)
            {
                // Manejo de errores
                lblError.Text = "Error al cargar bancos: " + ex.Message;
            }

        }
        // esta va xon lo comentado en el aspx
        protected override void OnPreRender(EventArgs e)
        {
            base.OnPreRender(e);

            var bancos = ViewState["ListaBancos"] as List<BancoWSClient>;
            if (bancos != null)
            {
                var bancosJson = Newtonsoft.Json.JsonConvert.SerializeObject(bancos);
                ClientScript.RegisterStartupScript(this.GetType(), "BancosData", $"var bancosData = {bancosJson};", true);
            }
        }

        protected void ddlBancoElegido_SelectedIndexChanged(object sender, EventArgs e)
        {
            string bancoSeleccionado = ddlBancoElegido.SelectedValue;

            if (!string.IsNullOrEmpty(bancoSeleccionado))
            {
                // Obtener la información del banco basado en el CCI o algún identificador único
                var banco = daoBanco.obtenerPorNombreBanco(bancoSeleccionado); // Modifica según tu método de obtención

                if (banco != null)
                {
                    txtCCI.Text = banco.CCI;
                    txtTitularBanco.Text = banco.nombreTitular;
                    txtTipoCuenta.Text = banco.tipoCuenta;
                    // Ejecutar la función JavaScript para mostrar detalles
                    ClientScript.RegisterStartupScript(this.GetType(), "mostrarDetallesBanco", "mostrarDetallesBanco();", true);
                }
            }
            else
            {
                // Limpiar los campos si no hay banco seleccionado
                txtCCI.Text = "";
                txtTitularBanco.Text = "";
                txtTipoCuenta.Text = "";

                // Ejecutar la función JavaScript para ocultar detalles
                ClientScript.RegisterStartupScript(this.GetType(), "ocultarDetallesBanco", "ocultarDetallesBanco();", true);
            }
        }
    }
}
