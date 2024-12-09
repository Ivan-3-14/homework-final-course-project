<%@ page import="application.dto.UserDTO" %>

<%@ page import="static application.utils.Constant.*" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>

<%
    Long currentProjectId = (long) request.getAttribute(CURRENT_PROJECT_ID);
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
        <th> user name </th>
        <th> user surname </th>
        <th> user login </th>
    </tr>
    </thead>
<%
    List<UserDTO> userDTOList = (List<UserDTO>) request.getAttribute(USER_FOR_PROJECT_LIST);
    if (userDTOList != null) {
        for (UserDTO u : userDTOList) {
%>
    <tbody>
    <tr>
        <td> <%=u.getName()%>
        </td>
        <td><%=u.getSurname()%>
        </td>
        <td> <%=u.getLogin()%>
        </td>
        <td>
            <form style="margin: 2px" action="deleteUserFromProject" method="post">
                <input type="hidden" name="projectId" value="<%=currentProjectId%>">
                <input type="hidden" name="userId" value="<%=u.getId()%>">
                <button> delete user </button>
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
