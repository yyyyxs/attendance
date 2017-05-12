<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.user" /> - <spring:message code="global.subMenu.userDelete" /></title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>
<body>
<%--导入头部banner--%>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>
    <div class="main-container-inner">
        <%--导入左边导航菜单--%>
        <%@include file="/WEB-INF/jsp/common/sidebar.jsp" %>
        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="${ctx}/"><spring:message code="global.homepage" /></a>
                    </li>
                    <li>
                        <a>用户管理</a>
                    </li>
                    <li class="active">删除用户</li>
                </ul><!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <%--修改--%>
                        <div class="" id="modifyRoleDiv">
                            <div class="space-10"></div>
                            <form class="form-horizontal" role="form" action="${ctx}/user/delete/${currentUser.id}" method="post">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="id"> 用户ID </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="id" name="id" value="${currentUser.id}" readonly>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="username"> 用户名 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="username" name="username" value="${currentUser.username}" readonly>
                                    </div>
                                </div>
                                <div class="space-4"></div>

                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button"
                                                onclick="javascrtpt:window.location.href='${ctx}/user/showUser'">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-danger" type="submit" id="amodRoleSubmitBtn">
                                            <i class="icon-warning-sign bigger-110"></i>
                                            确认修改
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
            <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
            <!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script type="text/javascript">
    //权限控制，不能删除自己
    <c:out value="${user.id}"/>
    <c:out value="${currentUser.id}"/>

    <%--不能删除自己的账户--%>
    <c:if test="${user.id == currentUser.id}">
        document.getElementById("amodRoleSubmitBtn").remove();
    </c:if>
    <%--镇级账户没有删除的权限--%>
    <c:if test="${user.auth_level == 2}">
        document.getElementById("amodRoleSubmitBtn").remove();
    </c:if>

    <%--县市账户可以删除其他的账户--%>
    <c:if test="${user.auth_level == 1}">
        <c:if test="${user.id == currentUser.id}">
            $("#newPassword").attr("disabled", "disabled");
            document.getElementById("amodRoleSubmitBtn").remove();
        </c:if>
    </c:if>

</script>
</body>
</html>
