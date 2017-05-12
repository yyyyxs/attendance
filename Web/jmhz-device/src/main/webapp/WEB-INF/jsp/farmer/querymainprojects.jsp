<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>农户统计 - 主要生产项目</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />

    <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
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
                        <a> 农户统计 </a>
                    </li>
                    <li class="active"> 主要生产项目 </li>
                </ul><!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="tabbable">
                            <ul class="nav nav-tabs" id="myTab">
                                <li class="active">
                                    <a data-toggle="tab" href="#total" onclick="modifytotal();">
                                        <i class="green icon-home bigger-110"></i>
                                        总计
                                    </a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="total" class="tab-pane in active">
                                    <div class=" infobox-container">
                                        <div>
                                            <div class="infobox infobox-green  ">
                                                <div class="infobox-icon">
                                                    <i class="icon-globe"></i>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-data-number">${farmerCount}</span>

                                                    <div class="infobox-content">总农户数</div>
                                                </div>

                                            </div>

                                            <div class="infobox infobox-blue  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${plantRatio}" data-size="46">
                                                        <span class="percent">${plantRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">种植业</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${farmerPlantCount}户
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-pink  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${farmingRatio}" data-size="46">
                                                        <span class="percent">${farmingRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">养殖业</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${farmerFarmingCount}户
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="infobox infobox-red  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${snackRatio}" data-size="46">
                                                        <span class="percent">${snackRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">小吃业</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${farmerSnackCount}户
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-orange2  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${workRatio}" data-size="46">
                                                        <span class="percent">${workRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">务工</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${farmerWorkCount}户
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-blue2  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${foundedRatio}" data-size="46">
                                                        <span class="percent">${foundedRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">创办实体</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${farmerFoundedCount}户
                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                                        <div class="space-6"></div>

                                        <div class="space-6"></div>

                                        <div id="tableDiv">
                                            <table id="grid-table-plant"></table>
                                            <!--This is the pager.-->
                                            <div id="grid-pager-plant"></div>
                                        </div>

                                    </div>
                                </div>
                                <div id="plant" class="tab-pane in  active">

                                </div>

                            </div>
                        </div>
                    </div><!-- /span -->
                </div>
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

<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/querymainprojects.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">

$(document).ready(function(){
    mainProjectsList();
    $('.easy-pie-chart.percentage').each(function(){
        var $box = $(this).closest('.infobox');
        var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
        var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
        var size = parseInt($(this).data('size')) || 50;
        $(this).easyPieChart({
            barColor: barColor,
            trackColor: trackColor,
            scaleColor: false,
            lineCap: 'butt',
            lineWidth: parseInt(size/10),
            animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
            size: size
        });
    })

    $('.sparkline').each(function(){
        var $box = $(this).closest('.infobox');
        var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
        $(this).sparkline('html', {tagValuesAttribute:'data-values', type: 'bar', barColor: barColor , chartRangeMin:$(this).data('min') || 0} );
    });
    //totallist();
    //modifytotal();
    $(window).resize(function () {
        $("#grid-table-plant").setGridWidth($("#tableDiv").width());
    });
});
    function modifytotal(){

        //$(".tab-pane").removeClass("in");
//        $(".tab-pane").removeClass("active");
//        //$("#total").addClass("in");
//        $("#total").addClass("active");

    }
function modifyplant(){

   // $(".tab-pane").removeClass("in");
//    $(".tab-pane").removeClass("active");
//    //$("#plant").addClass("in");
//    $("#plant").addClass("active");
//    $("#grid-table-plant").addClass("active");
//    $("#grid-pager-plant").addClass("active");
}
</script>
</body>
</html>
