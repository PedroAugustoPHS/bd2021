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
        <title>[User App] Login</title>
    </head>
    <body>
        <div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
            <div style="display: flex;flex-direction: column;justify-content: center;" class="logo">
                <img
                     src="${pageContext.request.contextPath}/img/drip_games.png"
                     height="250px" width="450px"/>
            </div>
            <br>
            <br>
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Por favor, faça login.</h2>

                <input class="form-control" type="text" name="login" placeholder="Usuário" required autofocus>
                <input class="form-control" type="password" name="senha" placeholder="Senha" required>
                <p class="help-block">Ainda não é cadastrado?
                    <a href="${pageContext.servletContext.contextPath}/user/create">
                        Clique aqui
                    </a>
                </p>
                <p class="help-block">Joguin?
                    <a href="${pageContext.servletContext.contextPath}/jogo">
                        Clique aqui
                    </a>
                </p>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>

        </div>

        <%@include file="/view/include/scripts.jsp"%>
    </body>
</html>
