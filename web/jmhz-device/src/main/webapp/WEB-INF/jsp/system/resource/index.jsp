<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.system" /> - <spring:message code="global.subMenu.resource" /></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/js/jquery-treetable/jquery.treetable.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/assets/js/jquery-treetable/jquery.treetable.theme.sima.css">
    <link rel="stylesheet" href="${ctx}/assets/css/pages/resource.css">
</head>
<body>
<%--遮罩层--%>
<div id="BgDiv"></div>
<%--导入头部banner--%>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<%--添加子节点层--%>
<div style="width:84%;display: none;" id="appendResourceDiv">
    <div class="breadcrumbs center">
        <ul class="breadcrumb">
            <li><i class="icon-home home-icon"></i>资源管理</li>
            <li>添加子节点</li>
        </ul>
    </div>
    <div class="space-10"></div>
    <form class="form-horizontal" role="form">
        <input type="hidden" id="addNodeParentId" name="addNodeParentId">
        <input type="hidden" id="addNodeParentIds" name="addNodeParentIds">

        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="parentNodeName"> 父节点名称 </label>

            <div class="col-sm-7">
                <input type="text" id="parentNodeName" readonly>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="name"> 子节点名称 </label>

            <div class="col-sm-7">
                <input type="text" id="name" name="name">
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="type"> 资源类型 </label>

            <div class="col-sm-7">
                <select name="type" id="type">
                    <option value="menu">菜单（menu）</option>
                    <option value="button">按钮（button）</option>
                </select>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="available"> 菜单是否显示 </label>

            <div class="col-sm-7">
                <label style="margin-bottom: -13px;">
                    <input name="available" id="available" class="ace ace-switch ace-switch-6" type="checkbox">
                    <span class="lbl"></span>
                </label>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="url"> URL访问路径 </label>

            <div class="col-sm-7">
                <input type="text" id="url" name="url">
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="permission"> 权限字符串 </label>

            <div class="col-sm-7">
                <input type="text" id="permission" name="permission">
                <i class="icon-key blue"></i>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="clearfix form-actions" style="margin-bottom:0;">
            <div class="center">
                <button class="btn btn-grey" type="button" onclick="HideMask('appendResourceDiv')">
                    <i class="icon-arrow-left bigger-110"></i>
                    取消
                </button>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" type="button" id="appendResourceSubmitBtn">
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
</div>
<%--修改节点层--%>
<div style="width:84%;display: none;" id="modifyResourceDiv">
    <div class="breadcrumbs center">
        <ul class="breadcrumb">
            <li><i class="icon-home home-icon"></i>资源管理</li>
            <li>修改节点属性</li>
        </ul>
    </div>
    <div class="space-10"></div>
    <form class="form-horizontal" role="form">
        <input type="hidden" id="modNodeId" name="modNodeId">
        <input type="hidden" id="modNodeParentId" name="modNodeParentId">
        <input type="hidden" id="modNodeParentIds" name="modNodeParentIds">

        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="nodeName"> 节点名称 </label>

            <div class="col-sm-7">
                <input type="text" id="nodeName">
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="nodeType"> 资源类型 </label>

            <div class="col-sm-7">
                <select name="nodeType" id="nodeType">
                    <option value="menu">菜单（menu）</option>
                    <option value="button">按钮（button）</option>
                </select>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="nodeAvailable"> 菜单是否显示 </label>

            <div class="col-sm-7">
                <label style="margin-bottom: -13px;">
                    <input name="nodeAvailable" id="nodeAvailable" class="ace ace-switch ace-switch-6" type="checkbox">
                    <span class="lbl"></span>
                </label>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="nodeUrl"> URL访问路径 </label>

            <div class="col-sm-7">
                <input type="text" id="nodeUrl" name="nodeUrl">
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="nodePermission"> 权限字符串 </label>

            <div class="col-sm-7">
                <input type="text" id="nodePermission" name="nodePermission">
                <i class="icon-key blue"></i>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="clearfix form-actions" style="margin-bottom:0;">
            <div class="center">
                <button class="btn btn-grey" type="button" onclick="HideMask('modifyResourceDiv')">
                    <i class="icon-arrow-left bigger-110"></i>
                    取消
                </button>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" type="button" id="modResourceSubmitBtn">
                    <i class="icon-ok bigger-110"></i>
                    确认修改
                </button>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-danger" type="reset">
                    <i class="icon-undo bigger-110"></i>
                    重置
                </button>
            </div>
        </div>
    </form>
</div>
<%--删除节点层--%>
<div style="width:84%;display: none;" id="deleteResourceDiv">
    <div class="breadcrumbs center">
        <ul class="breadcrumb">
            <li><i class="icon-home home-icon"></i>资源管理</li>
            <li>删除节点</li>
        </ul>
    </div>
    <div class="space-10"></div>
    <form class="form-horizontal" role="form">
        <input type="hidden" id="delNodeId" name="delNodeId">
        <input type="hidden" id="delNodeParentIds" name="delNodeParentIds">

        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="delNodeName"> 节点名称 </label>

            <div class="col-sm-7">
                <input type="text" id="delNodeName" name="delNodeName" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="delNodeType"> 资源类型 </label>

            <div class="col-sm-7">
                <select name="delNodeType" id="delNodeType" disabled="disabled">
                    <option value="menu">菜单（menu）</option>
                    <option value="button">按钮（button）</option>
                </select>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="delNodeAvailable"> 菜单是否显示 </label>

            <div class="col-sm-7">
                <label style="margin-bottom: -13px;">
                    <input name="delNodeAvailable" id="delNodeAvailable"
                           class="ace ace-switch ace-switch-6" type="checkbox">
                    <span class="lbl"></span>
                </label>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="delNodeUrl"> URL访问路径 </label>

            <div class="col-sm-7">
                <input type="text" id="delNodeUrl" name="delNodeUrl" readonly>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="form-group">
            <label class="col-sm-5 control-label no-padding-right" for="delNodePerm"> 权限字符串 </label>

            <div class="col-sm-7">
                <input type="text" id="delNodePerm" name="delNodePerm" readonly>
                <i class="icon-key blue"></i>
            </div>
        </div>
        <div class="space-4"></div>
        <div class="clearfix form-actions" style="margin-bottom:0;">
            <div class="center">
                <button class="btn btn-grey" type="button" onclick="HideMask('deleteResourceDiv')">
                    <i class="icon-arrow-left bigger-110"></i>
                    取消
                </button>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-danger" type="button" id="delResourceSubmitBtn">
                    <i class="icon-ok bigger-110"></i>
                    删除
                </button>
            </div>
        </div>
    </form>
</div>
<%--列表展示层--%>
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
                        <a href="${ctx}/audit"><spring:message code="global.navMenu.system" /></a>
                    </li>
                    <li class="active"><spring:message code="global.subMenu.resource" /></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">

                    <div class="col-xs-12">
                        <table id="resourceTable">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th style="width: 50px;">类型</th>
                                    <th>URL路径</th>
                                    <th>权限字符串</th>
                                    <th style="width: 110px;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${resourceList}" var="resource">
                                <tr data-tt-id='${resource.id}' <c:if test="${resource.parentId!=0}">data-tt-parent-id='${resource.parentId}'</c:if>
                                        id="${resource.parentIds}" name="${resource.name}">
                                    <td>${resource.name}</td>
                                    <td style="width: 50px;">${resource.type.info}</td>
                                    <td>${resource.url}</td>
                                    <td>${resource.permission}</td>
                                    <td resourcetype="${resource.type}" nodeavai="${resource.available}" nodeurl="${resource.url}"
                                        nodeperm="${resource.permission}">
                                        <shiro:hasPermission name="resource:create">
                                            <c:if test="${resource.type ne 'button'}">
                                                <button type="button" class="btn spinner-up btn-minier btn-success"
                                                        style="padding: 0 2px;line-height: 14px;margin-left: 10px;" id="appendResourceBtn">
                                                    <i class="icon-plus smaller-75" style="margin: 0;"></i>
                                                </button>
                                                <%--<a href="${pageContext.request.contextPath}/resource/${resource.id}/appendChild">添加子节点</a>--%>
                                            </c:if>
                                        </shiro:hasPermission>

                                        <shiro:hasPermission name="resource:update">
                                            <button type="button" class="btn spinner-up btn-minier btn-info" id="modifyResourceBtn"
                                                    style="padding: 0 2px;line-height: 14px;margin-left: 10px;">
                                                <i class="icon-pencil smaller-75" style="margin: 0;"></i>
                                            </button>
                                            <%--<a href="${pageContext.request.contextPath}/resource/${resource.id}/update">修改</a>--%>
                                        </shiro:hasPermission>

                                        <c:if test="${resource.parentId!=0}">
                                            <shiro:hasPermission name="resource:delete">
                                                <button type="button" class="btn spinner-up btn-minier btn-danger" id="deleteResourceBtn"
                                                        style="padding: 0 2px;line-height: 14px;margin-left: 10px;">
                                                    <i class="icon-cut smaller-75" style="margin: 0;"></i>
                                                </button>
                                                <%--<a class="deleteBtn" href="#" data-id="${resource.id}">删除</a>--%>
                                            </shiro:hasPermission>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i> </a>
</div>
<!-- /.main-container -->
    <%--导入尾部js--%>
    <%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
    <script type="text/javascript" src="${ctx}/assets/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/jquery-treetable/jquery.treetable.js"></script>
    <script type="text/javascript" src="${ctx}/assets/js/pages/system/resource/resourceFun.js"></script>
</body>
</html>