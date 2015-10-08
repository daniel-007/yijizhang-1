/**
 * 记账凭证的JS脚本文件。
 */
$(document).ready(function () {
	
});

Voucher=function(){
	var editIndex = undefined;//表格编辑index
	var oldValue = 0;//金额单元格的旧的值
	var row = undefined;//footer的第一行数据
	var debit = undefined;//总计借方金额
	var crebit = undefined;//总计贷方金额
	var balanceFlag = false;//借贷平衡标识符
	
	return {
		//凭证页面初始化
		init:function(id) {
			$('#dg').datagrid({
				heigth:400,
				singleSelect:true,
				fitColumns: true,
				//fit:true,
				//toolbar: '#tb',
				onClickCell:Voucher.onClickCell,
				url:'voucher/voucherDetailList',
				queryParams:{voucherId:id},
				method:'get',
				showFooter:true,
				columns:[[
						{field:'summary',title:'摘要',width:60,halign:'center',editor:'textbox',rowspan:2},
						{field:'subjectCode',title:'科目代码',width:30,halign:'center',editor:{
							type:'combobox',
							options:{
							    valueField:'subjectTextName',
							    textField:'subjectTextName',
							    method:'get',
							    url:'/voucher/accountSubjectList'
							        }},rowspan:2},
						{title:'借方金额',colspan:11},
						{title:'贷方金额',colspan:11}
				      ],[
						{field:'dHundredMillion',title:'亿',width:5,align:'center',editor:{type:'textMax',options:{num:10000000000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dTenMillions',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dMillions',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dHundredThousand',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dTenThousand',title:'万',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dThousand',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dHundred',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dTen',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dYuan',title:'元',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dAngle',title:'角',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'dCent',title:'分',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1,direction:'d'}},formatter:Voucher.formatDebit},
						{field:'crHundredMillion',title:'亿',width:5,align:'center',editor:{type:'textMax',options:{num:10000000000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crTenMillions',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crMillions',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crHundredThousand',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crTenThousand',title:'万',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crThousand',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crHundred',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crTen',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crYuan',title:'元',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crAngle',title:'角',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10,direction:'cr'}},formatter:Voucher.formatCredit},
						{field:'crCent',title:'分',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1,direction:'cr'}},formatter:Voucher.formatCredit}
				      ]],
				onLoadSuccess:function(data){
					if(data&&data.total>=5){
						console.log('not null');
					} else {
						var len = data&&data.total>0?5-data.total:5;
				        for(i=0;i<len;++i){
				        	$('#dg').datagrid('appendRow',{});
				        }
					}
					Voucher.initFooterCells();
					editIndex = undefined;
					$('#dg').datagrid('fitColumns');
				}
			});
			
			$('#voucherWord').combobox({
			    url:'voucher/voucherWordList',
			    method:'get',
			    valueField:'showValue',
			    textField:'showValue',
			    editable:false,
			    onLoadSuccess:function(){
			    	$('#voucherWord').combobox('setValue', '记');
			    }
			});
        },
        //合并footer中的单元格
        mergeFooterCells:function(){
			$('#dg').datagrid('mergeCells', {
				index: 0,
				field: 'summary',
				colspan: 2,
				type: 'footer'
			});
        },
        //初始化总计金额
        initFooterCells:function(){
			row = $('#dg').datagrid('getFooterRows')[0];
			debit = Voucher.cellSetValue(row['debit']);
			crebit = Voucher.cellSetValue(row['credit']);
			if(debit){
				Voucher.changeFooter(debit,"d");
			}
			if(crebit){
				Voucher.changeFooter(crebit,"cr");
			}
        },
        //结束编辑
		endEditing:function(){
		    if (editIndex == undefined){return true}
		    if ($('#dg').datagrid('validateRow', editIndex)){
		        $('#dg').datagrid('endEdit', editIndex);
		        editIndex = undefined;
		        return true;
		    } else {
		        return false;
		    }
		},
		//编辑表格行
		onClickCell:function(index, field){
		    if (editIndex != index){
		        if (Voucher.endEditing()){
		            $('#dg').datagrid('selectRow', index)
		                    .datagrid('beginEdit', index);
		            editIndex = index;
		        } else {
		            $('#dg').datagrid('selectRow', editIndex);
		        }
		    }
		},
		//插入行
		append:function(){
		    if (Voucher.endEditing()){
		        $('#dg').datagrid('appendRow',{});
		        editIndex = $('#dg').datagrid('getRows').length-1;
		        $('#dg').datagrid('selectRow', editIndex)
		                .datagrid('beginEdit', editIndex);
		    }
		},
		//删除行
		removeit:function(){
		    if (editIndex == undefined){return}
		    $('#dg').datagrid('cancelEdit', editIndex)
		            .datagrid('deleteRow', editIndex);
		    editIndex = undefined;
		},
		//取消修改
		reject:function(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        },
        //保存
		save:function(isAdd){
			if (Voucher.endEditing()){
				
				// 借贷平衡
				if(!balanceFlag){
					$.messager.alert("提示信息", "借贷不平衡!");
					return;
				}
				
				// 验证
				if(!$('#fm').form('validate')){
					return;
				}
				
				// 表格修改数据
				var params=Voucher.getChanges();
				if(!params){
					$.messager.alert("提示信息", "无凭证分录!");
					return;
				}
				console.log(params);
				// 提交保存
	            $.ajax({
	                url: "voucher/save",
	                type:'post',
	                data:$("#fm").serialize()+params,
	                success: function(data){
	                    if(data.result){
	                        $.messager.alert("提示信息", "已生成了一张记账凭证，凭证字号为："+data.result);
	                        if(isAdd){//新增
	                        	Voucher.add();
	                        }
	                    }else{
	                        $.messager.alert("提示信息", "操作失败，请联系管理员!");
	                    }
	                }
	            });
		    }
		},
		//表格修改数据
		getChanges:function(){
			var params='';
			var rows = $('#dg').datagrid('getRows');
			for(i=0;i<rows.length;++i){
				if(rows[i].subjectCode){
					//修改会计科目
					rows[i].subjectCode=rows[i].subjectCode.split(" ")[0];
					params = Voucher.jsonToString(params, rows[i]);
					//借方金额
					params += "&newDebit="+Voucher.getDebit(rows[i]);
					console.log(Voucher.getDebit(rows[i]));
					//贷方金额
					params += "&newCrebit="+Voucher.getCredit(rows[i]);
					console.log(Voucher.getCredit(rows[i]));
				}
			}
			params=params.replace(/{/g,'').replace(/}/g,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
			params=params.replace(/,/g,'&').replace(/undefined/g,'');
			return params;
		},
		//组织表格输入的提交数据
		jsonToString:function(destination, source) {
			return destination+'&'+JSON.stringify(source);
		},
		//借方金额
		getDebit:function(row){
			var pval='';
			pval+=Voucher.getParamValue(pval,row.dHundredMillion);
			pval+=Voucher.getParamValue(pval,row.dTenMillions);
			pval+=Voucher.getParamValue(pval,row.dMillions);
			pval+=Voucher.getParamValue(pval,row.dHundredThousand);
			pval+=Voucher.getParamValue(pval,row.dTenThousand);
			pval+=Voucher.getParamValue(pval,row.dThousand);
			pval+=Voucher.getParamValue(pval,row.dHundred);
			pval+=Voucher.getParamValue(pval,row.dTen);
			pval+=Voucher.getParamValue(pval,row.dYuan);
			if(Voucher.cellSetValue(row.dAngle)!=0||Voucher.cellSetValue(row.dCent)!=0){
				if(!pval)pval+='0';
				pval+='.';
				pval+=Voucher.cellSetValue(row.dAngle);
				pval+=Voucher.cellSetValue(row.dCent);
			}
			if(row.dpn=='-'&&pval){
				pval="-"+pval;
			}
			return pval;
		},
		//贷方金额
		getCredit:function(row){
			var pval='';
			pval+=Voucher.getParamValue(pval,row.crHundredMillion);
			pval+=Voucher.getParamValue(pval,row.crTenMillions);
			pval+=Voucher.getParamValue(pval,row.crMillions);
			pval+=Voucher.getParamValue(pval,row.crHundredThousand);
			pval+=Voucher.getParamValue(pval,row.crTenThousand);
			pval+=Voucher.getParamValue(pval,row.crThousand);
			pval+=Voucher.getParamValue(pval,row.crHundred);
			pval+=Voucher.getParamValue(pval,row.crTen);
			pval+=Voucher.getParamValue(pval,row.crYuan);
			if(Voucher.cellSetValue(row.crAngle)!=0||Voucher.cellSetValue(row.crCent)!=0){
				if(!pval)pval+='0';
				pval+='.';
				pval+=Voucher.cellSetValue(row.crAngle);
				pval+=Voucher.cellSetValue(row.crCent);
			}
			if(row.crpn=='-'&&pval){
				pval="-"+pval;
			}
			return pval;
		},
		getParamValue:function(pval,value){
        	if(value&&!isNaN(value)){
        		return value;
        	}else if(pval){
        		return 0;
        	}else{
        		return '';
        	}
		},
		//时间格式化
		myformatter:function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        },
        //时间格式化
        myparser:function(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        },
        //新增
        add:function(){
        	var tab = $TC.tabs('getSelected');  // get selected panel
        	tab.panel('refresh', 'voucher/main'+"?&time="+new Date().getTime());
        },
        //金额editor事件
        cellkeyDown:function(tthis,num,direction){
        	var changeValue=$('#dg').datagrid('getRows')[editIndex][direction+'pn']+Voucher.cellSetValue(tthis.value);
        	if(direction=='d'){
        		oldValue=parseInt(debit)-changeValue*num;
        	}else if(direction=='cr'){
        		oldValue=parseInt(crebit)-changeValue*num;
        	}else{
        		console.log("no change");
        	}
        	console.log(debit+":"+crebit);
        },
        /*金额editor事件*/
        cellkeyUp:function(event,tthis,num,direction){
        	//oldValue=Voucher.cellSetValue(tthis.value);
        	if(event.which==189){//189：'-'负号
        		console.log(Voucher.getDebit($('#dg').datagrid('getRows')[editIndex])+":"+Voucher.getCredit($('#dg').datagrid('getRows')[editIndex]));
        		if(direction=='d'){
        			oldValue=parseInt(oldValue)-Voucher.getDebit($('#dg').datagrid('getRows')[editIndex])*200;
        		}else if(direction=='cr'){
        			oldValue=parseInt(oldValue)-Voucher.getCredit($('#dg').datagrid('getRows')[editIndex])*200;
        		}else{
        			console.log("no change");
        		}
        		if($('#dg').datagrid('getRows')[editIndex][direction+'pn']=='-'){
        			$('#dg').datagrid('getRows')[editIndex][direction+'pn']='';
        			$("."+direction+"textMax").css("color","black");
        		}else{
        			$('#dg').datagrid('getRows')[editIndex][direction+'pn']='-';
        			$("."+direction+"textMax").css("color","red");
        		}
        	}
        	console.log(debit+":"+crebit);
        	var changeValue=$('#dg').datagrid('getRows')[editIndex][direction+'pn']+Voucher.cellSetValue(tthis.value);
        	if(direction=='d'){
        		debit=parseInt(oldValue)+changeValue*num;
        		Voucher.changeFooter(debit,direction);
        	}else if(direction=='cr'){
        		crebit=parseInt(oldValue)+changeValue*num;
        		Voucher.changeFooter(crebit,direction);
        	}else{
        		console.log("no change");
        	}
        	console.log(debit+":"+crebit);
        },
        //检查value为空或不是数字时置为0
        cellSetValue:function(value){
        	if(value&&!isNaN(value)){
        		return value;
        	}else {
        		return 0;
        	}
        },
        //合计中文
        footerDX:function(){
        	row['summary']='合计：';
        	if(debit==crebit){//借贷平衡
        		balanceFlag=true;
        		row['summary']+=Voucher.DX(debit/100);
        	}
        	$('#dg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
        },
        //修改表格footer总计金额
        changeFooter:function(value,direction){
        	var valueStr = value+"";
        	var length = valueStr.length;
        	var i = 0;
        	if(length>11){
        		i=length-11;
        	}
        	row[direction+'HundredMillion']=length>=11?valueStr.charAt(i++):'';
        	row[direction+'TenMillions']=length>=10?valueStr.charAt(i++):'';
        	row[direction+'Millions']=length>=9?valueStr.charAt(i++):'';
        	row[direction+'HundredThousand']=length>=8?valueStr.charAt(i++):'';
        	row[direction+'TenThousand']=length>=7?valueStr.charAt(i++):'';
        	row[direction+'Thousand']=length>=6?valueStr.charAt(i++):'';
        	row[direction+'Hundred']=length>=5?valueStr.charAt(i++):'';
        	row[direction+'Ten']=length>=4?valueStr.charAt(i++):'';
        	row[direction+'Yuan']=length>=3?valueStr.charAt(i++):'0';
        	row[direction+'Angle']=length>=2?valueStr.charAt(i++):'0';
        	row[direction+'Cent']=length>=1?valueStr.charAt(i++):'0';
        	if(value<0){
        		row[direction+'HundredMillion']='<span style="color:red;">'+row[direction+'HundredMillion']+'</span>';
        		row[direction+'TenMillions']='<span style="color:red;">'+row[direction+'TenMillions']+'</span>';
        		row[direction+'Millions']='<span style="color:red;">'+row[direction+'Millions']+'</span>';
        		row[direction+'HundredThousand']='<span style="color:red;">'+row[direction+'HundredThousand']+'</span>';
        		row[direction+'TenThousand']='<span style="color:red;">'+row[direction+'TenThousand']+'</span>';
        		row[direction+'Thousand']='<span style="color:red;">'+row[direction+'Thousand']+'</span>';
        		row[direction+'Hundred']='<span style="color:red;">'+row[direction+'Hundred']+'</span>';
        		row[direction+'Ten']='<span style="color:red;">'+row[direction+'Ten']+'</span>';
        		row[direction+'Yuan']='<span style="color:red;">'+row[direction+'Yuan']+'</span>';
        		row[direction+'Angle']='<span style="color:red;">'+row[direction+'Angle']+'</span>';
        		row[direction+'Cent']='<span style="color:red;">'+row[direction+'Cent']+'</span>';
        	}
        	$('#dg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
        	Voucher.footerDX();
        },
        // 阿拉伯数字转中文大写
        DX:function(n){
        	if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
	            return "数据非法";
	        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	            n += "00";
	        var p = n.indexOf('.');
	        if (p >= 0)
	            n = n.substring(0, p) + n.substr(p+1, 2);
	            unit = unit.substr(unit.length - n.length);
	        for (var i=0; i < n.length; i++)
	            str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	        return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
        },
        // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
        OnInput:function(event) {
            alert ("The new content: " + event.target.value);
        },
        // Internet Explorer
        OnPropChanged:function(event) {
            if (event.propertyName.toLowerCase () == "value") {
                alert ("The new content: " + event.srcElement.value);
            }
        },
        formatDebit:function(val,row){
            if (row.dpn=='-'&&val){
                return '<span style="color:red;">'+val+'</span>';
            } else {
                return val;
            }
        },
        formatCredit:function(val,row){
            if (row.crpn=='-'&&val){
                return '<span style="color:red;">'+val+'</span>';
            } else {
                return val;
            }
        }
	};
}();

//扩展的金额editor
$.extend($.fn.datagrid.defaults.editors, {
    textMax: {
        init: function(container, options){
            var input = $('<input type="text" class="'+options.direction+'textMax" maxlength="1" onkeyup="Voucher.cellkeyUp(event,this,'+options.num+',\''+options.direction+'\')" onfocus="Voucher.cellkeyDown(this,'+options.num+',\''+options.direction+'\')" class="datagrid-editable-input">').appendTo(container);
            //var input = $('<input type="text" maxlength="1" onkeyup="Voucher.cellkeyUp(this,'+options.num+',\''+options.direction+'\')" class="datagrid-editable-input">').appendTo(container);
            return input;
        },
        destroy: function(target){
            $(target).remove();
        },
        getValue: function(target){
            return $(target).val();
        },
        setValue: function(target, value){
            $(target).val(value);
        },
        resize: function(target, width){
            $(target)._outerWidth(width);
        }
    }
});
