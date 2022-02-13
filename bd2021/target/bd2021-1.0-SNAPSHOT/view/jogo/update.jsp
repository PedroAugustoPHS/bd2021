<%--
    Document   : update
    Created on : 24 de nov de 2021, 11:51:43
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/view/include/head.jsp"  %>
    <title>Jogo: atualização</title>
</head>
<body>
<div class="container">
    <h2 class="text-center">Edição do jogo <c:out value="${jogo.titulo}"/></h2>

    <form class="form" action="${pageContext.servletContext.contextPath}/jogo/update?id=${jogo.id}" method="POST">

        <input type="hidden" name="id" value="${jogo.id}">

        <div class="form-group">
            <label class="control-label" for="title">Titulo</label>
            <input id="title" class="form-control" type="text" name="title" value="${jogo.titulo}" data-value="${jogo.titulo}" required autofocus/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label class="control-label" for="developer">Desenvolvedora</label>
            <input id="developer" class="form-control" type="text" name="developer" value="${jogo.desenvolvedora}" data-value="${jogo.desenvolvedora}" required/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label class="control-label" for="category">Categoria</label>
            <input id="category" class="form-control" type="text" name="category" value="${jogo.categoria}" data-value="${jogo.categoria}" required/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label class="control-label" for="description">Descricao</label>
            <input id="description" class="form-control" type="text" name="description" value="${jogo.descricao}" data-value="${jogo.descricao}" required/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label class="control-label" for="publisher">Publicadora</label>
            <input id="publisher" class="form-control" type="text" name="publisher" value="${jogo.publicadora}" data-value="${jogo.publicadora}" required/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label class="control-label" for="date_publi">Ano de publicacao</label>
            <input id="date_publi" class="form-control" type="text" name="date_publi" value="${jogo.ano_publicacao}" data-value="${jogo.ano_publicacao}" required/>

            <p class="help-block"></p>
        </div>

        <div class="form-group">
            <label for="jogo-image" class="control-label">Link da imagem do jogo</label>
            <input id="jogo-image" class="form-control" type="text" name="image" value="${jogo.image}" required/>
        </div>

        <div class="col-12">
            <h2>Requisitos mínimos:</h2>
        </div>

        <div class="form-group">
            <label for="jogo-cpu" class="control-label">Processador</label>
            <input id="jogo-cpu" class="form-control" type="text" name="cpu" value="${jogo.cpu}" required/>
        </div>

        <div class="form-group">
            <label for="jogo-gpu" class="control-label">Placa gráfica</label>
            <input id="jogo-gpu" class="form-control" type="text" name="gpu" value="${jogo.gpu}" required/>
        </div>

        <div class="form-group">
            <label for="jogo-memoria_ram" class="control-label">Memória RAM</label>
            <input id="jogo-memoria_ram" class="form-control" type="text" name="memoria_ram" value="${jogo.memoria_ram}" required/>
        </div>

        <div class="form-group">
            <label for="jogo-so" class="control-label">Sistema Operacional</label>
            <input id="jogo-so" class="form-control" type="text" name="so" value="${jogo.so}" required/>
        </div>

        <div class="form-group">
            <label for="jogo-armazenamento" class="control-label">Armazenamento</label>
            <input id="jogo-armazenamento" class="form-control" type="text" name="armazenamento" value="${jogo.armazenamento}" required/>
        </div>

        <div class="text-center">
            <button class="btn btn-lg btn-primary" type="submit">Salvar</button>
        </div>
    </form>
</div>

<%@include file="/view/include/scripts.jsp"%>
<script src="${pageContext.servletContext.contextPath}/assets/js/user.js"></script>
</body>
</html>
