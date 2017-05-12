<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title><spring:message code="global.navMenu.user" /> - <spring:message code="global.subMenu.userView" /></title>

        <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <%--导入头部css--%>
        <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
        <!-- page specific plugin styles -->

        <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
        <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
        <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />

        <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
        <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
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
                                <a>添加用户</a>
                            </li>
                            <li class="active">用户信息</li>
                        </ul><!-- .breadcrumb -->
                    </div>
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <c:if test="${not empty msg}">
                                    <div class="alert alert-block alert-success" id="msgbox">
                                        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                        <p><strong><i class="icon-ok"></i>&nbsp;&nbsp;&nbsp;${msg}</strong></p>
                                    </div>
                                </c:if>
                                <c:if test="${not empty errorMsg}">
                                    <div class="alert alert-block alert-danger" id="msgbox">
                                        <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                        <p><strong><i class="icon-ok"></i>&nbsp;&nbsp;&nbsp;${errorMsg}</strong></p>
                                    </div>
                                </c:if>
                                <div id="tableDiv">
                                    <!--This is the table.-->
                                    <table id="grid-table"></table>
                                    <!--This is the pager.-->
                                    <div id="grid-pager"></div>
                                </div>

                                <script type="text/javascript">
                                    var $path_base = "/";//this will be used in gritter alerts containing images
                                </script>

                                <!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div><!-- /.main-content -->

                <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
            </div><!-- /.main-container-inner -->

            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="icon-double-angle-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->


        <%--导入尾部js--%>
        <%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
        <!-- page specific plugin scripts -->

        <script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
        <script src="${ctx}/assets/js/bootbox.min.js"></script>
        <script src="${ctx}/assets/js/pages/system/user/userFun.js"></script>
    </body>
</html>
