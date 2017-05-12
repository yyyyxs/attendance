<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>诉求展示</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
    <!-- inline styles related to this page -->
    <style>
        .ui-dialog .ui-dialog-titlebar {
            padding: .4em 1em;
            position: relative
        }
    </style>
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

        <c:choose>
            <c:when test="${tsmhlappeal.source == 0}">
                <li>
                    <a> 短信诉求 </a>
                </li>
            </c:when>
            <c:when test="${tsmhlappeal.source == 1}">
                <li>
                    <a> 热线诉求 </a>
                </li>
            </c:when>
        </c:choose>
        <li class="active">诉求信息</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="smhlAppealForm" id="smhlAppealForm" action="${ctx}/smhlappeal/update" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="7">
                                    <h1 style="margin-top:10px;">${user.city}${user.town}群众诉求</h1>
                                    <c:choose>
                                        <c:when test="${tsmhlappeal.source == 0}">
                                                <h2 style="margin-top:10px;"> 短 信 诉 求  登 记 卡</h2>
                                        </c:when>
                                        <c:when test="${tsmhlappeal.source == 1}">
                                                <h2 style="margin-top:10px;"> 热 线 诉 求  登 记 卡</h2>
                                        </c:when>
                                    </c:choose>

                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td class="center col-xs-1" colspan="2">诉求编号：--%>
                                    <%--<input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>--%>
                                <%--<td colspan="5"></td>--%>
                            <%--</tr>--%>
                            <tr class="border-bottom-2px">
                                <input type="hidden" name="id" id="id" value="${tsmhlappeal.id}">
                                <input type="hidden" name="source" id="source" value="${tsmhlappeal.source}">
                                <td colspan="2" class="center">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">诉求编号：</label>
                                        <input class="input-medium" type="text" name="uuid" id="uuid" value="${tsmhlappeal.uuid}">
                                    </div>
                                </td>
                                <td colspan="5">
                                    <div class="form-group" style="float: left">
                                        <label class="control-label no-padding-right">&nbsp;</label>
                                        <input class="input-larger" type="text" placeholder="村" name="village" id="village" value="${tsmhlappeal.village}">
                                        <label class="control-label no-padding-right">村</label>

                                        <%--<label class="col-xs-4 no-padding-right">走访时间：</label>--%>
                                        <%--<input class="col-xs-8" id="visittime" type="text" data-date-format="yyyy-mm-dd"--%>
                                               <%--placeholder="走访时间"  value="${farmer.visittime}"  readonly="readonly">--%>
                                    </div>
                                </td>
                            </tr>
                            <tr class="border-bottom-2px">
                                <td class="center col-xs-2">诉求者基本信息：</td>
                                <td class="center col-sm-1">
                                    姓名
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="appealname" id="appealname" style="width: 100%"  value="${tsmhlappeal.appealname}">
                                </td>
                                <td class="center">联系电话</td>
                                <td class="center" colspan="3"><input class="input-large" type="text" style="width:100%" name="appealtel"
                                                                      id="appealtel" value="${tsmhlappeal.appealtel}" ></td>
                            </tr>
                            <tr>
                                <td>
                                   <table width="100%">
                                       <tr>
                                           <td style="border-top:0;">诉求类别</td>
                                           <td style="border-top:0;">
                                               <select class="form-control" name="appealtype" id="appealtype">
                                                   <option value="0" selected="selected">发展生产</option>
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
                                <td class="center" colspan="5">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;">${tsmhlappeal.hardshipappeal}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="3">诉求办理责任信息</td>
                                <td class="center col-sm-1">
                                    责任领导
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutyleader" id="dutyleader" style="width: 100%" value="${tsmhlappeal.dutyleader}" >
                                </td>
                                <td class="center col-sm-1">责任部门</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutydept" id="dutydept" style="width: 100%" value="${tsmhlappeal.dutydept}">
                                </td>
                                <td class="center col-sm-1">责任人</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutymenber" id="dutymenber" style="width: 100%" value="${tsmhlappeal.dutymenber}">
                                </td>
                            </tr>

                            <tr>
                                <td class="center col-sm-1">解决措施</td>
                                <td class="center" colspan="5">
                                    <textarea name="solution" id="solution" class="form-control" style="height: 80px;"> ${tsmhlappeal.solution}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-sm-1">完成时限</td>
                                <td colspan="2">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="timelimit" name="timelimit" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="完成时限(请点击选择日期)" style="width: 100%;"   value="${tsmhlappeal.timelimit}">
                                    </div>
                                </td>
                                <td class="center col-sm-1">创建日期</td>
                                <td colspan="2">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="createdate" name="createdate" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="创建日期(请点击选择日期)" style="width: 100%;"   value="${tsmhlappeal.createdate}">
                                    </div>
                                </td>
                            </tr>

                            <tr class="border-top-2px">
                                <td class="center col-xs-2" rowspan="1">诉求追踪管理/进度更新</td>
                                <td class="center col-sm-1">
                                    诉求跟踪状态
                                </td>
                                <td class="center col-sm-2">
                                    <select class="form-control" name="status" id="status" onchange="onStatusChange()">
                                        <option value="0">未解决</option>
                                        <option value="1">已上报上级协调解决</option>
                                        <option value="2">延时解决</option>
                                        <option value="3">正在解决</option>
                                        <option value="4">已做好解释说明工作</option>
                                        <option value="5">已解决</option>
                                    </select>
                                    <select class="form-control display-hide" name="doingstatus" id="doingstatus">
                                        <option value="0">立学立改项目</option>
                                        <option value="1">短期整改项目</option>
                                        <option value="2">中长期整改项目</option>
                                    </select>
                                </td>
                                <td class="center col-sm-1">进度更新时间</td>
                                <td class="center col-sm-2">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="processupdatetime" name="processupdatetime" type="text" data-date-format="yyyy-mm-dd"
                                               placeholder="更新时间(请点击选择日期)" style="width: 100%;" >
                                    </div>
                                </td>
                                <td class="center col-sm-1">进度更新备注</td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="processupdateremark" id="processupdateremark" style="width: 100%">
                                </td>
                            </tr>
                            <%--诉求状态跟踪：备注信息 更新列表是需要更新“备注信息列表”和下面的"rowspan="4" ",其中 4 为备注信息数 加 1 ！--%>
                            <tr>
                                <td class="center col-sm-1" rowspan="${size+1}">
                                    诉求状态跟踪
                                </td>
                            </tr>
                            <%--下面是备注信息列表--%>
                           <c:forEach items="${tsmhlappealmarks}" var="tsmhlappealmarks">
                               <tr><td colspan="6">
                                       ${tsmhlappealmarks.processupdatetime}:${tsmhlappealmarks.processupdateremark}
                                   </td>
                               </tr>
                           </c:forEach>
                            <%--备注信息列表结束--%>
                            <tr>
                                <td colspan="7" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="updateSmhlAppealBtn">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                    <a onclick="sendSMS(${tsmhlappeal.id}, '${tsmhlappeal.uuid}')" class="btn btn-primary"> <i class="icon-mail-forward"></i>发送短信</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div id="dialog-send-sms" class="hide">
                    <table width="100%">
                        <tbody>
                        <tr>
                            <td width="20%">
                                <label class="control-label bolder blue">诉求编号：</label>
                            </td>
                            <td>
                                <input class="input-medium" type="text" name="appealuuid" id="appealuuid" readonly />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label bolder blue">接收号码：</label>
                            </td>
                            <td>
                                <input class="input-medium" type="text" name="appealtelq" id="appealtelq"  />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <label class="control-label bolder blue">诉求内容：</label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea name="appealContent" id="appealContent" class="form-control" style="height: 80px; resize: none;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <label class="control-label bolder blue">短信内容：</label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <textarea name="smsContent" id="smsContent" class="form-control" style="height:120px; resize: none;"></textarea>
                            </td>
                        </tr>

                        </tbody>
                    </table>
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
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/updateSmhlAppeal.js" type="text/javascript"></script>

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
        //权限控制，镇级账号
        <c:if test="${user.auth_level != 2}">
        document.getElementById("updateSmhlAppealBtn").disabled = true;
        </c:if>
        // 日期插件
        $('#timelimit').datepicker({autoclose: true, language:'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        $('#createdate').datepicker({autoclose: true, language:'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        $('#processupdatetime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        $("#appealtype").find("option[value=${tsmhlappeal.appealtype}]").attr("selected","selected");
        $("#affairtype").find("option[value=${tsmhlappeal.affairtype}]").attr("selected","selected");
        $("#status").find("option[value=${tsmhlappeal.status}]").attr("selected","selected");
        if ('${tsmhlappeal.status}'=='3') {
            $("#doingstatus").removeClass("display-hide");
            $("#doingstatus").find("option[value=${tsmhlappeal.doingstatus}]").attr("selected", "selected");
        }
    });
    function onStatusChange() {
        if ($("#status").val() == '3') {
            $("#doingstatus").removeClass("display-hide");
        } else {
            $("#doingstatus").addClass("display-hide");
        }
    }
    function sendSMS(id, uuid) {
        // 后台获取短信模板和诉求信息，并动态替换模板生成短信息内容返回
        $.ajax({
            type: "POST",
            url: "${ctx}/smhlappeal/getSMSForSmhlAppeal/" + id,//获得短信详情
            dataType: 'json',
            success: function (data) {
                $("#appealuuid").attr("value", data.uuid);
                $("#appealtelq").attr("value", data.appealtel);
                document.getElementById('appealContent').value = data.appealContent;
                document.getElementById('smsContent').value = data.smsContent;
            },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>短信模板获取失败！请联系系统管理员解决问题.</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                //Example.show("great success");
                            }
                        }
                    }
                });
            }
        });
        $("#dialog-send-sms").removeClass('hide').dialog({
            height: "auto",
            width: 600,
            resizable: true,
            modal: true,
            title: "短信诉求处理回复",
            buttons: [
                {
                    html: "<i class='icon-mail-forward bigger-110'></i>&nbsp; 发送",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        $.ajax({
                            // TODO: 调用逻辑发送短信
                            type: "POST",
                            url: "${ctx}/smhlappeal/sendSMS",//发送短信
                            data:{
                                appealtel: $('#appealtel').val(),
                                smsContent: $('#smsContent').val()
                            },
                            dataType: 'json',
                            beforeSend: function () {
                                $("#sendSMSMaskDIV").show();
                            },
                            success: function (data) {
                                $("#sendSMSMaskDIV").fadeOut();

                                bootbox.dialog({
                                    message: "<span class='bigger-110'>短信发送结果："+data.msg+"</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });


                            },
                            error: function (XMLHttpRequest, errorThrown) {
                                $("#sendSMSMaskDIV").fadeOut();
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>短信发送失败！请联系系统管理员解决问题.</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });
                            }
                        });
                        $(this).dialog("close");
                    }
                }
                ,
                {
                    html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });
    }
    $("<div id='sendSMSMaskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
            "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>短信发送中...</span></div>").css({
        position: 'absolute',
        top: 0,
        left: 0,
        backgroundColor: "#393939",
        opacity: 0.5,
        zIndex: 1040
    }).height($(document).height()).width($(document).width()).hide().appendTo("body");
</script>
</body>
</html>
