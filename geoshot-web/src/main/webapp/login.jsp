<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Geoshot - Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/login" method="POST">
    <input type="text" placeholder="Usuário" name="username"> <br> <br>
    <input type="password" placeholder="Senha" name="password"> <br> <br>
    <button>Login</button>
    <%= request.getAttribute("nologin") %>
</form>
<br>
<a href="/signup">Inscreva-se</a>
<a href="/forgot-password">Esqueci minha senha</a>
</body>
</html>
