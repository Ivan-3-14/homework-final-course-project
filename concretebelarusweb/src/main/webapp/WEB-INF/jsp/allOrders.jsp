<%@ page import="application.DTO.orderDTO.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="application.entity.enums.orderstatus.OrderStatus" %>
<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ page import="static application.utils.Constant.CURRENT_PAGE" %>
<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<title>Мои заказы</title>
<style>
    td {
        border: 1px solid black;
        font-size: 18px;
        text-align: center;
    }
    table {
        margin: auto;
        border-collapse: collapse;
        width: 90%
    }
    th {
        border: 1px solid black;
        padding: 1vw;
        text-align: center;
        margin: 5px
    }
</style>
<jsp:include page="allPageHeader.jsp"/>
<body>

    <% UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER); %>

<a style="font-size: 18px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}signUp'> Мой аккаунт
</a>

<body>
<div>
    <table style="border: none; margin-left: 40%; width: 10%; margin-bottom: 1px; margin-top: -3vw">
    <% if (userDTO != null) {%>

<form action="${pageContext.request.contextPath}searchOrder" method="get"
      style="">

        <label>
            <input  type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <th style="border: none; text-align: right">
            <input style="width: 22vw" type="text" name="search" placeholder="найти заказ">
            </th>
        </label>
    <th style="border: none; text-align: left">
        <button>Поиск</button>
    </th>
</form>
    <form action="${pageContext.request.contextPath}myOrders" method="get"
          style="margin-left: 55%; margin-top: 1vw">
        <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
        <th style="border: none; text-align: left">  <button>Сброс</button></th>

    </form>
    </table>
    <%}%>
</div>
</p>

<h3 style="text-align: center; margin-top: -1px; margin-bottom: 2px"> Мои заказы </h3>
<table>
    <thead>
    <tr>
        <th> Наименование объекта</th>
        <th> Дата доставки</th>
        <th> Время доставки</th>
        <th> Заполнитель</th>
        <th> Марка бетона</th>
        <th> Подвжность смеси</th>
        <th> Объём бетона</th>
        <th> Комментарий к заказу</th>
        <th> Стоимость, руб</th>
        <th> Статус заказа</th>
    </tr>
    </thead>
    <%
        List<OrderDTO> list = (List<OrderDTO>) request.getAttribute(LIST_ORDERS);
        int currentPage = (int) request.getAttribute(CURRENT_PAGE);
        int maxPage = (int) request.getAttribute(MAX_PAGE);
        if (list != null) {
        for (OrderDTO temp : list) { %>
    <tbody>
    <tr>
        <td><%=temp.getBuildingObjectDTO().getNameOfObject()%>
        </td>
        <td><%=temp.getDateOfDelivery()%>
        </td>
        <td><%=temp.getTimeOfDelivery()%>
        </td>
        <td><%=temp.getConcreteDTO().getAggregate().getValueOfAggregate()%>
        </td>
        <td><%=temp.getConcreteGradeDTO().getGradesConcrete()%>
        <%=temp.getConcreteGradeDTO().getConcreteClass().getConcreteClassName()%>
        <%=temp.getConcreteGradeDTO().getFrostResistance()%>
        <%=temp.getConcreteGradeDTO().getWaterproof()%>
        </td>
        <td><%=temp.getMobilityDTO().getMobilityValue().getValueOfMobility()%>
        </td>
        <td><%=temp.getVolumeOfConcrete()%>
        </td>
        <td><%=temp.getComment()%>
        </td>
        <td><%=temp.getCost()%>
        </td>
        <td><%=temp.getOrderStatus().getStatus()%>
        </td>
        <% if (OrderStatus.CLOSED != temp.getOrderStatus()) {
        %>
        <td style="border: none">
            <form style="margin: 2px" action="${pageContext.request.contextPath}updateOrderList" method="post">
            <button style="color: blue">
            <input type='hidden' name="tempOrderID" value="<%=temp.getId()%>">
                Изменить заказ
            </button>
            </form>
        </td>
        <td style="border: none">
            <form style="margin: 2px" action="${pageContext.request.contextPath}deleteOrderList" method="post">
                <input type='hidden' name="tempOrderID" value="<%=temp.getId()%>">
                <% if (userDTO != null) {%>
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <%}%>
                <button style="color: blue"> Удалить заказ </button>
            </form>
        </td>
        <%}%>
    </tr>
    <%}%>
    </tbody>
</table>

<div
        style="display:flex; position: absolute; margin-top: 5%; margin-left: 45%; height:20%;">
    <% if(maxPage != 0) {%>
    <% if (userDTO != null) {%>
<td style="text-align: left; margin-right: 4vw">
    <form method ="get" action="${pageContext.request.contextPath}searchOrder" >
        <button  style="margin: 6px; text-align: left">
            <%="первая  "%>
            <input type="hidden" name="currentPage" value="<%=NULL_PAGE%>" />
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
        </button>
    </form>
</td>

    <td style="text-align: right">
    <% if (currentPage != NULL_PAGE) {%>

        <form method ="get" action="${pageContext.request.contextPath}searchOrder" >
            <button  style="margin: 6px">
                        <%=currentPage%>
                <input type="hidden" name="currentPage" value="<%=currentPage - FIRST_PAGE%>" />
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
                       </button>
                   </form>

    <%} %>
</td>
<td>
    <form method ="get" action="${pageContext.request.contextPath}searchOrder">
    <button  style="margin: 6px; color: cornflowerblue">
    <%=currentPage + FIRST_PAGE%>
        <input type="hidden" name="currentPage" value="<%=currentPage%>" />
        <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
        <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
    </button>
    </form>
</td>

<td>
    <%
        if ((currentPage != maxPage - FIRST_PAGE)) {
    %>

        <form method="get" action="${pageContext.request.contextPath}searchOrder">
            <% int nextPage = currentPage + SECOND_PAGE; %>
            <button style="margin: 6px">
                <input type="hidden" name="currentPage" value="<%=nextPage - FIRST_PAGE%>"/>
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
                <%=nextPage%></button>
        </form>
</td>

    <td>
    <form method="get" action="${pageContext.request.contextPath}searchOrder">
                <button style="margin: 6px; text-align: right">
            <input type="hidden" name="currentPage" value="<%=maxPage - FIRST_PAGE%>"/>
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                    <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
            <%="последняя"%></button>
    </form>

    <%}%>
</td>
    <%}
    }%>
</div>
<%}%>


</body>
<jsp:include page="forAllPage.jsp"/>
</html>
