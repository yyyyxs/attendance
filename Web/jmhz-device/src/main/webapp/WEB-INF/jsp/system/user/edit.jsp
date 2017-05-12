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
    <title><spring:message code="global.navMenu.user" /> - <spring:message code="global.subMenu.userEdit" /></title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <link href="${ctx}/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.custom.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/chosen.css" />
    <!-- fonts -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ace-skins.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
    <![endif]-->
    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sima-custom-style.css" />
    <!-- ace settings handler -->
    <script src="${ctx}/assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/assets/js/html5shiv.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->
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
                        <a>用户管理</a>
                    </li>
                    <li class="active">修改用户信息</li>
                </ul><!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <%--修改--%>
                        <div class="" id="modifyUserDiv">
                            <div class="space-10"></div>
                            <form class="form-horizontal" role="form" action="${ctx}/user/update/${currentUser.id}" method="post" id="modifyUserForm">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="id"> 用户ID </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="id" name="id" value="${currentUser.id}" readonly>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="username"> 用户名 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="username" name="username" value="${currentUser.username}" readonly>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="tel"> 联系号码 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="tel" name="tel" value="${currentUser.tel}">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="roleIds"> 拥有角色 </label>

                                    <div class="col-sm-7">
                                        <select multiple="" class="chosen-select tag-input-style" id="roleIds" name="roleIds" style="width: 178px">
                                            <c:forEach items="${roleList}" var="role">
                                                <option value="${role.id}">${role.role}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="locked"> 用户状态 </label>
                                    <div class="col-sm-7">
                                        <label style="margin-bottom: -13px;">
                                            <input name="locked" id="locked" class="ace ace-switch ace-switch-6" type="checkbox">
                                            <span class="lbl"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button"
                                                onclick="javascrtpt:window.location.href='${ctx}/user/showUser'">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-primary" type="submit" id="amodRoleSubmitBtn">
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
            <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
            <!-- /#ace-settings-container -->
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
<script type="text/javascript" src="${ctx}/assets/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/bootstrap-tag.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery-cxselect/jquery.cxselect.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#villageLocationInfo").cxSelect({
            url: "${ctx}/assets/js/jquery-cxselect/shaXianVillageData.min.jsonData.js",
            selects: ["city", "town"],
            nodata: "none"
        });
        <c:forEach items="${roleIds}" var="ids">
            $("#roleIds option[value=${ids}]").attr("selected", "selected");
        </c:forEach>
        $("#auth_level" + ${currentUser.auth_level}).attr("checked", "checked");
        <c:if test="${user.auth_level == 1}">
            $("#auth_level2").attr("checked", "checked");
        </c:if>
        //权限控制，镇账号不能创建管理员，县市账号才能创建管理员
        <c:if test="${user.auth_level == 2}">
            $("#auth_level1").attr("disabled", "disabled");
            $("#auth_level2").attr("disabled", "disabled");
            document.getElementById("amodRoleSubmitBtn").remove();
        </c:if>
        $(".chosen-select").chosen();
        var parentTr = $(this).parent().parent().parent();
        var setting = {
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("roleResourceTree"),
                    nodes = zTree.getSelectedNodes(),
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
            $("#organizationId").val(id);
            $("#orgName").val(name);
            $("#roleOrgTreeDiv").toggleClass("display-hide");
        }

        var zNodes = [
            <c:forEach items="${organizationList}" var="o">
            <c:if test="${o.parentId!=0}">
            { id:${o.id}, pId:${o.parentId}, name: "${o.name}"},
            </c:if>
            </c:forEach>
        ];
        //初始化zTree
        $.fn.zTree.init($("#roleResourceTree"), setting, zNodes);
        $("#organizationId").val(${currentUser.organizationId});
        $("#orgName").on("click", function (e) {
            $("#roleOrgTreeDiv").toggleClass("display-hide");
        });

        if (!${currentUser.locked}) {
            $("input[name='locked']").prop("checked", true);
        }



    });
    </script>
<script type="text/javascript" src="${ctx}/assets/js/pages/system/user/modifyUserValidate.js"></script>
</body>
</html>
