<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <title>Личный кабинет менеджера</title>
</head>

<style>
    body { background: #F4F1F5}
</style>

<jsp:include page="allPageHeader.jsp"/>

<body>
</br>

<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/myAccount'> Мой профиль
</a>
</br>
</br>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/myOrders'> Создать нового менеджера
</a>
</br>
</br>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/myObjects'> Добавить марку бетона
</a>
</br>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>