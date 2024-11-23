using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{

    public partial class RecuperarContrasena : System.Web.UI.Page
    {

        private ClienteWSClient daoCliente = new ClienteWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Master is Main masterPage)
            {
                masterPage.MostrarHeader = false; // Oculta el header en esta página
            }
        }

        protected void btnRecuperar_Click(object sender, EventArgs e)
        {

            int codCli = Int32.Parse(Request.QueryString["token"]);

            if (txtContra.Text == txtContraConf.Text && txtContra.Text != "")
            {
                if(daoCliente.cambiarContra(codCli, txtContra.Text))
                {
                    Response.Redirect("Home.aspx");
                }
            }
        }
    }
}