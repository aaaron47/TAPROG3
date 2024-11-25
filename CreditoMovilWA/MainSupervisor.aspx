<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="MainSupervisor.aspx.cs" Inherits="CreditoMovilWA.MainSupervisor" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h2 {
            font-size: 28px;
            color: #2f7a44;
        }
        .table-container {
            margin-top: 20px;
            overflow-x: auto;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #2f7a44;
            color: #fff;
        }
        .view-btn {
            padding: 5px 10px;
            background-color: #2f7a44;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        /* Responsividad */
        @media (max-width: 768px) {
            label {
                font-size: 12px; /* Reducir tamaño del texto */
            }
            .input-text {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 6px; /* Ajustar padding */
            }
            .button {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 6px 13px; /* Ajustar padding */
                margin-top: 10px; /* Reducir margen superior */
            }
            th, td {
                font-size: 10px; /* Reducir tamaño del texto en tablas */
                padding: 6px; /* Ajustar padding */
            }
            h2{
                font-size: 20px;
            }
            p{
                font-size: 16px;
            }
        }

        @media (max-width: 480px) {
            label {
                font-size: 10px; /* Reducir más el tamaño del texto */
            }
            .input-text {
                font-size: 8px; /* Reducir tamaño del texto */
                padding: 4px; /* Reducir padding */
            }
            .button {
                font-size: 8px; /* Reducir tamaño del texto */
                padding: 4px 8px; /* Reducir padding */
                margin-top: 8px; /* Ajustar margen superior */
            }
            th, td {
                font-size: 8px; /* Reducir tamaño del texto en tablas */
                padding: 4px; /* Reducir padding */
            }
            h2{
                font-size: 16px;
            }
            p{
                font-size: 12px;
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div class="container">
        <h2 id="hola" runat="server">¡Hola, </h2>
        <p>Estas son las evaluaciones que posees:</p>

        <!-- Tabla de evaluaciones -->
        <div class="table-container">
            <asp:GridView ID="gvEvaluaciones" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numeroEvaluacion" HeaderText="ID_EVALUACION" />
                    <asp:BoundField DataField="nombreNegocio" HeaderText="NEGOCIO" />
                    <asp:BoundField DataField="ventasDiarias" HeaderText="VENTAS" />
                    <asp:BoundField DataField="margenGanancia" HeaderText="MR. GANANCIA" />
                    <asp:BoundField DataField="puntaje" HeaderText="PUNTAJE" />
                    <asp:BoundField DataField="activo" HeaderText="ESTADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="view-btn" CommandArgument='<%# Eval("numeroEvaluacion") %>' OnClick="btnVerDetalle_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>
