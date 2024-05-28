<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<jsp:include page="allPageHeader.jsp"/>
<body>

<%UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
    if (userDTO != null) {
        request.getSession().setAttribute(CURRENT_USER, userDTO);
    } else {
        userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
    }
%>
        <form action="${pageContext.request.contextPath}changePassword" method="post">
            <h2 style="margin-top: 15px; margin-bottom: 10px; width: 50vw; position: inherit;
             margin-left: 1%;"> Введите новый пароль </h2>
            <div style="width: 30vw;">
                <i class="fa-solid fa-key"></i>
                <input type="text" name="newPassword1" placeholder="новый пароль">
            </div>
            <div style="width: 30vw;">
                <i class="fa-solid fa-key"></i>
                <input type="text" name="newPassword2" placeholder="повторите пароль">
            </div>
            <% if (userDTO != null) {%>
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
<%}%>
            <input type="submit" value="Изменить" class="btn">
        </form>
</body>
<jsp:include page="forAllPage.jsp"/>