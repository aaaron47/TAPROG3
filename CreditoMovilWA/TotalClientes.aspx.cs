using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class TotalClientes : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void FiltrarClientes_Click(object sender, EventArgs e)
        {
            try
            {
                int puntajeMin = int.Parse(txtPuntajeMin.Text);
                int puntajeMax = int.Parse(txtPuntajeMax.Text);

                // Aquí puedes implementar la lógica de filtrado, llamando al servicio que obtenga los clientes en el rango.
                /*var clientes = ObtenerClientesPorPuntaje(puntajeMin, puntajeMax);

                gvClientes.DataSource = clientes;
                gvClientes.DataBind();*/
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al filtrar clientes: " + ex.Message;
            }
        }

        protected void VerDetalleCliente_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idCliente = btn.CommandArgument;
            Session["IdCliente"] = idCliente;
            Response.Redirect("DetalleCliente.aspx");
        }

    }
}