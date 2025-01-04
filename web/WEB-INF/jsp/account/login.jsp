<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <link rel="stylesheet" href="css/LoginCss.css" type="text/css" media="screen">
    <link rel="preload" href="background_img/login1.png" as="image">
    <link rel="preload" href="background_img/login2.jpg" as="image">
    <link rel="preload" href="background_img/login3.png" as="image">
    <link rel="preload" href="background_img/login4.png" as="image">

    <script>
        function goToRegister() {
            // 跳转到注册Servlet
            window.location.href = "regist";
        }
    </script>
</head>
<body>
<form action="login" method="post">
<div class="box" id="Catalog">
        <h2>欢迎</h2>
        <div class="input-box">
            <label>账号</label>
            <input type="text" name="username">
        </div>
        <div class="input-box">
            <label>密码</label>
            <input type="password" name="password">
        </div>
        <div class="btn-box">
            <a href="">忘记密码?</a>
            <c:if test="${requestScope.loginMsg != null}">
                <p><font color="#f0f8ff">${requestScope.loginMsg}</font></p>
            </c:if>
            <div>
                <button type="submit" value="Login">登录</button>
                <button type="button" onclick="goToRegister()">注册</button>
            </div>
        </div>
</div>
</form>
</body>
</html>
