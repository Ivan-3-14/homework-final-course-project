<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<jsp:include page="allPageHeader.jsp"/>
<body>
        <form action="changePass" method="post">
            <h2 style="margin-top: 15px; margin-bottom: 10px; width: 50vw; position: inherit;
             margin-left: 1%;"> Введите новый пароль </h2>
            <div style="width: 30vw;">
                <i class="fa-solid fa-key"></i>
                <input type="text" name="new" placeholder="новый пароль">
            </div>
            <div style="width: 30vw;">
                <i class="fa-solid fa-key"></i>
                <input type="text" name="new2" placeholder="повторите пароль">
            </div>
            <input type="submit" value="Изменить" class="btn">
        </form>
    <% if (request.getAttribute("wrong") != null) { %>
    <p style="color: #ec6767;"> Упс! Что-то пошло не так </p>
    <% } %>
</body>
<jsp:include page="forAllPage.jsp"/>