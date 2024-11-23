<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="Login.aspx.cs" Inherits="CreditoMovilWA.Login" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <style>
        .container{
            max-width: 650px;
        }
        h1 {
            font-size: 32px;
            color: #265f21;
            font-weight: 700;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            color: #333;
            margin-bottom: 20px;
        }
        .form-control {
            font-size: 16px;
        }
        .form-group {
            display: flex;
            text-align: left;
            flex-direction: column;
            margin-bottom: 15px;
        }
        .form-group label {
            font-size: 16px;
            color: #333;
            margin-bottom: 5px;
        }
        .form-group input {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #e4e4e4;
            width: 100%;
            box-sizing: border-box;
        }
        .btn-login {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: 700;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .btn-login:hover {
            background-color: #265f21;
        }
        .password{
            font-size: 16px;
        }

        @media (max-width: 768px) {
            h1 {
                font-size: 24px; /* Reducir tamaño del título */
            }
            p {
                font-size: 10px; /* Reducir tamaño del párrafo */
            }
            .form-control {
                font-size: 10px;
            }
            .form-group label {
                font-size: 10px; /* Reducir tamaño del label */
            }
            .form-group input {
                font-size: 10px; /* Reducir tamaño de texto del input */
                padding: 8px; /* Reducir padding */
            }
            .btn-login {
                font-size: 10px; /* Reducir tamaño del botón */
                padding: 10px; /* Ajustar padding */
            }
            .password{
                font-size: 10px;
            }
        }

        @media (max-width: 480px) {
            h1 {
                font-size: 20px; /* Reducir aún más el tamaño del título */
            }

            p {
                font-size: 8px; /* Reducir aún más el tamaño del párrafo */
            }

            .form-group label {
                font-size: 8px; /* Ajustar tamaño del label */
            }

            .form-group input {
                font-size: 8px; /* Reducir tamaño de texto del input */
                padding: 6px; /* Ajustar padding */
            }

            .btn-login {
                font-size: 8px; /* Ajustar tamaño del botón */
                padding: 8px; /* Reducir padding del botón */
            }
        }
    </style>
    <script type="text/javascript">
    if (typeof authExpiration !== 'undefined') {
        var currentTime = new Date().getTime();
        var timeRemaining = authExpiration - currentTime;

        if (timeRemaining > 0) {
            setTimeout(function () {
                showSessionExpiredModal();
            }, timeRemaining);
        } else {
            showSessionExpiredModal();
        }
    }

    function showSessionExpiredModal() {
        document.getElementById('sessionExpiredModal').style.display = 'block';
    }
    </script>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Encabezado con logo -->
    <div class="header">
        <img src="images/credit2.png" alt="Logo Crédito Móvil">
    </div>

    <div class="container">
        <h1>¡Bienvenido de vuelta!</h1>
        <p>Ingresa tus credenciales</p>
        
        <div class="form-group">
            <label for="tipo-documento">Tipo de Documento</label>
            <asp:DropDownList ID="ddlTipoDocumento" runat="server" CssClass="form-control">
                <asp:ListItem Text="Selecciona una opción" Value="" />
                <asp:ListItem Text="DNI" Value="DNI" />
                <asp:ListItem Text="Pasaporte" Value="Pasaporte" />
                <asp:ListItem Text="Carnet de Extranjeria" Value="Carnet_Extranjeria" />
            </asp:DropDownList>
        </div>

        <div class="form-group">
            <label for="documento">Documento de Identidad:</label>
            <asp:TextBox ID="txtDocumento" runat="server" CssClass="form-control" placeholder="Documento de Identidad"></asp:TextBox>
        </div>
        
        <div class="form-group">
            <label for="password">Contraseña:</label>
            <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control" placeholder="Contraseña"></asp:TextBox>
        </div>

        <div class="form-group">
            <asp:LinkButton ID="NoContra" runat="server" OnClick="LinkButton1_Click">
                <asp:Label ID="lblrecuperar" runat="server" Text="¿Haz olvidado tu contraseña?" CssClass="password"></asp:Label>
            </asp:LinkButton>
        </div>

        <asp:Label ID="lblError" runat="server" CssClass="error-message" EnableViewState="false"></asp:Label>
        
        <asp:Button ID="btnIngresar" runat="server" Text="Ingresar" CssClass="btn-login" OnClick="btnIngresar_Click" />
    </div>

    <div id="sessionExpiredModal" class="modal" style="display:none;">
    <div class="modal-content">
        <h2>Sesión Expirada</h2>
        <p>Su sesión ha expirado. Por favor, inicie sesión nuevamente.</p>
        <a href="Login.aspx" class="btn">Iniciar Sesión</a>
    </div>
</div>
</asp:Content>
