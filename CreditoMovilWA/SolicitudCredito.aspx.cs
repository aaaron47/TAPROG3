using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class SolicitudCredito : System.Web.UI.Page
    {

        private CreditoWSClient daoCredito = new CreditoWSClient();

        protected void Page_Init(object sender, EventArgs e)
        {
            cliente cli = (cliente)Session["Cliente"];
            if (cli == null)
            {
                Response.Redirect("Login.aspx");
            }

            minHiddenField.Value = (1000 * cli.ranking / 100).ToString();
            maxHiddenField.Value = (5000 * cli.ranking / 100).ToString();

            if(cli.ranking > 80) tasaInteres.Value = "0.05";
            else if(cli.ranking > 50) tasaInteres.Value = "0.8";
            else if(cli.ranking > 30) tasaInteres.Value = "0.1";
            else if(cli.ranking > 10) tasaInteres.Value = "0.12";
            else tasaInteres.Value = "0.15";
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                for (int i = 1; i <= 18; i++)
                {
                     ddlCuotas.Items.Add(new ListItem($"{i} mes(es)", i.ToString()));
                }
            }
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {

            if (string.IsNullOrEmpty(ddlCuotas.SelectedValue))
            {
                lblError.Text = "Debe seleccionar el número de cuotas.";
                lblError.Visible = true;
                return;
            }

            cliente cli = (cliente)Session["Cliente"];
            // Obtener el monto del HiddenField
            int monto = int.Parse(hfMonto.Value);
            int cuotas = int.Parse(ddlCuotas.SelectedValue);

            // Calcular el rango de interés (5% - 15%)
            double minInteres = monto * 0.05;
            double maxInteres = monto * 0.15;

            // Mostrar el interés en el Label
            //lblInteres.Text = $"Interés aproximado: S/. {minInteres:F2} - S/. {maxInteres:F2}";

            credito cred = new credito();
            cred.cliente = null; //no es necesario ya que se guarda desde el insertarcredito del dao.
            cred.estado = estado.Solicitado;
            cred.tasaInteres = double.Parse(tasaInteres.Value)*100;
            cred.monto = monto;
            cred.numCuotas = cuotas; // no sé cómo colocar esto btw, creo que es así, vamos a ver
            cred.numCredito = 0;//se autogenera
            cred.fechaOtorgamiento = DateTime.MinValue; // Establece la fecha más baja válida
            cred.fechaOtorgamientoSpecified = true; // Indica que la fecha será enviada

            daoCredito.insertarCredito(cred,cli.documento,cli.tipoDocumento.ToString());
            
            Main masterPage = (Main)this.Master;
            masterPage.AgregarNotificacion($"Crédito solicitado por el cliente {cli.nombre} por un monto de S/. {monto}.");

            Response.Redirect("MainCliente.aspx");
        }
    }
}