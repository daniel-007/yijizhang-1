/**
 * 试算平衡的JS脚本文件。
 */
TrialBalance=function(){
	return {
		//查询-试算平衡表页面初始化
		init:function(currentPeriod,level){
			TrialBalance.dgInit(currentPeriod,level);
			$('#trialbalanceListSearch').click(function(){
				$("#default_win").window({
					title : '<i class="fa fa-info-circle"></i>试算平衡表',
					width : 268,
					height : 210,
					modal : true,
					collapsible : false,
					shadow : true,
					href : 'search/trialbalance/search',
					queryParams:{currentPeriod:currentPeriod,level:level}
				});
			});
			$('#trialbalanceListRefresh').bind('click', function(){
				TrialBalance.refresh(currentPeriod,level);
			});
		},
        //查询-试算平衡表过滤页面初始化
        searchInit:function(currentPeriod,level){
        	$('#trialbalanceListSubmit').click(function(){
        		TrialBalance.submit();
        	});
        	$('#trialbalanceListReject').click(function(){
        		$("#default_win").window('close');
        	});
        },
        //过滤页面-确定
        submit:function(){
        	if(!$('#trialbalanceListFm').form('validate')){// 表单验证
				return;
			}
    		var currentPeriod=$('#trialcurrentPeriod').numberspinner('getValue');
    		var level=$('#triallevel').numberspinner('getValue');
    		$("#default_win").window('close');
    		TrialBalance.refresh(currentPeriod,level);
        },
        //刷新
        refresh:function(currentPeriod,level){
        	var tab = $TC.tabs('getSelected');
        	$('#tabContainer').tabs('update', {
         		tab: tab,
         		options: {
         			href: 'search/trialbalance/filter',
         			queryParams:{currentPeriod:currentPeriod,level:level}
         		}
         	});
        	tab.panel('refresh');
        },
        //表格初始化
        dgInit:function(currentPeriod,level){
    		$('#trialbalanceListDg').datagrid({
    			singleSelect:true,
    			fitColumns: true,
    			fit:true,
    			toolbar: '#trialbalanceListMenu',
    			url:'search/trialbalance/list',
    			queryParams:{currentPeriod:currentPeriod,level:level},
    			method:'get',
    			columns:[[
    			          {field:'subject_code',title:'科目代码',width:60,align:'left',halign:'center',rowspan:2},
    			          {field:'subject_name',title:'科目名称',width:60,align:'left',halign:'center',rowspan:2},
    			          {title:'期初余额',colspan:2},
    			          {title:'本期发生额',colspan:2},
    			          {title:'期末余额',colspan:2}
  				      ],[
    			          {field:'initial_debit_balance',title:'借方',width:60,align:'right',halign:'center'},
    			          {field:'initial_credit_balance',title:'贷方',width:60,align:'right',halign:'center'},
    			          {field:'period_debit_occur',title:'借方',width:60,align:'right',halign:'center'},
    			          {field:'period_credit_occur',title:'贷方',width:60,align:'right',halign:'center'},
    			          {field:'terminal_debit_balance',title:'借方',width:60,align:'right',halign:'center'},
    			          {field:'terminal_credit_balance',title:'贷方',width:60,align:'right',halign:'center'}
			          ]],
	            rowStyler: function(index,row){
		      		if (row.subject_name=='合计'){
		      			return 'background-color:#6293BB;color:#fff;';
		      		}
		      		if(index%2==0){
		      			return 'background-color:#E6E6FA;color:#000000;';
		      		}
		      	}
    		});
        }
	};
}();
