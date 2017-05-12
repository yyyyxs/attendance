$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#appealRateForm").validate({

        rules: {
            householdername: {
                required: true
            }

        },

        highlight: function (element) { // hightlight error inputs
            $(element).css({"border":"1px solid #b94a48"});  // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {                             //错误信息位置设置方法
           // error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: path+"/appeal/update",
                data: {
                    id: $('#id').val(),
                    uuid: $('#uuid').val(),
                    householdername: $('#householdername').val(),
                    householdernamehidden: $("#householdernamehidden").val(),
                    householderidhidden: $("#householderidhidden").val(),
                    appealtype: $("#appealtype").val(),
                    affairtype: $("#affairtype").val(),
                    hardshipappeal: $("#hardshipappeal").val(),
                    dutyleader: $("#dutyleader").val(),
                    dutydept: $("#dutydept").val(),
                    dutymenber: $("#dutymenber").val(),
                    solution: $("#solution").val(),
                    timelimit: $("#timelimit").val(),
                    status: $("#status").val(),
                    processupdatetime: $("#processupdatetime").val(),
                    processupdateremark: $("#processupdateremark").val()
                },
                dataType: 'json',
                success: function (data) {

                    if(data == 0){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>该诉求农户不存在！</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        //Example.show("great success");
                                    }
                                }
                            }
                        });
                    }else if(data == 1){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>更新成功！</span>",
                            buttons:
                            {
                                "success" :
                                {
                                    "label" : "<i class='icon-ok'></i> 确定",
                                    "className" : "btn-sm btn-success",
                                    "callback": function() {
                                        window.location.href=path+"/appeal/show";
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