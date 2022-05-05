<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 05/05/2022
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <%@include file="/view/include/head.jsp" %>
    <%@include file="/view/include/scripts.jsp" %>
    <title>Top alguma coisa</title>
</head>
<body>
<div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
    <button class="btn btn-lg btn-primary btn-block" type="button">
        <a style="color: #fff; text-decoration: none" href="${pageContext.servletContext.contextPath}/jogo/top-barato" methods="GET">
            TOP BARATINHOS
        </a>
    </button>

    <button class="btn btn-lg btn-primary btn-block" type="button">
        <a style="color: #fff; text-decoration: none"
           href="${pageContext.servletContext.contextPath}/jogo/top-promocao" methods="GET">
            TOP PROMOÇÕES
        </a>
    </button>

    <button class="btn btn-lg btn-primary btn-block" type="button">
        <a style="color: #fff; text-decoration: none"
           href="${pageContext.servletContext.contextPath}/jogo/top-drip" methods="GET">
            TOP DRIP
        </a>
    </button>

    <button class="btn btn-lg btn-primary btn-block" type="button">
        <a style="color: #fff; text-decoration: none"
           href="${pageContext.servletContext.contextPath}/jogo/top-caro" methods="GET">
            TOP RIM
        </a>
    </button>
    <div class="container">
        <div class="row">
            <c:forEach var="jogo" items="${requestScope.jogoList}" varStatus="loop">
                <div class="col-4 mb-5">
                    <div class="card" style="width: 18rem;">
                        <div style="display: flex; justify-content: space-between">
                            <h2>R$ 249,90</h2>
                            <img    src="${pageContext.request.contextPath}/img/steam.png"
                                    height="50px" width="50px"/>
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
</body>
</html>
