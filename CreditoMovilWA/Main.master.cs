using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Security;

namespace CreditoMovilWA
{
    public partial class Main : System.Web.UI.MasterPage
    {
        private UsuarioWSClient daoUsuario = new UsuarioWSClient();
        private NotificacionWSClient daoNotificacion = new NotificacionWSClient();


        protected void Page_Load(object sender, EventArgs e)
        {

            if (Session["Usuario"] == null)
            {
                return;
            }
            if (!IsPostBack)
            {
                ActualizarNotificaciones();
            }
        }

        // Método para agregar una nueva notificación
        public void AgregarNotificacion (string mensaje)
        {
            usuarioInstancia user = (usuarioInstancia)Session["Usuario"];
            notificacion noti = new notificacion();
            noti.mensaje = mensaje;
            noti.id_usuario = user.idUsuario;
            noti.activo = 1;
            daoNotificacion.insertarNotificacion(noti);
        }

        // Método para actualizar el modal de notificaciones y el indicador
        private void ActualizarNotificaciones()
        {
            usuarioInstancia user = (usuarioInstancia)Session["Usuario"];
            notificacion[] notificaciones = daoNotificacion.listarPorUsuario(user.idUsuario);
            // Si no hay notificaciones, limpiar el DataSource y ocultar el indicador
            foreach (var notificacion in notificaciones)
            {
                System.Diagnostics.Debug.WriteLine($"ID: {notificacion.id_usuario}, Mensaje: {notificacion.mensaje}");
            }
            if (notificaciones == null || notificaciones.Length == 0)
            {
                rptNotifications.DataSource = new List<string> { "No hay notificaciones disponibles." };
                rptNotifications.DataBind();
                lblNotificationDot.Visible = false;

            }
            else
            {
                // Crear lista de mensajes con las primeras 10 notificaciones en orden invertido
                List<string> mensajes = notificaciones
                    .Reverse() // Invertir el orden
                    .Take(10) // Tomar las primeras 10
                    .Select(n => n.mensaje) // Obtener solo el campo 'mensaje'
                    .ToList();

                foreach (var mensaje in mensajes)
                {
                    System.Diagnostics.Debug.WriteLine(mensaje);
                }

                // Asignar la lista de mensajes al Repeater
                rptNotifications.DataSource = mensajes;
                rptNotifications.DataBind();
                lblNotificationDot.Visible = true; // Mostrar el indicador
            }
        }

        public bool MostrarHeader
        {
            get { return headerDiv.Visible; }
            set { headerDiv.Visible = value; }
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            // Lógica de cierre de sesión, por ejemplo:
            //ession.Clear();
            String rol = (string)Session["Rol"];
            FormsAuthentication.SignOut();

            if (rol == "CLIENTE")
            {
                Session["Cliente"] = null;
            }
            else if(rol == "SUPERVISOR")
            {
                Session["Supervisor"] = null;
            }
            else
            {
                Session["Administrador"] = null;
            }


            Response.Redirect("Home.aspx");
        }

        public static void ControlDeAcceso(HttpContext context, string requiredRole)
        {
            /*var user = context.Session["Usuario"] as Usuario;
            if (user == null || user.Role != requiredRole)
            {
                context.Response.Redirect("~/Login.aspx");
            }*/
        }
    }
}