using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class TotalEvaluaciones : System.Web.UI.Page
    {
        private CreditoWSClient daoCredito = new CreditoWSClient();
        private TransaccionWSClient daoTransaccion = new TransaccionWSClient();
        private BancoWSClient daoBanco = new BancoWSClient();
        private BilleteraWSClient daoBilletera = new BilleteraWSClient();
        private EvaluacionWSClient daoEvaluacion = new EvaluacionWSClient();


        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {

            DateTime fechaInicio, fechaFin;
            bool isFechaInicio = DateTime.TryParse(txtFechaInicio.Text, out fechaInicio);
            bool isFechaFin = DateTime.TryParse(txtFechaFin.Text, out fechaFin);

            if (isFechaInicio && isFechaFin)
            {
                evaluacion[] resultados = daoEvaluacion.listarEvaluacionesFecha(fechaInicio, fechaFin);

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
            String idEvaluacion = btn.CommandArgument;
            Session["idEvaluacion"] = idEvaluacion;
            Response.Redirect("DetalleEvaluacion.aspx");
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            Response.Redirect("AgregarEvaluacion.aspx");
        }

        // falta agregar para agregar evaluacion xd que seria no se una pantalla para agregar evaluacion no se guardaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa supongo que te guias de detalleEvaluacion
    }
}