<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<title>Добавить менеджера</title>

<style>
    body { background: #F4F1F5}
    input {
        width: 300px;
        height: 35px;
        text-align: center;
    }
</style>

<jsp:include page="allPageHeader.jsp"/>

<body>

<%UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);%>
<%if (userDTO != null) {%>
<a style="font-size: 18px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}adminMainPage'> Мой аккаунт
</a><%}%>


<form style="margin-left: 40%; margin-top: 2vw; width: 21vw; margin-bottom: -1vw; display: block;"
      method="post" action="${pageContext.request.contextPath}createNewManager" >

    <label>
        Имя менеджера
        <input type="text" name="name"  placeholder="Имя"/>
    </label>

    </br>

    <label>
        Фамилия менеджера
        <input type="text" name="surname" placeholder="Фамилия"/>
    </label>

    </br>

    <label>
        Номер телефона
        <input type="text" name="telephoneNumber" placeholder="Номер телефона"/>
    </label>

    </br>

    <label>
        Email
        <input type="text" name="email" placeholder="Email"/>
    </label>

    </br>

    <label>
        Пароль
        <input type="password" name="password" placeholder="Пароль"/>
    </label>

    </br>

    <button style="margin-top: 0.5vw; height: 2vw;">Зарегистрировать</button>

</form>

</body>

<jsp:include page="forAllPage.jsp"/>
</html>
