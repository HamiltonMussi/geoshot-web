<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Geoshot - Initial Page</title>
</head>
<body>
<h1>Pagina Inicial de <%= request.getAttribute("username") %> </h1>
<a href="/perfil">Perfil</a>
<a href="/create-chall">Criar Desafio</a>
<a href="/search">Pesquisar</a>
<a href="/logout">Sair</a>
</body>
</html>