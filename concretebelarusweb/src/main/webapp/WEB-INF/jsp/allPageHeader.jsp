<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<style>
    .firstHeader {
        margin-top: -8px;
    }

    .fHh3 {
        text-align:right;
        margin-top: -8px;
        margin-bottom: -8px;
    }

    .fHh1 {
        margin-bottom:-8px;
        display:inline-block
    }
    .fHh5 {
        font-size: 17px;
        color: mediumblue;
        float: right;
        margin-right: 8px;
        margin-top:3vw;
        margin-bottom: 1vw
    }
    .fHa {
        font-size: 16px;
        color: mediumblue;
        float: right;
        margin-top: 2vw;
        margin-bottom: 4vw;
        margin-right: 8px
    }

    .secondHeader {
        background: #565874;
        height: 5vw;
    }
    .sHa {
        font-size: 14px;
        color: white;
        float: left;
        margin-left: 10px;
        margin-top: -3px;
    }
    .fHimg {
        vertical-align: middle;
        margin-top: -8px;
    }
    .sHbutton {
        border-color: #fff;
        background-color: #565874;
        color: #fff;
        margin-right: 8px;
        margin-left: 95%;
    }
</style>
<header class="firstHeader">

    <h3 class="fHh3"> &#9742; +375-29-650-70-20 </h3>
    <h1 class="fHh1">
        <img class="fHimg" src="${pageContext.request.contextPath}images/fon-1.jpg" alt="П"> ОКУПАЙТЕ БЕТОН B CONCRETE BELARUS
    </h1>
    <%
        UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
        if (userDTO != null) {
            request.getSession().setAttribute(CURRENT_USER, userDTO);
        } else {
            userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
        }
        if (userDTO != null) {%>
    <h5 class="fHh5"> Добро пожаловать, <%= userDTO.getName()%></h5>
    <% } else {%>
    <a class="fHa"
       href='${pageContext.request.contextPath}authorizationPage'> Войти/Зарегистрироваться
    </a>
    <% }%>
    <h1></h1>
</header>
<p></p>
<header class="secondHeader">
    </br>
    <%if (userDTO != null) {%>
    <button class="sHbutton"
            onclick="location.href='${pageContext.request.contextPath}exit';"> Выйти
    </button>
    <% } %>
    <a class="sHa"
       href='${pageContext.request.contextPath}mainPage'> На главную страницу
    </a>
    <p></p>
    </br>
</header>
</html>
