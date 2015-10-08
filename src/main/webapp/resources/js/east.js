/**
 * 首页新建账套的JS脚本文件。
 */
East=function(){
	return {
		createInit:function(){
			var $btn_xjzt = $("#btn_xjzt");
		    $btn_xjzt.click(function () {
		        $("#default_win").window({
		            title: '<i class="fa fa-list-alt"></i>新建账套',
		            width: 550,
		            height: 400,
		            modal: true,
		            collapsible: false,
		            shadow: true,
		            href: 'account/book/main',
		            onLoad: function () {
		            }
		        });
		    });
		}
	};
	
}();
