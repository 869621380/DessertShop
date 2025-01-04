
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
aElement.href = "product?id=0";
} else if (count === 3) {
image.src = 'background_img/login2.jpg';
image.alt = '2';
aElement.href = "product?id=1";
} else if (count === 6) {
image.src = 'background_img/login3.jpg';
image.alt = '3'
aElement.href = "product?id=2";
} else if (count === 9) {
image.src = 'background_img/login4.jpg';
image.alt = '4';
aElement.href = "product?id=3";
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