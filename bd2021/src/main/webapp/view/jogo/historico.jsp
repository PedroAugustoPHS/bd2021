<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 02/02/2022
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Historico</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/view/include/head.jsp" %>
    <%@include file="/view/include/scripts.jsp" %>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['line']});
        google.charts.setOnLoadCallback(drawChartEpic);
        google.charts.setOnLoadCallback(drawChartSteam);
        google.charts.setOnLoadCallback(drawChartNuuvem);

        var data1 = [];
        var preco1 = ${preco1};
        var data1 = '${data1}';
        data1aux = data1.slice(1, -1);
        data1 = data1aux.split(",");


        function drawChartEpic() {
            var dataArray = [
                ['Data', 'Epic'],
            ];

            for (var i = 0; i < preco1.length; i++) {
                var row = [new Date(data1[i]), preco1[i]];
                dataArray.push(row);

            }
            var data = google.visualization.arrayToDataTable(dataArray)

            var options = {
                title: 'Histórico de Preços da EpicStore',

                hAxis: {
                    format: 'YYYY-MM-dd',
                },

                legend: {position: 'bottom'},
                series: {
                    0: {color: '#e2431e'}
                }
            };

            var chart = new google.charts.Line(document.getElementById('epic_chart'));
            chart.draw(data, options);
        }

        var data2 = [];
        var preco2 = ${preco2};
        var data2 = '${data2}';
        data2aux = data2.slice(1, -1);
        data2 = data2aux.split(",");

        function drawChartSteam() {
            var dataArray = [
                ['Data', 'Steam'],
            ];

            for (var i = 0; i < preco2.length; i++) {
                var row = [new Date(data2[i]), preco2[i]];
                dataArray.push(row);

            }
            var data = google.visualization.arrayToDataTable(dataArray)

            var options = {
                title: 'Histórico de Preços da Steam',
                hAxis: {
                    format: 'YYYY-MM-dd',
                },

                legend: {position: 'bottom'},
                series: {
                    0: {color: '#00FF00'}
                }

            };

            var chart = new google.charts.Line(document.getElementById('steam_chart'));
            chart.draw(data, options);
        }


        var data3 = [];
        var preco3 = ${preco3};
        var data3 = '${data3}';
        data3aux = data3.slice(1, -1);
        data3 = data3aux.split(",");

        function drawChartNuuvem() {
            var dataArray = [
                ['Data', 'Nuuvem'],
            ];

            for (var i = 0; i < preco3.length; i++) {
                var row = [new Date(data3[i]), preco3[i]];
                dataArray.push(row);

            }
            var data = google.visualization.arrayToDataTable(dataArray)

            var options = {
                title: 'Histórico de Preços da Nuuvem',

                hAxis: {
                    format: 'YYYY-MM-dd',
                },

                legend: {position: 'bottom'},
                series: {
                    0: {color: '#0000FF'}
                }

            };

            var chart = new google.charts.Line(document.getElementById('nuuvem_chart'));
            chart.draw(data, options);
        }
    </script>
</head>

<body>
<div class="container">
    <div class="row">
        <h1>Epic</h1>
        <div class="col-12">
            <span>Data de leitura do menor preço: ${histL1.data_menor_preco} </span><br>
            <span>Maior porcentagem promocional: ${histL1.maior_promo} </span><br>
            <span>Menor preço registrado: ${histL1.menor_preco} </span><br>
            <span>Média de preço: ${histL1.media_preco} </span><br>
        </div>
    </div>
    <div class="row">
        <h1>Steam</h1>
        <div class="col-12">
            <span>Data de leitura do menor preço: ${histL2.data_menor_preco} </span><br>
            <span>Maior porcentagem promocional: ${histL2.maior_promo} </span><br>
            <span>Menor preço registrado: ${histL2.menor_preco} </span><br>
            <span>Média de preço: ${histL2.media_preco} </span><br>
        </div>
    </div>
    <div class="row">
        <h1>Nuuvem</h1>
        <div class="col-12">
            <span>Data de leitura do menor preço: ${histL3.data_menor_preco} </span><br>
            <span>Maior porcentagem promocional: ${histL3.maior_promo} </span><br>
            <span>Menor preço registrado: ${histL3.menor_preco} </span><br>
            <span>Média de preço: ${histL3.media_preco} </span><br>
        </div>
    </div>
    <br>
    <hr>
    <br>
    <div id="epic_chart" style="width: 900px; height: 400px"></div>
    <br>
    <div id="steam_chart" style="width: 900px; height: 400px"></div>
    <br>
    <div id="nuuvem_chart" style="width: 900px; height: 400px"></div>
    <br>
</div>
</body>
</html>
