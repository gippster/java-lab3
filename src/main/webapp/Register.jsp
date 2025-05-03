<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form action="Register" method="POST">
        Логин: <input type="text" name="login"/>
        Пароль: <input type="password" name="password"/>
        Почта: <input type="text" name="email"/>
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>