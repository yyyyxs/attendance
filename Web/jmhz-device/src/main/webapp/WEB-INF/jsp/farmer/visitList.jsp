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
    <title>农户走访时间管理</title>
    <meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />

    <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />

    <!-- inline styles related to this page -->
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <style>
        .ui-dialog .ui-dialog-titlebar {
            padding: .4em 1em;
            position: relative
        }
    </style>
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
                        <a>农户信息</a>
                    </li>
                    <li class="active">走访时间列表</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div id="tableDiv">
                            <!--This is the table.-->
                            <table id="grid-table"></table>
                            <!--This is the pager.-->
                            <div id="grid-pager"></div>
                        </div>
                        <script type="text/javascript">
                            var $path_base = "/";//this will be used in gritter alerts containing images
                        </script>

                        <div id="farmerVisitDialog" class="hide">
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th colspan="5">
                                        <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                            修改走访时间：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="date-picker col-xs-5" id="visittime" name="visittime" type="text" data-date-format="yyyy-mm-dd">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr style="margin: 0;" />
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th>
                                        <label class="control-label bolder blue" style="line-height: 20px;font-size: 18px;">
                                            修改走访备注：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="space-6"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea id="visitremark" name="visitremark" class="autosize-transition form-control"></textarea>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="delVisitDialog" class="hide">
                            <div class="alert alert-info bigger-110">
                                若是点击<b>[确认删除]</b><br />将会删除本走访记录！
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
    </div>
    <!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>
<!--日期插件js-->
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<!-- page specific plugin scripts -->
<script type="text/javascript">
    $('#visittime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('textarea[class*=autosize]').autosize({append: "\n"});

    function showUpdateVisitDialog(id, visittime, visitremark) {
        $("#visittime").val(visittime);
        $("#visitremark").val(visitremark);
        $("#farmerVisitDialog").removeClass('hide').dialog({
            height: 300,
            width: 600,
            resizable: false,
            modal: true,
            title: "农户走访时间修改",
            buttons: [
                {
                    html: "<i class='icon-arrow-left bigger-110'></i>&nbsp; 取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
                {
                    html: "<i class='icon-ok bigger-110'></i>&nbsp; 提交修改",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        if (false) {//$("#rateContent").val() == ''
                            $("#rateContent").addClass({"border": "1px solid #d16e6c"});
                            $("#errorNotRate").removeClass('hide');
                            return;
                        } else {
                            $.ajax({
                                type: "POST",
                                url: "${ctx}/farmerVisit/updateVisit",
                                data: {
                                    id: id,
                                    visittime: $("#visittime").val(),
                                    visitremark: $("#visitremark").val()
                                },
                                dataType: 'json',
                                success: function (data) {
                                    if (data.success) {
                                        $("#grid-table").jqGrid("setGridParam", {
                                            url: "${ctx}/farmerVisit/getAllVisitList/${farmerId}",
                                            datatype: "json" //设置数据类型
                                        }).trigger("reloadGrid");
                                        bootbox.dialog({
                                            message: "<span class='bigger-110'>农户走访时间修改成功！</span>",
                                            buttons: {
                                                "success": {
                                                    "label": "<i class='icon-ok'></i> 确定",
                                                    "className": "btn-sm btn-success",
                                                    "callback": function () {
                                                    }
                                                }
                                            }
                                        });
                                    } else {
                                        bootbox.dialog({
                                            message: "<span class='bigger-110'>农户走访时间修改失败！请联系系统管理员解决问题.</span>",
                                            buttons: {
                                                "success": {
                                                    "label": "<i class='icon-ok'></i> 确定",
                                                    "className": "btn-sm btn-success",
                                                    "callback": function () {
                                                        //Example.show("great success");
                                                    }
                                                }
                                            }
                                        });
                                    }
                                },
                                error: function (XMLHttpRequest, errorThrown) {
                                    bootbox.dialog({
                                        message: "<span class='bigger-110'>农户走访时间修改失败！请联系系统管理员解决问题.</span>",
                                        buttons: {
                                            "success": {
                                                "label": "<i class='icon-ok'></i> 确定",
                                                "className": "btn-sm btn-success",
                                                "callback": function () {
                                                    //Example.show("great success");
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                            $(this).dialog("close");
                        }
                    }
                }
            ]
        });
    }

    function showDeleteVisitDialog(id) {
        $("#delVisitDialog").removeClass('hide').dialog({
            resizable: false,
            modal: true,
            title: "确认删除？",
            buttons: [
                {
                    html: "<i class='icon-trash bigger-110'></i>&nbsp; 确认删除",
                    "class": "btn btn-danger btn-xs",
                    click: function () {
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/farmerVisit/deleteVisit",
                            data: {
                                id: id
                            },
                            dataType: 'json',
                            success: function (data) {
                                //删除该行，不与后台交互，减少一次数据库读取
                                $("#grid-table").jqGrid("delRowData", id);
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>删除成功.</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });
                            },
                            error: function (XMLHttpRequest, errorThrown) {
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>删除失败！请联系系统管理员解决问题.</span>",
                                    buttons: {
                                        "success": {
                                            "label": "<i class='icon-ok'></i> 确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                //Example.show("great success");
                                            }
                                        }
                                    }
                                });
                            }
                        });
                        $(this).dialog("close");
                    }
                }
                ,
                {
                    html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                }
            ]
        });
    }
</script>
<script type="text/javascript">
$(function ($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    $(grid_selector).jqGrid({
        url: "${ctx}/farmerVisit/getAllVisitList/${farmerId}",
        datatype: "json",
        mtype: "GET",
        height: 334,
        colNames: [ 'ID', '走访时间', '走访备注', '编辑'],
        colModel: [
            {name: 'id', index: 'id', width: 40},//ID
            {name: 'visittime', index: 'visittime', width: 90, editable: false},
            {name: 'visitremark', index: 'visitremark', editable: false, width: 90},
            {name: 'operation', index: '', width: 80, formatter: makeURLForOperation}
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: pager_selector,
        altRows: true,
        //toppager: true,
        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,
        prmNames: {
            page: "page", // 表示请求页码的参数名称
            rows: "rows", // 表示请求行数的参数名称
            sort: "id", // 表示用于排序的列名的参数名称
            order: "sord", // 表示采用的排序方式的参数名称
            search: "_search", // 表示是否是搜索请求的参数名称
            nd: "nd", // 表示已经发送请求的次数的参数名称
            id: "id", // 表示当在编辑数据模块中发送数据时，使用的id的名称
            oper: "oper", // operation参数名称
            editoper: "edit", // 当在edit模式中提交数据时，操作的名称
            addoper: "add", // 当在add模式中提交数据时，操作的名称
            deloper: "del", // 当在delete模式中提交数据时，操作的名称
            subgridid: "id", // 当点击以载入数据到子表时，传递的数据名称
            npage: null,
            totalrows: "totalrecords" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal
        },
        jsonReader: {
            root: "dataObj",//json中代表实际模型数据的入口
            total: "totalpages",//json中代表页码总数的数据
            page: "currentpage",//json中代表当前页码的数据
            records: "totalrecords",//json中代表数据行总数的数据
            repeatitems: false
        },
        onPaging: function () {

        },
        editurl: "${ctx}/appeal/modify",//nothing is saved
        caption: "农户走访时间列表",
        //shrinkToFit: false,
        autowidth: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
            //设置无数据显示时显示如下：
            var rowNum = $(grid_selector).jqGrid('getGridParam', 'records');
            if(rowNum==0){
                if ($("#norecords").html() == null) {
                    $(grid_selector).parent().append("<div id=\"norecords\" style='text-align: center;vertical-align: middle;'>无数据显示！</div>");
                }
            } else {
                $("#norecords").hide();
            }
        }
    });


    function makeURLForOperation(cellvalue, options, rowObject) {
        var visittime = rowObject["visittime"];
        var urlstring =
                "<div class=\"btn-group\" id='operationBtns'>" +
                "<button title=\"编辑走访记录\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick=showUpdateVisitDialog(" + rowObject["id"] + ",'" + rowObject["visittime"] + "','" + rowObject["visitremark"] + "')><i class=\"icon-edit bigger-120\" style=\"margin: 0;\"></i></button>" +
                "<button title=\"删除走访记录\" class=\"btn btn-xs btn-danger\" style=\"margin: 0;margin-left:10px;\" id=\"delRoleBtn\" onclick=showDeleteVisitDialog(" + rowObject["id"]+ ")><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
                "</div>";
        return urlstring;
    }

    //switch element when editing inline
    function aceSwitch(cellvalue, options, cell) {
        setTimeout(function () {
            $(cell).find('input[type=checkbox]')
                    .wrap('<label class="inline" />')
                    .addClass('ace ace-switch ace-switch-5')
                    .after('<span class="lbl"></span>');
        }, 0);
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid', pager_selector,
            {     //navbar options
                edit: false,
                add: false,
                del: false,
                search: false,
                refresh: true, refreshicon: 'icon-refresh green',
                view: false
            }
    )

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev': 'icon-angle-left bigger-140',
            'ui-icon-seek-next': 'icon-angle-right bigger-140',
            'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container: 'body'});
        $(table).find('.ui-pg-div').tooltip({container: 'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
    $(window).resize(function () {
        $(grid_selector).setGridWidth($("#tableDiv").width());
    });

});
</script>
</body>
</html>
