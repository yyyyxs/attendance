/**
 * Created by 锋情 on 2014/4/23.
 */
$(function () {
    //var path = window.location.pathname.split("/")[0];
    $("#deviceAdd").validate({
        errorClass: 'help-block',
        focusInvalid: false,
        ignore: "",
        rules: {
            price: {
                number: true
            }
        },
        messages: {
            price: {
                number: "只能输入数字和点"
            }
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
            $.ajax({
                type: "POST",
                url: path + "/devicemg/adddevice",
                data: {
                    id: $('#id').val(),
                    deviceName: $('#deviceName').val(),
                    price: $('#price').val(),
                    buyTime: $('#buyTime').val(),
                    serialNumber: $('#serialNumber').val(),
                    position: $("#position").val(),
                    deviceUser: $("#deviceUser").val(),
                    status: $("#status").val(),
                    brand: $("#brand").val(),
                    CPU: $("#CPU").val(),
                    internalMemory: $("#internalMemory").val(),
                    graphicsCard: $("#graphicsCard").val(),
                    otherInfo: $("#otherInfo").val(),
                    buyYear: $("#buyYear").val()


                },
                dataType: 'json',

                success: function (date) {
                    if (date == 0) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>设备添加失败，请重试！</span>",
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
                            message: "<span class='bigger-110'>设备添加成功！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        window.location.href = path + "/devicemg/show";
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