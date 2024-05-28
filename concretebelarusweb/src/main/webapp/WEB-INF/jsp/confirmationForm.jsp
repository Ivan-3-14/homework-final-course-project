<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="application.entity.enums.roles.Roles" %>
<%@ page import="application.DTO.orderDTO.OrderDTO" %>
<%@ page import="static application.utils.Constant.*" %>
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
<% UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER); %>

<%if (userDTO != null) { %>

<% if (Roles.USER.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}userMainPage'> Мой аккаунт
</a>
<% } else if (Roles.MANAGER.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForManager.jsp'> Мой аккаунт
</a>
<% } else if (Roles.ADMIN.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}adminMainPage'> Мой аккаунт
</a>
<% }
}%>

<% OrderDTO orderDTO = (OrderDTO) request.getAttribute(CURRENT_ORDER);%>

<h3 style="margin-left: 255px"> Информация о заказе </h3>

<% if (orderDTO != null) {%>
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

<div>
    <form action="${pageContext.request.contextPath}confirmationOrder" method="get">
        <input type='hidden' name="currentOrderId" value="<%=orderDTO.getId()%>">
        <button> Подтвердить заказ </button>
    </form>

</br>
    <form action="${pageContext.request.contextPath}orderUpdateForm" method="post">
        <input type='hidden' name="currentOrderId" value="<%=orderDTO.getId()%>">
<button> Изменить заказ </button>
    </form>
</br>
    <form action="${pageContext.request.contextPath}deleteOrder" method="post">
        <input type='hidden' name="currentOrderId" value="<%=orderDTO.getId()%>">
<button> Удалить заказ </button>
    </form>
</div>
<%}%>
<div style="float: right; margin: -16vw 12vw -2vw -2vw; ">
<img style="width: 38vw; height: 22vw" src="${pageContext.request.contextPath}images/lodka.jpg" alt="лодки">
</div >
</body>
<jsp:include page="forAllPage.jsp"/>
</html>