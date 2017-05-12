<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>农户管理 - 民情登记卡</title>
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
            <a>农户管理</a>
        </li>
        <li class="active">添加农户</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="mqcard" id="mqcard" action="${ctx}/farmer/addMqcard" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">${user.city}${user.town}联系服务群众</h1>

                                    <h2 style="margin-top:10px;">户 主 信 息 卡</h2>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td class="center col-xs-1" colspan="2">诉求编号：--%>
                                    <%--<input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>--%>
                            <%--</tr>--%>
                            <tr class="border-bottom-2px">
                                <td colspan="2" class="center">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">诉求编号：</label>
                                        <input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}">

                                    </div>
                                </td>
                                <td colspan="5">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="村" name="village" id="village">
                                        <label class="control-label no-padding-right">村&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="网格" name="grid" id="grid">
                                        <label class="control-label no-padding-right">网格&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="户" name="family" id="family">
                                        <label class="control-label no-padding-right">户&nbsp;</label>
                                        <label class="control-label no-padding-right">&nbsp;&nbsp;&nbsp;&nbsp;网格责任人&nbsp;</label>
                                        <input class="input-large" type="text" style="width:35%" placeholder="网格责任人" name="gridcharger" id="gridcharger">
                                        <%--<label class="col-xs-4 no-padding-right">走访时间：</label>--%>
                                        <%--<input class="date-picker col-xs-5" id="visittime" name="visittime" type="text" data-date-format="yyyy-mm-dd"--%>
                                               <%--placeholder="走访时间">--%>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="2">农户基本信息</td>
                                <td class="center col-sm-1">
                                    户主姓名
                                </td>
                                <td class="center col-sm-1">
                                    <input class="input-small" type="text" name="householdername" id="householdername" value="${verifyName}">
                                </td>
                                <td class="center col-sm-1">性 别</td>
                                <td class="center" style="width:10%">
                                    <select class="form-control" name="gender" id="gender">
                                        <option value="0" selected="selected">男</option>
                                        <option value="1">女</option>
                                    </select>
                                </td>
                                <td rowspan="2">
                                <%--representation: 任职情况--%>
                                <table width="100%">
                                    <tr>
                                        <td style="border-top:0;">任职情况：</td>
                                        <td style="border-top:0;">
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="0">
                                                <span class="lbl">村民代表</span>
                                            </label>
                                        </td>
                                        <td style="border-top:0;">
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="1">
                                                <span class="lbl">村民小组长</span>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2" style="border-top:0;"></td>
                                        <td style="border-top:0;" colspan="2">
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="2">
                                                <span class="lbl">现任村“两委”干部（</span>
                                            </label>
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="3">
                                                <span class="lbl">村主干）</span>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border-top:0;" colspan="2">
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="4">
                                                <span class="lbl">离任村“两委”干部（</span>
                                            </label>
                                            <label>
                                                <input name="representation" class="ace ace-checkbox-1" type="checkbox" value="5">
                                                <span class="lbl">村主干）</span>
                                            </label>
                                        </td>
                                    </tr>
                                </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">出生年月</td>
                                <td class="center">
                                    <input class="form-control input-mask-date" type="text" name="birthday" id="birthday">
                                </td>
                                <td class="center">政治面貌</td>
                                <td class="center">
                                    <select class="form-control" name="politicalstatus" id="politicalstatus">
                                        <option value="0">群众</option>
                                        <option value="1">共青团员</option>
                                        <option value="2">中共党员</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="9">家庭基本信息</td>
                                <td class="center">家庭人口</td>
                                <td class="center"><input class="input-small" type="text" name="familypopulation" id="familypopulation"></td>
                                <td class="center">联系电话</td>
                                <td class="center" colspan="2"><input class="input-large" type="text" style="width:100%" name="contactnumber"
                                                                      id="contactnumber" value="${verifyPhone}" /></td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="6">主要生产项目</td>
                                <td class="center">种植业</td>
                                <td class="center" colspan="4">
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">项目：
                                            </label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="input-medium" type="text" name="plantingproject" id="plantingproject" style="float: left;">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">规模：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="plantingscale" id="plantingscale">亩
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">养殖业</td>
                                <td class="center" colspan="3">
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">项目：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="input-medium" type="text" name="farmingproject" id="farmingproject" style="float: left;">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">规模：
                                            </label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="farmingscale" id="farmingscale">
                                                <select name="scaleunit" id="scaleunit">
                                                    <option value="0">万只</option>
                                                    <option value="1">公斤</option>
                                                    <option value="2">亩</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">小吃业</td>
                                <td class="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td style="border-top:0;" width="15%">所在地：</td>
                                            <td style="border-top:0;" width="85%">
                                                <div id="snackLocation" style="float: left;">
                                                    <select name="snackprovince" class="province" style="width:100px"></select>
                                                    <select name="snackcity" class="city"></select>
                                                    <select name="snackarea" class="area"></select>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-top:0;">月营业收入</td>
                                            <td style="border-top:0; float: left;">
                                                <input class="input-medium" type="text" name="snackscale" id="snackscale">万元
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">务工</td>
                                <td class="center" colspan="3">
                                    <table width="100%">
                                        <tr>
                                            <td style="border-top:0;" width="15%">职业：</td>
                                            <td style="border-top:0;" width="85%">
                                                <input class="input-medium" type="text" name="workprofession" id="workprofession"
                                                       style="float: left;">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border-top:0;">所在地</td>
                                            <td style="border-top:0; float: left;">
                                                <div id="workLocation" style="float: left;">
                                                    <select name="workprovince" class="province"></select>
                                                    <select name="workcity" class="city"></select>
                                                    <select name="workarea" class="area"></select>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">创办实体</td>
                                <td class="center" colspan="3">
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">实体名称:</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="input-medium" type="text" name="foundedname" id="foundedname" style="float: left;">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">年产值：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="foundedvalue" id="foundedvalue">万元
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">其他</td>
                                <td class="center" colspan="3">
                                    <input class="input-large" type="text" style="width:100%" name="othersofproduction" id="othersofproduction">
                                </td>
                            </tr>
                            <tr>
                                <td class="center">住房情况</td>
                                <td class="center" colspan="4">
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input  class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="hs0" value="0">
                                            <span class="lbl">钢混&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="hs1" value="1">
                                            <span class="lbl">砖混&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="hs2" value="2">
                                            <span class="lbl">砖木&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="hs3" value="3">
                                            <span class="lbl">木质&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-4">
                                        <label>
                                            <input  class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                    id="hs4" value="4">
                                            <span class="lbl">其他：<input name="otherhousingsituation" class="input-small" type="text"></span>
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">特殊家庭</td>
                                <td class="center" colspan="4">
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                               id="hometownremain" value="0">
                                            <span class="lbl">留守家庭</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="fiveproject" value="1">
                                            <span class="lbl">五保户</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="lowproject" value="2">
                                            <span class="lbl">低保户</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="poorfamilies" value="3">
                                            <span class="lbl">困难家庭</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-4">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                    value="4">
                                            <span class="lbl">其他：<input class="input-small" type="text" name="otherspecialfamily"></span>
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr class="border-top-2px">
                                <td class="center">走访信息</td>
                                <td class="center">走访时间：</td>
                                <td colspan="2">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-12" id="visittime" name="visittime" type="text"
                                               data-date-format="yyyy-mm-dd" placeholder="请点击选择”走访时间“">
                                    </div>
                                </td>
                                <td class="center">走访备注：</td>
                                <td colspan="2">
                                    <div class="form-group">
                                        <input class="col-xs-12" type="text" name="visitremark" id="visitremark">
                                    </div>
                                </td>
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
                                <td class="center">诉求内容</td>
                                <td class="center" colspan="4">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="addFarmerBtn">
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
<script src="${ctx}/assets/js/mqCardAdd.js" type="text/javascript"></script>
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
//            document.getElementById("addFarmerBtn").disabled = true;
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
<c:if test="${addres == 0} ">
    ${addres} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>添加失败！</span>",
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        //window.location.href=${ctx}+"/farnerController/show";
                    }
                }
            }
        });

    </script>
</c:if>
<c:if test="${addres == 1}">
    ${addres} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>添加成功！</span>",
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        //window.location.href=${ctx}+"/farmer/show";
                    }
                }
            }
        });

    </script>
</c:if>
<c:if test="${addres == 2}">
    ${addres} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>该农户已经存在！</span>",
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        //window.location.href=${ctx}+"/farmer/show";
                    }
                }
            }
        });

    </script>
</c:if>
<c:if test="${verifyRes == 0}">
    ${verifyRes} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>该农户不存在，跳转农户添加界面！</span>",
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        //window.location.href=${ctx}+"/farmer/show";
                    }
                }
            }
        });

    </script>
</c:if>
</body>
</html>
