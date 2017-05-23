<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>个人信息 - 查看信息</title>
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
            <a>个人信息</a>
        </li>
        <li class="active">查看详情</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="usercard" id="usercard" action="" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">用 户 信 息 卡</h1>

                                    <%--<h2 style="margin-top:10px;"></h2>--%>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td class="center col-xs-1" colspan="2">诉求编号：--%>
                                    <%--<input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>--%>
                            <%--</tr>--%>
                            <%-- <tr class="border-bottom-2px">
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
                                    <%--</div>
                                </td>
                            </tr>--%>
                            <tr>
                                <td class="center col-xs-2" rowspan="2">用户基本信息</td>
                                <td class="center ">学 &nbsp; &nbsp; &nbsp;号</td>
                                <td class="center ">
                                    <input class="form-control input-mask-date" type="text" name="userid" id="userid"
                                           value="${user.username}">
                                </td>
                                <td class="center col-sm-1">导师姓名</td>
                                <td class="center" style="width:10%">
                                    <input class="form-control input-mask-date" type="text" name="teachername"
                                           id="teachername" value="${teacher.teacherName}">
                                </td>
                                <td rowspan="2">
                                <%--representation: 任职情况--%>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-sm-1">用户姓名</td>
                                <td class="center col-sm-1">
                                    <input class="form-control input-mask-date" type="text" name="username"
                                           id="username" value="${user.studentName}">
                                </td>
                                <td class="center">所属年级</td>
                                <td class="center">
                                    <input class="form-control input-mask-date" type="text" name="username" id="grade" value="${user.grade}">
                                </td>
                            </tr>
                            <%-->
                            <tr>
                                <td class="center" rowspan="8">设备基本信息</td>
                                <td class="center">设备名字</td>
                                <td class="center"><input class="input-large" type="text" name="devicename" id="devicename"></td>
                                <td class="center">设备品牌</td>
                                <td class="center"><input class="input-large" type="text"  name="brand" id="brand"/></td>
                                <td class="center">我的设备二维码</td>
                            </tr>
                            <tr>
                                <td class="center">设备状态</td>
                                <td class="center"><input class="input-large" type="text" name="devicestatus" id="devicestatus"></td>
                                <td class="center">设备类型</td>
                                <td class="center"><input class="input-large" type="text" name="devicetype" id="devicetype"></td>
                                <td rowspan="7" class="center"><textarea rows="7">二维码</textarea></td>
                            </tr>
                            <tr>
                                <td class="center">购买价格</td>
                                <td class="center"><input class="input-large" type="text" name="price" id="price"></td>
                                <td class="center">购买时间</td>
                                <td class="center"><input class="input-large" type="text" name="buytime" id="buytime"></td>
                            </tr>
                            <tr>
                                <td class="center">存放地点</td>
                                <td class="center" colspan="3"><input class="form-control input-mask-date" type="text" name="position" id="position"></td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="4">配置信息</td>
                                <td class="center">CPU</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name="cpu" id="cpu"></td>
                            </tr>
                            <tr>
                                <td class="center">显卡</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name="gpu" id="gpu"></td>
                            </tr>
                            <tr>
                                <td class="center">内存</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name=" memory" id=" memory"></td>
                            </tr>
                            <tr>
                                <td class="center">其他</td>
                                < <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name=" other" id=" other"></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="addFarmerBtn">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                </td>
                            </tr><--%>
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
