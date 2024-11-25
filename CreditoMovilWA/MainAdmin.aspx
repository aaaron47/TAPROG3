<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="MainAdmin.aspx.cs" Inherits="CreditoMovilWA.MainAdmin" %>

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
        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        .button {
            background-color: #2f7a44;
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

        /* Responsividad */
        @media (max-width: 768px) {
            h1 {
                font-size: 28px; /* Reducir tamaño del título */
                margin-top: 30px;
                margin-bottom: 15px;
            }
            p {
                font-size: 14px; /* Reducir tamaño de fuente del párrafo */
            }
            .button {
                padding: 12px 16px; /* Reducir padding */
                font-size: 0.75em; /* Reducir tamaño de fuente */
                min-width: 130px; /* Reducir ancho mínimo del botón */
            }
            .button-container {
                gap: 15px; /* Reducir el espacio entre botones */
            }
        }

        @media (max-width: 480px) {
            h1 {
                font-size: 24px; /* Reducir más el tamaño del título */
                margin-top: 20px;
                margin-bottom: 10px;
            }
            p {
                font-size: 12px; /* Ajustar tamaño de fuente del párrafo */
            }
            .button {
                padding: 8px 10px; /* Reducir padding aún más */
                font-size: 0.6em; /* Reducir tamaño de fuente */
                min-width: 110px; /* Ajustar ancho mínimo */
                width: 80%;
                align-content: center;
                margin: 5px auto;
                
            }
            .button-container {
                gap: 10px; /* Reducir espacio entre botones */
                flex-direction: column; /* Cambiar los botones a disposición vertical */
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div>
        <h1 id="hola" runat="server">¡Hola, </h1>
        <p>¿Qué desea realizar el día de hoy?:</p>
        <asp:Label ID="lblRanking" runat="server" CssClass="ranking-label" Font-Size="0px"></asp:Label>

        <div class="button-container">
            <asp:Button ID="btnVisualizarCreditos" runat="server" Text="Visualizar Créditos" CssClass="button" OnClick="btnVisualizarCreditos_Click" />
            <asp:Button ID="btnVisualizarClientes" runat="server" Text="Visualizar Clientes" CssClass="button" OnClick="btnVisualizarClientes_Click" />
            <asp:Button ID="btnVisualizarEvaluaciones" runat="server" Text="Visualizar Evaluaciones" CssClass="button" OnClick="btnVisualizarEvaluaciones_Click" />
        </div>
    </div>
</asp:Content>
