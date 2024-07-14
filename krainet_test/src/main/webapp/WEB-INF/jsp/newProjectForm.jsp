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

<a  href='${pageContext.request.contextPath}userPage'> main page </a>
</br>
<%}%>

</br>

<form method="post" modelAttribute="projectForm" action="${pageContext.request.contextPath}saveProject">
    <label>
        <input type="text" style="width: 200px; height: 35px;" name="name" placeholder="project name"/>
    </label>
    </br>
    <label>
        <input type="text" style="width: 200px; height: 35px;" name="description" placeholder="project description"/>
    </label>
    </br>

    </br>
    <button> add project </button>

</form>

</body>
</html>
