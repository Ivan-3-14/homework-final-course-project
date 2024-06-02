<%@ page import="application.DTO.usersDTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="application.DTO.concreteDTO.ConcretePriceDTO" %>
<%@ page import="application.DTO.autoDTO.AutoPriceDTO" %>
<%@ page import="application.DTO.autoDTO.AutoCapacityDTO" %>
<%@ page import="static application.utils.Constant.CURRENT_USER" %>
<%@ page import="static application.utils.Constant.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    body { background: #F4F1F5}
    table{
        border-collapse:collapse;
        border: 1px solid rgb(210, 208, 208);
        width:45%;
        text-align:center;
    }
    th, td {
        padding-bottom: 15px;
        padding-top: 15px;
        text-align:center;
        border: 1px solid rgb(210, 208, 208);
    }
    thead {
        background:  #bbbaba;
    }
    tbody tr:nth-child(even) {
        background: #dfdada;
    }
</style>
<title>Главная страница</title>
<header style="margin-top: -8px;">

    <h3 style= "text-align:right; margin-top: -8px; margin-bottom: -8px;"> &#9742; +375-29-650-70-20 </h3>
    <h1 style="margin-bottom:-8px; display:inline-block">
        <img style="vertical-align: middle; margin-top: -8px;"
             src="${pageContext.request.contextPath}images/fon-1.jpg" alt="П"
        > ОКУПАЙТЕ БЕТОН B CONCRETE BELARUS
    </h1>
    <%
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CURRENT_USER);
        if (userDTO != null) { %>
    <a style="font-size: 17px; color: mediumblue;  float: right; vertical-align: bottom; margin-right: 8px;margin-top: 4%"
            href='${pageContext.request.contextPath}signUp'
    > Добро пожаловать,<%= userDTO.getName()%> </a>
    <%
    } else { %>
    <a style="font-size: 16px; color: mediumblue;  float: right; vertical-align: bottom; margin-right: 8px;margin-top: 4%"
       href='${pageContext.request.contextPath}authorizationPage'> Войти/Зарегистрироваться

    <% }%>
    <h1></h1>
</header>
<p></p>
<header style="background: #565874; height: 5vw; margin-top: 1vw">
    </br>
    </br>
    <a style="font-size: 18px;color: white; float: right; margin-right: 1vw" href="${pageContext.request.contextPath}newOrderForm">Заказать бетон</a>
    </br>
    <h9>   </h9>
    </br>
</header>
<body>
<h2>Бетон</h2>

Бетон (от фр. beton) - это  искусственный строительный материал, получаемый в результате затвердевания рационально подобранной и уплотненной смеси вяжущего вещества (цемент или др.), заполнителей с водой. В ряде случаев может содержать специальные добавки.
<p>
    <img src="${pageContext.request.contextPath}images/lotok.jpg" width="300" height="301" alt="лоток" style="float: left; margin-right: 15px;">
    В наши дни бетон является наиболее широко используемым строительным материалом.
</p>
<p>Это происходит потому бетон это наиболее доступный строительный материал в мире.
    Из всех известных строительных материалов, будучи доступным, бетон также очень прочен, долговечен,
    устойчив к воздействию воды, огня, а также легко может быть уложен в бесконечном разнообразии форм и размеров
    (что, к слову, является ключевым преимуществом для торкретирования).
    Из основных преимуществ бетона выделяют низкую себестоимость конструкций на его основе,
    возможность применения в монолитных и сборных конструкциях произвольного типа,
    а также большое разнообразие характеристик и свойств, которые легко изменяются в зависимости от состава бетонной смеси.
</p>
<p>Вяжущее вещество и вода — активные составляющие бетона, которые в смеси обволакивают тонким слоем зерна заполнителя.
    Со временем вяжущее вещество затвердевает и связывает их, превращая бетонную смесь в прочный монолитный камень — бетон.
    Заполнители (песок, щебень или гравий) занимают до 80 — 85 % объема бетона и образуют его жесткий скелет препятствующий усадке.
    Применяя заполнители с различными свойствами, можно получать бетоны с разнообразными физико-механическими показателями,
    например, легкие, жароупорные и пр.
</p>
<p>Успех бетонного строительства напрямую зависит от того какие свойства заложил проектировщик на этапе проектирования сооружения,
    в основном это набор прочности и другие свойства.Прочность бетона, кроме прочего, напрямую зависит от рабочего на стройплощадке.
    Поэтому для того чтобы работать с бетоном, каждому рабочему необходимо понимать факторы, влияющие на свойства бетона.
</p>
<img src="${pageContext.request.contextPath}images/vidy-beton.jpg" width="400" height="301" alt="бетон1" style="float: right;; margin-right: 15px;">
<h2>Как выбрать и купить подходящую смесь</h2>
Первостепенными критериями при выборе бетонной смеси являются:
<ul>
    <li>Марка — показывает сколько нагрузки на сжатие выдерживает материал; проверяется в лаборатории и обозначается буквой «М», а цифра рядом отражает предел прочности.</li>
    <br>
    <li>Вид крупного заполнителя (гравий, щебень) зависит от особенностей дома или иной постройки, местности, где будет строиться объект, а также марки бетона.</li>
    <br>
    <li>Подвижность (пластичность) — способность материала растекаться, которая не влияет на конечную прочность конструкции, а важна для транспортировки смеси и удобства укладки.</li>
</ul>

Ответ на вопрос, сколько стоит готовый бетон с доставкой зависит от следующих факторов:
<ul>
    <li>марка;</li>
    <br>
    <li> качество крупного заполнителя;</li>
    <br>
    <li> расстояние от МКАД до места доставки;</li>
    <br>
    <li> необходимый объем;</li>
    <br>
    <li> требуемые машины;</li>
    <br>
</ul>
    <h4 style="float: left; padding-left: 300px;"> Стоимость бетона </h4>
    <h4 style="float: left; padding-left: 600px;"> Стоимость доставки </h4>
    <div style="clear: left"></div>

<%  List<ConcretePriceDTO> concretePrice = (List<ConcretePriceDTO>) request.getAttribute(CONCRETE_PRICE_LIST);
    List<AutoPriceDTO> autoPrice = (List<AutoPriceDTO>) request.getAttribute(AUTO_PRICE_LIST);
    List<AutoCapacityDTO> autoCapacity = (List<AutoCapacityDTO>) request.getAttribute(AUTO_CAPACITY_LIST);
%>
<table style="float: left; margin-right: 50px; margin-left: 50px;">
    <thead >
    <tr>
        <th>Марка (класс)</th>
        <th>Гравий</th>
        <th>Щебень</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>М150 (С8/10)</td>
        <td><%=concretePrice.get(0).getPrice()%> руб </td>
        <td><%=concretePrice.get(4).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М200 (С12/15)</td>
        <td><%=concretePrice.get(1).getPrice()%> руб</td>
        <td><%=concretePrice.get(5).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М250 (С16/20)</td>
        <td><%=concretePrice.get(2).getPrice()%> руб</td>
        <td><%=concretePrice.get(6).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М300 (С18/22,5)</td>
        <td><%=concretePrice.get(3).getPrice()%> руб</td>
        <td><%=concretePrice.get(7).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М350 (С20/25)</td>
        <td>-</td>
        <td><%=concretePrice.get(8).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М400 (С25/30)</td>
        <td>-</td>
        <td><%=concretePrice.get(9).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М450 (С27/32)</td>
        <td>-</td>
        <td><%=concretePrice.get(10).getPrice()%> руб</td>
    </tr>
    <tr>
        <td>М500 (С30/37)</td>
        <td>-</td>
        <td><%=concretePrice.get(11).getPrice()%> руб</td>
    </tr>
    </tbody>
</table>


<table style="float: left;">
    <thead>
    <tr>
        <th>Куда</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <% for (AutoPriceDTO c : autoPrice) { %>
        <td>По Минску (в пределах МКАД)</td>
        <td><%=c.getPrice()%> руб/миксер до <%=c.getCapacity().getAutoCapacity()%>м3 </td>
    </tr>
    <%}%>
    <tr>
        <td>за МКАД (до 50км)</td>
        <td>+<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientBfr50()%> руб/км</td>
    </tr>
    <tr>
        <td>за МКАД (св. 50км)</td>
        <td>+<%=autoCapacity.get(0).getDeliveryCoefficient().getDeliveryCoefficientAft50()%> руб/км</td>
    </tr>
    </tbody>
</table>
<div style="clear: left"></div>
</body>

<div style="position: relative; background: #5ff13f; bottom: 0; left: 0; width: 100%; min-height: 20vh; height: 20%;
margin: -8px">
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
