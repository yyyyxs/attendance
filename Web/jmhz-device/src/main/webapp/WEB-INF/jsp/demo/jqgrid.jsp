<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>jqGrid - Ace Admin</title>

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
        <!-- inline styles related to this page -->

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
                                <a href="#">Home</a>
                            </li>

                            <li>
                                <a href="#">Tables</a>
                            </li>
                            <li class="active">jqGrid plugin</li>
                        </ul><!-- .breadcrumb -->

                        <div class="nav-search" id="nav-search">
                            <form class="form-search">
                                <span class="input-icon">
                                    <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                                    <i class="icon-search nav-search-icon"></i>
                                </span>
                            </form>
                        </div><!-- #nav-search -->
                    </div>

                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                jqGrid
                                <small>
                                    <i class="icon-double-angle-right"></i>
                                    Dynamic tables and grids using jqGrid plugin
                                </small>
                            </h1>
                        </div><!-- /.page-header -->

                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <!--This is the table.-->
                                <table id="grid-table"></table>
                                <!--This is the pager.-->
                                <div id="grid-pager"></div>

                                <script type="text/javascript">
                                    var $path_base = "/";//this will be used in gritter alerts containing images
                                </script>

                                <!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div><!-- /.main-content -->

                <div class="ace-settings-container" id="ace-settings-container">
                    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                        <i class="icon-cog bigger-150"></i>
                    </div>

                    <div class="ace-settings-box" id="ace-settings-box">
                        <div>
                            <div class="pull-left">
                                <select id="skin-colorpicker" class="hide">
                                    <option data-skin="default" value="#438EB9">#438EB9</option>
                                    <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                    <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                    <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                </select>
                            </div>
                            <span>&nbsp; Choose Skin</span>
                        </div>

                        <div>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                            <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                        </div>

                        <div>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                            <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                        </div>

                        <div>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                            <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                        </div>

                        <div>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                            <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                        </div>

                        <div>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                            <label class="lbl" for="ace-settings-add-container">
                                Inside
                                <b>.container</b>
                            </label>
                        </div>
                    </div>
                </div><!-- /#ace-settings-container -->
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

        <!-- inline scripts related to this page -->

        <script type="text/javascript">
            $(function($) {
                var grid_selector = "#grid-table";
                var pager_selector = "#grid-pager";
                var lastFlag;
                $(grid_selector).jqGrid({
                    url : "${ctx}/testController/getUsers",
                    datatype: "json",
                    mtype: "GET",
                    height: 372,
                    colNames:[' ', 'ID','Username','Pwd'],
                    colModel:[
                        {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                            formatter:'actions',
                            formatoptions:{
                                keys:true,
                                delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback}
                                //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                            }
                        },
                        {name:'id',index:'id', width:60, sorttype:"int", editable: false,
                            formatter: makeURL
                        },
                        {name:'username',index:'username',width:90, editable:true, sorttype:"textarea",
                            edittype: 'select', editoptions: {value: getSelectNames("usernames")}},<!--select测试成功：返回类似["charkey","beyond"]-->
                        {name:'pwd',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}}
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
                    editurl: "${ctx}/testController/modify",
                    caption: "这是伟大的标题",<!--表格表头标题-->
                    autowidth: true,
                    loadComplete: function () {
                        var table = this;
                        setTimeout(function () {
                            styleCheckbox(table);
                            updateActionIcons(table);
                            updatePagerIcons(table);
                            enableTooltips(table);
                        }, 0);
                    },
                    onSelectRow: function (id) {
                        //if (id && id != lastFlag) {
                        //$(grid_selector).jqGrid('saveRow', lastFlag);
                        //    lastFlag = id;
                        //}
                        //$(grid_selector).jqGrid('editRow', id, true);
                    }
                });
                <!--jqgrid下拉选框-->
                function getSelectNames(type) {
                    var data;
                    if (type == "usernames") {
                        $.ajax({
                            url: "${ctx}/testController/getUsernames?type=usernames",
                            async: false,<!--一定要是同步-->
                            success: function (e) {
                                if (e != null) {
                                    data = e;
                                }
                            }
                        });
                    }
                    return data;<!--一定要有返回值-->
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
                    urlstring = "<a href='${ctx}/testController/toviewpage/"+rowObject["id"]+"'>"+cellvalue+"</a>";
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
                    //navbar options
                    {
                        edit: true,
                        editicon : 'icon-pencil blue',
                        add: true,
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
                    //edit record form
                    {
                        closeAfterEdit: true,//编辑后是否自动关闭窗口
                        recreateForm: true,
                        viewPagerButtons: false,//是否出现翻页按钮
                        beforeShowForm : function(e) {
                            var form = $(e[0]);
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                            style_edit_form(form);
                        }
                    },
                    //new record form
                    {
                        closeAfterAdd: true,
                        recreateForm: true,
                        viewPagerButtons: false,
                        beforeShowForm : function(e) {
                            var form = $(e[0]);
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                            style_edit_form(form);
                        }
                    },
                    //delete record form
                    {
                        recreateForm: true,
                        beforeShowForm : function(e) {
                            var form = $(e[0]);
                            if(form.data('styled')) return false;
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                            style_delete_form(form);
                            form.data('styled', true);
                        }
                    },
                    //search form
                    {
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
                    //view record form
                    {
                        recreateForm: true,
                        beforeShowForm: function(e){
                            var form = $(e[0]);
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        }
                    }
                )
                function style_edit_form(form) {
                    //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
                        .end().find('input[name=name]')
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
            
                function enableTooltips(table) {
                    $('.navtable .ui-pg-button').tooltip({container:'body'});
                    $(table).find('.ui-pg-div').tooltip({container:'body'});
                }
            
                //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
            });
        </script>
    </body>
</html>
