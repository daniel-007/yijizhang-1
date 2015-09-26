/**
 * Application通用函数集合
 */
$(document).ready(function(){
	$TC = $('#tabContainer');
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