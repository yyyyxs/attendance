<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.appeal" /> - <spring:message code="global.subMenu.appealCreate" /></title>
    <meta name="description" content="appeal addInput" />
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
        <li>
            <a><spring:message code="global.navMenu.appeal" /></a>
        </li>
        <li class="active"><spring:message code="global.subMenu.appealCreate" /></li>
    </ul>
    <!-- .breadcrumb -->
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">

                    <form name="mqcard" id="mqcard">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">${user.city}${user.town}群众诉求</h1>

                                    <h2 style="margin-top:10px;">登 记 卡</h2>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1" colspan="2">诉求编号：
                                    <input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="村" name="village" id="village" value="${farmer.village}" readonly="readonly">
                                        <label class="control-label no-padding-right">村</label>
                                        <input class="input-medium" type="text" placeholder="网格" name="grid" id="grid" value="${farmer.grid}" readonly="readonly">
                                        <label class="control-label no-padding-right">网格</label>
                                        <input class="input-medium" type="text" placeholder="户" name="family" id="family" value="${farmer.family}" readonly="readonly">
                                        <label class="control-label no-padding-right">户</label>
                                    </div>
                                </td>
                                <td class="center col-xs-6">
                                    <div class="form-group">
                                        <label class="col-xs-4 no-padding-right">走访时间：</label>
                                        <input class="date-picker col-xs-5" id="visittime" name="visittime" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="走访时间" value="${farmer.visittime}" readonly="readonly">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="2">农户基本信息</td>
                                <td class="center col-sm-1">
                                    户主姓名
                                </td>
                                <td class="center col-sm-1">
                                    <input class="input-small" type="text" name="householdername" id="householdername" value="${farmer.householdername}" readonly="readonly">
                                </td>
                                <td class="center col-sm-1">性 别</td>
                                <td class="center" style="width:10%">
                                    <select class="form-control" name="gender" id="gender" disabled="disabled">
                                        <c:if test="${farmer.gender == 0}">
                                            <option value="0" selected="selected">男</option>
                                            <option value="1">女</option>
                                        </c:if>
                                        <c:if test="${farmer.gender == 1}">
                                            <option value="0">男</option>
                                            <option value="1" selected="selected">女</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td rowspan="2">
                                <%--representation: 任职情况--%>
                                <table width="100%">

                                    <tr>
                                        <td style="border-top:0;">任职情况：</td>
                                        <td style="border-top:0;">
                                            <label>
                                                <input id="c0" name="representation" class="ace ace-checkbox-1" type="checkbox" value="0" disabled="disabled">
                                                <span class="lbl">村民代表</span>
                                            </label>
                                        </td>
                                        <td style="border-top:0;">
                                            <label>
                                                <input id="c1" name="representation" class="ace ace-checkbox-1" type="checkbox" value="1" disabled="disabled">
                                                <span class="lbl">村民小组长</span>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="2" style="border-top:0;"></td>
                                        <td style="border-top:0;" colspan="2">
                                            <label>
                                                <input id="c2" name="representation" class="ace ace-checkbox-1" type="checkbox" value="2" disabled="disabled">
                                                <span class="lbl">现任村“两委”干部（</span>
                                            </label>
                                            <label>
                                                <input id="c3" name="representation" class="ace ace-checkbox-1" type="checkbox" value="3" disabled="disabled">
                                                <span class="lbl">村主干）</span>
                                            </label>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td style="border-top:0;" colspan="2">
                                            <label>
                                                <input id="c4" name="representation" class="ace ace-checkbox-1" type="checkbox" value="4" disabled="disabled">
                                                <span class="lbl">离任村“两委”干部（</span>
                                            </label>
                                            <label>
                                                <input id="c5" name="representation" class="ace ace-checkbox-1" type="checkbox" value="5" disabled="disabled">
                                                <span class="lbl">村主干）</span>
                                            </label>
                                        </td>

                                    </tr>
                                </table>
                                </td>
                            </tr>

                            <c:forEach items="${representationlist}" var="rep">

                                    <script>
                                        var check = document.getElementById("c"+${rep});
                                        check.checked = true;
                                    </script>

                            </c:forEach>

                            <tr>
                                <td class="center">出生年月</td>
                                <td class="center">
                                    <input class="form-control input-mask-date" type="text" name="birthday" id="birthday" value="${farmer.birthday}" readonly="readonly">
                                </td>
                                <td class="center">政治面貌</td>
                                <td class="center">
                                    <select class="form-control" name="politicalstatus" id="politicalstatus" disabled="disabled">
                                        <c:choose>
                                            <c:when test="${farmer.politicalstatus== 0}">
                                                <option value="0" selected="selected">群众</option>
                                                <option value="1">共青团员</option>
                                                <option value="2">中共党员</option>
                                            </c:when>
                                            <c:when test="${farmer.politicalstatus == 1}">
                                                <option value="0">群众</option>
                                                <option value="1" selected="selected">共青团员</option>
                                                <option value="2">中共党员</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0">群众</option>
                                                <option value="1">共青团员</option>
                                                <option value="2" selected="selected">中共党员</option>
                                            </c:otherwise>
                                        </c:choose>

                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="9">家庭基本信息</td>
                                <td class="center">家庭人口</td>
                                <td class="center"><input class="input-small" type="text" name="familypopulation" value="${farmer.familypopulation}" readonly="readonly"></td>
                                <td class="center">联系电话</td>
                                <td class="center" colspan="2"><input class="input-large" type="text" style="width:100%" name="contactnumber"
                                                                      id="contactnumber" value="${farmer.contactnumber}" readonly="readonly"></td>
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
                                            <input class="input-medium" type="text" name="plantingproject" id="plantingproject" style="float: left;" readonly="readonly" value="${farmer.plantingproject}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">规模：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="plantingscale" id="plantingscale" value="${farmer.plantingscale}" readonly="readonly">亩
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
                                            <input class="input-medium" type="text" name="farmingproject" id="farmingproject" style="float: left;" value="${farmer.farmingproject}" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">规模：
                                            </label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="farmingscale" id="farmingscale" value="${farmer.farmingscale}" readonly="readonly">
                                                <select name="scaleunit" id="scaleunit" disabled="disabled">
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
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 15px;">所在地：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div id="snackLocation" style="float: left;">
                                                <select name="snackprovince" data-value="${farmer.snackprovince}" class="province"  style="width:100px" disabled="disabled"></select>
                                                <select name="snackcity" data-value="${farmer.snackcity}" class="city" disabled="disabled" ></select>
                                                <select name="snackarea" data-value="${farmer.snackarea}" class="area"  disabled="disabled" contenteditable="false"></select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">月营业收入约 ：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="snackscale" id="snackscale" style="margin-top:10px;" readonly="readonly" value="${farmer.snackscale}">万元
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">务工</td>
                                <td class="center" colspan="3">
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">职业：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <input class="input-medium" type="text" name="workprofession" id="workprofession" style="float: left;" readonly="readonly" value="${farmer.workprofession}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">所在地：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div id="workLocation" style="float: left;">
                                                <select name="workprovince" class="province" data-value="${farmer.workprovince}" disabled></select>
                                                <select name="workcity" class="city" data-value="${farmer.workcity}" disabled="disabled"></select>
                                                <select name="workarea" class="area" data-value="${farmer.workarea}" disabled="disabled"></select>
                                            </div>
                                        </div>
                                    </div>
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
                                            <input class="input-medium" type="text" name="foundedname" id="foundedname" style="float: left;" readonly="readonly" value="${farmer.foundedname}">
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-3">
                                            <label style="padding-top: 5px;">年产值：</label>
                                        </div>
                                        <div class="col-xs-9">
                                            <div style="float: left;">
                                                <input class="input-medium" type="text" name="foundedvalue" id="foundedvalue" value="${farmer.foundedvalue}" readonly="readonly">万元
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center">其他</td>
                                <td class="center" colspan="3">
                                    <input class="input-large" type="text" style="width:100%" name="othersofproduction" id="othersofproduction" value="${farmer.othersofproduction}" readonly="readonly">
                                </td>
                            </tr>
                            <tr>
                                <td class="center">住房情况</td>
                                <td class="center" colspan="4">
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input  class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                    id="h0" value="0" disabled="disabled">
                                            <span class="lbl">钢混&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="h1" value="1" disabled="disabled">
                                            <span class="lbl">砖混&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="h2" value="2" disabled="disabled">
                                            <span class="lbl">砖木&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                   id="h3" value="3" disabled="disabled">
                                            <span class="lbl">木质&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-4">
                                        <label>
                                            <input  class="ace ace-checkbox-1" type="checkbox" name="housingsituation"
                                                    id="h4" value="4" disabled="disabled">
                                            <span class="lbl">其他：<input name="otherhousingsituation" class="input-small" type="text" value="${farmer.otherhousingsituation}" readonly="readonly"></span>
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <c:forEach items="${housingsituationlist}" var="spe">

                                <script>
                                    var check = document.getElementById("h"+${spe});
                                    check.checked = true;
                                    console.info("h"+${spe});
                                </script>

                            </c:forEach>
                            <tr>
                                <td class="center">特殊家庭</td>
                                <td class="center" colspan="4">
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                               id="t0" value="0" disabled="disabled">
                                            <span class="lbl">留守家庭</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="t1" value="1" disabled="disabled">
                                            <span class="lbl">五保户</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="t2" value="2" disabled="disabled">
                                            <span class="lbl">低保户</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-2" style="padding-top:5px;">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                   id="t3" value="3" disabled="disabled">
                                            <span class="lbl">困难家庭</span>
                                        </label>
                                    </div>
                                    <div class="col-xs-4">
                                        <label>
                                            <input name="specialfamily" class="ace ace-checkbox-1" type="checkbox"
                                                    id="t4" value="4"disabled="disabled">
                                            <span class="lbl">其他：<input class="input-small" type="text" name="otherspecialfamily" readonly="readonly" value="${farmer.otherspecialfamily}" ></span>
                                        </label>
                                    </div>
                                </td>
                            </tr>

                            <c:forEach items="${specialfamilylist}" var="spe">

                                <script>
                                    var check = document.getElementById("t"+${spe});
                                    check.checked = true;
                                </script>

                            </c:forEach>
                            <input name="id"  type="hidden" id="farmerid" value=${farmer.id}>

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
        </div>
    </div>
</div>
</div>
</div>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
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
    <script src="${ctx}/assets/js/pages/appeal/addAppealValidate.js" type="text/javascript"></script>
    <script src="${ctx}/assets/js/pages/appeal/addAppealFun.js" type="text/javascript"></script>
    <script>

        <%--set the value of select and checkbox and radio, etc.--%>
        $(function(){
            $("#scaleunit").find("option[value=${farmer.scaleunit}]").attr("selected", "selected");
        });
    </script>
</body>
</html>
