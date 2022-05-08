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
        google.charts.load('current', {'packages':['line']});
        google.charts.setOnLoadCallback(drawChart);



        function drawChart() {

            var preco1 = '${pd1}';
            var preco2 = '${pd2}';
            var preco3 = '${pd3}';
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'Data');
            data.addColumn('number', 'Epic');
            data.addColumn('number', 'Steam');
            data.addColumn('number', 'Nuuvem');

            data.addRows([
                [new Date("2022-01-17"),  preco1[0]["preco"], 80.8, 41.8],
                [new Date("2022-01-21"),  30.9, 69.5, 32.4],
                [new Date("2022-01-24"),  25.4,   57, 25.7],
                [new Date("2022-01-28"),  11.7, 18.8, 10.5],
            ]);
            /*data.addRows([
                [new Date("2022-01-17"),  37.8, 80.8, 41.8],
                [new Date("2022-01-21"),  30.9, 69.5, 32.4],
                [new Date("2022-01-24"),  25.4,   57, 25.7],
                [new Date("2022-01-28"),  11.7, 18.8, 10.5],
            ]);*/

            var options = {
                chart: {
                    title: 'Lista de Preços',
                },
                width: 900,
                height: 500,
                axes: {
                    x: {
                        0: {side: 'bottom'}
                    }
                }
            };

            var chart = new google.charts.Line(document.getElementById('line_top_x'));

            chart.draw(data, google.charts.Line.convertOptions(options));
        }
    </script>
</head>

<body>
<div class="container">
    <div>
        <c:forEach items="${pd1}" var="item">
            ${item.preco}<br>
        </c:forEach>
    </div>
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
            <div class="row">
                <h1>Nuuvem</h1>
                <div class="col-12">
                    <span>Data de leitura do menor preço: ${histL3.data_menor_preco} </span><br>
                    <span>Maior porcentagem promocional: ${histL3.maior_promo} </span><br>
                    <span>Menor preço registrado: ${histL3.menor_preco} </span><br>
                    <span>Média de preço: ${histL3.media_preco} </span><br>
                </div>
            </div>
        </div>
        <br><br>
        <div id="line_top_x"></div>
    </div>
</div>
</body>
</html>
