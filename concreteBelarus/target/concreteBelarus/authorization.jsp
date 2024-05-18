<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css'">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,700;">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<style>
    h1{

        color: rgb(13, 13, 13);
        margin-top: 25px;
        width: 200px;
        margin-left: 620px;
    }

    .social-text{
        margin-left: 5px;
        font-size: 16px;
    }
    .social-media{
        display: flex;

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
    button {
        background: #14d554;
        color: #fff;
        border: none;
        margin-top: 10px;
        margin-bottom: 0px;
        width: 200px;
        height: 35px;
        font-size: 15px;
    }
</style>
<head>
    <title>Авторизация</title>
</head>

<header style="background: #000000">
    </br>
    </br>
    <a style="font-size: 14px; color: white;  float: left; margin-left: 10px; vertical-align: bottom;"
       href='${pageContext.request.contextPath}/index.jsp'> На главную страницу
    </a>
    </br>
    </br>
</header>
<body>
<h1>Регистрация</h1>
<div >
    <form style="margin-left: 40%; margin-top: 2%; width: 300px; display: block;"

          method="post" action="${pageContext.request.contextPath}/authorization" >
        <label>
            <input type="text" style="width: 200px; height: 35px;" name="name" placeholder="Имя"/>
        </label>
        </br>
        <label>
            <input type="text" style="width: 200px; height: 35px;" name="surname" placeholder="Фамилия"/>
        </label>
        </br>
        <label>
            <input type="text" style="width: 200px; height: 35px;" name="telephoneNumber" placeholder="Номер Телефона"/>
        </label>
        </br>
        <label>
            <input type="text" style="width: 200px; height: 35px;" name="email" placeholder="Email"/>
        </label>
        <% if (request.getAttribute("wrong") != null){%>
        <p style="color: #ec6767;">Такой email уже зарегистрирован</p>
        <% } %>
        </br>
        <label>
            <input type="password" style="width: 200px; height: 35px;" name="password" placeholder="Пароль"/>
        </label>
        </br>
        <button >Зарегистрироваться</button>
        <p style="margin-top: -2px;">забыли свой пароль? <a href="${pageContext.request.contextPath}/forgotPass">нажмите сюда</a></p>

        <p>Уже зарегистрирован? <a class="social-text" href="${pageContext.request.contextPath}/signUp">Войти</a></p>

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
</div>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>