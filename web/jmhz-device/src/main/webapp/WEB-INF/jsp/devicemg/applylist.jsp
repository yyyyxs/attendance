<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>维修审核进度</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css"/>
    <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css"/>
    <!--日期插件css-->
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
    <!-- inline styles related to this page -->
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
                        <a href="${ctx}/"><spring:message code="global.homepage"/></a>
                    </li>

                    <li>
                        <a> 故障申报</a>
                    </li>

                    <li class="active"> 故障申报进度</li>
                </ul>
                <!-- .breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div id="tableDiv">
                            <!--This is the table.-->
                            <table id="grid-table"></table>
                            <!--This is the pager.-->
                            <div id="grid-pager"></div>
                        </div>
                        <script type="text/javascript">
                            var $path_base = "/";//this will be used in gritter alerts containing images
                        </script>
                        <div id="ApprvoeOpinion" class="hide">
                            <div class="space-12"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th colspan="5">
                                        <label class="control-label bolder blue"
                                               style="line-height: 20px;font-size: 18px;">
                                            设备状态：
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td>
                                        <select class="form-control" name="status"
                                                id="status">
                                            <option value="0">使用中</option>
                                            <option value="1">报废</option>
                                            <option value="2">维修中</option>
                                            <option value="3">升级中</option>
                                        </select>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr style="margin: 0;"/>
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th>
                                        <label class="control-label bolder blue"
                                               style="line-height: 20px;font-size: 18px;">
                                            审核进度：
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
                                        <select class="form-control" name="approve"
                                                id="approve">
                                            <option value="0">待审核</option>
                                            <option value="1">同意维修</option>
                                            <option value="2">拒绝维修</option>
                                            <option value="3">已维修</option>
                                        </select>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                            <hr style="margin: 0;"/>
                            <div class="space-6"></div>
                            <table width="100%">
                                <tbody>
                                <tr>
                                    <th>
                                        <label class="control-label bolder blue"
                                               style="line-height: 20px;font-size: 18px;">
                                            备注：
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
                                                <textarea id="approveRemark" name="approveRemark"
                                                          class="autosize-transition form-control"></textarea>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
        <%--<div style="position: absolute;right: 20px;top: 50px;z-index: 12;">--%>
        <%--<button class="btn btn-primary no-border" type="button" id="exportAppealBtn" style="height:40px;">--%>
        <%--<i class="icon-download-alt bigger-110"></i>--%>
        <%--导出Excel--%>
        <%--</button>--%>
        <%--</div>--%>
        <div id="confirmDelAppeal" class="hide">
            <div class="alert alert-info bigger-110">
                若是点击<b>[确认删除]</b><br/>将会删除本条申请！
            </div>
        </div>
        <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
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
<!--日期插件js-->
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    function showApproveDialog(id) {
//        var path = window.location.pathname.split("/")[0];
        $.ajax({
            type: "POST",
            url: path + "/fault/getfaultById",
            data: {
                id: id
            },
            dataType: 'json',
            success: function (data) {
                var fault = data.dataObj;
                document.getElementById("status").value = fault.status;
                document.getElementById("approve").value = fault.approve;
                document.getElementById("approveRemark").value = fault.approveRemark;
        $("#ApprvoeOpinion").removeClass('hide').dialog({
            height: 300,
            width: 600,
            resizable: false,
            modal: true,
            title: "审核意见",
            buttons: [
                {
                    html: "<i class='icon-arrow-left bigger-110'></i>&nbsp; 取消",
                    "class": "btn btn-xs",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
                {
                    html: "<i class='icon-ok bigger-110'></i>&nbsp; 确认",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/fault/approveopinion",
                            data: {
                                id: id,
                                status: $("#status").val(),
                                approve: $("#approve").val(),
                                approveRemark: $("#approveRemark").val()
                            },
                            dataType: 'json',
                            success: function (data) {
                                if (data.success) {
                                    $("#grid-table").jqGrid("setGridParam").trigger("reloadGrid");
                                    bootbox.dialog({
                                        message: "<span class='bigger-110'>修改审核意见成功</span>",
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
                                        message: "<span class='bigger-110'修改审核意见失败！请联系系统管理员解决问题.</span>",
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
                                    message: "<span class='bigger-110'>修改审核意见失败！请联系系统管理员解决问题.</span>",
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
            ]
        });
            },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>无法获取失败原因，请联系管理员</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                            }
                        }
                    }
                });
            }
        });
    }
    $(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";


        $(grid_selector).jqGrid({
            url: "${ctx}/fault/getAllapply",
            datatype: "json",
            mtype: "GET",
            height: 'auto',
            colNames: ['id', '设备id', '设备名字', '设备类型', '设备使用者', '申报时间', '故障描述', '设备状态', '审核进度', '审核意见', '维修报告', '操作'],
            colModel: [
                {name: 'id', index: 'id', width: 20, sorttype: "int", editable: false, formatter: makeURL},
                {name: 'deviceId', index: 'deviceId', width: 20, sorttype: "int", editable: false},
                {name: 'deviceName', index: 'deviceName', width: 30, editable: false},
                {name: 'deviceType', index: 'deviceType', width: 30, editable: false, formatter: styleType},
                {name: 'deviceUser', index: 'deviceUser', width: 30, editable: false},
                {name: 'applytime', index: 'applytime', width: 40, editable: false},
                {name: 'faultDescribe', index: 'faultDescribe', width: 100, editable: false},
                {name: 'status', index: 'status', width: 30, sorttype: "int", editable: true, formatter: styleStatus},
                {
                    name: 'approve',
                    index: 'approve',
                    width: 30,
                    sorttype: "int",
                    editable: true,
                    formatter: styleapprove
                },
                {name: 'approveRemark', index: 'approveRemark', width: 100, editable: false},
                {name: 'logmark', index: 'logmark', width: 30, editable: false, formatter: stylelogmark},
                {name: 'operation', index: '', width: 30, formatter: makeURLForOperation}
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
            editurl: "${ctx}/fault/deletes",//nothing is saved


            //shrinkToFit: false,
            autowidth: true,
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });
        function makeURLForOperation(cellvalue, options, rowObject) {
            var urlstring =
                    "<div class=\"btn-group\" id='operationBtns'>" +
                    "<button title=\"审核意见\" class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\" onclick=showApproveDialog(" + rowObject["id"] + ")>" +
                    "<i class=\"icon-plus bigger-120\" style=\"margin: 0;\"></i></button>" +
                    "<button title=\"查看维修报告\" class=\"btn btn-xs btn-info\" style=\"margin: 0;\" id=\"modRoleBtn\" " +
                    "onclick='showlog (" + rowObject['id'] + "," + rowObject['logmark'] + ")''\"><i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +
                    "</div>";
            return urlstring;
        }
        function makeURL(cellvalue, options, rowObject) {
            //cellvalue:要格式化的值，就是原本单元格的值，放置：<a>cellvalue</a>
            //options["rowId"]:
            //rowObject:当前行的值，可以这样取值：rowObject["id"]，rowObject["username"]，rowObject["pwd"]

            //alert(rowObject["test"]);//此处的值为返回的json中对应的值。
            //alert(options["rowId"]);
            //alert(options["colModel"]["name"]);//此处返回的就是“filename”
            var urlstring;
            urlstring = "<a href='${ctx}/fault/toviewpage/" + rowObject["id"] + "' target='_blank'>" + cellvalue + "</a>";
            return urlstring;
        }

        function styleType(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "公有";
            } else if (cellvalue == '1') {
                return "私有";
            }

        }


        function styleapprove(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "审核中";
            } else if (cellvalue == '1') {
                return "同意维修";
            }
            else if (cellvalue == '2') {
                return "拒绝维修";
            }
            else {
                return "已维修";
            }

        }

        function styleStatus(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "使用中";
            } else if (cellvalue == '1') {
                return "废弃";
            } else if (cellvalue == '2') {
                return "维修中";
            } else if (cellvalue == '3') {
                return "升级中";
            }

        }

        function stylelogmark(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "无";
            } else if (cellvalue == '1') {
                return "有";
            }

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
                    editicon: 'icon-pencil blue',
                    add: false,
                    addicon: 'icon-plus-sign purple',
                    del: false,
                    delicon: 'icon-trash red',
                    search: true,
                    searchicon: 'icon-search orange',
                    refresh: true,
                    refreshicon: 'icon-refresh green',
                    view: false,
                    viewicon: 'icon-zoom-in grey'
                },
                {
                    //edit record form
                    //closeAfterEdit: true,
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //new record form
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //delete record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        if (form.data('styled')) return false;

                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_delete_form(form);

                        form.data('styled', true);
                    },
                    onClick: function (e) {
                        alert(1);
                    }
                },
                {
                    //search form
                    recreateForm: true,
                    afterShowSearch: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        style_search_form(form);
                    },
                    afterRedraw: function () {
                        style_search_filters($(this));
                    }
                    ,
                    multipleSearch: true
                    /**
                     multipleGroup:true,
                     showQuery: true
                     */
                },
                {
                    //view record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    }
                }
        );

        jQuery(grid_selector).navSeparatorAdd(pager_selector, {
            sepclass: "ui-separator",
            sepcontent: ''
        }).navButtonAdd(pager_selector, {
            caption: "",
            buttonicon: "icon-download-alt",
            onClickButton: function () {
                exportExcelDataByRange();
            },
            position: "last",
            title: "根据『时间范围』导出Excel"
        }).navSeparatorAdd(pager_selector, {
            sepclass: "ui-separator",
            sepcontent: ''
        }).navButtonAdd(pager_selector, {
            caption: "",
            buttonicon: "icon-arrow-down",
            onClickButton: function () {
                exportExcelDataByFilter();
            },
            position: "last",
            title: "根据『搜索条件』导出Excel"
        });

        function style_edit_form(form) {
            //enable datepicker on "sdate" field and switches for "stock" field
            form.find('input[name=sdate]').datepicker({format: 'yyyy-mm-dd', autoclose: true})
                    .end().find('input[name=stock]')
                    .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

            //update buttons classes
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
            buttons.eq(1).prepend('<i class="icon-remove"></i>')

            buttons = form.next().find('.navButton a');
            buttons.find('.ui-icon').remove();
            buttons.eq(0).append('<i class="icon-chevron-left"></i>');
            buttons.eq(1).append('<i class="icon-chevron-right"></i>');
        }

        function style_delete_form(form) {
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
            buttons.eq(1).prepend('<i class="icon-remove"></i>')
        }

        function style_search_filters(form) {
            form.find('.delete-rule').val('X');
            form.find('.add-rule').addClass('btn btn-xs btn-primary');
            form.find('.add-group').addClass('btn btn-xs btn-success');
            form.find('.delete-group').addClass('btn btn-xs btn-danger');
        }

        function style_search_form(form) {
            var dialog = form.closest('.ui-jqdialog');
            var buttons = dialog.find('.EditTable')
            buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
            buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
            buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
        }

        function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if (form.data('styled')) return false;

            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_delete_form(form);

            form.data('styled', true);
        }

        function beforeEditCallback(e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_edit_form(form);
        }

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
<script type="text/javascript">
    $('textarea[class*=autosize]').autosize({append: "\n"});
    // 日期插件
    $('#starttime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
        console.info("dnc");
    });
    // 日期插件
    $('#endtime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    function delAppeal(id, uuid) {
        $("#confirmDelAppeal").removeClass('hide').dialog({
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
                            url: "${ctx}/smhlappeal/delete",//原本是modify，现在改用新方法delete
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
    function showlog(id, logmark) {
        if (logmark == "0") {
            bootbox.dialog({
                message: "<span class='bigger-110'>该条申请记录无维修报告！</span>",
                buttons: {
                    "success": {
                        "label": "<i class='icon-ok'></i> 确定",
                        "className": "btn-sm btn-success"
                    }
                }
            })
        }

        else {
            window.open("${ctx}/fault/showlog/" + id);
        }


    }
</script>
</body>
</html>
