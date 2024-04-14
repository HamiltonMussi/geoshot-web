<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Geoshot - Perfil</title>
</head>
<body>
<h1>Perfil de <%= request.getAttribute("username") %></h1>
<!--
Tem um monte de parametro que botei na request....:

            request.setAttribute("username", thisUser.getUsername());

            request.setAttribute("name", thisUser.getName());

            request.setAttribute("email", thisUser.getEmail());

            (STRING) request.setAttribute("photo", thisUser.getPhoto());

            request.setAttribute("attempts",thisUser.getAttempts());

            request.setAttribute("accuracy",thisUser.getAccuracy());
-->

<a href="/perfil/edit">Editar Perfil</a>
<a href="/perfil/my-attempts">Minhas Tentativas</a>
<a href="perfil/my-challs">Meus Desafios</a>

</body>
</html>
