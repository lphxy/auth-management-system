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
├── cms -- 内容管理系统
|       ├── cms-dao -- DAO数据持久层
|       ├── cms-service -- 业务逻辑层
|       ├── cms-web -- WEB项目
|       └── cms-mq  -- 备用消息，cms-web中已经包含mq
├── oss -- 对象存储系统
|       ├── oss-sdk -- OSS上传下载管理凭证等
|       └──oss-web -- OSS在线管理项目
├── qa -- 问答系统
|       ├── qa-dao -- DAO层
|       └── qa-service -- service层
├── upms -- User Permissions Management System，通用用户权限管理系统
```
### 技术选择
### 基础构建
| 技术 | 作用 |
| :------- | :------- |
| MySQL    | 数据库    | 
| Jetty    | 后台服务器 |
| IntelliJ IDEA | IDE |
| PowerDesigner | 建模工具 |
#### 后端技术
| 技术 | 作用 | 官网 |
| :------- | :------- | :------|
| Spring Framework | 主框架   | http://projects.spring.io/spring-framework/ |
| SpringMVC        | MVC 框架 | 	http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc |
| MyBatis          | ORM 框架 | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatis Generator| 代码生成 | 	http://www.mybatis.org/generator/index.html |
| PageHelper | MyBatis分页插件| http://git.oschina.net/free/Mybatis_PageHelper |
| Ehcache	       | 进程内缓存框架| http://www.ehcache.org/ |
| Maven	           |项目构建管理	 | http://maven.apache.org/ |
