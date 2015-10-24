/**
 * Application通用函数集合
 */
App=function(){

	return{

		//初始化
		init:function(){
			$TC = $('#tabContainer');
			$TC.tabs({
				border:false,
				fit:true,
				tabWidth:150
			})
		},

		/**
		 * 弹出窗口.
		 * @param title
		 * @param width
		 * @param height
		 * @param href
		 * @param loadFn
		 */
		openWin:function(title,width,height,href,loadFn){
			$('#default_win').window({
				title: title,
				width: width,
				shadow: false,
				closed: false,
				cache:  false,
				height: height,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				resizable:false,
				href:href,
				modal:true,
				onOpen:function(){
					$(this).window('center');
				},
				onLoad: loadFn||function(){;}
			});
		},

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
		},
		
		/** 凭证增加新的tab */
		addVoucherTab:function(title,href,closable){
			$TC = $('#tabContainer');
			if($TC.tabs('exists',title)){
				$TC.tabs('select',title);
				$TC.tabs('getSelected').panel('refresh', href);
			}else{
				App.addTab(title,href,closable);
			}
		},

		/**
		 * 选择tab
		 * @param title
		 */
		selectTab:function(title){
			$TC = $('#tabContainer');
			$TC.tabs('select',title);
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
	App.init();

	/** 所有的ajax请求都自动提交_csrf头信息，否则POST方式调用REST会报403. */
	//var token = $("meta[name='_csrf']").attr("content");
	//var header = $("meta[name='_csrf_header']").attr("content");
	//$(document).ajaxSend(function(e, xhr, options) {
	//	xhr.setRequestHeader(header, token);
	//});


	/**
	 * 重写jquery ajax
	 */
	(function($){
		//备份jquery的ajax方法
		var _ajax=$.ajax;

		//重写jquery的ajax方法
		$.ajax=function(opt){
			//备份opt中error和success方法
			var fn = {
				error:function(XMLHttpRequest, textStatus, errorThrown){},
				success:function(data, textStatus){}
			};
			if(opt.error){
				fn.error=opt.error;
			}
			if(opt.success){
				fn.success=opt.success;
			}

			//扩展增强处理
			var _opt = $.extend(opt,{
				//覆盖状态码处理函数，当session过期的时候，能够让ajax请求处理
				statusCode: {
					401: function() {
						//如果是顶层窗口
						if (window.top != window.self) {
							window.parent.location.href = "/";
						} else {
							window.location.href = "/";
						}
					},
					403: function(){
						$.messager.alert('警告消息','您的权限不足，请联系管理申请权限！','warning');
						return;
					},
					500: function(){
						$.messager.alert('错误消息','很抱歉，系统好像正在开小差，请稍候再试！','error');
						return;
					}
				},

				error:function(XMLHttpRequest, textStatus, errorThrown){
					//错误方法增强处理
					fn.error(XMLHttpRequest, textStatus, errorThrown);
				},
				success:function(data, textStatus){
					//成功回调方法增强处理
					fn.success(data, textStatus);
				}
			});
			_ajax(_opt);
		};
	})(jQuery);
});