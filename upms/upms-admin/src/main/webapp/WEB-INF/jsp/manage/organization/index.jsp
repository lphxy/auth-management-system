﻿﻿
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>组织管理</title>
    <link href="${basePath}/resources/admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css"
          rel="stylesheet"/>
    <link href="${basePath}/resources/admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/admin/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <shiro:hasPermission name="upms:organization:create"><a class="waves-effect waves-button" href="javascript:;"><i
                class="zmdi zmdi-plus"></i> 新增组织</a></shiro:hasPermission>
        <shiro:hasPermission name="upms:organization:update"><a class="waves-effect waves-button" href="javascript:;"><i
                class="zmdi zmdi-edit"></i> 编辑组织</a></shiro:hasPermission>
        <shiro:hasPermission name="upms:organization:delete"><a class="waves-effect waves-button" href="javascript:;"><i
                class="zmdi zmdi-close"></i> 删除组织</a></shiro:hasPermission>
    </div>
    <table id="table"></table>
</div>
<script src="${basePath}/resources/admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/admin/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/admin/js/common.js"></script>
<script>
    $(function () {
        // bootstrap table初始化
        $('#table').bootstrapTable({
            url: '${basePath}/manage/organization/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'state', checkbox: true},
                {field: 'organizationId', title: '编号', sortable: true, align: 'center'},
                {field: 'name', title: '组织名称'},
                {field: 'description', title: '组织描述'},
                {
                    field: 'action',
                    title: '操作',
                    align: 'center',
                    formatter: 'actionFormatter',
                    events: 'actionEvents',
                    clickToSelect: false
                }
            ]
        }).on('all.bs.table', function (e, name, args) {
            $('[data-toggle="tooltip"]').tooltip();
            $('[data-toggle="popover"]').popover();
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="delete" href="javascript:void(0)" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }
    // 操作按钮事件
    window.actionEvents = {
        'click .update': function (e, value, row, index) {
            alert('You click update icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        },
        'click .delete': function (e, value, row, index) {
            alert('You click delete icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        }
    };
</script>
</body>
</html>