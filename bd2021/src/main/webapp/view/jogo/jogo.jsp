<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 31/01/2022
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="/view/include/head.jsp"  %>
    <title>Drip ${jogo.titulo}</title>
</head>
<body>
<div style="display: flex;height: 100vh;flex-direction: column;justify-content: center;" class="container">
    <div class="col-12">
        <div class="row">
            <div class="col-6">
                <div style="display: flex;flex-direction: column;justify-content: center;align-items: center; height: 30rem; background: black; padding: 25px;">
                    <img src="${jogo.image}"/>
                </div>
            </div>
            <div class="col-12">
                <h1>${jogo.titulo}</h1>
            </div>
            <div class="col-12">
                <span>Desenvolvedora: ${jogo.desenvolvedora}</span> <br>
                <span>Publicadora: ${jogo.publicadora}</span> <br>
                <span>Data de lançamento: ${jogo.ano_publicacao}</span> <br>
            </div>
            <div class="col-12">
                <span>Processador: ${jogo.cpu}</span> <br>
                <span>Placa de Video: ${jogo.gpu}</span><br>
                <span>Memoria RAM: ${jogo.memoria_ram}</span> <br>
                <span>Sistema Operacional: ${jogo.so}</span> <br>
                <span>Armazenamento de Disco: ${jogo.armazenamento}</span> <br>
            </div>
        </div>
    </div>
</div>

<%@include file="/view/include/scripts.jsp"%>
</body>
</html>
