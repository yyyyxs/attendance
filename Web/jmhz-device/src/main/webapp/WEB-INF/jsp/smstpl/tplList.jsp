<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="pretty" tagdir="/WEB-INF/tags/pretty" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>短信模板列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
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
                        <a>短信模板</a>
                    </li>
                    <li class="active">模板列表</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <c:if test="${addrs == 1}">
                            <div class="alert alert-block alert-success" id="msgbox">
                                <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                <p><strong><i class="icon-ok"></i>&nbsp;&nbsp;&nbsp;${addmsg}</strong></p>
                            </div>
                        </c:if>
                        <c:if test="${addrs == 0}">
                            <div class="alert alert-block alert-warning" id="msgbox">
                                <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                <p><strong><i class="icon-remove"></i>&nbsp;&nbsp;&nbsp;${addmsg}</strong></p>
                            </div>
                        </c:if>
                        <div class="table-responsive" id="roleListDiv">
                            <table id="SMSTplTable" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width:4%;">序号</th>
                                    <th style="width:15%;">模板用途</th>
                                    <th style="width:45%;">模板内容</th>
                                    <th style="width:8%;">创建者</th>
                                    <th style="width:7%;">是否生效</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tsmstemplateList}" var="tsmstemplate">
                                    <tr id="tr${tsmstemplate.id}">
                                        <td>
                                            <a href="#">${tsmstemplate.id}</a>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${tsmstemplate.tplname == 'rar'}">
                                                    当收到诉求后回复本短信
                                                </c:when>
                                                <c:when test="${tsmstemplate.tplname == 'rad'}">
                                                    当诉求处理后回复本短信
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${tsmstemplate.tplcontent}</td>
                                        <td>${tsmstemplate.tplcreator}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${tsmstemplate.isvalid == 0}">
                                                    <span class="label label-sm label-warning">模板失效</span>
                                                </c:when>
                                                <c:when test="${tsmstemplate.isvalid == 1}">
                                                    <span class="label label-sm label-success">模板可用</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="${ctx}/smstpl/update/${tsmstemplate.id}">
                                                    <span class="label label-primary">
                                                        <i class="icon-edit bigger-120"></i>
                                                        修改模板
                                                    </span>
                                            </a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <style type="text/css">
                                                a:hover {
                                                    text-decoration: none;
                                                }
                                            </style>
                                            <input type="hidden" value="${tsmstemplate.id}" id="tplid" />
                                            <span class="label label-warning" id="deleteSMSTpl" style="cursor:pointer;" onclick="deleteSMSTplFun(${tsmstemplate.id})">
                                                <i class="icon-warning-sign bigger-120"></i>
                                                删除模板
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <button class="btn btn-primary" type="button" id="addSMSTemplateBtn" style="float:right;"
                                    onclick="window.location.href='${ctx}/smstpl/add'">
                                <i class="icon-plus bigger-110"></i>
                                新建短信模板
                            </button>
                        </div>
                        <div id="dialog-confirm" class="hide">
                            <div class="alert alert-info bigger-110">
                                若是点击<b>[确认删除]</b><br/>将会删除本条短信模板！
                            </div>
                            <span class="label label-warning label-xlg">注意: 删除后无法恢复，只能重新配置！</span>
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
<script src="${ctx}/assets/js/jquery.ui.touch-punch.min.js"></script>
<!-- page specific plugin scripts -->
<script type="text/javascript">
    $(function(){
        //$("#msgbox").on("mouseover", function (e) {
        //    $("#msgbox").fadeOut("slow");
        //});
        $("button").on("mouseover", function (e) {
            $("#msgbox").fadeOut("slow");
        });
    });
    function deleteSMSTplFun(id) {
        console.info(id);
        $("#dialog-confirm").removeClass('hide').dialog({
            resizable: false,
            modal: true,
            title: "确认删除？",
            buttons: [
                {
                    html: "<i class='icon-trash bigger-110'></i>&nbsp; 确认删除",
                    "class": "btn btn-danger btn-xs",
                    click: function () {
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/smstpl/delete/"+id,
                            data: {
                            },
                            dataType: 'json',
                            success: function (data) {
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>删除成功.</span>",
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
                                $("#tr" + id).remove();
                            },
                            error: function (XMLHttpRequest, errorThrown) {
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>删除失败！请联系系统管理员解决问题.</span>",
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
</script>
</body>
</html>
