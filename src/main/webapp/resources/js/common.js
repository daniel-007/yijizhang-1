/**
 * Application通用函数集合
 */
$(document).ready(function(){
	$TC = $('#tabContainer');


	/** 所有的ajax请求都自动提交_csrf头信息，否则POST方式调用REST会报403. */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

App=function(){
	return{
		/** 增加新的tab */
		addTab:function(title,href,closable){
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