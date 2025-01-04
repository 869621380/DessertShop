<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/26
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="css/LoginCss.css" type="text/css" media="screen">
  <link rel="preload" href="background_img/login1.png" as="image">
  <link rel="preload" href="background_img/login2.jpg" as="image">
  <link rel="preload" href="background_img/login3.png" as="image">
  <link rel="preload" href="background_img/login4.png" as="image">
</head>
<body>
<form action="regist" method="post">
  <div class="box" id="Catalog" style="height: 750px;">
    <h2>注册</h2>
    <div class="input-box">
      <label>账号</label>
      <input type="text" name="username">
    </div>
    <div class="input-box">
      <label>密码</label>
      <input type="password" name="password">
    </div>
    <div class="input-box">
      <label>电话</label>
      <input type="text" name="phone">
    </div>
    <div class="input-box">
      <label>Email</label>
      <input type="text" name="email">
    </div>
    <div class="input-box">
      <label>收货地址</label>
      <input type="text" name="adress">
    </div>
    <div class="input-box">
      <label>验证码</label>
      <input type="text" name="code" style="width: 200px;">
    </div>
    <div>
      <img src="code">
    </div>

    <div class="btn-box">
      <div>
        <c:if test="${requestScope.RegistMsg != null}">
          <p><font color="#f0f8ff">${requestScope.RegistMsg}</font></p>
        </c:if>
      </div>
      <div>
        <button type="submit">注册并登录</button>
      </div>
    </div>
  </div>
</form>
</body>
</html>

