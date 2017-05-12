<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title><spring:message code="global.navMenu.appeal" /> - <spring:message code="global.subMenu.appealSearch" /></title>
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
                                <a><spring:message code="global.navMenu.appeal" /></a>
                            </li>
                            <li class="active"><spring:message code="global.subMenu.appealSearch" /></li>
                        </ul><!-- .breadcrumb -->
                    </div>
                    <div class="page-content">
                        <div class="row">
                            <div class="space-10"></div>
                            <table width="100%" style="border: 1px dotted #e2e2e2">
                                <tbody>
                                    <tr>
                                        <td width="15%"></td>
                                        <td>
                                            <div style="padding-top:5px;">
                                                <label>
                                                    <input name="doingstatus" type="radio" class="ace" value="0" checked="checked"/>
                                                    <span class="lbl">&nbsp;立学立改项目&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                </label>
                                            </div></td>
                                        <td>
                                            <div style="padding-top:5px;">
                                                <label>
                                                    <input name="doingstatus" type="radio" class="ace" value="1" />
                                                    <span class="lbl"> &nbsp;短期整改项目</span>
                                                </label>
                                            </div></td>
                                        <td>
                                            <div style="padding-top:5px;">
                                                <label>
                                                    <input name="doingstatus" type="radio" class="ace" value="2" />
                                                    <span class="lbl"> &nbsp;中长期整改项目</span>
                                                </label>
                                            </div></td>

                                        <td>
                                            <div style="padding-top:5px;">
                                                <label>
                                                    <button class="btn btn-info" id="queryAppealBtn">
                                                        <i class="icon-ok bigger-110"></i>
                                                        查询
                                                    </button>
                                                </label>
                                            </div></td>
                                    </tr>
                                </tbody>
                            </table>

                            <div class="space-10"></div>
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
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
        <script src="${ctx}/assets/js/pages/appeal/queryAppealFun.js" type="text/javascript"></script>
    </body>
</html>
