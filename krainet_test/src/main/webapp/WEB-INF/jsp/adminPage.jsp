<%@ page import="application.dto.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<%
    UserDTO currentUser = (UserDTO) request.getAttribute(CURRENT_USER);
    if (currentUser == null) {
        currentUser = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
    }
    request.getSession().setAttribute(CURRENT_USER, currentUser);
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

</br>

<a href='${pageContext.request.contextPath}getAllProjects'> all projects </a>
</br>
</br>
<a href='${pageContext.request.contextPath}createNewUser'> create new user </a>
</br>
</br>
<a href='${pageContext.request.contextPath}addNewProject'> add new project </a>

<%}%>
</body>
</html>
