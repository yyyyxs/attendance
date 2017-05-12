<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>设备管理 - 设备信息</title>

    <meta name="description" content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--导入头部css--%>
    <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css"/>

    <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css"/>
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
                        <a> 管理员设备管理</a>
                    </li>
                    <li class="active"> 设备信息</li>
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
                        <div id="confirmDelAppeal" class="hide">
                            <div class="alert alert-info bigger-110">
                                若是点击<b>[确认删除]</b><br/>将会删除本条设备信息！
                            </div>
                        </div>
                        <!-- PAGE CONTENT ENDS -->
                        <div id="picture" class="hide">
                            <div class="space-12"></div>
                            <img id="erweima" src="">

                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
        <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %><!-- /#ace-settings-container -->--%>
    </div>
    <!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->


<%--导入尾部js--%>
<%--<script src="${ctx}/assets/js/bootbox.min.js"></script>--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<!-- page specific plugin scripts -->
<script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${ctx}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.autosize.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
    function showerweima(id) {
//        var path = window.location.pathname.split("/")[0];
        $.ajax({
            type: "POST",
            url: path + "/equipment/getEquipmentById",
            data: {
                id: id
            },
            dataType: 'json',
            success:
                    function (data) {
                var equipment = data.dataObj;
                var erweima = document.getElementById("erweima");
                var str=equipment.codeUrl;

                var strs= new Array(); //定义一数组
                strs=str.split("\\"); //字符分
                 var len=strs.length;
                 //var strx="..\\"+"..\\"+"..\\"+"assets\\erweima\\"+strs[len-1];
                 var strx="..\\"+"..\\"+"..\\"+"device\\assets\\erweima\\"+strs[len-1];//部署到服务器时用这个，device作为项目名

                 erweima.src =strx;

                $("#picture").removeClass('hide').dialog({
                    height: 400,
                    width: 400,
                    resizable: false,
                    modal: true,
                    title: "二维码",
                    buttons: [
                        {
                            html: "<i class='icon-arrow-left bigger-110'></i>&nbsp; 确认",
                            "class": "btn btn-xs",
                            click: function () {
                                $(this).dialog("close");
                            }
                        },
                    ]
                });
            },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>无法获取二维码，请联系管理员</span>",
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
    function downweima(id) {
        //*****************
        //下载图片
//        var path = window.location.pathname.split("/")[0];
        $.ajax({
            type: "POST",
            url: path + "/equipment/downEeweima",
            data: {
                id: id
            },
            dataType: 'json',
            success:
                    function (data) {
                        var equipment = data.dataObj;
                        var erweima = document.getElementById("erweima");
                        var str=equipment.codeUrl;

                        var strs= new Array(); //定义一数组
                        strs=str.split("\\"); //字符分
                        var len=strs.length;
                        //var strx="..\\"+"..\\"+"..\\"+"assets\\erweima\\"+strs[len-1];//
                        var strx="..\\"+"..\\"+"..\\"+"device\\assets\\erweima\\"+strs[len-1];//部署到服务器时用这个，device作为项目名
                        erweima.src =strx;


                    },
            error: function (XMLHttpRequest, errorThrown) {
                bootbox.dialog({
                    message: "<span class='bigger-110'>无法获取二维码，请联系管理员</span>",
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

    $('#visittime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('textarea[class*=autosize]').autosize({append: "\n"});

    $(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";

        $(grid_selector).jqGrid({
            url: "${ctx}/equipment/getAlldevice",
            datatype: "json",
            mtype: "GET",
            height: 372,
            colNames: ['ID', '设备名字', '设备类型', '设备品牌', '购入价格', '购买时间', '存放地点', '使用者', '设备状态', 'CPU', '内存', '显卡', '设备其他信息', '设备二维码', '操作'],
            colModel: [

                {name: 'id', index: 'id', width: 20, sorttype: "int", editable: false, formatter: makeURL},
                {name: 'deviceName', index: 'deviceName', width: 30, editable: false, sorttype: "textarea"},
                {
                    name: 'deviceType',
                    index: 'deviceType',
                    width: 22,
                    editable: true,
                    editable: false,
                    formatter: deviceTypeStyle
                },
                {name: 'brand', index: 'brand', width: 22, editable: true, editable: false},
                {name: 'price', index: 'price', width: 22, editable: true, editable: false},
                {name: 'buyTime', index: 'buyTime', width: 40, editable: true, editable: false},
                {
                    name: 'position',
                    index: 'position',
                    width: 22,
                    sorttype: "int",
                    editable: false,
                    formatter: positionStyle
                },
                {name: 'deviceUser', index: 'deviceUser', width: 30, sorttype: "int", editable: false},
                {name: 'status', index: 'status', width: 25, sorttype: "int", editable: false, formatter: statusStyle},
                {name: 'CPU', index: 'CPU', width: 22, editable: true, editable: false},
                {name: 'internalMemory', index: 'internalMemory', width: 22, editable: true, editable: false},
                {name: 'graphicsCard', index: 'graphicsCard', width: 22, editable: true, editable: false},
                {name: 'otherInfo', index: 'otherInfo', width: 30, editable: true, editable: false},
                {
                    name: 'erweima',
                    index: 'erweima',
                    width: 25,
                    sorttype: "int",
                    editable: false,
                    formatter: makeURLForUUID
                },
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
            editurl: "${ctx}/equipment/delete",//nothing is saved
            caption: "设备信息",
            autowidth: true,
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    styleCheckbox(table);
                    updateActionIcons(table);
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });

        function statusStyle(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "使用中";
            } else if (cellvalue == '1') {
                return "废弃";
            }
            else if (cellvalue == '2') {
                return "维修中"
            }
            else {
                return "升级中"
            }
        }

        function deviceTypeStyle(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "公有";
            }
            else if (cellvalue == '1') {
                return "私有"
            }
        }

        function positionStyle(cellvalue, options, rowObject) {
            if (cellvalue == '0') {
                return "301";
            } else if (cellvalue == '1') {
                return "302";
            }
            else if (cellvalue == '2') {
                return "303"
            }
            else if (cellvalue == '3') {
                return "304"
            }
            else {
                return "305"
            }
        }

        function makeURLForOperation(cellvalue, options, rowObject) {
            var urlstring =
                    "<div class=\"btn-group\" id=\"operationBtns\">" +
                    "<button title=\"更新\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick =\"window.open('${ctx}/equipment/toviewpage/" + rowObject["id"] +
                    "','_blank')\">" +
                    "<i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +
                    "<button title=\"删除设备信息\" class=\"btn btn-xs btn-danger\" onclick='delAppeal(" + rowObject['id'] + ",\"" + rowObject['uuid'] + "\")' style=\"margin: 0;margin-left:10px;\" id=\"delAppealBtn\">" +
                    "<i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
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
            urlstring = "<a href='${ctx}/equipment/toviewpage/" + rowObject["id"] + "' target='_blank'>" + cellvalue + "</a>";
            return urlstring;
        }

        function makeURLForUUID(cellvalue, options, rowObject) {
            var urlstring =
                    "<button title=\"查看二维码\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" onclick=showerweima(" + rowObject['id'] + ")>" +
                    "<i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +
                    /*"<button title=\"下载\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" onclick=downweima(" + rowObject['id'] + ")>" +
                    "<i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +*/
                        //start   by 彭巍 2016-11
                        //需要改ip和端口
                    "<a href='http://123.207.96.94:8080/device/assets/erweima/" + rowObject["uuid"] + ".png'  download='"+rowObject["uuid"]+".png'>" + "下载"+ "</a>" +
                    "</div>";
            //end   by 彭巍 2016-11
                    "</div>";
            return urlstring;

        }


        function pickDate(cellvalue, options, cell) {
            setTimeout(function () {
                $(cell).find('input[type=text]')
                        .datepicker({format: 'yyyy-mm-dd', autoclose: true});
            }, 0);
        }


        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                {     //navbar options
                    edit: false,
                    editicon: 'icon-pencil blue',
                    add: false,
                    addicon: 'icon-plus-sign purple',
                    del: true,
                    delicon: 'icon-trash red',
                    search: true,
                    searchicon: 'icon-search orange',
                    refresh: true,
                    refreshicon: 'icon-refresh green',
                    view: true,
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
        )

       /* jQuery(grid_selector).navSeparatorAdd(pager_selector, {
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
        });*/
        //添加设备信息
     /*   jQuery(grid_selector).navSeparatorAdd(pager_selector, {
            sepclass: "ui-separator",
            sepcontent: ''
        }).navButtonAdd(pager_selector, {
            caption: "",
            buttonicon: "btn btn-xs btn-success",
            onClickButton: function () {
                makeURL();
            },
            position: "last",
            title: "添加设备"
        });*/

        function makeAdd(cellvalue, options, rowObject) {
            //cellvalue:要格式化的值，就是原本单元格的值，放置：<a>cellvalue</a>
            //options["rowId"]:
            //rowObject:当前行的值，可以这样取值：rowObject["id"]，rowObject["username"]，rowObject["pwd"]

            //alert(rowObject["test"]);//此处的值为返回的json中对应的值。
            //alert(options["rowId"]);
            //alert(options["colModel"]["name"]);//此处返回的就是“filename”
            var urlstring;
            /* urlstring = "<a href='
            ${ctx}/equipment/toviewpage/" + rowObject["id"] + "' target='_blank'>" + cellvalue + "</a>";*/
            urlstring =
                    "<div class=\"btn-group\" id='operationBtns'>" +

                    "<button title=\"更新\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick =\"window.open('${ctx}/equipment/toviewpage/" + rowObject["id"] + "','_blank')\"><i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +
                    "</div>";
            return urlstring;
        }
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
            buttons.find('.EditButton a[id*="_se arch"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
        }

        /* function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if (form.data('styled')) return false;

            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_delete_form(form);

            form.data('styled', true);
         }*/


        function beforeEditCallback(e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_edit_form(form);
        }

        //it causes some flicker when reloading or navigating grid
        //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
        //or go back to default browser checkbox styles for the grid
        function styleCheckbox(table) {
            /**
             $(table).find('input:checkbox').addClass('ace')
             .wrap('<label />')
             .after('<span class="lbl align-top" />')


             $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
             .find('input.cbox[type=checkbox]').addClass('ace')
             .wrap('<label />').after('<span class="lbl align-top" />');
             */
        }


        //unlike navButtons icons, action icons in rows seem to be hard-coded
        //you can change them like this in here if you want
        function updateActionIcons(table) {
            /**
             var replacement =
             {
                 'ui-icon-pencil' : 'icon-pencil blue',
                 'ui-icon-trash' : 'icon-trash red',
                 'ui-icon-disk' : 'icon-ok green',
                 'ui-icon-cancel' : 'icon-remove red'
             };
             $(table).find('.ui-pg-div span.ui-icon').each(function(){
                        var icon = $(this);
                        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
                        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
                    })
             */
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
        $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
        "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>请求导出中...</span></div>").css({
            position: 'absolute',
            top: 0,
            left: 0,
            backgroundColor: "#393939",
            opacity: 0.5,
            zIndex: 1040
        }).height($(document).height()).width($(document).width()).hide().appendTo("body");
    });
    function delAppeal(id) {
        var path = window.location.pathname.split("/")[0];
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
                            url: path + "/device/equipment/modify",//原本是modify，现在改用新方法delete
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
<c:if test="${updateRes == 1}">
    ${addres} = -1;
    <script>
        bootbox.dialog({
            message: "<span class='bigger-110'>更新成功！</span>",
            buttons: {
                "success": {
                    "label": "<i class='icon-ok'></i> 确定",
                    "className": "btn-sm btn-success",
                    "callback": function () {
                        //window.location.href=${ctx}+"/farmer/show";
                        //关闭窗口
                        this.window.opener = null;
                        window.close();
                    }
                }
            }
        });

    </script>
</c:if>
</body>
</html>
