<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="SolicitudCredito.aspx.cs" Inherits="CreditoMovilWA.SolicitudCredito" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        h1 {
            font-size: 32px;
            color: #265f21;
            font-weight: 700;
        }
        label {
            font-size: 18px;
            margin-top: 20px;
            color: #333;
            display: block;
        }
        .slider-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .slider {
            width: 100%;
            margin: 0 10px;
        }
        .amount-display {
            font-size: 24px;
            font-weight: 700;
            color: #2f7a44;
        }
        .btn-option {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #333;
            border: 1px solid #ddd;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
        }
        .btn-option.selected {
            background-color: #2f7a44;
            color: #fff;
        }
        .submit-btn {
            width: 100%;
            padding: 15px;
            font-size: 18px;
            font-weight: 700;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 50px;
            margin-bottom: 25px;
        }
        .interest-display {
            font-size: 16px;
            color: #333;
            margin-top: 20px;
            text-align: left;
        }

        /* Responsividad */
    @media (max-width: 768px) {
        #min-value,
        #max-value {
            font-size: 12px; /* Reducir tamaño del texto */
        }
        h1 {
            font-size: 24px; /* Reducir tamaño del título */
        }
        label {
            font-size: 12px; /* Reducir tamaño del label */
        }
        .slider-container {
            align-items: flex-start;
        }
        .slider {
            width: 90%; 
            margin: 10px 0; /* Ajustar márgenes */
        }
        .amount-display {
            font-size: 16px; /* Reducir tamaño de la cantidad mostrada */
            text-align: center; /* Centrar el texto */
        }
        .btn-option {
            font-size: 10px; /* Reducir tamaño del texto del botón */
            padding: 8px 15px; /* Ajustar padding */
        }
        .submit-btn {
            font-size: 12px; /* Reducir tamaño del botón */
            padding: 12px; /* Ajustar padding */
            margin-top: 30px; /* Reducir margen superior */
        }
        .interest-display {
            font-size: 10px; /* Reducir tamaño del texto */
        }
    }

    @media (max-width: 480px) {
        #min-value,
        #max-value {
            font-size: 10px; /* Reducir tamaño del texto */
        }
        
        h1 {
            font-size: 20px; /* Reducir aún más el tamaño del título */
            text-align: center; /* Centrar el texto */
        }
        label {
            font-size: 10px; /* Reducir tamaño del label */
        }
        .slider-container {
            align-items: center; /* Centrar contenido */
        }
        .slider {
            width: 90%; /* Ajustar ancho completo */
            margin: 5px 0; /* Reducir margen */
        }
        .amount-display {
            font-size: 14px; /* Reducir aún más el tamaño de la cantidad mostrada */
        }
        .btn-option {
            font-size: 8px; /* Ajustar tamaño del texto del botón */
            padding: 6px 10px; /* Reducir padding */
            margin: 5px; /* Mantener márgenes pequeños */
        }
        .submit-btn {
            font-size: 10px; /* Reducir tamaño del texto del botón */
            padding: 10px; /* Reducir padding */
            margin-top: 20px; /* Ajustar margen superior */
        }
        .interest-display {
            font-size: 8px; /* Reducir tamaño del texto */
            text-align: center; /* Centrar texto */
        }
    }
    </style>
    <script>

        window.onload = function () {
            const backendMin = document.getElementById("<%= minHiddenField.ClientID %>").value;
            const backendMax = document.getElementById("<%= maxHiddenField.ClientID %>").value;

            console.log("Min Value from Backend:", backendMin);
            console.log("Max Value from Backend:", backendMax);

            const slider = document.querySelector(`.slider`);
            slider.min = backendMin;
            slider.max = backendMax;

            document.getElementById("min-value").textContent = backendMin;
            document.getElementById("max-value").textContent = backendMax;
        };

        function updateAmount(value) {
            document.getElementById("amountDisplay").innerText = "S/. " + value;
            document.getElementById("<%= hfMonto.ClientID %>").value = value;
            updateInterest(value); // Actualizar el interés cuando se cambie el monto
        }

        function selectOption(button, group) {
            let options = document.querySelectorAll(`.btn-option[data-group="${group}"]`);
            options.forEach(btn => btn.classList.remove("selected"));
            button.classList.add("selected");

            document.getElementById("selectedCuotas").value = button.textContent;
        }

        function selectOption2(button, group) {
            let options = document.querySelectorAll(`.btn-option[data-group="${group}"]`);
            options.forEach(btn => btn.classList.remove("selected"));
            button.classList.add("selected");

            document.getElementById("selectedPrimerPago").value = button.textContent;
        }

        function updateInterest(amount) {
            const interes = parseFloat(document.getElementById("<%= tasaInteres.ClientID %>").value);
            const minInterest = (amount * interes).toFixed(2); // interes mínimo
            const maxInterest = (amount * (interes+0.05)).toFixed(2); // interes máximo
            document.getElementById("interestDisplay").innerText = `Interés aproximado: S/. ${minInterest} - S/. ${maxInterest}`;
        }
    </script>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Contenedor principal -->
    <div class="container">
        <h1>Registro de Créditos</h1>
        
        <label>¿Cuánto dinero desea solicitar?</label>
        <asp:HiddenField ID="minHiddenField" runat="server" />
        <asp:HiddenField ID="maxHiddenField" runat="server" />
        <asp:HiddenField ID="tasaInteres" runat="server" />

        <div class="slider-container">
            <span id="min-value">10</span>
            <input type="range" min="1000" max="5000" value="3500" class="slider" oninput="updateAmount(this.value)" />
            <span id="max-value">5000</span>
        </div>
        <div id="amountDisplay" class="amount-display">S/. 3500</div>

        <!-- HiddenField para almacenar el monto seleccionado -->
        <asp:HiddenField ID="hfMonto" runat="server" Value="3500" />

        <label>¿En cuántas cuotas lo desea?</label>
        <div>
            <button type="button" class="btn-option" data-group="cuotas" onclick="selectOption(this, 'cuotas')">1</button>
            <button type="button" class="btn-option" data-group="cuotas" onclick="selectOption(this, 'cuotas')">3</button>
            <button type="button" class="btn-option" data-group="cuotas" onclick="selectOption(this, 'cuotas')">6</button>
            <button type="button" class="btn-option" data-group="cuotas" onclick="selectOption(this, 'cuotas')">12</button>

            <asp:HiddenField ID ="selectedCuotas" runat="server" ClientIDMode="Static" />
        </div>

        <!-- Interés aproximado -->
        <div id="interestDisplay" class="interest-display">Interés aproximado: S/. 175 - S/. 525</div>

        <asp:Button ID="btnSubmit" runat="server" Text="Solicitar Crédito" CssClass="submit-btn" OnClick="btnSubmit_Click" />
    </div>
</asp:Content>




