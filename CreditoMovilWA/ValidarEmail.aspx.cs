using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Net.Mail;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;

namespace CreditoMovilWA
{
    public partial class ValidarEmail : System.Web.UI.Page
    {

        private ClienteWSClient daoCliente = new ClienteWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Master is Main masterPage)
            {
                masterPage.MostrarHeader = false; // Oculta el header en esta página
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Login.aspx");
        }

        protected void btnVerificar_Click(object sender, EventArgs e)
        {
            String email = txtEmail.Text;
            if (email != "")
            {
                int codCli = daoCliente.validarEmail(email);
                if (codCli != -1)
                {
                    //enviarCorreo(txtEmail.Text, codCli);
                    Response.Redirect("RecuperarContrasena.aspx?token=" + codCli.ToString());
                }
                lblVerificar.Text = "En caso el cliente exista, se ha enviado un correo para poder restablecer la contraseña.";
            }
            else
            {
                lblError.Text = "Por favor, propocione un email";
            }
        }

        protected void enviarCorreo(String email, int token)
        {
            string enlaceRecuperacion = "https://localhost:44333/RecuperarContrasena.aspx?token=" + token;

            MailMessage mensaje = new MailMessage();
            mensaje.From = new MailAddress("taprog3credmov@outlook.com");
            mensaje.To.Add(email);
            mensaje.Subject = "Recuperación de Contraseña - CreditoMovil";
            mensaje.Body = @"
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Recuperación de Contraseña</title>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
                    h2 { color: #4CAF50; }
                    a { text-decoration: none; color: #ffffff; background-color: #4CAF50; padding: 10px 20px; border-radius: 5px; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class='container'>
                    <h2>Recuperación de Contraseña</h2>
                    <p>Hola,</p>
                    <p>Hemos recibido una solicitud para recuperar tu contraseña. Si fuiste tú, por favor haz clic en el siguiente enlace para establecer una nueva contraseña:</p>
                    <p style='text-align: center;'>
                        <a href='{enlaceRecuperacion}' target='_blank'>Recuperar Contraseña</a>
                    </p>
                    <p>Si no solicitaste este cambio, por favor ignora este correo. Tu contraseña permanecerá sin cambios.</p>
                    <p>Este enlace de recuperación expirará en 24 horas.</p>
                    <br>
                    <p>Saludos cordiales,</p>
                    <p>El equipo de soporte de CreditoMovil</p>
                </div>
            </body>
            </html>";
            mensaje.IsBodyHtml = true;

            SmtpClient clienteSmtp = new SmtpClient("smtp.office365.com", 587); // Servidor SMTP
            clienteSmtp.Credentials = new NetworkCredential("taprog3credmov@outlook.com", "creditomovil1");
            clienteSmtp.EnableSsl = true;

            try
            {
                clienteSmtp.Send(mensaje);
                Console.WriteLine("Correo enviado exitosamente.");
            }
            catch (System.Exception ex)
            {
                Console.WriteLine("Error al enviar el correo: " + ex.Message);
            }
        }
    }
}