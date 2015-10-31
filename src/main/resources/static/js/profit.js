/**
 * 企业通用配置值的JS脚本文件。
 */
Profit=function(){
	var editIndex = undefined;
	
	return {
		
		//初始化
		init:function(searchtPeriod) {
			$('#profitSave').click(function() {
				Voucher.add();
			});
			$('#profitAppend').click(function() {
				Voucher.add();
			});
			$('#profitRemoveit').click(function() {
				Voucher.add();
			});
			
			$('#searchtPeriod').numberspinner({
				min:1,
				max:12,
				value:searchtPeriod,
				onChange:function(){
					$('#profitDg').datagrid('reload');
				}
			});
			
			Profit.datainit(1);
		},
		datainit:function(flag){
			//表格
			var dg = $('#profitDg').datagrid({
				singleSelect:true,
				//fitColumns: true,
				//fit:true,
				rownumbers:true,
				clickToEdit: false,
		        dblclickToEdit: true,
				toolbar: '#profitMenu,#profitDgTd',
				url:'profit/list',
				columns:[[
							{field:'cA',title:'A',width:260,align:'left',halign:'center'},
							{field:'cB',title:'B',width:240,align:'right',halign:'center',
								formatter: function(value,row,index){
									if(index==0){
										return '<div style="text-align: center;">'+row.cB+'</div>';
									} else {
										return row.cBVal;
						            }
								},
								editor:'textbox'
							},
							{field:'cC',title:'C',width:240,align:'right',halign:'center',
								formatter: function(value,row,index){
									if(index==0){
										return '<div style="text-align: center;">'+row.cC+'</div>';
									} else {
										return row.cCVal;
						            }
								},
								editor:'textbox'
							}
						]],
				onBeforeLoad:function(param){
//					var rows=$('#profitDg').datagrid('getRows');
//					if(rows){
//						for(i=0;i<rows.length;++i){
//							param=Profit.extend(param,JSON.parse(JSON.stringify(rows[i]).replace(/(\[.+\])/g,'$1')));
//						}
//					}
					param.searchtPeriod=$('#searchtPeriod').numberspinner('getValue');
				},
				onLoadSuccess:function(data) {
					if(flag){
						$('#profitDg').datagrid('enableCellEditing', function (cur_idx, pre_idx, field) {
	                        //可修改判断
							if(cur_idx==0){
								return false;
							}else{
								return true;
							}
						});
						flag = undefined;
					}
				},
				onEndEdit:function(index,row,changes){
					var cell =$('#profitDg').datagrid('cell');
					if(!cell){
						return;
					}
					var rows=$('#profitDg').datagrid('getRows');
					var expStr=changes.cB?changes.cB:changes.cC;
					if(!expStr){
						return;
					}
					rows[cell.index][cell.field]=expStr;
			        console.log("count expression:"+expStr);
			        //计算公式
			        var searchtPeriod=$('#searchtPeriod').numberspinner('getValue');
			        //$('#profitDg').datagrid('reload');
					$.ajax({
                        url: 'profit/count',
                        data: JSON.stringify(rows),
                        type: 'POST',
                        dataType:"json",      
                        contentType:"application/json",
                        async: true,
                        success: function (data) {
                        	console.log(data);
                            if (data) {
                            	$('#profitDg').datagrid('loadData', data);
                            } else {
                            	$.messager.alert("错误", data.msg, "error");
                            }
                        }
                    });
				},
				onClickCell:function(){
					var cell = $('#profitDg').datagrid('cell');
					if(cell){
						$('#profitDg').datagrid('endEdit', cell.index);
					}
				}
			});
		},
		extend:function(src, override){
			for(var i in override){
				if(!override[i]||override[i]=='null'){
					override[i] = '';
				}
				if(i in src){
					src[i] = src[i]+','+override[i];
				} else {
					src[i] = override[i];
				}
			} 
			return src;
		},
		//表格修改数据
		getChanges:function(){
			var params='';
			var rows = $('#profitDg').datagrid('getRows');
			for(i=0;i<rows.length;++i){
				if(rows[i].subjectCode){
					params+='&'+JSON.stringify(rows[i]);
				}
			}
			params=params.replace(/{/g,'').replace(/}/g,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
			params=params.replace(/,/g,'&').replace(/undefined/g,'');
			return params;
		},
		updateRows:function(){
			var exp = new RegExp(cell.field.replace("c",'')+cell.index+'([^0-9])?','g');
        	console.log(exp);
        	console.log(exp.test('=B2+B3+B4'));
        	for(i=1;i<rows.length;i++){
        		if(exp.test(rows[i].cB)){
        			rows[i].cBVal=Profit.sum(rows[i].cB,rows[i].cBVal,exp,oldValue,data.success);
        			$('#profitDg').datagrid('updateRow',{index: i});
        		}
        		if(exp.test(rows[i].cC)){
        			rows[i].cCVal=Profit.sum(rows[i].cC,rows[i].cCVal,exp,oldValue,data.success);
        			$('#profitDg').datagrid('updateRow',{index: i});
        		}
        	}
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
