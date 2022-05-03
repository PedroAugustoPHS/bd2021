<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 31/01/2022
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="/view/include/head.jsp" %>
    <title>Drip ${jogo.titulo}</title>
</head>
<body>
<div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
    <div class="col-12">
        <div class="row">
            <div class="col-12">
                <div class="row">
                    <div class="col-6"
                         style="display: flex;flex-direction: column;justify-content: center;align-items: center; height: 15rem; background: black; padding: 25px;">
                        <img class="col-12" src="${jogo.image}"/>
                    </div>
                    <div class="col-6">
                        <span>Steam: </span>${pd1[0].preco}<br>
                        <span>Epic: </span>${pd2[0].preco}<br>
                        <span>Nuuvem: </span>${pd3[0].preco}<br>
                    </div>
                </div>
            </div>
            <div class="col-12">
                <h1>${jogo.titulo}</h1>
                <hr style="color: black"/>
            </div>
            <div class="col-12">
                <span>Categoria: ${jogo.categoria}</span> <br>
                <span>Desenvolvedora: ${jogo.desenvolvedora}</span> <br>
                <span>Publicadora: ${jogo.publicadora}</span> <br>
                <span>Data de lançamento: ${jogo.ano_publicacao}</span> <br>
            </div>
            <div class="col-12">
                <h2>Requisitos mínimos</h2>
                <span>Processador: ${jogo.cpu}</span> <br>
                <span>Placa de Video: ${jogo.gpu}</span><br>
                <span>Memoria RAM: ${jogo.memoria_ram}</span> <br>
                <span>Sistema Operacional: ${jogo.so}</span> <br>
                <span>Armazenamento de Disco: ${jogo.armazenamento}</span> <br>
            </div>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="button">
            <a style="color: #fff; text-decoration: none"
               href="${pageContext.servletContext.contextPath}/jogo/hist?id=${jogo.id}">
                Ver histórico de preços do jogo ${jogo.titulo}
            </a>
        </button>
    </div>
</div>

<%@include file="/view/include/scripts.jsp" %>
</body>
</html>
