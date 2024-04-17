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
            <div class="profile-card">
                <div class="banner-zone">
                    <div class="profile-zone">
                        <div class="pic-mask"><img class="profile-pic" src=src="data:image/jpeg;base64,<%= user.getPhoto() %>"></div>
                        <div class="user-nickname"><%= user.getUsername() %></div>
                        <div class="profile-ac"><%= user.getAccuracy() %>%</div>
                    </div>
                </div>
                <div class="info_zone">
                    <a href="/my-challs"><button class="profile-info-button">Meus Desafios</button></a>
                    <a href="/my-attempts"><button class="profile-info-button">Resolvidos por mim</button></a>
                    <a href="/edit-perfil"><button class="profile-info-button">Editar perfil</button></a>
                </div>
            </div>
        </div>
<%--            <div class="profile-card">--%>
<%--                <div class="banner-zone">--%>
<%--                    <div class="profile-zone">--%>
<%--                        <div class="pic-mask"><img class="profile-pic" src=src="data:image/jpeg;base64,<%= user.getPhoto() %>"></div>--%>
<%--                        <div class="user-nickname"><%= user.getUsername() %></div>--%>
<%--                        <div class="profile-ac"><%= user.getAccuracy() %>%</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="info_zone">--%>
<%--                    <a href="/my-challs"><button class="profile-info-button">Meus Desafios</button></a>--%>
<%--                    <a href="/my-attempts"><button class="profile-info-button">Resolvidos por mim</button></a>--%>
<%--                    <a href="/edit-perfil"><button class="profile-info-button">Editar perfil</button></a>--%>
<%--                </div>--%>
<%--            </div>--%>
    </div>

</body>
</html>
