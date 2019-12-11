<%@page pageEncoding="UTF-8" isELIgnored="false"  contentType="text/html;UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="nav navbar-nav">.
                    <li><a class="navbar-brand" href="#">持明法洲管理系统</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎:${login.username}</a></li>
                    <li><a href="${path}/admin/logs">退出登录<span class="glyphicon glyphicon-remove-sign"></span></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!--栅格系统-->
    <div class="row">
        <div class="col-sm-6 col-md-12">
            <div class="col-sm-6 col-md-2">
                <!--左边手风琴部分-->
                <!--1-->
                <div class="panel-group" id="accordion" align="center">
                    <div class="panel panel-warning">
                        <div class="panel-heading" id="headingOne">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body" role="button">
                                    <ul class="nav nav-pills nav-stacked">
                                        <li>
                                            <button class=" btn btn-warning">
                                                <a href="javascript:$('#mainId').load('${path}/main/user.jsp')">用户信息</a>
                                            </button>
                                        </li><br>
                                        <li>
                                            <button class=" btn btn-warning">
                                                <a href="javascript:$('#mainId').load('${path}/main/userstatis.jsp')">用户统计</a>
                                            </button>
                                        </li><br>
                                        <li>
                                            <button class=" btn btn-warning">
                                                <a href="javascript:$('#mainId').load('${path}/main/usersdistr.jsp')">用户分布</a>
                                            </button>
                                        </li>
                                    </ul>
                            </div>
                        </div>
                    </div><br>
                    <!--2-->
                    <div class="panel panel-success">
                        <div class="panel-heading" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class=" btn btn-success">
                                            <a href="javascript:$('#mainId').load('${path}/main/banner.jsp')">轮播图展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div><br>
                    <!--3-->
                    <div class="panel panel-danger">
                        <div class="panel-heading" id="headingThree">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class=" btn btn-danger">
                                            <a href="javascript:$('#mainId').load('${path}/main/album.jsp')">专辑展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div><br>
                    <!--4-->
                    <div class="panel panel-warning">
                        <div class="panel-heading" id="headingFive">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" >
                                    文章管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class=" btn btn-warning">
                                            <a href="javascript:$('#mainId').load('${path}/main/article.jsp')">文章展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div><br>
                    <%--5--%>
                    <div class="panel panel-info">
                        <div class="panel-heading" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" >
                                    上师管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class=" btn btn-info">
                                            <a href="javascript:$('#mainId').load('${path}/main/guru.jsp')">上师展示</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div><br>
                    <%--6--%>
                    <div class="panel panel-default">
                        <div class="panel-heading" id="headingSix">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" >
                                    管理员管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseSix" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav nav-pills nav-stacked">
                                    <li>
                                        <button class=" btn btn-default">
                                            <a href="javascript:$('#mainId').load('${path}/main/admin.jsp')">管理员信息</a>
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-md-10" id="mainId">
                <!--巨幕开始-->
                <div class="jumbotron" align="center">
                    <h3>欢迎来到持明法洲后台管理系统</h3>
                </div>
                <!--右边轮播图部分-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- 图片数 -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                    </ol>
                    <!--将图片包裹-->
                    <div class="carousel-inner">
                        <%--1--%>
                        <div class="item active"  >
                            <img src="${path}/bootstrap/img/shouye.jpg" />
                        </div>
                        <%--2--%>
                        <div class="item">
                            <img src="${path}/bootstrap/img/1.png" height="50%" width="100%"/>
                        </div>
                        <%--3--%>
                        <div class="item">
                            <img src="${path}/bootstrap/img/2.png" height="50%" width="100%"/>
                        </div>
                        <%--3--%>
                        <div class="item">
                            <img src="${path}/bootstrap/img/3.png" height="50%" width="100%"/>
                        </div>
                        <%--3--%>
                        <div class="item">
                            <img src="${path}/bootstrap/img/4.png" height="50%" width="100%"/>
                        </div>
                    </div>
                    <!-- 箭头 -->
                    <a class="left carousel-control" href="${path}/#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <!--页脚-->
                <div align="center">
                    <h4><small>@百知教育 @持明法洲</small></h4>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
