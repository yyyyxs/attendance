<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>党务政务 - 党务公开</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <%--导入头部css--%>
        <%@include file="/WEB-INF/jsp/common/import-css.jsp" %>
        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
        <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
        <!-- ace styles 必须保留！要覆盖ui.jqgrid.css的样式 -->
        <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
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
                                <a> 党务政务 </a>
                            </li>
                            <li class="active"> 党务公开 </li>
                        </ul><!-- .breadcrumb -->
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <form name="sendSingleSMS" id="sendSMSForm2">
                                    <table class="table table-bordered sima-custom-table">
                                        <tbody>
                                        <tr>
                                            <td class="center " colspan="6">
                                                <h4>党务公开</h4>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center col-xs-1">发送短信给全镇号码</td>
                                            <td class="center col-sm-1">
                                                <input class="input-medium" type="text" name="qrytownname" id="qrytownname" style="width: 100%">
                                            </td>
                                            <td class="center col-sm-1">
                                                <button type="button" class="btn btn-xs no-border btn-primary" id="qryTelsByTownNameBtn" title="通过所属镇查询号码">
                                                    <i class="icon-search bigger-110"></i>
                                                    查询号码
                                                </button>
                                            </td>
                                            <td class="center col-xs-1">发送短信给全村号码</td>
                                            <td class="center col-sm-1">
                                                <input class="input-medium" type="text" name="qryvillagename" id="qryvillagename" style="width: 100%">
                                            </td>
                                            <td class="center col-sm-1">
                                                <button type="button" class="btn btn-xs no-border btn-primary" id="qryTelsByVillageNameBtn" title="通过所属村查询号码">
                                                    <i class="icon-search bigger-110"></i>
                                                    查询号码
                                                </button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center col-xs-1">短信类型</td>
                                            <td colspan="3">
                                                <div id="smstypes" style="float: left;">
                                                    <select id="smsparenttype" name="smsparenttype" data-value="党务公开" class="smsparenttype" style="width:105px"></select>
                                                    <select id="smschildtype" name="smschildtype" class="smschildtype"></select>
                                                </div>
                                            </td>
                                            <td style="border-top:0;">是否公开：</td>
                                            <td>
                                                <input name="ispublic" id="ispublic" class="ace ace-switch ace-switch-6" type="checkbox">
                                                <span class="lbl"></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center">号码<br><span style="color: #F12B2B">（逗号分隔）</span></td>
                                            <td class="center" colspan="5">
                                                <textarea name="telnumbers" id="telnumbers" class="form-control"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="center">短信内容</td>
                                            <td class="center" colspan="5">
                                                <textarea name="smscontent" id="smscontent" class="form-control"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6" class="center">
                                                <button type="button" class="btn btn-error" id="clearBtn">
                                                    <i class="icon-remove bigger-110"></i>
                                                    清空
                                                </button>
                                                <button type="button" class="btn btn-primary" id="confirmAddTelsBtn">
                                                    <i class="icon-arrow-down bigger-110"></i>
                                                    添加发送
                                                    <i class="icon-plus bigger-110"></i>
                                                </button>
                                                <button type="button" class="btn btn-info" id="sendSMSBtn">
                                                    <i class="icon-ok bigger-110"></i>
                                                    确认发送
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <div id="tableDiv">
                                    <!--This is the table.-->
                                    <table id="grid-table"></table>
                                    <!--This is the pager.-->
                                    <div id="grid-pager"></div>

                                </div>
                            </div><!-- /.col -->
                        </div><!-- /.page-content -->
                    </div><!-- /.main-content -->
                <div id="confirmDelAppeal" class="hide">
                    <div class="alert alert-info bigger-110">
                        若是点击<b>[确认删除]</b><br />将会删除本条发送历史！
                    </div>
                </div>
                <%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
            </div><!-- /.main-container-inner -->
            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="icon-double-angle-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->
        <%--导入尾部js--%>
        <%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
        <!-- page specific plugin scripts -->
        <!--日期插件js-->
        <script src="${ctx}/assets/js/jquery-ui-1.10.3.full.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
        <script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
        <script src="${ctx}/assets/js/bootbox.min.js"></script>
        <!-- inline scripts related to this page -->
        <script src="${ctx}/assets/js/jquery-cxselect/jquery.cxselect.min.js"></script>
        <script src="${ctx}/assets/js/jquery-cxselect/smsTypesData.min.jsonData.js"></script>
        <!-- inline scripts related to this page -->

        <script type="text/javascript">
            $("#smstypes").cxSelect({
                url: "${ctx}/assets/js/jquery-cxselect/smsTypesData.min.jsonData.js",
                selects: ["smsparenttype", "smschildtype"],
                nodata: "none"
            });
            // 全选时的号码数组
            var numberArray = [];
            $(function($) {
                var grid_selector = "#grid-table";
                var pager_selector = "#grid-pager";

                $(grid_selector).jqGrid({
                    url : "${ctx}/farmer/getAllFarmer",
                    datatype: "json",
                    mtype: "GET",
                    height: 'auto',
                    colNames:['ID','姓名','性别','出生年月','所属镇','所属村','网格','政治面貌','联系号码'],
                    colModel:[

                        {name:'id',index:'id', width:40, sorttype:"int", editable: false},
                        {name:'householdername',index:'householdername',width:30, editable:false, sorttype:"textarea",
                            formatter: makeURL
                        },
                        {name:'gender',index:'gender', width:22,editable: true , editable: false, formatter: styleGender},
                        {name:'birthday',index:'birthday', width:30, sorttype:"int", editable: false},
                        {name:'town',index:'town', width:25, sorttype:"int", editable: false},
                        {name:'village',index:'village', width:25, sorttype:"int", editable: false},
                        {name:'grid',index:'grid', width:25, sorttype:"int", editable: false},
                        {name:'politicalstatus',index:'politicalstatus', width:40, sorttype:"int", editable: false, formatter: stylePoliticalStatus},
                        {name:'contactnumber',index:'contactnumber', width:60, sorttype:"int", editable: false},
                    ],
                    viewrecords : true,
                    rowNum:10,
                    rowList:[10,20,30,50,100,300,500,1000,3000,5000,10000],
                    pager : pager_selector,
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
                    onPaging : function() {

                    },
                    onSelectRow: onSelectRowForjqGrid,
                    onSelectAll: onSelectAllForjqGrid,
                    editurl: "${ctx}/farmer/modify",//nothing is saved
                    caption: "农户信息",
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

                function styleVisitTime (cellvalue, options, rowObject) {
                    var urlstring =
                            "<div class=\"btn-group\" id='operationBtns'>" +
                            "<button title=\"添加走访记录\" class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\" onclick=showAddVisitDialog(" + rowObject["id"] + ")><i class=\"icon-plus bigger-120\" style=\"margin: 0;\"></i></button>" +
                            "<button title=\"查看走访时间列表\" class=\"btn btn-xs btn-info\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick =\"window.open('${ctx}/farmerVisit/getVisitList/" + rowObject["id"] + "','_blank')\"><i class=\"icon-list bigger-120\" style=\"margin: 0;\"></i></button>" +
                            "</div>";
                    return urlstring;
                }

                function styleGender(cellvalue, options, rowObject) {
                    if(cellvalue =='0'){
                        return "男";
                    } else {
                        return "女";
                    }
                }

                function stylePoliticalStatus(cellvalue, options, rowObject) {
                    if(cellvalue =='0'){
                        return "群众";
                    } else if(cellvalue == '1') {
                        return "共青团员";
                    } else {
                        return "中共党员";
                    }
                }
                //enable search/filter toolbar
                //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
                function makeURL(cellvalue, options, rowObject) {
                    //cellvalue:要格式化的值，就是原本单元格的值，放置：<a>cellvalue</a>
                    //options["rowId"]:
                    //rowObject:当前行的值，可以这样取值：rowObject["id"]，rowObject["username"]，rowObject["pwd"]

                    //alert(rowObject["test"]);//此处的值为返回的json中对应的值。
                    //alert(options["rowId"]);
                    //alert(options["colModel"]["name"]);//此处返回的就是“filename”
                    var urlstring;
                    urlstring = "<a href='${ctx}/farmer/toviewpage/"+rowObject["id"]+"' target='_blank'>"+cellvalue+"</a>";
                    return urlstring;
                }
                //switch element when editing inline
                function aceSwitch( cellvalue, options, cell ) {
                    setTimeout(function(){
                        $(cell) .find('input[type=checkbox]')
                                .wrap('<label class="inline" />')
                                .addClass('ace ace-switch ace-switch-5')
                                .after('<span class="lbl"></span>');
                    }, 0);
                }
                //enable datepicker
                function pickDate( cellvalue, options, cell ) {
                    setTimeout(function(){
                        $(cell) .find('input[type=text]')
                                .datepicker({format:'yyyy-mm-dd' , autoclose:true});
                    }, 0);
                }

                //navButtons
                jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                        {     //navbar options
                            edit: false,
                            editicon : 'icon-pencil blue',
                            add: false,
                            addicon : 'icon-plus-sign purple',
                            del: false,
                            delicon : 'icon-trash red',
                            search: true,
                            searchicon : 'icon-search orange',
                            refresh: true,
                            refreshicon : 'icon-refresh green',
                            view: false,
                            viewicon : 'icon-zoom-in grey'
                        },
                        {
                            //edit record form
                            //closeAfterEdit: true,
                            recreateForm: true,
                            beforeShowForm : function(e) {
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
                            beforeShowForm : function(e) {
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                                style_edit_form(form);
                            }
                        },
                        {
                            //delete record form
                            recreateForm: true,
                            beforeShowForm : function(e) {
                                var form = $(e[0]);
                                if(form.data('styled')) return false;

                                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                                style_delete_form(form);

                                form.data('styled', true);
                            },
                            onClick : function(e) {
                                alert(1);
                            }
                        },
                        {
                            //search form
                            recreateForm: true,
                            afterShowSearch: function(e){
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                                style_search_form(form);
                            },
                            afterRedraw: function(){
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
                            beforeShowForm: function(e){
                                var form = $(e[0]);
                                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                            }
                        }
                )

//                jQuery(grid_selector).navSeparatorAdd(pager_selector, {
//                    sepclass: "ui-separator",
//                    sepcontent: ''
//                }).navButtonAdd(pager_selector, {
//                    caption: "",
//                    buttonicon: "icon-arrow-down",
//                    onClickButton: function () {
//                        exportExcelDataByFilter();
//                    },
//                    position: "last",
//                    title: "根据『搜索条件』导出Excel"
//                });


                function style_edit_form(form) {
                    //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
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
                    if(form.data('styled')) return false;

                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);

                    form.data('styled', true);
                }

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
                        'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
                        'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
                        'ui-icon-seek-next' : 'icon-angle-right bigger-140',
                        'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
                    };
                    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                        var icon = $(this);
                        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
                    })
                }

                // 表格全选时回调事件：将11位移动号码添加到数组numberArray
                function onSelectRowForjqGrid(rowid,status) {
                    var number = $(grid_selector).jqGrid('getRowData', rowid).contactnumber;
                    console.info(number);
                }

                // 表格全选时回调事件：将11位移动号码添加到数组numberArray
                function onSelectAllForjqGrid(aRowids,status) {
                    numberArray = [];
                    for(var i = 0; i < aRowids.length; i++) {
                        var number = $(grid_selector).jqGrid('getRowData', aRowids[i]).contactnumber;
                        if(number !== "" && number.length == 11) {
                            if(numberArray.indexOf(number) == -1 && status) {
                                //not exist
                                numberArray.push(number);
                            } else {
                                numberArray.splice(numberArray.indexOf(number), 1);
                            }
                        }
                    }
                }

                function enableTooltips(table) {
                    $('.navtable .ui-pg-button').tooltip({container:'body'});
                    $(table).find('.ui-pg-div').tooltip({container:'body'});
                }

                //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

                $(window).resize(function () {
                    $(grid_selector).setGridWidth($("#tableDiv").width());
                });
                $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
                "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>短信发送中...</span></div>").css({
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    backgroundColor: "#393939",
                    opacity: 0.5,
                    zIndex: 1040
                }).height($(document).height()).width($(document).width()).hide().appendTo("body");

            });
        </script>
        <script type="text/javascript">
            $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
                    "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>短信发送中...</span></div>").css({
                position: 'absolute',
                top: 0,
                left: 0,
                backgroundColor: "#393939",
                opacity: 0.5,
                zIndex: 1040
            }).height($(document).height()).width($(document).width()).hide().appendTo("body");
            //发送短信
            $("#sendSMSBtn").click(function(){
                if($("#telnumbers").val() == "" || $("#smscontent").val() == "") {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>发送号码或短信内容不能为空！</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                }
                            }
                        }
                    });
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: "${ctx}/smssent/sendSMS",
                    data: {
                        telnumbers: $("#telnumbers").val(),
                        smscontent: $("#smscontent").val(),
                        smsparenttype: $("#smsparenttype").val(),
                        smschildtype: $("#smschildtype").val(),
                        ispublic: $("input[name='ispublic']").prop("checked")
                    },
                    dataType: 'json',
                    beforeSend: function () {
                        $("#maskDIV").show();
                    },
                    success: function (data) {
                        if(data.success) {
                            bootbox.dialog({
                                message: "<span class='bigger-110'>短信发送成功</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            $("#maskDIV").fadeOut();
                                        }
                                    }
                                }
                            });
                        } else {
                            bootbox.dialog({
                                message: "<span class='bigger-110'>短信发送失败，原因："+data.msg+"，请联系系统管理员解决.</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            $("#telnumber").val("");
                                            $("#maskDIV").fadeOut();
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>系统异常，请联系系统管理员！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        $("#maskDIV").fadeOut();
                                    }
                                }
                            }
                        });
                    }
                });
            });
            //添加到待发送
            $("#addTelBtn").click(function(){
                if($("#telnumber").val() !== "" && $("#telnumber").val().length == 11) {
                    if($("#telnumbers").val() == "") {
                        $("#telnumbers").val($("#telnumber").val());
                    } else {
                        $("#telnumbers").val($("#telnumbers").val() + "," + $("#telnumber").val());
                    }
                    $("#telnumber").val("");
                    $("#qryname").val("");
                } else {
                    $("#telnumber").val("");
                    $("#qryname").val("");
                }
            });
            //清空待发送
            $("#clearBtn").click(function(){
                $("#telnumbers").val("");
                $("#smscontent").val("");
            });
            //添加发送
            $("#confirmAddTelsBtn").click(function(){
                $("#telnumbers").val(numberArray.toString());
            });
            //通过所属镇查询号码
            $("#qryTelsByTownNameBtn").click(function(){
                if($("#qrytownname").val() == "") {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>查询镇名不能为空</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                }
                            }
                        }
                    });
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: "${ctx}/smssent/qryTelsByTownName",
                    data: {
                        qrytownname: $("#qrytownname").val()
                    },
                    dataType: 'json',
                    beforeSend: function () {
                    },
                    success: function (data) {
                        if(data.success) {
                            $("#telnumbers").val(data.dataObj);
                        } else {
                            bootbox.dialog({
                                message: "<span class='bigger-110'>在当前登录管理员所属的县\\镇\\村\\网格查询不到记录，请手动输入号码!</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            $("#telnumbers").val("");
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>查询异常，请联系系统管理员！</span>",
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
            });
            //通过所属村查询号码
            $("#qryTelsByVillageNameBtn").click(function(){
                if($("#qryvillagename").val() == "") {
                    bootbox.dialog({
                        message: "<span class='bigger-110'>查询姓名不能为空</span>",
                        buttons: {
                            "success": {
                                "label": "<i class='icon-ok'></i> 确定",
                                "className": "btn-sm btn-success",
                                "callback": function () {
                                }
                            }
                        }
                    });
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: "${ctx}/smssent/qryTelsByVillageName",
                    data: {
                        qryvillagename: $("#qryvillagename").val()
                    },
                    dataType: 'json',
                    beforeSend: function () {
                    },
                    success: function (data) {
                        if(data.success) {
                            $("#telnumbers").val(data.dataObj);
                        } else {
                            bootbox.dialog({
                                message: "<span class='bigger-110'>在当前登录管理员所属的县\\镇\\村\\网格查询不到记录，请手动输入号码!</span>",
                                buttons: {
                                    "success": {
                                        "label": "<i class='icon-ok'></i> 确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            $("#telnumbers").val("");
                                        }
                                    }
                                }
                            });
                        }
                    },
                    error: function (XMLHttpRequest, errorThrown) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>查询异常，请联系系统管理员！</span>",
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
            });
        </script>
    </body>
</html>
