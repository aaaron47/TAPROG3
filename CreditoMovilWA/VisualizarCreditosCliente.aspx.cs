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

                // Serializar la lista de bancos en JSON
                var bancosJson = JsonConvert.SerializeObject(bancos);
                // Registrar la variable JavaScript con los datos
                ScriptManager.RegisterStartupScript(this, this.GetType(), "BancosData",
                    $"var bancosData = {bancosJson};", true);
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar bancos: " + ex.Message;
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
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal(); mostrarDetallesBanco();", true);
                }
            }
            else
            {
                // Limpiar los campos si no hay banco seleccionado
                txtCCI.Text = "";
                txtTitularBanco.Text = "";
                txtTipoCuenta.Text = "";

                // Ejecutar la función JavaScript para ocultar detalles
                 ScriptManager.RegisterStartupScript(this, this.GetType(), "OpenModal", "openModal(); ocultarDetallesBanco();", true);
            }
        }

        [WebMethod]
        public static banco ObtenerDatosBanco(string nombreBanco)
        {
            BancoWSClient daoBanco = new BancoWSClient();
            var banco = daoBanco.obtenerPorNombreBanco(nombreBanco);
            return banco;
        }
    }
}
