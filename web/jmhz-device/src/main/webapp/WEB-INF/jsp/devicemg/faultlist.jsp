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

                    <li class="active"> 故障申报</li>
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

<c:if test="${approve==1}">
    ${addres} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>你的申报已经通过或者设备已维修完成！</span>",
            buttons: {
                "success": {
                    "label": "<i class='icon-ok'></i> 确定",
                    "className": "btn-sm btn-success",
                    <%--"callback": function() {--%>
                    <%--//window.location.href=${ctx}+"/farnerController/show";--%>
                    <%--}--%>
                }
            }
        });

    </script>
</c:if>
<script type="text/javascript">
    $(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";

        $(grid_selector).jqGrid({
            url: "${ctx}/fault/getAllapply",
            datatype: "json",
            mtype: "GET",
            height: 'auto',
            colNames: ['id', '设备id', '设备名字', '设备类型', '设备使用者', '申报时间', '故障描述', '设备状态', '审核进度', '审核意见', '维修报告', '查看'],
            colModel: [
                {name: 'id', index: 'id', width: 20, sorttype: "int", editable: false, hidden: true},
                {name: 'deviceId', index: 'deviceId', width: 20, sorttype: "int", editable: false},
                {name: 'deviceName', index: 'deviceName', width: 30, editable: false},
                {name: 'deviceType', index: 'deviceType', width: 30, editable: false, formatter: styleType},
                {name: 'deviceUser', index: 'deviceUser', width: 30, editable: false},
                {name: 'applytime', index: 'applytime', width: 40, editable: false},
                {name: 'faultDescribe', index: 'faultDescribe', width: 80, editable: false},
                {name: 'status', index: 'status', width: 30, sorttype: "int", editable: false, formatter: styleStatus},
                {
                    name: 'approve',
                    index: 'approve',
                    width: 30,
                    sorttype: "int",
                    editable: false,
                    formatter: styleapprove
                },
                {name: 'approveRemark', index: 'approveRemark', width: 70, editable: false},
                {name: 'logmark', index: 'logmark', width: 30, editable: false, formatter: stylelogmark},
                {name: 'operation', index: 'operation', width: 30, formatter: makeURLForOperation},
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

        function makeURLForOperation(cellvalue, options, rowObject) {
            var urlstring =
                    "<div class=\"btn-group\" id='operationBtns'>" +
                    "<button title=\"查看申报情况\" class=\"btn btn-xs btn-info\" style=\"margin: 0;\" id=\"modRoleBtn\" " +
                    "onclick='viewCondition (" + rowObject['id'] + ",\"" + rowObject['approve'] + "\")''>" +
                    "<i class=\"icon-search bigger-120\" style=\"margin: 0;\"></i></button>" +
                    "<button title=\"添加维修日志\" class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\" " +
                    "onclick='addlog (" + rowObject['id'] + ",\"" + rowObject['approve'] + "\" ," + rowObject['logmark'] + ")''>" +
                    "<i class=\"icon-plus bigger-120\" style=\"margin: 0;\"></i></button>" +
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
    function sendSMS(id, uuid, appealtype) {
        // 后台获取短信模板和诉求信息，并动态替换模板生成短信息内容返回
        $.ajax({
            type: "POST",
            url: "${ctx}/smhlappeal/getSMSForSmhlAppeal/" + id,//获得短信详情
            dataType: 'json',
            success: function (data) {
                $("#uuid").attr("value", data.uuid);
                $("#appealtel").attr("value", data.appealtel);
                document.getElementById('appealContent').value = data.appealContent;
                document.getElementById('smsContent').value = data.smsContent;
            },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>短信模板获取失败！请联系系统管理员解决问题.</span>",
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
        $("#dialog-send-sms").removeClass('hide').dialog({
            height: "auto",
            width: 600,
            resizable: true,
            modal: true,
            title: "短信诉求处理回复",
            buttons: [
                {
                    html: "<i class='icon-mail-forward bigger-110'></i>&nbsp; 发送",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        $.ajax({
                            // TODO: 调用逻辑发送短信
                            type: "POST",
                            url: "${ctx}/smhlappeal/sendSMS",//发送短信
                            data: {
                                appealtel: $('#appealtel').val(),
                                smsContent: $('#smsContent').val()
                            },
                            dataType: 'json',
                            beforeSend: function () {
                                $("#sendSMSMaskDIV").show();
                            },
                            success: function (data) {
                                $("#sendSMSMaskDIV").fadeOut();

                                bootbox.dialog({
                                    message: "<span class='bigger-110'>短信发送结果：" + data.msg + "</span>",
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
                                $("#sendSMSMaskDIV").fadeOut();
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>短信发送失败！请联系系统管理员解决问题.</span>",
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
    $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
    "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>请求导出中...</span></div>").css({
        position: 'absolute',
        top: 0,
        left: 0,
        backgroundColor: "#393939",
        opacity: 0.5,
        zIndex: 1040
    }).height($(document).height()).width($(document).width()).hide().appendTo("body");
    $("<div id='sendSMSMaskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
    "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>短信发送中...</span></div>").css({
        position: 'absolute',
        top: 0,
        left: 0,
        backgroundColor: "#393939",
        opacity: 0.5,
        zIndex: 1040
    }).height($(document).height()).width($(document).width()).hide().appendTo("body");
    function exportExcelDataByFilter() {
        $.ajax({
            type: "POST",
            url: "${ctx}/appeal/export",
            data: {
                id: "1"//以后把搜索的参数从这边传到后台
            },
            dataType: 'json',
            beforeSend: function () {
                $("#maskDIV").show();
            },
            success: function (data) {
                $("#maskDIV").fadeOut();
                bootbox.dialog({
                    message: "<span class='bigger-110'>诉求信息正在导出为Excel，请稍后点击导出列表查看下载.</span>",
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
                $("#maskDIV").fadeOut();
                bootbox.dialog({
                    message: "<span class='bigger-110'>诉求信息导出失败！请联系系统管理员解决问题.</span>",
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
    }
    function exportExcelDataByRange() {
        $("#dialog-export-range").removeClass('hide').dialog({
            resizable: false,
            modal: true,
            title: "请选择起始时间和终止时间",
            buttons: [
                {
                    html: "<i class='icon-trash bigger-110'></i>&nbsp; 确认",
                    "class": "btn btn-success btn-xs",
                    click: function () {
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/appeal/exportData",
                            data: {
                                starttime: $("#starttime").val(),
                                endtime: $("#endtime").val()
                            },
                            dataType: 'json',
                            beforeSend: function () {
                                $("#maskDIV").show();
                            },
                            success: function (data) {
                                $("#maskDIV").fadeOut();
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>导出成功.</span>",
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
                                $("#tr" + $("#fileID").val()).remove();
                            },
                            error: function (XMLHttpRequest, errorThrown) {
                                $("#maskDIV").fadeOut();
                                bootbox.dialog({
                                    message: "<span class='bigger-110'>导诉求信息导出失败！请联系系统管理员解决问题.</span>",
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
    function viewCondition(id, approve) {
        if (approve == "0" || approve == "2") {

            window.open("${ctx}/fault/modifyapply/" + id);

        }
        else {
            bootbox.dialog({
                message: "<span class='bigger-110'>你的申报已经通过或者设备已维修完成</span>",
                buttons: {
                    "success": {
                        "label": "<i class='icon-ok'></i> 确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            //Example.show("great success");
                        }
                    }
                }
            })
        }

    }
</script>
<script type="text/javascript">
    function addlog(id, approve, logmark) {
        //alert(logmark);
        if (approve == "1" || approve == "3") {

            if (logmark == "1") {
                bootbox.dialog({
                    message: "<span class='bigger-110'>已有维修报告，不能再次添加！</span>",
                    buttons: {
                        "success": {
                            "label": "<i class='icon-ok'></i> 确定",
                            "className": "btn-sm btn-success"
                        }
                    }
                })
            }
            else {
                window.open("${ctx}/fault/faultLog/" + id);
            }

        }
        else {
            bootbox.dialog({
                message: "<span class='bigger-110'>未通过审核，你不能填写维修报告！</span>",
                buttons: {
                    "success": {
                        "label": "<i class='icon-ok'></i> 确定",
                        "className": "btn-sm btn-success"
                        //"callback": function () {
                        //Example.show("great success");
                        //}
                    }
                }
            })
        }

    }
</script>
</body>
</html>
