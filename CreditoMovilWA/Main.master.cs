﻿using CreditoMovilWA.CreditoMovil;
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

        private static List<string> notificaciones = new List<string>();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                ActualizarNotificaciones();
            }
        }

        // Método para agregar una nueva notificación
        public void AgregarNotificacion (string mensaje)
        {
            notificaciones.Add(mensaje);
        }

        // Método para actualizar el modal de notificaciones y el indicador
        private void ActualizarNotificaciones()
        {
            rptNotifications.DataSource = notificaciones;
            rptNotifications.DataBind();

            // Mostrar o ocultar el punto indicador de nuevas notificaciones
            lblNotificationDot.Visible = notificaciones.Count > 0;
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