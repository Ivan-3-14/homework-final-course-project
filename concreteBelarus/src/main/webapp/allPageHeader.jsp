<%@ page import="application.servicelevel.DTO.usersDTO.UserDTO" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<header style="margin-top: -8px;">

    <h3 style="text-align:right; margin-top: -8px; margin-bottom: -8px;"> &#9742; +375-29-650-70-20 </h3>
    <h1 style=margin-bottom:-8px; display:inline-block>
        <img style="vertical-align: middle; margin-top: -8px;" src="img/fon-1.jpg" alt="П"> ОКУПАЙТЕ БЕТОН B CONCRETE
        BELARUS
    </h1>
    <%
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("current");
        if (userDTO != null) {%>
    <h5 style="font-size: 17px; color: mediumblue;  float: right;  margin-right: 8px; margin-top:0; margin-bottom: 1vw"
    > Добро пожаловать, <%= userDTO.getName()%>
    </h5>
    <% } else {%>
    <a style="font-size: 16px; color: mediumblue;  float: right; vertical-align: bottom; margin-right: 8px"
       href='${pageContext.request.contextPath}/authorization'> Войти/Зарегистрироваться
    </a>
    <% }%>
    <h1></h1>
</header>
<p></p>
<header style="background: #000000">
    </br>
    <%if (userDTO != null) {%>
    <button style="border-color: #fff; background-color: #000000 ;  color: #fff;   margin-right: 8px; margin-left: 95%;"
            onclick="location.href='${pageContext.request.contextPath}/exit';"> Выйти
    </button>
    <% } %>
    <a style="font-size: 14px; color: white;  float: left; margin-left: 10px; margin-top: -3px;"
       href='${pageContext.request.contextPath}/index.jsp'> На главную страницу
    </a>
    <p></p>
    </br>
</header>
</html>
