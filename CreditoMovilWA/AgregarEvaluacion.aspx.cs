using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class AgregarEvaluacion : System.Web.UI.Page
    {
        private EvaluacionWSClient daoEvaluacion = new EvaluacionWSClient();

        protected void Page_Init(object sender, EventArgs e)
        {

            supervisor sup = (supervisor)Session["supervisor"];
            administrador admin = (administrador)Session["Administrador"];
            if (sup == null && admin == null)
            {
                Response.Redirect("Login.aspx");
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }
        }

        private void btnAgregar_Click(object sender, EventArgs e)
        {
            evaluacion ev = (evaluacion)Session["evaluacion"];
            ev.nombreNegocio = txtNombreNegocio.Text;
            ev.fechaRegistro = DateTime.Parse(txtFechaRegistro.Text);
            ev.direccionNegocio = txtDireccionNegocio.Text;
            ev.telefonoNegocio = txtTelefonoNegocio.Text;
            //logica para el cliente asignado
            string clienteAsignado = txtClienteAsignado.Text;
            //logica para el cliente asignado
            ev.margenGanancia = Double.Parse(txtMargenGanancia.Text);
            ev.ventasDiarias = Double.Parse(txtVentasDiarias.Text);
            ev.inventario = Double.Parse(txtInventario.Text);
            ev.costoVentas = Double.Parse(txtCostoVentas.Text);
            ev.activo = txtEstado.Text == "Activo" ? true : false;
            ev.observaciones = txtObservaciones.Text;


            // aca pa actualizar base de dates
            daoEvaluacion.insertarEvaluacion(ev);
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            Response.Redirect("TotalEvaluaciones.aspx");
        }
    }
}