<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<jsp:include page="allPageHeader.jsp"/>
<body>

<%
    UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
    if (userDTO != null) {
        request.getSession().setAttribute(CURRENT_USER, userDTO);
    } else {
        userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
    }%>
        <form method="get" action="checkPassword">
            <p style="margin-top: 15px; position: inherit; width: 50vw; margin-left: 1%; margin-bottom: 5px;">
                Введите Ваш старый пароль:
            </p>
            <div style="width: 30vw;">
                <input type="text" name="password" placeholder="старый пароль">
                <input type='hidden' name="email" value="<%=userDTO.getEmail()%>">
            </div>
            <input style="margin-top: 1vw" type="submit" value="Проверить" class="btn">

        </form>
</body>
<jsp:include page="forAllPage.jsp"/>