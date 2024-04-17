<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geoshot.geoshotweb.classes.Feed" %>
<%@ page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GeoShot | Página Inicial</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>

        <div class="hidden"></div>
        <div class="feeds">
            <h1>Pagina Inicial de <%= request.getAttribute("username") %> </h1>

            <%
                // Retrieve the list of Feed objects from the request
                List<Feed> feedList = (List<Feed>) request.getAttribute("feedlist");
                if (feedList.isEmpty()) {
            %>
                <div>Não há nenhum desafio novo! Faça mais amigos e obtenha mais desafios!</div>
            <% }
                else {
                    for (Feed feed : feedList) {
            %>

            <div class="post-feed">
                <div class="post-feed-identifier">
                    <div class="foto-usuario">
                        <img src="data:image/jpeg;base64,<%= feed.getUserPhoto() %>" alt="user-photo">
                    </div>
                    <div class="nome-usuario"><%= feed.getUsername() %></div>
                </div>
                <div class="foto-desafio">
                    <img src="data:image/jpeg;base64,<%= feed.getPhoto() %>">
                </div>
                Date Of Creation: <%= feed.getDateOfCreation()%>
                <div>
                    <form action="/chall" method="GET">
                        <input type="hidden" value="<%= feed.getPubId() %>" name="pub-id">
                        <button type="submit" class="btn-shot">shot</button>
                    </form>
                </div>
            </div>

                    <% } %>
                    <div>
                        <h2>Não há mais desafios! Go get'em tiger!</h2>
                    </div>
                <% } %>
        </div>
    </div>
</body>
</html>