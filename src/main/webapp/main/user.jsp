<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            //创建一个表单
            jQuery("#usTable").jqGrid({
                url : "${path}/user/showAll",    //page 当前页    rows  每页展示条数
                editurl:"${path}/user/edit",
                datatype : "json",
                rowNum : 10,
                rowList : [ 3,6,9 ],
                pager : '#uspager',  //分页
                sortname : 'id',
                viewrecords : true,   //是否展示条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth: true,
                colNames : [ 'Id', '手机号', '密码', '盐', '头像', '姓名', '法名', '性别', '地址', '签名','注册时间', '状态','上师id' ],
                colModel : [
                    {name : 'id',index : 'id',width : 60,align : "center"},
                    {name : 'phone',index : 'phone',width : 60,align : "center"},
                    {name : 'password',index : 'password',width : 60,align : "center"},
                    {name : 'salt',index : 'salt',width : 60,align : "center"},
                    {name : 'pic_img',index : 'pic_img',width : 60,align : "center"},
                    {name : 'name',index : 'name',width : 60,align : "center"},
                    {name : 'f_name',index : 'f_name',width : 60,align : "center"},
                    {name : 'sex',index : 'sex',width : 60,align : "center",
                        formatter:function (cellvalue) {
                            if(cellvalue==1){
                                return"男";
                            }else{
                                return"女";

                            }
                        }
                    },
                    {name : 'city',index : 'city',width : 60,align : "center"},
                    {name : 'sign',index : 'sign',width : 60,align : "center"},
                    {name : 'reg_time',index : 'reg_time',width : 60,align : "center"},
                    {name : 'status',index : 'name asc, invdate',width : 60,align : "center",
                        formatter:function (cellvalue,options,rowObject) {

                            if(cellvalue==1){
                                return"<butten class='btn btn-danger' onclick='uploadState(\""+rowObject.id+"\",\""+cellvalue+"\")'>未展示</button>";
                            }else{
                                return"<butten class='btn btn-success' onclick='uploadState(\""+rowObject.id+"\",\""+cellvalue+"\")'>已展示</button>";

                            }
                        }
                    },
                    {name : 'guru_id',index : 'id',width : 60,align : "center"}
                ]
            });
            //增删改查操作
            jQuery("#usTable").jqGrid('navGrid', '#uspager', {edit : false,add : false,del : false},
                {}, //修改之后的额外操作
                {}, //添加之后的额外操作
                {}  //删除之后的额外操作
            );
        });
        /*修改状态*/
        function uploadState(id,status) {
            if(status=="1"){
                $.ajax({

                    url:"${path}/user/edit",
                    type:"post",
                    datatype:"json",
                    data:{"id":id,"status":"0","oper":"edit"},
                    success:function () {
                        //刷新表单
                        $("#usTable").trigger("reloadGrid");
                    }
                });

            }else{
                $.ajax({
                    url:"${path}/user/edit",
                    type:"post",
                    datatype:"json",
                    data:{"id":id,"status":"1","oper":"edit"},
                    success:function () {
                        //刷新表单
                        $("#usTable").trigger("reloadGrid");
                    }
                });
            }
        }

        //点击导出
        function btn1(){
            $.ajax({
                url:"${path}/user/derive",
                type:"post",
                datatype:"json",
                success:function () {
                    alert("导出完成");
                }
            });
        }
    </script>
</head>
<body>
<div class="panel panel-info">
    <%--面板--%>
    <div class="panel panel-heading">
        <h4>用户信息管理</h4>
    </div>


    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>信息展示</a></li>
        <div class="panel panel-body" align="reght">
            <button onclick="btn1()" align="reght">一键导出</button>
            <button onclick="btn2()" align="reght">一键导入</button>
        </div>
    </ul>


    <%--初始化表单--%>
    <table id="usTable"/>
    <%--分页工具栏--%>
    <div id="uspager"/>

</div>

</body>
</html>