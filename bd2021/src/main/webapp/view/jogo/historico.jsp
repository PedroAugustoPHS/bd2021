<%--
  Created by IntelliJ IDEA.
  User: Guto
  Date: 02/02/2022
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Historico</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Epic</h1>
        <div class="col-12">
            <span>Data de leitura do menor preço: ${histL1.data_menor_preco} </span><br>
            <span>Maior porcentagem promocional: ${histL1.maior_promo} </span><br>
            <span>Menor preço registrado: ${histL1.menor_preco} </span><br>
            <span>Média de preço: ${histL1.media_preco} </span><br>
        </div>
    </div>
    <div class="row">
        <h1>Steam</h1>
        <div class="col-12">
            <span>Data de leitura do menor preço: ${histL2.data_menor_preco} </span><br>
            <span>Maior porcentagem promocional: ${histL2.maior_promo} </span><br>
            <span>Menor preço registrado: ${histL2.menor_preco} </span><br>
            <span>Média de preço: ${histL2.media_preco} </span><br>
            <div>
            </div>
            <div class="row">
                <h1>Nuuvem</h1>
                <div class="col-12">
                    <span>Data de leitura do menor preço: ${histL3.data_menor_preco} </span><br>
                    <span>Maior porcentagem promocional: ${histL3.maior_promo} </span><br>
                    <span>Menor preço registrado: ${histL3.menor_preco} </span><br>
                    <span>Média de preço: ${histL3.media_preco} </span><br>
                    <div>
                    </div>
                </div>
</body>
</html>
