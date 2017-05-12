<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>升级申报</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
</head>
<%
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String date = format.format(new Date());
%>
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

                    <%-- <c:choose>
                         <c:when test="${source == 0}">
                             <li>
                                 <a> 短信诉求 </a>
                             </li>
                         </c:when>
                         <c:when test="${source == 1}">
                             <li>
                                 <a> 热线诉求 </a>
                             </li>
                         </c:when>
                     </c:choose>--%>
                    <li class="active">升级处理</li>
                    <li class="active">升级申报</li>
                </ul>
                <!-- .breadcrumb -->
            </div>


            <script type="text/javascript">
                SimpleDateFormat
                format = new SimpleDateFormat("yyyy-MM-dd");
                String
                date = format.format(new Date());
            </script>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">

                                <form name="upgradeApply" id="upgradeApply">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="6">
                                                <h1 style="margin-top:10px;">升级申报</h1>
                                                <%--  <c:choose>
                                                      <c:when test="${source == 0}">
                                                          <h2 style="margin-top:10px;">短 信 诉 求 登 记 卡</h2>
                                                      </c:when>
                                                      <c:when test="${source == 1}">
                                                          <h2 style="margin-top:10px;">热 线 诉 求 登 记 卡</h2>
                                                      </c:when>
                                                  </c:choose>--%>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                设备id：
                                                <input class="input-medium" type="text" name="deviceId" id="deviceId"
                                                       value=${device.id}>
                                                <label class="control-label no-padding-right">&nbsp;设备名字：</label>
                                                <input class="input-small" type="text" name="deviceName"
                                                       id="deviceName"value=${device.deviceName}>
                                            </td>

                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label class="control-label no-padding-right">&nbsp;申报时间：</label>
                                                    <input class=" date-picker col-xs-15 " id="applytime"
                                                           name="applytime"
                                                           type="date" data-date-format="yyyy-mm-dd" value="<%=date%>">

                                                </div>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td colspan="2">
                                                <table width="100%">
                                                    <tr>

                                                    </tr>
                                                    <tr>
                                                        <td style="border-top:0;">使用者</td>
                                                        <td style="border-top:0;">
                                                            <select class="form-control" name="deviceType"
                                                                    id="deviceType">
                                                                <option value="0">公有</option>
                                                                <option value="1"selected="selected">私有</option>
                                                            </select>

                                                        </td>
                                                        <td style="border-top:0;">
                                                            <input class="input-medium" type="text" placeholder="使用者"
                                                                   name="deviceUser" id="deviceUser"value=${device.deviceUser}>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td class="center" colspan="1">升级描述</td>
                                            <td class="center" colspan="4">
                                                <textarea name="upgradeDescribe" id="upgradeDescribe"
                                                          style="width:800px;height:100px" class="faultDescribe-control"
                                                          style="height: 80px;" placeholder="请详细描述需要升级的地方"></textarea>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td colspan="6" class="center">
                                                <a href="javascript:history.back(-1)" class="btn btn-grey"> <i
                                                        class="icon-arrow-left"> </i> 返回 </a>
                                                <button class="btn btn-info" id="addAppealBtn">
                                                    <i class="icon-ok bigger-110"></i>
                                                    提交申请
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
<script src="${ctx}/assets/js/upgradeapply.js" type="text/javascript"></script>
<script>
    jQuery(function ($) {
        //权限控制，镇级账号
        <%--<c:if test="${user.auth_level != 2}">--%>
        <%--document.getElementById("addAppealBtn").disabled = true;--%>
        <%--</c:if>--%>
        // 日期插件
//        $('#visittime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
//            $(this).prev().focus();
//        });
        $('#birthday').mask('9999年99月');
        <%--$("#scaleunit option[value='"+${farmer.scaleunit}+"']").attr("selected","selected");--%>
        $("#scaleunit").find("option[value=${farmer.scaleunit}]").attr("selected", "selected");

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
        $('#buytime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
</script>
</body>
</html>
