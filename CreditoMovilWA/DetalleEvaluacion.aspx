<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="DetalleEvaluacion.aspx.cs" Inherits="CreditoMovilWA.DetalleEvaluacion" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h2 {
            font-weight: bold;
            font-size: 28px;
            color: #2f7a44;
            text-align: center;
            margin-bottom: 30px;
        }
        .puntaje {
            font-size: 36px;
            color: #2f7a44;
            font-weight: bold;
            text-align: right;
            margin-right: 20px;
        }
        .form-group {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }
        .form-group label {
            font-size: 16px;
            color: #333;
            flex: 1;
        }
        .form-group input {
            flex: 2;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-left: 10px;
        }
        .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-top: 10px;
            resize: none;
        }
        .modify-btn {
            padding: 10px 20px;
            font-size: 16px;
            font-weight: 700;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            width: 150px;
            text-align: center;
            margin: 20px auto;
        }
        .row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }
        .label-group {
            flex: 1;
            margin-right: 15px; /* Añade espacio entre columnas */
        }
        .input-text{
            width:100%;
        }
        .back-btn{
            background-color: #002e6e;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 13px;
            border-radius: 5px;
            color: #fff;
            font-weight: 700;
            font-family: 'Poppins', sans-serif; 
            margin-right: 100px;
            margin-top:20px;
            margin-bottom: 20px;
        }
        .puntaje-display {
            font-size: 48px; /* Tamaño del texto */
            font-weight: bold; /* Texto en negrita */
            color: #7a7a7a; /* Texto plomo */
            text-align: center; /* Centrar el texto */
            border: none; /* Sin bordes */
            background-color: transparent; /* Fondo transparente */
            outline: none; /* Sin borde al hacer clic */
            width: 100%; /* Ocupa todo el espacio disponible */
            font-family: 'Poppins', sans-serif; /* Fuente elegante */
        }
        .label {
            font-weight: bold;
            display: block; /* Asegura que el margen superior se aplique correctamente */
            font-size: 16px; /* Tamaño del texto (opcional) */
            color: #000000; /* Color del texto (opcional) */
            font-family: 'Poppins', sans-serif; /* Fuente elegante (opcional) */
        }
        .container {
            max-width: 1000px;
        }

        /* Responsividad */
        @media (max-width: 768px) {
            h2 {
                font-size: 24px;
            }
            .form-group {
                flex-direction: column; /* Colocar elementos en columna */
                align-items: flex-start;
            }
            .form-group label {
                font-size: 14px;
                margin-bottom: 5px;
            }
            .form-group input, .form-group textarea {
                width: 100%; /* Usar todo el ancho disponible */
                margin-left: 0; /* Quitar margen izquierdo */
            }
            .modify-btn, .back-btn {
                font-size: 14px;
                padding: 8px 15px;
                width: 100%; /* Usar todo el ancho */
                max-width: none;
            }
            .puntaje-display {
                font-size: 28px;
            }
            label,.input-text, .form-control{
                font-size: 14px;
            }
        }

        @media (max-width: 480px) {
            h2 {
                font-size: 20px;
            }
            .puntaje {
                font-size: 24px;
            }
            .form-group label {
                font-size: 12px;
            }
            .form-group input, .form-group textarea {
                font-size: 12px;
                padding: 6px;
            }
            .modify-btn, .back-btn {
                font-size: 12px;
                padding: 6px 10px;
            }
            .puntaje-display {
                font-size: 20px;
            }
            .img-fluid {
                max-width: 100%; /* Asegurar que la imagen ocupe el ancho del contenedor */
            }
            label, .input-text, .form-control{
                font-size: 10px;
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div class="container">
        <h2>Evaluación Nro <asp:Label ID="lblNumeroEvaluacion" runat="server" Text="XXXXXXXXXX"></asp:Label></h2>
        
        <div class="form-group">
            <label class="label">Nombre del Negocio</label>
            <asp:TextBox ID="txtNombreNegocio" runat="server" CssClass="label-group" ReadOnly="true" />
            <label class="label">Fecha de Registro</label>
            <asp:TextBox ID="txtFechaRegistro" runat="server" CssClass="input-text" ReadOnly="true" />
        </div>
        
        <div class="form-group">
            <label class="label">Dirección del Negocio</label>
            <asp:TextBox ID="txtDireccionNegocio" runat="server" CssClass="label-group" ReadOnly="true" />
            <label class="label">Teléfono del Negocio</label>
            <asp:TextBox ID="txtTelefonoNegocio" runat="server" CssClass="input-text" ReadOnly="true" />
        </div>

        <div class="form-group">
            <label class="label">Nombre del Cliente Asignado</label>
            <asp:TextBox ID="txtClienteAsignado" runat="server" CssClass="label-group" ReadOnly="true" />
            <label class="label">Margen de Ganancia</label>
            <asp:TextBox ID="txtMargenGanancia" runat="server" CssClass="input-text" ReadOnly="true" />
        </div>
        
        <div class="form-group">
            <label class="label">Ventas Diarias</label>
            <asp:TextBox ID="txtVentasDiarias" runat="server" CssClass="label-group" ReadOnly="true" />
            <label class="label">Inventario</label>
            <asp:TextBox ID="txtInventario" runat="server" CssClass="input-text" ReadOnly="true" />
        </div>

        <div class="form-group">
            <label class="label">Costo de Ventas</label>
            <asp:TextBox ID="txtCostoVentas" runat="server" CssClass="label-group" ReadOnly="true" />
            <label class="label">Estado</label>
            <asp:TextBox ID="txtEstado" runat="server" CssClass="input-text" ReadOnly="true" />
        </div>

        <label style="margin-top: 30px;" class="label">Observaciones</label>
        <asp:TextBox ID="txtObservaciones" runat="server" CssClass="input-text" TextMode="MultiLine" Rows="3" ReadOnly="true" />

        <label style="margin-top: 20px;" class="label">Puntaje</label>
        <asp:TextBox ID="txtPuntaje" runat="server" CssClass="puntaje-display" ReadOnly="true" />

        <label style="margin-top: 20px;" class="label">Foto</label>
        <asp:Image ID="imgEvaluacionFoto" runat="server" CssClass="img-fluid img-thumbnail" ImageUrl="/Images/placeholder.jpg" Height="235" Width="720" />
        <asp:FileUpload ID="fileUploadEvaluacionFoto" CssClass="form-control mb-2" runat="server" onchange="this.form.submit()" ClientIDMode="Static" />

        <!-- Puntaje y botón modificar -->
        <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 20px;">
            <asp:Button ID="btnModificar" runat="server" Text="MODIFICAR" CssClass="modify-btn" OnClick="btnModificar_Click" />
            <asp:Button ID="btnRegresar" runat="server" Text="Regresar" CssClass="back-btn" OnClick="btnBack_Click" />
        </div>
    </div>
</asp:Content>


