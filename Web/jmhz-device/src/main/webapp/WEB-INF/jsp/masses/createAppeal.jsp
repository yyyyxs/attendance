<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>群众诉求登记平台</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container">
            <span style="float: left;margin-top:5px;margin-left: 5px;">
                <img src="${ctx}/assets/images/logo.png" />
            </span>

        <div class="navbar-header pull-left">
            <a class="navbar-brand" style="margin-left: 10px;">
        <span style="font-size: 40px;line-height: 60px;font-family:'楷体','Helvetica Neue Light', 'Lucida Grande', 'Calibri', 'Arial', 'sans-serif';">
            沙县民情管理服务工作平台
        </span>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>
<div class="main-container" id="main-container">
<script type="text/javascript">
    try {
        ace.settings.check('main-container', 'fixed')
    } catch (e) {
    }
</script>

<div class="main-container-inner">
<div class="main-content" style="margin: 0">
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
            <a><spring:message code="global.homepage" /></a>
        </li>

        <li>
            <a>群众诉求</a>
        </li>
        <li class="active">诉求添加</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="massessappeal" id="massessappeal" action="${ctx}//" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="10">
                                    <h2 style="margin-top:10px;">沙县群众诉求</h2>

                                    <h2 style="margin-top:10px;">登 记 卡</h2>
                                </td>
                            </tr>
                            <tr class="border-top-2px">
                                <td class="center col-sm-2"></td>
                                <td class="center col-sm-1">姓名</td>
                                <td class="center col-sm-1">
                                    <input class="input-medium" type="text" name="name" id="name">
                                </td>
                                <td class="center col-sm-1">所属镇</td>
                                <td class="center col-sm-1">
                                    <input class="input-medium" type="text" name="name" id="town">
                                </td>
                                <td class="center col-sm-1">所属村</td>
                                <td class="center col-sm-1">
                                    <input class="input-medium" type="text" name="village" id="village">
                                </td>
                                <td class="center col-sm-1">联系电话</td>
                                <td class="center col-sm-1">
                                    <input class="input-medium" type="text" name="telephone" id="telephone">
                                </td>


                                    <%--<div class="form-group">--%>
                                        <%--<input class="date-picker col-xs-12" id="visittime" name="visittime" type="text"--%>
                                               <%--data-date-format="yyyy-mm-dd" placeholder="请点击选择”走访时间“">--%>
                                    <%--</div>--%>
                            </tr>
                            <tr>
                                <td>
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
                                                    <option value="6">其他</option>
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
                                <td class="center">诉求内容</td>
                                <td class="center" colspan="9">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="10" class="center">
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
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/createAppeal.js" type="text/javascript"></script>

<!-- page specific plugin scripts -->
<!--日期插件js-->
<%--<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>--%>
<%--<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>--%>
<!-- inline scripts related to this page -->
<script>
    jQuery(function ($) {
        // 日期插件
        //$('#visittime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        //    $(this).prev().focus();
        //});
        //$('#birthday').mask('9999年99月');
        //下面是cxselect的配置
        //先引入数据和cxselect的js，jquery最先引入
        //然后是下面的配置

        //设置全局默认值，引入jquery.cxselect.js之后，调用之前设置
        <%--$.cxSelect.defaults.url = "${ctx}/assets/js/jquery-cxselect/cityData.min.jsonData.js";--%>
        //$.cxSelect.defaults.nodata = "none";
        //
        //selects 为数组形式，请注意顺序
        //$("#snackLocation").cxSelect({
        //    selects: ["province", "city", "area"],
        //    nodata: "none"
        //});
        //$("#workLocation").cxSelect({
        //    selects: ["province", "city", "area"],
        //    nodata: "none"
        //});
        //上面是cxselect的配置
    });
</script>

</body>
</html>
