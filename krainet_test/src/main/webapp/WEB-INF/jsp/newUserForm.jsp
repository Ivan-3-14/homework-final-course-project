<%@ page import="application.dto.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<%
    UserDTO currentUser = (UserDTO) request.getSession().getAttribute(CURRENT_USER);

    if (currentUser != null) {
%>
<body>
<table>
    <th style="width: 8vw; text-align: left">
        hello, <%=currentUser.getName()%>
    </th>
    <th>
        <form style="margin-top: 13px" action="exit" method="get">
            <button> exit </button>
        </form>
    </th>
</table>

<%}%>

<a style="text-align: right" href='${pageContext.request.contextPath}userPage'> main page </a>
</br>
</br>

<form method="post" modelAttribute="userForm" action="${pageContext.request.contextPath}saveUser" >
    <label>
        <input type="text" name="name" placeholder="user name"/>
    </label>
    </br>
    <label>
        <input type="text" name="surname" placeholder="user surname"/>
    </label>
    </br>
    <label>
        <input type="text" name="login" placeholder="user login"/>
    </label>
    </br>
    <label>
        <input type="text" name="password" placeholder="user password"/>
    </label>
    </br>

    </br>
    <button> save user </button>

</form>

</body>
</html>
