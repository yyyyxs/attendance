$(function () {
    $('a[href*=#]:not([href=#])').click(function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top - 50
                }, 1000);
                return false;
            }
        }
    });
    //var path = window.location.pathname.split("/")[0];

    // Handle login
    $('#login-form').validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "用户名不能为空."
            },
            password: {
                required: "密码不能为空."
            }
        },
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('#login-form')).show();
            $("#usernamePwdNull").removeClass('display-hide');
        },
        highlight: function (element) { // hightlight error inputs
            console.log("aa");
            $(element).closest(".form-group").addClass('has-error'); // set error class to the control group
        },
        success: function (label) {
            label.closest(".form-group").removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: path + "/login",
                data: {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                dataType: 'json',
                success: function (data) {
                    //data = $.parseJSON(data);
                    console.log("data toString= " + data);
                    console.log("data success=" + " " + data.success + " " + data.msg);
                    if (data.success == true) {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>" + data.msg + "</span>",
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
                        location.href = path + "/";
                    } else {
                        bootbox.dialog({
                            message: "<span class='bigger-110'>" + data.msg + "</span>",
                            buttons: {
                                "success": {
                                    "label": "<i class='icon-ok'></i> 确定",
                                    "className": "btn-sm btn-success",
                                    "callback": function () {
                                        location.href = path + "/";
                                        //window.location.href=path+"/appeal/show";
                                    }
                                }
                            }
                        });
                    }
                },
                error: function (XMLHttpRequest, errorThrown) {
                    messageBox("联系管理员，错误代码：" + XMLHttpRequest.status);
                    location.href = path + "/";
                }
            });
            //form.submit(); // form validation success, call ajax form submit
        }
    });
    $('#login-form').keypress(function (e) {
        if (e.which == 13) {
            if ($('#login-form').validate().form()) {
                $('#login-form').submit(); //form validation success, call ajax form submit
            }
            return false;
        }
    });
});

/*jQuery(document).ready(function($){
 var $timeline_block = $('.cd-timeline-block');

 //hide timeline blocks which are outside the viewport
 $timeline_block.each(function(){
 if($(this).offset().top > $(window).scrollTop()+$(window).height()*0.75) {
 $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
 }
 });

 //on scolling, show/animate timeline blocks when enter the viewport
 $(window).on('scroll', function(){
 $timeline_block.each(function(){
 if( $(this).offset().top <= $(window).scrollTop()+$(window).height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
 $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
 }
 });
 });
 });*/

$(".navbar-collapse").css({ maxHeight: $(window).height() - $(".navbar-header").height() + "px" });
