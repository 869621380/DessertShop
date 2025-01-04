<%--
  Created by IntelliJ IDEA.
  User: lizijian
  Date: 2024/10/28
  Time: 下午9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lizijian
  Date: 2024/10/27
  Time: 下午7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="styleSheet" type="text/css" href="css/left.css">
    <link rel="stylesheet" type="text/css" href="css/checkout.css">
    <link rel="stylesheet" href="css/pilot.css">
</head>

<body>
<nav class="shell close">
    <header>
        <div class="image-text">
                <span class="image">
                    <img src="background_img/head.jpg" alt="头像">
                </span>
            <div class="text logo-text">
                <span class="name">${sessionScope.user_name != null ? sessionScope.user_name : "匿名用户"}</span>
                <span class="software">18423485230</span>
            </div>
        </div>
        <i class="iconfont icon-xiangyoujiantou toggle"></i>
    </header>
    <div class="menu-bar">
        <div class="menu">
            <li class="search-box">
                <i class="iconfont icon-sousuo icon"></i>
                <input type="text" placeholder="搜索...">
            </li>
            <ul class="menu-links">
                <li class="nav-link">
                    <a href="main">
                        <i class="iconfont icon-shouye icon"></i>
                        <span class="text nac-text">主页</span>
                    </a>
                </li>

                <li class="nav-link">
                    <a href="#">
                        <i class="iconfont icon-shoucangxiao icon"></i>
                        <span class="text nac-text">我的收藏</span>
                    </a>
                </li>

                <li class="nav-link">
                    <a href="searchOrder">
                        <i class="iconfont icon-lishi icon"></i>
                        <span class="text nac-text">历史记录</span>
                    </a>
                </li>

                <li class="nav-link">
                    <a href="searchOrder">
                        <i class="iconfont icon-xiaoxi icon"></i>
                        <span class="text nac-text">消息</span>
                    </a>
                </li>


            </ul>
        </div>
        <div class="bottom-content">
            <li class="">
                <a href="#">
                    <i class="iconfont icon-zhuxiaoyuan icon"></i>
                    <span class="text nac-text">注销</span>
                </a>
            </li>
            <li class="mode">
                <div class="sun-moon">
                    <i class="iconfont icon-rijian icon sun"><img style="width:30px;" src="background_img/sun.png" alt="" /></i>
                    <i class="iconfont icon-yejian icon moon">O</i>
                </div>
                <span class="mode-text text">夜间模式</span>
                <div class="toggle-switch">
                    <span class="switch"></span>
                </div>
            </li>
        </div>
    </div>
    <script src="js/left.js"></script>
</nav>
<div style="display:flex;">
    <div style="width:100px;height:max-content;"></div><!--导航栏遮挡部分-->
    <!--主体-->
    <div>
        <!--导航栏-->
        <div>
            <div class="pilot">
                <div class="nav">
                    <ul>
                        <li><a href="#">HTML</a></li>
                        <li><a href="#">CSS</a></li>
                        <li style="display: flex;"><a href="shopCart">购物车</a></li>
                        <li><a href="searchOrder">订单历史</a></li>
                        <li><a href="main">主页</a></li>
                        <li><a href="#">客服</a></li>
                        <div class="nav-box"></div>
                    </ul>
                </div>
            </div>
        </div>
        <div style="width:1500px; height:60px;"></div>
    </div>
</div>
<div class="container">
    <h1>Your Orders</h1>

    <c:forEach items="${AllOrder}" var="orders">
    <c:forEach items="${orders.goodsArrayList}" var="order">
        <ul class="order-list">
            <li class="order-item">
                <img src="${order.getKey().imgUrl}" alt="${order.getKey().name}">
                <div class="order-item-details">
                    <h2>${order.getKey().name}</h2>
                    <p>${order.getKey().description}</p>
                    <p class="order-item-price">$${order.getKey().price}</p>
                    <p>Quantity: ${order.getValue()}</p>
                </div>
            </li>
        </ul>
    </c:forEach>
    <div class="order-total">
        Total: $${orders.total_price}
    </div>
    <div class="order-info">
        <div class="order-date">Order Date: ${orders.timestamp}</div>
        <div class="order-address">Delivery Address: ${orders.address}</div>
        <div class="order-status">Order Status: ${orders.order_status}</div>
    </div>
    </c:forEach>
    <button class="back-button" onclick="window.location.href='shopCart'">Back</button>
</div>
</body>

</html>