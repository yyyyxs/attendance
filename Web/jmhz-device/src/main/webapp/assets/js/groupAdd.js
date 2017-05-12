/**
 * Created by 锋情 on 2014/4/23.
 */
$(function() {
    var path = window.location.pathname.split("/")[0];
    $("#groupAddForm").validate({
        rules: {
            
            groupname: {
                required: true
            },
            groupchargername: {
                required: true
            },
            groupchargertel: {
                required: true,
                number:true

            }

        },
        messages: {
            groupname: {
                required: "请输入集体名"
            },
            groupchargername: {
                required: "请输入负责人"

            },
            groupchargertel: {
                required: "请输入电话",
                number:"请输入数字"
            }

        },
        highlight: function (element) { // hightlight error inputs
           // alert("hjghj")
//            label.closest('.form-group').removeClass('has-error');
            $(element).css({"border":"1px solid #b94a48"});
//            $(element).closest('.form-group').addClass('has-error');
//            $(element).closest('.col-sm-1').addClass('has-error');
        },
        success: function (label) {
            //alert("123");
//            label.closest('.form-group').removeClass('has-error');
//            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            //error.insertAfter(element.parent());                            //这里的element是录入数据的对象
        },
        error: function (XMLHttpRequest, errorThrown) {
            alert(XMLHttpRequest.status);
            // form.submit(); // form validation success, call ajax form submit
        },
        submitHandler: function (form) {
            //alert("122342423");
            $.ajax({
                type: "POST",
                url: path + "/group/addgroupcard",
                data: {
                    uuid: $('#uuid').val(),
                    groupname: $('#groupname').val(),
                    groupchargername: $('#groupchargername').val(),
                    groupchargertel: $('#groupchargertel').val(),
                    hardshipappeal:$("#hardshipappeal").val(),
                    appealtype: $("#appealtype").val(),
                    affairtype: $("#affairtype").val()
                },
                dataType: 'json',
                success: function (data) {
                    if (data == 0) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>该用户已经存在！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        //Example.show("great success");
                                    }
                                }
                            }
                        })
                    } else if (data == 1) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>添加成功！</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        window.location.href = path + "/group/show";
                                    }
                                }
                            }
                        });
                    } else {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>添加失败，请重试！</span>",
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
                }

            });

        }
    })
 });
