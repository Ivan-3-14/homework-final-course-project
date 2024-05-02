<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@ page import="application.servicelevel.DTO.orderDTO.OrderDTO" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
</head>
<style>
    button {
        font-size: 14px;
        margin-left: 32px;
        margin-top: 22px;
    }
</style>
<head>
    <title>Информация о заказе</title>
</head>

<jsp:include page="allPageHeader.jsp"/>

<body>
<% UserDTO userDTO = (UserDTO) request.getSession().getAttribute("current"); %>
<%if (userDTO != null) {%>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForUser.jsp'> Мой аккаунт
</a><%}%>
<% OrderDTO orderDTO = (OrderDTO) request.getSession().getAttribute("currentOrder");%>

<h3 style="margin-left: 255px"> Информация о заказе </h3>
<table style="float: left; margin-right: 20px; margin-left: 200px;">
    <tbody>
    <tr>
        <td>Наименование объекта</td>
        <td><%=orderDTO.getBuildingObjectDTO().getNameOfObject()%>
        </td>
    </tr>
    <tr>
        <td>Расстояние до объекта</td>
        <td><%=orderDTO.getBuildingObjectDTO().getDistanceToObject()%>
        </td>
    </tr>
    <tr>
        <td>Дата заливки</td>
        <td><%=orderDTO.getDateOfDelivery()%>
        </td>
    </tr>
    <tr>
        <td>Время доставки</td>
        <td><%=orderDTO.getTimeOfDelivery()%>
        </td>
    </tr>

    <tr>
        <td>Объём бетона</td>
        <td><%=orderDTO.getVolumeOfConcrete()%> м3</td>
    </tr>
    <tr>
        <td>Марка Бетона</td>
        <td><%=orderDTO.getConcreteGradeDTO().getGradesConcrete()%>
            <%=orderDTO.getConcreteGradeDTO().getConcreteClass().getConcreteClassName()%>
            <%=orderDTO.getConcreteGradeDTO().getFrostResistance()%>
            <%=orderDTO.getConcreteGradeDTO().getWaterproof()%>
        </td>
    </tr>
    <tr>
        <td>Заполнитель</td>
        <td><%=orderDTO.getConcreteDTO().getAggregate().getValueOfAggregate()%>
        </td>
    </tr>
    <tr>
        <td>Подвижность смеси</td>
        <td><%=orderDTO.getMobilityDTO().getMobilityValue().getValueOfMobility()%>
        </td>
    </tr>
    <tr>
        <td>Имя заказчика</td>
        <td><%=orderDTO.getUserDTO().getName()%>
            <%=orderDTO.getUserDTO().getSurname()%>
        </td>
    </tr>
    <tr>
        <td>Телефон для связи</td>
        <td><%=orderDTO.getUserDTO().getTelephoneNumber()%>
        </td>
    </tr>
    <tr>
        <td>Комментарий</td>
        <td><%=orderDTO.getComment()%>
        </td>
    </tr>
    <tr>
        <td>Стоимость заказа</td>
        <td><%=orderDTO.getCost()%> руб</td>
    </tr>
</table>
<%
    request.getSession().setAttribute("currentOrderId", orderDTO.getId());
%>
<div>
<%if (userDTO != null) {%>
<button
        onclick="location.href='${pageContext.request.contextPath}/mainPageForUser.jsp';"> Подтвердить заказ
</button>
<% } else {%>
<button
        onclick="location.href='${pageContext.request.contextPath}/index.jsp';"> Подтвердить заказ
</button>
<% } %>
</br>
<button
        onclick="location.href='${pageContext.request.contextPath}/orderUpdate.jsp';"> Изменить заказ
</button>
</br>
<button
        onclick="location.href='${pageContext.request.contextPath}/deleteOrder';"> Удалить заказ
</button>
</div>

<div style="float: right; margin: -11vw 12vw 2vw 2vw;">
<img  style="width: 38vw; height: 22vw" src="img/lodka.jpg" alt="лодки">
</div>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>