<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title><spring:message code="global.navMenu.appeal" /> - <spring:message code="global.subMenu.appealStatistics" /></title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/js/nvd3/nv.d3.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/pages/appeal/statistics-nvd3.css"/>
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
                    <li><i class="icon-home home-icon"></i> <a href="${ctx}/"><spring:message code="global.homepage" /></a></li>
                    <li><a><spring:message code="global.navMenu.appeal" /></a></li>
                    <li class="active">信息统计</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content" style="padding-bottom: 0;">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div id="chart1">
                                <svg></svg>
                            </div>
                        </div>
                        <div class="hr hr8 hr-double"></div>
                        <div class="space-10"></div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="col-sm-5">
                                    <div id="piechart-placeholder"></div>
                                </div>
                                <div class="col-sm-7">
                                    <table class="table table-bordered table-striped" id="statisticsTable">
                                        <tbody>
                                        <tr>
                                            <td width="40%">未解决</td>
                                            <td>
                                                <b>${unsolvedCount}</b>
                                                <input id="unsolvedCount" name="unsolvedCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>已上报上级协调解决</td>
                                            <td>
                                                <b>${reportedCount}</b>
                                                <input id="reportedCount" name="reportedCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>延时解决</td>
                                            <td>
                                                <b>${delayedCount}</b>
                                                <input id="delayedCount" name="delayedCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>正在解决</td>
                                            <td>
                                                <b>${doingCount}</b>
                                                <input id="doingCount" name="doingCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>已做好解释说明工作</td>
                                            <td>
                                                <b>${explainCount}</b>
                                                <input id="explainCount" name="explainCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>已解决</td>
                                            <td>
                                                <b>${finishCount}</b>
                                                <input id="finishCount" name="finishCount" hidden="hidden">
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i> </a>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script src="${ctx}/assets/js/nvd3/d3.v3.min.js"></script>
<script src="${ctx}/assets/js/nvd3/nv.d3.min.js"></script>
<script src="${ctx}/assets/js/jquery.easy-pie-chart.min.js"></script>
<!-- inline scripts related to this page -->
<script>
    var unsolvedColor = "#FFB752";
    var reportedColor = "#2091CF";
    var delayedColor = "#DA5430";
    var doingColor = "#AF4E96";
    var explainColor = "#C1D4ED";
    var finishColor = "#68BC31";
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
        var data = [
            { label: "未解决", data: ${unsolvedCount}, color: unsolvedColor},
            { label: "已上报上级协调解决", data: ${reportedCount}, color: reportedColor},
            { label: "延时解决", data: ${delayedCount}, color: delayedColor},
            { label: "正在解决", data: ${doingCount}, color: doingColor},
            { label: "已做好解释说明工作", data: ${explainCount}, color: explainColor},
            { label: "已解决", data: ${finishCount}, color: finishColor}
        ];
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
        placeholder.data('chart', data);
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
    nv.addGraph(function () {
        var chart = nv.models.discreteBarChart()
                        .x(function (d) {
                            return d.label
                        })
                        .y(function (d) {
                            return d.value
                        })
                        .valueFormat(d3.format(',.0f'))
                        .tooltipContent(function (key, x, y, e, graph) {
                            return '<span>' + y + ' ' + x + '</sapn>';
                        })
                        .staggerLabels(true)
                        //.staggerLabels(historicalBarChart[0].values.length > 8)
                        .tooltips(true)
                        .showValues(true)
                        .transitionDuration(250)
                ;
        chart.yAxis.tickFormat(d3.format(',.0f'));
        d3.select('#chart1 svg')
                .datum(historicalBarChart)
                .call(chart);
        nv.utils.windowResize(chart.update);
        return chart;
    });
    historicalBarChart = [
        {
            key: "Cumulative Return",
            values: [
                {
                    "label": "未解决",
                    "value": ${unsolvedCount},
                    "color": unsolvedColor
                } ,
                {
                    "label": "已上报上级协调解决",
                    "value": ${reportedCount},
                    "color": reportedColor
                } ,
                {
                    "label": "延时解决",
                    "value": ${delayedCount},
                    "color": delayedColor
                } ,
                {
                    "label": "正在解决",
                    "value": ${doingCount},
                    "color": doingColor
                } ,
                {
                    "label": "已做好解释说明工作",
                    "value": ${explainCount},
                    "color": explainColor
                } ,
                {
                    "label": "已解决",
                    "value": ${finishCount},
                    "color": finishColor
                }
            ]
        }
    ];
</script>
</body>
</html>