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
    <title><spring:message code="global.navMenu.farmer" /> - <spring:message code="global.subMenu.farmerExportList" /></title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
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
                        <a><spring:message code="global.navMenu.farmer" /></a>
                    </li>
                    <li class="active"><spring:message code="global.subMenu.farmerExportList" /></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="table-responsive" id="roleListDiv">
                            <table id="excelTable" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th style="width:10%;">序号</th>
                                    <th style="width:10%;">导出内容</th>
                                    <th style="width:10%;">导出时间</th>
                                    <th style="width:10%;">花费时间</th>
                                    <th style="width:25%;">文件名称</th>
                                    <th style="width:10%;">文件大小</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${exportDataList}" var="exportfarmerData">
                                    <tr id="tr${exportfarmerData.id}">
                                        <td>
                                            <a href="#">${exportfarmerData.id}</a>
                                        </td>
                                        <td>${exportfarmerData.exportcontent}</td>
                                        <td><pretty:prettyTime date="${exportfarmerData.exporttime}" /></td>
                                        <td>${exportfarmerData.spendtime}秒</td>
                                        <td>${exportfarmerData.filename}</td>
                                        <td>${exportfarmerData.filesize}</td>
                                        <td>
                                            <a href="${ctx}${exportfarmerData.url}">
                                                    <span class="label label-success">
                                                        <i class="icon-download-alt bigger-120"></i>
                                                        点击下载
                                                    </span>
                                            </a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <style type="text/css">
                                                a:hover {
                                                    text-decoration: none;
                                                }
                                            </style>
                                            <input type="hidden" value="${exportfarmerData.id}" id="fileID"/>
                                            <span class="label label-warning" id="deleteExportData" style="cursor:pointer;">
                                                <i class="icon-warning-sign bigger-120"></i>
                                                删除文件
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div id="dialog-confirm" class="hide">
                            <div class="alert alert-info bigger-110">
                                若是点击<b>[确认删除]</b><br/>将会删除本条通知和已导出的文件！
                            </div>
                            <span class="label label-warning label-xlg">注意: 删除后无法恢复，只能重新导出！</span>
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
        var deleteExportBtnList = $("#excelTable #deleteExportData");
        for (var i = 0; i < deleteExportBtnList.length; i++) {
            deleteExportBtnList[i].onclick = function () {
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
                                    url: "${ctx}/exportfarmerData/delete",
                                    data: {
                                        id: $("#fileID").val()
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
                                        $("#tr" + $("#fileID").val()).remove();
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
        }
    });
</script>
</body>
</html>
