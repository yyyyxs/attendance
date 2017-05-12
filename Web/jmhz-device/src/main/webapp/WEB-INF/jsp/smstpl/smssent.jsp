<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>短信模板 - 发送历史</title>
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
                                <a> 短信模板 </a>
                            </li>
                            <li class="active"> 发送历史 </li>
                        </ul><!-- .breadcrumb -->
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
                                <!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
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

        <script type="text/javascript">
            $(function($) {
                var grid_selector = "#grid-table";
                var pager_selector = "#grid-pager";

                $(grid_selector).jqGrid({
                    // qrytype注意大小写，大于0表示查询党务政务的公开信息。默认的记录=0，党务公开=10，政务公开=20
                    url : "${ctx}/smssent/getAllSMSSent?qrytype=0",
                    datatype: "json",
                    mtype: "GET",
                    height: 'auto',
                    colNames:['编号','模板名称','发送给','发送日期','发送内容','操作'],
                    colModel:[
                        {name:'id',index:'id', width:50, editable: false, hidden: true},
                        {name:'tplname',index:'tplname', width: 33, editable: false, formatter: styleTplname},
                        {name:'smsto',index:'smsto', width: 20, editable: false},
                        {name:'senddate',index:'senddate',width: 18, editable:false},
                        {name:'smscontent',index:'smscontent',width: 150, editable:false},
                        {name:'operation',index:'', width:13, formatter: makeURLForOperation}
                    ],
                    viewrecords : true,
                    rowNum:10,
                    rowList:[10,20,30],
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
                    editurl: "${ctx}/appeal/modify",//nothing is saved
                    caption: "发送历史",
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

                function styleTplname(cellvalue, options, rowObject) {
                    if(cellvalue=='rar') {
                        return "当收到诉求后回复本短信";
                    } else if (cellvalue == 'rad') {
                        return "当诉求处理后回复本短信";
                    } else {
                        return "短信通知";
                    }
                }

                function makeURLForOperation(cellvalue, options, rowObject) {
                    var urlstring =
                            "<div class=\"btn-group\" id='operationBtns'>" +
                            "<button title=\"删除历史\" class=\"btn btn-xs btn-danger\" onclick='delSMSSent("+rowObject['id']+")' style=\"margin: 0;margin-left:10px;\" id=\"delSMSSentBtn\">" +
                                    "<i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
                            "</div>";
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

                //navButtons
                jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                    {     //navbar options
                        edit: false,
                        editicon : 'icon-pencil blue',
                        add: false,
                        addicon : 'icon-plus-sign purple',
                        del: true,
                        delicon : 'icon-trash red',
                        search: true,
                        searchicon : 'icon-search orange',
                        refresh: true,
                        refreshicon : 'icon-refresh green',
                        view: true,
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
                            form.closest('.ui-jqdialog').find('#v_smscontent').wrap('<div class="smscontent" />')
                        }
                    }
                )

                //jQuery(grid_selector).navSeparatorAdd(pager_selector, {
                //    sepclass: "ui-separator",
                //    sepcontent: ''
                //}).navButtonAdd(pager_selector, {
                //    caption: "",
                //    buttonicon: "icon-download-alt",
                //    onClickButton: function () {
                //        exportExcelDataByRange();
                //    },
                //    position: "last",
                //    title: "根据『时间范围』导出Excel"
                //}).navSeparatorAdd(pager_selector, {
                //    sepclass: "ui-separator",
                //    sepcontent: ''
                //}).navButtonAdd(pager_selector, {
                //    caption: "",
                //    buttonicon: "icon-arrow-down",
                //    onClickButton: function () {
                //        exportExcelDataByFilter();
                //    },
                //    position: "last",
                //    title:"根据『搜索条件』导出Excel"
                //});

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
            
                function enableTooltips(table) {
                    $('.navtable .ui-pg-button').tooltip({container:'body'});
                    $(table).find('.ui-pg-div').tooltip({container:'body'});
                }
            
                //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
                $(window).resize(function () {
                    $(grid_selector).setGridWidth($("#tableDiv").width());
                });


            });
        </script>
        <script type="text/javascript">
            function delSMSSent(id) {
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
                                    url: "${ctx}/smssent/delete",
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
            $("<div id='maskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
                    "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>请求导出中...</span></div>").css({
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
    </body>
</html>
