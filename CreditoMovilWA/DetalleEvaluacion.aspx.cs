﻿using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class DetalleEvaluacion : System.Web.UI.Page
    {
        private EvaluacionWSClient daoEvaluacion = new EvaluacionWSClient();
        private bool modoEdicion;

        protected void Page_Init(object sender, EventArgs e)
        {
            modoEdicion = false;
            btnModificar.Text = "MODIFICAR";

            supervisor sup = (supervisor)Session["Supervisor"];
            administrador admin = (administrador)Session["Administrador"];
            if (sup == null && admin == null)
            {
                Response.Redirect("Login.aspx");
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosEvaluacion();
                DeshabilitarCampos(); //inicialmente inhabilitad
            }
        }

        private void CargarDatosEvaluacion()
        {

            string idEvaluacion = (string)Session["idEvaluacion"];
            lblNumeroEvaluacion.Text = idEvaluacion;
            evaluacion ev = daoEvaluacion.obtenerPorIDEvaluacion(Int32.Parse(idEvaluacion));

            // ejemplo pa probart
            txtNombreNegocio.Text = ev.nombreNegocio;
            txtFechaRegistro.Text = ev.fechaRegistro.ToString("dd/MM/yyyy");
            txtDireccionNegocio.Text = ev.direccionNegocio;
            txtTelefonoNegocio.Text = ev.telefonoNegocio;
            txtClienteAsignado.Text = ev.clienteAsignado.nombre + " " + ev.clienteAsignado.apPaterno + " " + ev.clienteAsignado.apMaterno;

            txtMargenGanancia.Text = ev.margenGanancia.ToString();
            txtVentasDiarias.Text = ev.ventasDiarias.ToString();
            txtInventario.Text = ev.inventario.ToString();
            txtCostoVentas.Text = ev.costoVentas.ToString();
            txtEstado.Text = ev.activo ? "Activo" : "Inactivo";
            txtObservaciones.Text = ev.observaciones.ToString();
            txtPuntaje.Text = ev.puntaje.ToString();

            // Actualizar el color del puntaje
            ActualizarColorPuntaje();

            Session["evaluacion"] = ev;
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            // alternar 
            modoEdicion = !modoEdicion;

            if (btnModificar.Text == "MODIFICAR")
            {
                HabilitarCampos();
                btnModificar.Text = "GUARDAR";
            }
            else
            {
                GuardarDatosEvaluacion();
                DeshabilitarCampos();
                btnModificar.Text = "MODIFICAR";
                // Recargar el color del puntaje después de guardar los cambios
                ActualizarColorPuntaje();
            }
        }

        private void HabilitarCampos()
        {
            txtNombreNegocio.ReadOnly = false;
            txtFechaRegistro.ReadOnly = false;
            txtDireccionNegocio.ReadOnly = false;
            txtTelefonoNegocio.ReadOnly = false;
            txtClienteAsignado.ReadOnly = false;
            txtMargenGanancia.ReadOnly = false;
            txtVentasDiarias.ReadOnly = false;
            txtInventario.ReadOnly = false;
            txtCostoVentas.ReadOnly = false;
            txtEstado.ReadOnly = false;
            txtObservaciones.ReadOnly = false;
            txtPuntaje.ReadOnly = false;
        }

        private void DeshabilitarCampos()
        {
            txtNombreNegocio.ReadOnly = true;
            txtFechaRegistro.ReadOnly = true;
            txtDireccionNegocio.ReadOnly = true;
            txtTelefonoNegocio.ReadOnly = true;
            txtClienteAsignado.ReadOnly = true;
            txtMargenGanancia.ReadOnly = true;
            txtVentasDiarias.ReadOnly = true;
            txtInventario.ReadOnly = true;
            txtCostoVentas.ReadOnly = true;
            txtPuntaje.ReadOnly = true;
            txtEstado.ReadOnly = true;
            txtObservaciones.ReadOnly = true;
        }

        private void GuardarDatosEvaluacion()
        {
            evaluacion ev = (evaluacion)Session["evaluacion"];
            ev.nombreNegocio = txtNombreNegocio.Text;
            ev.fechaRegistro = DateTime.Parse(txtFechaRegistro.Text);
            ev.direccionNegocio = txtDireccionNegocio.Text;
            ev.telefonoNegocio = txtTelefonoNegocio.Text;
            //logica para el cliente asignado
            string clienteAsignado = txtClienteAsignado.Text;
            //logica para el cliente asignado
            ev.margenGanancia = Double.Parse(txtMargenGanancia.Text);
            ev.ventasDiarias = Double.Parse(txtVentasDiarias.Text);
            ev.inventario = Double.Parse(txtInventario.Text);
            ev.costoVentas = Double.Parse(txtCostoVentas.Text);
            ev.activo = txtEstado.Text == "Activo" ? true : false;
            ev.observaciones = txtObservaciones.Text;
            ev.puntaje = Double.Parse(txtPuntaje.Text);

            // aca pa actualizar base de dates
            daoEvaluacion.modificarEvaluacion(ev);
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            if ((supervisor)Session["Supervisor"] != null) Response.Redirect("MainSupervisor.aspx");
            Response.Redirect("TotalEvaluaciones.aspx");
        }

        private void ActualizarColorPuntaje()
        {
            // Obtener el puntaje actual
            int puntaje = int.TryParse(txtPuntaje.Text, out int resultado) ? resultado : 0;

            // Determinar el color según el puntaje
            string color = "#000000"; // Color por defecto (negro)
            if (puntaje >= 0 && puntaje < 20) color = "#ff0000"; // Rojo
            else if (puntaje >= 20 && puntaje < 40) color = "#ff8c00"; // Naranja
            else if (puntaje >= 40 && puntaje < 60) color = "#ffd700"; // Amarillo
            else if (puntaje >= 60 && puntaje < 80) color = "#7fff00"; // Verde claro
            else if (puntaje >= 80 && puntaje <= 100) color = "#006400"; // Verde oscuro

            // Aplicar el estilo en línea al TextBox
            txtPuntaje.Attributes.Add("style", $"color: {color}; font-weight: bold;");
        }
    }
}