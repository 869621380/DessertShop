# DessertShop

## 1. 用户相关接口

### 1.1 登录

#### 1.1.1 基本信息

> 请求路径：/user/login
>
> 请求方式：POST
>
> 接口描述：该接口用于登录

#### 1.1.2 请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| username | 用户名 | string | 是       | 6~16位非空字符 |
| password | 密码   | string | 是       | 6~16位非空字符 |

请求数据样例：

```shell
username=ssassd&password=ssassd
```

#### 1.1.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | string | 必须     |        | 返回的数据,jwt令牌    |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjUsInVzZXJuYW1lIjoid2FuZ2JhIn0sImV4cCI6MTY5MzcxNTk3OH0.pE_RATcoF7Nm9KEp9eC3CzcBbKWAFOL0IsuMNjnZ95M"
}
```

#### 1.1.4 备注说明

> 用户登录成功后，系统会自动下发JWT令牌，然后在后续的每次请求中，浏览器都需要在请求头header中携带到服务端，请求头的名称为 Authorization，值为 登录时下发的JWT令牌。
>
> 如果检测到用户未登录，则http响应状态码为401

### 1.2 登录验证码

#### 1.2.1 基本信息

> 请求路径：/user/validateCode
>
> 请求方式：GET
>
> 接口描述：该接口用于请求注册所需要的验证码

#### 1.2.2 请求参数

请求参数：无

#### 1.2.3 响应数据

响应数据类型:image/jpeg
响应头参数说明：

|参数名称|类型|是否必须|备注|
|-------|----|-------|----|
|codeKey|string|是|存储在 Redis 中的验证码的键，用户注册时需要将这个 codeKey 发送回后端，以便后端从 Redis 中获取对应的验证码进行验证|

响应数据示例：
![alt text](image.png)

### 1.3 注册

#### 1.3.1 基本信息

> 请求路径：/user/register
>
> 请求方式：POST
>
> 接口描述：该接口用于注册新用户

#### 1.3.2 请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| username | 用户名 | string | 是       | 6~16位非空字符 |
| password | 密码   | string | 是       | 6~16位非空字符 |
| codes     |验证码  | string | 是      | 4位字符串 |
| codeKey|校验码 | string | 是 | 获取验证码时返回的值|

请求数据样例：

```shell
username=sdfadf&password=555644
```

#### 1.3.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | object | 非必须   |        | 返回的数据            |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": null
}
```

### 1.4 获取用户详细信息

#### 1.4.1 基本信息

> 请求路径：/user/userInfo
>
> 请求方式：GET
>
> 接口描述：该接口用于获取当前已登录用户的详细信息

#### 1.4.2 请求参数

无

#### 1.4.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------------- | ------ | -------- | ------ | --------------------- | -------- |
| code          | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message       | string | 非必须   |        | 提示信息              |          |
| data          | object | 必须     |        | 返回的数据            |          |
| username         | number | 必须   |        | 用户名                |          |
| nickName   | string | 必须   |        | 昵称                  |          |
| phone | string | 必须 | |电话 | |
| province      | string | 必须   |        |省份                  |          |
| city    | string | 必须   |        | 城市              |          |
| address | string | 必须   |        | 详细地址              |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": {
        "username": "xyy",
        "nickname": "未命名用户",
        "phone": "",
        "province": "湖南省",
        "city": "长沙市",
        "address": "岳麓区中南大学",
        "state": "1"
    }
}
```

### 1.5 更新用户基本信息

#### 1.5.1 基本信息

> 请求路径：/user/update
>
> 请求方式：PATCH
>
> 接口描述：该接口用于更新已登录用户的基本信息(不包含密码)

#### 1.5.2 请求参数

请求参数格式：application/json

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 |
| -------- | ------ | ------ | -------- |
| nickName|昵称   | string | 非必须   |
| phone | 电话号码 |string| 非必须 |
| province  |省份    | string | 非必须   |
| city    | 城市|string | 非必须   |
| address | 详细地址|string | 非必须   |

请求数据样例：

```shell
{
    "nickName":"sdfsd",
    "phone":"132658794556"
    "province":"山东省"
}
```

#### 1.5.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | object | 非必须   |        | 返回的数据            |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": null
}
```

## 2.商品主页相关接口

## 3.购物车相关接口

### 3.1 获取购物车信息

#### 3.1.1 基本信息

> 请求路径：/shopCart
>
> 请求方式：GET
>
> 接口描述：该接口用于获取当前已登录用户的购物车信息

#### 3.1.2请求参数

无

#### 3.1.3响应数据

响应数据类型：application/json

响应参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------------- | ------ | -------- | ------ | --------------------- | -------- |
| code          | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message       | string | 非必须   |        | 提示信息              |          |
| data          | object | 必须     |        | 返回的数据            |          |
| totalPrice          | double | 非必须     |        | 总价格      |    前端自行计算     |
| items         | array | 必须   |        | 购物车商品项列表        |          |
| items[].category   | object |  必须  |        | 商品信息     |    |
| items[].category.id | number | 必须 | |商品id | |
| items[].category.name      | string | 必须   |        |商品名称|          |
| items[].category.price    | string | 必须   |        | 商品价格        |          |
| items[].category.imageURL | string | 必须   |        | 图片路径 |          |
| items[].category.description | string | 必须   |        | 商品描述 |          |
| items[].category.remain | number | 必须   |        | 商品剩余数量 |          |
| items[].category.seller | string | 必须   |        | 售卖者 |          |
|items[].quantity|number|必须| |商品数量| |

响应数据样例：

```json
{
  "code": 0,
  "msg": "操作成功",
  "data": {
    "totalPrice": 2061.0,
    "items": [
      {
        "category": {
          "id": 2,
          "name": "示例1",
          "price": 234.0,
          "imgURL": "image/k.png",
          "description": "这只是一个示例",
          "remain": 453,
          "seller": "453"
        },
        "quantity": 3
      },
      {
        "category": {
          "id": 3,
          "name": "示例2",
          "price": 453.0,
          "imgURL": "image/s.png",
          "description": "这也只是一个示例",
          "remain": 453,
          "seller": "5786"
        },
        "quantity": 3
      }
    ]
  }
}
```

### 3.2购物车结账

#### 3.2.1基本信息

> 请求路径：/shopCart/checkout
>
> 请求方式：POST
>
> 接口描述：该接口用于对当前账户的购物车进行结账

#### 3.2.2请求参数

请求数据类型：application/json

请求参数说明：

| 名称          | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------------- | ------ | -------- | ------ | --------------------- | -------- |
| totalPrice          | double | 非必须     |        | 总价格      |    后端自行计算     |
| items         | array | 必须   |        | 结账商品项列表        |          |
| items[].id | number | 必须 | |商品id | |

请求数据样例：

```json
{
    "items": [
      {
        "id": 2
      },
      {
        "id": 3
      }
    ]
}
```

#### 3.2.2响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | object | 非必须   |        | 返回的数据            |          |

### 3.3改变购物车中商品数目

#### 3.3.1基本信息

> 请求路径：/shopCart/update
>
> 请求方式：PATCH
>
> 接口描述：该接口用于改变购物车中某一商品的数目

#### 3.3.2请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| id | 商品id | number | 是     |   |
| quantity | 购物车中商品数目   | string | 是       |  |

请求数据样例：

```shell
id=3&quantity=5
```

#### 3.3.3响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | string | 非必须     |        |     |

### 3.4删除购物车中商品

#### 3.4.1基本信息

> 请求路径：/shopCart/delete
>
> 请求方式：PATCH
>
> 接口描述：该接口用于删除购物车中某一商品

#### 3.4.2请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| id | 商品id | number | 是     |   |

请求数据样例：

```shell
id=3
```

#### 3.4.3响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | string | 非必须     |        | |

### 3.5向购物车添加商品

#### 3.5.1基本信息

> 请求路径：/shopCart/add
>
> 请求方式：POST
>
> 接口描述：该接口用于向购物车添加商品

#### 3.5.2请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 参数名称 | 说明   | 类型   | 是否必须 | 备注           |
| -------- | ------ | ------ | -------- | -------------- |
| id | 商品id | number | 是     |   |

请求数据样例：

```shell
id=3
```

#### 3.5.3响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | string | 非必须     |        | |

## 4.订单相关接口

### 4.1获取所有订单信息

#### 4.1.1请求参数

> 请求路径：/order
>
> 请求方式：POST
>
> 接口描述：该接口用于获取该用户的所有订单信息

#### 4.1.2请求参数

无

#### 4.1.3响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  |
| ------- | ------ | -------- | ------ | --------------------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |
| message | string | 非必须   |        | 提示信息              |
| data    | array | 必须     |        | |
| orderId    | number | 必须     |        | |
| totalPrice          | double | 必须     |        | 总价格      |
| address          | double | 必须     |        | 地址      |
| status         | string | 必须   |        | 订单状态        |
| cartItem   | array |  必须  |        | 商品信息     |
| cartItem[].category.id | number | 必须 | |商品id |
| cartItem[].category.name      | string | 必须   |        |商品名称|
| cartItem[].category.price    | string | 必须   |        | 商品购买时价格        |
| cartItem[].category.imageURL | string | 必须   |        | 图片路径 |
| cartItem[].category.description | string | 必须   |        | 商品描述|
| cartItem[].category.remain | number | 非必须   |        | 商品剩余数量 |
| cartItem[].category.seller | string | 非必须   |        | 售卖者 |
|cartItem[].quantity|number|必须| |商品数量|

响应数据示例：

```json
{
  "code": 0,
  "msg": "操作成功",
  "data": [
    {
      "orderId": 6,
      "totalPrice": 2061.0,
      "username": "sdffff",
      "address": "",
      "status": "未完成",
      "cartItem": [
        {
          "category": {
            "id": 2,
            "name": "示例1",
            "price": 234.0,
            "imgURL": "image/k.png",
            "description": "这只是一个示例",
            "remain": null,
            "seller": null
          },
          "quantity": 3
        },
        {
          "category": {
            "id": 3,
            "name": "示例2",
            "price": 453.0,
            "imgURL": "image/s.png",
            "description": "这也只是一个示例",
            "remain": null,
            "seller": null
          },
          "quantity": 3
        }
      ],
      "createTime": "2025-22-14 06:22:34"
    }
  ]
}
```

### 4.2订单支付

#### 4.2.1基本信息

> 请求路径：/order/checkout
>
> 请求方式：POST
>
> 接口描述：该接口用于对订单进行结账

#### 4.2.2请求参数

请求参数格式：x-www-form-urlencoded

请求参数说明：

| 名称         | 类型    | 是否必须  | 默认值 |
| -------------| ------ | -------- | ------ |
| orderId      | number | 必须      |        |

请求数据样例：

```
orderId=5
```

#### 4.3.3 响应数据

响应数据类型：application/json

响应参数说明：

| 名称    | 类型   | 是否必须 | 默认值 | 备注                  | 其他信息 |
| ------- | ------ | -------- | ------ | --------------------- | -------- |
| code    | number | 必须     |        | 响应码, 0-成功,1-失败 |          |
| message | string | 非必须   |        | 提示信息              |          |
| data    | object | 非必须   |        | 返回的数据            |          |

响应数据样例：

```json
{
    "code": 0,
    "message": "操作成功",
    "data": null
}
```
