
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/view/include/head.jsp"  %>
    <title>[User App] Jogo</title>
</head>
<body>
<div class="container">
    <div class="col-12">
        <h2>página do jogo</h2>
        <c:forEach var="jogo" items="${jogoList}">
            <tr>
                <td>
                    <span class="h4"><c:out value="${jogo.id}"/></span>
                </td>
                <td>
                    <span class="h4"><c:out value="${jogo.titulo}"/></span>
                </td>
                <td>
                    <img src=${jogo.image}/>
                </td>
            </tr>
        </c:forEach>
    </div>
    <p class="help-block">Ainda não é cadastrado?
        <a href="${pageContext.servletContext.contextPath}/">
            Clique aqui
        </a>
    </p>

</div>

<%@include file="/view/include/scripts.jsp"%>
<script src="${pageContext.servletContext.contextPath}../../assets/js/user.js"></script>
</body>
</html>
