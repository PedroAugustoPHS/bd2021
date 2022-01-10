<%-- 
    Document   : create
    Created on : 24 de nov de 2021, 11:11:16
    Author     : dskaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/view/include/head.jsp"  %>
        <title>[User App] Usuários: cadastro</title>
    </head>
    <body>
       <div class="container">
            <h2 class="text-center">Inserção de um novo usuário</h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/user/create"
                enctype="multipart/form-data"
                method="POST">

                <div class="form-group">
                    <label class="control-label" for="usuario-login">Login</label>
                    <input id="usuario-login" class="form-control" type="text" name="login" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <div class="form-group">
                    <label class="control-label">Senha</label>
                    <input class="form-control password-input"
                           type="password" name="senha"
                           pattern=".{4,}" required title="Pelo menos 4 caracteres."/>
                </div>

                <div class="form-group pwd-confirm">
                    <label class="control-label">Confirmar senha</label>
                    <input class="form-control password-confirm"
                           type="password" name="senha-confirmacao"
                           pattern=".{4,}" required title="Pelo menos 4 caracteres."/>
                    <p class="help-block"></p>
                </div>

                <div class="form-group">
                    <label for="usuario-nome" class="control-label">Nome</label>
                    <input id="usuario-nome" class="form-control" type="text" name="nome" required/>
                </div>


                <div class="form-group">
                    <label for="usuario-nasc" class="control-label">Data de nascimento</label>
                    <input id="usuario-nasc" class="form-control datepicker" type="date" name="nascimento"
                           placeholder="dd/mm/yyyy"
                           pattern="\d{2}/\d{2}/\d{4}" required/>
                </div>

                <div class="form-group">
                    <label for="usuario-avatar">Foto do perfil</label>
                    <input type="file"
                           class="form-control" id="usuario-avatar"
                           name="avatar"
                           accept="image/*"/>
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
