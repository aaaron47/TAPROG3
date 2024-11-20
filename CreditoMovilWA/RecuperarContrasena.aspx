<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Usuario.master" CodeFile="RecuperarContrasena.aspx.cs" Inherits="CreditoMovilWA.RecuperarContrasena" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        .container{
            max-width: 650px;
        }
        h1 {
            font-size: 32px;
            color: #265f21;
            font-weight: 700;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            color: #333;
            margin-bottom: 20px;
        }
        .form-group {
            display: flex;
            text-align: left;
            flex-direction: column;
            margin-bottom: 15px;
        }
        .form-group label {
            font-size: 16px;
            color: #333;
            margin-bottom: 5px;
        }
        .form-group input {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            width: 100%;
            box-sizing: border-box;
        }
        .btn-login {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: 700;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .btn-login:hover {
            background-color: #265f21;
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Encabezado con logo -->
    <div class="header">
        <img src="images/credit2.png" alt="Logo Crédito Móvil">
    </div>

    <div class="container">
        <h1>Recuperar Contraseña</h1>
        <div class="form-group">
            <label for="contrasena">Nueva Contraseña:</label>
            <asp:TextBox ID="txtContra" runat="server" TextMode="Email" CssClass="form-control" placeholder="Contrasena"></asp:TextBox>
            <label for="contrasena">Confirmar Contraseña:</label>
            <asp:TextBox ID="txtContraConf" runat="server" TextMode="Email" CssClass="form-control" placeholder="Contrasena"></asp:TextBox>
            <asp:Button ID="btnRecuperar" runat="server" Text="Recuperar Contrasena" CssClass ="btn-login" OnClick="btnRecuperar_Click" />
        </div>
    </div>
</asp:Content>