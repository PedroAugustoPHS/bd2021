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

        var precodata1 = ${pd1};
        var precodata2 = ${pd2};
        var precodata3 = ${pd3};
        var array_pd =  [['Data', 'Epic', 'Steam', 'Nuuvem'],];

        function drawChart() {
            var data1 = google.visualization.arrayToDataTable;
            //var data2 = google.visualization.arrayToDataTable;
            //var data3 = google.visualization.arrayToDataTable;


            for (i = 0; i < precodata1["preco"].length; i++) {
                data1.addRows([precodata1["data"][i],precodata1["preco"][i]);
            }
            /*for (i = 0; i < precodata2["preco"].length; i++) {
                data2.addRows([precodata2[i]["data"],precodata2[i]["preco"]]);
            }
            for (i = 0; i < precodata3["preco"].length; i++) {
                data3.addRows([precodata3[i]["data"],precodata3[i]["preco"]]);
            }*/

                var options = {
                    title: 'Company Performance',
                    hAxis: {title: 'Year', titleTextStyle: {color: '#333'}},
                    vAxis: {minValue: 0}
                };

                var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(data1, options);
           /*chart.draw(data2, options);
            chart.draw(data3, options);*/
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
