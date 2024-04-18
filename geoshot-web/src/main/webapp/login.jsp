<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Login</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
<div class="main-login">
    <div class="card-login">

        <a href="/"><img src="static/images/Geoshot-logo120px.png" class="logo"></a>

        <h1>Login</h1>
        <% if(request.getAttribute("nologin") != null){ %>
        <div class="error-message">Seu login ou senha estão incorretos!</div>
        <% } %>
        <form class="form-login" action="/login" method="POST">
            <div class="textfield">
                <label for="username">Username</label>
                <input id="username" type="text" name="username" placeholder="Digite seu username">
            </div>
            <div class="textfield">
                <label for="password">Senha</label>
                <input id="password" type="password" name="password" placeholder="Digite sua senha">
            </div>
            <button class="btn-submit" >Login</button>
        </form>
        <a href="/signup" class="link-menu-lateral">Inscreva-se</a>
    </div>

</div>
</body>
</html>
