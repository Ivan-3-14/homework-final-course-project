<%@ page import="java.util.List" %>
<%@ page import="application.servicelevel.DTO.objectDTO.BuildingObjectDTO" %>
<%@ page import="static application.utils.constant.ConstantsContainer.ROW_IN_PAGE_ORDERS" %>
<%@ page import="static application.utils.constant.ConstantsContainer.CURRENT_PAGE" %>
<%@ page import="static application.utils.constant.ConstantsContainer.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<title>Мои объекты</title>
<style>
    table {
        margin: auto;
        border-collapse: collapse;
        width: 700px
    }
    td {
        border: 1px solid black;
        font-size: 18px;
        text-align: center;
    }
    th {
        border: 1px solid black;
        padding: 1vw;
        text-align: center;
        margin: 5px
    }
</style>
<jsp:include page="allPageHeader.jsp"/>
<body>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}/mainPageForUser.jsp'> Мой аккаунт
</a>
<h3 style="text-align: center">Мои объекты </h3>
<table>

    <thead>
    <tr>
        <th> Наименование объекта</th>
        <th> Расстояние до объекта</th>
    </tr>
    </thead>
    <% List<BuildingObjectDTO> list = (List<BuildingObjectDTO>) request.getAttribute("listOfObjects");
        int currentPage = (int) request.getAttribute(CURRENT_PAGE);
        int maxPage = (int) request.getAttribute(MAX_PAGE);
        if (list != null) {
        for (BuildingObjectDTO temp : list) { %>
    <tbody>
    <tr>
        <td><%=temp.getNameOfObject()%>
        </td>
        <td><%=temp.getDistanceToObject()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<div
        style="display:flex; justify-content:center; align-items:center; height:20%;"
>
<td>
<%
        if (currentPage != 1) {
    %>

        <form method ="post" action="${pageContext.request.contextPath}/myObjects" >
            <% int prevPage = currentPage - 1;
            %>
            <button  style="margin: 6px">
                        <%=prevPage%>
                <input type="hidden" name="currentPage" value="<%=prevPage%>" />
                       </button>
                   </form>

    <%} %>
</td>
<td>
    <button  style="margin: 6px; color: cornflowerblue">
    <%=currentPage%>
    </button>
</td>
<td>
    <%
    if (currentPage != maxPage) {
    %>

        <form method="post" action="${pageContext.request.contextPath}/myObjects">
            <% int nextPage = currentPage + FIRST_PAGE; %>
            <button style="margin: 6px">
                <input type="hidden" name="currentPage" value="<%=nextPage%>"/>
                <%=nextPage%></button>
        </form>

    <%}%>
</td>
    <%}%>
</div>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>
