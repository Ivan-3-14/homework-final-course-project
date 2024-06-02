<%@ page import="application.DTO.orderDTO.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ page import="static application.utils.Constant.CURRENT_PAGE" %>
<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<title>Мои заказы</title>
<style>
    td {
        border: 1px solid black;
        font-size: 16px;
        text-align: center;
    }
    table {
        margin: auto;
        border-collapse: collapse;
        width: 90%
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

    <% UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
            List<UserDTO> list = (List<UserDTO>) request.getAttribute(USER_LIST);
            int currentPage = (int) request.getAttribute(CURRENT_PAGE);
            int maxPage = (int) request.getAttribute(MAX_PAGE);
            String status = (String) request.getAttribute(STATUS);
            %>

    <a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
       href='${pageContext.request.contextPath}signUp'> Мой аккаунт
    </a>
</br>
    <form style="margin-top: 2vw"  action="filterUser" method="get">
        <button> Зарегистрированные
            <input type="hidden" name="currentPage" value="<%=currentPage%>" />
            <input type="hidden" name="status" value="user" />
        </button>
    </form>

    <form action="filterUser" method="get">
        <button> Не зарегистрированные
            <input type="hidden" name="currentPage" value="<%=currentPage%>" />
            <input type="hidden" name="status" value="superuser" />
        </button>
    </form>

    <form action="filterUser" method="get">
        <button> Сбросить фильтр
            <input type="hidden" name="currentPage" value="<%=currentPage%>" />
            <input type="hidden" name="status" value="null" />
        </button>
    </form>

</p>

<div style="float: left; margin-top: -1vw; margin-left: 11vw; width: 80%">

    <h3 style="text-align: center; margin-top: -6vw; margin-bottom: 2vw"> Все пользователи </h3>
    <table>
        <thead>
        <tr>
            <th> Имя  </th>
            <th> Фамилия </th>
            <th> Номер телефона</th>
            <th> Email </th>
            <th> Статус </th>

        </tr>
        </thead>
        <%
            if (list != null) {
                for (UserDTO temp : list) { %>
        <tbody>
        <tr>
            <td><%=temp.getName()%>
            </td>
            <td><%=temp.getSurname()%>
            </td>
            <td><%=temp.getTelephoneNumber()%>
            </td>
            <td><%= temp.getEmail() != null ? temp.getEmail() : "-"%>
            </td>
            <td><%=temp.getRole().getStringRole()%>
            </td>

            <td style="border: none">
                <form style="margin: 2px" action="${pageContext.request.contextPath}toUpdateUserFromM" method="get">
                    <button style="color: blue">
                        <input type='hidden' name="currentPage" value="<%=currentPage%>">
                        <input type='hidden' name="userId" value="<%=temp.getId()%>">
                        Изменить пользователя
                    </button>
                </form>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <%}%>

    <div style="display:flex; justify-content:center; align-items:center; height:20%;">

            <% if(maxPage != 0) {%>
        <% if (userDTO != null) {%>
        <td style="text-align: left; margin-right: 4vw">
            <form method ="get" action="${pageContext.request.contextPath}filterUser" >
                <button  style="margin: 6px; text-align: left">
                    <%="первая  "%>
                    <input type="hidden" name="currentPage" value="<%=NULL_PAGE%>" />
                    <input type="hidden" name="status" value="<%=status%>" />
                </button>
            </form>
        </td>

        <td style="text-align: right">
            <% if (currentPage != NULL_PAGE) {%>

            <form method ="get" action="${pageContext.request.contextPath}filterUser" >
                <button  style="margin: 6px">
                    <%=currentPage%>
                    <input type="hidden" name="currentPage" value="<%=currentPage - FIRST_PAGE%>" />
                    <input type="hidden" name="status" value="<%=status%>" />
                </button>
            </form>

            <%} %>
        </td>
        <td>
            <form method ="get" action="${pageContext.request.contextPath}filterUser">
                <button  style="margin: 6px; color: cornflowerblue">
                    <%=currentPage + FIRST_PAGE%>
                    <input type="hidden" name="currentPage" value="<%=currentPage%>" />
                    <input type="hidden" name="status" value="<%=status%>" />
                </button>
            </form>
        </td>

        <td>
            <%
                if ((currentPage != maxPage - 1)) {
            %>

            <form method="get" action="${pageContext.request.contextPath}filterUser">
                <% int nextPage = currentPage + SECOND_PAGE; %>
                <button style="margin: 6px">
                    <input type="hidden" name="currentPage" value="<%=nextPage - FIRST_PAGE%>"/>
                    <input type="hidden" name="status" value="<%=status%>" />
                    <%=nextPage%></button>
            </form>
        </td>

        <td>
            <form method="get" action="${pageContext.request.contextPath}filterUser">
                <button style="margin: 6px; text-align: right">
                    <input type="hidden" name="currentPage" value="<%=maxPage - FIRST_PAGE%>"/>
                    <input type="hidden" name="status" value="<%=status%>" />
                    <%="последняя"%></button>
            </form>

            <%}%>
        </td>
        <%}
            }%>
    </div>
</div>

</body>
<jsp:include page="forAllPage.jsp"/>
</html>