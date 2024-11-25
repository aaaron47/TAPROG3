<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="DetalleCredito.aspx.cs" Inherits="CreditoMovilWA.DetalleCredito" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h1 {
            font-size: 28px;
            color: #2f7a44;
            font-weight: 700;
            margin-bottom: 20px;
            text-align: center;
        }
        label {
            display: block;
            font-size: 18px;
            color: #333;
            margin: 10px 0 5px;
        }
        .input-text, .select-dropdown {
            width: 97%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-bottom: 15px;
            pointer-events: none; /*read only */
        }
        .section-title {
            font-size: 18px;
            font-weight: 700;
            color: #2f7a44;
            margin: 20px 0 10px;
            text-align: left;
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
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .back-btn {
            background-color: #002e6e;
            color: white;
            border: none;
            padding: 10px 30px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            width: 45%;
            margin-left: 50px;
            margin-right: 50px;
            margin-bottom: 20px;
        }
        .modify-btn {
            background-color: #2f7a44;
            color: white;
            border: none;
            padding: 10px 30px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
            width: 45%;
            margin-right: 50px;
            margin-left: 50px;
            margin-bottom: 20px;
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
                padding: 4px 9px; /* Ajustar padding */
                margin-top: 9px; /* Reducir margen superior */
            }
            th, td {
                font-size: 10px; /* Reducir tamaño del texto en tablas */
                padding: 6px; /* Ajustar padding */
            }
            .section-title {
                font-size: 16px;
            }
            h1{
                font-size: 20px;
            }
            .back-btn {
                font-size: 12px;
            }
            .modify-btn {
                font-size: 12px;
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
            .section-title {
                font-size: 12px;
            }
            h1{
                font-size: 16px;
            }
            .back-btn {
                font-size: 10px;
            }
            .modify-btn {
                font-size: 10px;
            }
        }
    </style>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container">
        <h1 id="idCredito" runat="server">Crédito Nro </h1>

        <label>Fecha de Otorgamiento</label>
        <asp:TextBox ID="txtFechaOtorgamiento" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Estado</label>
        <asp:TextBox ID="txtEstado" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Monto</label>
        <asp:TextBox ID="txtMonto" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Número de Cuotas</label>
        <asp:TextBox ID="txtNumeroCuotas" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <label>Tasa de Interés</label>
        <asp:TextBox ID="txtTasaInteres" runat="server" CssClass="input-text" ReadOnly="true"></asp:TextBox>

        <asp:Button ID="btnDesembolso" runat="server" CssClass="back-btn" OnClick="btnDesembolso_Click"></asp:Button>

        

        <div class="section-title">LISTADO DE TRANSACCIONES</div>
        <div class="table-container">
            <asp:GridView ID="gvTransacciones" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numOperacion" HeaderText="ID_TRANS." />
                    <asp:BoundField DataField="fecha" HeaderText="FECHA" DataFormatString="{0:dd/MM/yyyy}"/>
                    <asp:BoundField DataField="concepto" HeaderText="CONCEPTO" />
                    <asp:BoundField DataField="monto" HeaderText="MONTO" />
                    <asp:BoundField DataField="anulado" HeaderText="ANULADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="view-btn" CommandArgument='<%# Eval("numOperacion") %>' OnClick="btnVerDetalleTransaccion_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>

        <div class="button-container">
            <asp:Button ID="btnRegresar" runat="server" Text="Regresar" CssClass="back-btn" OnClick="btnBack_Click"/>
             <asp:Button ID="btnModificar" runat="server" Text="Modificar" CssClass="modify-btn" OnClick="btnModificar_Click" Visible="false" />
        </div>

    </div>
</asp:Content>

