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

        <div class="feeds">
            <h1>Pagina Inicial de <%= request.getAttribute("username") %> </h1>

            <%
                // Retrieve the list of Feed objects from the request
                List<Feed> feedList = (List<Feed>) request.getAttribute("feedlist");
                if (feedList != null) {
                    for (Feed feed : feedList) {
            %>

            <div class="post-feed">
                <div class="post-feed-identifier">
                    <div class="foto-usuario">
                        <img src="static/images/usuario.png">
                        <%= feed.getUserPhoto() %>
                    </div>
                    <div class="nome-usuario"><%= feed.getUsername() %></div>
                </div>
                <div class="foto-desafio">
                    <img src="static/images/paisagem.jpg">
                    <%= feed.getPhoto() %>
                    <%= feed.getPubId() %>
                </div>
                Date Of Creaton: <%= feed.getDateOfCreation()%>
                <div>
                    <form action="/initial-page" method="POST">
                        <input type="hidden" value="<%= feed.getPubId() %>" name="pub-id">

                        <button type="submit" class="btn-shot">shot</button>
                    </form>
                </div>
            </div>

            <%
                }
            } %>
            <div>
                <h2>Não há mais desafios! Go get'em tiger!</h2>
            </div>
        </div>
    </div>
</body>
</html>