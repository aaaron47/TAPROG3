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
    public partial class DetalleCredito : System.Web.UI.Page
    {
        private ClienteWSClient daoCliente = new ClienteWSClient();
        private CreditoWSClient daoCredito = new CreditoWSClient();
        private TransaccionWSClient daoTransaccion = new TransaccionWSClient();
        private bool modoEdicion;
        protected void Page_Init(object sender, EventArgs e)
        {
            modoEdicion = false;
            btnModificar.Text = "MODIFICAR";
            cliente cli = (cliente)Session["Cliente"];
            administrador1 admin = (administrador1)Session["Administrador"];
            if (cli == null && admin == null)
            {
                Response.Redirect("Login.aspx");
            }else if (cli != null)
            {
                DeshabilitarCampos();
            }
            btnDesembolso.Visible = false;
            btnModificar.Text = "MODIFICAR";
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDetalleCredito();
                CargarTransacciones();
                DeshabilitarCampos(); //inicialmente inhabilitad

                if (Session["Rol"].ToString() == "ADMINISTRADOR")
                {
                    btnModificar.Visible = true;
                }
            }
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            modoEdicion = !modoEdicion;
            if (btnModificar.Text == "MODIFICAR")
            {
                btnModificar.Text = "GUARDAR";
                HabilitarCampos();
            }
            else
            {

                credito cred = (credito)Session["Credito"];

                switch (txtEstado.Text)
                {
                    case "Solicitado":
                        cred.estado = estado.Solicitado;
                        break;
                    case "Cancelado":
                        cred.estado = estado.Cancelado;
                        break;
                    case "Anulado":
                        cred.estado = estado.Anulado;
                        break;
                    case "Retrasado":
                        cred.estado = estado.Retrasado;
                        break;
                    case "Aprobado":
                        cred.estado = estado.Aprobado;
                        break;
                    case "Desembolsado":
                        cred.estado = estado.Desembolsado;
                        cred.fechaOtorgamiento = DateTime.Now;
                        cred.fechaOtorgamientoSpecified = true;
                        break;
                }
                string tasa = txtTasaInteres.Text;
                string tasa2 = "";
                int tam = tasa.Length;
                for (int i = 0; i < tam; i++) tasa2 += tasa[i];
                cred.tasaInteres = Double.Parse(tasa2);

                daoCredito.modificarCredito(cred);
                DeshabilitarCampos();
                btnModificar.Text = "MODIFICAR";
                
            }


        }

        private void DeshabilitarCampos()
        {

            txtEstado.ReadOnly = true;
            txtMonto.ReadOnly = true;
            txtTasaInteres.ReadOnly = true;
            txtNumeroCuotas.ReadOnly = true;
        }

        private void HabilitarCampos()
        {
            txtEstado.ReadOnly = false;
            txtMonto.ReadOnly = false;
            txtTasaInteres.ReadOnly = false;
            txtNumeroCuotas.ReadOnly = false;
        }

        private void CargarDetalleCredito()
        {
            int id = (int)Session["idCredito"];

            credito cred = daoCredito.obtenerPorIDCredito(id);

            Session["Credito"] = cred;
            // Cargar datos de ejemplo para los detalles del crédito.
            txtFechaOtorgamiento.Text = cred.fechaOtorgamiento.ToString("dd/MM/yyyy");
            txtEstado.Text = cred.estado.ToString();
            txtMonto.Text = cred.monto.ToString();
            txtNumeroCuotas.Text = cred.numCuotas.ToString();
            txtTasaInteres.Text = cred.tasaInteres.ToString();

            if (cred.estado.ToString() == "ARPOBADO")
            {
                btnDesembolso.Visible = true;
            }

            idCredito.InnerText += " " + cred.numCredito;
        }

        private void CargarTransacciones()
        {
            credito cred = (credito)Session["Credito"];
            // Cargar datos de ejemplo o conectar a la base de datos para obtener transacciones.
            gvTransacciones.DataSource = daoTransaccion.listarTransaccionCredito(cred.numCredito); // falta el metodo que sea listar por credito :p
            gvTransacciones.DataBind();
        }

        protected void btnVerDetalleTransaccion_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            string idTransaccion = btn.CommandArgument;
            Session["idTransaccion"] = idTransaccion;
            Response.Redirect("DetalleTransaccion.aspx");
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            string rol = (string)Session["Rol"];
            if (rol == "CLIENTE")
                Response.Redirect("VisualizarCreditosCliente.aspx");
            else Response.Redirect("TotalCreditos.aspx");
        }
        protected void btnDesembolso_Click(object sender, EventArgs e)
        {
            cliente cli = (cliente)Session["Cliente"];
            int id = (int)Session["idCredito"];

            Byte[] reporte = daoCliente.generarDesembolso(cli.codigoCliente, id);
            credito cred = daoCredito.obtenerPorIDCredito(id);

            Main masterPage = (Main)this.Master;
            masterPage.AgregarNotificacion($"El cliente {cli.nombre} ha solicitado un desembolso por un monto de S/. {cred.monto} para el crédito con ID {id}.");
        }
    }
}