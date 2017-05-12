<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.appeal" /> - <spring:message code="global.subMenu.appealCreate" /></title>
    <meta name="description" content="" />
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
                    <li><a><spring:message code="global.navMenu.appeal" /></a></li>
                    <li class="active">添加诉求</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="space-10"></div>
                        <div class="space-10"></div>
                        <div class="space-10"></div>
                        <form id="verifyForm" action="${ctx}/appeal/verify" class="form-horizontal" role="form" method="post">

                            <div class="form-group">
                                <label class="col-sm-5 control-label no-padding-right">诉求者/集体名</label>
                                <div class="col-sm-7">
											<span class="input-icon">
												<input type="text" id="name" name="name" />
												<i class="icon-user blue"></i>
											</span>
                                </div>
                                <label class="col-sm-5 control-label no-padding-right"></label>
                                <div class="col-sm-7">
											<span class="input-icon">

											</span>
                                </div>
                                <label class="col-sm-5 control-label no-padding-right">诉求者联系号码(选填)</label>
                                <div class="col-sm-7">
											<span class="input-icon">
												<input type="text" id="phone" name="phone" />
												<i class="icon-phone blue"></i>
											</span>
                                </div>
                            </div>

                            <div class="space-10"></div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right">类型</label>

                                    <div class="col-sm-7">
                                        <label>
                                            <input name="proposer" type="radio" class="ace" value="0" checked="checked"/>
                                            <span class="lbl">&nbsp;个人诉求&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                        <label>
                                            <input name="proposer" type="radio" class="ace" value="1" />
                                            <span class="lbl"> &nbsp;集体诉求</span>
                                        </label>
                                    </div>

                                </div>
                            <div class="space-10"></div>

                            <div class="clearfix form-actions center">
                                <div>
                                    <button class="btn btn-info" type="submit">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>

                                    &nbsp; &nbsp; &nbsp;
                                    <button class="btn" type="reset">
                                        <i class="icon-undo bigger-110"></i>
                                        重置
                                    </button>
                                </div>
                            </div>
                        </form>


                        <div class="page-content">
                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
            <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i> </a>
</div>
<!-- /.main-container -->
    <%--导入尾部js--%>
    <%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
    <script src="${ctx}/assets/js/bootbox.min.js"></script>
    <script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/js/pages/appeal/addAppealVerify.js" type="text/javascript"></script>
</body>
</html>