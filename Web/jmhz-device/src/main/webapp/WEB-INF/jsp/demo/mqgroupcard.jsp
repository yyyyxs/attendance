<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>民情登记卡</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
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
            <a href="#">Home</a>
        </li>

        <li>
            <a href="#">Other Pages</a>
        </li>
        <li class="active">Blank Page</li>
    </ul>
    <!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                <i class="icon-search nav-search-icon"></i>
            </span>
        </form>
    </div>
    <!-- #nav-search -->
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="mqcard" action="/groupController/addgroupcard" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h2 style="margin-top:10px;">高桥镇开展“五全”联系服务群众活动</h2>

                                    <h2 style="margin-top:10px;">民 情 登 记 卡</h2>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1">集体名称</td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="groupname" id="groupname">
                                </td>
                                <td class="center col-sm-1">
                                    集体负责人
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="groupchargername" id="groupchargername">
                                </td>
                                <td class="center col-sm-2">集体负责人联系号码</td>
                                <td class="center col-sm-4">
                                    <input class="input-large" type="text" name="groupchargertel" id="groupchargertel" style="float: left">
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-sm-1">诉求类别</td>
                                <td class="center col-sm-2">
                                    <select class="form-control" name="appealtype" id="appealtype">
                                        <option value="0">发展生产</option>
                                        <option value="1">基础设施</option>
                                        <option value="2">矛盾纠纷</option>
                                        <option value="3">社会治安</option>
                                        <option value="4">生活救助</option>
                                        <option value="5">征地拆迁</option>
                                        <option value="6">其他</option>
                                    </select>
                                </td>
                                <td class="center col-sm-1">事务类别</td>
                                <td class="center col-sm-2">
                                    <select class="form-control" name="affairtype" id="affairtype">
                                        <option value="0">个人事务</option>
                                        <option value="1">公共事务</option>
                                    </select>
                                </td>
                                <td colspan="2"></td>
                            </tr>
                            <tr>
                                <td class="center">诉求内容</td>
                                <td class="center" colspan="5">
                                    <textarea name="hardshipappeal" id="hardshipappeal" class="form-control" style="height: 80px;"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <a href="javascript:mqcard.submit()" class="btn btn-primary"> <i class="icon-ok"> </i> 提交 </a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
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
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-navbar" />
            <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-sidebar" />
            <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-breadcrumbs" />
            <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-rtl" />
            <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
        </div>

        <div>
            <input type="checkbox" class="ace ace-checkbox-1" id="ace-settings-add-container" />
            <label class="lbl" for="ace-settings-add-container">
                Inside
                <b>.container</b>
            </label>
        </div>
    </div>
</div>
<!-- /#ace-settings-container -->
</div>
<!-- /.main-container-inner -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- page specific plugin scripts -->

</body>
</html>
