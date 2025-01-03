﻿<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="VisualizarCreditosCliente.aspx.cs" Inherits="CreditoMovilWA.VisualizarCreditos" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        .container{
            max-width: 1200px;
        }
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
            width: 100%;
            max-width: 800px;
            text-align: left;
            border-radius: 10px;
            position: relative;
        }
        .close-btn {
            background: none;
            border: none;
            color: #aaa;
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
            width: 50%; /* Ajusta el ancho para que ambos botones ocupen el mismo espacio */
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
    </style>

    <script type="text/javascript">
        function openModal() {
            document.getElementById("PagoModal").style.display = "block";
        }

        function closeModal() {
            // Reinicia el selector de método de pago
            const metodoPago = document.getElementById("metodoPago");
            if (metodoPago) metodoPago.value = ''; // Regresa a "Seleccione"

            // Oculta las secciones de banco y billetera
            const infoBanco = document.getElementById("infoBanco");
            const infoBilletera = document.getElementById("infoBilletera");
            if (infoBanco) infoBanco.style.display = 'none';
            if (infoBilletera) infoBilletera.style.display = 'none';

            // Reinicia el selector de banco
            const bancoElegido = document.getElementById("ddlBancoElegido");
            if (bancoElegido) bancoElegido.value = ''; // Regresa a "Seleccione un banco"

            // Reinicia el selector de billetera
            const billeteraElegida = document.getElementById("ddlBilleteraElegida");
            if (billeteraElegida) billeteraElegida.value = ''; // Regresa a "Seleccione una billetera"

            // Limpia los campos de texto relacionados con banco
            const txtCCI = document.getElementById("txtCCI");
            const txtTitularBanco = document.getElementById("txtTitularBanco");
            const txtTipoCuenta = document.getElementById("txtTipoCuenta");
            if (txtCCI) txtCCI.value = '';
            if (txtTitularBanco) txtTitularBanco.value = '';
            if (txtTipoCuenta) txtTipoCuenta.value = '';

            // Limpia los campos de texto relacionados con billetera
            const txtNumeroBilletera = document.getElementById("txtNumeroBilletera");
            const txtTitularBilletera = document.getElementById("txtTitularBilletera");
            if (txtNumeroBilletera) txtNumeroBilletera.value = '';
            if (txtTitularBilletera) txtTitularBilletera.value = '';

            // Limpia el campo de archivo subido
            const fileUpload = document.getElementById("fileUpload");
            if (fileUpload) fileUpload.value = '';

            // Finalmente, cierra el modal
            const modal = document.getElementById("PagoModal");
            if (modal) modal.style.display = "none";
        }

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

        function mostrarDetallesBanco() {
            var detallesBanco = document.getElementById("<%= detallesBanco.ClientID %>");
            detallesBanco.style.display = "block";
        }

        function ocultarDetallesBanco() {
            var detallesBanco = document.getElementById("<%= detallesBanco.ClientID %>");
            detallesBanco.style.display = "none";
        }

        function onBancoSeleccionado(nombreBanco) {
            if (nombreBanco) {
                fetch('VisualizarCreditosCliente.aspx/ObtenerDatosBanco', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ nombreBanco: nombreBanco })
                })
                    .then(response => response.json())
                    .then(data => {
                        var banco = data.d; // 'd' es donde ASP.NET coloca los datos
                        if (banco) {
                            document.getElementById("<%= txtCCI.ClientID %>").value = banco.CCI;
                        document.getElementById("<%= txtTitularBanco.ClientID %>").value = banco.nombreTitular;
                        document.getElementById("<%= txtTipoCuenta.ClientID %>").value = banco.tipoCuenta;
                        mostrarDetallesBanco();
                    }
                })
                    .catch(error => console.error('Error:', error));
            } else {
                document.getElementById("<%= txtCCI.ClientID %>").value = "";
                document.getElementById("<%= txtTitularBanco.ClientID %>").value = "";
                document.getElementById("<%= txtTipoCuenta.ClientID %>").value = "";
                ocultarDetallesBanco();
            }
        }

        function mostrarDetallesBilletera() {
            var detallesBilletera = document.getElementById("<%= detallesBilletera.ClientID %>");
            detallesBilletera.style.display = "block";
        }

        function ocultarDetallesBilletera() {
            var detallesBilletera = document.getElementById("<%= detallesBilletera.ClientID %>");
            detallesBilletera.style.display = "none";
        }

        function onBilleteraSeleccionada(nombreBilletera) {
            if (nombreBilletera) {
                fetch('VisualizarCreditosCliente.aspx/ObtenerDatosBilletera', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ nombreBilletera: nombreBilletera })
                })
                .then(response => response.json())
                .then(data => {
                    console.log("Respuesta del servidor:", data);
                    var billetera = data.d; // 'd' es donde ASP.NET coloca los datos
                    console.log("Datos de la billetera:", billetera);
                    if (billetera) {
                        document.getElementById("<%= txtNumeroBilletera.ClientID %>").value = billetera.numeroTelefono;
                        document.getElementById("<%= txtTitularBilletera.ClientID %>").value = billetera.nombreTitular
                        mostrarDetallesBilletera();
                    }
                })
                .catch(error => console.error('Error:', error));
            } else {
                document.getElementById("<%= txtNumeroBilletera.ClientID %>").value = "";
                document.getElementById("<%= txtTitularBilletera.ClientID %>").value = "";
                ocultarDetallesBilletera();
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
            <asp:Label ID="lblRetrasado" runat="server" CssClass="error-message"></asp:Label>
            <asp:GridView ID="gvCreditos" runat="server" AutoGenerateColumns="false">
                <Columns>
                    <asp:BoundField DataField="numCredito" HeaderText="ID_CRÉDITO" />
                    <asp:BoundField DataField="Monto" HeaderText="MONTO" />
                    <asp:BoundField DataField="NumCuotas" HeaderText="NUM. CUOTAS" />
                    <asp:BoundField DataField="TasaInteres" HeaderText="TASA INTERÉS" />
                    <asp:TemplateField HeaderText="FECHA OTORGAMIENTO">
                        <ItemTemplate>
                            <%# Eval("FechaOtorgamiento") != null && Eval("FechaOtorgamiento") != DBNull.Value
                                ? Convert.ToDateTime(Eval("FechaOtorgamiento")).ToString("dd/MM/yyyy")
                                : "No asignada" %>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="Estado" HeaderText="ESTADO" />
                    <asp:TemplateField>
                        <ItemTemplate>
                            <asp:Button ID="btnPagar" runat="server" Text="Pagar" CssClass="pay-btn" CommandArgument='<%# Eval("numCredito") %>' OnClick="btnPagar_Click"
                                Visible='<%# Eval("Estado").ToString() == "Activo" || Eval("Estado").ToString() == "Retrasado" %>'    />
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
            <!-- Encabezado del modal con botón de cerrar -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="mb-0">Métodos de Pago:</h2>
                <button type="button" class="close-btn" onclick="closeModal()">&times;</button>
            </div>

            <!-- Selector de método de pago -->
            <label for="metodoPago">Seleccione el método de pago:</label>
            <select id="metodoPago" onchange="mostrarCamposPago()" class="select-dropdown">
                <option value="">Seleccione</option>
                <option value="banco">Banco</option>
                <option value="billetera">Billetera Digital</option>
            </select>

            <!-- Información de banco -->
            <div id="infoBanco" style="display: none; margin-top: 20px;">
                <h3>Bancos Aceptados:</h3>
                <img src="images/bancos.png" alt="Bancos Aceptados" style="width:100%; max-width:400px;">
                <label for="bancoElegido">Seleccione el banco</label>
                <asp:DropDownList ID="ddlBancoElegido" runat="server" CssClass="select-dropdown" onchange="onBancoSeleccionado(this.value);">
                    <asp:ListItem Text="Seleccione un banco" Value="" />
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
                <label for="billeteraElegida">Seleccione la billetera</label>
                <asp:DropDownList ID="ddlBilleteraElegida" runat="server" CssClass="select-dropdown" onchange="onBilleteraSeleccionada(this.value);">
                    <asp:ListItem Text="Seleccione una billetera" Value="" />
                </asp:DropDownList>
                <div id="detallesBilletera" runat="server" style="margin-top: 20px; display: none;">
                    <p>Número de Billetera:</p>
                    <asp:TextBox ID="txtNumeroBilletera" runat="server" CssClass="input-text" ReadOnly="True"></asp:TextBox>
                    <p>Nombre del Titular:</p>
                    <asp:TextBox ID="txtTitularBilletera" runat="server" CssClass="input-text" ReadOnly="True"></asp:TextBox>
                </div>
            </div>

            <p>Inserte imagen jpeg o pdf:</p>
            <asp:FileUpload ID="fileUpload" runat="server" />

            <!-- Mensaje de error en el modal -->
            <asp:Label ID="lblErrorModal" runat="server" CssClass="text-danger" />
            <br />
            <!-- Botón de acción centrado -->
            <div class="text-center mt-4">
                <asp:Button ID="btnSave" runat="server" Text="Grabar" CssClass="save-btn" OnClick="btnSave_Click" />
            </div>
        </div>
    </div>
</asp:Content>