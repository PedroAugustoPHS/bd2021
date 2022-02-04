<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 04/02/2022
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/view/include/head.jsp"  %>
    <title>Upload de arquivo</title>
</head>
<body>
    <div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
        <form class="form-jogo-create" action="${pageContext.servletContext.contextPath}/jogo/create" method="POST">
            <h2 class="form-jogo-create-heading">Digite o nome arquivo para inserir do jogo</h2>
            <div class="row">
                <input class="form-control col-6" style="height: 48px; margin-right: 2rem" type="text" name="fileName" placeholder="Nome do arquivo.json" required autofocus>
                <button class="btn btn-lg btn-primary btn-block col-2" type="submit">Inserir</button>
            </div>
        </form>
    </div>
</body>
</html>
