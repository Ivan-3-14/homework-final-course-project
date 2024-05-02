<%@ page import="static application.utils.constant.ConstantsContainer.LAST_DAYS" %>
<%@ page import="static application.utils.constant.ConstantsContainer.INCORRECT_TIME" %>
<%@ page import="static application.utils.constant.ConstantsContainer.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<style>
    body { background: #801fe2
    }
    .windows {
        background: #e1dfe3;
        position: fixed;
        top: 30%;
        left: 40%;
        justify-content: center;
        width: 300px;
        height: 75px;
        text-align: center;
        padding: 5px;
        border: 3px solid #cc0018;
        border-radius: 10px;
        color: #cc0029;

    }
</style>
<body>

<jsp:include page="orderForm.jsp"/>

<%request.getSession().setAttribute(NAME, request.getAttribute(NAME));%>
<%request.getSession().setAttribute(SURNAME, request.getAttribute(SURNAME));%>
<%request.getSession().setAttribute(NUMBER_OF_PHONE, request.getAttribute(NUMBER_OF_PHONE));%>
<%request.getSession().setAttribute(DISTANCE, request.getAttribute(DISTANCE));%>
<%request.getSession().setAttribute(VOLUME, request.getAttribute(VOLUME));%>
<%request.getSession().setAttribute(OBJECT_NAME, request.getAttribute(OBJECT_NAME));%>
<%request.getSession().setAttribute(COMMENT, request.getAttribute(COMMENT));%>
<%request.getSession().setAttribute(INCORRECT_D_T, true);%>

<% if(request.getAttribute(LAST_DAYS) != null) {%>
<div class="windows">
    <p>Неверный выбор даты!</p>
    <p>
        <button
                onclick="location.href='${pageContext.request.contextPath}/orderForm.jsp';"> Понятно
        </button>
    </p>

</div>
<%} %>

<%if(request.getAttribute(INCORRECT_TIME) != null) {%>
<div class="windows">

    <p>Вы выбрали нерабочее время!</p>
    <p>
        <button
                onclick="location.href='${pageContext.request.contextPath}/orderForm.jsp';"> Понятно
        </button>
    </p>
</div>
<%}%>

</body>

</html>
