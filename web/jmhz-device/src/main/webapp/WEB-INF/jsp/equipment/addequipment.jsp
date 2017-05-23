<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>设备添加</title>
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
                        <a href="${ctx}/index"><spring:message code="global.homepage"/></a>
                    </li>

                    <li class="active">设备管理</li>
                    <li class="active">设备添加</li>
                </ul>
                <!-- .breadcrumb -->
            </div>

            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">

                                <form id="equipmentAdd" class="form-horizontal" role="form">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="6">
                                                <h1 style="margin-top:10px;">设备添加</h1>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <label class="control-label no-padding-right">&nbsp;设备名字：</label>
                                                <input class="input-small" type="text" placeholder="名字"
                                                       name="deviceName" id="deviceName" required="required">
                                                    <label class="control-label no-padding-right">&nbsp;品牌：</label>
                                                <input class="input-small" type="text" placeholder="如联想"
                                                           name="brand" id="brand">
                                            </td>

                                            <td colspan="3">
                                                <div class="form-group">


                                                    <label class="control-label no-padding-right">&nbsp;价格：</label>
                                                    <input class="input-small" type="text" placeholder="价格" name="price"
                                                           id="price" onkeyup="value=value.replace(/[^\d\.]/g,'')"
                                                           required="required">
                                                    <label class="control-label no-padding-right">&nbsp;购买时间：</label>
                                                    <input class=" date-picker col-xs-15 " id="buyTime" name="buyTime"
                                                           type="text" data-date-format="yyyy-mm-dd" value="<%=date%>"
                                                           readonly="readonly">
                                                    <input name="buyYear" type="hidden" id="buyYear" value="<%=year%>">
                                                    <label class="control-label no-padding-right">&nbsp;设备SN码：</label>
                                                    <input class="input-big" type="text" placeholder="SN码由英文和数字组成"
                                                           id="serialNumber"
                                                           onkeyup="value=value.replace(/[^0-9a-zA-Z]/g,'')"
                                                           required="required"/>
                                                </div>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <table width="100%">
                                                    <tr>
                                                        <td style="border-top:0;">设备状态</td>
                                                        <td style="border-top:0;">
                                                            <select class="form-control" name="status" id="status">
                                                                <option value="0" selected="selected">使用中</option>
                                                                <option value="1">废弃</option>
                                                                <option value="2">维修中</option>
                                                                <option value="3">升级中</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-top:0;">存放地点</td>
                                                        <td style="border-top:0;">
                                                            <select class="form-control" name="status" id="position">
                                                                <option value="0" selected="selected">301</option>
                                                                <option value="1">302</option>
                                                                <option value="2">303</option>
                                                                <option value="3">304</option>
                                                                <option value="3">305</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="border-top:0;">设备类型</td>
                                                        <td style="border-top:0;">
                                                            <select class="form-control" name="deviceType"
                                                                    id="deviceType">
                                                                <option value="0" selected="selected">公有</option>
                                                                <option value="1">私有</option>
                                                            </select>
                                                        </td>
                                                        <td style="border-top:0;">
                                                            <input class="input-small" type="text" placeholder="使用者"
                                                                   name="deviceUser" id="deviceUser">
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td class="center" colspan="1">配置信息</td>
                                            <%-- <td class="center" colspan="4">
                                                 <textarea name="configinfo" id="configinfo" class="form-control"
                                                           style="height: 80px;"></textarea>
                                             </td>--%>

                                            <td colspan="3">
                                                <table width="100%">
                                                    <tr>
                                                        <td style="border-top:0;">CPU:&nbsp;&nbsp;

                                                            <input class="input-medium" type="text" placeholder="CPU"
                                                                   name="CPU"
                                                                   id="CPU">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                            内存:&nbsp;&nbsp;

                                                            <input class="input-medium" type="text" placeholder="内存"
                                                                   name="internalMemory"
                                                                   id="internalMemory">
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td style="border-top:0;">显卡:&nbsp;&nbsp;

                                                            <input class="input-medium" type="text" placeholder="显卡"
                                                                   name="graphicsCard"
                                                                   id="graphicsCard">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                            信息:&nbsp;&nbsp;

                                                            <input class="input-medium" type="text" placeholder="其他配置信息"
                                                                   name="otherInfo"
                                                                   id="otherInfo">
                                                        </td>
                                                    </tr>

                                                </table>


                                            </td>

                                        </tr>

                                        <tr>
                                            <td colspan="6" class="center">
                                                <a href="javascript:history.back(-1)" class="btn btn-grey"> <i
                                                        class="icon-arrow-left"> </i> 返回 </a>
                                                <button class="btn btn-info" id="addEquipmentBtn" id="submit"
                                                        type="submit">
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
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/equipmentAdd.js" type="text/javascript"></script>

<script>
    jQuery(function ($) {

        //权限控制，镇级账号
       /* <c:if test="${user.auth_level != 2}">
        document.getElementById("addFarmerBtn").disabled = true;
        </c:if>*/

        // 日期插件
        $('#buyTime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
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
<script type="text/javascript">
    $('#buyTime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

</script>


</body>
</html>
