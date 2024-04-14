<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Geoshot - Cadastre-se</title>
</head>
<body>
    <h1>Cadastre-se</h1>
    <form action="/signup" method="POST">
        <input type="text" name="name" placeholder="Nome Completo"> <br> <br>
        <input type="text" name="username" placeholder="Usuário"> <br> <br>
        <input type="email" name="email" placeholder="Email"> <br> <br>
        <input type="password" name="password" placeholder="Senha"> <br> <br>
        <input type="password" name="confirm-password" placeholder="Confirme sua Senha"> <br> <br>
        <button>Cadastrar</button>
        <br>
        <%= request.getAttribute("non-equals-password") %> <br>
        <%= request.getAttribute("already-username") %> <br>
        <%= request.getAttribute("already-email") %> <br>

    </form>
</body>
</html>