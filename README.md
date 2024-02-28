# Point-Code-Generator项目

> 作者：[point](https://github.com/point610)

## 项目介绍

基于 Spring Boot + Picocli + 对象存储（+ React + Ant Design）的代码生成器共享平台。

开发者可以在线制作发布代码生成器，用户可以搜索、下载、在线使用生成器。

管理员可以集中管理所有用户和生成器。

开发过程：本地代码生成器->生成器制作工具->生成器 web 平台。

##### 😀用户

- 注册成为网站用户
- 浏览和搜索想要的代码生成器
- 下载代码生成器
- 在线使用代码生成器
- 在线制作发布代码生成器

##### 😀管理员

- 管理用户
- 管理代码生成器

## 项目导航 🧭

- **[Point-Code-Generator 前端，web模块，网关模块，接口模块](https://github.com/point610/point-api)**
- **[Point-Code-Generator 在线平台](http://43.139.205.77/)**

## 目录结构 📑

| 目录                                                                                                                              | 描述                              |
|---------------------------------------------------------------------------------------------------------------------------------|---------------------------------|
| **[point-code-generator-backend](https://github.com/point610/point-code-generator/tree/master/point-code-generator-backend)**   | 后端模块                            |
| **[point-code-generator-frontend](https://github.com/point610/point-code-generator/tree/master/point-code-generator-frontend)** | 前端模块                            |
| **[base-code-generator-maker](https://github.com/point610/point-code-generator/tree/master/base-code-generator-maker)**         | maker生成器制作工具<br/>template模板制作工具 |
| **[base-code-generator](https://github.com/point610/point-api/tree/master/point-api-interface)**                                | basic本地代码生成器                    |
| **[demo-project](https://github.com/point610/point-code-generator/tree/master/demo-project)**                                   | demo模块                          |

## 项目架构 🗺️

#### web平台

页面逻辑
![img_11.png](img/img_11.png)
用户在线使用代码生成器流程
![img_12.png](img/img_12.png)
用户在线制作代码生成器流程
![img_13.png](img/img_13.png)

#### basic本地代码生成器

开发者编写
![img.png](img/img.png)
用户使用
![img_1.png](img/img_1.png)
动态文件处理
![img_2.png](img/img_2.png)

#### maker生成器制作工具

maker模块架构
![img_3.png](img/img_3.png)
![img_4.png](img/img_4.png)

modelConfig信息使用原理
![img_5.png](img/img_5.png)

#### template模板制作工具

template模块架构
![img_6.png](img/img_6.png)

###### 方法流程图

makeTemplate方法
![img_7.png](img/img_7.png)

makeFileTemplate方法
![img_8.png](img/img_8.png)

distinctFiles方法
![img_9.png](img/img_9.png)

distinctModels方法
![img_10.png](img/img_10.png)

## 项目选型 🎯

#### 后端

- Spring Boot 2.7.0
- Spring MVC
- MySQL 数据库
- 腾讯云COS存储
- 免费free-img组件模块
- Spring Session Redis 分布式登录，首页缓存
- Dubbo 分布式（RPC、Nacos）
- MyBatis-Plus 及 MyBatis X 自动生成
- Swagger + Knife4j 接口文档
- Apache Commons Lang3 工具类
- Hutool、Apache Common Utils、Gson 等工具库

#### 前端

- React 18
- Ant Design Pro 5.x 脚手架
- OpenAPI 前端代码生成
- Umi 4 前端框架
- Ant Design & Procomponents 组件库

## 功能介绍 📋

| **项目功能**           | 游客 | **用户** | **管理员** |
|--------------------|----|--------|---------|
| 浏览和搜索想要的代码生成器      | ✅  | ✅      | ✅       |
| 更新个人信息             | ❌  | ✅      | ✅       |
| 下载代码生成器            | ❌  | ✅      | ✅       |
| 在线使用代码生成器          | ❌  | ✅      | ✅       |
| 在线制作发布代码生成器        | ❌  | ✅      | ✅       |
| 用户管理               | ❌  | ❌      | ✅       |
| 代码生成器管理、代码生成器上线、下线 | ❌  | ❌      | ✅       |
| 用户接口管理，禁止用户调用接口    | ❌  | ❌      | ✅       |

## 功能展示 ✨

#### 登录
![img_14.png](img/img_14.png)

#### 注册
![img_15.png](img/img_15.png)

#### 首页
![img_16.png](img/img_16.png)

#### 代码生成器信息
![img_17.png](img/img_17.png)


#### 代码生成器文件配置
![img_18.png](img/img_18.png)


#### 代码生成器模型配置
![img_19.png](img/img_19.png)


#### 编辑页面
![img_20.png](img/img_20.png)
![img_21.png](img/img_21.png)
![img_22.png](img/img_22.png)
![img_23.png](img/img_23.png)

#### 用户管理
![img_24.png](img/img_24.png)

#### 代码生成器管理
![img_25.png](img/img_25.png)





