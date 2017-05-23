/**
 * Created by QiKai on 2014/4/27.
 */
$(function () {
    var path = window.location.pathname.split("/")[0];
    $("#createRoleForm").validate({
        rules: {
            addRoleName: {
                required: true
            },
            addResourceName: {
                required: true
            }
        },
        messages: {
            addRoleName: {
                required: "&nbsp;* 角色名称不能为空！"
            },
            addResourceName: {
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
            $.ajax({
                type: "POST",
                url: path+"/role/create",
                data: {
                    role: $("#addRoleName").val(),
                    description: $("#addRoleDesc").val(),
                    resourceIds: $("#addRoleIds").val(),
                    available: $("input[name='addRoleAvailable']").prop("checked")
                },
                dataType: 'json',
                success: function (data) {//名称修改成功
                    $("#createRoleDiv").toggleClass("display-hide");
                    $("#roleListDiv").toggleClass("display-hide");
                    window.location.href = path+'/role?' + new Date().getTime();
                },
                error: function (XMLHttpRequest, errorThrown) {
                    alert(XMLHttpRequest.status);
                }
            });
        }
    });

});