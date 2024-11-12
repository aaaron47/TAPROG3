using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class DetalleCliente : System.Web.UI.Page
    {

        private ClienteWSClient daoCliente = new ClienteWSClient();
        private CreditoWSClient daoCredito = new CreditoWSClient();
        protected void Page_Init(object sender, EventArgs e)
        {
            administrador admin = (administrador)Session["Administrador"];
            if (admin == null)
            {
                Response.Redirect("Login.aspx");
            }

        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int id = (int)Session["IdCliente"];
                cliente cli = daoCliente.obtenerPorIDCliente(id);
                CargarCliente(cli);
                CargarCreditos(cli);
            }
        }

        private void CargarCliente(cliente cli)
        {

            ncliente.InnerText += cli.codigoCliente.ToString();
            txtNombreCompleto.Text = cli.nombre + " " + cli.apPaterno + " " + cli.apMaterno;
            txtDireccion.Text = cli.direccion;
            txtTelefono.Text = cli.telefono;

        }

        private void CargarCreditos(cliente cli)
        {
            
        }

        protected void btnVerDetalleCredito_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idCredito = btn.CommandArgument;
            Session["idCredito"] = idCredito;
            Response.Redirect("DetalleCredito.aspx");
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            /*try
            {
                // Aquí llamarías a tu método de generación de reportes con JasperReports.
                // Puedes hacer esto a través de una API que se comunique con JasperReports.

                string reportUrl = "http://tu_servidor_jasper/reports/creditos"; // Ejemplo de URL
                

                // Redirigir al usuario a la URL del reporte o descargarlo directamente
                Response.Redirect(reportUrl + parameters);
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al generar el reporte: " + ex.Message;
            }*/
        }

    }
}