<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="TotalEvaluaciones.aspx.cs" Inherits="CreditoMovilWA.TotalEvaluaciones" %>

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
        .filter-section {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
            flex-wrap: wrap;
        }
        .form-group {
            flex: 1;
            display: flex;
            flex-direction: column;
        }
        /* Botones */
        .button-group {
            display: flex;
            justify-content: space-between;
            gap: 10px;
            margin-bottom: 20px;
        }
        .filter-btn,
        .add-btn {
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .filter-btn {
            background-color: #28a745; /* Verde */
        }
        .add-btn {
            background-color: #007bff; /* Azul */
        }
        .filter-btn:hover,
        .add-btn:hover {
            opacity: 0.9;
        }
        /* Tabla */
        .table-container {
            margin-top: 20px;
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
        .styled-table {
            width: 100%;
            border-collapse: collapse;
        }
        .styled-table th,
        .styled-table td {
            text-align: left;
            padding: 10px;
            border: 1px solid #ddd;
        }
        .styled-table th {
            background-color: #003366;
            color: white;
        }
        .styled-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        /* Botón de ver detalles */
        .view-btn {
            padding: 5px 10px;
            background-color: #17a2b8; /* Azul claro */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .view-btn:hover {
            background-color: #138496;
        }

        /* Responsividad */
        @media (max-width: 768px) {
            label {
                font-size: 12px; /* Reducir tamaño del texto */
            }
            .filter-section {
                flex-direction: column; /* Cambiar a disposición en columna */
                gap: 15px; /* Reducir espacio entre elementos */
            }
            .form-group {
                width: 100%; /* Asegurar que los inputs ocupen todo el ancho */
            }
            .input-text {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 8px; /* Reducir padding */
            }
            .button-group {
                flex-direction: column; /* Disposición vertical para botones */
                gap: 10px;
            }
            .filter-btn,
            .add-btn {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 8px 15px; /* Reducir padding */
            }
            th, td {
                font-size: 10px; /* Reducir tamaño del texto en tablas */
                padding: 8px; /* Reducir padding */
            }
            .view-btn {
                font-size: 10px; /* Ajustar tamaño del botón */
            }
            h2 {
                font-size: 22px;
            }
        }

        @media (max-width: 480px) {
            label {
                font-size: 10px; /* Reducir más el tamaño del texto */
            }
            .filter-section {
                gap: 10px; /* Reducir espacio entre elementos */
            }
            .input-text {
                font-size: 8px; /* Reducir tamaño del texto */
                padding: 6px; /* Ajustar padding */
            }
            .filter-btn,
            .add-btn {
                font-size: 8px; /* Ajustar tamaño del texto */
                padding: 6px 10px; /* Reducir padding */
            }
            th, td {
                font-size: 8px; /* Reducir tamaño del texto en tablas */
                padding: 6px; /* Reducir padding */
            }
            .view-btn {
                font-size: 8px; /* Ajustar tamaño del botón */
                padding: 5px 8px; /* Reducir padding */
            }
            h2 {
                font-size: 18px;
            }
        }
    </style>

    <script type="text/javascript">
        

    </script>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div class="container">
        <h2>Listado de Evaluaciones</h2>

        <label>Fecha de Inicio</label>
        <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="input-text" TextMode="Date"></asp:TextBox>

        <label>Fecha de Fin</label>
        <asp:TextBox ID="txtFechaFin" runat="server" CssClass="input-text" TextMode="Date"></asp:TextBox>

                <!-- Botones -->
        <div class="button-group">
            <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="filter-btn" OnClick="btnFiltrar_Click" />
            <asp:Button ID="btnAgregar" runat="server" Text="Agregar Evaluación" CssClass="add-btn" OnClick="btnAgregar_Click" />
        </div>

        <!-- Tabla de créditos -->
        <div class="table-container">
            <asp:Label ID="lblRetrasado" runat="server" CssClass="error-message"></asp:Label>
            <asp:GridView ID="gvCreditos" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numeroEvaluacion" HeaderText="ID_EVALUACION" />
                    <asp:BoundField DataField="costoVentas" HeaderText="COSTO DE VENTAS" />
                    <asp:BoundField DataField="inventario" HeaderText="INVENTARIO" />
                    <asp:BoundField DataField="ventasDiarias" HeaderText="VENTAS DIARIAS" />
                    <asp:BoundField DataField="margenGanancia" HeaderText="MARGEN GANANCIA"/>
                    <asp:BoundField DataField="puntaje" HeaderText="PUNTAJE" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="pay-btn" CommandArgument='<%# Eval("numeroEvaluacion") %>' OnClick="btnVerDetalles_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
    </div>
</asp:Content>