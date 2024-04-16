<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geoshot.geoshotweb.classes.User" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Editar perfil</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>

        <% User user = (User) request.getAttribute("user"); %>
        <form action="" method="POST" enctype="multipart/form-data">
            <div class="post-feed-identifier">
                <div class="foto-usuario">
                    <img src="static/images/usuario.png">
                    <%= user.getPhoto() %>
                </div>
                <div class="nome-usuario"><%= user.getUsername() %></div>
                <div>
                    <label for="imageFile">Trocar foto: </label>
                    <input type="file" id="imageFile" name="photo" >
                </div>
            </div>

            <div class="textfield">
                <label for="password">Nova senha</label>
                <input id="password" type="password" name="password" placeholder="Digite sua nova senha">
            </div>
            <div class="textfield">
                <label for="confirm-password">Confirmar nova senha</label>
                <input id="confirm-password" type="password" name="confirm-password" placeholder="Confirme sua nova senha">
            </div>
            <div class="textfield">
                <label for="old-password">Nova senha</label>
                <input id="old-password" type="password" name="old-password" placeholder="Digite sua senha atual">
            </div>
            <button type="submit" class="btn-submit">Trocar</button>
        </form>
    </div>
</body>
</html>
