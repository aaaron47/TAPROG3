<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Main.master" CodeFile="MainCliente.aspx.cs" Inherits="CreditoMovilWA.MainCliente" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="HeadContent" runat="server">
    <script src="https://cdn.jsdelivr.net/npm/raphael@2.2.8/raphael.min.js"></script> <!-- Dependencia de JustGage -->
    <script src="https://cdn.jsdelivr.net/npm/justgage"></script> <!-- CDN de JustGage -->
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    <style>
        h1 {
            font-size: 40px;
            color: #265f21;
            font-weight: 700;
            margin-top: 40px;
            margin-bottom: 20px;
        }
        p {
            margin-top: 10px;
            font-size: 20px;
            color: #333;
        }
        .btn {
            display: inline-block;
            width: 205px;
            padding: 12px;
            font-size: 16px;
            font-weight: 700;
            color: #fff;
            background-color: #2f7a44;
            border: none;
            border-radius: 5px;
            cursor: pointer;
             margin: 10px;
        }
        .btn:hover {
            background-color: #265f21;
        }
        .ranking-label {
            font-size: 30px;
            color: #ffb400; /*se cambia por detras*/
            background-color: rgba(0, 0, 0, 0);
            padding: 5px 50px;
            border-radius: 5px;
            display: inline-block;
        }
        #apexGauge {
            max-width: 380px;
            margin: auto;
        }
        .form-group label {
            font-size: 16px;
            color: #333;
        }

        /* Responsividad */
        @media (max-width: 768px) {
            h1 {
                font-size: 28px; /* Reducir tamaño del título */
                margin-top: 30px;
                margin-bottom: 15px;
            }
            p {
                font-size: 14px; /* Reducir tamaño del párrafo */
            }
            .btn {
                width: 180px; /* Reducir ancho del botón */
                padding: 10px; /* Reducir padding */
                font-size: 10px; /* Reducir tamaño del texto */
            }
            .ranking-label {
                font-size: 20px; /* Reducir tamaño de la etiqueta */
                padding: 5px 30px; /* Ajustar padding */
            }
            #apexGauge {
                max-width: 280px; /* Reducir tamaño del gráfico */
            }
            .form-group label {
                font-size: 10px; /* Reducir tamaño de las etiquetas del formulario */
            }
        }

        @media (max-width: 480px) {
            h1 {
                font-size: 24px; /* Reducir aún más el tamaño del título */
                margin-top: 20px;
                margin-bottom: 10px;
            }
            p {
                font-size: 12px; /* Ajustar el tamaño del párrafo */
            }
            .btn {
                width: 150px; /* Reducir más el ancho del botón */
                padding: 8px; /* Reducir padding del botón */
                font-size: 8px; /* Ajustar tamaño de fuente */
            }
            .ranking-label {
                font-size: 16px; /* Reducir aún más el tamaño de la etiqueta */
                padding: 5px 20px; /* Ajustar padding */
            }
            #apexGauge {
                max-width: 240px; /* Reducir tamaño del gráfico */
            }
            .form-group label {
                font-size: 8px; /* Ajustar tamaño de las etiquetas del formulario */
            }
        }
    </style>
    <script>
        function renderApexGauge(ranking) {
            let color;

            // Asigna un color específico basado en el valor de ranking
            if (ranking <= 20) {
                color = '#FF0000'; // Rojo para 0-20%
            } else if (ranking <= 40) {
                color = '#FFA500'; // Naranja para 20-40%
            } else if (ranking <= 60) {
                color = '#FFFF00'; // Amarillo para 40-60%
            } else if (ranking <= 80) {
                color = '#90EE90'; // Verde claro para 60-80%
            } else {
                color = '#006400'; // Verde oscuro para 80-100%
            }

            var options = {
                series: [ranking],
                chart: {
                    type: 'radialBar',
                    height: 350
                },
                plotOptions: {
                    radialBar: {
                        startAngle: -90,
                        endAngle: 90,
                        hollow: {
                            margin: 15,
                            size: '60%',
                        },
                        track: {
                            background: '#ADBAC0', // Fondo gris para mejorar el contraste
                            strokeWidth: '70%',
                            margin: 5, // Espacio entre el track y el gráfico
                            opacity: 0.7
                        },
                        dataLabels: {
                            name: {
                                show: false
                            },
                            value: {
                                fontSize: '50px',
                                color: '#78818D',
                                offsetY: -10,
                                fontFamily: 'Poppins, sans-serif', // Cambia la fuente
                                fontWeight: 'bold'
                            }
                        }
                    }
                },
                fill: {
                    colors: [color] // Color asignado según el valor del ranking
                },
                labels: ['Puntaje'],
                responsive: [
                    {
                        breakpoint: 768, // Para pantallas menores a 768px
                        options: {
                            chart: {
                                height: 300 // Ajustar altura del gráfico
                            },
                            plotOptions: {
                                radialBar: {
                                    dataLabels: {
                                        value: {
                                            fontSize: '40px', // Reducir tamaño de fuente
                                            offsetY: -5 // Ajustar posición vertical
                                        }
                                    }
                                }
                            }
                        }
                    },
                    {
                        breakpoint: 480, // Para pantallas menores a 480px
                        options: {
                            chart: {
                                height: 250 // Ajustar altura del gráfico
                            },
                            plotOptions: {
                                radialBar: {
                                    dataLabels: {
                                        value: {
                                            fontSize: '30px', // Reducir aún más el tamaño
                                            offsetY: 0 // Centrar la posición vertical
                                        }
                                    }
                                }
                            }
                        }
                    }
                ]
            };

            var chart = new ApexCharts(document.querySelector("#apexGauge"), options);
            chart.render();
        }
        // Llama a la función con el puntaje desde el servidor
        document.addEventListener("DOMContentLoaded", function () {
            renderApexGauge(<%= ObtenerRankingSinPorcentaje() %>);
        });
    </script>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div>
        <h1 id="hola" runat="server">¡Hola, </h1>
        <p>Actualmente tu ranking crediticio es:</p>
        <asp:Label ID="lblNotificacion" runat="server" CssClass="error-message" EnableViewState="false"></asp:Label>
        <asp:Label ID="lblRanking" runat="server" CssClass="ranking-label" Font-Size="0px"></asp:Label>
        

        <!-- Contenedor del medidor con ApexCharts -->
        <div id="apexGauge"></div>

        <!-- Botones de acción -->
        <asp:Button ID="btnSolicitarCredito" runat="server" Text="Solicitar un crédito" CssClass="btn" OnClick="btnSolicitarCredito_Click" />
        <asp:Button ID="btnVerCreditos" runat="server" Text="Visualizar mis créditos" CssClass="btn" OnClick="btnVerCreditos_Click" />
    </div>
</asp:Content>

