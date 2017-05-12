<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>添加设备</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(new Date());
    %>
    <%
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(new Date());
    %>
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
                        <a>设备管理</a>
                    </li>
                    <li class="active">添加设备</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <form name="deviceAdd" id="deviceAdd" role="form">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="11">
                                                <h1 style="margin-top:10px;">添加设备</h1>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class=" center col-sm-2" rowspan="2" colspan="2">设备基本信息</td>
                                            <td class=" center col-sm-1" colspan="1">
                                                设备名字
                                            </td>
                                            <td class=" center col-sm-2" colspan="2">
                                                <input type="text" name="deviceName"
                                                       id="deviceName" required="required">
                                            </td>
                                            <td class="center col-sm-1" colspan="1">
                                                购入价格
                                            </td>
                                            <td class="center col-sm-2" colspan="2">
                                                <input type="text" name="price"
                                                       id="price" onkeyup="value=value.replace(/[^\d\.]/g,'')"
                                                       required="required">
                                            </td>
                                            <td class="center col-sm-1" colspan="1">购买时间</td>
                                            <td class="center col-sm-2" colspan="2">
                                                <input class=" date-picker  " id="buyTime" name="buyTime"
                                                       type="date" data-date-format="yyyy-mm-dd" value="<%=date%>">

                                            </td>

                                        </tr>
                                        <td class="center col-sm-1" colspan="1">设备所在位置</td>
                                        <td class="center col-sm-2" colspan="2">
                                            <select class="form-control" name="status" id="position" id="position">
                                                <option value="0" selected="selected">301</option>
                                                <option value="1">302</option>
                                                <option value="2">303</option>
                                                <option value="3">304</option>
                                                <option value="4">305</option>
                                            </select>
                                            </td>
                                        <td class="center col-sm-1" colspan="1">设备使用者</td>
                                        <td class="center col-sm-2" colspan="2">
                                            <input type="text" name="deviceUser"
                                                   id="deviceUser" readonly=true value=${username}>
                                            </td>
                                        <td class="center col-sm-1" colspan="1">设备状态</td>
                                        <td class="center col-sm-2" style="width:10%" colspan="2">
                                            <select class="form-control" name="status" id="status" id="status">
                                                <option value="0" selected="selected">使用中</option>
                                                <option value="1">废弃</option>
                                                <option value="2">维修中</option>
                                                <option value="3">升级中</option>
                                            </select>
                                            </td>
                                        <tr>
                                            <td class=" center col-sm-2" rowspan="2" colspan="2">设备其他信息</td>
                                            <td class="center col-sm-1" rowspan="1" colspan="1">设备品牌</td>
                                            <td class="center col-sm-1" rowspan="1" colspan="2">
                                                <input type="text" name="brand" id="brand" placeholder="如联想">
                                            </td>
                                            <td class="center col-sm-2" rowspan="2" colspan="2">设备配置信息</td>
                                            <td class="center col-sm-1" colspan="1">CPU</td>
                                            <td class="center col-sm-1" colspan="1">
                                                <input type="text" name="CPU" id="CPU" placeholder="个人电脑CPU型号">
                                            </td>
                                            <td class="center col-sm-1" colspan="1">内存</td>
                                            <td class="center col-sm-1" colspan="1">
                                                <input type="text" name="internalMemory" id="internalMemory"
                                                       placeholder="个人电脑内存大小">
                                            </td>
                                        </tr>
                                        <td class="center col-sm-1" rowspan="1" colspan="1">设备SN码</td>
                                        <td class="center col-sm-1" rowspan="1" colspan="2">
                                            <input type="text" name="serialNumber" id="serialNumber"
                                                   placeholder="SN码由英文和数字组成"
                                                   onkeyup="value=value.replace(/[^0-9a-zA-Z]/g,'')"
                                                   required="required">
                                        </td>
                                        <td class="center col-sm-1" colspan="1">显卡</td>
                                        <td class="center col-sm-1" colspan="1">
                                            <input type="text" name="graphicsCard" id="graphicsCard"
                                                   placeholder="个人电脑显卡型号">
                                        </td>
                                        <td class="center col-sm-1" colspan="1">其他信息</td>
                                        <td class="center col-sm-1" colspan="1">
                                            <input type="text" name="otherInfo"
                                                   id="otherInfo">
                                        </td>
                                        <input name="buyYear" type="hidden" id="buyYear" value="<%=year%>">
                                        <tr>
                                            <td colspan="11" class="center">
                                                <a href="javascript:history.back(-1)" class="btn btn-grey"> <i
                                                        class="icon-arrow-left"> </i> 返回 </a>
                                                <button class="btn btn-info" id="addDeviceBtn" type="submit">
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
<script src="${ctx}/assets/js/deviceAdd.js" type="text/javascript"></script>
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
<!-- inline scripts related to this page -->
<script>
    jQuery(function ($) {

        //权限控制，镇级账号
        <c:if test="${user.auth_level != 2}">
        document.getElementById("addFarmerBtn").disabled = true;
        </c:if>

        // 日期插件
        $('#visittime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        $('#birthday').mask('9999年99月');
        //下面是cxselect的配置
        //先引入数据和cxselect的js，jquery最先引入
        //然后是下面的配置

        //设置全局默认值，引入jquery.cxselect.js之后，调用之前设置
        $.cxSelect.defaults.url = "${ctx}/assets/js/jquery-cxselect/cityData.min.jsonData.js";
        $.cxSelect.defaults.nodata = "none";

        // selects 为数组形式，请注意顺序
        $("#snackLocation").cxSelect({
            selects: ["province", "city", "area"],
            nodata: "none"
        });
        $("#workLocation").cxSelect({
            selects: ["province", "city", "area"],
            nodata: "none"
        });
        //上面是cxselect的配置
    });
    </script>



</body>
</html>
