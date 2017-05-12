<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Login Page - Sima Studio</title>
    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- inline styles related to this page -->
    <style type="text/css">

    </style>
    <!-- page specific styles -->
    <link href="${ctx}/assets/css/pages/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1> <i class="icon-leaf green"></i> <span class="red">Charkey</span> <span class="white">Application</span> </h1>
                        <h4 class="blue">&copy; Sima</h4>
                    </div>
                    <div class="space-6"></div>
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger"> <i class="icon-coffee green"></i> Please Enter Your Information </h4>
                                    <div class="space-6"></div>
                                    <div class="alert alert-danger display-hide">
                                        <button class="close" onclick="close_error()"></button>
                                        <span> 请输入用户名密码 </span>
                                    </div>
                                    <form id="login-form" action="${ctx}/managerController/login" method="post">
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="block clearfix"> <span class="block input-icon input-icon-left"> <input type="text" class="form-control" placeholder="name" name="name" /> <i class="icon-user"></i> </span> </label>
                                            </div>
                                            <div class="form-group">
                                                <label class="block clearfix"> <span class="block input-icon input-icon-left"> <input type="password" class="form-control" placeholder="Password" name="password" /> <i class="icon-lock"></i> </span> </label>
                                            </div>
                                            <div class="space"></div>
                                            <div class="clearfix">
                                                <label class="inline"> <input type="checkbox" class="ace" name="remember" /> <span class="lbl"> Remember Me</span> </label>
                                                <button type="submit" class="width-35 pull-right btn btn-sm btn-primary"> <i class="icon-key"></i> Login </button>
                                            </div>
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- /widget-main -->
                                <div class="toolbar clearfix">
                                    <div class="forget-password">
                                        <h4>Forgot your password ?</h4>
                                        <p> Click <a href="#" onclick="show_box('forgot-box'); return false;" id="forget-password"><b>here</b></a> to reset your password. </p>
                                    </div>
                                </div>
                            </div>
                            <!-- /widget-body -->
                        </div>
                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger"> <i class="icon-key"></i> Retrieve Password </h4>
                                    <div class="space-6"></div>
                                    <p> Enter your email and to receive instructions </p>
                                    <form id="forget-form" action="index.html" method="post">
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="email" class="form-control" placeholder="Email" name="email" /> <i class="icon-envelope"></i> </span> </label>
                                            </div>
                                            <div class="space-10"></div>
                            <div class="clearfix">
                                <button type="button" class="width-30 pull-left btn btn-sm btn-lighter" onclick="show_box('login-box'); return false;"> <i class="icon-arrow-left icon-on-left"></i> Back </button>
                                <button type="submit" class="width-35 pull-right btn btn-sm btn-danger"> <i class="icon-lightbulb"></i> Send Me！ </button>
                            </div>
                            </fieldset>
                            </form>
                        </div>
                        <!-- /widget-main -->
                    </div>
                    <!-- /widget-body -->
                        </div>
                        <!-- /forgot-box -->
                    </div>
                    <!-- /position-relative -->
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </div>
</div>
<!-- /.main-container -->
<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='../../../assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='${ctx}/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="${ctx}/assets/js/page/login/loginFun.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
</body>
</html>