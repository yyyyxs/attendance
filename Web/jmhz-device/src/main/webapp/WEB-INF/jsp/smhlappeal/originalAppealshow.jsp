<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>原始诉求展示</title>
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
            <a href="${ctx}/"><spring:message code="global.homepage" /></a>
        </li>


                <li>
                    <a> 短信诉求 </a>
                </li>

        <li class="active">原始诉求信息</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="smOriginalAppealForm" id="smOriginalAppealForm" action="${ctx}/appeal/update" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">${user.city}${user.town}群众诉求</h1>

                                                <h2 style="margin-top:10px;"> 短 信 原 始 诉 求 </h2>

                                    <input type="hidden" name="id" id="id" value="${tsmoriginalappeal.id}">
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1">联系电话：</td>
                                <td><input class="input-medium" type="text" style="width:100%" name="tel"
                                                                      id="tel" value="${tsmoriginalappeal.tel}" ></td>
                                <td  class="center col-xs-1">原始诉求状态</td>
                                <td>
                                    <select class="form-control" name="status" id="status">
                                        <option value="1" selected="selected">新增</option>
                                        <option value="2">正在处理</option>
                                        <option value="3">已回复</option>
                                    </select>
                                </td>
                                <td class="center col-sm-1">接收日期</td>
                                <td>
                                    <div class="form-group">
                                        <input class="date-picker" id="createtime" name="createtime" type="text"
                                               data-date-format="yyyy-mm-dd"
                                               placeholder="接收日期(请点击选择日期)" style="width: 100%;" value="${tsmoriginalappeal.createtime}">
                                    </div>
                                </td>
                            </tr>
                            <tr>

                                <td class="center">诉求内容</td>
                                <td class="center" colspan="5">
                                    <textarea name="content" id="content" class="form-control" style="height: 80px;">${tsmoriginalappeal.content}</textarea>
                                </td>

                            </tr>

                            <tr>
                                <td colspan="7" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="updateAppealBtn">
                                        <i class="icon-ok bigger-110"></i>
                                        更新
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
<script src="${ctx}/assets/js/updateSmOriginalAppeal.js" type="text/javascript"></script>

<!-- page specific plugin scripts -->
<!--日期插件js-->
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<!--限制输入字数js-->
<script src="${ctx}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<!--限制输入格式js-->
<script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>

<!-- inline scripts related to this page -->
<script>
    jQuery(function ($) {
        // 日期插件

        $('#createtime').datepicker({autoclose: true, language:'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $("#status").find("option[value=${tsmoriginalappeal.status}]").attr("selected","selected");

    });

</script>
</body>
</html>
