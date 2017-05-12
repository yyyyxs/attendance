<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>短信模板 - 添加模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
                        <a>短信模板</a>
                    </li>
                    <li class="active">添加模板</li>
                </ul><!-- .breadcrumb -->
            </div>
            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <%--修改--%>
                        <div class="" id="modifyRoleDiv">
                            <div class="space-10"></div>
                            <div class="space-10"></div>
                            <form class="form-horizontal" action="${ctx}/smstpl/add" method="post" id="updateSMSTemplateForm">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right" for="tplname"> 模板用途 </label>

                                    <div class="col-sm-8">
                                        <select name="tplname" id="tplname" class="col-sm-5" style="padding: 0px;">
                                            <option value="rar">当收到诉求后回复本短信</option>
                                            <option value="rad">当诉求处理后回复本短信</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right" for="tplcontent"> 模板内容 </label>

                                    <div class="col-sm-8">
                                        <textarea id="tplcontent" name="tplcontent" class="col-sm-5" style="height: 120px;padding: 5px;"></textarea>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label no-padding-right" for="isvalid"> 是否生效 </label>

                                    <div class="col-sm-8">
                                        <label style="margin-bottom: -13px;">
                                            <input name="isvalid" id="isvalid" class="ace ace-switch ace-switch-6" type="checkbox">
                                            <span class="lbl"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button"
                                                onclick="javascript:history.back(-1)">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-primary" type="submit" id="modSMSTemplateSubmitBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            确认添加
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
            <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %><!-- /#ace-settings-container -->--%>
    </div><!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- page specific plugin scripts -->
<script type="text/javascript" src="${ctx}/assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#updateSMSTemplateForm").validate({
            rules: {
                tplcontent: {
                    required: true
                }
            },
            messages: {
                tplcontent: {
                    required: "<span style='color: #d16e6c;'>&nbsp;* 模板内容不能为空！</span>"
                }
            },
            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {                             //错误信息位置设置方法
                error.insertAfter(element);                            //这里的element是录入数据的对象
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
    </script>
</body>
</html>
