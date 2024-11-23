<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="TotalClientes.aspx.cs" Inherits="CreditoMovilWA.TotalClientes" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
       .header h2 {
            font-size: 24px;
            color: #2f7a44;
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

