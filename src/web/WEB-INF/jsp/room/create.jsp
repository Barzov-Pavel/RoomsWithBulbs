<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создание новой комнаты</title>
    <c:url var="urlCss" value="//main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Приложение комнаты</h1>
<h2>Создание новой комнаты</h2>

<c:url var="urlRoomCreate" value="//room/save.html"/>
<form action="${urlRoomCreate}" method="post">
    <c:if test="${not empty room.id}">
        <input name="id" value="${room.id}" type="hidden">
    </c:if>
    <label for="name">Имя комнаты:</label>
    <input id="name" name="name" value="${room.name}">
    <label for="country">Страна:</label>
    <select id="country" name="country">
        <c:forEach var="country" items="${countries}">
            <c:choose>
                <c:when test="${role.id == user.role.id}">
                    <c:set var="selected" value="selected"/>
                </c:when>
                <c:otherwise>
                    <c:remove var="selected"/>
                </c:otherwise>
            </c:choose>
            <option value="${country}" ${selected}>${country}</option>
        </c:forEach>
    </select>
    <label for="light">Включена ли лампочка:</label>
    <input id="light" name="light" value="${room.lightIsON}">
    <button class="save">Сохранить</button>
</form>
</body>
</html>