<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.geoshot.geoshotweb.classes.MyAttempt" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoshot | Desafios resolvidos por mim</title>
    <link rel="stylesheet" href="static/styles/allStyles.css">
</head>
<body>
    <div class="main">
        <%@include file="sidebar.jsp"%>

        <div class="hidden"></div>

        <div class="feeds">
            <h1>Tentativas de <%= request.getAttribute("username") %> </h1>

            <%
                // Retrieve the list of Feed objects from the request
                List<MyAttempt> myAttemptsList = (List<MyAttempt>) request.getAttribute("attemptslist");
                if (!myAttemptsList.isEmpty()) {
                    for (MyAttempt myAttempt : myAttemptsList) {
            %>

            <div class="post-feed">
                <div class="post-feed-identifier">
                    <div class="foto-usuario">
                        <img src="static/images/usuario.png">
                        <%= myAttempt.getUserphoto() %>
                    </div>
                    <div class="nome-usuario"><%= myAttempt.getUsername() %></div>
                </div>
                <div class="foto-desafio">
                    <img src="static/images/paisagem.jpg">
                    <%= myAttempt.getPhoto() %>
                    <%= myAttempt.getPubId() %>
                </div>
                Date Of Creaton: <%= myAttempt.getAttemptDate()%>
                <div>Minha acuracia: <%= myAttempt.getAccuracy() %></div>
            </div>

            <%
                    }
                }
             else {%>
            <h2>Voce não resolveu nenhum desafio ainda! Go get'em tiger!</h2>
            <% } %>
        </div>
    </div>
</body>
</html>
