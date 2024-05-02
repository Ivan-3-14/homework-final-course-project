<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<jsp:include page="allPageHeader.jsp"/>
<body>
        <form action="changePass" method="get">
            <h2 style="margin-top: 15px;
            margin-bottom: 10px; width: 50vw; position: inherit;
            margin-left: 1%;"
            > Для смены пароля подтвердите, пожалуйста, свой email</h2>
            <div class="input-field" style="width: 30vw;">
                <i class="fas fa-envelope"></i>
                <input type="text" name="email" placeholder="email">
            </div>
            <input type="submit" value="Проверить" class="btn">
        </form>
</body>
<jsp:include page="forAllPage.jsp"/>