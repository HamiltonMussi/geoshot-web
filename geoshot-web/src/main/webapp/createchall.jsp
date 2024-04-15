<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Geoshot - Criar Desafio</title>
</head>
<body>

<h1>Criar Desafio</h1>
<form action="/create-chall" method="POST" enctype="multipart/form-data">
    <input name="photo" type="file">
    <input name="anwser" type="number" placeholder="resposta"> <!-- Será mudado quando for encontrado a forma correta -->
    <button>Criar Desafio</button>
    <%= request.getAttribute("missing") %>
</form>
</body>
</html>
