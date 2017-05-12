<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>诉求详细信息</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
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

<div class="main-content"  style="margin: 0">
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
            <a>群众查询</a>
        </li>
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
                    <form name="mqcard" id="mqcard" action="/appeal/update" method="post">
                        <input type="hidden" id="appealId" name="appealId" value="${appeal.id}"/>
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="7">
                                    <h1 style="margin-top:10px;">沙县群众诉求</h1>

                                    <h2 style="margin-top:10px;">登 记 卡</h2>
                                </td>
                            </tr>
                            <tr class="border-bottom-2px">
                                <input type="hidden" name="id" id="id" value="${appeal.id}">
                                <td colspan="2" class="center">
                                    <div class="form-group">
                                        <label class="control-label no-padding-right">诉求编号：</label>
                                        <input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}" readonly="readonly">
                                    </div>
                                </td>
                                <td colspan="5">
                                    <div class="form-group" style="float: left">
                                        <label class="control-label no-padding-right">&nbsp;</label>
                                        <input class="input-small" type="text" placeholder="村" name="village" id="village"
                                               value="${appeal.village}" readonly="readonly">
                                        <label class="control-label no-padding-right">村</label>
                                        <input class="input-medium" type="text" placeholder="网格" name="grid" id="grid" value="${farmer.grid}"
                                               readonly="readonly">
                                        <label class="control-label no-padding-right">网格</label>
                                        <input class="input-medium" type="text" placeholder="户" name="family" id="family" value="${farmer.family}"
                                               readonly="readonly">
                                        <label class="control-label no-padding-right">户</label>
                                        <%--<label class="col-xs-4 no-padding-right">走访时间：</label>--%>
                                        <%--<input class="col-xs-8" id="visittime" type="text" data-date-format="yyyy-mm-dd"--%>
                                        <%--placeholder="走访时间"  value="${farmer.visittime}"  readonly="readonly">--%>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2">农户基本信息：</td>
                                <td class="center col-sm-1">
                                    户主姓名
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="householdername" id="householdername" style="width: 100%"  value="${appeal.appealname}" readonly="readonly">
                                </td>
                                <td class="center">联系电话</td>
                                <td class="center" colspan="3"><input class="input-large" type="text" style="width:100%" name="contactnumber"
                                                                      id="contactnumber" value="${appeal.appealtel}"  readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>
                                   <table width="100%">
                                       <tr>
                                           <td style="border-top:0;">诉求类别</td>
                                           <td style="border-top:0;">
                                               <select class="form-control" name="appealtype" id="appealtype"  disabled="disabled">
                                                   <option value="0" selected="selected">发展生产</option>
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
                                               <select class="form-control" name="affairtype" id="affairtype"  disabled="disabled">
                                                   <option value="0">个人事务</option>
                                                   <option value="1">公共事务</option>
                                               </select>
                                           </td>
                                       </tr>
                                   </table>
                                </td>
                                <td class="center">诉求内容</td>
                                <td class="center" colspan="5">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;" readonly="readonly">${appeal.hardshipappeal}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="3">诉求办理责任信息</td>
                                <td class="center col-sm-1">
                                    责任领导
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutyleader" id="dutyleader" style="width: 100%" value="${appeal.dutyleader}"  readonly="readonly" >
                                </td>
                                <td class="center col-sm-1">责任部门</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutydept" id="dutydept" style="width: 100%" value="${appeal.dutydept}"  readonly="readonly">
                                </td>
                                <td class="center col-sm-1">责任人</td>
                                <td class="center col-sm-2">
                                    <input class="input-medium" type="text" name="dutymenber" id="dutymenber" style="width: 100%" value="${appeal.dutymenber}"  readonly="readonly">
                                </td>
                            </tr>

                            <tr>
                                <td class="center col-sm-1">解决措施</td>
                                <td class="center" colspan="5">
                                    <textarea name="solution" id="solution" class="form-control" style="height: 80px;"  readonly="readonly">${appeal.solution}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-sm-1">完成时限</td>
                                <td colspan="5">
                                    <div class="form-group">
                                        <input class="date-picker col-xs-8" id="timelimit" name="timelimit" type="text" data-date-format="yyyy-mm-dd"
                                                style="width: 100%;" readonly="readonly"  value="${appeal.timelimit}">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-2" rowspan="1">诉求追踪</td>
                                <td class="center col-sm-1">
                                    诉求跟踪状态
                                </td>
                                <td class="center col-sm-2">
                                    <select class="form-control" name="status" id="status" disabled="disabled">
                                        <option value="0">未解决</option>
                                        <option value="1">已上报上级协调解决</option>
                                        <option value="2">延时解决</option>
                                        <option value="3">正在解决</option>
                                        <option value="4">已做好解释说明工作</option>
                                        <option value="5">已解决</option>
                                    </select>

                                </td>
                                <td class="center col-sm-2" colspan="2">
                                    <select class="form-control display-hide" name="doingstatus" id="doingstatus" disabled="disabled">
                                        <option value="0">立学立改项目</option>
                                        <option value="1">短期整改项目</option>
                                        <option value="2">中长期整改项目</option>
                                    </select>
                                </td>

                                <td class="center col-sm-1"></td>
                                <td class="center col-sm-2">

                                </td>
                            </tr>
                            <%--诉求状态跟踪：备注信息 更新列表是需要更新“备注信息列表”和下面的"rowspan="4" ",其中 4 为备注信息数 加 1 ！--%>
                            <c:if test="${not empty tappealmarks}">
                                <tr>
                                    <td class="center col-sm-1" rowspan="${marksize+1}">
                                        诉求状态跟踪
                                    </td>
                                </tr>
                            </c:if>
                            <%--下面是备注信息列表--%>
                            <style type="text/css">
                                #markTable td {
                                    border-top: 0;
                                }
                            </style>
                            <c:forEach items="${tappealmarks}" var="tappealmarks">
                                <tr>
                                    <td colspan="6">
                                        <table id="markTable">
                                            <tbody>
                                                <tr>
                                                    <td>状态日期：</td>
                                                    <td style="border-right: 1px solid #ddd">${tappealmarks.processupdatetime}</td>
                                                    <td>状态备注：</td>
                                                    <td>${tappealmarks.processupdateremark}</td>
                                                    <td></td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty tappealmarks}">
                                <tr>
                                    <td class="center col-sm-1" rowspan="2">
                                        诉求状态跟踪
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6">
                                        <span style="color: grey">&nbsp;&nbsp;&nbsp;暂无诉求跟踪状态</span>
                                    </td>
                                </tr>
                            </c:if>
                            <%--备注信息列表结束--%>
                            <%--评价信息：界面如上要求--%>
                            <c:if test="${not empty tappealrates}">
                                <tr id="appealProcess">
                                    <td class="center col-sm-1" rowspan="${ratesize+1}">
                                        诉求评价
                                    </td>
                                </tr>
                            </c:if>
                            <style type="text/css">
                                #rateTable {
                                    table-layout: fixed;
                                }
                                #rateTable td {
                                    border-top: 0;
                                    text-overflow: ellipsis;
                                    overflow: hidden;
                                    white-space: nowrap;
                                }
                            </style>
                            <c:forEach items="${tappealrates}" var="tappealrate">
                                <tr>
                                    <td colspan="6">
                                        <table id="rateTable" width="100%">
                                            <tbody>
                                            <tr>
                                                <td width="8%">评价日期：</td>
                                                <td style="border-right: 1px solid #ddd" width="8%">${tappealrate.ratedate}</td>
                                                <td width="10%">评价满意度：</td>
                                                <td style="border-right: 1px solid #ddd" width="6%">
                                                    <c:choose>
                                                        <c:when test="${tappealrate.ratelevel==4}">
                                                            满意
                                                        </c:when>
                                                        <c:when test="${tappealrate.ratelevel==3}">
                                                            基本满意
                                                        </c:when>
                                                        <c:when test="${tappealrate.ratelevel==2}">
                                                            一般
                                                        </c:when>
                                                        <c:when test="${tappealrate.ratelevel==1}">
                                                            不满意
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td width="8%">评价内容：</td>
                                                <td width="60%" title="${tappealrate.ratecontent}">${tappealrate.ratecontent}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty tappealrates}">
                                <tr id="appealProcess">
                                    <td class="center col-sm-1" rowspan="2">
                                        诉求评价
                                    </td>
                                </tr>
                                <tr id="appealProcessToDel">
                                    <td colspan="6">
                                        <span style="color: grey">&nbsp;&nbsp;&nbsp;暂无诉求评价</span>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td colspan="7" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="appealRateBtn">
                                        <i class="icon-thumbs-up-alt bigger-110"></i>
                                        评价
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div id="appealRateDialog" class="hide">
                <div class="space-6"></div>
                <table width="100%">
                    <tbody>
                    <tr>
                        <th colspan="5">
                            <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                请您给予本诉求处理结果评价：
                            </label>
                        </th>
                    </tr>
                    <tr>
                        <td width="20%">
                            <div class="radio">
                                <label>
                                    <input id="rateLevel4" name="rateLevel" strname="满意" type="radio" class="ace" checked value="4"/>
                                    <span class="lbl">&nbsp;满意</span>
                                </label>
                            </div>
                        </td>
                        <td width="20%">
                            <div class="radio">
                                <label>
                                    <input id="rateLevel3" name="rateLevel" strname="基本满意" type="radio" class="ace" value="3"/>
                                    <span class="lbl">&nbsp;基本满意</span>
                                </label>
                            </div>
                        </td>
                        <td width="20%">
                            <div class="radio">
                                <label>
                                    <input id="rateLevel2" name="rateLevel" strname="一般" type="radio" class="ace" value="2"/>
                                    <span class="lbl">&nbsp;一般</span>
                                </label>
                            </div>
                        </td>
                        <td width="20%">
                            <div class="radio">
                                <label>
                                    <input id="rateLevel1" name="rateLevel" strname="不满意" type="radio" class="ace" value="1"/>
                                    <span class="lbl">&nbsp;不满意</span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <hr style="margin: 0;"/>
                <div class="space-6"></div>
                <table width="100%">
                    <tbody>
                    <tr>
                        <th>
                            <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                请您输入评价内容：
                            </label>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div class="space-6"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <textarea id="rateContent" name="rateContent" class="autosize-transition form-control"></textarea>
                            <%--onblur="checkEmptyRate()"--%>
                        <%--<span id="errorNotRate" style="color: #d16e6c;line-height: 20px;font-size:15px;" class="hide">请输入诉求评价！</span>--%>
                        </td>
                    </tr>
                    </tbody>
                </table>
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
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>
<!-- page specific plugin scripts -->
<!-- inline scripts related to this page -->
<script>
    $("#appealtype").find("option[value=${appeal.appealtype}]").attr("selected","selected");
    $("#affairtype").find("option[value=${appeal.affairtype}]").attr("selected","selected");
    $("#status").find("option[value=${appeal.status}]").attr("selected", "selected");
    if ('${appeal.status}' == '3') {
        $("#doingstatus").removeClass("display-hide");
        $("#doingstatus").find("option[value=${appeal.doingstatus}]").attr("selected", "selected");
    }
    $('textarea[class*=autosize]').autosize({append: "\n"});
    function checkEmptyRate() {
        if ($("#rateContent").val() == '') {
            $("#rateContent").css({"border": "1px solid #d16e6c"});
            $("#errorNotRate").removeClass('hide');
        } else {
            $("#rateContent").remove({"border": "1px solid #d5d5d5"});
            $("#errorNotRate").addClass('hide');
        }
    }

    $("#appealRateBtn").on('click', function (e) {
        e.preventDefault();
        $("#appealRateDialog").removeClass('hide').dialog({
            height: 380,
            width: 600,
            resizable: false,
            modal: true,
            title: "诉求评价",
            buttons: [
                {
                    html: "<i class='icon-arrow-left bigger-110'></i>&nbsp; 取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
                {
                    html: "<i class='icon-ok bigger-110'></i>&nbsp; 提交评价",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        if(false) {//$("#rateContent").val()==''
                            $("#rateContent").addClass({"border": "1px solid #d16e6c"});
                            $("#errorNotRate").removeClass('hide');
                            return;
                        } else {
                            $.ajax({
                                type: "POST",
                                url: "${ctx}/masses/rate",
                                data: {
                                    appealId: $("#appealId").val(),
                                    rateLevel: $("input[name='rateLevel']:checked").val(),
                                    rateContent: $("#rateContent").val()
                                },
                                dataType: 'json',
                                success: function (data) {
                                    if(data.success) {
                                        var appealRate =
                                                "<tr><td colspan=\"6\"><table id=\"rateTable\" width='100%'><tbody><td width='8%'>评价日期：</td>" +
                                                "<td style=\"border-right: 1px solid #ddd\" width='8%'>${curDate}</td><td width='10%'>评价满意度：</td>" +
                                                "<td style=\"border-right: 1px solid #ddd\" width='6%'>" +
                                                $("input[name='rateLevel']:checked").attr("strname") + "</td>" +
                                                "<td width='8%'>评价内容：</td><td width='60%' title="+$("#rateContent").val()+">" + $("#rateContent").val() +
                                                        "</td></tbody></table></td></tr>";
                                        $("#appealProcess").after(appealRate);
                                        var raterows = $("#appealProcess").find("td:first").attr("rowspan");
                                        $("#appealProcess").find("td:first").attr("rowspan", new Number(raterows) + 1);
                                        if (document.getElementById("appealProcessToDel")) {
                                            $("#appealProcessToDel").remove();
                                            $("#appealProcess").find("td:first").attr("rowspan", new Number(raterows));
                                        }
                                        //$("#appealProcessToDel").remove();
                                        bootbox.dialog({
                                            message: "<span class='bigger-110'>诉求评价成功！</span>",
                                            buttons: {
                                                "success": {
                                                    "label": "<i class='icon-ok'></i> 确定",
                                                    "className": "btn-sm btn-success",
                                                    "callback": function () {
                                                    }
                                                }
                                            }
                                        });
                                    } else {
                                        bootbox.dialog({
                                            message: "<span class='bigger-110'>诉求评价失败！请联系系统管理员解决问题.</span>",
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
                                },
                                error: function (XMLHttpRequest, errorThrown) {
                                    bootbox.dialog({
                                        message: "<span class='bigger-110'>诉求评价失败！请联系系统管理员解决问题.</span>",
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
                }
            ]
        });
    });
</script>
</body>
</html>
