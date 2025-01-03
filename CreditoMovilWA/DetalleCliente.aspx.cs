﻿using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class DetalleCliente : System.Web.UI.Page
    {

        private ClienteWSClient daoCliente = new ClienteWSClient();
        private CreditoWSClient daoCredito = new CreditoWSClient();
        protected void Page_Init(object sender, EventArgs e)
        {
            administrador1 admin = (administrador1)Session["Administrador"];
            if (admin == null)
            {
                Response.Redirect("Login.aspx");
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int id = (int)Session["IdCliente"];
                cliente cli = daoCliente.obtenerPorIDCliente(id);
                CargarCliente(cli);
                CargarCreditos(cli);
            }
        }

        private void CargarCliente(cliente cli)
        {

            ncliente.InnerText += cli.codigoCliente.ToString();
            txtNombreCompleto.Text = cli.nombre + " " + cli.apPaterno + " " + cli.apMaterno;
            txtDireccion.Text = cli.direccion;
            txtTelefono.Text = cli.telefono;

        }

        private void CargarCreditos(cliente cli)
        {
            var creditos = daoCredito.listarCreditosCliente(cli.codigoCliente);
            if(creditos != null)
            {
                gvCreditos.DataSource = creditos;
                gvCreditos.DataBind();
                lblError.Text = "";
            }
            else
            {
                gvCreditos.DataSource = null;
                gvCreditos.DataBind();
                lblError.Text = "No se encontraron créditos para el cliente.";
            }
        }

        protected void btnVerDetalleCredito_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            int idCredito = int.Parse(btn.CommandArgument);
            Session["idCredito"] = idCredito;
            Response.Redirect("DetalleCredito.aspx");
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            int id = (int)Session["IdCliente"];
            Byte[] reporte = daoCliente.generarReporte(id);
            if(reporte != null)
            {
                Response.ContentType = "application/pdf";
                Response.AddHeader("content-length", reporte.Length.ToString());
                Response.BinaryWrite(reporte);
            }
            else
            {
                lblError.Text = "Error al generar el reporte.";
            }

        }

    }
}