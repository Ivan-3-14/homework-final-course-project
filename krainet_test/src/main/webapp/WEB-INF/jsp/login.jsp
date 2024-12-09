<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>

<title>login page</title>

<body>
<div style="text-align: center; margin-top: 15vw">
    <form method="post" action="${pageContext.request.contextPath}login">

        <div>
            <input type="text" name="username" placeholder="login">
        </div>

        <div>
            <input type="password" name="password" placeholder="password">
        </div>
    </br>
        <button>enter</button>
    </form>
</div>
</body>
</html>