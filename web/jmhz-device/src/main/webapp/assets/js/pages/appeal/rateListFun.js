$(function(){
    var path = window.location.pathname.split("/")[0];

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    $(grid_selector).jqGrid({
        //因为js中不能解析el表达式${appealId}，因此在页面设置一个隐藏域存放appealid
        url: path + "/appealRate/getAllRatedList/" + $("#appealid").val(),
        datatype: "json",
        mtype: "GET",
        height: 334,
        colNames: [ 'ID', '诉求编号', '诉求方', '所属村', '责任领导', '责任部门', '责任人', '诉求状态', '评价等级', '评价内容', '评价日期', '编辑'],
        colModel: [
            {name: 'id', index: 'id', hidden: true},//ID
            {name: 'uuid', index: 'uuid', width: 60, editable: false},//uuid
            {name: 'appealname', index: 'appealname', width: 90, editable: false},
            {name: 'appealvillage', index: 'appealvillage', width: 90},
            {name: 'dutyleader', index: 'dutyleader', width: 90},
            {name: 'dutydept', index: 'dutydept', width: 90},
            {name: 'dutymenber', index: 'dutymenber', width: 90},
            {name: 'status', index: 'status', width: 90, formatter: styleStatus},
            {name: 'ratelevel', index: 'ratelevel', width: 90, formatter: styleRatelevel},
            {name: 'ratecontent', index: 'ratecontent', width: 90},
            {name: 'ratedate', index: 'ratedate', width: 90},
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
        editurl: path + "/appeal/modify",//nothing is saved
        caption: "诉求评价",
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
            if (rowNum == 0) {
                if ($("#norecords").html() == null) {
                    $(grid_selector).parent().append("<div id=\"norecords\" style='text-align: center;vertical-align: middle;'>无数据显示！</div>");
                }
            } else {
                $("#norecords").hide();
            }
        }
    });


    function styleRatelevel(cellvalue, options, rowObject) {
        if (cellvalue == '4') {
            return "满意";
        } else if (cellvalue == '3') {
            return "基本满意";
        } else if (cellvalue == '2') {
            return "一般";
        } else if (cellvalue == '1') {
            return "不满意";
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

    function makeURLForOperation(cellvalue, options, rowObject) {
        var urlstring =
            "<div class=\"btn-group\" id='operationBtns'>" +
            "<button title=\"编辑诉求评价\" class=\"btn btn-xs btn-success\" style=\"margin: 0;margin-left:10px;\" id=\"changePwd\" onclick=showUpdateRateDialog(" + rowObject["id"] + "," + rowObject["ratelevel"] + ",'" + rowObject["ratecontent"] + "','" + rowObject["ratedate"] + "')><i class=\"icon-edit bigger-120\" style=\"margin: 0;\"></i></button>" +
            "<button title=\"删除诉求评价\" class=\"btn btn-xs btn-danger\" style=\"margin: 0;margin-left:10px;\" id=\"delRoleBtn\" onclick=showDeleteRateDialog(" + rowObject["id"] + ")><i class=\"icon-trash bigger-120\" style=\"margin: 0;\"></i></button>" +
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
    );

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

    $('#ratedate').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('textarea[class*=autosize]').autosize({append: "\n"});

});

function checkEmptyRate() {
    if ($("#rateContent").val() == '') {
        $("#rateContent").css({"border": "1px solid #d16e6c"});
        $("#errorNotRate").removeClass('hide');
    } else {
        $("#rateContent").css({"border": "1px solid #d5d5d5"});
        $("#errorNotRate").addClass('hide');
    }
}

function showUpdateRateDialog(id, ratelevel, ratecontent, ratedate) {
    var path = window.location.pathname.split("/")[0];
    $("#rateLevel" + ratelevel).attr("checked", "checked");
    $("#rateContent").val(ratecontent);
    $("#ratedate").val(ratedate);
    $("#appealRateDialog").removeClass('hide').dialog({
        height: 400,
        width: 600,
        resizable: false,
        modal: true,
        title: "诉求评价修改",
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
                            url: path + "/appealRate/updateRate",
                            data: {
                                id: id,
                                rateLevel: $("input[name='rateLevel']:checked").val(),
                                rateContent: $("#rateContent").val(),
                                rateDate: $("#ratedate").val()
                            },
                            dataType: 'json',
                            success: function (data) {
                                if (data.success) {
                                    $("#grid-table").jqGrid("setGridParam", {
                                        url: path + "/appealRate/getAllRatedList/" + $("#appealid").val(),
                                        datatype: "json" //设置数据类型
                                    }).trigger("reloadGrid");
                                    bootbox.dialog({
                                        message: "<span class='bigger-110'>诉求评价修改成功！</span>",
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
                                        message: "<span class='bigger-110'>诉求评价修改失败！请联系系统管理员解决问题.</span>",
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
                                    message: "<span class='bigger-110'>诉求评价修改失败！请联系系统管理员解决问题.</span>",
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

function showDeleteRateDialog(id) {
    var path = window.location.pathname.split("/")[0];
    $("#delRateDialog").removeClass('hide').dialog({
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
                        url: path + "/appealRate/deleteRate",
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