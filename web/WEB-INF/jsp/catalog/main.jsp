<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024/10/23
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DessertShop</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="css/MainCss.css">
</HEAD>

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
                    <a href="shopCart">
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
                    <a href="find">
                        <i class="iconfont icon-xiaoxi icon"></i>
                        <span class="text nac-text">修改信息</span>
                    </a>
                </li>


            </ul>
        </div>
        <div class="bottom-content">
            <li class="">
                <a href="login">
                    <i class="iconfont icon-zhuxiaoyuan icon"></i>
                    <span class="text nac-text">登录</span>
                </a>
            </li>
            <li class="">
                <a href="login">
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

</nav>
<div style="display:flex;">
    <div style="width:90px;height:max-content;"></div><!--导航栏遮挡部分-->
    <!--主体-->
    <div>
        <!--导航栏-->
        <div>
            <div class="pilot">
                <div class="nav">
                    <ul>
                        <li><a href="#">HTML</a></li>
                        <li><a href="#">CSS</a></li>
                        <li style="display: flex;"><a href="shopCart">购物车  <div class="shopping-cart-icon" id="cartIcon">${itemCount}</div></a></li>
                        <li><a href="searchOrder">订单历史</a></li>
                        <li><a href="main">主页</a></li>
                        <li><a href="#">客服</a></li>
                        <div class="nav-box"></div>
                    </ul>
                </div>
            </div>
        </div>
        <div style="width:1500px; height:60px;"></div>
        <!--搜索栏-->
        <div class="search-container">
            <form action="main" method="post">
            <input style="width: 1520px;" name="name" type="text" placeholder="搜索...">
            <button>搜索</button>
            </form>
        </div>
        <!--热词-->
        <div class="hotword">
            <a href="product?id=0">巧克力牛油果慕斯</a>
            <a href="product?id=1">提拉米苏</a>
            <a href="product?id=2">泡芙</a>
            <a href="product?id=3">芒果椰奶冻</a>
            <a href="#">甜品5</a>
            <a href="#">甜品6</a>
            <a href="#">甜品1</a>
            <a href="#">甜品2</a>
            <a href="#">甜品3</a>
            <a href="#">甜品4</a>
            <a href="#">甜品5</a>
            <a href="#">甜品6</a>
            <a href="#">甜品1</a>
            <a href="#">甜品2</a>
            <a href="#">甜品3</a>
            <a href="#">甜品4</a>
            <a href="#">甜品5</a>
            <a href="#">甜品6</a>
        </div>

        <!--轮播图-->
        <div style="display:flex;">
            <div style="width:220px;"></div>
            <div style="width: 980px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5); ">
                <div class="rotation">
                    <a id="rotation_a" href="product?id=1">
                    <img id="rotation_img" src="background_img/login1.jpg" alt="Image 1">
                    </a>
                </div>
            </div>
        </div>
        <!--表单-->
        <table class="css-table" cellspacing="10">
            <c:forEach var="index" begin="0" end="${fn:length(sessionScope.categoryList)}" step="4">
                <tr>
                    <c:forEach var="i" begin="0" end="3">
                        <c:if test="${index + i < fn:length(sessionScope.categoryList)}">
                            <td>
                                <a href="product?id=${index+ i}">
                                <img src="${sessionScope.categoryList[index + i].getImgURL()}" alt="${sessionScope.categoryList[index + i].getCategoryId()}">
                                </a>
                                <p>${sessionScope.categoryList[index + i].getName()}</p>
                                <span>价格:</span><span>${sessionScope.categoryList[index + i].getPrice()}</span><span>元</span>
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <div class="footer">
            <div class="footer-links">
                <a href="#">关于我们</a>
                <a href="#">联系我们</a>
                <a href="#">加入我们</a>
                <a href="#">隐私政策</a>
                <a href="#">服务条款</a>
            </div>
            <p class="copyright">© 2024 [DessertShop]. 版权所有。</p>
        </div>
    </div>
</div>
</body>
</HTML>


<%
    Integer itemCount = (Integer) session.getAttribute("itemCount");
%>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const itemCount = <%= itemCount %>;
        const cartIcon = document.getElementById('cartIcon');
        if (itemCount!== null&&itemCount != 0) {
            cartIcon.style.zIndex = 2;
        }
        else
        {
            cartIcon.style.zIndex = -1;
        }
    });
    const image = document.getElementById('rotation_img');
    const aElement = document.getElementById('rotation_a');
    let count = 0;
    setInterval(() => {
        if(image)
        {
            if (count === 0) {
                image.src = 'background_img/login1.jpg';
                image.alt = '1';
                aElement.href = "product?id=1";
            } else if (count === 3) {
                image.src = 'background_img/login2.jpg';
                image.alt = '2';
                aElement.href = "product?id=2";
            } else if (count === 6) {
                image.src = 'background_img/login3.jpg';
                image.alt = '3'
                aElement.href = "product?id=4";
            } else if (count === 9) {
                image.src = 'background_img/04.jpg';
                image.alt = '4';
                aElement.href = "product?id=5";
            }
            count = (count + 1) % 12;
        }

    }, 1000);
    const body = document.querySelector('body'),
        shell = body.querySelector('nav'),
        toggle = body.querySelector(".toggle"),
        searchBtn = body.querySelector(".search-box"),
        modeSwitch = body.querySelector(".toggle-switch"),
        modeText = body.querySelector(".mode-text");
    // 点击toggle元素时触发事件
    toggle.addEventListener("click", () => {
// 切换shell元素的close类
        shell.classList.toggle("close");
    })
    // 点击searchBtn元素时触发事件
    searchBtn.addEventListener("click", () => {
// 移除shell元素的close类
        shell.classList.remove("close");
    })
    // 点击modeSwitch元素时触发事件
    modeSwitch.addEventListener("click", () => {
// 切换body元素的dark类
        body.classList.toggle("dark");
// 如果body元素包含dark类
        if (body.classList.contains("dark")) {
            modeText.innerText = "白日模式";
        } else {
            modeText.innerText = "夜间模式";
        }
    });
</script>
