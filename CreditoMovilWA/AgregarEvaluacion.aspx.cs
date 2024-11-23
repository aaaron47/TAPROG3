using CreditoMovilWA.CreditoMovil;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CreditoMovilWA
{
    public partial class AgregarEvaluacion : System.Web.UI.Page
    {
        private EvaluacionWSClient daoEvaluacion = new EvaluacionWSClient();
        private ClienteWSClient daoCliente = new ClienteWSClient();
        private SupervisorWSClient daoSupervisor = new SupervisorWSClient();

        protected void Page_Init(object sender, EventArgs e)
        {
            
            administrador admin = (administrador)Session["Administrador"];
            if (admin == null)
            {
                Response.Redirect("Login.aspx");
               
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

            }
        }

        protected void btnAgregar_Click(object sender, EventArgs e)
        {
            evaluacion ev = new evaluacion();
            ev.nombreNegocio = "";
            ev.puntaje = 0;
            ev.activo = true;
            ev.costoVentas = 0;
            ev.direccionNegocio = "";
            ev.fechaRegistro = DateTime.Now;
            ev.fechaRegistroSpecified = true;
            ev.foto = null;
            ev.inventario = 0;
            ev.margenGanancia = 0;
            ev.observaciones = "";
            ev.telefonoNegocio = "";
            ev.ventasDiarias = 0;
            ev.numeroEvaluacion = 20;

            cliente cli = daoCliente.obtenerPorDocIdenCliente(txtDocumento.Text, ddlTipoDocumento.SelectedValue.ToString());

            supervisor sup = daoSupervisor.obtenerPorDocIdenSup(txtDocumentoSup.Text, ddlTipoDocSup.SelectedValue.ToString());

            cliente1 cli2 = new cliente1();
            cli2.activo = cli.activo;
            cli2.fechaVencimiento = cli.fechaVencimiento;
            cli2.apMaterno = cli.apMaterno;
            cli2.apPaterno = cli.apPaterno;
            cli2.codigoCliente = cli.codigoCliente;
            cli2.contrasenha = cli.contrasenha;
            cli2.direccion = cli.direccion;
            cli2.documento = cli.documento;
            cli2.email = cli.email;
            cli2.fecha = cli.fecha;
            cli2.idUsuario = cli.idUsuario;
            cli2.nombre = cli.nombre;
            cli2.ranking = cli.ranking;
            cli2.telefono = cli.telefono;
            cli2.tipoCliente = cli.tipoCliente;
            cli2.tipoDocumento = cli2.tipoDocumento;
            cli2.ultimoLogueo = cli.ultimoLogueo;

            ev.clienteAsignado = cli2;
            ev.evaluador = sup;
            
            // aca pa actualizar base de dates
            daoEvaluacion.insertarEvaluacion(ev,sup.codigoEv,cli.codigoCliente);
        }

        protected void btnBack_Click(object sender, EventArgs e)
        {
            Response.Redirect("TotalEvaluaciones.aspx");
        }
    }
}