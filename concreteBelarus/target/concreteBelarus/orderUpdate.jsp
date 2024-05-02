<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@ page import="application.servicelevel.DTO.orderDTO.OrderDTO" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<title>Форма заказа</title>

<style>
    body { background: #F4F1F5}
</style>

<jsp:include page="allPageHeader.jsp"/>

<%UserDTO userDTO = (UserDTO) request.getSession().getAttribute("current");%>

<body>
<%if (userDTO != null) {%>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForUser.jsp'> Мой аккаунт
</a>
<%}%>

<% OrderDTO orderDTO = (OrderDTO) request.getSession().getAttribute("currentOrder");
if (orderDTO != null) {
        request.setAttribute("currentOrderId", orderDTO.getId());
    %>
<form style="margin-left: 20%; margin-top: 40px; width: 80%; display: block;"
      method="post" action="${pageContext.request.contextPath}/updateOrder" >

    <label>
        Наименование объекта
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 66.5px;"
               name="nameOfObject"
               value="<%=orderDTO.getBuildingObjectDTO().getNameOfObject()%>"
               placeholder="Наименование"/>
    </label>

    <label style="margin-left: 40px;" for="Aggregate">Выберите заполнитель</label>
    <select id="Aggregate" name="aggregate" style="width: 200px; height: 35px; text-align:center; margin-left: 60px">
        <option value="GRAVEL">Гравий</option>
        <option value="CRUSHED_STONE">Щебень</option>
    </select>

    </br>

    <label>
        Расстояние от МКАД до объекта
        <input type="text" style="width: 200px; height: 35px; text-align:center"
               name="distanceToObject"
               value="<%=orderDTO.getBuildingObjectDTO().getDistanceToObject()%>"
               placeholder="Расстояние, км"/>
    </label>

    <label style="margin-left: 40px;" for="ConcreteGrade">Выберите марку бетона</label>
    <select id="ConcreteGrade" name="concreteGrade" style="width: 200px; height: 35px; text-align:center; margin-left: 54px">
        <option value="150">M150</option>
        <option value="200">M200</option>
        <option value="250">M250</option>
        <option value="300">M300</option>
        <option value="350">M350</option>
        <option value="400">M400</option>
        <option value="450">M450</option>
        <option value="500">M500</option>
    </select>

    </br>

    <label>
        Объём бетона
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 129px"
               name="volumeOfConcrete"
               value="<%=orderDTO.getVolumeOfConcrete()%>"
               placeholder="Объём, м3"/>
    </label>

    <label style="margin-left: 40px;" for="Mobility">Выберите подвижность смеси</label>
    <select id="Mobility" name="mobility" style="width: 200px; height: 35px; text-align:center; margin-left: 10px">
        <option value="1">П1</option>
        <option value="2">П2</option>
        <option value="3">П3</option>
        <option value="4">П4</option>
        <option value="5">П5</option>
    </select>

    </br>

    <label>
        Имя заказчика
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 125px"
               name="name" value="<%=orderDTO.getUserDTO().getName()%>"
               placeholder=<%=orderDTO.getUserDTO().getName()%>
        >

    </label>

    <label style="margin-left: 40px;">
        Выберите дату доставки
        <input  type="date" style="width: 200px; height: 35px; text-align:center; margin-left: 49px"
                id="dateOfDelivery" name="dateOfDelivery"
                value="<%=orderDTO.getDateOfDelivery()%>"
        >
    </label>

    </br>

    <label>
        Фамилия заказчика
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 91.5px"
               name="surname" value="<%=orderDTO.getUserDTO().getSurname()%>">
    </label>

     <label style="margin-left: 40px;">
        Выберите время доставки
        <input type="time" style="width: 200px; height: 35px; text-align:center; margin-left: 39px"
               id="timeOfDelivery" name="timeOfDelivery"
               value="<%=orderDTO.getTimeOfDelivery()%>"
        >
    </label>

    </br>
    <label>
        Номер телефона
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 111.5px"
               name="numberOfPhone" value="<%=orderDTO.getUserDTO().getTelephoneNumber()%>">
    </label>
    <label>
    <input type="text" style="width: 420px; height: 38px; text-align:center; margin-left: 40px"
           name="comment" placeholder="Комментарий к заказу"
    value="<%=orderDTO.getComment()%>"/>
    </label>
    </br>
    <input style="width: 150px; height: 25px; text-align:center; margin-top: 12px; margin-left: 740px;" type="submit">
</form>
<% } %>

<% if (request.getAttribute("checkGrade") != null) {%>
<p style="color: #ec6767; margin-left: 550px; font-size: 18px;"> Марка бетона на гравии не может быть выше М300</p>
<% } %>

<% if (request.getAttribute("wrongDistanceOrVolume") != null) {%>
<p style="color: #ec6767; margin-left: 550px; font-size: 18px;"> Расстояние и объём бетона должны быть указаны цифрами</p>
<% } %>
</body>

<% if (request.getAttribute("wrongTimeOrDate") != null) {%>
<p style="color: #ec6767; margin-left: 550px; font-size: 18px;"> Пожалуйста, выберите дату и время</p>
<% } %>
</body>

<jsp:include page="forAllPage.jsp"/>
</html>
