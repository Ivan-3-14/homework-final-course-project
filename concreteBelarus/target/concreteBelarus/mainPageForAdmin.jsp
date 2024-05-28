<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <title>Личный кабинет администратора</title>
</head>

<title>Страница администратора</title>

<style>
    body {
        background: #F4F1F5
    }

    table {
        margin: auto;
        border-collapse: collapse;
        width: 80%;
    }
    td {
        border: 1px solid black;
        font-size: 18px;
        text-align: center;
    }
    th {
        border: 1px solid black;
        padding: 1vw;
        text-align: center;
        margin: 5px;
        width: auto;
    }
    a {
        font-size: 16px; color: mediumblue;  float: left; vertical-align: bottom;
    }
</style>

<jsp:include page="allPageHeader.jsp"/>

<body>
</br>

<a href='${pageContext.request.contextPath}/myAccount'> Мой профиль
</a>
</br>
</br>
<a href='${pageContext.request.contextPath}/createNewManager'> Создать нового менеджера
</a>
</br>
</br>
<a href='${pageContext.request.contextPath}/changePrices.jsp'> Изменить цены
</a>
</br>

<% List<UserDTO> list = (List<UserDTO>) request.getAttribute("allManagerList"); %>

<div style="float: left; margin-top: -4vw; margin-left: 9vw; width: 80%">
    <table>
        <thead>
        <tr>
            <th> № п/п</th>
            <th> Имя менеджера</th>
            <th> Фамилия менеджера</th>
            <th> Телефон менеджера</th>
            <th> Email</th>
        </tr>
        </thead>

        <%
            if (list != null) {
                int i = 0;
                for (UserDTO u : list) {
                    i++;
        %>
        <tbody>
        <tr>
            <td><%=i%>
            </td>
            <td><%=u.getName()%>
            </td>
            <td><%=u.getSurname()%>
            </td>
            <td><%=u.getTelephoneNumber()%>
            </td>
            <td><%=u.getEmail()%>
            </td>
            <td style="border: none; width: 2vw">
                <form style="margin: 2px" action="${pageContext.request.contextPath}/updateManager" method="get">
                    <button style="color: blue;">
                        <input type='hidden' name="managerId" value="<%=u.getId()%>">
                        Изменить менеджера
                    </button>
                </form>
            </td>
            <td style="border: none; width: 2vw">
                <form style="margin: 2px" action="${pageContext.request.contextPath}/deleteManager" method="get">
                    <button style="color:darkmagenta">
                        <input type='hidden' name="managerId" value="<%=u.getId()%>">
                        Удалить менеджера
                    </button>
                </form>
            </td>
        </tr>
        </tbody>
        <% }
        } %>
    </table>
</div>

</body>
<jsp:include page="forAllPage.jsp"/>
</html>