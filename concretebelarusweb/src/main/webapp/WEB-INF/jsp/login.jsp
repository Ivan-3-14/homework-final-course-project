<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<title>Вход в аккаунт</title>
<style>
    body { background: white}
    button {
        background: #14d554;
        color: #fff;
        border: none;
        margin-top: 10px;
        margin-bottom: 0px;
        margin-left: 21px;
        width: 202px;
        height: 35px;
        font-size: 15px;
    }
    .social-media{
        display: flex;
        margin-left: 40px;

    }
    .social-icon{
        height: 45px;
        width: 45px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #444;
        border: 1px solid #444;
        border-radius: 50px;
        margin: 5px;

    }
    a{
        text-decoration: none;
    }
    .social-icon:hover{
        color: rgb(90, 90, 255);
        border-color: rgb(90, 90, 255);

    }
</style>
<header>
    <a style="font-size: 14px; color: blue;  float: left; margin-left: 10px"
       href='${pageContext.request.contextPath}mainPage'> На главную страницу
    </a>
</header>
<body>
<div >
    <form method="post" action="${pageContext.request.contextPath}login"
          style=" position: absolute; 	height: 190px; width: 300px; border-radius: 8px; margin-left: 41%;
          margin-top: 19%;">

        <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input type="text" style="width: 200px; height: 30px;" name="username" placeholder="Email">
        </div>

        <div class="input-field">
            <i class="fas fa-lock"></i>
            <input type="password" style="width: 200px; height: 30px; margin-left: 2px;" name="password" placeholder="Пароль">
        </div>

        <button>Войти</button>

        </br>
        <a style="margin-left: 120px;" href="${pageContext.request.contextPath}forgotPassword">Забыли пароль?</a>

        <div class="social-media">
            <a href="https://web.telegram.org/" class="social-icon">
                <i class="fa-brands fa-telegram"></i>
            </a>
            <a href="https://twitter.com/home?lang=en" class="social-icon">
                <i class="fab fa-twitter"></i>
            </a>
            <a href="https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwj0hoHc4oqFAxWGh_0HHeyDAmcQPAgJ" class="social-icon">
                <i class="fab fa-google"></i>
            </a>
        </div>
    </form>
    <img src="${pageContext.request.contextPath}images/autorization.jpg" width=30% height="540" alt="вход в Морию" style=" margin-left: 25.5%">
</div>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>