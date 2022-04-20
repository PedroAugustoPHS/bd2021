
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
                           placeholder="Nome do jogo">
                    <button class="btn btn-lg btn-primary btn-block col-2" type="submit">Buscar</button>
                </div>
                <br>
                <div>
                    <div class="btn-group-toggle" data-toggle="buttons" aria-label="Categorias"
                         style="margin-left: 16px;">
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Ação"> Ação
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Aventura"> Aventura
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Baseado em Turnos">
                            Baseado em Turnos
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Casual"> Casual
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Independente">
                            Independente
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Mundo Aberto"> Mundo
                            Aberto
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Plataforma"> Plataforma
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="RPG"> RPG
                        </label>
                        <label class="btn btn-secondary">
                            <input type="checkbox" autocomplete="off" name="categoria" value="Simulação"> Simulação
                        </label>
                    </div>
                </div>
            </form>
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
