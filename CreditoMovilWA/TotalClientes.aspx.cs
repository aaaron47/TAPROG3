using CreditoMovilWA.CreditoMovil;
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
        private ClienteWSClient daoCliente = new ClienteWSClient();

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

        protected void FiltrarClientes_Click(object sender, EventArgs e)
        {
            int puntajeMin = int.Parse(txtPuntajeMin.Text);
            int puntajeMax = int.Parse(txtPuntajeMax.Text);

            var clientes = daoCliente.listarClientesPorRanking(puntajeMin,puntajeMax);

            if(clientes != null)
            {
                gvClientes.DataSource = clientes;
                gvClientes.DataBind();
            }
            else
            {
                gvClientes.DataSource = null;
                gvClientes.DataBind();
                lblError.Text = "Error al filtrar clientes.";
            }
        }

        protected void VerDetalleCliente_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int idCliente = int.Parse(btn.CommandArgument);
            Session["IdCliente"] = idCliente;
            Response.Redirect("DetalleCliente.aspx");
        }

    }
}