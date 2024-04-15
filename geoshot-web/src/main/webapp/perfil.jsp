<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document | Perfil de Usuário</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <div class="main">
            <%@include file="sidebar.jsp"%>

            <!-- <div class="menu-lateral">
                <div class="logo"></div>
                <div class="link-menu-lateral">
                    <a href="/home">Página inicial</a>
                </div>
                <div class="link-menu-lateral">
                    <a href="#">Criar desafio</a>
                </div>
                <div class="link-menu-lateral">
                    <a href="/perfil">Perfil do usuário</a>
                </div>
                <div class="link-menu-lateral">
                    <a href="#">Pesquisar amigos</a>
                </div>
            </div> -->

            <div class="feeds">
                <div class="post-feed">
                    <div class="post-feed-identifier">
                        <div class="foto-usuario">
                            <img src="static/images/usuario.png" alt="foto do usuario">
                        </div>
                        <div class="nome-usuario"><%= request.getAttribute("username") %></div>
                    </div>
                    <h4>Minha taxa de acurácia: <%= request.getAttribute("attempts") %> %</h4>
                    <div><a href="#">Meus Desafios:</a> 45</div>
                    <div><a href="#">Resolvidos por mim:</a> 25</div>
                    <div><a href="#">Editar perfil</a></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>


<!--
<html>
<head>
    <title>Geoshot - Perfil</title>
</head>
<body>
<h1>Perfil de <%= request.getAttribute("username") %></h1>

Tem um monte de parametro que botei na request....:

            request.setAttribute("username", thisUser.getUsername());

            request.setAttribute("name", thisUser.getName());

            request.setAttribute("email", thisUser.getEmail());

            (STRING) request.setAttribute("photo", thisUser.getPhoto());

            request.setAttribute("attempts",thisUser.getAttempts());

            request.setAttribute("accuracy",thisUser.getAccuracy());

<a href="/perfil/edit">Editar Perfil</a>
<a href="/perfil/my-attempts">Minhas Tentativas</a>
<a href="perfil/my-challs">Meus Desafios</a>

</body>
</html>
 -->
