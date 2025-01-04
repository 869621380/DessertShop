<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/shop-cart.css">
    <link rel="stylesheet" href="css/left.css">
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
                    <a href="#">
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
    </div>
</div>
<div style="width:1500px; height:60px;"></div>

<form id="checkoutForm" action="checkout" method="POST"accept-charset="UTF-8" >
    <div class="container">
        <div class="shopping-cart">
            <h1>Shopping Cart</h1>
            <ul class="cart-items" id="cartItemsList">
                <c:forEach items="${shopCart.shopCarts}" var="item" varStatus="loop">
                    <script>
                        var xhr;
                        function changNum${loop.index}(operation){
                            xhr=new XMLHttpRequest();
                            xhr.onreadystatechange=fun${loop.index};
                            var index =${loop.index};
                            var numValue = document.getElementById('num' + index).value;
                            numValue = parseInt(numValue);
                            if (operation === "increase") {
                               numValue=numValue+1;
                            } else if (operation === "decrease") {
                                numValue = Math.max(numValue - 1, 1);
                            }

                            xhr.open("GET","changeGoodsCounts?id="+index+"&newNum="+numValue,true);
                            xhr.send(null);
                        }

                        function fun${loop.index}(){
                        }
                    </script>
                    <script>
                        var xhr;
                        function remove${loop.index}(){
                            xhr=new XMLHttpRequest();
                            xhr.onreadystatechange=func${loop.index};
                            var index =${loop.index};
                            xhr.open("GET","removeGoods?id="+index,true);
                            xhr.send(null);

                        }
                        function func${loop.index}() {
                            if (xhr.readyState === 4 && xhr.status === 200) {
                                // 当请求完成且响应成功时，执行页面刷新操作
                                location.reload();
                            }
                        }


                    </script>
                    <li>
                        <img src="${item.getKey().getImgUrl()}" alt="${item.getKey().getName()}">
                        <div class="item-details">
                            <h2>${item.getKey().getName()}</h2>
                            <p>${item.getKey().getDescription()}</p >
                            <div class="price-and-quantity">
                                <span class="price">$${item.getKey().getPrice()}</span>
                                <div class="quantity-controls">
                                    <button class="increase-quantity" type="button" onclick="changNum${loop.index}('increase')">+</button>
                                    <input type="number" min="1" value="${item.getValue()}" id="num${loop.index}" onblur="changNum${loop.index}()">
                                    <button class="decrease-quantity" type="button" onclick="changNum${loop.index}('decrease')">-</button>
                                </div>
                            </div>
                            <button class="remove-item" onclick="remove${loop.index}()">Remove</button>
                        </div>
                        <input type="hidden" name="itemName${loop.index}" value="${item.getKey().getName()}">
                        <input type="hidden" name="itemDescription${loop.index}" value="${item.getKey().getDescription()}">
                        <input type="hidden" name="itemPrice${loop.index}" value="${item.getKey().getPrice()}">
                        <input type="hidden" name="itemQuantity${loop.index}" value="${item.getValue()}">
                        <input type="hidden" name="itemId${loop.index}" value="${item.getKey().getId()}">
                        <input type="hidden" name="itemSrc${loop.index}" value="${item.getKey().getImgUrl()}">
                    </li>
                </c:forEach>
                <input type="hidden" name="msg" value="goCheckout">
            </ul>
            <div class="total" id="totalElement">Total:$${shopCart.totalPrice}</div>
            <button class="checkout" type="submit">Checkout</button>
        </div>
    </div>
    </div>
</form>
            <script src="js/shop-cart.js"></script>
</body>
</html>