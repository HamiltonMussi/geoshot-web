<%@ page import="com.geoshot.geoshotweb.classes.Feed" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Geoshot - Initial Page</title>
</head>
<body>
<h1>Pagina Inicial de <%= request.getAttribute("username") %> </h1>
<ul>
    <%
        // Retrieve the list of Feed objects from the request
        List<Feed> feedList = (List<Feed>) request.getAttribute("feedlist");
        if (feedList != null) {
            for (Feed feed : feedList) {
    %>
    <li>username: <%= feed.getUsername() %></li>
    <li>userphoto: <%= feed.getUserPhoto() %></li>
    <li>photo: <%= feed.getPhoto() %></li>
    <li>PubId: <%= feed.getPubId() %></li>
    <li>Date Of Creaton: <%= feed.getDateOfCreation()%> </li>
    <!-- Add more properties as needed -->
    <%
        }
    } else {
    %>
    <li>Sem novas pubs.</li>
    <% } %>
</ul>

<a href="/perfil">Perfil</a>
<a href="/create-chall">Criar Desafio</a>
<a href="/search">Pesquisar</a>
<a href="/logout">Sair</a>
</body>
</html>