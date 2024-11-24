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
using System.CodeDom;

namespace CreditoMovilWA
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioWSClient daoUsuario = new UsuarioWSClient();
        private ClienteWSClient daoCliente = new ClienteWSClient();
        private SupervisorWSClient daoSupervisor = new SupervisorWSClient();
        private AdministradorWSClient daoAdmin = new AdministradorWSClient();
        protected static cliente[] todoClientes = null;

        private DateTime BloqueoHasta = DateTime.MinValue;
        private int maxIntentos = 3;
        private double minutosAñadir = 0.5;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Master is Main masterPage)
            {
                masterPage.MostrarHeader = false; // Oculta el header en esta página
            }

            if (!IsPostBack)
            {
                Session["totalIntentos"] = 0;
                Session["Bloqueo"] = BloqueoHasta;
                Session["minutos"] = minutosAñadir;
                if (Session["AuthExpiration"] != null)
                {
                    DateTime expirationTime = (DateTime)Session["AuthExpiration"];
                    long expirationTimestamp = (long)(expirationTime.ToUniversalTime() - new DateTime(1970, 1, 1)).TotalMilliseconds;
                    string script = $"var authExpiration = {expirationTimestamp};";
                    ClientScript.RegisterStartupScript(this.GetType(), "AuthExpirationScript", script, true);
                }

            }
        }

        protected void btnIngresar_Click(object sender, EventArgs e)
        {
            DateTime Bloqueo = (DateTime)Session["Bloqueo"];
            int totalIntentos = (int)Session["totalIntentos"];
            double minutos = (double)Session["minutos"];

            string tipoDocumento = ddlTipoDocumento.SelectedValue;
            string numDocumentoIdentidad = txtDocumento.Text.Trim();
            string password = txtPassword.Text;
            if (totalIntentos < maxIntentos && Bloqueo <= DateTime.Now)
            {
                if (tipoDocumento != null && numDocumentoIdentidad != null && password != null)
                {
                    usuarioInstancia user = daoUsuario.obtenerPorDocIdenUsuario(numDocumentoIdentidad, tipoDocumento);

                    Session["Usuario"] = user;
                    Session["Cliente"] = null;
                    Session["Supervisor"] = null;
                    Session["Administrador"] = null;

                    if (user != null && password == user.contrasenha)
                    {
                        totalIntentos = 0;

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

                        // Pasar la fecha de expiración al cliente
                        DateTime expirationTime = tkt.Expiration;
                        Session["AuthExpiration"] = expirationTime;

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
                        totalIntentos++;
                        Session["totalIntentos"] = totalIntentos;
                        lblError.Text = "Usuario o contraseña incorrectos.";
                    }
                }
                else
                {
                    lblError.Text = "Por favor, ingrese todos los datos.";
                }
            }
            else
            {
                if (totalIntentos >= maxIntentos)
                {
                    Bloqueo = DateTime.Now.AddMinutes(minutos);
                    totalIntentos = 0;
                    minutos += minutosAñadir;
                    Session["minutos"] = minutos;
                    Session["totalIntentos"] = totalIntentos;
                    Session["Bloqueo"] = Bloqueo;
                }
                lblError.Text = $"Usuario bloqueado debido a múltiples intentos. Intente nuevamente después del {Bloqueo}";
            }
        }

        protected void LinkButton1_Click(object sender, EventArgs e)
        {
            Response.Redirect("ValidarEmail.aspx");
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