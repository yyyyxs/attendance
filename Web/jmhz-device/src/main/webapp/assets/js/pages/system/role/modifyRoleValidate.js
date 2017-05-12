$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#modifyRoleForm").validate({
        rules: {
            role: {
                required: true
            },
            modResourceName: {
                required: true
            }
        },
        messages: {
            role: {
                required: "&nbsp;* 角色名称不能为空！"
            },
            modResourceName: {
                required: "&nbsp;* 角色权限必须选择！"
            }
        },
        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).closest('.col-sm-1').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {                             //错误信息位置设置方法
            error.insertAfter(element);                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});