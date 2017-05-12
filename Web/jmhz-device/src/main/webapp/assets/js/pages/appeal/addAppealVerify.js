/**
 * Created by 锋情 on 2014/4/23.
 */
$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#verifyForm").validate({

        rules: {
            name: {
                required: true
            }
        },

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function(error, element) {                             //错误信息位置设置方法
            //error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {

          form.submit(); // form validation success, call ajax form submit
        }
    });
});