using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class MainAdmin : System.Web.UI.Page
    {
        protected void Page_Init(object sender, EventArgs e)
        {
            administrador1 admin = (administrador1)Session["Administrador"];
            if (admin == null)
            {
                Response.Redirect("Login.aspx");
            }
            hola.InnerText += " " + admin.nombre + "!";
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnVisualizarCreditos_Click(object sender, EventArgs e)
        {

            Response.Redirect("TotalCreditos.aspx");
        }

        protected void btnVisualizarClientes_Click(object sender, EventArgs e)
        {

            Response.Redirect("TotalClientes.aspx");
        }

        protected void btnVisualizarEvaluaciones_Click(object sender, EventArgs e)
        {

            Response.Redirect("TotalEvaluaciones.aspx");
        }
    }
}