<%@ page import="application.DTO.objectDTO.BuildingObjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static application.utils.Constant.*" %>
<%@ page import="application.DTO.orderDTO.OrderDTO" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="application.DTO.usersDTO.UserDTO" %>
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
<% UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER); %>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}signUp'> Мой аккаунт
</a>

<div>
    <table style="border: none; margin-left: 40%; width: 10%; margin-bottom: 1px; margin-top: -3vw">
<form action="${pageContext.request.contextPath}searchObject" method="get"
      style="margin-left: 40%; margin-top: 1vw">

        <label>
            <input style="width: 12vw" type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <th style="border: none; text-align: right">
                <input type="text" name="search" placeholder="найти объект">
            </th>
        </label>
    <th style="border: none; text-align: left">
    <button >Поиск</button>
    </th>

</form>
        <form action="${pageContext.request.contextPath}myObjects" method="get">
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <th style="border: none; text-align: left">
                <button>Сброс</button>
            </th>
        </form>
    </table>
</div>
<h3 style="text-align: center; margin: 1px; margin-top: 1px">Мои объекты </h3>
<table>

    <thead>
    <tr>
        <th> Наименование объекта</th>
        <th> Расстояние до объекта</th>
        <th> Общее количество бетона </th>
    </tr>
    </thead>
    <% List<BuildingObjectDTO> list = (List<BuildingObjectDTO>) request.getAttribute(LIST_OF_OBJECTS);
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
        <td><%=temp.getOrderSet().stream()
                .map(OrderDTO::getVolumeOfConcrete)
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum()%>
        </td>
    </tr>
    <%}
    }%>
    </tbody>
</table>
<div
        style="display:flex; justify-content:center; align-items:center; height:20%;">
    <% if(maxPage != 0) {%>
    <% if (userDTO != null) {%>
<td>
    <form method ="get" action="${pageContext.request.contextPath}searchObject" >
        <button  style="margin: 6px; text-align: left">
            <%="первая  "%>
            <input type="hidden" name="currentPage" value="<%=NULL_PAGE%>" />
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
        </button>
    </form>
</td>

    <td style="text-align: right">
<% if (currentPage != NULL_PAGE) {
    %>
    <form method ="get" action="${pageContext.request.contextPath}searchObject" >
        <button  style="margin: 6px">
            <%=currentPage%>
            <input type="hidden" name="currentPage" value="<%=currentPage - FIRST_PAGE%>" />
            <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
            <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
        </button>
    </form>
    <%} %>
    </td>

    <td>
        <form method ="get" action="${pageContext.request.contextPath}searchObject">
            <button  style="margin: 6px; color: cornflowerblue">
                <%=currentPage + FIRST_PAGE%>
                <input type="hidden" name="currentPage" value="<%=currentPage%>" />
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
            </button>
        </form>
    </td>

    <td>
        <%
            if ((currentPage != maxPage - FIRST_PAGE)) {
        %>

        <form method="get" action="${pageContext.request.contextPath}searchObject">
            <% int nextPage = currentPage + SECOND_PAGE; %>
            <button style="margin: 6px">
                <input type="hidden" name="currentPage" value="<%=nextPage - FIRST_PAGE%>"/>
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
                <%=nextPage%></button>
        </form>
    </td>

    <td>
        <form method="get" action="${pageContext.request.contextPath}searchObject">
            <button style="margin: 6px; text-align: right">
                <input type="hidden" name="currentPage" value="<%=maxPage - FIRST_PAGE%>"/>
                <input type="hidden" name="currentUserId" value="<%=userDTO.getId()%>">
                <input type="hidden" name="search" value="<%=request.getAttribute(SEARCH)%>">
                <%="последняя"%></button>
        </form>

        <%}%>
    </td>
    <%}
    }%>
</div>
</body>
<jsp:include page="forAllPage.jsp"/>
</html>
