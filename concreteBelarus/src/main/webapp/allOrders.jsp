<%@ page import="java.util.List" %>
<%@ page import="application.servicelevel.DTO.orderDTO.OrderDTO" %>
<%@ page import="static application.utils.constant.ConstantsContainer.ROW_IN_PAGE_ORDERS" %>
<%@ page import="static application.utils.constant.ConstantsContainer.CURRENT_PAGE" %>
<%@ page import="application.utils.enums.orderstatus.OrderStatus" %>
<%@ page import="static application.utils.constant.ConstantsContainer.*" %>
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
        width: 95%
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
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForUser.jsp'> Мой аккаунт
</a>
<h3 style="text-align: center"> Мои заказы </h3>
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
        <th> Статус заказа, руб</th>
    </tr>
    </thead>
    <%
        List<OrderDTO> list = (List<OrderDTO>) request.getAttribute("listOrders");
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
        <td>
            <form style="margin: 2px" action="${pageContext.request.contextPath}/updateOrderList" method="get">
            <button>
            <input type='hidden' name="tempOrderID" value="<%=temp.getId()%>">
                Изменить заказ
            </button>
            </form>
        </td>
        <%}%>
    </tr>
    <%}%>
    </tbody>
</table>
<div
        style="display:flex; justify-content:center; align-items:center; height:20%;"
>
<td>
<%
        if (currentPage != 1) {
    %>

        <form method ="post" action="${pageContext.request.contextPath}/myOrders" >
            <% int prevPage = currentPage - 1;
            %>
            <button  style="margin: 6px">
                        <%=prevPage%>
                <input type="hidden" name="currentPage" value="<%=prevPage%>" />
                       </button>
                   </form>

    <%} %>
</td>
<td>
    <button  style="margin: 6px; color: cornflowerblue">
    <%=currentPage%>
    </button>
</td>
<td>
    <%
        if ((currentPage != maxPage)) {
    %>

        <form method="post" action="${pageContext.request.contextPath}/myOrders">
            <% int nextPage = currentPage + FIRST_PAGE; %>
            <button style="margin: 6px">
                <input type="hidden" name="currentPage" value="<%=nextPage%>"/>
                <%=nextPage%></button>
        </form>

    <%}%>
</td>
    <%}%>
</div>


</body>
<jsp:include page="forAllPage.jsp"/>
</html>
