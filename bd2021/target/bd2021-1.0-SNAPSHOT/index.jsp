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
        <title>Drip games</title>
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
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Digite o nome do game</h2>
                <div class="row">
                    <input class="form-control col-6" style="height: 48px; margin-right: 2rem" type="text" name="login" placeholder="Nome do jogo" required autofocus>
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

        </div>

        <%@include file="/view/include/scripts.jsp" %>
    </body>
</html>
