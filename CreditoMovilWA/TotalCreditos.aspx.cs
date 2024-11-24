using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class TotalCreditos : System.Web.UI.Page
    {

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
            
        }
        protected void btnFiltrar_Click(object sender, EventArgs e)
        {

            DateTime fechaInicio, fechaFin;
            bool isFechaInicio = DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
            bool isFechaFin = DateTime.TryParse(txtFechaFin.Text, out fechaFin);
            string estado;
            estado = ddlEstado.SelectedValue;
            if (estado == "") estado = null;

            if (isFechaInicio && isFechaFin)
            {


                //var resultados = ObtenerCreditosPorFecha(fechaInicio, fechaFin);
                credito [] resultados = daoCredito.listarCreditosSinCliFiltro(fechaInicio, fechaFin, estado);//corregir

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

        protected void btnVerDetalles_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int idCredito = int.Parse(btn.CommandArgument);
            Session["idCredito"] = idCredito;
            Response.Redirect("DetalleCredito.aspx");
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            DateTime fechaInicio, fechaFin;
            bool isFechaInicio = DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
            bool isFechaFin = DateTime.TryParse(txtFechaFin.Text, out fechaFin);
            string estado;
            estado = ddlEstado.SelectedValue;
            if (estado == "") estado = null;

            if (isFechaFin && isFechaInicio)
            {
                Byte[] reporte = daoCredito.generarReporteCreditos(fechaInicio,fechaFin,estado);
                if (reporte != null)
                {
                    Response.ContentType = "application/pdf";
                    Response.AddHeader("content-length", reporte.Length.ToString());
                    Response.BinaryWrite(reporte);
                }
                else
                {
                    lblError.Text = "Error al generar el reporte.";
                }
            }
        }

    }
}