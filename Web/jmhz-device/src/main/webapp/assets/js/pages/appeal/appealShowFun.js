$(function(){
    var path = window.location.pathname.split("/")[0];
    // 日期插件
    $('#timelimit').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('#createdate').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('#processupdatetime').datepicker({autoclose: true, language: 'zh-CN'}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $("<div id='sendSMSMaskDIV'><i class='icon-spinner icon-spin white' style='font-size:550%;position: relative;top: 48%;left: 48%;'></i>" +
        "<span style='position: relative;color: white;top: 52%;left:42%;font-size:20px;'>短信发送中...</span></div>").css({
        position: 'absolute',
        top: 0,
        left: 0,
        backgroundColor: "#393939",
        opacity: 0.5,
        zIndex: 1040
    }).height($(document).height()).width($(document).width()).hide().appendTo("body");
});
function onStatusChange() {
    if ($("#status").val() == '3') {
        $("#doingstatus").removeClass("display-hide");
    } else {
        $("#doingstatus").addClass("display-hide");
    }
}
function sendSMS(id, uuid) {
    var path = window.location.pathname.split("/")[0];
    $.ajax({
        type: "POST",
        url: path + "/appeal/getSMSForAppeal/" + id,//获得短信详情
        dataType: 'json',
        success: function (data) {
            $("#appealuuid").attr("value", data.uuid);
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
                        url: path + "/appeal/sendSMS",//发送短信
                        data:{
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
                                    message: "<span class='bigger-110'>短信发送结果："+data.msg+"</span>",
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
            },
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