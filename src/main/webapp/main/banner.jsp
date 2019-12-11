<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            //创建一个表单
            jQuery("#bTable").jqGrid({
                url : "${path}/banner/showAll",    //page 当前页    rows  每页展示条数
                editurl:"${path}/banner/edit",
                datatype : "json",
                rowNum : 10,
                rowList : [ 3,6,9 ],
                pager : '#bpager',  //分页
                sortname : 'id',
                viewrecords : true,   //是否展示条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth: true,
                colNames : [ 'Id', '图片', '描述', '状态', '上传时间' ],
                colModel : [
                    {name : 'id',index : 'id',width : 120,align : "center"},
                    {name : 'src_img',editable:true,edittype:"file",index : 'invdate',width : 90,align : "center",
                        formatter:function (cellvalue,options,rowObject) {
                            return"<img src='${path}/webapp/upload/image"+cellvalue+"' style='width:150px;height:80px'/>";
                        }
                    },
                    {name : 'describ',editable:true,index : 'name asc, invdate',width : 100,align : "center"},
                    {name : 'state',index : 'tax',width : 50,align : "center",
                        formatter:function (cellvalue,options,rowObject) {
                            if(cellvalue==1){
                                return"<butten class='btn btn-danger' onclick='uploadState(\""+rowObject.id+"\",\""+cellvalue+"\")'>已冻结</button>";

                            }else{
                                return"<butten class='btn btn-success' onclick='uploadState(\""+rowObject.id+"\",\""+cellvalue+"\")'>已解冻</button>";

                            }
                        }
                    },
                    {name : 'upload_time',index : 'amount',width : 60,align : "center"},
                ]
            });
            //增删改查操作
            jQuery("#bTable").jqGrid('navGrid', '#bpager', {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑"},
                {
                    closeAfterEdit:true,//关闭添加框
                }, //修改之后的额外操作
            {
                closeAfterAdd:true,//关闭添加框
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/banner/uploadBanner",
                        type:"post",
                        datatype:"json",
                        data:{id:data.responseText}, //获取id
                        fileElementId:"src_img",//需要上传文件的文件域id,既<input type="file">的id
                        success:function () {
                            //刷新表单
                            $("#bTable").trigger("reloadGrid");
                        }
                    });
                    return "bTable";
                },


            }, //添加之后的额外操作
            {}  //删除之后的额外操作
            );
        });
        /*修改状态*/
        function uploadState(id,state) {
            if(state=="1"){
                $.ajax({
                    url:"${path}/banner/edit",
                    type:"post",
                    datatype:"json",
                    data:{"id":id,"state":"0","oper":"edit"},
                    success:function () {
                        //刷新表单
                        $("#bTable").trigger("reloadGrid");
                    }
                });

            }else{
                $.ajax({
                    url:"${path}/banner/edit",
                    type:"post",
                    datatype:"json",
                    data:{"id":id,"state":"1","oper":"edit"},
                    success:function () {
                        //刷新表单
                        $("#bTable").trigger("reloadGrid");
                    }
                });
            }
        }
    </script>
</head>
<body>
    <div class="panel panel-info">
        <%--面板--%>
        <div class="panel panel-heading">
            <h4>轮播图管理</h4>
        </div>

        <%--标签页--%>
        <ul class="nav nav-tabs">
            <li class="active"><a>轮播图管理</a></li>
        </ul>

        <%--初始化表单--%>
        <table id="bTable"/>
        <%--分页工具栏--%>
        <div id="bpager"/>
    </div>
</body>
</html>