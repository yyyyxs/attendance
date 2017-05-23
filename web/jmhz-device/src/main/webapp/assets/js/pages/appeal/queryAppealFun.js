$(function(){
    var path = window.location.pathname.split("/")[0];

    var specialfamilyValue = '';
    $('input[name="specialfamily"]:checked').each(function () {
        specialfamilyValue = specialfamilyValue + $(this).val() + ',';
    });
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    $(grid_selector).jqGrid({
        url: "",
        datatype: "json",
        mtype: "GET",
        height: 'auto',
        colNames: ['诉求编号', '诉求编号', '诉求来源', '诉求方', '所属镇','所属村', '诉求类别', '事务类别', '状态', '整改类型', '责任领导', '责任部门', '责任人', '创建日期', '完成时限', '诉求操作'],
        colModel: [
            {name: 'id', index: 'id', width: 50, sorttype: "int", editable: false, hidden: true},
            {name: 'uuid', index: 'uuid', width: 50, sorttype: "int", editable: false},
            {name: 'proposer', index: 'proposer', width: 54, editable: false, formatter: styleProposer},
            {name: 'appealname', index: 'appealname', width: 50, editable: false, sorttype: "textarea"},
            {name: 'town', index: 'town', width: 50, editable: false, sorttype: "textarea"},
            {name: 'village', index: 'village', width: 50, editable: false, sorttype: "textarea"},
            {name: 'appealtype', index: 'appealtype', width: 60, editable: false, formatter: styleAppealType},
            {name: 'affairtype', index: 'affairtype', width: 56, editable: false, formatter: styleAffairType},
            {name: 'status', index: 'status', width: 50, sorttype: "int", editable: false, formatter: styleStatus},
            {name: 'doingstatus', index: 'doingstatus', width: 90, sorttype: "int", editable: false, formatter: styleDoingStatus},
            {name: 'dutyleader', index: 'dutyleader', width: 60, sorttype: "int", editable: false},
            {name: 'dutydept', index: 'dutydept', width: 80, sorttype: "int", editable: false},
            {name: 'dutymenber', index: 'dutymenber', width: 60, sorttype: "int", editable: false},
            {name: 'createdate', index: 'createdate', width: 60, sorttype: "int", editable: false, hidden: true},
            {name: 'timelimit', index: 'timelimit', width: 60, sorttype: "int", editable: false},
            {name: 'operation', index: '', width: 110, formatter: makeURLForOperation}
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
        editurl: path + "/appeal/modify",//nothing is saved
        caption: "诉求信息",
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

    function styleProposer(cellvalue, options, rowObject) {
        if (cellvalue == '0') {
            return "个人提出";
        } else {
            return "集体提出";
        }
    }

    function styleAppealType(cellvalue, options, rowObject) {
        if (cellvalue == '0') {
            return "发展生产";
        } else if (cellvalue == '1') {
            return "基础设施";
        } else if (cellvalue == '2') {
            return "矛盾纠纷";
        } else if (cellvalue == '3') {
            return "社会治安";
        } else if (cellvalue == '4') {
            return "生活救助";
        } else if (cellvalue == '5') {
            return "征地拆迁";
        } else if (cellvalue == '6') {
            return "政策咨询";
        } else if (cellvalue == '7') {
            return "证照办理";
        } else if (cellvalue == '8') {
            return "群众投诉";
        } else if (cellvalue == '9') {
            return "其他";
        }
    }

    function styleAffairType(cellvalue, options, rowObject) {
        if (cellvalue == '0') {
            return "个人事务";
        } else if (cellvalue == '1') {
            return "公共事务";
        }
    }

    function styleStatus(cellvalue, options, rowObject) {
        if (cellvalue == '0') {
            return "未解决";
        } else if (cellvalue == '1') {
            return "已上报上级协调解决";
        } else if (cellvalue == '2') {
            return "延时解决";
        } else if (cellvalue == '3') {
            return "正在解决";
        } else if (cellvalue == '4') {
            return "已做好解释说明工作";
        } else if (cellvalue == '5') {
            return "已解决";
        }
    }

    function styleDoingStatus(cellvalue, options, rowObject) {
        if (cellvalue == '0') {
            return "立学立改项目";
        } else if (cellvalue == '1') {
            return "短期整改项目";
        } else if (cellvalue == '2') {
            return "中长期整改项目";
        } else {
            return "";
        }
    }

    function makeURLForOperation(cellvalue, options, rowObject) {
        var urlstring =
            "<div class=\"btn-group\" id='operationBtns'>" +
            "<button title=\"诉求查看/跟踪\" class=\"btn btn-xs btn-info\" style=\"margin: 0;\" id=\"modRoleBtn\" " +
            "onclick=\"window.open('" + path + "/appeal/toviewpage/" + rowObject["id"] + "','_blank')\">" +
            "<i class=\"icon-search bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button title=\"诉求备注管理\" class=\"btn btn-xs btn-grey\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" " +
            "onclick=\"window.open('" + path + "/appealmark/getmarklist/" + rowObject["id"] + "','_blank')\">" +
            "<i class=\"icon-bookmark-empty bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button title=\"诉求评价管理\" class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" " +
            "onclick=\"window.open('" + path + "/appealRate/getRateList/" + rowObject["id"] + "','_blank')\">" +
            "<i class=\"icon-thumbs-up-alt bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button title=\"删除诉求\" class=\"btn btn-xs btn-danger\" onclick='delAppeal(" + rowObject['id'] + ",\"" + rowObject['uuid'] + "\")' style=\"margin: 0;margin-left:10px;\" id=\"delAppealBtn\">" +
            "<i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
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
            edit: true,
            editicon: 'icon-pencil blue',
            add: true,
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
            },
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
        // var replacement =
        // {
        //     'ui-icon-pencil' : 'icon-pencil blue',
        //     'ui-icon-trash' : 'icon-trash red',
        //     'ui-icon-disk' : 'icon-ok green',
        //     'ui-icon-cancel' : 'icon-remove red'
        // };
        // $(table).find('.ui-pg-div span.ui-icon').each(function(){
        //    var icon = $(this);
        //    var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        //    if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        //})
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
    $("#queryAppealBtn").click(function () {
        reloadList(grid_selector);
    });
    function reloadList(grid_selector) {
        var doingstatus = $("input[name='doingstatus']:checked").val();
        $(grid_selector).jqGrid("setGridParam", {
            url: path + "/appeal/searchAppealDoingstatus/" + doingstatus, //设置表格的url
            datatype: "json" //设置数据类型
            //postData:{'orderId':1} //发送数据
        }).trigger("reloadGrid");
    }

    $(window).resize(function () {
        $(grid_selector).setGridWidth($("#tableDiv").width());
    });

});