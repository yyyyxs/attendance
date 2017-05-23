$(function(){
    $("#signupForm").validate({
        rules: {
            name: "required",
            tel: "required"

        },
        messages: {
            name: "请输入姓名",
            tel: "请输入电话"

        }
    });
});
