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
                var resultados = daoCredito.listarCreditosFiltro(cli.codigoCliente, fechaInicio, fechaFin, estado);

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
            //transaccion trans = new transaccion();
            //trans.numOperacion = 0;
            //trans.usuarioRegistrado = (usuario1) Session["Cliente"];
            //trans.fecha = DateTime.Now;
            //trans.concepto = "que ponemos xd";
            //trans.monto = 1.0; //como seria esto? debe pagar la fraccion correspondiente a la cuota de la deuda total?
            //trans.anulado = false;
            //trans.agencia = "de donde sacamos esto";
            ////la foto
            ////trans.metodoPago = ;
            //daoTransaccion.insertarTransaccion(trans);

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
            return banco;
        }

        [WebMethod]
        public static billetera ObtenerDatosBilletera(string nombreBilletera)
        {
            BilleteraWSClient daoBilletera = new BilleteraWSClient();
            var billetera = daoBilletera.obtenerPorNombreBilletera(nombreBilletera);
            return billetera;
        }
    }
}
