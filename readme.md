# DessertShop-Springboot
使用spring-boot对项目进行了重写

使用jwt令牌与拦截器，Redis存储验证登录状态

使用过滤器和slf4j存储记录日志

对数据库进行了重构，优化了数据库结构

新加了商家商品订单，用于商家查看订单信息
# DessertShop
此项目为中南大学web系统与技术实验作业  
DessertShop 是一个模拟的在线甜品店项目，旨在展示一个完整的 Java Web 应用程序的结构和功能。该项目包括用户管理、商品管理、购物车管理和订单管理等主要功能模块。

## 项目结构
```
DessertShop/
├── domain/
│   ├── Account.java
│   ├── Category.java
│   ├── Goods.java
│   ├── Order.java
│   ├── Pair.java
│   └── ShopCart.java
├── filter/
│   └── Filter01.java
├── jdbc/
│   └── DBUtil.java
├── persistence/
│   ├── AccountDao.java
│   ├── CategoryDao.java
│   ├── ShopCartDao.java
│   ├── impl/
│   │   ├── AccountDaoImpl.java
│   │   ├── CategoryDaoImpl.java
│   │   └── ShopCartDaoImpl.java
├── services/
│   ├── CatalogService.java
│   └── CheckOutService.java
├── web/
│   ├── index.html
│   ├── css/
│   │   └── styles.css
│   ├── js/
│   │   └── scripts.js
│   └── WEB-INF/
│       ├── web.xml
│       └── views/
│           └── somePage.jsp
```
## 主要模块和文件

- **domain**: 包含项目的主要实体类，如 `Account`、`Category`、`Goods`、`Order`、`Pair` 和 `ShopCart`。这些类定义了系统中的主要数据结构和业务对象。
- **filter**: 包含过滤器类，如 `Filter01.java`，用于处理请求和响应的过滤逻辑。
- **jdbc**: 包含数据库工具类，如 `DBUtil.java`，用于管理数据库连接和操作。
- **persistence**: 包含数据访问对象（DAO）接口和实现类，如 `AccountDao`、`CategoryDao`、`ShopCartDao` 及其实现类。DAO 层负责与数据库进行交互。
- **services**: 包含业务逻辑层的服务类，如 `CatalogService` 和 `CheckOutService`，用于处理业务逻辑。
- **web**: 包含前端资源和 JSP 文件，如 `index.html`、CSS 文件、JavaScript 文件和 JSP 页面。`WEB-INF` 目录包含配置文件和 JSP 页面。

## 主要功能

- **用户管理**: 通过 `Account` 类和 `AccountDao` 实现用户的注册、登录和信息管理。
- **商品管理**: 通过 `Category` 和 `Goods` 类以及相应的 DAO 实现商品的管理和展示。
- **购物车管理**: 通过 `ShopCart` 类和 `ShopCartDao` 实现购物车的添加、删除和更新功能。
- **订单管理**: 通过 `Order` 类和相应的 DAO 实现订单的创建和查询。
- **过滤器**: 通过 `Filter01.java` 实现请求和响应的过滤逻辑。
## 构建和运行项目

### 环境配置

要运行该项目，您需要以下环境和工具：

- **Java 17 或更高版本**: 项目使用 Java 语言编写。
- **Tomcat 服务器**: 用于部署和运行 Java Web 应用程序。
- **MySQL 数据库**: 用于存储项目数据。
### 使用 IDE 构建和运行项目

1. **导入项目**:
    - 打开您的 IDE（如 IntelliJ IDEA 或 Eclipse）。
    - 选择 `File` -> `Open`，然后选择项目的根目录 `DessertShop`。

2. **配置数据库**:
    - 创建一个新的 MySQL 数据库。
    - 导入项目根目录下的 `schema.sql` 文件来创建表和初始数据。

3. **修改数据库配置**:
    - 在 `jdbc/DBUtil.java` 文件中，修改数据库连接的 URL、用户名和密码。

4. **配置 Tomcat 服务器**:
    - 在 IDE 中，选择 `Run` -> `Edit Configurations`。
    - 点击 `+` 号，选择 `Tomcat Server` -> `Local`。
    - 配置 Tomcat 服务器的安装路径。
    - 在 `Deployment` 选项卡中，点击 `+` 号，选择 `Artifact`，然后选择 `DessertShop:war`。

5. **运行项目**:
    - 点击 `Run` 按钮启动 Tomcat 服务器。

6. **访问应用**:
    - 在浏览器中打开 `http://localhost:8080/main`。

