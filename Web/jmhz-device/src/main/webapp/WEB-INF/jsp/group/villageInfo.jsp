<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>民情登记卡</title>
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
            <a href="${ctx}/"><spring:message code="global.homepage"/></a>
        </li>

        <li>
            <a href="#">集体管理</a>
        </li>
        <li class="active">添加村情</li>
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
                    <form name="villageinfo"  id="villageinfo" action="${ctx}/group/addVillageinfo" method="post">
                        <table class="table table-bordered sima-td-input-100" style="margin-bottom: 0;">
                            <tbody>
                                <tr>
                                    <td class="center" colspan="13">
                                        <h2 style="margin-top:10px;">村情基本信息</h2>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="center" colspan="2"><label for="village"><b>村名：</b></label></td>
                                    <td colspan="11"><input type="text" name="village" id="village"></td>
                                </tr>
                                <tr>
                                    <td class="center" rowspan="5" style="width: 4%">人<br />口<br />资<br />料</td>
                                    <td class="center" style="border-top:0; width: 8%; padding: 1px; margin: 0; line-height: 30px;">村民小组</td>
                                    <td class="center">户数</td>
                                    <td class="center" colspan="3">总人口</td>
                                    <td class="center" colspan="3">年龄结构</td>
                                    <td class="center" colspan="4">民族构成</td>
                                </tr>
                                <tr>
                                    <td class="center" style="border-top:0; width: 8%; padding: 1px; margin: 0; line-height: 30px;" rowspan="2">
                                        <input style="height: 60px;" type="text" name="villagerTeam" id="villagerTeam">
                                    </td>
                                    <td class="center" style="border-top:0; width: 8%; padding: 1px; margin: 0; line-height: 30px;" rowspan="2">
                                        <input style="height: 60px;" type="text" name="households" id="households">
                                    </td>
                                    <td class="center" style="line-height: 20px;">合计</td>
                                    <td class="center">男</td>
                                    <td class="center">女</td>
                                    <td class="center">18岁以下</td>
                                    <td class="center">19-59岁</td>
                                    <td class="center">60岁以上</td>
                                    <td class="center">主要民族</td>
                                    <td class="center">人口</td>
                                    <td class="center">其他</td>
                                    <td class="center">人口</td>
                                </tr>
                                <tr>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="totalPepCnt" id="totalPepCnt">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="totalPepMan" id="totalPepMan">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="totalPepWoman" id="totalPepWoman">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="belowEighteen" id="belowEighteen">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="belowSixty" id="belowSixty">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="aboveSixty" id="aboveSixty">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="mainNation" id="mainNation">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="nationPeps" id="nationPeps">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="otherNation" id="otherNation">
                                    </td>
                                    <td style="border-top:0; width: 8%; padding: 1px; margin: 0;">
                                        <input type="text" name="otherPeps" id="otherPeps">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="center" style="border-top:0; width: 8%; padding: 1px; margin: 0; line-height: 30px;">党小组数</td>
                                    <td class="center">党员人数</td>
                                    <td class="center" colspan="2">留守家庭</td>
                                    <td class="center" colspan="2">五保户</td>
                                    <td class="center" colspan="2">低保户</td>
                                    <td class="center" colspan="2">困难家庭</td>
                                    <td class="center" colspan="2">其他</td>
                                </tr>
                                <tr>
                                    <td style="border-top:0; padding: 1px; margin: 0;">
                                        <input type="text" name="partyGroupCnt" id="partyGroupCnt">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;">
                                        <input type="text" name="partyMemCnt" id="partyMemCnt">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;" colspan="2">
                                        <input type="text" name="hometownRemain" id="hometownRemain">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;" colspan="2">
                                        <input type="text" name="fiveGuarantees" id="fiveGuarantees">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;" colspan="2">
                                        <input type="text" name="lowIncome" id="lowIncome">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;" colspan="2">
                                        <input type="text" name="poorFamily" id="poorFamily">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0;" colspan="2">
                                        <input type="text" name="otherFamily" id="otherFamily">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="center" rowspan="2" style="border-bottom-color: #fff;">土地<br />状况</td>
                                    <td class="center" colspan="2" style="line-height: 30px;">耕地面积（亩）</td>
                                    <td class="center" colspan="2">人均耕地面积（亩）</td>
                                    <td class="center" colspan="2">林地面积（亩）</td>
                                    <td class="center" colspan="2">茶果面积（亩）</td>
                                    <td class="center" colspan="2">毛竹面积（亩）</td>
                                    <td class="center" colspan="2">土地流转情况（亩）</td>
                                </tr>
                                <tr>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="totalCultArea" id="totalCultArea">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="perCultArea" id="perCultArea">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="forestArea" id="forestArea">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="fruitArea" id="fruitArea">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="bambooArea" id="bambooArea">
                                    </td>
                                    <td style="border-top:0; padding: 1px; margin: 0; border-bottom-color: #fff;" colspan="2">
                                        <input type="text" name="landCycleCond" id="landCycleCond">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered sima-td-input-100" style="margin-bottom: 0;">
                            <tbody>
                            <tr>
                                <td class="center" rowspan="5" style="width: 4%">经<br />济<br />发<br />展</td>
                                <td class="center" style="border-top:0; width: 8%; padding: 1px; margin: 0; line-height: 30px;" colspan="2">第一产业<br />（农林牧渔业）</td>
                                <td class="center" colspan="2">第二产业（工业）</td>
                                <td class="center" colspan="2">第三产业（服务业）</td>
                                <td class="center" colspan="3">外出经营小吃</td>
                                <td class="center" colspan="4">合作社</td>
                                <td class="center">总收入<br />（万元）</td>
                                <td class="center">农民人均<br />纯收入<br />（元）</td>
                            </tr>
                            <tr>
                                <td class="center" style="line-height: 23px;">产值<br />（万元）</td>
                                <td class="center">从业人员</td>
                                <td class="center">产值<br />（万元）</td>
                                <td class="center">从业人员</td>
                                <td class="center">产值<br />（万元）</td>
                                <td class="center">从业人员</td>
                                <td class="center">户数</td>
                                <td class="center">人数</td>
                                <td class="center">总收入<br />（万元）</td>
                                <td class="center">种植(个）</td>
                                <td class="center">养殖(个）</td>
                                <td class="center">农机(个）</td>
                                <td class="center">其他(个）</td>
                                <td class="center" style="border-top:0; width: 6%; padding: 1px; margin: 0;" rowspan="2">
                                    <input style="height: 86px;" type="text" name="totalIncome" id="totalIncome">
                                </td>
                                <td class="center" style="border-top:0; width: 6%; padding: 1px; margin: 0;" rowspan="2">
                                    <input style="height: 86px;" type="text" name="perFarmerIncome" id="perFarmerIncome">
                                </td>
                            </tr>
                            <tr>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="primaryIndValue" id="primaryIndValue"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="primaryIntPepCnt" id="primaryIntPepCnt"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="secondIndValue" id="secondIndValue"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="secondIndPepCnt" id="secondIndPepCnt"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="thirdIndValue" id="thirdIndValue"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="thirdIndPepCnt" id="thirdIndPepCnt"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="snackHouseholds" id="snackHouseholds"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="snackPepCnt" id="snackPepCnt"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="snackIncome" id="snackIncome"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="coopPlant" id="coopPlant"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="coopBreed" id="coopBreed"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="coopAgro" id="coopAgro"></td>
                                <td style="border-top:0; width: 6%; padding: 1px; margin: 0;">
                                    <input type="text" name="coopOther" id="coopOther"></td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered sima-td-input-100">
                            <tbody>
                            <tr>
                                <td class="center" rowspan="3" style="width: 4%; border-top-color: #fff;">公共<br />服务<br />设施</td>
                                <td class="center" style="width: 9%; padding: 1px; margin: 0; line-height: 30px; border-top-color: #fff;">村级组织<br />活动场所</td>
                                <td class="center" colspan="2" style="border-top-color: #fff;">农民户外健身场地</td>
                                <td class="center" style="border-top-color: #fff;">老年人活动室</td>
                                <td class="center" colspan="2" style="border-top-color: #fff;">村卫生室</td>
                                <td class="center" colspan="2" style="border-top-color: #fff;">完小/教学点</td>
                                <td class="center" colspan="2" style="border-top-color: #fff;">幼儿园</td>
                            </tr>
                            <tr>
                                <td class="center" style="line-height: 23px;">建筑面积</td>
                                <td class="center">器械（台）</td>
                                <td class="center">占地面积</td>
                                <td class="center">建筑面积</td>
                                <td class="center">建筑面积</td>
                                <td class="center">床位</td>
                                <td class="center">教师数</td>
                                <td class="center">学生数</td>
                                <td class="center">教师数</td>
                                <td class="center">学生数</td>
                            </tr>
                            <tr>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="villageOrgArea" id="villageOrgArea"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="farmerFitWeap" id="farmerFitWeap"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="farmerFitArea" id="farmerFitArea"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="elderRoomArea" id="elderRoomArea"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="healthRoomArea" id="healthRoomArea"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="healthRoomBeds" id="healthRoomBeds"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="eduPotTeachers" id="eduPotTeachers"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="eduPotStudents" id="eduPotStudents"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="kidTeachers" id="kidTeachers"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;">
                                    <input type="text" name="kidStudents" id="kidStudents"></td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="2">村级集体收入</td>
                                <td class="center" colspan="2" style="line-height: 30px;">村集体收入（万元）</td>
                                <td class="center" colspan="2">年末村集体资产总额（万元）</td>
                                <td class="center" colspan="2">年末村集体负债总额（万元）</td>
                                <td class="center" colspan="2">村“两委”干部人数</td>
                                <td class="center" colspan="2">村民代表数</td>
                            </tr>
                            <tr>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;" colspan="2">
                                    <input type="text" name="vilTotalIncome" id="vilTotalIncome"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;" colspan="2">
                                    <input type="text" name="vilTotalAssets" id="vilTotalAssets"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;" colspan="2">
                                    <input type="text" name="vilTotalDebts" id="vilTotalDebts"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;" colspan="2">
                                    <input type="text" name="twoCommitsCnt" id="twoCommitsCnt"></td>
                                <td style="border-top:0; width: 9%; padding: 1px; margin: 0;" colspan="2">
                                    <input type="text" name="vilRepresentCnt" id="vilRepresentCnt"></td>
                            </tr>
                            <tr>
                                <td colspan="11" height="25px;" style="border-bottom-color: #fff;"></td>
                            </tr>
                            <tr>
                                <td colspan="11" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="addVillageInfoBtn">
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
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/villageInfoAdd.js" type="text/javascript"></script>
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
<script>
    jQuery(function ($) {
        // 日期插件
        $('#visittime').datepicker({autoclose: true, language:'zh-CN'}).next().on(ace.click_event, function () {
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
            message: "<span class='bigger-110'>该村情已经存在！</span>",
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
