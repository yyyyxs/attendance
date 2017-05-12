jQuery(function($){
	// 日期插件
	$('#visittime').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$('#birthday').mask('9999年99月');
});