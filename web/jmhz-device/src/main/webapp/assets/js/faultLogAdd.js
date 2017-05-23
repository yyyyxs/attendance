/**
 * Created by lw on 2014/4/23.
 */
$(function () {
    //var path = window.location.pathname.split("/")[0];
    $("#faultDeviceLog").validate({

        rules: {
            //deviceId: {
            //    required: true
            //},
            //deviceName: {
            //    required: true
            //}
        },

        highlight: function (element) { // hightlight error inputs
            $(element).css({"border": "1px solid #b94a48"});  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            // error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            //var faultlog={};
            //faultlog.applyId= $('#applyId').val().trim();
            //faultlog.deviceName= $('#deviceName').val().trim();
            //faultlog.deviceUser= $('#deviceUser').val().trim();
            //faultlog.applyId= $('#applyId').val().trim();
            //faultlog.deviceType= $('#deviceType').val().trim();
            //faultlog.dealStatus= $('#dealStatus').val().trim();
            //faultlog.cost= $('#cost').val().trim();
            //faultlog.repairpart= $('#repairpart').val().trim();
            //faultlog.repairResult= $('#repairResult').val().trim();
            $.ajax({
                type: "POST",
                url: path + "/fault/addFaultLog",
                data: {
                    //faultlog:faultlog
                    applyId: $('#applyId').val(),
                    deviceName: $('#deviceName').val(),
                    deviceUser: $('#deviceUser').val(),
                    deviceType: $('#deviceType').val(),
                    dealStatus: $("#dealStatus").val(),
                    cost: $("#cost").val(),
                    repairpart: $("#repairpart").val(),
                    repairResult: $("#repairResult").val()

                },
                dataType: 'json',
                success: function (date) {
                    if (date == 0) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>报告添加失败，请重试！</span>",
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
                    } else if (date == 1) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>报告添加成功！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        //window.location.href = path + "/faulet/showapply";
                                    }
                                }
                            }
                        });

                    }

                },
                error: function (XMLHttpRequest, errorThrown) {
                    alert(XMLHttpRequest.status);
                }
            });
            // form.submit(); // form validation success, call ajax form submit
        }
    });
});