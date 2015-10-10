/**
 * 首页新建账套的JS脚本文件。
 */
East=function(){
	return {
		createInit:function(){
			var $btn_xjzt = $("#btn_xjzt");
		    $btn_xjzt.click(function () {
				App.openWin('<i class="fa fa-list-alt"></i>新建账套', 550,400,'account/book/main',function(){
					$('#book_name').next('span').find('input').focus();
				});
		    });
		}
	};
	
}();
