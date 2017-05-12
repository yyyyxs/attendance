<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>个人信息 - 查看信息</title>
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
            <a href="${ctx}/"><spring:message code="global.homepage" /></a>
        </li>

        <li>
            <a>个人信息</a>
        </li>
        <li class="active">查看详情</li>
    </ul>
    <!-- .breadcrumb -->
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <form name="usercard" id="usercard" action="" method="post">
                        <table class="table table-bordered sima-custom-table">
                            <tbody>
                            <tr>
                                <td class="center " colspan="6">
                                    <h1 style="margin-top:10px;">用 户 信 息 卡</h1>

                                    <%--<h2 style="margin-top:10px;"></h2>--%>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td class="center col-xs-1" colspan="2">诉求编号：--%>
                                    <%--<input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}"></td>--%>
                            <%--</tr>--%>
                            <%-- <tr class="border-bottom-2px">
                                 <td colspan="2" class="center">
                                     <div class="form-group">
                                         <label class="control-label no-padding-right">诉求编号：</label>
                                         <input class="input-medium" type="text" name="uuid" id="uuid" value="${appeal.uuid}">

                                     </div>
                                 </td>
                                 <td colspan="5">
                                     <div class="form-group">
                                         <label class="control-label no-padding-right">&nbsp;</label>
                                         <input class="input-small" type="text" placeholder="村" name="village" id="village">
                                         <label class="control-label no-padding-right">村&nbsp;</label>
                                         <input class="input-small" type="text" placeholder="网格" name="grid" id="grid">
                                         <label class="control-label no-padding-right">网格&nbsp;</label>
                                         <input class="input-small" type="text" placeholder="户" name="family" id="family">
                                         <label class="control-label no-padding-right">户&nbsp;</label>
                                         <label class="control-label no-padding-right">&nbsp;&nbsp;&nbsp;&nbsp;网格责任人&nbsp;</label>
                                         <input class="input-large" type="text" style="width:35%" placeholder="网格责任人" name="gridcharger" id="gridcharger">
                                         <%--<label class="col-xs-4 no-padding-right">走访时间：</label>--%>
                                        <%--<input class="date-picker col-xs-5" id="visittime" name="visittime" type="text" data-date-format="yyyy-mm-dd"--%>
                                               <%--placeholder="走访时间">--%>
                                    <%--</div>
                                </td>
                            </tr>--%>
                            <tr>
                                <td class="center col-xs-2" >用户基本信息</td>
                                <td class="center col-xs-1">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
                                <td class="center col-xs-2 ">
                                    <input class="form-control input-mask-date" type="text" name="userid" id="userid"
                                           value="${user.username}">
                                </td>
                                <td class="center col-xs-1">用户姓名</td>
                                <td class="center col-xs-2">
                                    <input class="form-control input-mask-date" type="text" name="username"
                                           id="username" value="${user.teacherName}">
                                </td>
                                <td class="center col-xs-4" >
                                <%--representation: 任职情况--%>
                                </td>
                            </tr>
                            <tr >
                                <td class="center" colspan="6"><h3 style="margin-top:10px;">所带学生信息卡</h3></td>
                            </tr>
                            <tr>
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
                            </tr>
                            <%--<tr>
                                <td class="center" rowspan="8">设备基本信息</td>
                                <td class="center">设备名字</td>
                                <td class="center"><input class="input-large" type="text" name="devicename" id="devicename"></td>
                                <td class="center">设备品牌</td>
                                <td class="center"><input class="input-large" type="text"  name="brand" id="brand"/></td>
                                <td class="center">我的设备二维码</td>
                            </tr>
                            <tr>
                                <td class="center">设备状态</td>
                                <td class="center"><input class="input-large" type="text" name="devicestatus" id="devicestatus"></td>
                                <td class="center">设备类型</td>
                                <td class="center"><input class="input-large" type="text" name="devicetype" id="devicetype"></td>
                                <td rowspan="7" class="center"><textarea rows="7">二维码</textarea></td>
                            </tr>
                            <tr>
                                <td class="center">购买价格</td>
                                <td class="center"><input class="input-large" type="text" name="price" id="price"></td>
                                <td class="center">购买时间</td>
                                <td class="center"><input class="input-large" type="text" name="buytime" id="buytime"></td>
                            </tr>
                            <tr>
                                <td class="center">存放地点</td>
                                <td class="center" colspan="3"><input class="form-control input-mask-date" type="text" name="position" id="position"></td>
                            </tr>
                            <tr>
                                <td class="center" rowspan="4">配置信息</td>
                                <td class="center">CPU</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name="cpu" id="cpu"></td>
                            </tr>
                            <tr>
                                <td class="center">显卡</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name="gpu" id="gpu"></td>
                            </tr>
                            <tr>
                                <td class="center">内存</td>
                                <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name=" memory" id=" memory"></td>
                            </tr>
                            <tr>
                                <td class="center">其他</td>
                                < <td class="center" colspan="2"><input class="form-control input-mask-date" type="text" name=" other" id=" other"></td>
                            </tr>
                            <tr>
                                <td colspan="6" class="center">
                                    <a href="javascript:history.back(-1)" class="btn btn-grey"> <i class="icon-arrow-left"> </i> 返回 </a>
                                    <button class="btn btn-info" id="addFarmerBtn">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                </td>
                            </tr><--%>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>

            <!-- PAGE CONTENT ENDS -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</div>
<!-- /.page-content -->
</div>
<!-- /.main-content -->
<%--<%@include file="/WEB-INF/jsp/common/setting-btn.jsp" %>--%>
<!-- /#ace-settings-container -->
</div>
<!-- /.main-container-inner -->

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div>

<!-- /.main-container -->
<%--导入尾部js--%>
<%@include file="/WEB-INF/jsp/common/import-js.jsp" %>
<script src="${ctx}/assets/js/bootbox.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/js/mqCardAdd.js" type="text/javascript"></script>
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
    $(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";

        $(grid_selector).jqGrid({
            url: "${ctx}/user/liststudent",
            datatype: "json",
            mtype: "GET",
            height: 'auto',
            colNames: ['学号', '学生姓名', '年级'],
            colModel: [
                {name: 'username', index: 'username', width: 50, sorttype: "int", editable: false},
                {name: 'studentName', index: 'studentName', width: 54, editable: false},
                {name: 'grade', index: 'grade', width: 40, editable: false}
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
        }).navSeparatorAdd(pager_selector, {
            sepclass: "ui-separator",
            sepcontent: ''
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
</body>
</html>
