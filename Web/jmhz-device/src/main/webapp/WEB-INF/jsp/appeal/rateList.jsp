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
    <title><spring:message code="global.navMenu.appeal" /> - <spring:message code="global.subMenu.appealRate" /></title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
    <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
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
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="${ctx}/"><spring:message code="global.homepage" /></a>
                    </li>
                    <li>
                        <a><spring:message code="global.navMenu.appeal" /></a>
                    </li>
                    <li class="active"><spring:message code="global.subMenu.appealRate" /></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div id="tableDiv">
                            <%--因为js中不能解析el表达式${appealId}，因此在此设置一个隐藏域存放appealid--%>
                            <input id="appealid" name="appealid" value="${appealId}" hidden="hidden">
                            <!--This is the table.-->
                            <table id="grid-table"></table>
                            <!--This is the pager.-->
                            <div id="grid-pager"></div>
                        </div>
                        <script type="text/javascript">
                            var $path_base = "/";//this will be used in gritter alerts containing images
                        </script>
                        <div id="appealRateDialog" class="hide">
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th colspan="5">
                                        <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                            修改本诉求处理结果评价：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        <div class="radio">
                                            <label>
                                                <input id="rateLevel4" name="rateLevel" strname="满意" type="radio" class="ace" value="4" />
                                                <span class="lbl">&nbsp;满意</span>
                                            </label>
                                        </div>
                                    </td>
                                    <td width="20%">
                                        <div class="radio">
                                            <label>
                                                <input id="rateLevel3" name="rateLevel" strname="基本满意" type="radio" class="ace" value="3" />
                                                <span class="lbl">&nbsp;基本满意</span>
                                            </label>
                                        </div>
                                    </td>
                                    <td width="20%">
                                        <div class="radio">
                                            <label>
                                                <input id="rateLevel2" name="rateLevel" strname="一般" type="radio" class="ace" value="2" />
                                                <span class="lbl">&nbsp;一般</span>
                                            </label>
                                        </div>
                                    </td>
                                    <td width="20%">
                                        <div class="radio">
                                            <label>
                                                <input id="rateLevel1" name="rateLevel" strname="不满意" type="radio" class="ace" value="1" />
                                                <span class="lbl">&nbsp;不满意</span>
                                            </label>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr style="margin: 0;" />
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th>
                                        <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                            修改评价内容：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="space-6"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea id="rateContent" name="rateContent" class="autosize-transition form-control"></textarea>
                                        <%--<span id="errorNotRate" style="color: #d16e6c;line-height: 20px;font-size:15px;" class="hide">请输入诉求评价！</span>--%>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr style="margin: 0;" />
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th>
                                        <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                            修改评价时间：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="space-6"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="date-picker col-xs-5" id="ratedate" name="ratedate" type="text" data-date-format="yyyy-mm-dd">
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                        <div id="delRateDialog" class="hide">
                            <div class="alert alert-info bigger-110">
                                若是点击<b>[确认删除]</b><br />将会删除本诉求评价！
                            </div>
                        </div>
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
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>
<!--日期插件js-->
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/js/pages/appeal/rateListFun.js" type="text/javascript"></script>
</body>
</html>
