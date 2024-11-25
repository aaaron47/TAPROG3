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
            administrador1 admin = (administrador1)Session["Administrador"];
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
            if (string.IsNullOrWhiteSpace(txtPuntajeMin.Text) || string.IsNullOrWhiteSpace(txtPuntajeMax.Text))
            {
                lblError.Text = "Debe ingresar valores en ambos campos.";
                return;
            }

            int puntajeMin, puntajeMax;
            if (!int.TryParse(txtPuntajeMin.Text, out puntajeMin) || !int.TryParse(txtPuntajeMax.Text, out puntajeMax))
            {
                lblError.Text = "Debe ingresar valores numéricos válidos.";
                return;
            }

            if (puntajeMin<0 || puntajeMin > 100 || puntajeMax < 0 || puntajeMax > 100)
            {
                lblError.Text = "Los valores deben estar entre 0 y 100.";
                return;
            }

            if (puntajeMin > puntajeMax)
            {
                lblError.Text = "El mínimo debe ser menor al máximo.";
                return;
            }

            cliente[] clientes = daoCliente.listarClientesPorRanking(puntajeMin,puntajeMax);

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