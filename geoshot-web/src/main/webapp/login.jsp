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
        <h1>Login</h1>
        <form class="form-login" action="/login" method="POST">
            <div class="textfield">
                <label for="username">Username ou e-mail</label>
                <input id="username" type="text" name="username" placeholder="Digite seu username ou e-mail">
            </div>
            <div class="textfield">
                <label for="password">Senha</label>
                <input id="password" type="password" name="password" placeholder="Digite sua senha">
            </div>
            <button class="btn-submit" >Login</button>
        </form>
    </div>
    <a href="/signup">Inscreva-se</a>
    <a href="/forgot-password">Esqueci minha senha</a>
</div>
</body>
</html>
