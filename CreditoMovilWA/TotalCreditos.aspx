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

        /* Responsividad */
        @media (max-width: 768px) {
            label {
                font-size: 12px; /* Reducir tamaño de texto */
            }
            .input-text, .select-dropdown {
                padding: 8px; /* Reducir padding */
                font-size: 10px; /* Ajustar tamaño de texto */
            }
            .filter-btn {
                padding: 10px; /* Reducir padding */
                font-size: 10px; /* Ajustar tamaño del texto */
                margin-top: 30px; /* Reducir margen */
            }
            th, td {
                padding: 8px; /* Reducir padding en tablas */
                font-size: 10px; /* Ajustar tamaño del texto */
            }
            .modal-content {
                padding: 15px; /* Reducir padding */
                width: 90%; /* Hacer el modal más compacto */
            }
            .save-btn, .cancel-btn {
                font-size: 10px; /* Reducir tamaño del texto */
                padding: 10px; /* Reducir padding */
            }
            h2 {
                font-size: 24px;
            }
        }

        @media (max-width: 480px) {
            label {
                font-size: 10px; /* Ajustar tamaño de texto para móviles */
            }
            .input-text, .select-dropdown {
                font-size: 10px; /* Reducir tamaño de texto */
                padding: 6px; /* Ajustar padding */
            }
            .filter-btn {
                font-size: 8px; /* Ajustar tamaño del texto */
                padding: 8px; /* Reducir padding */
                margin-top: 20px; /* Reducir margen */
            }
            th, td {
                font-size: 8px; /* Reducir tamaño del texto */
                padding: 6px; /* Ajustar padding */
            }
            .modal-content {
                margin: 10% auto; /* Ajustar posición del modal */
                width: 95%; /* Ampliar para pantallas pequeñas */
            }
            .save-btn, .cancel-btn {
                font-size: 8px; /* Ajustar tamaño del texto */
                padding: 8px; /* Reducir padding */
                margin-top: 15px; /* Reducir margen */
            }
            h2 {
                font-size: 20px;
            }
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
            <asp:ListItem Value="Retrasado" Text="Retrasado" />
            <asp:ListItem Value="Activo" Text="Activo" />
            <asp:ListItem Value="Aprobado" Text="Aprobado" />
            <asp:ListItem Value="Desembolsado" Text="Desembolsado" />
            <asp:ListItem Value="Solicitado" Text="Solicitado" />
            <asp:ListItem Value="Cancelado" Text="Cancelado" />
            <asp:ListItem Value="Anulado" Text="Anulado" />
        </asp:DropDownList>

        <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" CssClass="filter-btn" OnClick="btnFiltrar_Click" />

        <!-- Tabla de créditos -->
        <div class="table-container">
            <asp:GridView ID="gvCreditos" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numCredito" HeaderText="ID_CRÉDITO" />
                    <asp:TemplateField HeaderText="CLIENTE">
                        <ItemTemplate>
                            <%# Eval("cliente.nombre") + " " + Eval("cliente.apPaterno") + " " + Eval("cliente.apMaterno") %>
                        </ItemTemplate>
                    </asp:TemplateField>
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
