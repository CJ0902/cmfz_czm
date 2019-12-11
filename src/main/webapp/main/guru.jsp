<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            //创建一个表单
            jQuery("#usTable").jqGrid({
                url : "${path}/guru/showAll",    //page 当前页    rows  每页展示条数
                datatype : "json",
                rowNum : 6,
                rowList : [ 3,6,9 ],
                pager : '#uspager',  //分页
                sortname : 'id',
                viewrecords : true,   //是否展示条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth: true,
                colNames : [ 'Id', '名字', '头像', '登录名','登录密码','注册时间' ],
                colModel : [
                    {name : 'id',index : 'id',width : 50,align : "center"},
                    {name : 'name',index : 'name',width : 50,align : "center"},
                    {name : 'pic_img',index : 'pic_img',width : 50,align : "center",
                        formatter:function (cellvalue,options,rowObject) {
                            return"<img src='${path}/upload/###/"+cellvalue+"' style='width:130px;height:70px'/>";
                        }
                    },
                    {name : 'username',index : 'username',width : 50,align : "center"},
                    {name : 'password',index : 'password',width : 50,align : "center"},
                    {name : 'reg_time',index : 'reg_time',width : 50,align : "center"},
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
        <h4>上师管理</h4>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>上师信息展示</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="usTable"/>
    <%--分页工具栏--%>
    <div id="uspager"/>
</div>
</body>
</html>