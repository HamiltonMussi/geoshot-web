<%@ page import="com.geoshot.geoshotweb.classes.Publication" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Meus Desafios</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>

        <div class="post-feed">
            <div class="post-feed-identifier">
                <div class="foto-usuario">
                    <img src="static/images/usuario.png" alt="foto do usuario">
                </div>
                <div class="nome-usuario"><%= request.getAttribute("username") %></div>
            </div>
        </div>

        <div class="feeds">
            <%
                // Retrieve the list of Publication objects from the request
                List<Publication> pubList = (List<Publication>) request.getAttribute("publicationlist");
                if (pubList != null) {
                    for (Publication pub : pubList) {
            %>

            <div class="post-feed">
                <div class="foto-desafio">
                    <img src="static/images/paisagem.jpg">
                    <%= pub.getPhoto() %>
                    <%= pub.getPubId() %>
                </div>
                Date Of Creaton: <%= pub.getDateOfCreation()%>
                <div>
                    <div>Resposta: <%= pub.getCorrectValue() %></div>
                    <form action="/my-challs" method="POST">
                        <input type="hidden" value="<%= pub.getPubId() %>" name="pub-id">
                        <button type="submit" class="btn-delete">Excluir desafio</button>
                    </form>
                </div>
            </div>

            <%
                    }
                } %>
        </div>
    </div>
</body>
</html>
