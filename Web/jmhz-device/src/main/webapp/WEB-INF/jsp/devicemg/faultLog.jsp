<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>故障处理—维修报告</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
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
                        <a href="${ctx}/"><spring:message code="global.homepage"/></a>
                    </li>

                    <li>
                        <a>故障处理</a>
                    </li>
                    <li class="active">维修报告</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <form name="faultDeviceLog" id="faultDeviceLog">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="8">
                                                <h1 style="margin-top:10px;">维修报告</h1>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center col-xs-2" rowspan="2">设备基本信息</td>
                                            <td class="center col-sm-1">
                                                维修记录id
                                            </td>
                                            <td>
                                                <input type="text" name="applyId"
                                                       id="applyId" value="${apply.id}">
                                            </td>
                                            <td class="center col-sm-1">
                                                设备名字
                                            </td>
                                            <td>
                                                <input type="text" name="deviceName"
                                                       id="deviceName" value="${apply.deviceName}">
                                            </td>
                                        </tr>
                                        <td class="center col-sm-1">
                                            设备类型
                                        </td>
                                        <td class="center" style="width:10%">
                                            <select class="form-control" name="deviceType" id="deviceType">
                                                <c:choose>
                                                    <c:when test="${apply.deviceType == 0}">
                                                        <option value="0" selected="selected">公有</option>
                                                        <option value="1">私有</option>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <<option value="0">公有</option>
                                                        <option value="1" selected="selected">私有</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                        <td class="center col-sm-1">设备使用者</td>
                                        <td>
                                            <input type="text" name="deviceUser"
                                                   id="deviceUser" value="${apply.deviceUser}">
                                        </td>
                                        <tr>
                                            <td class="center col-xs-2" rowspan="2">维修信息</td>
                                            <td class="center col-sm-1">维修状态</td>
                                            <td class="center col-sm-1">
                                                <select class="form-control" name="dealStatus" id="dealStatus">
                                                    <option value="0">未维修</option>
                                                    <option value="1">维修中</option>
                                                    <option value="2" selected="selected">已维修</option>
                                                </select>
                                            </td>
                                            <td class="center">维修部件</td>
                                            <td class="center" colspan=2>
                                            <textarea name="repairpart" class="form-control"
                                                      id="repairpart" placeholder="修理设备的哪些部件"></textarea>
                                            </td>

                                        </tr>
                                        <td class="center">花费</td>
                                        <td>
                                            <input type="text" name="cost"
                                                   id="cost" placeholder="维修设备所花金额">
                                        </td>
                                        <td class="center">维修结果</td>
                                        <td class="center" colspan="4">
                                                <textarea name="repairResult" id="repairResult"
                                                          class="form-control" style="height: 80px;"
                                                          placeholder="设备维修结果描述以及备注"></textarea>
                                        </td>
                                        <%--<input id="id" name="id" type="hidden" value=>--%>

                                        <tr>
                                            <td colspan="8" class="center">
                                                <a href="javascript:history.back(-1)" class="btn btn-grey"> <i
                                                        class="icon-arrow-left"> </i> 返回 </a>
                                                <button class="btn btn-info" id="addDeviceBtn">
                                                    <i class="icon-ok bigger-110"></i>
                                                    提交
                                                </button>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>

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

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- page specific plugin scripts -->
<!--日期插件js-->
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<!--限制输入字数js-->
<script src="${ctx}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<!--限制输入格式js-->
<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>
<!-- inline scripts related to this page -->
<script src="${ctx}/assets/js/jquery-cxselect/jquery.cxselect.min.js"></script>
<script src="${ctx}/assets/js/jquery-cxselect/cityData.min.jsonData.js"></script>
<script src="${ctx}/assets/js/faultLogAdd.js" type="text/javascript"></script>
<!-- inline scripts related to this page -->



</body>
</html>
