<%@ page import="com.geoshot.geoshotweb.classes.Publication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Draggable Advanced Marker</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>

    <link rel="stylesheet" type="text/css" href="static/styles/challStyle.css" />
    <script type="module" src="index.js"></script>
</head>
<body>
<div class="topRow">
    <h1>Arreste o pino para a posição desejada</h1>
    <div id="map"></div>
</div>
<div class="bottomRow">
    <form method="post" action="/chall" id="guessForm">
        <input type="hidden" value="<%= ((Publication) request.getAttribute("publication")).getPubId() %>" name="pub-id">
        <input type="hidden" id="newPositionInput" name="user-answer">
        <button type="submit" class="chall-button">Te achei!</button>
    </form>
</div>
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&libraries=marker&v=beta"
        defer
></script>
<script src="index.js"></script>
</body>
</html>