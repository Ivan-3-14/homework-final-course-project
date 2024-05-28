<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Заказ подтверждён</title>

<jsp:include page="allPageHeader.jsp"/>

<% UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER); %>

<div style="text-align: center; margin-top: 8%">
<p style="font-size: 18px; color: #32b404">Ваш заказ подтверждён.
    Менеджер свяжется с Вами в течение ближайшего времени</p>

    <%if (userDTO != null) {%>
    <form action="myOrders" method="get">
    <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
    <button> Далее
    </button>
    </form>
    <% } else {%>
    <button
            onclick="location.href='${pageContext.request.contextPath}mainPage';"> Далее
    </button>
    <% } %>

</div>

<jsp:include page="forAllPage.jsp"/>

</html>