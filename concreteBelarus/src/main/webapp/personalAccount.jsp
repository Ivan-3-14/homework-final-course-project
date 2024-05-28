<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@ page import="application.utils.enums.roles.Roles" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<style>
    td {
        font-size: 18px;
    }
</style>
<jsp:include page="allPageHeader.jsp"/>

<%UserDTO userDTO = (UserDTO) request.getSession().getAttribute("current");%>
<body>
<% if (Roles.USER.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForUser.jsp'> Мой аккаунт
</a>
<% } else if (Roles.MANAGER.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForManager.jsp'> Мой аккаунт
</a>
<% } else if (Roles.ADMIN.equals(userDTO.getRole())) { %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/adminMyself'> Мой аккаунт
</a>
<% } %>

<h3 style="text-align: center">Персональные данные </h3>
<table style="margin: auto">
    <tbody>
    <tr>
    <td>Имя </td>
    <td><%=userDTO.getName()%></td>
    </tr>

    <tr>
    <td>Фамилия </td>
    <td><%=userDTO.getSurname()%></td>
    </tr>

    <tr>
        <td>Номер телефона     </td>
        <td>+ <%=userDTO.getTelephoneNumber()%></td>
    </tr>
    <tr>
        <td>Электронная почта (email) </td>
        <td><%=userDTO.getEmail()%></td>
    </tr>
    <tr>
    <td>
        <button style="font-size: 14px; margin-left: 326px"
            onclick="location.href='${pageContext.request.contextPath}/updateMe.jsp';"> Изменить
        </button>
    </td>
    </tr>
    <td>
        <form action="${pageContext.request.contextPath}/verifyPass" method="get">
        <button style="font-size: 14px; margin-left: 326px"
        > Сменить пароль
            <input type='hidden' name="email" value="<%=userDTO.getEmail()%>">
        </button>
        </form>
    </td>
    </tbody>
</table>

</body>
<jsp:include page="forAllPage.jsp"/>
</html>
