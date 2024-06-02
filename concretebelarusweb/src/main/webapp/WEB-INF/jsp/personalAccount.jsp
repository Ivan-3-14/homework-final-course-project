<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="application.entity.enums.roles.Roles" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<style>
    td {
        font-size: 18px;
    }
</style>
<jsp:include page="allPageHeader.jsp"/>

<%UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
if (userDTO != null) {
    request.getSession().setAttribute(CURRENT_USER, userDTO);
} else {
    userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
}%>
<body>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}signUp'> Мой аккаунт
</a>

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
        <td> <%=userDTO.getTelephoneNumber()%></td>
    </tr>
    <tr>
        <td>Электронная почта (email) </td>
        <td><%=userDTO.getEmail()%></td>
    </tr>
    <tr>
    <td>
        <button style="font-size: 14px; margin-left: 326px"
            onclick="location.href='${pageContext.request.contextPath}updateMe'"> Изменить
        </button>
    </td>
    </tr>
    <td>
        <form action="${pageContext.request.contextPath}verifyPassword" method="get">
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
