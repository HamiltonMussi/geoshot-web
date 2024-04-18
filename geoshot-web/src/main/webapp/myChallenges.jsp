<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.geoshot.geoshotweb.classes.Publication" %>
<%@ page import="com.geoshot.geoshotweb.classes.User" %>
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

        <div class="feeds">
            <h1>Aqui estão seus desafios, <%= ((User) request.getAttribute("user")).getUsername() %></h1>
            <%
                // Retrieve the list of Publication objects from the request
                List<Publication> pubList = (List<Publication>) request.getAttribute("publicationlist");
                if (!pubList.isEmpty()) {
                    for (Publication pub : pubList) {
            %>

            <div class="post-feed">
                <div class="foto-desafio">
                    <img src="data:image/jpeg;base64,<%= pub.getPhoto() %>">
                </div>

                <div class="legenda-my-challs">
                    <div class="legenda">Resposta: <%= pub.getCorrectValue() %></div>
                    <form action="/my-challs" method="POST">
                        <input type="hidden" value="<%= pub.getPubId() %>" name="pub-id">
                        <button type="submit" class="btn-delete" >Excluir desafio</button>
                    </form>
                </div>
            </div>

            <%
                    } %>
                    <div>Não há mais nenhum desafio. Aproveite para criar mais!</div>
               <% } else {%>
            <div>Você ainda não criou desafios. Aproveite para criar agora!</div>
            <% } %>
        </div>
    </div>
</body>
</html>
