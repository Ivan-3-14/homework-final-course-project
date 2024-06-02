<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ page import="application.entity.enums.roles.Roles" %>
<%@ page import="java.util.List" %>
<%@ page import="application.DTO.concreteDTO.ConcretePriceDTO" %>
<%@ page import="application.DTO.autoDTO.AutoPriceDTO" %>
<%@ page import="application.DTO.autoDTO.AutoCapacityDTO" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<title>Изменить цены</title>

<style>
    body {
        background: #F4F1F5
    }

    .chptable {
        border-collapse: collapse;
        border: 1px solid rgb(210, 208, 208);
        width: 30%;
        text-align: center;
        margin-top: 1vw;
    }

    th, td {
        padding-bottom: 15px;
        padding-top: 15px;
        text-align: center;
        border: 1px solid rgb(210, 208, 208);
    }

    .mythead {
        background: #bbbaba;
    }

    tbody tr:nth-child(even) {
        background: #dfdada;
    }
</style>

<jsp:include page="allPageHeader.jsp"/>


<body>
<%
    UserDTO userDTO = (UserDTO) request.getAttribute(CURRENT_USER);
    if (userDTO != null) {
        request.getSession().setAttribute(CURRENT_USER, userDTO);
    } else {
        userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
    }
%>

<%if (userDTO != null) {%>
<a style="font-size: 20px; color: mediumblue;  float: left; vertical-align: bottom;"
   href='${pageContext.request.contextPath}signUp'> Мой аккаунт
</a>
        <% } %>

    <div style="clear: right"></div>
        <%  List<ConcretePriceDTO> concretePrice = (List<ConcretePriceDTO>) request.getAttribute("concretePriceList");
    List<AutoPriceDTO> autoPrice = (List<AutoPriceDTO>) request.getAttribute("autoPriceList");
     List<AutoCapacityDTO> autoCapacity = (List<AutoCapacityDTO>) request.getAttribute("autoCapacityList");
%>

    <table class="chptable" style="float: left; margin-right: 50px; margin-left: 10%;">
        <thead class="mythead">
        <tr>
            <th>Марка (класс)</th>
            <th>Заполнитель</th>
            <th>Цена</th>
            <th></th>
        </tr>
        </thead>
        <tbody>


        <% for (ConcretePriceDTO c : concretePrice) {%>
        <tr>
            <td><%=c.getConcreteGrade().getGradesConcrete()%> <%=c.getConcreteGrade().getConcreteClass().getConcreteClassName()%>
            </td>
            <td>Гравий</td>
            <td>
                <form action="${pageContext.request.contextPath}changeConcretePrice" method="post">
                    <label>
                        <input style="text-align:center; vertical-align: center; width: 100%" type="text"
                               name="concretePrice"
                               value="<%=c.getPrice()%>"
                               placeholder="<%=c.getPrice()%> руб ">
                    </label>
            <td>
                <button style="color: blue; margin-top: -1vw">
                    <input type='hidden' name="tempConcretePriceID" value="<%=c.getId()%>">
                    Изменить
                </button>
            </td>
            </form>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>

    <table class="chptable" style="float: right; margin-right: 10vw">
        <thead class="mythead">
        <tr>
            <th>Куда</th>
            <th>Цена</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <% for (AutoPriceDTO a : autoPrice) {%>
        <tr>
            <td>По Минску (в пределах МКАД)
            <td>
                <form action="${pageContext.request.contextPath}changeAutoPrice" method="post">
                    <label>
                        <input style="text-align:center; vertical-align: center; width: 100%" type="text"
                               name="autoPrice"
                               value="<%=a.getPrice()%>"
                               placeholder="<%=a.getPrice()%> руб/миксер до 8м3">
                    </label>
            <td style="border: none; background: none" >
                <button style="color: blue; margin-top: -1vw">
                    <input type='hidden' name="tempAutoPriceID" value="<%=a.getId()%>">
                    Изменить
                </button>
            </td>
            </form>

        </tr>
        <%}%>

        <tr>
            <td>за МКАД (до 50км)</td>
            <td>
                <form action="${pageContext.request.contextPath}changeDeliveryCoefficient" method="post">
                    <label>
                        <input style="text-align:center; vertical-align: center; width: 100%" type="text"
                               name="delCoefficient"
                               value="<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientBfr50()%>"
                               placeholder="<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientBfr50()%>%> руб/миксер до 8м3">
                    </label>
            <td>
                <button style="color: blue;">
                    <input type='hidden' name="tempDelAfter50ID" value="<%=-1%>">
                    <input type='hidden' name="tempDelBefore50ID" value="<%=autoCapacity.get(0).getId()%>">
                    Изменить
                </button>
            </td>
            </form>
        </tr>
        <tr>
            <td>за МКАД (св. 50км)</td>
            <td> <form action="${pageContext.request.contextPath}changeDeliveryCoefficient" method="post">
                <label>
                    <input style="text-align:center; vertical-align: center; width: 100%" type="text"
                           name="delCoefficient"
                           value="<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientAft50()%>"
                           placeholder="<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientAft50()%>%> руб/миксер до 8м3">
                </label>
            <td>
                <button style="color: blue;">
                    <input type='hidden' name="tempDelAfter50ID" value="<%=autoCapacity.get(0).getId()%>">
                    <input type='hidden' name="tempDelBefore50ID" value="<%=-1%>">
                    Изменить
                </button>
            </td>
            </form>
        </tr>
        </tbody>
    </table>
    <div style="clear: left"></div>
    <div style="clear: right"></div>
</body>
<div style="position: relative; background: #5ff13f; bottom: 0; left: 0; width: 100%; ">
    <footer>
        <p style="float: left; margin-left: 30px; margin-bottom: -8px; "> Course project for IT-academy.by
        <p style="float: right; margin-bottom: -8px;margin-right: 8px;"> &#128379; +375-29-169-23-21</p>
        </p>
        <div style="clear: left; "></div>
        <p style="text-align:right; margin-right: 8px;">&#128386; 9129946@mail.ru</p>
        <p style="text-align:center; padding-bottom: 10px;">Авторские права "ПИ в квадрате" &copy; 2024</p>
        <p> </p>
    </footer>
</div>
</html>
