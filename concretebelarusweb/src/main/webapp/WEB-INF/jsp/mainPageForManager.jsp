<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <title>Личный кабинет менеджера</title>
</head>

<style>
    body { background: #F4F1F5}
    a {font-size: 16px; color: mediumblue;  float: left; vertical-align: bottom;}
</style>

<jsp:include page="allPageHeader.jsp"/>

<body>
</br>

<a href='${pageContext.request.contextPath}/myAccount'> Мой профиль
</a>
</br>
</br>
<a href='${pageContext.request.contextPath}/orderForm.jsp'> Создать новую заявку
</a>
</br>
</br>
<a href='${pageContext.request.contextPath}/myObjects'> Все пользователи
</a>
</br>
<a href='${pageContext.request.contextPath}/changePrices.jsp'> Изменить цены
</a>
</body>
</br>
<jsp:include page="forAllPage.jsp"/>
</html>