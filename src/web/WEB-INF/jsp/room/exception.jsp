<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ошибка доступа</title>
    <c:url var="urlCss" value="//main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Из вашей страны запрещен доступ в эту комнату</h1>
<c:url var="listRooms" value="//room/list.html">
</c:url>
<a href="${listRooms}">Вернуться к списку комнат</a>
</body>
</html>