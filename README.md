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
|       ├── upms-dao -- MyBatisGenerator代码生成模块，无需开发
|       ├── upms-rpc-api -- rpc接口包
|       ├── upms-rpc-service -- rpc服务提供者
|       ├── upms-client -- 集成upms依赖包，提供单点认证、授权、会话管理
|       └── upms-server -- 系统及SSO服务端[端口:1111]
├── cms -- 内容管理系统
|       ├── cms-common -- cms系统公共模块
|       ├── cms-dao -- MyBatisGenerator代码生成模块，无需开发
|       ├── cms-rpc-api -- rpc接口包
|       ├── cms-rpc-service -- rpc服务提供者
|       ├── cms-search 搜索服务[端口:2221]
|       ├── cms-job  -- 消息队列、任务调度等[端口:2223]
|       ├── cms-admin CMS后台管理[端口:2222]
|       └── cms-web -- CMS网站前台[端口:2224]
├── pay -- 支付系统
|       ├── pay-dao -- MyBatisGenerator代码生成模块，无需开发
|       ├── pay-service -- 业务逻辑
|       ├── pay-sdk -- pay 开发工具包
|       ├── pay-admin pay后台管理[端口:3331]
|       └── pay-web -- pay网站前台[端口:3332]
├── ucenter -- 用户系统(包括第三方登录)
|       ├── ucenter-dao -- MyBatisGenerator代码生成模块，无需开发
|       ├── ucenter-service -- 业务逻辑
|       └── ucenter-home ucenter前台[端口:4441]
├── wechat -- 微信
|       ├── wechat-mp --  微信公众号管理系统
|       |          ├── wechat-mp-dao  -- MyBatisGenerator代码生成模块，无需开发
|       |          ├── wechat-mp-service  -- 业务逻辑
|       |          └── wechat-mp-admin  -- 后台管理[端口:5551]
|       └── wechat-app -- 微信小程序后台
├── oss -- 对象存储系统
|       ├── oss-sdk -- OSS上传下载管理凭证等
|       └──oss-web -- OSS在线管理项目[端口:7771]
├── qa -- 问答系统
|       ├── qa-dao -- MyBatisGenerator代码生成模块，无需开发
|       └── qa-service -- service层
├── api -- API接口总线系统
|       ├── api-common -- api系统公共模块
|       ├── api-rpc-api -- rpc接口包
|       ├── api-rpc-service -- rpc服务提供者
|       └── api-server -- api系统服务端[端口:6666]
├── shop -- 电子商务系统
├── oa -- 办公自动化系统
├── eoms -- 运维系统
└── im -- 即时通讯系统
```

## 模块介绍
>common 
Spring+SpringMVC+Mybatis框架集成公共模块，包括公共配置、MybatisGenerator扩展插件、通用BaseService、工具类等。
>admin
项目所有系统都是使用该模块界面作为前端展示。
>upms
本系统是基于RBAC授权和基于用户授权的细粒度权限控制通用平台，并提供单点登录、会话管理和日志管理。接入的系统可自由定义组织、角色、权限、资源等。
>cms
内容管理系统：支持多标签、多类目、强大评论的内容管理，有基本单页展示，菜单管理，系统设置等功能。
>api
接口总线系统，对外暴露统一规范的接口，包括各个子系统的交互接口、对外开放接口、开发加密接口、接口文档等服务
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

## 编译顺序
admin/common -> oss -> upms -> other

## 启动顺序
upms-rpc-service => upms-admin => xxx-rpc-service => xxx-yyy

## 框架规范约定

约定优于配置(convention over configuration)，此框架约定了很多编程规范，下面一一列举：

- service类，需要在叫名service的包下，并以Service结尾，如CmsArticleServiceImpl
- controller类，需要在以controller结尾的包下，类名以Controller结尾，如CmsArticleController.java，并继承BaseController
- spring task类，需要在叫名task的包下，并以Task结尾，如TestTask.java
- mapper.xml，需要在名叫mapper的包下，并以Mapper.xml结尾，如CmsArticleMapper.xml
- mapper接口，需要在名叫mapper的包下，并以Mapper结尾，如CmsArticleMapper.java
- model实体类，需要在名叫model的包下，命名规则为数据表转驼峰规则，如CmsArticle.java
- spring配置文件，命名规则为applicationContext-*.xml
- 类名：首字母大写驼峰规则；方法名：首字母小写驼峰规则；常量：全大写；变量：首字母小写驼峰规则，尽量非缩写
- springmvc配置加到对应模块的springMVC-servlet.xml文件里
- 配置文件放到src/main/resources目录下
- 静态资源文件放到src/main/webapp/resources目录下
- jsp文件，需要在/WEB-INF/jsp目录下
- RequestMapping和返回物理试图路径的url尽量写全路径，如：@RequestMapping("/manage")、return "/manage/index"
- RequestMapping指定method
- 模块命名为项目-业务，如cms-admin
- 数据表命名为：系统_表，如cms_article

## 附件
### 优秀文章和博客
[跟我学Shiro目录贴](http://jinnianshilongnian.iteye.com/blog/2018398 "跟我学Shiro目录贴")
[Redis中文网](http://www.redis.net.cn/ "Redis中文网")
