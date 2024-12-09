<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Ups, error</title>

<% String errorMessage = (String) request.getAttribute(ERROR_MESSAGE);
String pathToReturn = (String) request.getAttribute(PATH_TO_RETURN);
if (pathToReturn == null || pathToReturn.isEmpty()) {
    pathToReturn = EXIT;
    request.getSession().setAttribute(CURRENT_USER, null);
}

if (errorMessage == null) {
    errorMessage = "Ups, error";
}
%>


<p><%=errorMessage%></p>

<form action="<%=pathToReturn%>">

<button> back </button>
</form>

</div>


</html>