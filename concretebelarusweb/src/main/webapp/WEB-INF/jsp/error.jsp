<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Упс, ошибочка</title>

<jsp:include page="allPageHeader.jsp"/>

<% String errorMessage = (String) request.getAttribute("errorMessage");
String pathToReturn = (String) request.getAttribute("pathToReturn");

if(errorMessage == null) {
    errorMessage = DEFAULT_ERROR_MESSAGE;
}
if (pathToReturn == null) {
    pathToReturn = AUTHORIZATION_PAGE;
}
%>


<div style="text-align: center; margin-top: 8%">
<p style="font-size: 18px; color: crimson; width: 27%; margin-left: 37%"><%=errorMessage%></p>

<form action="<%=pathToReturn%>">
    <% if (request.getAttribute(PERSONAL_ERROR) == null) {%>
    <input type="hidden" name="<%=NAME%>" value="<%=request.getAttribute(NAME)%>">
    <input type="hidden" name="<%=SURNAME%>" value="<%=request.getAttribute(SURNAME)%>">
    <input type="hidden" name="<%=TEL_NUMBER%>" value="<%=request.getAttribute(TEL_NUMBER)%>">
    <input type="hidden" name="<%=DISTANCE%>" value="<%=request.getAttribute(DISTANCE)%>">
    <input type="hidden" name="<%=VOLUME%>" value="<%=request.getAttribute(VOLUME)%>">
    <input type="hidden" name="<%=OBJECT_NAME%>" value="<%=request.getAttribute(OBJECT_NAME)%>">
    <input type="hidden" name="<%=COMMENT%>" value="<%=request.getAttribute(COMMENT)%>">
    <%}%>
<button> Назад </button>
</form>

</div>

<jsp:include page="forAllPage.jsp"/>

</html>