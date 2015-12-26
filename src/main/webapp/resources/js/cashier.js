Cashier=function(){
	return {
		init:function(){
			$('#carryOver').click(function(){Cashier.carryOver();});
			$('#cashier').click(function(){Cashier.cashier();});
		},
		carryOver:function(){
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
		},
		cashier:function(){
			//先判断本期是否平衡，再进行弹出提示操作
			$.ajax({
                url: "account/cashier/is/balance",
                success: function(data){
                    if(data){
                    	var content="<table style='width:100%;' cellpadding='5'><tr><td style='vertical-align: top;'>" +
                    			"<button class='button button-3d button-action button-circle button-jumbo'><i class='fa fa-info-circle fa-2x' style='line-height:60px;'></i></button>"+
                    			"</td><td style='font-size:13px;'>请在结账之前仔细检查：" +
                    			"<br/>1）各种业务是否都编制记账凭证，没有漏记、错记；" +
                    			"<br/>2）“结转损益”只结转损益类科目，不包含成本类科目。系统结账之后，不允许再反结账修改。是否要进行结账操作？</td></tr></table>";
                    	$('#newDialog').dialog({
                    	    title: '提示信息',
                    	    width: 440, 
                    	    height: 240,
                    	    closed: false,
                    	    region:'center',
                    	    cache: false,
                    	    padding:10,
                   		 	content:content,
                    	    modal: true,
                    	    buttons:[{
                				text:'确定',
                				handler:function(){
                					$.ajax({ 
        	                        	url: 'account/cashier/submit',
        	            				context: document.body,
        	            				success:function(data){
        	            					if(data==1){
        	            					    $.messager.alert("提示信息", "操作成功!","info",function(){
        	            					    	$('#newDialog').dialog('close');
        	                               		 });
        	            					}else{
        	            						$.messager.alert("提示信息", "操作失败！","error");
        	            					}
        	            				}
                					});
                				}
                			},{
                				text:'取消',
                				handler:function(){
                					$('#newDialog').dialog('close');
                				}
                			}]
                    	});
                    	
                    }else{
                        $.messager.alert("提示信息", "会计科目不平衡,请查看初始化数据!不允许结账!");
                        $('#newDialog').dialog('close');
                    }
                }
            });
		}
	}
}();