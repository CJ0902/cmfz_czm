<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
        uploadJson:"${path}/editor/uploadPhoto",  //指定文件上传的路径
        filePostName:"img",  //指定上传文件的名  默认是imgFile
        allowFileManager:true,//显示浏览远程图片的按钮
        fileManagerJson:"${path}/editor/photos",  //指定浏览远程图片的路径
        afterBlur:function () {//失去焦点后执行的方法
            this.sync();        //将KindEditor中的内容同步到表单中
        }
    });
</script>

<html>
<head>
    <script>
        $(function () {
            //创建一个表单
            jQuery("#usTable").jqGrid({
                url : "${path}/article/showAll",    //page 当前页    rows  每页展示条数
                editurl:"${path}/article/edit",
                datatype : "json",
                rowNum : 10,
                rowList : [ 3,6,9 ],
                pager : '#uspager',  //分页
                sortname : 'id',
                viewrecords : true,   //是否展示条数
                styleUI:"Bootstrap",
                height:"auto",
                autowidth: true,
                colNames : [ 'Id', '标题', '内容', '发布时间','作者','作者id' ],
                colModel : [
                    {name : 'id',index : 'id',width : 60,align : "center"},
                    {name : 'title',index : 'phone',width : 30,align : "center"},
                    {name : 'content',index : 'password',width : 180,align : "center"},
                    {name : 'upload_time',index : 'salt',width : 60,align : "center"},
                    {name : 'guru_name',index : 'guru_name',width : 60,align : "center"},
                    {name : 'guru_id',index : 'guru_id',width : 60,align : "center"},

                ]
            });
            //增删改查操作
            jQuery("#usTable").jqGrid('navGrid', '#uspager', {edit : false,add : false,del : false},
                {}, //修改之后的额外操作
                {}, //添加之后的额外操作
                {}  //删除之后的额外操作
            );
            /*点击触发模态框*/
            $("#btn1").click(function () {

                //选中一行展示        返回参数信息      最后行的id
                var rowId=$("#usTable").jqGrid("getGridParam","selrow");
                if(rowId!=null){

                    /*根据行id返回订行的数据*/
                   var row= $("#usTable").jqGrid("getRowData",rowId);
                    //给标题的input设置内容
                   $("#titles").val(row.title);
                    //给作者的input设置内容
                   $("#guru_names").val(row.guru_name);

                    //给KindEditor设置值
                    KindEditor.html("#editor_id",row.content);

                    //给模态框设置两个按钮
                    $("#modalFooter").html("<button onclick='updateArticle(\""+rowId+"\")' class='btn btn-default'>修改文章</button>" +
                        "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>")

                    //展示模态框
                    $("#articlemodel").modal("show");
                }else{
                    alert("请先选中一条信息进行查看");
                }
            });

            //单击添加文章
            $("#btn2").click(function () {

                //重置表单
                $("#articleForm")[0].reset();
                //给KindEditor设置值
                KindEditor.html("#editor_id","");
                //展示模态框
                $("#articlemodel").modal("show");

                //给模态框设置两个按钮
                $("#modalFooter").html("<button onclick='addArticle()' class='btn btn-default'>添加</button>" +
                                        "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>")
            });

            /*点击触发模态框删除*/
            $("#btn3").click(function () {

                //选中一行展示        返回参数信息      最后行的id
                var rowId=$("#usTable").jqGrid("getGridParam","selrow");
                if(rowId!=null){

                    /*根据行id返回订行的数据*/
                    var row= $("#usTable").jqGrid("getRowData",rowId);
                    //给标题的input设置内容
                    $("#titles1").val("您确定删除id为>"+row.id+"<的文章信息吗?");

                    //给模态框设置两个按钮
                    $("#modalFooter1").html("<button onclick='deleteArticle(\""+rowId+"\")' class='btn btn-default'>确定</button>" +
                        "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>")

                    //展示模态框
                    $("#articlemodel1").modal("show");
                }else{
                    alert("请先选中一条信息进行删除");
                }
            });
        });

        //点击添加文章
        function addArticle(){
            $.ajax({
                url:"${path}/article/add",
                type:"post",
                datatype:"text",
                data:$("#articleForm").serialize(),
                success:function () {
                    //关闭模态框
                    $("#articlemodel").modal("hide");
                    //刷新表单页面
                    $("#usTable").trigger("reloadGrid");
                }
            });
        }

        //点击修改文章
        function updateArticle(id){
            $.ajax({
                url:"${path}/article/update?id="+id,
                type:"post",
                datatype:"text",
                data:$("#articleForm").serialize(),
                success:function () {
                    //关闭模态框
                    $("#articlemodel").modal("hide");
                    //刷新表单页面
                    $("#usTable").trigger("reloadGrid");
                }
            });
        }

        //点击却删除文章
        function deleteArticle(id){
            $.ajax({
                url:"${path}/article/delete?id="+id,
                type:"post",
                datatype:"json",
                data:$("#articleForm").serialize(),
                success:function () {
                    //关闭模态框
                    $("#articlemodel1").modal("hide");
                    //刷新表单页面
                    $("#usTable").trigger("reloadGrid");
                }
            });
        }
    </script>
</head>
<body>
<div class="panel panel-info">
    <%--面板--%>
    <div class="panel panel-heading">
        <h4>文章管理</h4>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>文章管理</a></li>
    </ul>

     <div class="panel panel-body">
        <button id="btn1" class="btn btn-success">文章查看</button>
        <button id="btn2" class="btn btn-info">文章添加</button>
        <button id="btn3" class="btn btn-danger">文章删除</button>
     </div>


    <%--初始化表单--%>
    <table id="usTable"/>
    <%--分页工具栏--%>
    <div id="uspager"/>
</div>

<%--查看文章模态框--%>
<div id="articlemodel" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content"  style="width:730px" >
           <%--模态框的标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">文章信息</h4>
            </div>
               <%--模态框的内容--%>
            <div class="modal-body">
                <%--表单--%>
                <form class="form-horizontal" id="articleForm">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">文章标题</span>
                        <input type="text" id="titles" name="title" class="form-control" />
                    </div><br>
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon2">文章作者</span>
                        <input type="text" id="guru_names" name="guru_name" class="form-control"/>
                    </div><br>
                    <div class="input-group">
                        <%--引入输入框--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"/>
                    </div>


                </form>
            </div>
               <%--模态框的按钮--%>
            <div class="modal-footer" id="modalFooter">

            </div>
        </div>
    </div>
</div>

<%--删除文章模提示框--%>
<div id="articlemodel1" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content"  style="width:730px" >
           <%--模态框的标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel1">文章删除</h4>
            </div>
               <%--模态框的内容--%>
            <div class="modal-body">
                <%--表单--%>
                <form class="form-horizontal" id="articleForm">
                    <div class="modal-header " align="center">
                        <input type="button" id="titles1" name="title" class="form-box" />
                    </div>


                </form>
            </div>
               <%--模态框的按钮--%>
            <div class="modal-footer" id="modalFooter1">

            </div>
        </div>
    </div>
</div>


</body>
</html>