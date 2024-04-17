<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geoshot.geoshotweb.classes.User" %>
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

        <div class="hidden"></div>
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
                <div><a href="/my-challs">Meus Desafios</a></div>
                <div><a href="/my-attempts">Resolvidos por mim:</a> <%= user.getAttempts() %> </div>
                <div><a href="/edit-perfil">Editar perfil</a></div>
            </div>
        </div>
    </div>

</body>
</html>
