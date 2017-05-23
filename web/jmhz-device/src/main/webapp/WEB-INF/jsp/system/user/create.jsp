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
    <title><spring:message code="global.navMenu.user" /> - <spring:message code="global.subMenu.userCreate" /></title>
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
                    <li class="active">添加用户</li>
                </ul><!-- .breadcrumb -->
            </div>
            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <c:if test="${not empty msg}">
                            <div class="alert alert-danger" id="msgbox">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                                <strong>
                                    &nbsp;&nbsp;&nbsp;${msg}
                                </strong>
                                <br>
                            </div>
                        </c:if>
                        <%--修改--%>
                        <div class="" id="createUserDiv">
                            <div class="space-10"></div>
                            <form class="form-horizontal" role="form" action="${ctx}/user/create" method="post" id="createUserForm">
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="username"> 用户名 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="username" name="username">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="password"> 密码 </label>

                                    <div class="col-sm-7">
                                        <input type="password" id="password" name="password">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="tel"> 联系号码 </label>

                                    <div class="col-sm-7">
                                        <input type="text" id="tel" name="tel">
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="roleIds"> 拥有角色 </label>

                                    <div class="col-sm-7">
                                        <select multiple="" class="chosen-select tag-input-style" id="roleIds" name="roleIds" style="width: 178px"
                                                data-placeholder="点击选择角色">
                                            <c:forEach items="${roleList}" var="role">
                                                <option value="${role.id}">${role.role}</option>
                                            </c:forEach>
                                        </select>
                                        <span id="roleIds-error"></span>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="studentName">
                                        学生姓名 </label>
                                    <%--<input type="hidden" id="organizationId" name="organizationId"/>--%>
                                    <div class="col-sm-7">
                                        <input type="text" id="studentName" name="studentName"
                                               placeholder="若是添加学生，添加下两栏">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="grade"> 年级</label>
                                    <div class="col-sm-7">
                                        <input type="text" id="grade" name="grade" placeholder="学生信息">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <%--<label class="col-sm-5 control-label no-padding-right" for="teacherId"> 所属导师</label>--%>
                                    <div class="col-sm-7">
                                        <select id="teacherId" name="teacherId"hidden="true">
                                            <option value=0 selected="selected">无</option>
                                            <c:forEach items="${userList}" var="user">
                                                <option value="${user.id}">${user.teacherName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label no-padding-right" for="teacherName">
                                        老师姓名</label>

                                    <div class="col-sm-7">
                                        <input type="text" id="teacherName" name="teacherName"
                                               placeholder="若是添加老师，填写这栏即可">
                                    </div>
                                </div>
                                <%--<div class="form-group display-hide" id="roleOrgTreeDiv">--%>
                                <%--<label class="col-sm-5 control-label no-padding-right" for="orgName"></label>--%>
                                <%--&lt;%&ndash;树&ndash;%&gt;--%>
                                <%--<div class="form-group">--%>
                                <%--<div class="col-md-offset-5">--%>
                                <%--<ul id="roleResourceTree" class="ztree"></ul>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
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
                                <%--  <div class="form-group">
                                      <label class="col-sm-5 control-label no-padding-right" for="locked"> 县-镇-村-网格: </label>
                                      <div class="col-sm-7">
                                          <div id="villageLocationInfo" style="float: left;">
                                              <select name="city" class="city" style="width:100px" data-value="沙县"></select>
                                              <select name="town" class="town" data-value="高桥镇"></select>
                                          </div>

                                      </div>
                                  </div>--%>
                                <%--  <div class="form-group">
                                      <label class="col-sm-5 control-label no-padding-right" for="auth_level1">账号行政级别: </label>
                                      <div class="col-sm-7">
                                          <div class="radio center">
                                              <div class="col-xs-3" id="auth_level1_div">
                                                  <label>
                                                      <input name="auth_level" type="radio" class="ace" id="auth_level1" value="1"/>
                                                      <span class="lbl"> 县/市级账号</span>
                                                  </label>
                                              </div>
                                              <div class="col-xs-3" id="auth_level2_div">
                                                  <label>
                                                      <input name="auth_level" type="radio" class="ace" id="auth_level2" value="2"/>
                                                      <span class="lbl"> 镇级账号</span>
                                                  </label>
                                              </div>
                                          </div>
                                      </div>
                                  </div>--%>
                                <div class="space-4"></div>
                                <div class="clearfix form-actions" style="margin-bottom:0;">
                                    <div class="center">
                                        <button class="btn btn-grey" type="button"
                                                onclick="javascrtpt:window.location.href='${ctx}/user/'">
                                            <i class="icon-arrow-left bigger-110"></i>
                                            取消
                                        </button>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-primary" type="submit" id="amodRoleSubmitBtn">
                                            <i class="icon-plus bigger-110"></i>
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
<script type="text/javascript" src="${ctx}/assets/js/bootstrap-tag.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/system/user/createUserValidate.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery-cxselect/jquery.cxselect.min.js"></script>
<script type="text/javascript">
    $(function () {
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
        $("#orgName").on("click", function (e) {
            $("#roleOrgTreeDiv").toggleClass("display-hide");
        });
        $("#msgbox").on("mouseover", function (e) {
            $("#msgbox").fadeOut("slow");
        });
        $("button").on("mouseover", function (e) {
            $("#msgbox").fadeOut(1800);
        });

        $("input[name='locked']").prop("checked", true);
        $("#villageLocationInfo").cxSelect({
            url: "${ctx}/assets/js/jquery-cxselect/shaXianVillageData.min.jsonData.js",
            selects: ["city", "town"],
            nodata: "none"
        });


        <c:if test="${user.auth_level == 1}">
            $("#auth_level2").attr("checked", "checked");
        </c:if>
        //权限控制，镇账号不能创建管理员，县市账号才能创建管理员
        <c:if test="${user.auth_level == 2}">
            $("#auth_level1").attr("disabled", "disabled");
            $("#auth_level2").attr("disabled", "disabled");
            document.getElementById("amodRoleSubmitBtn").remove();
        </c:if>
    });
    </script>
</body>
</html>
