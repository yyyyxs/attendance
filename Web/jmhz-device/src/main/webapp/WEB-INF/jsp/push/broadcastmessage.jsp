<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>消息推送 - 广播推送</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <%--导入头部css--%>
        <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
        <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
        <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
        <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
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
                try{ace.settings.check('main-container' , 'fixed')}catch(e){}
            </script>

            <div class="main-container-inner">
                <%--导入左边导航菜单--%>
                <%@include file="/WEB-INF/jsp/common/sidebar.jsp" %>

                <div class="main-content">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                        </script>

                        <ul class="breadcrumb">
                            <li>
                                <i class="icon-home home-icon"></i>
                                <a href="${ctx}/"><spring:message code="global.homepage" /></a>
                            </li>

                            <li>
                                <a> 消息推送 </a>
                            </li>
                            <li class="active"> 广播推送 </li>
                        </ul><!-- .breadcrumb -->
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-8">
                                <form name="sendSingleSMS" id="sendSingleSMSForm">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="2">
                                                <h4>广播推送</h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center col-sm-2">消息标题</td>
                                            <td class="center col-sm-10">
                                                <input class="input-medium" type="text" name="title" id="title" style="width: 100%">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center">短信内容</td>
                                            <td class="center">
                                                <textarea name="content" id="content" class="form-control"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="center">
                                                <button type="button" class="btn btn-info" id="pushBroadcastMessageBtn">
                                                    <i class="icon-ok bigger-110"></i>
                                                    确认发送
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div><!-- /.page-content -->
                    </div><!-- /.main-content -->
                <div id="confirmDelAppeal" class="hide">
                    <div class="alert alert-info bigger-110">
                        若是点击<b>[确认删除]</b><br />将会删除本条发送历史！
                    </div>
                </div>
                <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
            </div><!-- /.main-container-inner -->
            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="icon-double-angle-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->
        <%--导入尾部js--%>
        <%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
        <!-- page specific plugin scripts -->
        <!--日期插件js-->
        <script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
        <script src="${ctx}/assets/js/bootbox.min.js"></script>
        <script src="${ctx}/assets/js/jquery.maskedinput.min.js"></script>
        <!-- inline scripts related to this page -->
        <script src="${ctx}/assets/js/jquery-cxselect/jquery.cxselect.min.js"></script>
        <script src="${ctx}/assets/js/jquery-cxselect/smsTypesData.min.jsonData.js"></script>
        <!-- inline scripts related to this page -->
        <script type="text/javascript">
            //发送短信
            $("#pushBroadcastMessageBtn").click(function(){
                $.ajax({
                    type: "POST",
                    url: "${ctx}/push/broadcast",
                    data: {
                        title: $("#title").val(),
                        content: $("#content").val()
                    },
                    dataType: 'json',
                    beforeSend: function () {
                        $("#maskDIV").show();
                    },
                    success: function (data) {
                        if(data.success) {
                            $("#maskDIV").fadeOut();
                            bootbox.dialog({
                                message: "<span class='bigger-110'>消息推送成功</span>",
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
                                message: "<span class='bigger-110'>消息推送失败，原因："+data.msg+"，请联系系统管理员解决.</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            $("#maskDIV").fadeOut();
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>系统异常，请联系系统管理员！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        $("#maskDIV").fadeOut();
                                    }
                                }
                            }
                        });
                    }
                });
            });
            $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
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
