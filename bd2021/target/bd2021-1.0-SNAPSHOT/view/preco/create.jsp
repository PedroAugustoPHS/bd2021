<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 04/02/2022
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <%@include file="/view/include/head.jsp" %>
    <title>Upload de arquivo</title>
</head>
<body>
<div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
    <form class="form-jogo-create"
          action="${pageContext.servletContext.contextPath}/preco/create" method="POST">
        <h2 class="form-jogo-create-heading">Digite o nome arquivo de jogos</h2>
        <div class="row">
            <input class="form-control col-6" style="height: 48px; margin-right: 2rem" type="text" name="fileName"
                   placeholder="Nome do arquivo.json" required autofocus>
            <button class="btn btn-lg btn-primary btn-block col-2" type="submit">Inserir</button>
        </div>
    </form>
    <br>
    <span>
        Toda adição de um novo arquivo de preços atualiza o histórico simultaneamente.
    </span>
</div>

<%@include file="/view/include/scripts.jsp" %>
<script src="${pageContext.servletContext.contextPath}/assets/js/popup.js"></script>
</body>
</html>
