
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/view/include/head.jsp" %>
    <title>Jogo</title>
</head>
<body>
<div class="container">
    <div class="col-12">
        <h1>Lista de jogos</h1>
        <br>
        <div>
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/jogo/search" method="GET">
                <h2 class="form-signin-heading" style="margin-left: 16px;">Digite o nome do game</h2>
                <div class="row">
                    <input class="form-control col-6" style="height: 48px; margin-left: 30px; margin-right: 2rem"
                           type="text" name="search"
                           placeholder="Nome do jogo" required autofocus>
                    <button class="btn btn-lg btn-primary btn-block col-2" type="submit">Buscar</button>
                </div>
            </form>
        </div>
        <br>
        <div>
            <div class="btn-group" role="group" aria-label="Categorias" style="height: 48px; margin-left: 16px;">
                <button type="button" class="btn btn-secondary">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=All" methods="GET">
                        Todos
                    </a></button>
                <button type="button" class="btn btn-secondary">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Ação" methods="GET">
                        Ação
                    </a></button>
                <button type="button" class="btn btn-secondary">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Adventure" methods="GET">
                        Aventura
                    </a></button>
                <button type="button" class="btn btn-secondary">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Baseado" methods="GET">
                        Baseado em Turnos
                    </a></button>
                <button type="button" class="btn btn-secondary" name="Casual">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Casual" methods="GET">
                        Casual
                    </a></button>
                <button type="button" class="btn btn-secondary">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Independente" methods="GET">
                        Independente
                    </a></button>
                <button type="button" class="btn btn-secondary" name="RPG">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=RPG" methods="GET">
                        RPG
                    </a></button>
                <button type="button" class="btn btn-secondary" name="Mundo Aberto">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Mundo" methods="GET">
                        Mundo Aberto
                    </a></button>
                <button type="button" class="btn btn-secondary" name="Plataforma">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Plataforma" methods="GET">
                        Plataforma
                    </a></button>
                <button type="button" class="btn btn-secondary" name="Simulação">
                    <a style="color: #fff; text-decoration: none"
                       href="${pageContext.servletContext.contextPath}/jogo/search-cat?cat=Simulação" methods="GET">
                        Simulação
                    </a></button>
            </div>
        </div>
        <br>
        <div class="container">
            <div class="row">
                <c:forEach var="jogo" items="${requestScope.jogoList}" varStatus="loop">
                    <div class="col-4 mb-5">
                        <div class="card" style="width: 18rem;">
                            <div style="display: flex; justify-content: space-between">
                                <a class="btn btn-default"
                                   href="${pageContext.servletContext.contextPath}/jogo/update?id=${jogo.id}"
                                   data-toggle="tooltip"
                                   data-original-title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a class="btn btn-default link_excluir_usuario"
                                       href="${pageContext.servletContext.contextPath}/jogo/delete?id=${jogo.id}"
                                       data-toggle="tooltip"
                                       data-original-title="Excluir">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </div>
                                <a style="text-decoration: none; color: black;"
                                   href="${pageContext.servletContext.contextPath}/jogo/read?id=${jogo.id}">
                                    <div style="display:flex; flex-direction: column; justify-content: center; height: 10rem; background: black; padding: 25px; border-top-left-radius: 0.25rem; border-top-right-radius: 0.25rem;">
                                        <img class="card-img-top" src=${jogo.image}/>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text"><c:out value="${jogo.titulo}"/></p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
            </div>
        </div>
    </div>
</div>

<%@include file="/view/include/scripts.jsp"%>
</body>
</html>
