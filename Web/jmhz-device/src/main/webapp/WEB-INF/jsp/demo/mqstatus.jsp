<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>诉求状态更新</title>
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
            <a href="#">Home</a>
        </li>

        <li>
            <a href="#">Other Pages</a>
        </li>
        <li class="active">Blank Page</li>
    </ul>
    <!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                <i class="icon-search nav-search-icon"></i>
            </span>
        </form>
    </div>
    <!-- #nav-search -->
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="mqcard" action="/testController/testform" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="7">
                                    <h2 style="margin-top:10px;">高桥镇开展“五全”联系服务群众活动</h2>

                                    <h2 style="margin-top:10px;">诉 求 状 态 更 新</h2>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1">诉求编号：20140401894859</td>
                                <td colspan="4">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="村" name="village" id="village">
                                        <label class="control-label no-padding-right">村</label>
                                        <input class="input-medium" type="text" placeholder="网格" name="grid" id="grid">
                                        <label class="control-label no-padding-right">网格</label>
                                        <input class="input-medium" type="text" placeholder="户" name="family" id="family">
                                        <label class="control-label no-padding-right">户</label>
                                    </div>
                                </td>
                                <td class="left col-xs-6" colspan="3">
                                    <div class="form-group" style="float: left">
                                        <label class="col-xs-4 no-padding-right">走访时间：</label>
                                        <input class="col-xs-8" id="visittime" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="走访时间">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2">户主基本信息：</td>
                                <td class="center col-sm-1">
                                    户主姓名
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="householdername" id="householdername" style="width: 100%">
                                </td>
                                <td class="center">联系电话</td>
                                <td class="center" colspan="3"><input class="input-large" type="text" style="width:100%" name="contactnumber"
                                                                      id="contactnumber"></td>
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
                                <td class="center" colspan="5">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="3">诉求办理责任信息</td>
                                <td class="center col-sm-1">
                                    责任领导
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="householdername" id="householdername" style="width: 100%">
                                </td>
                                <td class="center col-sm-1">责任部门</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="contactnumber" id="contactnumber" style="width: 100%">
                                </td>
                                <td class="center col-sm-1">责任人</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="contactnumber" id="contactnumber" style="width: 100%">
                                </td>
                            </tr>

                            <tr>
                                <td class="center col-sm-1">解决措施</td>
                                <td class="center" colspan="5">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-sm-1">完成时限</td>
                                <td colspan="5">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="finishtime" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="完成时限(请点击选择日期)" style="width: 100%;" readonly="readonly">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="1">诉求追踪管理/进度更新</td>
                                <td class="center col-sm-1">
                                    诉求跟踪状态
                                </td>
                                <td class="center col-sm-2">
                                    <select class="form-control" name="affairtype" id="affairtype">
                                        <option value="0">未解决</option>
                                        <option value="1">正在解决</option>
                                        <option value="2">延时解决</option>
                                        <option value="3">已做好说明解释工作</option>
                                        <option value="4">已上报上级协调解决</option>
                                        <option value="5">已解决</option>
                                    </select>
                                </td>
                                <td class="center col-sm-1">进度更新时间</td>
                                <td class="center col-sm-2">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="processupdatetime" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="更新时间(请点击选择日期)" style="width: 100%;" readonly="readonly">
                                    </div>
                                </td>
                                <td class="center col-sm-1">进度更新备注</td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="contactnumber" id="contactnumber" style="width: 100%">
                                </td>
                            </tr>
                            <%--诉求状态跟踪：备注信息 更新列表是需要更新“备注信息列表”和下面的"rowspan="4" ",其中 4 为备注信息数 加 1 ！--%>
                            <tr>
                                <td class="center col-sm-1" rowspan="4">
                                    诉求状态跟踪
                                </td>
                            </tr>
                            <%--下面是备注信息列表--%>
                            <tr>
                                <td colspan="6">
                                    20140419：已完成资料填写，请耐心等待；
                                </td>
                            </tr>

                            <tr>
                                <td colspan="6">
                                    20140420：已上报上级协调解决，会尽快给予回复；
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    20140421：出现一些困难，暂时延时解决；
                                </td>
                            </tr>
                            <%--备注信息列表结束--%>
                            <tr>
                                <td colspan="7" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <a href="javascript:mqcard.submit()" class="btn btn-primary"> <i class="icon-ok"> </i> 提交 </a>
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

<div class="ace-settings-container" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="icon-cog bigger-150"></i>
    </div>

    <div class="ace-settings-box" id="ace-settings-box">
        <div>
            <div class="pull-left">
                <select id="skin-colorpicker" class="hide">
                    <option data-skin="default" value="#438EB9">#438EB9</option>
                    <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                    <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                    <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                </select>
            </div>
            <span>&nbsp; Choose Skin</span>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-navbar" />
            <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-sidebar" />
            <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-breadcrumbs" />
            <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-rtl" />
            <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-add-container" />
            <label class="lbl" for="ace-settings-add-container">
                Inside
                <b>.container</b>
            </label>
        </div>
    </div>
</div>
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
<script>
    jQuery(function ($) {
        // 日期插件
        $('#finishtime').datepicker({autoclose: true, language:'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        $('#processupdatetime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
</script>
</body>
</html>
