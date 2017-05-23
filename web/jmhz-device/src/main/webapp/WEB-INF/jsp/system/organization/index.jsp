<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.system" /> - <spring:message code="global.subMenu.organization" /></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
                        <a><spring:message code="global.navMenu.system" /></a>
                    </li>
                    <li class="active"><spring:message code="global.subMenu.organization" /></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="space-10"></div>
                        <div class="space-10"></div>
                        <div class="col-xs-2">
                            <div>
                                <ul id="simaTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div id="content" class="col-xs-10">
                            <div id="fordel">

                                <div class="tabbable tabs-left">
                                    <ul class="nav nav-tabs" id="myTab3">
                                        <li class="active">
                                            <a data-toggle="tab" href="#modifyName">
                                                <i class="pink icon-edit bigger-110"></i>
                                                修改名称
                                            </a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" href="#addNode">
                                                <i class="blue icon-plus bigger-110"></i>
                                                添加子节点
                                            </a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" href="#moveNode">
                                                <i class="icon-filter green"></i>
                                                移动节点
                                            </a>
                                        </li>
                                        <li class="">
                                            <a data-toggle="tab" href="#deleteNode">
                                                <i class="icon-cut red"></i>
                                                删除节点
                                            </a>
                                        </li>
                                    </ul>

                                    <div class="tab-content">
                                        <div id="modifyName" class="tab-pane active">
                                            <form class="form-horizontal" id="modifyNameForm">
                                                <div class="form-group">
                                                    <div class="space-10"></div>
                                                    <label class="col-sm-3 control-label no-padding-right">原名称</label>
                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="oldNodeName" name="oldNodeName" readonly />
                                                            <i class="icon-file blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">新名称</label>
                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="newNodeName" name="newNodeName"/>
                                                            <i class="icon-pencil blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="space-10"></div>
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button class="btn btn-sm btn-primary" id="modifyNameBtn" name="modifyNameBtn"
                                                            type="submit">
                                                        <i class="icon-ok bigger-110"></i>
                                                        提交修改
                                                    </button>
                                                    <div class="space-14"></div>
                                                </div>
                                            </form>
                                        </div>

                                        <div id="addNode" class="tab-pane">
                                            <form class="form-horizontal" id="addNodeForm">
                                                <div class="form-group">
                                                    <div class="space-10"></div>
                                                    <label class="col-sm-3 control-label no-padding-right">父节点名称</label>
                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="parentNodeName" name="parentNodeName" readonly/>
                                                            <i class="icon-file blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">子节点名称</label>
                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="childNodeName" name="childNodeName" />
                                                            <i class="icon-pencil blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="space-10"></div>
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button class="btn btn-sm btn-primary" id="addNodeBtn" name="addNodeBtn"
                                                            type="submit">
                                                        <i class="icon-ok bigger-110"></i>
                                                        确认添加
                                                    </button>
                                                    <div class="space-14"></div>
                                                </div>
                                            </form>
                                        </div>
                                        <div id="moveNode" class="tab-pane">
                                            <form class="form-horizontal" id="moveNodeForm">
                                                <div class="form-group">
                                                    <div class="space-10"></div>
                                                    <label class="col-sm-3 control-label no-padding-right">待移动节点</label>

                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="nodeToMove" name="nodeToMove" readonly />
                                                            <i class="icon-file blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label no-padding-right">移到此节点下</label>

                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="hidden" id="targetNodeId" name="targetNodeId" />
                                                            <input type="text" id="targetNodeName" name="targetNodeName" placeholder="点击选择目标节点" readonly/>
                                                            <i class="icon-pencil blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="col-md-offset-3 col-md-9 display-hide" id="contentForTree">
                                                    <ul id="selectTree" class="ztree"></ul>
                                                    <div class="space-14"></div>
                                                </div>
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button class="btn btn-sm btn-primary" id="moveNodebtn" name="moveNodebtn" type="submit">
                                                        <i class="icon-ok bigger-110"></i>
                                                        确认移动
                                                    </button>
                                                    <div class="space-14"></div>
                                                </div>
                                            </form>
                                        </div>
                                        <div id="deleteNode" class="tab-pane">
                                            <form class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="space-10"></div>
                                                    <label class="col-sm-3 control-label no-padding-right">节点名称</label>
                                                    <div class="col-sm-9">
                                                        <span class="input-icon">
                                                            <input type="text" id="delNodeName" name="delNodeName" readonly />
                                                            <i class="icon-file blue"></i>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="space-10"></div>
                                            </form>
                                            <div class="col-md-offset-3 col-md-9">
                                                <button class="btn btn-sm btn-danger" id="delNodeBtn" name="delNodeBtn">
                                                    <i class="icon-bolt bigger-110"></i>
                                                    确认删除
                                                </button>
                                                <div class="space-14"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <!-- PAGE CONTENT ENDS -->
                    <!-- /.col -->
                </div>
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
<!-- inline scripts related to this page -->
<script type="text/javascript" src="${ctx}/assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/pages/system/organization/organizationValidate.js"></script>
<script type="text/javascript">
    $(function () {
        var setting = {
            view: {
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    $("#oldNodeName").attr("value", treeNode.name);
                    $("#parentNodeName").attr("value", treeNode.name);
                    $("#nodeToMove").attr("value", treeNode.name);
                    $("#delNodeName").attr("value", treeNode.name);
                }
            }
        };
        var zNodes = [
            <c:forEach items="${organizationList}" var="o">
            {
                id:${o.id},
                pId:${o.parentId},
                name:"${o.name}",
                open:
                    <c:choose>
                        <c:when test="${o.parentId==0}">true</c:when>
                        <c:otherwise>false</c:otherwise>
                    </c:choose>,
                parentId: "${o.parentId}",
                parentIds:"${o.parentIds}",
                available:${o.available}
            },
            </c:forEach>
        ];
        $(document).ready(function () {
            //初始化zTree
            $.fn.zTree.init($("#simaTree"), setting, zNodes);
            //获得zTree对象
            var treeObj = $.fn.zTree.getZTreeObj("simaTree");
            var nodes = treeObj.getNodes();
            if (nodes.length > 0) {
                treeObj.selectNode(nodes[0]);//单独选中根节点中第一个节点
                $("#oldNodeName").attr("value", nodes[0].name);
                $("#parentNodeName").attr("value", nodes[0].name);
                $("#nodeToMove").attr("value", nodes[0].name);
                $("#delNodeName").attr("value", nodes[0].name);
            }
        });
    });
</script>
<script src="${ctx}/assets/js/pages/system/organization/organizationFun.js"></script>
</body>
</html>