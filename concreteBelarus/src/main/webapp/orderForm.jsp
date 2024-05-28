<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.constant.ConstantsContainer.NAME" %>
<%@ page import="static application.utils.constant.ConstantsContainer.SURNAME" %>
<%@ page import="static application.utils.constant.ConstantsContainer.*" %>
<%@ page import="static application.utils.constant.ConstantsContainer.DISTANCE" %>
<%@ page import="static application.utils.constant.ConstantsContainer.VOLUME" %>
<%@ page import="static application.utils.constant.ConstantsContainer.OBJECT_NAME" %>
<%@ page import="application.utils.enums.roles.Roles" %>
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
</a><%}%>

<%
    if (request.getSession().getAttribute(INCORRECT_D_T) != null) {
        request.setAttribute(NAME, request.getSession().getAttribute(NAME));
        request.setAttribute(SURNAME, request.getSession().getAttribute(SURNAME));
        request.setAttribute(TEL_NUMBER, request.getSession().getAttribute(TEL_NUMBER));
        request.setAttribute(DISTANCE, request.getSession().getAttribute(DISTANCE));
        request.setAttribute(VOLUME, request.getSession().getAttribute(VOLUME));
        request.setAttribute(OBJECT_NAME, request.getSession().getAttribute(OBJECT_NAME));
        request.setAttribute(COMMENT, request.getSession().getAttribute(COMMENT));
        request.setAttribute(INCORRECT_D_T, null);
    }
    %>
<form style="margin-left: 20%; margin-top: 40px; width: 80%; display: block;"
      method="post" action="${pageContext.request.contextPath}/newOrder" >

    <label>
        Наименование объекта
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 66.5px;"
               name="nameOfObject"
               value="<%=request.getAttribute(OBJECT_NAME) != null ? request.getAttribute(OBJECT_NAME) : ""%>"
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
               value="<%=request.getAttribute(DISTANCE) != null ? request.getAttribute(DISTANCE) : ""%>"
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
               value="<%=request.getAttribute(VOLUME) != null ? request.getAttribute(VOLUME) : ""%>"
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
        <%if (userDTO != null && !Roles.MANAGER.equals(userDTO.getRole())) {%>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 125px"
               name="name" value="<%=userDTO.getName()%>" placeholder=<%=userDTO.getName()%>>
            <% } else {%>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 125px"
               name="name" value="<%=request.getAttribute(NAME) != null ? request.getAttribute(NAME) : ""%>"  placeholder="Имя"/>
        <% }%>
    </label>

    <label style="margin-left: 40px;">
        Выберите дату доставки
        <input  type="date" style="width: 200px; height: 35px; text-align:center; margin-left: 49px"
                id="dateOfDelivery" name="dateOfDelivery"
                value="<%=request.getParameter(DATE_OF_DEL) != null ? request.getParameter(DATE_OF_DEL) : ""%>"
        >
    </label>

    </br>

    <label>
        Фамилия заказчика
        <%if (userDTO != null && !Roles.MANAGER.equals(userDTO.getRole())) {%>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 91.5px"
               name="surname" value="<%=userDTO.getSurname()%>" placeholder=<%=userDTO.getSurname()%>>
        <% } else {%>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 91.5px"
               name="surname"
               value="<%=request.getAttribute(SURNAME) != null ? request.getAttribute(SURNAME) : ""%>"
               placeholder="Фамилия"/>
        <% }%>
    </label>

    <label style="margin-left: 40px;">
        Выберите время доставки
        <input type="time" style="width: 200px; height: 35px; text-align:center; margin-left: 39px"
               id="timeOfDelivery" name="timeOfDelivery"
               value="<%=request.getParameter(TIME_OF_DEL) != null ? request.getParameter(TIME_OF_DEL) : ""%>"
        >
    </label>

    </br>
    <label>
        Номер телефона
        <%if (userDTO != null && !Roles.MANAGER.equals(userDTO.getRole())) { %>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 111.5px"
               name="telephoneNumber" value="<%=userDTO.getTelephoneNumber()%>" placeholder="<%=userDTO.getTelephoneNumber()%>">
        <% } else {%>
        <input type="text" style="width: 200px; height: 35px; text-align:center; margin-left: 111.5px"
               name="telephoneNumber"
               value="<%=request.getAttribute(TEL_NUMBER) != null ? request.getAttribute(TEL_NUMBER) : ""%>"
               placeholder="Номер телефона"/>
        <% }%>
    </label>
    <label>
    <input type="text" style="width: 420px; height: 38px; text-align:center; margin-left: 40px"
           name="comment"
           value="<%=request.getAttribute(COMMENT) != null ? request.getAttribute(COMMENT) : ""%>"
           placeholder="Комментарий к заказу"/>
    </label>
    </br>
    <input style="width: 150px; height: 25px; text-align:center; margin-top: 12px; margin-left: 740px;" type="submit">
</form>

<% if (request.getAttribute("checkGrade") != null) {%>
<p style="color: #ec6767; margin-left: 38%; font-size: 18px;"> Марка бетона на гравии не может быть выше М300</p>
<% } %>

<% if (request.getAttribute("wrongDistanceOrVolume") != null) {%>
<p style="color: #ec6767; margin-left: 38%; font-size: 18px;"> Расстояние и объём бетона должны быть указаны цифрами</p>
<% } %>
</body>

<% if (request.getAttribute("wrongTimeOrDate") != null) {%>
<p style="color: #ec6767; margin-left: 38%; font-size: 18px;"> Пожалуйста, выберите дату и время</p>
<% } %>
</body>

<jsp:include page="forAllPage.jsp"/>
</html>
