<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>信息统计 - 升级概况</title>
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
                    <%--<li><a><spring:message code="global.navMenu.appeal" /></a></li>--%>
                    <li>信息统计</li>
                    <li class="active">年份地点</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div>
                <table border="1" style="border-color: #00b3ee">
                    <tr>
                        <td>
                            <select id="positionOryear" name="positionOryear" >
                                <option value="0">显示所有年份设备量</option>
                                <option value="1">显示所有实验室设备量</option>
                            </select>
                        </td>
                        <td><input type="button" id="statisticYorP" class="btn btn-info"  value="统计"/></td>
                    </tr>
                </table>
            </div>
            <div class="page-content" style="padding-bottom: 0;">
                <div class="row">
                    <div class="col-xs-12">

                        <div class="row">
                            <div id="chart1">
                                <svg></svg>
                            </div>
                        </div>
                        <div class="space-10"></div>
                        <div class="hr hr8 hr-double"></div>
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
    var y2011Color = "#FFB752";
    var y2012Color = "#2091CF";
    var y2013Color = "#DA5430";
    var y2014Color = "#AF4E96";
    var y2015Color = "#C1D4ED";
    var y2016Color = "#68BC31";
    $("#statisticYorP").click(function(){
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
                    placeholder = $('#piechart-placeholder').css({'width': '80%', 'min-height': '178px'});

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
                    var myselect=document.getElementById("positionOryear");
                    var index=myselect.selectedIndex;
                    if(myselect.options[index].value=="0")
                    {
                        d3.select('#chart1 svg')
                                .datum(historicalBarChart1)
                                .call(chart);
                        nv.utils.windowResize(chart.update);
                    }else if(myselect.options[index].value=="1")
                    {
                        d3.select('#chart1 svg')
                                .datum(historicalBarChart2)
                                .call(chart);
                        nv.utils.windowResize(chart.update);
                    }
                    return chart;
                });
                historicalBarChart1 = [
                    {
                        key: "Cumulative Return",
                        values: [
                            {
                                "label": "2011年",
                                "value": ${y2011Count},
                                "color": y2011Color
                            } ,
                            {
                                "label": "2012年",
                                "value": ${y2012Count},
                                "color": y2012Color
                            } ,
                            {
                                "label": "2013年",
                                "value": ${y2013Count},
                                "color": y2013Color
                            } ,
                            {
                                "label": "2014年",
                                "value": ${y2014Count},
                                "color": y2014Color
                            } ,
                            {
                                "label": "2015年",
                                "value": ${y2015Count},
                                "color": y2015Color
                            }
                        ]
                    }
                ];
                historicalBarChart2 = [
                    {
                        key: "Cumulative Return",
                        values: [
                            {
                                "label": "301",
                                "value": ${p301Count},
                                "color": y2012Color
                            } ,
                            {
                                "label": "302",
                                "value": ${p302Count},
                                "color": y2013Color
                            } ,
                            {
                                "label": "303",
                                "value": ${p303Count},
                                "color": y2014Color
                            } ,
                            {
                                "label": "304",
                                "value": ${p304Count},
                                "color": y2015Color
                            } ,
                            {
                                "label": "305",
                                "value": ${p305Count},
                                "color": y2016Color
                            },
                            {
                                "label": "306",
                                "value": ${p306Count},
                                "color": y2011Color
                            }
                        ]
                    }
                ];
            }
    );
    var data = [
        { label: "2011年", data: ${y2011Count}, color: y2011Color},
        { label: "2012年", data: ${y2012Count}, color: y2012Color},
        { label: "2013年", data: ${y2013Count}, color: y2013Color},
        { label: "2014年", data: ${y2014Count}, color: y2014Color},
        { label: "2015年", data: ${y2015Count}, color: y2015Color}
    ];

    var data2 = [
        { label: "301", data: ${p301Count}, color: y2012Color},
        { label: "302", data: ${p302Count}, color: y2013Color},
        { label: "303", data: ${p303Count}, color: y2014Color},
        { label: "304", data: ${p304Count}, color: y2015Color},
        { label: "305", data: ${p305Count}, color: y2016Color},
        { label: "306", data: ${p306Count}, color: y2011Color}
    ];

</script>
</body>
</html>