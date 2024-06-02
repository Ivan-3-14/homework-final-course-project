<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<style>
    h2 {
        color: rgb(13, 13, 13);
        margin-top: 2vw;
        margin-left: 33vw;
        margin-bottom: -3vw;
    }
    a {
        text-decoration: none;
    }
    .mbutton {
        background: #14d554;
        color: #fff;
        border: none;
        margin-top: 1vw;
        margin-bottom: 1vw;
        width: 13vw;
        height: 2.5vw;
        font-size: 1vw;
    }
    input {
        width: 200px;
        height: 35px;
        text-align: center;
    }

</style>
<head>
    <title>Oбновление аккаунта</title>
</head>
<jsp:include page="allPageHeader.jsp"/>

<%
    UserDTO userDTO = (UserDTO) request.getAttribute("currentManager");
if (userDTO != null) { %>

<body>
<a style="font-size: 18px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}signUp'> Мой аккаунт
</a>

<h2>введите новые данные, где необходимо</h2>
<div>
    <form style="margin-left: 40%; margin-top: -1vw; width: 21vw; margin-bottom: -1vw; display: block;"
          method="post" action="${pageContext.request.contextPath}updateManager" >
        <table style="margin-top: -1vw">
        <tr>
            <td> Имя</td>
                <td>
        <label>
            <input type="text" name="name" value="<%=userDTO.getName()%>" placeholder="<%=userDTO.getName()%>"/>
        </label>
                </td>
        </tr>
        </br>
        <tr>
            <td> Фамилия</td>
            <td>
        <label>
            <input type="text" name="surname" value="<%=userDTO.getSurname()%>" placeholder="<%=userDTO.getSurname()%>"/>
        </label>
            </td>
        </tr>
        </br>
        <tr>
            <td> Номер телефона </td>
            <td>
        <label>
            <input type="text" name="telephoneNumber" value="<%=userDTO.getTelephoneNumber()%>" placeholder="<%=userDTO.getTelephoneNumber()%>"/>
        </label>
            </td>
        </tr>
        </br>
        <tr>
            <td>  Email </td>
            <td>
        <label>
            <input type="hidden" name="userId" value="<%=userDTO.getId()%>">
            <input type="hidden" name="password" value="<%=userDTO.getPassword()%>">
            <input type="text" name="email" value="<%=userDTO.getEmail()%>" placeholder="<%=userDTO.getEmail()%>"/>
        </label>
        </td>

        </tr>

        </br>
            <tr>
                <td></td>
                <td><button class="mbutton" >Изменить</button></td>
            </tr>
        </table>

    </form>

</div>
<% } %>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>