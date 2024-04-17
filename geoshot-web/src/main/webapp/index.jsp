<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Home</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main-home">
        <div class="navbar">
            <div class="navbar-area-1">
                <a href="/"><img src="static/images/Geoshot-logo100px.png" class="navbar-logo"></a>
            </div>

            <%  String username = (String) session.getAttribute("username");
            if (username == null) { %>
            <div class="navbar-area-2">
                <a href="/login" class="navbar-link">Login</a>
                <a href="/signup" class="navbar-link">Cadastre-se</a>
            </div>
            <% }
            else { %>
            <div class="navbar-area-2">
                <a href="/initial-page" class="navbar-link">Clique aqui para ir para ver seus feeds, <%= username %>!</a>
            </div>
            <% } %>
        </div>

        <div class="home-container">
            <div class="home-image">
                <img src="static/images/Geoshot-logo.png">
            </div>
            <div class="home-text">
                Bem vindo ao GeoShot, o melhor jogo geográfico do mundo!
            </div>
        </div>
    </div>
</body>
</html>