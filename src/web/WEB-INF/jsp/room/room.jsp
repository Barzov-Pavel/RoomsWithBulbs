<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" HTTP-EQUIV="Refresh" CONTENT="5">
    <title>Комната</title>
    <c:url var="urlCss" value="//main.css"/>
    <link href="${urlCss}" rel="stylesheet">
    <c:url var="urlLightOnOff" value="//room/light-on.html">
        <c:param name="id" value="${room.id}"/>
        <c:param name="name" value="${room.name}"/>
        <c:param name="country" value="${room.country}"/>
        <c:param name="light" value="${room.lightIsOn}"/>
    </c:url>
</head>
<body>

<a href="/rooms/index.html">Главная страница</a>
<h1>Комната ${room.name}</h1>

<form action="${urlLightOnOff}" method="post">
    ${room.lightIsOn ? "Лампочка включена" : "Лампочка выключена"}

    <button class="save" formaction="${urlLightOnOff}" formmethod="post" >Лампочка вкл/выкл</button>

</form>
</body>
</html>