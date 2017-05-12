/**
 * 
 */
	var app = angular.module('registerApp', []);
	app.controller('validateCtrl', function($scope) {  
	});
	/*指令创建*/
	app.directive('equals',function(){
	return{
	require:'ngModel',
	link:function(scope,elm,attrs,ngModelCtrl){
	function validateEqual(myValue){
	var valid = (myValue === scope.$eval(attrs.equals));
	ngModelCtrl.$setValidity('equal',valid);
	return valid ? myValue : undefined;
	}
	ngModelCtrl.$parsers.push(validateEqual);
	ngModelCtrl.$formatters.push(validateEqual);
	scope.$watch(attrs.equals,function(){
	ngModelCtrl.$setViewValue(ngModelCtrl.$viewValue);
	})
	}
	}
	});
	
	
$(function() {
	$("#register_btn").click(function() {
		var usernum = document.getElementById("usernum").value;
		var username = document.getElementById("username").value;
		var tel = document.getElementById("tel").value;
		var passwd = document.getElementById("passwd").value;
		var user_json = {
			"userNum" : usernum,
			"userName" : username,
			"userTel" : tel,
			"userPasswd" : passwd
		}
		var json_str = JSON.stringify(user_json);

		// js对象转换成JSON字符串
		$.ajax({
			url : "/AttendanceWeb/UserController/registerStu.do",
			cache : true,
			type : "POST",
			datatype : "json",
			contentType : "application/json; charset=utf-8",
			data : json_str,
			success : function(data) {
				switch (data) {
				case 0:
					window.location.href = "home.html";
					break;
				}
			}
		});
	});
	 
});

