using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Net.Mail;
using System.Net;
using System.Runtime.CompilerServices;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class Registro : System.Web.UI.Page
    {

        private ClienteWSClient daoCliente = new ClienteWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Master is Main masterPage)
            {
                masterPage.MostrarHeader = false; // Oculta el header en esta página
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            cliente Cliente = new cliente();

            // Capturar los valores ingresados
            Cliente.idUsuario = 0;
            Cliente.nombre = txtNombre.Text.Trim();
            Cliente.apPaterno = txtApPaterno.Text.Trim();
            Cliente.apMaterno = txtApMaterno.Text.Trim();
            switch (ddlTipoDocumento.SelectedValue)
            {
                case "DNI":
                    Cliente.tipoDocumento = tipoDocumento.DNI;
                    break;
                case "Pasaporte":
                    Cliente.tipoDocumento = tipoDocumento.PASAPORTE;
                    break;
                case "Carnet_Extranjeria":
                    Cliente.tipoDocumento = tipoDocumento.CARNET_EXTRANJERIA;
                    break;
            }
            Cliente.tipoDocumentoSpecified = true;
            Cliente.documento = txtNroDoc.Text.Trim();
            Cliente.email = txtEmail.Text.Trim();
            Cliente.telefono = txtTelefono.Text.Trim();
            Cliente.direccion = txtDireccion.Text.Trim();
            string contrasena = txtContrasena.Text;

            // Validaciones básicas
            if (string.IsNullOrEmpty(Cliente.nombre) || string.IsNullOrEmpty(Cliente.apPaterno) ||
                string.IsNullOrEmpty(ddlTipoDocumento.SelectedValue) || string.IsNullOrEmpty(Cliente.documento) ||
                string.IsNullOrEmpty(Cliente.email) || string.IsNullOrEmpty(contrasena))
            {
                lblError.Text = "Por favor, complete todos los campos.";
                return;
            }

            if (contrasena.Length < 8 || !contieneNumero(contrasena))
            {
                lblError.Text = "La contraseña debe tener al menos 8 caracteres y un número.";
                return;
            }

            // Aquí puedes agregar lógica para guardar los datos en la base de datos
            // Asegúrate de hashear la contraseña antes de almacenarla
            string hashedPassword = HashPassword(contrasena);
            Cliente.contrasenha = contrasena;

            // Código para guardar en la base de datos...
            Cliente.tipoCliente = "DE_BAJA";
            Cliente.activo = true;
            Cliente.fecha = DateTime.Now;
            Cliente.fechaSpecified = true;
            Cliente.fechaVencimiento = DateTime.Now; // falta ver
            Cliente.fechaVencimientoSpecified = true;
            Cliente.ultimoLogueo = DateTime.Now; // falta ver, no lo utiliza para la creacion de cliente
            Cliente.codigoCliente = 0;
            Cliente.ranking = 50;
            Cliente.rol = rol.CLIENTE;
            Cliente.rolSpecified = true;

            bool resultado = daoCliente.insertarCliente(Cliente);
            if (resultado)
            {
                enviarCorreo(Cliente.email);
                Response.Redirect("Home.aspx");
            }

        }

        private string HashPassword(string password)
        {
            // Implementa un método seguro para hashear la contraseña
            // Por ejemplo, utilizando SHA256 (aunque se recomienda usar algoritmos más seguros como BCrypt)
            using (System.Security.Cryptography.SHA256 sha256 = System.Security.Cryptography.SHA256.Create())
            {
                byte[] bytes = System.Text.Encoding.UTF8.GetBytes(password);
                byte[] hash = sha256.ComputeHash(bytes);
                return Convert.ToBase64String(hash);
            }
        }

        private bool contieneNumero(string password)
        {
            for (int i = 0; i < password.Length; i++)
            {
                if (password[i] >= '0' && password[i] <= '9') return true;
            }
            return false;
        }

        protected void enviarCorreo(String email)
        {
            MailMessage mensaje = new MailMessage();
            mensaje.From = new MailAddress(ConfigurationManager.AppSettings["Email"]);
            mensaje.To.Add(email);
            mensaje.Subject = "Registro de Cuenta - CreditoMovil";
            mensaje.Body = @"
            <!DOCTYPE html>
            <html lang='es'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Registro de Cuenta</title>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #f9f9f9; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
                    h2 { color: #4CAF50; }
                    a { text-decoration: none; color: #ffffff; background-color: #4CAF50; padding: 10px 20px; border-radius: 5px; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class='container'>
                    <h2>Registro de Cuenta</h2>
                    <p>Hola,</p>
                    <p>Se ha registrado correctamente su cuenta en Crédito Movil. Agradacemos por elegirnos como su empresa de confianza.</p>
                    <br>
                    <p>Saludos cordiales,</p>
                    <p>El equipo de soporte de CreditoMovil</p>
                </div>
            </body>
            </html>";

            mensaje.IsBodyHtml = true;

            SmtpClient clienteSmtp = new SmtpClient(ConfigurationManager.AppSettings["SmtpHost"], int.Parse(ConfigurationManager.AppSettings["SmtpPort"])); // Servidor SMTP
            clienteSmtp.Credentials = new NetworkCredential(ConfigurationManager.AppSettings["Email"], ConfigurationManager.AppSettings["EmailPassword"]);
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