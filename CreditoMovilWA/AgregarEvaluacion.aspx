<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="AgregarEvaluacion.aspx.cs" Inherits="CreditoMovilWA.AgregarEvaluacion" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h2 {
            font-size: 28px;
            color: #2f7a44;
            text-align: center;
        }
        .section-title {
            font-size: 20px;
            font-weight: bold;
            color: #2f7a44;
            margin-bottom: 10px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-size: 16px;
            color: #333;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
        }
        .btn-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
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
            text-align: center;
        }
        .back-btn {
            background-color: #002e6e;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            color: #fff;
            font-weight: 700;
            font-family: 'Poppins', sans-serif; 
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container">
        <h2>Insertar Evaluación</h2>

        <!-- Cliente Asignado -->
        <div class="form-group">
            <div class="section-title">Cliente Asignado</div>
            <label for="ddlTipoDocumento">Tipo de Documento</label>
            <asp:DropDownList ID="ddlTipoDocumento" runat="server">
                <asp:ListItem Text="Selecciona una opción" Value="" />
                <asp:ListItem Text="DNI" Value="DNI" />
                <asp:ListItem Text="Pasaporte" Value="Pasaporte" />
                <asp:ListItem Text="Carnet de Extranjería" Value="Carnet_Extranjeria" />
            </asp:DropDownList>

            <label for="txtDocumento">Documento</label>
            <asp:TextBox ID="txtDocumento" runat="server" />
        </div>

        <!-- Supervisor Asignado -->
        <div class="form-group">
            <div class="section-title">Supervisor Asignado</div>
            <label for="ddlTipoDocSup">Tipo de Documento</label>
            <asp:DropDownList ID="ddlTipoDocSup" runat="server">
                <asp:ListItem Text="Selecciona una opción" Value="" />
                <asp:ListItem Text="DNI" Value="DNI" />
                <asp:ListItem Text="Pasaporte" Value="Pasaporte" />
                <asp:ListItem Text="Carnet de Extranjería" Value="Carnet_Extranjeria" />
            </asp:DropDownList>

            <label for="txtDocumentoSup">Documento</label>
            <asp:TextBox ID="txtDocumentoSup" runat="server" />
        </div>

        <!-- Botones -->
        <div class="btn-group">
            <asp:Button ID="btnAgregar" runat="server" Text="AGREGAR" CssClass="modify-btn" OnClick="btnAgregar_Click" />
            <asp:Button ID="btnRegresar" runat="server" Text="Regresar" CssClass="back-btn" OnClick="btnBack_Click" />
        </div>

        <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
        <asp:Label ID="lblSuccess" runat="server" CssClass="error-message"></asp:Label>
    </div>
</asp:Content>
