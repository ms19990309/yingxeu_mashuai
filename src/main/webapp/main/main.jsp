<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
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
    <!--导航条-->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${path}/main/main.jsp">应学视频APP后台管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <span class="navbar-brand"><span style="color: #c0c0c0">欢迎:</span><span class="text-primary">${sessionScope.admin.username}</span></span>

                    <a class="navbar-brand" href="${path}/adminController/logout">退出登陆<span class="glyphicon glyphicon-log-out"></span></a>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <!--栅格系统-->
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-2" style="padding-left: 40px">
                <!--左边手风琴部分-->
                <!--菜单-->
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                    <!--面板-->
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <h3 style="text-align:center;">用户管理</h3>
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <ul class="list-group text-center">
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/user/showUser.jsp')">用户信息</a></li>
                                    <br>
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/user/testEchartsJsons.jsp')">用户统计</a></li>
                                    <br>
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/user/testChina.jsp')">用户分布</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <!--面板-->
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                    <h3 style="text-align:center;">分类管理</h3>
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <ul class="list-group text-center">
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/category/showCategory.jsp')">分类展示</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <!--面板-->
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                                    <h3 style="text-align:center;">视频管理</h3>
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <ul class="list-group text-center">
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/video/showVideo.jsp')">视频展示</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <!--面板-->
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
                                    <h3 style="text-align:center;">日志管理</h3>
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <ul class="list-group text-center">
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/log/showLog.jsp')">日志详情</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>



                    <!--面板-->
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
                                    <h3 style="text-align:center;">反馈管理</h3>
                                </a>
                            </h4>
                        </div>
                        <!--面板内容-->
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                            <div class="panel-body">
                                <ul class="list-group text-center">
                                    <li class="list-group-item"><a href="javascript:$('#content').load('${path}/feedback/showfeedback.jsp')">反馈详情</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>

            </div>


            <div class="col-md-10" style="padding-right: 40px;" id="content">


                <div class="container-fluid">

                <!--巨幕开始-->
                <div class="container-fluid">
                    <div class="row">

                            <div class="jumbotron">
                                <h1>欢迎来到应学视频APP后台管理系统</h1>
                            </div>

                    </div>
                </div>

                <!--右边轮播图部分-->
                <!--创建轮播图-->

                    <div id="carousel-example-generic" style="width: 1130px;" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="5"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="${path}/bootstrap/foodimgs/1.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/foodimgs/2.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/foodimgs/3.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/foodimgs/4.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/foodimgs/5.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>
                            <div class="item">
                                <img src="${path}/bootstrap/foodimgs/6.jpg" style="height: 450px; width: 1130px;" alt="...">
                                <div class="carousel-caption">
                                    <h3></h3>
                                    <p></p>
                                </div>
                            </div>

                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        </a>

                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        </a>

                    </div>
                </div>
            </div>

        </div>
    </div>

    <!--栅格系统-->


    <nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top: 15px">
        <div class="container" style="text-align: center">
            <p>&copy;巧夺天工&reg;科技有限公司</p >
        </div>
    </nav>

</body>
</html>
