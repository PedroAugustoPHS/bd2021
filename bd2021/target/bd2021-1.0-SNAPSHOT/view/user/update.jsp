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
        <title>[User App] Usuários: atualização</title>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center">Edição do usuário <c:out value="${user.nome}"/></h2>

            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/user/update"
                enctype="multipart/form-data"
                method="POST">

                <input type="hidden" name="id" value="${user.id}">

                <div class="form-group">
                    <label class="control-label" for="usuario-login">Login</label>
                    <input id="usuario-login" class="form-control" type="text" name="login" value="${user.login}"
                           data-value="${user.login}" required autofocus/>

                    <p class="help-block"></p>
                </div>


                <%--    private Integer id;--%>
                <%--    private String titulo;--%>
                <%--    private String desenvolvedora;--%>
                <%--    private String categoria;--%>
                <%--    private String descricao;--%>
                <%--    private String publicadora;--%>
                <%--    private String ano_publicacao;--%>
                <%--    private String cpu;--%>
                <%--    private String gpu;--%>
                <%--    private String memoria_ram;--%>
                <%--    private String so;--%>
                <%--    private String armazenamento;--%>
                <%--    private String image;--%>

                <div class="form-group">
                    <label for="jogo-cpu" class="control-label">Nome</label>
                    <input id="jogo-cpu" class="form-control" type="text" name="nome" value="${user.nome}" required/>
                </div>


                <div class="form-group">
                    <label for="usuario-nasc" class="control-label">Data de nascimento</label>
                    <input id="usuario-nasc" class="form-control datepicker" type="date" name="nascimento"
                           placeholder="dd/mm/yyyy" value="${user.nascimento}"
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
