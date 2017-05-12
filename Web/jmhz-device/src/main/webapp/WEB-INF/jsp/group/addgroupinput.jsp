<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <a href="${ctx}/"><spring:message code="global.homepage" /></a>
        </li>

        <li>
            <a>集体管理</a>
        </li>
        <li class="active">添加诉求</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="mqcard" action="/group/addAppeal" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h2 style="margin-top:10px;">${user.city}${user.town}开展“五全”联系服务群众活动</h2>

                                    <h2 style="margin-top:10px;">民 情 登 记 卡</h2>
                                </td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1" colspan="2">诉求编号：
                                    <input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>
                            </tr>
                            <tr>
                                <td class="center col-xs-1">集体名称</td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="groupname" id="groupname" value="${group.groupname}" readonly="readonly">
                                </td>
                                <td class="center col-sm-1">
                                    集体负责人
                                </td>
                                <td class="center col-sm-2">
                                    <input class="input-large" type="text" name="groupchargername" id="groupchargername" value="${group.groupchargername}" readonly="readonly">
                                </td>
                                <td class="center col-sm-2">集体负责人联系号码</td>
                                <td class="center col-sm-4">
                                    <input class="input-large" type="text" name="groupchargertel" id="groupchargertel" style="float: left" value="${group.groupchargertel}" readonly="readonly">
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
                                        <option value="6">政策咨询</option>
                                        <option value="7">证照办理</option>
                                        <option value="8">群众投诉</option>
                                        <option value="9">其他</option>
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
                            <input name="id"  type="hidden" id="groupid" value=${group.id}>
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
    <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
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
