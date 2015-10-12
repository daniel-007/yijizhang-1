/**
 * 科目余额的JS脚本文件。
 */
SubjectBalance=function(){
	return {
		//页面初始化
		voucherInit:function(id) {
			$('#voucherBalanceDg').datagrid({
				heigth:400,
				singleSelect:true,
				fitColumns: true,
				fit:true,
				onClickCell:Voucher.onClickCell,
				url:'subjectBalance/subjectBalanceList',
				queryParams:{subjectCode:id},
				method:'get',
				columns:[[
			          	{field:'subject_name',title:'科目名称',width:60,align:'left',halign:'center'},
			          	{field:'initial_debit_balance',title:'期初借方金额',width:60,align:'right',halign:'center'},
			          	{field:'initial_credit_balance',title:'期初贷方金额',width:60,align:'right',halign:'center'},
			          	{field:'period_debit_occur',title:'本期借方发生额',width:60,align:'right',halign:'center'},
			          	{field:'period_credit_occur',title:'本期贷方发生额',width:60,align:'right',halign:'center'},
			          	{field:'year_debit_occur',title:'本年累计借方发生额',width:60,align:'right',halign:'center'},
			          	{field:'year_credit_occur',title:'本年累计贷方发生额',width:60,align:'right',halign:'center'},
			          	{field:'terminal_debit_balance',title:'期末借方金额',width:60,align:'right',halign:'center'},
			          	{field:'terminal_credit_balance',title:'期末贷方金额',width:60,align:'right',halign:'center'}
				]]
			});
        },
        //页面初始化
        init:function(){
        }
	};
}();
