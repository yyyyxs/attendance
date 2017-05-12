/**
 * Created by 锋情 on 2014/4/23.
 */
$(function(){
    var path = window.location.pathname.split("/")[0];
    $("#farmerform").validate({

        rules: {

            householdername: {
                required: true
            },

            contactnumber: {
                required: true
            }

        },
        success: function (element) {

            $(element).removeAttr("style");
            //$(element).atrr("style","");
            $(element).css({"border":"2px solid green"});
        },
        highlight: function (element) { // hightlight error inputs
            //$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            $(element).css({"border":"1px solid #b94a48"}); // set error class to the control group
        },

        errorPlacement: function(error, element) {                             //错误信息位置设置方法
            //error.insertAfter( element.parent() );                            //这里的element是录入数据的对象
        },
        submitHandler: function (form) {
           form.submit(); // form validation success, call ajax form submit
        }
    });
});