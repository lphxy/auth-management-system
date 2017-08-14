# auth-management-system
### 简介
跟踪 shuzheng/zheng 项目的基于SSM构建的权限管理系统（菜鸟学习方法）
### 版本要求
JDK 1.8
### 组织结构
```lua
auth-management-system
├── common -- 公共模块
├── datamodel -- sql脚本等存放
├── upms -- User Permissions Management System -- 通用用户权限管理系统
|       ├── upms-dao -- 数据库MyBatisGenerator操作
|       ├── upms-rpc-api -- rpc接口包
|       ├── upms-rpc-service -- rpc服务提供者[端口:1112]
|       ├── upms-service -- 业务逻辑
|       ├── upms-client -- SSO登录客户端
|       ├── upms-app1 -- SSO登录客户端2[端口:1113]
|       ├── upms-app2 -- SSO登录客户端2[端口:1114]
|       └── upms-server -- 系统及SSO服务端[端口:1111]
├── cms -- 内容管理系统
|       ├── cms-dao -- 数据库MyBatisGenerator操作
|       ├── cms-service -- 业务逻辑
|       ├── cms-search 搜索服务[端口:2221]
|       ├── cms-job  -- 消息队列、任务调度等[端口:2223]
|       ├── cms-admin CMS后台管理[端口:2222]
|       └── cms-web -- CMS网站前台[端口:2224]
├── pay -- 支付系统
|       ├── pay-dao -- 数据库MyBatisGenerator操作
|       ├── pay-service -- 业务逻辑
|       ├── pay-sdk -- pay 开发工具包
|       ├── pay-admin pay后台管理[端口:3331]
|       └── pay-web -- pay网站前台[端口:3332]
├── ucenter -- 用户中心系统
|       ├── ucenter-dao -- 数据库MyBatisGenerator操作
|       ├── ucenter-service -- 业务逻辑
|       └── ucenter-home ucenter前台[端口:4441]
├── wechat -- 微信
|       ├── wechat-mp -- 微信小程序[端口:5551]
├── oss -- 对象存储系统
|       ├── oss-sdk -- OSS上传下载管理凭证等
|       └──oss-web -- OSS在线管理项目[端口:7771]
├── qa -- 问答系统
|       ├── qa-dao -- DAO层
|       └── qa-service -- service层
```
### 技术选择
### 基础构建
| 技术 | 作用 |
| :------- | :------- |
| MySQL    | 数据库    | 
| Jetty    | 后台服务器 |
| IntelliJ IDEA | IDE |
| PowerDesigner | 建模工具 |
| Git      | 版本管理 |
#### 后端技术
| 技术 | 作用 | 官网 |
| :------- | :------- | :------|
| Spring Framework | 主框架           | http://projects.spring.io/spring-framework/ |
| SpringMVC        | MVC 框架         | 	http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc |
| MyBatis          | ORM 框架         | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatis Generator| 代码生成         | 	http://www.mybatis.org/generator/index.html |
| PageHelper       | MyBatis分页插件  | http://git.oschina.net/free/Mybatis_PageHelper |
| Maven	           | 项目构建管理	  | http://maven.apache.org/ |
| TBSchedule       | 任务调度         |
| Quartz           | 定时任务         | http://www.quartz-scheduler.org/|
| Ehcache          | 进程内缓存框架    | http://www.ehcache.org/ |
| Redis            | 分布式缓存数据库  | https://redis.io/ |
| ActiveMQ         | 消息队列         | http://activemq.apache.org/ |
| Dubbo            | rpc框架          | http://dubbo.io/ |
| Jsp/Velocity/Thymeleaf | 模板引擎   | 
## 开发环境搭建
### 修改本地host
* 127.0.0.1 upms.w1992wishes.cn
* 127.0.0.1 cms.w1992wishes.cn
* 127.0.0.1 pay.w1992wishes.cn
* 127.0.0.1 ucenter.w1992wishes.cn
* 127.0.0.1 wechat.w1992wishes.cn
* 127.0.0.1 api.w1992wishes.cn
* 127.0.0.1 oss.w1992wishes.cn