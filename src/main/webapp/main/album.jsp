<%@page pageEncoding="UTF-8"  contentType="text/html;UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script>
        $(function () {
            jQuery("#abTable").jqGrid({
                url : "${path}/album/showAll",
                editurl:"${path}/album/edit",
                datatype : "json",
                rowNum : 5,
                pageNum: 1,
                rowList : [ 3,6,9 ],
                pager : '#abpage',
                viewrecords : true,
                styleUI:"Bootstrap",
                height : "auto",
                autowidth:true,
                multiselect : false,  //多选
                colNames : [ 'Id', '标题', '封面', '评分', '作者','播音', '集数 ', '内容 ', '发布日期 ' ],
                colModel : [
                    {name : 'id',index : 'id',  width : 140,align : "center"},
                    {name : 'title',editable:true,index : 'title',width : 80,align : "center"},
                    {name : 'cover_img',editable:true,edittype:"file",index : 'invdate',width : 120,align : "center",
                        formatter:function (cellvalue,options,rowObject) {
                            return"<img src='${path}/upload/image1/"+cellvalue+"' style='width:150px;height:80px'/>";
                        }
                    },
                    {name : 'score',editable:true,index : 'score',width : 70,align : "right",align : "center"},
                    {name : 'author',editable:true,index : 'author',width : 75,align : "right",align : "center"},
                    {name : 'broadcast',editable:true,index : 'broadcast',width : 75,align : "right",align : "center"},
                    {name : 'counts',editable:true,index : 'counts',width : 50,sortable : false,align : "center"},
                    {name : 'content',editable:true,index : 'content',width : 100,sortable : false,align : "center"},
                    {name : 'pub_date',index : 'pub_date',width : 80,sortable : false,align : "center"}
                ],
                subGrid : true,   //是否开启子表格
                /*父表格中容纳子表格   点击行的id*/
                subGridRowExpanded : function(subgrid_id, row_id) {
                    addSubGrid(subgrid_id,row_id);
                }
            });

            //父表格 增删改查操作操作
            jQuery("#abTable").jqGrid('navGrid', '#abpage', {add : true,edit : true,del : true},
                {
                    closeAfterEdit:true,//关闭添加框
                },//修改后的操作
                {
                    closeAfterAdd:true,//关闭添加框
                    afterSubmit:function (data) {
                        //文件上传
                        $.ajaxFileUpload({
                            url:"${path}/album/uploadAlbum",
                            type:"post",
                            datatype:"json",
                            data:{id:data.responseText}, //获取id
                            fileElementId:"cover_img",//需要上传文件的文件域id,既<input type="file">的id
                            success:function () {
                                //刷新表单
                                $("#abTable").trigger("reloadGrid");
                            }
                        });
                        return "abTable";
                    }
                },//添加后的操作
                {}
            );
            return "a";
        });
        //子表格
        function addSubGrid(subgridId, rowId) {
            var subgridTableId = subgridId + "Table";
            var pagerId= subgridId+"Page";

            $("#" + subgridId).html("<table id='" + subgridTableId+
                "'/>"+"<div id='" + pagerId + "'/>");

            //子表格
            $("#" + subgridTableId).jqGrid({
                //url : "" + rowId,
                url:"${path}/chapter/showAll?rowId="+rowId,
                editurl:"${path}/chapter/edit",
                rowNum : 20,
                rowList : [ 3,6,9 ],
                pager : "#"+pagerId,
                sortname : 'num',
                sortorder : "asc",
                styleUI:"Bootstrap",
                height : "auto",
                viewrecords : true,
                autowidth:true,
                datatype : "json",
                colNames : [ 'Id', '名字', '音频', '时长','上传时间','大小','所属专辑','操作' ],
                colModel : [
                    {name : "id",  index : "id",width : 140,key : true,align : "center"},
                    {name : "title",editable:true,index : "title",  width : 70,align : "center"},
                    {name : "src",editable:true,edittype:"file",index : "src",width : 70,align : "center"},
                    {name : "duration",index : "duration",width : 60,align : "center"},
                    {name : "upload_time",index : "upload_time",width : 50,align : "center"},
                    {name : "size",index : "size",width : 50,align : "center"},
                    {name : "album_id",index : "album_id",width : 50,sortable : false,align : "center"},
                    {name : "handle",width : 60,align : "center",
                        formatter:function(cellvalue) {
                            return"<a href='#' onclick='auDownloag(\""+cellvalue+"\")'><span class='glyphicon glyphicon-download'/></a>&nbsp;&nbsp;" +//下载
                                    "<a href='#' onclick='auPlay(\""+cellvalue+"\")'><span class='glyphicon glyphicon-play-circle'/></a>"          //播放
                        }
                    }
                ]
            });
            //子表格的增删改查操作
            $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true},
                {
                    closeAfterEdit:true,//关闭添加框
                },//修改后的操作
                {
                    closeAfterAdd:true,//关闭添加框
                    afterSubmit:function (data) {
                        //文件上传
                        $.ajaxFileUpload({
                            url:"${path}/chapter/uploadChapter",
                            type:"post",
                            datatype:"json",
                            data:{id:data.responseText}, //获取id
                            fileElementId:"src",//需要上传文件的文件域id,既<input type="file">的id
                            success:function () {
                                //刷新表单
                                $("#" + subgridTableId).trigger("reloadGrid");
                            }
                        });
                        return "abTable";
                    }
                },//添加后的操作
                {}//删除后的操作
            );
        }

        //下载
        function auDownloag(dname) {
            location.href="${path}/chapter/auDownloag?dname="+dname;
        }
        //播放
        function auPlay() {


        }

        
    </script>


</head>
<body>
<div class="panel panel-info">
    <%--面板--%>
    <div class="panel panel-heading">
        <h4>专辑管理</h4>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a>专辑展示</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="abTable"/>
    <%--分页工具栏--%>
    <div id="abpage"/>
</div>
</body>
</html>