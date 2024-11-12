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
                var resultados = daoCredito.li(fechaInicio, fechaFin, estado);//corregir

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

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            /*try
            {
                // Aquí llamarías a tu método de generación de reportes con JasperReports.
                // Puedes hacer esto a través de una API que se comunique con JasperReports.

                string reportUrl = "http://tu_servidor_jasper/reports/creditos"; // Ejemplo de URL
                string parameters = $"?fechaInicio={txtFechaInicio.Text}&fechaFin={txtFechaFin.Text}&estado={ddlEstado.SelectedValue}";

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