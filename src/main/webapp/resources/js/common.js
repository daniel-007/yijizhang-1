/**
 * Application通用函数集合
 */
App=function(){

	return{

		/** 增加新的tab */
		addTab:function(title,href,closable){
			$TC = $('#tabContainer');
			if($TC.tabs('exists',title)){
				$TC.tabs('select',title);
			}else{
				$TC.tabs('add',{
					id: title,
					title: title,
					href: href,
					selected: true,
					closable: closable|true,
					tools:[{
				        iconCls:'icon-mini-refresh',
				        handler:function(){
				        	var tab = $TC.tabs('getSelected');  // get selected panel
				        	tab.panel('refresh', href);
				        }
				    }]
				});
			}
		}

	};

}();


//////扩展easyui的功能
$.extend($.fn.validatebox.defaults.rules, {
	minLength: {
		validator: function(value, param){
			return value.length >= param[0];
		},
		message: '请输入至少{0}个字符'
	}
});

$(function(){
	/** 所有的ajax请求都自动提交_csrf头信息，否则POST方式调用REST会报403. */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
