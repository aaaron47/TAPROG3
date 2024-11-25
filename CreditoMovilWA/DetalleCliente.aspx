<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeBehind="DetalleCliente.aspx.cs" Inherits="CreditoMovilWA.DetalleCliente" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        label {
            font-size: 18px;
            color: #333;
            margin: 10px 0 5px;
            display: block;
        }
        .input-text {
            width: 100%; 
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-bottom: 15px;
        }
        .button {
            background-color: #2f7a44;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        .table-container {
            margin-top: 20px;
            overflow-x: auto;
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
        /* Responsividad */
        @media (max-width: 768px) {
            label {
                font-size: 16px; /* Reducir tamaño del texto */
            }
            .input-text {
                font-size: 14px; /* Reducir tamaño del texto */
                padding: 8px; /* Ajustar padding */
            }
            .button {
                font-size: 14px; /* Reducir tamaño del texto */
                padding: 8px 15px; /* Ajustar padding */
                margin-top: 15px; /* Reducir margen superior */
            }
            th, td {
                font-size: 14px; /* Reducir tamaño del texto en tablas */
                padding: 8px; /* Ajustar padding */
            }
            h2, h3{
                font-size: 20px;
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
            h2, h3{
                font-size: 16px;
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container">
        <h2 id="ncliente" runat ="server">Cliente Nro </h2>
        <asp:Button ID="btnGenerarReporte" runat="server" Text="Generar Reporte" CssClass="button" OnClick="btnGenerarReporte_Click" />

        <label>Nombre Completo</label>
        <asp:TextBox ID="txtNombreCompleto" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Dirección</label>
        <asp:TextBox ID="txtDireccion" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Teléfono</label>
        <asp:TextBox ID="txtTelefono" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <h3>Lista de Créditos</h3>
        <div class="table-container">
            <asp:GridView ID="gvCreditos" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numCredito" HeaderText="ID_CRÉDITO" />
                    <asp:BoundField DataField="Monto" HeaderText="MONTO" />
                    <asp:BoundField DataField="NumCuotas" HeaderText="NUM. CUOTAS" />
                    <asp:BoundField DataField="TasaInteres" HeaderText="TASA INTERÉS" />
                    <asp:BoundField DataField="FechaOtorgamiento" HeaderText="FECHA OTORGAMIENTO" DataFormatString="{0:yyyy-MM-dd}" />
                    <asp:BoundField DataField="Estado" HeaderText="ESTADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalleCredito" runat="server" Text="👁️" CssClass="button" CommandArgument='<%# Eval("numCredito") %>' OnClick="btnVerDetalleCredito_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
    </div>
</asp:Content>
