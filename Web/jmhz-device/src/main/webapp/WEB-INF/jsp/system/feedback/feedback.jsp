<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.feedback" /></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/js/jquery-treetable/jquery.treetable.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/assets/js/jquery-treetable/jquery.treetable.theme.sima.css">
    <link rel="stylesheet" href="${ctx}/assets/css/pages/resource.css">
</head>
<body>
<%--导入头部banner--%>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
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
                    <li><i class="icon-home home-icon"></i> <a href="${ctx}/"><spring:message code="global.homepage" /></a></li>
                    <li class="active"><spring:message code="global.navMenu.feedback" /></li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">

                    <div class="col-xs-12">
                    <div class="tab-content no-border padding-24">
                    <div id="faq-tab-1" class="tab-pane fade active in">
                        <h4 class="blue">
                            <i class="icon-ok bigger-110"></i>
                            关于系统的普遍问题解答
                        </h4>

                        <div class="space-8"></div>

                        <div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                        <i class="pull-right icon-chevron-left" data-icon-hide="icon-chevron-down"
                                           data-icon-show="icon-chevron-left"></i>

                                        <i class="icon-user bigger-130"></i>
                                        &nbsp; 管理员管理的作用
                                    </a>
                                </div>

                                <div class="panel-collapse collapse" id="faq-1-1" style="height: 0px;">
                                    <div class="panel-body">
                                        本系统支持细粒度的权限控制，可以创建不同的管理员角色，分配给不一样的权限，从而实现较为灵活的系统权限控制；
                                        <br/>因此，设置不同权限的管理员，可以为系统的管理带来很大的便利性！
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <a href="#faq-1-2" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down"
                                           data-icon-show="icon-chevron-left"></i>

                                        <i class="icon-sort-by-attributes-alt"></i>
                                        &nbsp; 关于菜单管理
                                    </a>
                                </div>

                                <div class="panel-collapse collapse" id="faq-1-2">
                                    <div class="panel-body">
                                        菜单是系统权限控制的一部分，通过菜单管理对系统菜单进行权限、路径、名称等相关信息的配置，
                                        <br/>从而实现菜单灵活的控制。
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default display-hide">
                                <div class="panel-heading">
                                    <a href="#faq-1-3" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down"
                                           data-icon-show="icon-chevron-left"></i>

                                        <i class="icon-credit-card bigger-130"></i>
                                        &nbsp; Single-origin coffee nulla assumenda shoreditch et?
                                    </a>
                                </div>

                                <div class="panel-collapse collapse" id="faq-1-3">
                                    <div class="panel-body">
                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon
                                        officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf
                                        moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default display-hide">
                                <div class="panel-heading">
                                    <a href="#faq-1-4" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down"
                                           data-icon-show="icon-chevron-left"></i>

                                        <i class="icon-copy bigger-130"></i>
                                        &nbsp; Sunt aliqua put a bird on it squid?
                                    </a>
                                </div>

                                <div class="panel-collapse collapse" id="faq-1-4">
                                    <div class="panel-body">
                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon
                                        officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf
                                        moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <a href="#faq-1-5" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down"
                                           data-icon-show="icon-chevron-left"></i>

                                        <i class="icon-cog bigger-130"></i>
                                        &nbsp; 系统出现问题了怎么办？
                                    </a>
                                </div>

                                <div class="panel-collapse collapse" id="faq-1-5">
                                    <div class="panel-body">
                                        系统出现一些小问题是难免的，不过请不要担心，当系统出现问题时，请联系我们的工程师给予解决！
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    </div>
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
</body>
</html>