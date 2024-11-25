<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="TotalClientes.aspx.cs" Inherits="CreditoMovilWA.TotalClientes" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        .header h2 {
                font-size: 24px;
                color: #2f7a44;
        }
        .container{
            width: 1000px;
        }
        .filter-container {
            margin: 20px 0;
        }
        .filter-container label {
            display: block;
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
        }
        .input-text {
            width: calc(50% - 10px);
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            margin-right: 10px;
        }
        .filter-btn {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .table-container {
            margin-top: 20px;
            overflow-x: auto;
        }
        .client-table {
            width: 100%;
            border-collapse: collapse;
        }
        .client-table th, .client-table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        .client-table th {
            background-color: #2f7a44;
            color: #fff;
        }
        .view-btn {
            background-color: transparent;
            border: none;
            cursor: pointer;
            font-size: 18px;
        }

        @media (max-width: 768px) {
            .header h2 {
                font-size: 16px; /* Reducir tamaño del título */
            }
            .filter-container {
                flex-direction: column; /* Cambiar disposición a columna */
                gap: 15px;
            }
            .filter-container label {
                font-size: 12px; /* Reducir tamaño del texto */
            }
            .input-text {
                width: calc(100% - 10px); /* Ajustar ancho del input */
                margin-bottom: 10px; /* Agregar espacio entre campos */
                font-size: 12px;
            }
            .filter-btn {
                width: 100%; /* Botón ocupa todo el ancho */
                padding: 10px; /* Ajustar padding */
                font-size: 10px; /* Reducir tamaño de texto */
            }
            .client-table th, .client-table td {
                font-size: 10px; /* Reducir tamaño de texto */
                padding: 8px; /* Ajustar padding */
            }
        }

        @media (max-width: 480px) {
            .header h2 {
                font-size: 14px; /* Reducir más el tamaño del título */
            }
            .filter-container label {
                font-size: 10px; /* Reducir tamaño del texto */
            }
            .input-text {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 8px; /* Ajustar padding */
            }
            .filter-btn {
                font-size: 8px; /* Reducir tamaño del texto */
                padding: 8px; /* Reducir padding */
            }
            .client-table th, .client-table td {
                font-size: 8px; /* Reducir tamaño de texto */
                padding: 6px; /* Reducir padding */
            }
            .view-btn {
                font-size: 12px; /* Ajustar tamaño del botón */
            }
        }
    </style>

    <script type="text/javascript">


    </script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container">
        <!-- Encabezado y cierre de sesión -->

        <!-- Filtro de rango de puntajes -->
        <div class="filter-container">
            <label>Rango de Puntajes</label>
            <asp:TextBox ID="txtPuntajeMin" runat="server" CssClass="input-text" Placeholder="Mínimo"></asp:TextBox>
            <asp:TextBox ID="txtPuntajeMax" runat="server" CssClass="input-text" Placeholder="Máximo"></asp:TextBox>
            <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="filter-btn" OnClick="FiltrarClientes_Click" />
        </div>

        <!-- Tabla de clientes -->
        <div class="table-container">
            <asp:GridView ID="gvClientes" runat="server" AutoGenerateColumns="false" CssClass="client-table">
                <Columns>
                    <asp:BoundField DataField="codigoCliente" HeaderText="ID_CLIENTE" />
                    <asp:BoundField DataField="Nombre" HeaderText="NOMBRE" />
                    <asp:BoundField DataField="apPaterno" HeaderText="AP.PATERNO" />
                    <asp:BoundField DataField="apMaterno" HeaderText="AP.MATERNO" />
                    <asp:BoundField DataField="Telefono" HeaderText="TELEFONO" />
                    <asp:BoundField DataField="Email" HeaderText="EMAIL" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="view-btn" CommandArgument='<%# Eval("codigoCliente") %>' OnClick="VerDetalleCliente_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>

            <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
        </div>
    </div>
</asp:Content>

