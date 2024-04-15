<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Cadastre-se</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div id="container">
        <h2>Cadastre-se</h2>
        <form action="/signup" method="POST">
            <label for="name">Nome</label>
            <input id="name" type="text" name="name" placeholder="Digite seu Nome Completo">
            
            <label for="username">Username</label>
            <input id="username" type="text" name="username" placeholder="Digite seu username">
            
            <label for="email">Email</label>
            <input id="email" type="email" name="email" placeholder="Digite seu email">
            
            <label for="password">Senha</label>
            <input id="password" type="password" name="password" placeholder="Crie sua senha">
            
            <label for="confirm-password">Repita sua senha</label>
            <input id="confirm-password" type="password" name="confirm-password" placeholder="Repita sua senha">
            
            <button class="btn-submit">Cadastrar</button>
        </form>
        <br>
        <%= request.getAttribute("non-equals-password") %> <br>
        <%= request.getAttribute("already-username") %> <br>
        <%= request.getAttribute("already-email") %> <br>
    </div>
</body>
</html>