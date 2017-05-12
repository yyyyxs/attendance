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
    <title><spring:message code="global.navMenu.system" /> - <spring:message code="global.subMenu.role" /></title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
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
                        <a><spring:message code="global.navMenu.system" /></a>
                    </li>
                    <li class="active"><spring:message code="global.subMenu.role" /></li>
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
                            <form class="form-horizontal" role="form" action="${ctx}/role/update/${role.id}" method="post" id="modifyRoleForm">
                                <input type="hidden" id="resourceIds" name="resourceIds">

                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="role"> 角色名称 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="role" name="role" value="${role.role}">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="description"> 角色描述 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="description" name="description" value="${role.description}">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="available"> 角色是否可用 </label>

                                    <div class="col-sm-7">
                                        <label style="margin-bottom: -13px;">
                                            <input name="available" id="available" class="ace ace-switch ace-switch-6" type="checkbox">
                                            <span class="lbl"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="modResourceName"> 拥有的资源 </label>

                                    <div class="col-sm-7">
                                        <textarea id="modResourceName" name="modResourceName"
                                                  style="overflow: hidden; word-wrap: break-word;
                                                resize: none; height: 68px;width:28%;">${resourceNames}</textarea>
                                    </div>
                                </div>
                                <%--树--%>
                                <div class="form-group display-hide" id="roleResourceTreeDiv">
                                    <div class="col-md-offset-5">
                                            <ul id="roleResourceTree" class="ztree"></ul>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button"
                                                onclick="javascrtpt:window.location.href='${ctx}/role/'">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-primary" type="submit" id="modRoleSubmitBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            确认修改
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
<script type="text/javascript" src="${ctx}/assets/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/zTree3.5.15/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/system/role/modifyRoleValidate.js"></script>
<script type="text/javascript">
    $(function () {
        var parentTr = $(this).parent().parent().parent();
        var setting = {
            check: {
                enable: true,
                chkboxType: { "Y": "", "N": "" }
            },
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        function onCheck(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("roleResourceTree"),
                    nodes = zTree.getCheckedNodes(true),
                    id = "",
                    name = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                id += nodes[i].id + ",";
                name += nodes[i].name + ",";
            }
            if (id.length > 0) id = id.substring(0, id.length - 1);
            if (name.length > 0) name = name.substring(0, name.length - 1);
            $("#resourceIds").val(id);
            $("#modResourceName").val(name);
        }

        var zNodes = [
            <c:forEach items="${resourceList}" var="r">
            <c:if test="${resource.parentId!=0}">
            { id:${r.id}, pId:${r.parentId}, name: "${r.name}", checked: "${fn:contains(role.resourceIds, r.id)}"},
            </c:if>
            </c:forEach>
        ];
        //初始化zTree
        $.fn.zTree.init($("#roleResourceTree"), setting, zNodes);
        $("#modResourceName").on("click", function(e){
            e.preventDefault();
            $("#roleResourceTreeDiv").toggleClass("display-hide");
        });

        if (${role.available}) {
            $("input[name='available']").prop("checked", true);
        }
        $("#resourceIds").attr("value", ${role.resourceIds});
    });
    </script>
</body>
</html>
