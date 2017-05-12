/**
 * 
 */
		var app = angular.module('loginApp', []);
		app.controller('validateCtrl', function($scope) {
		
		});

		function firm() {
			//利用对话框返回的值 （true 或者 false）  
			if (confirm("用户名不存在，请注册！")) {
				window.location.href = "register.html";
			} else {

			}
		};
		$(function() {
			$("#login_btn").click(function() {
				var usernum = document.getElementById("usernum").value;
				var passwd = document.getElementById("passwd").value;
				var user_json = {
					"userNum" : usernum,
					"userPasswd" : passwd
				}
				var json_str = JSON.stringify(user_json);
				//js对象转换成JSON字符串  
				$.ajax({
					url : "/AttendanceWeb/UserController/checkUserLogin.do",
					cache : true,
					type : "POST",
					datatype : "json",
					contentType : "application/json; charset=utf-8",
					data : json_str,
					success : function(data) {
						switch (data) {
						case 1:
							window.location.href = "home.html";
					
							break;
						case 11:
						
							window.location.href = "";
							break;
						case 111:
						
							window.location.href = "";
							break;
						case 2:
							alert("用户名或密码错误");
							break;
						case 0:
							firm();
							break;
						}
					}
				});
			});
		});
