/**
 * 结转损益的JS脚本文件。
 */
$(function () {
    var $btn_jzsy = $("#btn_jzsy");
    $btn_jzsy.click(function () {
        $("#default_win").window({
            title: '<i class="fa fa-list-alt"></i>结转本期损益',
            width: 600,
            height: 400,
            modal: true,
            collapsible: false,
            shadow: true,
            href: 'account/carryOver/main',
            onLoad: function () {
            }
        });
    });
});


CarryOver=function(){
	return {
		cancel:function(){
			$("#default_win").window("close");
		},
		complete:function(){
			$('#carry_over').form('submit', {
				url : 'account/carryOver/complete',
				success : function(data) {
                    data = $.parseJSON(data);
                    if(data.result){
						$.messager.alert("提示信息", "新建账套成功!");
						$("#default_win").window('close');
					}else{
						$.messager.alert("提示信息", "操作失败，请联系管理员!");
					}
				}
			});
		}
	};
}();