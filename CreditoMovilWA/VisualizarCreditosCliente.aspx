﻿<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Usuario.master" CodeFile="VisualizarCreditosCliente.aspx.cs" Inherits="CreditoMovilWA.VisualizarCreditos" %>

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
        function openModal() {
            document.getElementById("PagoModal").style.display = "block";
        }
        function closeModal() {
            document.getElementById("PagoModal").style.display = "none";
        }

        document.addEventListener("DOMContentLoaded", function () {
            if (typeof bancosData !== 'undefined') {
                console.log("Bancos cargados:", bancosData);
            } else {
                console.error("No se pudo cargar la lista de bancos.");
            }
        });

        function mostrarCamposPago() {
            var metodo = document.getElementById("metodoPago").value;
            var infoBanco = document.getElementById("infoBanco");
            var infoBilletera = document.getElementById("infoBilletera");

            // Mostrar u ocultar los campos según el método seleccionado
            if (metodo === "banco") {
                infoBanco.style.display = "block";
                infoBilletera.style.display = "none";
            } else if (metodo === "billetera") {
                infoBanco.style.display = "none";
                infoBilletera.style.display = "block";
            } else {
                infoBanco.style.display = "none";
                infoBilletera.style.display = "none";
            }
        }

        function mostrarInformacionBanco() {
            const bancoElegido = document.getElementById("bancoElegido").value;
            const detallesBanco = document.getElementById("detallesBanco");
            bancosInfo = {
                "bcp": { cci: "12345678912345678901", titular: "Titular BCP", cuenta: "Cuenta en soles" },
                "bbva": { cci: "23456789123456789012", titular: "Titular BBVA", cuenta: "Cuenta en dólares" },
                "interbank": { cci: "34567891234567890123", titular: "Titular Interbank", cuenta: "Cuenta en soles" },
                "scotiabank": { cci: "45678912345678901234", titular: "Titular Scotiabank", cuenta: "Cuenta en dólares" }
            };

            if (bancosInfo[bancoElegido]) {
                detallesBanco.style.display = "block";
                document.getElementById("txtCCI").value = bancosInfo[bancoElegido].cci;
                document.getElementById("txtTitularBanco").value = bancosInfo[bancoElegido].titular;
                document.getElementById("txtTipoCuenta").value = bancosInfo[bancoElegido].cuenta;
            } else {
                detallesBanco.style.display = "none";
            }
        }

        function mostrarDetallesBanco() {
             document.getElementById("detallesBanco").style.display = "block";
        }

        function ocultarDetallesBanco() {
            document.getElementById("detallesBanco").style.display = "none";
        }

        function actualizarDetallesBanco() {
            var ddlBanco = document.getElementById("<%= ddlBancoElegido.ClientID %>");
                var bancoSeleccionado = ddlBanco.value;

                if (bancoSeleccionado) {
                    // Encuentra el banco seleccionado en bancosData
                    var banco = bancosData.find(b => b.nombreBanco === bancoSeleccionado);
                    if (banco) {
                        document.getElementById("txtCCI").value = banco.CCI;
                        document.getElementById("txtTitularBanco").value = banco.nombreTitular;
                        document.getElementById("txtTipoCuenta").value = banco.tipoCuenta;
                        document.getElementById("detallesBanco").style.display = "block";
                    }
                } else {
                    // Limpiar los campos si no hay banco seleccionado
                    document.getElementById("txtCCI").value = "";
                    document.getElementById("txtTitularBanco").value = "";
                    document.getElementById("txtTipoCuenta").value = "";
                    document.getElementById("detallesBanco").style.display = "none";
                }
        }

    </script>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
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
                    <asp:BoundField DataField="Monto" HeaderText="MONTO" />
                    <asp:BoundField DataField="NumCuotas" HeaderText="NUM. CUOTAS" />
                    <asp:BoundField DataField="TasaInteres" HeaderText="TASA INTERÉS" />
                    <asp:BoundField DataField="FechaOtorgamiento" HeaderText="FECHA OTORGAMIENTO" />
                    <asp:BoundField DataField="Estado" HeaderText="ESTADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnPagar" runat="server" Text="Pagar" CssClass="pay-btn" CommandArgument='<%# Eval("numCredito") %>' OnClick="btnPagar_Click" />
                            <asp:Button ID="btnVerDetalle" runat="server" Text="👁️" CssClass="pay-btn" CommandArgument='<%# Eval("numCredito") %>' OnClick="btnVerDetalles_Click" />
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
        <asp:Label ID="lblError" runat="server" CssClass="error-message"></asp:Label>
    </div>


   <div id="PagoModal" class="modal">
        <div class="modal-content">
            <span class="close-btn" onclick="closeModal()">&times;</span>
            <h2>Métodos de Pago:</h2>

            <!-- Selector de método de pago -->
            <label for="metodoPago">Seleccione el método de pago:</label>
            <select id="metodoPago" onchange="mostrarCamposPago()">
                <option value="">Seleccione</option>
                <option value="banco">Banco</option>
                <option value="billetera">Billetera Digital</option>
            </select>

            <!-- Información de banco -->
            <div id="infoBanco" style="display: none; margin-top: 20px;">
                <h3>Bancos Aceptados:</h3>
                <img src="images/bancos.png" alt="Bancos Aceptados" style="width:100%; max-width:400px;">
                    <label for="bancoElegido">Seleccione el banco</label>
<%--                <asp:DropDownList ID="ddlBancoElegido" runat="server" CssClass="select-dropdown" AutoPostBack="true" OnSelectedIndexChanged="ddlBancoElegido_SelectedIndexChanged">
                    <asp:ListItem Text="Seleccione" Value="" />
                </asp:DropDownList>--%>
                <asp:DropDownList ID="ddlBancoElegido" runat="server" CssClass="select-dropdown" AutoPostBack="false" onchange="actualizarDetallesBanco()">
                    <asp:ListItem Text="Seleccione" Value="" />
                </asp:DropDownList> 
                <div id="detallesBanco" runat="server" style="margin-top: 20px; display: none;">
                    <p>CCI:</p>
                    <asp:TextBox ID="txtCCI" runat="server" CssClass="input-text" ReadOnly="True"></asp:TextBox>

                    <p>Nombre del Titular:</p>
                    <asp:TextBox ID="txtTitularBanco" runat="server" CssClass="input-text" ReadOnly="True"></asp:TextBox>

                    <p>Tipo de Cuenta:</p>
                    <asp:TextBox ID="txtTipoCuenta" runat="server" CssClass="input-text" ReadOnly="True"></asp:TextBox>
                </div>
            </div>

            <!-- Información de billetera digital -->
            <div id="infoBilletera" style="display: none; margin-top: 20px;">
                <h3>Billeteras Digitales Aceptadas:</h3>
                <img src="images/billeteras.png" alt="Billeteras Aceptadas" style="width:100%; max-width:180px;">
                <p>Número de Billetera:</p>
                <asp:TextBox ID="txtNumeroBilletera" runat="server" CssClass="input-text" Text="987654321" ReadOnly="True" Enabled="False" />
                <p>Nombre del Titular:</p>
                <asp:TextBox ID="txtTitularBilletera" runat="server" CssClass="input-text" Text="Nombre del Titular de la Billetera" ReadOnly="True" Enabled="False" />
            </div>

            <p>Inserte imagen jpeg o pdf:</p>
            <asp:FileUpload ID="fileUpload" runat="server" />
            <br /><br />

            <!-- Botones de acción -->
            <asp:Button ID="btnSave" runat="server" Text="Grabar" CssClass="save-btn" />
            <asp:Button ID="btnCancel" runat="server" Text="Cancelar" CssClass="cancel-btn" OnClientClick="closeModal(); return false;" />
        </div>
    </div>
</asp:Content>