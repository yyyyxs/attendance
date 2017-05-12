<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Blank Page - Ace Admin</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <style>
        .ace-nav > li.open > a {
            background-color: #F12B2B !important
        }
    </style>
    <div class="navbar-container" id="navbar-container">
<span style="float: left;margin-top:5px;margin-left: 5px;">
    <img src="${ctx}/assets/images/logo.png" />
</span>

        <div class="navbar-header pull-left">
            <a class="navbar-brand" style="margin-left: 10px;">
        <span style="font-size: 40px;line-height: 60px;font-family:'楷体','Helvetica Neue Light', 'Lucida Grande', 'Calibri', 'Arial', 'sans-serif';">
            高桥镇联系服务群众民情工作平台
        </span>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="main-container-inner">
        <%--导入左边导航菜单--%>
        <div class="main-content" style="margin: 0;">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <ul class="breadcrumb">
                    <li><i class="icon-home home-icon"></i> <a>群众诉求查询</a></li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="page-header">
                            <h1> 群众诉求查询<small><i class="icon-double-angle-right"></i> 诉求详情 </small></h1>
                        </div>
                        PAGE CONTENT
                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
    </div>
    <!-- /.main-container-inner -->
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">
jQuery(function ($) {

});
</script>
</body>
</html>