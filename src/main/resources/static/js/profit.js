/**
 * 企业通用配置值的JS脚本文件。
 */
Profit=function(){
	var editIndex = undefined;
	
	return {
		
		//初始化
		init:function(searchtPeriod) {
			
			$('#searchtPeriod').numberspinner({
				min:1,
				max:12,
				value:searchtPeriod,
				onChange:function(){
					$('#profitDg').datagrid('reload');
				}
			});
			
			//表格
			var dg = $('#profitDg').datagrid({
				singleSelect:true,
				fitColumns: true,
				fit:true,
				rownumbers:true,
				clickToEdit: false,
		        dblclickToEdit: true,
				toolbar: '#profitDgTd',
				url:'profit/list',
				method:'get',
				columns:[[
							{field:'cA',title:'A',width:60,align:'left',halign:'center'},
							{field:'cB',title:'B',width:40,align:'right',halign:'center',
								formatter: function(value,row,index){
									if(index==0){
										return '<div style="text-align: center;">'+row.cBVal+'</div>';
									} else {
										return row.cBVal==0?'':Profit.formatCurrency(row.cBVal);
						            }
								},
								editor:'textbox'
							},
							{field:'cC',title:'C',width:40,align:'right',halign:'center',
								formatter: function(value,row,index){
									if(index==0){
										return '<div style="text-align: center;">'+row.cCVal+'</div>';
									} else {
										return row.cCVal==0?'':Profit.formatCurrency(row.cCVal);
						            }
								},
								editor:'textbox'
							}
						]],
				onBeforeLoad:function(param){
					param.searchtPeriod=$('#searchtPeriod').numberspinner('getValue');
				},
				onLoadSuccess:function(data) {
					$(this).datagrid('enableCellEditing', function (cur_idx, pre_idx, field) {
                        //可修改判断
						if(cur_idx==0){
							return false;
						}else{
							return true;
						}
					});
				},
				onEndEdit:function(index,row,changes){
					if(!changes){
						return;
					}
					var cell = $(this).datagrid('cell');
					if(!cell){
						return;
					}
					var rows=$('#profitDg').datagrid('getRows');
					var expStr=changes.cB?changes.cB:changes.cC;
					if(!expStr){
						return;
					}
			        for(i=1;i<rows.length;i++){
			        	expStr=expStr.replace(new RegExp('(B'+(i+1)+')([^0-9]?)','g'),rows[i].cBVal+'$2');
			        	expStr=expStr.replace(new RegExp('(C'+(i+1)+')([^0-9]?)','g'),rows[i].cCVal+'$2');
			        }
			        console.log("count expression:"+expStr);
			        //计算公式
					if(expStr){
						var searchtPeriod=$('#searchtPeriod').numberspinner('getValue');
						$.ajax({
                            url: 'profit/count',
                            data: {expStr: expStr,searchtPeriod: searchtPeriod},
                            type: 'POST',
                            async: true,
                            success: function (data) {
                            	var oldValue = rows[cell.index][cell.field+'Val'];
                                if (data.success||data.success==0) {
                                	rows[cell.index][cell.field+'Val']=data.success;
                                	$('#profitDg').datagrid('updateRow',{
                                		index: cell.index
                                	});
                                	var exp = new RegExp('('+cell.field.replace("c",'')+cell.index+')([^0-9]?)','g');
                                	for(i=1;i<rows.length;i++){
                                		if(rows[i].cB.indexOf(exp)!=-1){
                                			rows[i].cBVal=Profit.sum(rows[i].cB,rows[i].cBVal,exp,oldValue,data.success);
                                			$('#profitDg').datagrid('updateRow',{index: i});
                                		}
                                		if(rows[i].cC.indexOf(exp)!=-1){
                                			rows[i].cCVal=Profit.sum(rows[i].cC,rows[i].cCVal,exp,oldValue,data.success);
                                			$('#profitDg').datagrid('updateRow',{index: i});
                                		}
                                	}
                                } else {
                                	$.messager.alert("错误", data.msg, "error");
                                	rows[cell.index][cell.field+'Val']=oldValue;
                                	$('#profitDg').datagrid('updateRow',{
                                		index: cell.index
                                	});
                                }
                            }
                        });
					}
				},
				onClickCell:function(){
					var cell = $(this).datagrid('cell');
					if(cell){
						$(this).datagrid('endEdit', cell.index);
					}
				}
			});
		},
		sum:function(cExp,cVal,exp,oldValue,newValue){
			var dir = cExp.substr(cExp.indexOf(exp),1);
			switch(dir){
				case '+':cVal=Voucher.accSub(Voucher.accAdd(cVal,newValue),oldValue);break;
				case '-':cVal=Voucher.accAdd(Voucher.accSub(cVal,newValue),oldValue);break;
				case '*':cVal=Voucher.accDiv(Voucher.accMul(cVal,newValue),oldValue);break;
				case '/':cVal=Voucher.accMul(Voucher.accDiv(cVal,newValue),oldValue);break;
				default:cVal=Voucher.accSub(Voucher.accAdd(cVal,newValue),oldValue);break;
			}
			return cVal;
		},
		/**
		 * 将数值四舍五入(保留2位小数)后格式化成金额形式
		 *
		 * @param num 数值(Number或者String)
		 * @return 金额格式的字符串,如'1,234,567.45'
		 * @type String
		 */
		formatCurrency:function (num) {
		    num = num.toString().replace(/\$|\,/g,'');
		    if(isNaN(num))
		    num = "0";
		    sign = (num == (num = Math.abs(num)));
		    num = Math.floor(num*100+0.50000000001);
		    cents = num%100;
		    num = Math.floor(num/100).toString();
		    if(cents<10)
		    cents = "0" + cents;
		    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		    num = num.substring(0,num.length-(4*i+3))+','+
		    num.substring(num.length-(4*i+3));
		    return (((sign)?'':'-') + num + '.' + cents);
		}
    }
}();
