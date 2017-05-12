<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.homepage" /></title>
    <meta name="description" content="index page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>
<body>
<%--导入头部banner--%>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="main-container-inner">
        <%--导入左边导航菜单--%>
        <%@include file="/WEB-INF/jsp/common/sidebar.jsp" %>
        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>
                <ul class="breadcrumb">
                    <li><i class="icon-home home-icon"></i> <a href="${ctx}/"><spring:message code="global.homepage" /></a></li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="page-header center">
                            <h1>欢迎使用实验室管理系统！</h1>
                        </div>
                        <div class="toolbar clearfix pull-right">
                            <div class="forget-password">
                                <a href="${ctx}/masses/createAppeal"></a> |
                                <a href="${ctx}/masses/queryAppeal"></a>
                                <%--<p> 忘记密码? 点击--%>
                                <%--<a href="#" onclick="show_box('forgot-box'); return false;" id="forget-password"><b>这里</b></a>--%>
                                <%--找回你的密码 </p>--%>
                            </div>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>
                <div class="tech-support-index">
                    <%--技术支持--%>
                    <%@include file="/WEB-INF/jsp/common/tech-support.jsp" %>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->

        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
</body>
</html>