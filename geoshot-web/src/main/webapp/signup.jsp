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
    <div class="main-login">
        <div class="card-login">
            <div>
               <a href="/"><img src="static/images/Geoshot-logo120px.png" class="logo"></a>
            </div>
            <h1>Cadastre-se</h1>
            <% if (request.getAttribute("non-equals-password") != null) { %>
            <div class="error-message">Suas senhas não batem!</div>
            <% } %>
            <form class="form-login" action="/signup" method="POST">
                <div class="textfield">
                    <label for="name">Nome</label>
                    <input id="name" type="text" name="name" placeholder="Digite seu Nome Completo">
                </div>
                <div class="textfield">
                    <label for="username">Username</label>
                    <input id="username" type="text" name="username" placeholder="Digite seu username">
                </div>
                <div class="textfield">
                    <label for="email">Email</label>
                    <input id="email" type="email" name="email" placeholder="Digite seu email">
                </div>
                <div class="textfield">
                    <label for="password">Senha</label>
                    <input id="password" type="password" name="password" placeholder="Crie sua senha">
                </div>
                <div class="textfield">
                    <label for="confirm-password">Repita sua senha</label>
                    <input id="confirm-password" type="password" name="confirm-password" placeholder="Repita sua senha">
                </div>

                <button class="btn-submit" >Cadastrar</button>
            </form>
        </div>

    </div>
</body>
</html>