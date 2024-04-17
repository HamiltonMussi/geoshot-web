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
    <div id="map"></div>
</div>
<div class="bottomRow">
    <form method="post" action="/create-chall" id="guessForm" enctype="multipart/form-data">
        <input name="photo" type="file">
        <input type="hidden" id="newPositionInput" name="anwser">
        <button type="submit">Criar Desafio</button>
    </form>
</div>
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initMap&libraries=marker&v=beta"
        defer
></script>
<script src="index.js"></script>
</body>
</html>
