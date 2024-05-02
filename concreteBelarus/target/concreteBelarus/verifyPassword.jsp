<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<jsp:include page="allPageHeader.jsp"/>
<body>
        <form method="get" action="changePass">
            <p style="margin-top: 15px; position: inherit; width: 50vw; margin-left: 1%; margin-bottom: 5px;">
                Введите Ваш старый пароль:
            </p>
            <div style="width: 30vw;">
                <input type="text" name="password" placeholder="старый пароль">
                <input type='hidden' name="email" value="<%=request.getParameter("email")%>">
            </div>
            <input style="margin-top: 1vw" type="submit" value="Проверить" class="btn">

        </form>
</body>
<jsp:include page="forAllPage.jsp"/>