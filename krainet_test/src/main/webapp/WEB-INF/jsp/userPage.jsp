<%@ page import="application.dto.UserDTO" %>
<%@ page import="application.dto.ProjectDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>

<%
    UserDTO currentUser = (UserDTO) request.getAttribute(CURRENT_USER);
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

<p> My projects: </p>

<%
    if (currentUser.getProjectSet() != null) {
        for (ProjectDTO project : currentUser.getProjectSet()) {
%>
<a href='${pageContext.request.contextPath}readProject?projectId=<%=project.getId()%>'><%=project.getName()%>
</a>
</br>
<%
            }
        }
    }
%>
</body>
</html>
