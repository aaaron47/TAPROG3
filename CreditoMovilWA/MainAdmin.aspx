<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Usuario.master" CodeFile="MainAdmin.aspx.cs" Inherits="CreditoMovilWA.MainAdmin" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h1 {
            font-size: 40px;
            color: #265f21;
            font-weight: 700;
            margin-top: 40px;
            margin-bottom: 20px;
        }
        p {
            margin-top: 10px;
            font-size: 20px;
            color: #333;
        }

        /* Estilos para los botones */
        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .button {
            background-color: #2f7a44;
            font-family: 'Poppins', sans-serif;
            color: #ffffff;
            border: none;
            border-radius: 8px;
            padding: 15px 20px;
            font-size: 1em;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            min-width: 150px;
            margin-top: 40px;
        }

        .button:hover {
            background-color: #357D3C;
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div>
        <h1 id="hola" runat="server">¡Hola, </h1>
        <p>¿Qué desea realizar el día de hoy?</p>
        <div class="button-container">
            <asp:Button ID="btnVisualizarCreditos" runat="server" Text="Visualizar Créditos" CssClass="button" OnClick="btnVisualizarCreditos_Click" />
            <asp:Button ID="btnVisualizarClientes" runat="server" Text="Visualizar Clientes" CssClass="button" OnClick="btnVisualizarClientes_Click" />
            <asp:Button ID="btnVisualizarEvaluaciones" runat="server" Text="Visualizar Evaluaciones" CssClass="button" OnClick="btnVisualizarEvaluaciones_Click" />
        </div>
    </div>
</asp:Content>
