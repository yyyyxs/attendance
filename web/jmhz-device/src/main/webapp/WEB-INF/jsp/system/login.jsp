<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="login.page.title" /></title>
    <meta name="description" content="Admin login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- inline styles related to this page -->
    <link href="${ctx}/assets/css/pages/login.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body {
            background: url(${ctx}/assets/img/background-jpeg.jpg) no-repeat;
        }
    </style>
</head>
<body class="login-layout">
    <div class="main-container">
        <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="space-6"></div>
                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border" style="margin-top: 180px;">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h3 class="header blue lighter">
                                            <i class="icon-coffee green"></i>
                                            <spring:message code="login.box.title" />
                                        </h3>
                                        <div class="space-6"></div>
                                        <div class="alert alert-danger display-hide" id="usernamePwdNull">
                                            <button class="close" onclick="close_error()"></button>
                                            <span><spring:message code="login.error.usernamePwdNull" /></span>
                                        </div>
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-danger">
                                                <button class="close" onclick="close_error()"></button>
                                                <span> ${message} </span>
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty exception}">
                                            <div class="alert alert-danger">
                                                <button class="close" onclick="close_error()"></button>
                                                <span> ${exception} </span>
                                            </div>
                                        </c:if>
                                        <form id="login-form" method="post" action="">
                                            <fieldset>
                                                <div class="form-group">
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-left">
                                                            <input type="text" class="form-control"
                                                                   placeholder="<spring:message code="login.placeholder.username" />" name="username" />
                                                            <i class="icon-user"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-left">
                                                            <input type="password" class="form-control"
                                                                   placeholder="<spring:message code="login.placeholder.password" />" name="password" />
                                                            <i class="icon-lock"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="space"></div>
                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input type="checkbox" class="ace" name="rememberMe" value="true"/>
                                                        <span class="lbl"><spring:message code="login.box.rememberMe" /></span>
                                                    </label>
                                                    <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                                                        <i class="icon-key"></i>
                                                        <spring:message code="login.button.name" />
                                                    </button>
                                                </div>
                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>
                                    </div>
                                    <!-- /widget-main -->
                                    <div class="toolbar clearfix">
                                        <div class="forget-password">
                                            <a href="${ctx}/masses/createAppeal"><spring:message code="login.toolbar.createAppeal" /></a>
                                           &nbsp; |
                                            <a href="${ctx}/masses/queryAppeal"><spring:message code="login.toolbar.queryAppeal" /></a>
                                            <%--<p> 忘记密码? 点击--%>
                                                <%--<a href="#" onclick="show_box('forgot-box'); return false;" id="forget-password"><b>这里</b></a>--%>
                                                <%--找回你的密码 </p>--%>
                                        </div>
                                    </div>
                                </div>
                                <!-- /widget-body -->
                            </div>
                            <div id="forgot-box" class="forgot-box widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header red lighter bigger"> <i class="icon-key"></i>
                                            <spring:message code="login.box.getPwd" />
                                        </h4>
                                        <div class="space-6"></div>
                                        <h5><spring:message code="login.forget.title" /></h5>
                                        <form id="forget-form" action="index.html" method="post">
                                            <fieldset>
                                                <div class="form-group">
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="email" class="form-control"
                                                                   placeholder="<spring:message code="login.placeholder.email" />" name="email" />
                                                            <i class="icon-envelope"></i>
                                                        </span>
                                                    </label>
                                                </div>
                                                <div class="space-10"></div>
                                                <div class="clearfix">
                                                    <button type="button" class="width-30 pull-left btn btn-sm btn-lighter"
                                                            onclick="show_box('login-box'); return false;">
                                                        <i class="icon-arrow-left icon-on-left"></i>
                                                        <spring:message code="global.back" />
                                                    </button>
                                                    <button type="submit" class="width-35 pull-right btn btn-sm btn-danger"> <i
                                                            class="icon-lightbulb"></i>
                                                        <spring:message code="login.forget.sendMail" />
                                                    </button>
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
        <%--技术支持--%>
        <div id="support" class="tech-support-login">
            <%@include file="/WEB-INF/jsp/common/tech-support.jsp" %>
        </div>
    </div>
    </div>
    <!-- basic scripts -->
    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${ctx}/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
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
    <script type="text/javascript" src="${ctx}/assets/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
    <!-- page specific plugin scripts -->
    <script type="text/javascript" src="${ctx}/assets/js/pages/login/loginFun.js"></script>
</body>
</html>