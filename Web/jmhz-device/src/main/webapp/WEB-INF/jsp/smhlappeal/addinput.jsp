<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>诉求添加</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
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
            <a href="${ctx}/index"><spring:message code="global.homepage" /></a>
        </li>

        <c:choose>
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
        </c:choose>
        <li class="active">添加诉求</li>
    </ul>
    <!-- .breadcrumb -->
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">

                    <form name="hlAppealForm" id="hlAppealForm">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">${user.city}${user.town}群众诉求</h1>
                                    <c:choose>
                                        <c:when test="${source == 0}">
                                            <h2 style="margin-top:10px;">短 信 诉 求 登 记 卡</h2>
                                        </c:when>
                                        <c:when test="${source == 1}">
                                            <h2 style="margin-top:10px;">热 线 诉 求 登 记 卡</h2>
                                        </c:when>
                                    </c:choose>

                                </td>
                            </tr>
                            <tr>
                                <td  colspan="3">诉求编号：
                                    <input class="input-medium" type="text" name="uuid" id="uuid"></td>
                                    <input  type="hidden" name="createdate" id="createdate" value="${tsmoriginalappeal.createtime}">
                                    <input  type="hidden" name="source" id="source" value="${source}">

                                <td colspan="3">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">&nbsp;所属村：</label>
                                        <input class="input-small" type="text" placeholder="村" name="village" id="village" >
                                        <label class="control-label no-padding-right">&nbsp;姓名：</label>
                                        <input class="input-medium" type="text" placeholder="姓名" name="appealname" id="appealname" >
                                        <label class="control-label no-padding-right">&nbsp;诉求电话：</label>
                                        <input class="input-larger" type="text" placeholder="电话号码" name="appealtel" id="appealtel" value="${tsmoriginalappeal.tel}">
                                    </div>
                                </td>

                            </tr>

                            <tr>
                                <td colspan="2">
                                    <table width="100%">
                                        <tr>
                                            <td style="border-top:0;">诉求类别</td>
                                            <td style="border-top:0;">
                                                <select class="form-control" name="appealtype" id="appealtype">
                                                    <option value="0">发展生产</option>
                                                    <option value="1">基础设施</option>
                                                    <option value="2">矛盾纠纷</option>
                                                    <option value="3">社会治安</option>
                                                    <option value="4">生活救助</option>
                                                    <option value="5">征地拆迁</option>
                                                    <option value="6">政策咨询</option>
                                                    <option value="7">证照办理</option>
                                                    <option value="8">群众投诉</option>
                                                    <option value="9">其他</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-top:0;">事务类别</td>
                                            <td style="border-top:0;">
                                                <select class="form-control" name="affairtype" id="affairtype">
                                                    <option value="0">个人事务</option>
                                                    <option value="1">公共事务</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td class="center" colspan="1">诉求内容</td>
                                <td class="center" colspan="4" >
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;">${tsmoriginalappeal.content}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="6" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="addAppealBtn">
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
<script src="${ctx}/assets/js/addHlAppeal.js" type="text/javascript"></script>
<script>
    jQuery(function ($) {
        //权限控制，镇级账号
        <c:if test="${user.auth_level != 2}">
        document.getElementById("addAppealBtn").disabled = true;
        </c:if>
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
    });
</script>
</body>
</html>
