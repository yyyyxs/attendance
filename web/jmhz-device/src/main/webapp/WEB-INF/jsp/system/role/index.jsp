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
                        <c:if test="${not empty msg}">
                            <div class="alert alert-block alert-success" id="msgbox">
                                <button type="button" class="close" data-dismiss="alert"><i class="icon-remove"></i></button>
                                <p><strong><i class="icon-ok"></i>&nbsp;&nbsp;&nbsp;${msg}</strong></p>
                            </div>
                        </c:if>
                        <%--添加--%>
                        <div class="display-hide" id="createRoleDiv">
                            <div class="space-10"></div>
                            <div class="space-10"></div>
                            <form class="form-horizontal" role="form" id="createRoleForm">
                                <input type="hidden" id="addRoleIds" name="addRoleIds">
                                <input type="hidden" id="roleids" name="roleids">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="addRoleName"> 角色名称 </label>
                                    <div class="col-sm-7">
                                        <input type="text" id="addRoleName" name="addRoleName">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="addRoleDesc"> 角色描述 </label>
                                    <div class="col-sm-7">
                                        <input type="text" id="addRoleDesc" name="addRoleDesc">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="addRoleAvailable"> 角色是否可用 </label>
                                    <div class="col-sm-7">
                                        <label style="margin-bottom: -13px;">
                                            <input name="addRoleAvailable" id="addRoleAvailable" class="ace ace-switch ace-switch-6" type="checkbox">
                                            <span class="lbl"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="addResourceName">
                                        角色权限
                                    </label>
                                    <div class="col-sm-7">
                                        <textarea id="addResourceName" name="addResourceName"
                                                style="overflow: hidden; word-wrap: break-word;
                                                resize: none; height: 68px;width:28%;" placeholder="点击显示资源树选择资源 再次点击隐藏资源树"></textarea>
                                    </div>
                                </div>
                                <%--树--%>
                                <div class="form-group display-hide" id="resourceTreeDiv">
                                    <div class="col-md-offset-5">
                                        <ul id="resourceTree" class="ztree"></ul>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button" onclick="cancelAddRole()">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-primary" type="submit" id="addRoleSubmitBtn">
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
                        <%--删除--%>
                        <div class="display-hide" id="delRoleDiv">
                            <div class="space-10"></div>
                            <div class="space-10"></div>
                            <form class="form-horizontal" role="form" action="${ctx}/role/delete" method="post">
                                <input type="hidden" id="delRoleId" name="delRoleId"/>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="delRoleName"> 角色名称 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="delRoleName" name="delRoleName">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="delRoleDesc"> 角色描述 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="delRoleDesc" name="delRoleDesc">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button" onclick="cancelDelRole()">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-danger" type="submit" id="delRoleSbmitBtn">
                                            <i class="icon-bolt bigger-110"></i>
                                            删除
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="table-responsive" id="roleListDiv">
                            <table id="roletable" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th style="width:3%;" class="center">ID</th>
                                        <th style="width:10%;">角色名</th>
                                        <th style="width:18%;">描述</th>
                                        <th>资源列表</th>
                                        <th style="width:8%;">状态</th>
                                        <th style="width:10%;">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${roleList}" var="role">
                                        <tr>
                                            <td class="center">
                                                <a href="#">${role.id}</a>
                                            </td>
                                            <td>${role.role}</td>
                                            <td>${role.description}</td>
                                            <td>${resourceNames.get(role.id)}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${role.available == true}">
                                                        <span class="label label-sm label-success">角色可用</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-sm label-warning">角色不可用</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <div class="btn-group" roleid="${role.id}" rolename="${role.role}" desc="${role.description}">
                                                    <shiro:hasPermission name="role:update">
                                                        <button class="btn btn-xs btn-info" style="margin: 0;" id="modRoleBtn"
                                                                onclick="javascrtpt:window.location.href='${ctx}/role/update/${role.id}'">
                                                            <i class="icon-edit bigger-120"></i>
                                                        </button>
                                                    </shiro:hasPermission>
                                                    <shiro:hasPermission name="role:delete">
                                                        <button class="btn btn-xs btn-danger" style="margin: 0;margin-left:10px;"
                                                                id="delRoleBtn">
                                                            <i class="icon-trash bigger-120"></i>
                                                        </button>
                                                    </shiro:hasPermission>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <shiro:hasPermission name="role:create">
                                    <div style="float:right;">
                                        <button class="btn btn-primary" type="button" id="addRoleBtn">
                                            <i class="icon-plus bigger-110"></i>
                                            添加角色
                                        </button>
                                    </div>
                            </shiro:hasPermission>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->
        <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
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
<script type="text/javascript" src="${ctx}/assets/js/pages/system/role/createRoleValidate.js"></script>
<script type="text/javascript">
    $(function () {
        $("#addRoleBtn").on("click", function(e){
           e.preventDefault();
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
               var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
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
               $("#addRoleIds").val(id);
               $("#addResourceName").val(name);
           }
           var zNodes = [
               <c:forEach items="${resourceList}" var="r">
               <c:if test="${resource.parentId!=0}">
               { id:${r.id}, pId:${r.parentId}, name: "${r.name}", checked: false},
               </c:if>
               </c:forEach>
           ];
           //初始化zTree
           $.fn.zTree.init($("#resourceTree"), setting, zNodes);
           //获得zTree对象
           var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
            $("#createRoleDiv").toggleClass("display-hide");
            $("#roleListDiv").toggleClass("display-hide");
            $("input[name='addRoleAvailable']").prop("checked", true);
       });
    });
</script>
<script type="text/javascript" src="${ctx}/assets/js/pages/system/role/roleFun.js"></script>
</body>
</html>
