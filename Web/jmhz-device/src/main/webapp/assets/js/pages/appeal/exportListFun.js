function deleteExportDataFun(id) {
    var path = window.location.pathname.split("/")[0];
    $("#dialog-confirm").removeClass('hide').dialog({
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
                        url: path + "/exportData/delete",
                        data: {
                            id: id
                        },
                        dataType: 'json',
                        success: function (data) {
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
                            $("#tr" + id).remove();
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