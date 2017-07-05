# auth-management-system
### 简介
基于SSM构建的权限管理系统
### 版本要求
JDK 1.8
### 组织结构
>auth-management-system
>>common --> ssm框架公共模块  
>>cms --> 内容管理系统
>>>cms-dao --> 代码生成模块，无需开发
### 技术选择
### 基础构建
| 技术 | 作用 |
| :------- | :------- |
| MySQL    | 数据库    | 
| Jetty    | 后台服务器 |
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
