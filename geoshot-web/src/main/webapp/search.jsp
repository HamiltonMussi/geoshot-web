<%@ page import="com.geoshot.geoshotweb.classes.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Geoshot - Pesquisar</title>
</head>
<body>
    <%=  request.getAttribute("username") %>
    <form action="/search" method="POST">
        <input type="text" name="searched-username" placeholder="Usuario a Pesquisar">
        <button>Buscar</button>
    </form>

    <%-- Exibir o nome de usuário ou mensagem de erro --%>
    <%
        User user = (User) request.getAttribute("user");
        String error = (String) request.getAttribute("user-not-found");

        if (user != null) {
    %>
    <form action="/toggle-followship" method="POST">
        <p>Usuário encontrado: <%= user.getUsername() %></p>
        <input type="hidden" value="<%= user.getUsername() %>" name="searched-username">
        <%
            if((boolean) request.getAttribute("followship-state")) {
                %>

        <button> Deixar de Seguir </button>

        <%
            } else {
        %>

        <button> Seguir </button>

        <% } %>
    </form>
    <%
    } else if (error != null) {
    %>
    <p> Usuario nao encontrado </p>
    <%
        }
    %>



</body>
</html>
