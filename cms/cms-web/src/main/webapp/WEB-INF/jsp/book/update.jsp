<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css?v=201509231"/>
    <title>修改书籍</title>
</head>
<body>
<div class="breadcrumb">
    <span class="crust"><a href="${pageContext.request.contextPath}/" class="crumb">首页</a><span class="arrow"><span>&gt;</span></span></span>
    <span class="crust"><a href="${pageContext.request.contextPath}/user" class="crumb">用户管理</a><span class="arrow"><span>&gt;</span></span></span>
    <span class="crust"><a href="${pageContext.request.contextPath}/user/list" class="crumb">用户列表</a><span class="arrow"><span>&gt;</span></span></span>
    <span class="crust"><a href="${pageContext.request.contextPath}/book/list/${book.user.id}" class="crumb">书籍列表</a><span class="arrow"><span>&gt;</span></span></span>
    <span class="crust"><a href="" class="crumb">修改书籍</a><span class="arrow"><span>&gt;</span></span></span>
</div>
<div id="main">
    <form id="form" method="post">
        <input id="userid" type="hidden" name="userid" value="${book.userid}"/>
        <table border="1">
            <caption>修改书籍</caption>
            <tr><td>帐号：</td><td>${book.user.username}</td></tr>
            <tr><td>密码：</td><td>${book.user.password}</td></tr>
            <tr><td>昵称：</td><td>${book.user.nickname}</td></tr>
            <tr><td>性别：</td><td><c:if test="${book.user.sex==1}">男</c:if><c:if test="${book.user.sex==2}">女</c:if></td></tr>
            <tr><td>书名：<font color="#cc0000">*</font></td><td><input id="name" type="text" name="name" placeholder="必填" required="true" maxlength="20" autofocus value="${book.name}"/></td></tr>
            <tr><td></td><td><a href="${pageContext.request.contextPath}/book/list/${book.user.id}">取消</a>　<input type="submit" value="保存"/></td></tr>
        </table>
    </form>
</div>
</body>
</html>