/**
 * Application通用函数集合
 */
$(document).ready(function(){
	$TC = $('#tabContainer');

	//绑定事件
	$('#switchBtn').click(function(){
		$('#accountBookList').combobox({
			url:'account/book/list',
			valueField:'id',
			textField:'bookName',
			editable:false,
			onSelect: function(rec){
				if(rec.id == $('#currentAccountBookId').val()){
					$('#confirmSwitchBtn').attr('disabled','disabled');
				}
			},
			onLoadSuccess:function(){
				$('#accountBookList').combobox('setValue', $('#currentAccountBookId').val());
			}
		});
		$('.top-bar .info .textbox-text').css({'padding-top':'3px'});
		$("#accountBookList + .combo").show();
		$('#switchBtn').hide();
		$('#confirmSwitchBtn').show();
		$('#cancelSwitchBtn').show();
	});

	$('#confirmSwitchBtn').click(function(){
		var id = $('#accountBookList').combobox('getValue');
		if(id != $('#currentAccountBookId').val()){
			$.ajax({
				url: "switch/to/book/" + id,
				context: document.body,
				success:function(data){
					if(data){
						document.location.reload();
					}else{
						$.messager.alert("提示信息", "切换账套失败，请稍候重试！","error");
					}
				}
			});
		}
	});

	$('#cancelSwitchBtn').click(function(){
		$("#accountBookList + .combo").hide();
		$('#confirmSwitchBtn').hide();
		$('#cancelSwitchBtn').hide();
		$('#switchBtn').show();
	});


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