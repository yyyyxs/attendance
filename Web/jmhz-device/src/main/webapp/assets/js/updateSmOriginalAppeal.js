/**
 * Created by 锋情 on 2014/4/23.
 */
$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#smOriginalAppealForm").validate({

        rules: {
            content: {
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
                url: path+"/smhlappeal/updateOriginal",
                data: {
                    id: $('#id').val(),
                    tel: $("#tel").val(),
                    status: $("#status").val(),
                    content: $("#content").val(),
                    createtime: $("#createtime").val()
                },
                dataType: 'json',
                success: function (data) {

                    if(data == 0){
                        bootbox.dialog({
                            message: "<span class='bigger-110'>更新失败！</span>",
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
                                        //window.location.href=path+"/appeal/show";
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