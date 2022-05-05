<%-- 
    Document   : index
    Created on : 30 de nov de 2021, 10:38:38
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/view/include/head.jsp"  %>
        <%@include file="/view/include/scripts.jsp" %>
        <title>Drip games</title>
        <script type="text/javascript">

            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages': ['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawChart() {

                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Topping');
                data.addColumn('number', 'Slices');
                /*for (i = 0; i < userList["jogos"].length; i++) {
					data.addRows([
						[userList["jogos"][i]["nome"],userList["jogos"][i]["numero"]]
					]);
          		}*/
                data.addRows([
                    ['Mushrooms', 3],
                    ['Onions', 1],
                    ['Olives', 1],
                    ['Zucchini', 1],
                    ['Pepperoni', 2]
                ]);

                // Set chart options
                var options = {
                    'title': 'How Much Pizza I Ate Last Night',
                    'width': 500,
                    'height': 300
                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
    <div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
        <div style="display: flex;flex-direction: column;justify-content: center;align-items: center;" class="logo">
            <img
                    src="${pageContext.request.contextPath}/img/drip_games.png"
                    height="250px" width="450px"/>
        </div>
        <br>
        <br>
        <form class="form-signin" action="${pageContext.servletContext.contextPath}/jogo/search" method="GET">
            <h2 class="form-signin-heading">Digite o nome do game</h2>
            <div class="row">
                <input class="form-control col-6" style="height: 48px; margin-right: 2rem" type="text" name="search"
                       placeholder="Nome do jogo" required autofocus>
                <button class="btn btn-lg btn-primary btn-block col-2" type="submit">Buscar</button>
            </div>
        </form>
        <br>
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="button">
            <a style="color: #fff; text-decoration: none" href="${pageContext.servletContext.contextPath}/jogo">
                Lista com todos os jogos
            </a>
        </button>

        <button class="btn btn-lg btn-primary btn-block" type="button">
            <a style="color: #fff; text-decoration: none"
               href="${pageContext.servletContext.contextPath}/jogo/create" methods="GET">
                Adicionar jogos ao catálogo
            </a>
        </button>

        <button class="btn btn-lg btn-primary btn-block" type="button">
            <a style="color: #fff; text-decoration: none"
               href="${pageContext.servletContext.contextPath}/preco/create" methods="GET">
                Fazer uma nova leitura de preços
            </a>
        </button>

        <button class="btn btn-lg btn-primary btn-block" type="button">
            <a style="color: #fff; text-decoration: none"
               href="${pageContext.servletContext.contextPath}/jogo/top" methods="GET">
                TOP ALGUMA COISA!
            </a>
        </button>


    </div>
    <div id="chart_div"></div>
    </body>
</html>
