<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            //创建一个表单
            jQuery("#usTable").jqGrid({
                url : "${path}/admin/log",
                datatype : "json",
                rowNum : 10,
                sortname : 'id',
                viewrecords : true,   //是否展示条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth: true,
                colNames : [ 'Id', 'username', 'password'],
                colModel : [
                    {name : 'id',index : 'id',width : 60,align : "center"},
                    {name : 'username',index : 'username',width : 60,align : "center"},
                    {name : 'password',index : 'password',width : 60,align : "center"},
                ]
            });
            //增删改查操作
            jQuery("#usTable").jqGrid('navGrid', '#uspager', {edit : false,add : false,del : false});
        });
    </script>
</head>
<body>
<div class="panel panel-info">
    <%--面板--%>
    <div class="panel panel-heading">
        <h4>管理员信息</h4>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>信息展示</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="usTable"/>
</div>
</body>
</html>