<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>信息统计 - 设备统计</title>

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
<script src="${ctx}/assets/js/condition-statistics.js"></script>
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
                        <a> 信息统计 </a>
                    </li>
                    <li class="active"> 设备统计 </li>
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
                                                    <span class="infobox-data-number">${deviceCount}</span>

                                                    <div class="infobox-content">总设备数</div>
                                                </div>

                                            </div>

                                            <div class="infobox infobox-blue  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${usingRatio}" data-size="46">
                                                        <span class="percent">${usingRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">使用中</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${deviceUsingCount}台
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-pink  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${discardRatio}" data-size="46">
                                                        <span class="percent">${discardRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">废弃</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${deviceDiscardCount}台
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="infobox infobox-red  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${repairRatio}" data-size="46">
                                                        <span class="percent">${repairRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">维修</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${deviceRepairCount}台
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-orange2  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${updateRatio}" data-size="46">
                                                        <span class="percent">${updateRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">升级</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${deviceUpdateCount}台
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="infobox infobox-blue2  ">
                                                <div class="infobox-progress">
                                                    <div class="easy-pie-chart percentage" data-percent="${unuseRatio}" data-size="46">
                                                        <span class="percent">${unuseRatio}</span>%
                                                    </div>
                                                </div>

                                                <div class="infobox-data">
                                                    <span class="infobox-text">闲置</span>

                                                    <div class="infobox-content">
                                                        <span class="bigger-110">~</span>
                                                        ${deviceUnuseCount}台
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="space-6"></div>
                                        <div class="space-6"></div>
                                    </div>
                                </div>
                                <div id="plant" class="tab-pane in  active">

                                </div>
                                <div class="hr hr8 hr-double"></div>
                                <div class="space-10"></div>
                                <div >
                                    <table id="statistic" border="1" width="100%" style="border-color: #00b3ee">
                                        <tr>
                                            <td width="10%">按条件统计:</td>
                                            <td width="25%">
                                                <select class="form-control" name="statistictype" id="first"  onclick="setSecond(this)">
                                                    <option value="choice1">---请选择---</option>
                                                    <option value="time">年份</option>
                                                    <option value="position">地点</option>
                                                </select></td>
                                            <td  width="25%">
                                                <select class="form-control" id="second" >
                                                    <option value="choice2">---请选择---</option>
                                                </select>
                                            </td>
                                            <td align="center" width="25%">
                                                <button class="btn btn-info" id="staticBtn" type="submit" name="staticBtn">
                                                    <i class="icon-ok bigger-110"></i>
                                                    统计
                                                </button>
                                                <%--<input type="submit" class="btn btn-info" id="static-btn" value="统计" width="100%" >--%>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="space-6"></div>
                                <div class="space-6"></div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div id="piechart-placeholder"></div>
                                        <div class="col-sm-5">

                                        </div>
                                        <div class="col-sm-7">

                                        </div>
                                    </div>
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
<script src="${ctx}/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${ctx}/assets/js/nvd3/d3.v3.min.js"></script>
<script src="${ctx}/assets/js/nvd3/nv.d3.min.js"></script>
<script src="${ctx}/assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="${ctx}/assets/js/querydevices.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    $(document).ready(function(){
        deviceList();
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


        var path = window.location.pathname.split("/")[0];
        $("#staticBtn").click(function(){
            reloadPie();//点击触发调用reloadPie函数
        });
        function reloadPie()
        {
            $.ajax({
                type: "POST",
                url: path+"/statistics/showPie",
                data: {//把数据传给上面url的控制器
                    selectFirst: $("#first").val(),
                    selectSecond:$("#second").val()
                },
                dataType: 'json',
                success: function (data) {//成功时执行下面代码
                    if(data[0].data==0&&data[1].data==0&&data[2].data==0&data[3].data==0&&data[4].data==0)
                    {
                        data[0].label="无数据";
                        data[0].data=1;
                        data[0].color="#080808";
                        data[1].label=null;
                        data[2].label=null;
                        data[3].label=null;
                        data[4].label=null;
                    }
                    $(function () {
                        $('.easy-pie-chart.percentage').each(function () {
                            var $box = $(this).closest('.infobox');
                            var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
                            var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
                            var size = parseInt($(this).data('size')) || 50;
                            $(this).easyPieChart({
                                barColor: barColor,
                                trackColor: trackColor,
                                scaleColor: false,
                                lineCap: 'butt',
                                lineWidth: parseInt(size / 10),
                                animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
                                size: size
                            });
                        });
                        var placeholder = $('#piechart-placeholder').css({'width': '80%', 'min-height': '178px'});
                        function drawPieChart(placeholder, data, position) {
                            $.plot(placeholder, data, {
                                series: {
                                    pie: {
                                        show: true,
                                        tilt: 0.8,
                                        highlight: {
                                            opacity: 0.25
                                        },
                                        stroke: {
                                            color: '#fff',
                                            width: 2
                                        },
                                        startAngle: 2
                                    }
                                },
                                legend: {
                                    show: true,
                                    position: position || "ne",
                                    labelBoxBorderColor: null,
                                    margin: [-30, 15]
                                },
                                grid: {
                                    hoverable: true,
                                    clickable: true
                                }
                            })
                        }
                        drawPieChart(placeholder, data);
                        //placeholder.data('chart', data);
                        placeholder.data('draw', drawPieChart);
                        var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
                        var previousPoint = null;
                        placeholder.on('plothover', function (event, pos, item) {
                            if (item) {
                                if (previousPoint != item.seriesIndex) {
                                    previousPoint = item.seriesIndex;
                                    var tip = item.series['label'] + " : " + item.series['percent'] + '%';
                                    $tooltip.show().children(0).text(tip);
                                }
                                $tooltip.css({top: pos.pageY + 10, left: pos.pageX + 10});
                            } else {
                                $tooltip.hide();
                                previousPoint = null;
                            }

                        });
                    });
                },
                error: function (data)
                {
                    alert("您没选择，请重新操作");
                    window.location.reload();
                }
            })
            // form.submit(); // form validation success, call ajax form submit
        }
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
