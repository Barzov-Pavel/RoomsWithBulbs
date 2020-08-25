<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список комнат</title>
    <c:url var="urlCss" value="//main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Список комнат</h1>
<table>
    <tr>
        <th>Название комнаты</th>
        <th>Страна</th>
        <th>Включена ли лампочка</th>
    </tr>
    <c:forEach var="room" items="${rooms}">
        <tr>
            <td class="content">${room.name}</td>
            <td class="content">${room.country}</td>
            <td class="content">${room.lightIsOn}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>