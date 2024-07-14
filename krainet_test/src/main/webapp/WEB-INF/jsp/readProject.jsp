<%@ page import="application.dto.UserDTO" %>
<%@ page import="application.dto.ProjectDTO" %>
<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

<a style="text-align: right" href='${pageContext.request.contextPath}userPage'> main page </a>
</br>

<%
    ProjectDTO projectDTO = (ProjectDTO) request.getAttribute(CURRENT_PROJECT);
    request.getSession().setAttribute(CURRENT_PROJECT, projectDTO);
    if (projectDTO != null) { %>

<p>Current project: <%=projectDTO.getName()%> </p>
<p>Project description: <%=projectDTO.getDescription()%> </p>
<p>Level of readiness of project: <%=projectDTO.getReadinessLevel() * 100%>%</p>

<form action="createNewRecord" method="post">
    <input type="hidden" name="currentUserId" value="<%=currentUser.getId()%>">
    <input type="hidden" name="currentProjectId" value="<%=projectDTO.getId()%>">
    <button> start time tracker</button>
</form>

<%
        }
    }
%>
</body>
</html>
