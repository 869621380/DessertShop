<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
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

<%--    <script>--%>
<%--        function goToRegister() {--%>
<%--            // 跳转到注册Servlet--%>
<%--            window.location.href = "login";--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<form action="find" method="post">
    <div class="box" id="Catalog">
        <h2>修改信息</h2>
        <div class="input-box">
            <label>收货地址</label>
            <input type="text" name="adress">
        </div>
        <div class="input-box">
            <label>电话</label>
            <input type="password" name="phone">
        </div>
        <div class="input-box">
            <label>Email</label>
            <input type="text" name="email">
        </div>
        <div class="btn-box">
            <c:if test="${requestScope.FindMsg != null}">
                <p><font color="#f0f8ff">${requestScope.findMsg}</font></p>
            </c:if>
            <div>
                <button type="submit">确认修改</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>