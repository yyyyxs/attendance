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
                    <li><i class="icon-home home-icon"></i> <a href="#">Home</a></li>
                    <li><a href="#">Other Pages</a></li>
                    <li class="active">Blank Page</li>
                </ul>
                <!-- .breadcrumb -->
                <div class="nav-search" id="nav-search">
                    <form class="form-search">
                        <span class="input-icon"> <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                                                         autocomplete="off" /> <i class="icon-search nav-search-icon"></i> </span>
                    </form>
                </div>
                <!-- #nav-search -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="page-header">
                            <h1> 从这里插入页面内容！！
                                <small><i class="icon-double-angle-right"></i> 左边导航栏需要注意设置菜单class="active"</small>
                            </h1>
                        </div>
                        <div>
                            <ul id="treeDemo" class="ztree"></ul>
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
        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>
            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; Choose Skin</span>
                </div>
                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                    <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                </div>
                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                    <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                </div>
                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                    <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                </div>
                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                    <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                </div>
                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                    <label class="lbl" for="ace-settings-add-container"> Inside <b>.container</b> </label>
                </div>
            </div>
        </div>
        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i> </a>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- inline scripts related to this page -->
<script type="text/javascript" src="${ctx}/assets/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jquery.ztree.core-3.5.js"></script>


<SCRIPT type="text/javascript">
    <!--
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes = [
        { id: 1, pId: 0, name: "展开、折叠 自定义图标不同", open: true, iconOpen: "${ctx}/assets/css/zTreeStyle/img/diy/1_open.png", iconClose:
                "${ctx}/assets/css/zTreeStyle/img/diy/1_close.png"},
        { id: 11, pId: 1, name: "叶子节点1", icon: "${ctx}/assets/css/zTreeStyle/img/diy/2.png"},
        { id: 12, pId: 1, name: "叶子节点2", icon: "${ctx}/assets/css/zTreeStyle/img/diy/3.png"},
        { id: 13, pId: 1, name: "叶子节点3", icon: "${ctx}/assets/css/zTreeStyle/img/diy/5.png"},
        { id: 2, pId: 0, name: "展开、折叠 自定义图标相同", open: true, icon: "${ctx}/assets/css/zTreeStyle/img/diy/4.png"},
        { id: 21, pId: 2, name: "叶子节点1", icon: "${ctx}/assets/css/zTreeStyle/img/diy/6.png"},
        { id: 22, pId: 2, name: "叶子节点2", icon: "${ctx}/assets/css/zTreeStyle/img/diy/7.png"},
        { id: 23, pId: 2, name: "叶子节点3", icon: "${ctx}/assets/css/zTreeStyle/img/diy/8.png"},
        { id: 3, pId: 0, name: "不使用自定义图标", open: true },
        { id: 31, pId: 3, name: "叶子节点1"},
        { id: 32, pId: 3, name: "叶子节点2"},
        { id: 33, pId: 3, name: "叶子节点3"}

    ];

    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
    //-->
</SCRIPT>
</body>
</html>