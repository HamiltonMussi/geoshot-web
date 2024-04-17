<%@ page import="com.geoshot.geoshotweb.classes.User" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document | Perfil de Usuário</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>
        <%
            User user = (User) request.getAttribute("user");
        %>
        <div class="feeds">
            <div class="post-feed">
                <div class="post-feed-identifier">

                    <div class="foto-usuario">
                        <img src="data:image/jpeg;base64,<%= user.getPhoto() %>"/>
                    </div>

                    <div class="nome-usuario"><%= user.getUsername() %></div>
                </div>
                <h4>Minha taxa de acurácia: <%= user.getAccuracy() %> %</h4>
                <div><a href="/my-challs">Meus Desafios:</a> 45</div>
                <div><a href="/my-attempts">Resolvidos por mim:</a> 25</div>
                <div><a href="/edit-perfil">Editar perfil</a></div>
            </div>
        </div>
    </div>

</body>
</html>
