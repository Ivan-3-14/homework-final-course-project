<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <title>Личный кабинет пользователя</title>
</head>

<style>
    body { background: #F4F1F5}
</style>

<jsp:include page="allPageHeader.jsp"/>

<body>
</br>

<%UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
if (userDTO != null) {
    request.getSession().setAttribute(CURRENT_USER, userDTO);
}
else {
    userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
}
%>

<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}myAccount'> Мой профиль
</a>
</br>
</br>

        <a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
           href='${pageContext.request.contextPath}myOrders?currentUserId=<%=userDTO.getId()%>'> Мои заказы
</a>
</br>
</br>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}myObjects?currentUserId=<%=userDTO.getId()%>'> Мои объекты
</a>
</br>
<button style="position: absolute;  background: #66d4ef; color: #fff; border: none; padding: 1rem 1.5rem;
        margin-bottom: 1rem; border-radius: 8px; font-size: 15px;  left: 5px; top: 480px;"
        onclick="location.href='${pageContext.request.contextPath}newOrderForm';"> Новая заявка </button>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>