<%@ page import="application.dto.UserDTO" %>
<%@ page import="application.dto.ProjectDTO" %>
<%@ page import="static application.utils.Constant.*" %>
<%@ page import="java.util.List" %>
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

<a href='${pageContext.request.contextPath}userPage'> main page </a>
</br>
</br>
<table>

    <thead>
    <tr>
        <th> Current project </th>
        <th> Project description </th>
        <th> Level of readiness of project </th>
    </tr>
    </thead>
<%
    List<ProjectDTO> projectDTOList = (List<ProjectDTO>) request.getAttribute(PROJECT_LIST);
    if (projectDTOList != null) {
        for (ProjectDTO p : projectDTOList) {
%>
    <tbody>
    <tr>
        <td> <%=p.getName()%>
        </td>
        <td><%=p.getDescription()%>
        </td>
        <td> <%=p.getReadinessLevel() * 100%>
        </td>
        <td>
            <form style="margin: 2px" action="addUsersToProject" method="post">
                <input type="hidden" name="projectId" value="<%=p.getId()%>">
                <button style="margin-top: 3px;"> add users </button>
            </form>
        </td>
        <td>
            <form style="margin: 2px" action="deleteUsersFromProject" method="post">
                <input type="hidden" name="projectId" value="<%=p.getId()%>">
                <button style="margin-top: 3px;"> delete users </button>
            </form>
        </td>
    </tr>

<% }
}
%>
    </tbody>
</table>
<% }
%>


</body>
</html>
