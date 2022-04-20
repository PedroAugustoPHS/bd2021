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
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Year', 'Epic', 'Steam', 'Nuuvem'],
                ['2013', 1000, 400, 0],
                ['2014', 1170, 460, 0],
                ['2015', 660, 1120, 0],
                ['2016', 1030, 540, 0]
            ]);

            var options = {
                title: 'Company Performance',
                hAxis: {title: 'Year', titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0}
            };

            var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(data, options);
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
            <div>
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
        </div>
        <div id="chart_div"></div>
</body>
</html>
