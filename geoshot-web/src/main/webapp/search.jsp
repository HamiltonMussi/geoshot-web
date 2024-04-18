<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geoshot.geoshotweb.classes.User" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document | Pesquisar</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>

        <div class="search">
            <% String thisUser = (String) request.getAttribute("username");  %>
            <h2>Procure seus amigos pelo username, <%= thisUser %>!</h2>

            <form action="/search" method="POST" class="form-search">
                <div class="textfield">
                    <input type="text" name="searched-username" placeholder="Usuário a Pesquisar ">
                    <button class="btn-submit"><i class="bi bi-search"></i> Buscar</button>
                </div>
            </form>

            <%-- Exibir o nome de usuário ou mensagem de erro --%>
            <%
                User user = (User) request.getAttribute("user");
                String error = (String) request.getAttribute("user-not-found");

                if (user != null) {
            %>

            <div class="user-identifier">
                <% if(thisUser.equals(user.getUsername())) { %>
                <div class="easter">
                    Por que está procurando a si mesmo, <%= thisUser %>? Está se sentindo solitário?
                    Quer conversar com alguém? Está pensando em se matar?
                </div>
                <% } else {%>
                <div class="user-identifier-cramp">
                    <div class="foto-usuario">
                        <% if(user.getPhoto().equals("default-photo")) { %>
                        <img src="static/images/default-user-photo.png">
                        <% }
                        else { %>
                        <img src="data:image/jpeg;base64,<%= user.getPhoto() %>"/>
                        <% } %>
                    </div>
                    <div class="nome-usuario"><%= user.getUsername() %></div>
                </div>
                <form action="/toggle-followship" method="POST" class="form-un-follow">
                    <input type="hidden" value="<%= user.getUsername() %>" name="searched-username">
                    <%
                        if((boolean) request.getAttribute("followship-state")) {
                            %>
                            <button class="btn-un-follow"> Deixar de Seguir </button>
                    <%
                        } else {
                    %>
                            <button class="btn-un-follow"> Seguir </button>
                    <% } %>
                </form>
                <% } %>
            </div>

            <%
            } else if (error != null) {
            %>
            <h2> Usuário não encontrado  <i class="bi bi-emoji-frown-fill"></i></h2>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
