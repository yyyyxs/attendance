<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Blank Page - Ace Admin</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
</head>
<body>
<div id="BgDiv"></div>
<div class="main-container" id="appendResourceDiv">
    <div class="breadcrumbs center" id="breadcrumbs">
        <ul class="breadcrumb">
            <li><i class="icon-home home-icon"></i>资源管理</li>
            <li>添加子节点</li>
        </ul>
    </div>
    <div class="row">
            <div class="col-xs-12">
                <div class="space-10"></div>
                <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="parentNodeName"> 父节点名称 </label>
                    <div class="col-sm-7">
                        <input type="text" id="parentNodeName" class="col-xs-10 col-sm-3" readonly>
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="childNodeName"> 子节点名称 </label>
                    <div class="col-sm-7">
                        <input type="text" id="childNodeName" placeholder="请输入想要添加的子节点名称" class="col-xs-10 col-sm-3">
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="resourceType"> 资源类型 </label>
                    <div class="col-sm-7">
                        <select class="col-xs-10 col-sm-3" name="resourceType" id="resourceType">
                            <option value="0">菜单（menu）</option>
                            <option value="1">按钮（button）</option>
                        </select>
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="urlAccessPath"> URL访问路径 </label>
                    <div class="col-sm-7">
                        <input type="text" class="col-xs-10 col-sm-3" id="urlAccessPath" >
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="form-group">
                    <label class="col-sm-5 control-label no-padding-right" for="permissionString"> 权限字符串 </label>
                    <div class="col-sm-7">
                        <span class="input-icon">
                            <input type="text" id="permissionString">
                            <i class="icon-key blue"></i>
                        </span>
                    </div>
                </div>
                <div class="space-4"></div>
                <div class="clearfix form-actions">
                    <div class="center">
                        <button class="btn btn-grey" type="button">
                            <i class="icon-arrow-left bigger-110"></i>
                            取消
                        </button>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <button class="btn btn-primary" type="button">
                            <i class="icon-ok bigger-110"></i>
                            确认添加
                        </button>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <button class="btn btn-danger" type="reset">
                            <i class="icon-undo bigger-110"></i>
                            重置
                        </button>
                    </div>
                </div>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div>
        </div>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- inline scripts related to this page -->
<script type="text/javascript">

</script>
</body>
</html>