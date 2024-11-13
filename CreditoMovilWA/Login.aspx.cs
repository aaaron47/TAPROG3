using System;
using System.Web.UI.WebControls;
using System.Web.UI;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Security.Cryptography;
using CreditoMovilWA.CreditoMovil;
using System.Web.Security;
using System.Web;

namespace CreditoMovilWA
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioWSClient daoUsuario = new UsuarioWSClient();
        private ClienteWSClient daoCliente = new ClienteWSClient();
        private SupervisorWSClient daoSupervisor = new SupervisorWSClient();
        private AdministradorWSClient daoAdmin = new AdministradorWSClient();
        protected static cliente[] todoClientes = null;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Master is Usuario masterPage)
            {
                masterPage.MostrarHeader = false; // Oculta el header en esta página
            }
        }

        protected void btnIngresar_Click(object sender, EventArgs e)
        {

            string tipoDocumento = ddlTipoDocumento.SelectedValue;
            string numDocumentoIdentidad = txtDocumento.Text.Trim();
            string password = txtPassword.Text;

            if (tipoDocumento != null && numDocumentoIdentidad != null && password != null)
            {
                usuario2 user = daoUsuario.obtenerPorDocIdenUsuario(numDocumentoIdentidad, tipoDocumento);

                Session["Cliente"] = null;
                Session["Supervisor"] = null;
                Session["Administrador"] = null;

                if (user != null && password == user.contrasenha)
                {

                    Session["Rol"] = user.rol.ToString();

                    FormsAuthenticationTicket tkt;
                    string cookiestr;
                    HttpCookie ck;
                    tkt = new FormsAuthenticationTicket(1, user.idUsuario.ToString(), DateTime.Now,
                    DateTime.Now.AddMinutes(30), true, user.nombre + " " + user.apPaterno + " " + user.apMaterno);
                    cookiestr = FormsAuthentication.Encrypt(tkt);
                    ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                    ck.Expires = tkt.Expiration; //esto genera que la cookie se quede guardada
                    ck.Path = FormsAuthentication.FormsCookiePath;
                    Response.Cookies.Add(ck);

                    string strRedirect = Request["ReturnUrl"];

                    switch (user.rol)
                    {
                        case rol1.CLIENTE:
                            cliente cli = daoCliente.obtenerPorDocIdenCliente(user.documento, user.tipoDocumento.ToString());
                            Session["Cliente"] = cli;

                            if (strRedirect == null)
                                strRedirect = "MainCliente.aspx";
                            break;
                        case rol1.SUPERVISOR:
                            supervisor sup = daoSupervisor.obtenerPorDocIdenSup(user.documento, user.tipoDocumento.ToString());
                            Session["Supervisor"] = sup;

                            if (strRedirect == null)
                                strRedirect = "MainSupervisor.aspx";
                            break;
                        case rol1.ADMINISTRADOR:
                            administrador admin = daoAdmin.obtenerPorDocIdenAdmin(user.documento, user.tipoDocumento.ToString());
                            Session["Administrador"] = admin;

                            if (strRedirect == null)
                                strRedirect = "MainAdmin.aspx";
                            break;
                    }
                    Response.Redirect(strRedirect, true);
                }
                else
                {
                    lblError.Text = "Usuario o contraseña incorrectos.";
                }
            }
        }

        private bool VerificarContraseña(string contraseñaIngresada, string salAlmacenada, string contraseñaHashAlmacenada)
        {
            // Convertir la sal y la contraseña hasheada almacenadas de Base64 a bytes
            byte[] salBytes = Convert.FromBase64String(salAlmacenada);
            byte[] hashAlmacenadoBytes = Convert.FromBase64String(contraseñaHashAlmacenada);

            // Hashear la contraseña ingresada con la misma sal
            using (var pbkdf2 = new Rfc2898DeriveBytes(contraseñaIngresada, salBytes, 10000))
            {
                byte[] hashIngresadoBytes = pbkdf2.GetBytes(32); // 256 bits

                // Comparar los hashes
                return hashIngresadoBytes.SequenceEqual(hashAlmacenadoBytes);
            }
        }
    }
}