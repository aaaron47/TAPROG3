<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="TotalCreditos.aspx.cs" Inherits="CreditoMovilWA.TotalCreditos" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        label {
            display: block;
            font-size: 18px;
            color: #333;
            margin: 10px 0 5px;
        }
        .input-text {
            width: calc(100% - 20px); 
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-bottom: 15px;
        }

        .select-dropdown {
            width: 100%; 
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-bottom: 15px;
        }
        .filter-btn {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 40px;
            margin-bottom: 20px;
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
        .pay-btn {
            padding: 5px 10px;
            background-color: #2f7a44;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .message {
            color: red;
            font-size: 16px;
            margin-top: 10px;
        }
        .modal {
            display: none; /* Oculto por defecto */
            position: fixed;
            z-index: 10;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }
        .modal-content {
            background-color: #faf8fc;
            margin: 20% auto;
            padding: 20px 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
            text-align: left;
            border-radius: 10px;
        }
        .close-btn {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        .close-btn:hover, .close-btn:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .save-btn {
            width: 48%; /* Ajusta el ancho para que ambos botones ocupen el mismo espacio */
            background-color: #2f7a44;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
            margin-bottom: 30px;
        }

        .cancel-btn {
            width: 48%; /* Ajusta el ancho para que ambos botones ocupen el mismo espacio */
            background-color: #002e6e;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">


    </script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div class="container">
        <h2>Listado de Créditos</h2>

        <label>Fecha de Inicio</label>
        <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="input-text" TextMode="Date"></asp:TextBox>

        <label>Fecha de Fin</label>
        <asp:TextBox ID="txtFechaFin" runat="server" CssClass="input-text" TextMode="Date"></asp:TextBox>

        <label>Estado del crédito</label>
        <asp:DropDownList ID="ddlEstado" runat="server" CssClass="select-dropdown">
            <asp:ListItem Value="" Text="Seleccionar Estado" />
            <asp:ListItem Value="Activo" Text="Activo" />
            <asp:ListItem Value="Inactivo" Text="Inactivo" />
            <asp:ListItem Value="Pendiente" Text="Pendiente" />
            <asp:ListItem Value="Finalizado" Text="Finalizado" />
        </asp:DropDownList>

        <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="filter-btn" OnClick="btnFiltrar_Click" />

        <!-- Tabla de créditos -->
        <div class="table-container">
            <asp:GridView ID="gvCreditos" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numCredito" HeaderText="ID_CRÉDITO" />
                    <asp:BoundField DataField="Cliente" HeaderText="CLIENTE" />
                    <asp:BoundField DataField="Monto" HeaderText="MONTO" />
                    <asp:BoundField DataField="NumCuotas" HeaderText="NUM. CUOTAS" />
                    <asp:BoundField DataField="TasaInteres" HeaderText="TASA INTERÉS" />
                    <asp:BoundField DataField="FechaOtorgamiento" HeaderText="FECHA OTORGAMIENTO"  DataFormatString="{0:dd/MM/yyyy}"/>
                    <asp:BoundField DataField="Estado" HeaderText="ESTADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="pay-btn" CommandArgument='<%# Eval("numCredito") %>' OnClick="btnVerDetalles_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
                <!-- Botón para generar reporte -->
        <asp:Button ID="btnGenerarReporte" runat="server" Text="Generar Reporte" CssClass="filter-btn" OnClick="btnGenerarReporte_Click" />
    </div>
</asp:Content>
