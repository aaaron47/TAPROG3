﻿using CreditoMovilWA.CreditoMovil;
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
        private CreditoWSClient daoCredito = new CreditoWSClient();
        private TransaccionWSClient daoTransaccion = new TransaccionWSClient();

        protected void Page_Init(object sender, EventArgs e)
        {
            cliente cli = (cliente)Session["Cliente"];
            administrador admin = (administrador)Session["Administrador"];
            if (cli == null && admin==null)
            {
                Response.Redirect("Login.aspx");
            }

        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDetalleCredito();
                CargarTransacciones();

                if(Session["Rol"].ToString() == "ADMINISTRADOR")
                {
                    btnModificar.Visible = true;
                }

            }
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            // Lógica para modificar el crédito

        }


        private void CargarDetalleCredito()
        {
            int id = (int)Session["idCredito"];

            credito cred = daoCredito.obtenerPorIDCredito(id);

            Session["Credito"] = cred;
            // Cargar datos de ejemplo para los detalles del crédito.
            txtFechaOtorgamiento.Text = cred.fechaOtorgamiento.ToString("dd/MM/yyyy");
            txtEstado.Text = cred.estado;
            txtMonto.Text = cred.monto.ToString();
            txtNumeroCuotas.Text = cred.numCuotas.ToString();
            txtTasaInteres.Text = cred.tasaInteres.ToString()+"%";

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
            else Response.Redirect("DetalleCliente.aspx");
        }
    }
}