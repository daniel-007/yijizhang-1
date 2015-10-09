/**
 * 记账凭证的JS脚本文件。
 */
$(document).ready(function () {
	
});

Voucher=function(){
	var editIndex = undefined;//表格编辑index
	var row = undefined;//footer的第一行数据
	var debit = undefined;//总计借方金额
	var crebit = undefined;//总计贷方金额
	var balanceFlag = false;//借贷平衡标识符
	
	return {
		//凭证页面初始化
		init:function(id) {
			//新增
			$('#voucherAdd,#voucherMm1Add').on('click', function() {
				Voucher.add();
			});
			//保存并新增
			$('#voucherSave,#voucherMm2Save2').on('click', function() {
				Voucher.save(1);
			});
			//保存
			$('#voucherMm2Save1').on('click', function() {
				Voucher.save();
			});
			//取消修改
			$('#voucherReject').on('click', function() {
				Voucher.reject();
			});
			//插入行
			$('#voucherAppend').on('click', function() {
				Voucher.append();
			});
			//删除行
			$('#voucherRemoveit').on('click', function() {
				Voucher.removeit();
			});
			//明细 TODO
			$('#voucherSubjectDetail').on('click', function() {
			});
			//科目余额 TODO
			$('#voucherSubjectBalance').on('click', function() {
				$("#default_win").window({
					title : '<i class="fa fa-info-circle"></i>凭证编制说明',
					width : 650,
					height : 500,
					modal : true,
					collapsible : false,
					shadow : true,
					href : 'voucher/help'
				});
			});
			//凭证编制说明
			$('#voucherHelp').on('click', function() {
				$("#default_win").window({
					title : '<i class="fa fa-info-circle"></i>凭证编制说明',
					width : 650,
					height : 500,
					modal : true,
					collapsible : false,
					shadow : true,
					href : 'voucher/help'
				});
			});
			//凭证字
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
			//日期
			$('#voucherTime').datebox({
				formatter:Voucher.myformatter,
				parser:Voucher.myparser,
				editable:false
			});
			
			//凭证分录表格
			$('#voucherDg').datagrid({
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
						{field:'dHundredMillion',title:'亿',width:5,align:'center',editor:{type:'textMax',options:{num:10000000000,direction:'d',field:'dHundredMillion'}},formatter:Voucher.formatDebit},
						{field:'dTenMillions',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000000,direction:'d',field:'dTenMillions'}},formatter:Voucher.formatDebit},
						{field:'dMillions',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000000,direction:'d',field:'dMillions'}},formatter:Voucher.formatDebit},
						{field:'dHundredThousand',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000000,direction:'d',field:'dHundredThousand'}},formatter:Voucher.formatDebit},
						{field:'dTenThousand',title:'万',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000,direction:'d',field:'dTenThousand'}},formatter:Voucher.formatDebit},
						{field:'dThousand',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000,direction:'d',field:'dThousand'}},formatter:Voucher.formatDebit},
						{field:'dHundred',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000,direction:'d',field:'dHundred'}},formatter:Voucher.formatDebit},
						{field:'dTen',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000,direction:'d',field:'dTen'}},formatter:Voucher.formatDebit},
						{field:'dYuan',title:'元',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100,direction:'d',field:'dYuan'}},formatter:Voucher.formatDebit},
						{field:'dAngle',title:'角',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10,direction:'d',field:'dAngle'}},formatter:Voucher.formatDebit},
						{field:'dCent',title:'分',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1,direction:'d',field:'dCent'}},formatter:Voucher.formatDebit},
						{field:'crHundredMillion',title:'亿',width:5,align:'center',editor:{type:'textMax',options:{num:10000000000,direction:'cr',field:'crHundredMillion'}},formatter:Voucher.formatCredit},
						{field:'crTenMillions',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000000,direction:'cr',field:'crTenMillions'}},formatter:Voucher.formatCredit},
						{field:'crMillions',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000000,direction:'cr',field:'crMillions'}},formatter:Voucher.formatCredit},
						{field:'crHundredThousand',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000000,direction:'cr',field:'crHundredThousand'}},formatter:Voucher.formatCredit},
						{field:'crTenThousand',title:'万',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000000,direction:'cr',field:'crTenThousand'}},formatter:Voucher.formatCredit},
						{field:'crThousand',title:'千',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100000,direction:'cr',field:'crThousand'}},formatter:Voucher.formatCredit},
						{field:'crHundred',title:'百',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10000,direction:'cr',field:'crHundred'}},formatter:Voucher.formatCredit},
						{field:'crTen',title:'十',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1000,direction:'cr',field:'crTen'}},formatter:Voucher.formatCredit},
						{field:'crYuan',title:'元',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:100,direction:'cr',field:'crYuan'}},formatter:Voucher.formatCredit},
						{field:'crAngle',title:'角',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:10,direction:'cr',field:'crAngle'}},formatter:Voucher.formatCredit},
						{field:'crCent',title:'分',width:5,align:'center',editor:'textbox',editor:{type:'textMax',options:{num:1,direction:'cr',field:'crCent'}},formatter:Voucher.formatCredit}
				      ]],
				onLoadSuccess:function(data){
					if(data&&data.total>=5){
						console.log('not null');
					} else {
						var len = data&&data.total>0?5-data.total:5;
				        for(i=0;i<len;++i){
				        	$('#voucherDg').datagrid('appendRow',{});
				        }
					}
					Voucher.initFooterCells();
					editIndex = undefined;
					$('#voucherDg').datagrid('fitColumns');
				}
			});
        },
        //合并footer中的单元格
        mergeFooterCells:function(){
			$('#voucherDg').datagrid('mergeCells', {
				index: 0,
				field: 'summary',
				colspan: 2,
				type: 'footer'
			});
        },
        //初始化总计金额
        initFooterCells:function(){
			row = $('#voucherDg').datagrid('getFooterRows')[0];
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
		    if ($('#voucherDg').datagrid('validateRow', editIndex)){
		        $('#voucherDg').datagrid('endEdit', editIndex);
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
		            $('#voucherDg').datagrid('selectRow', index)
		                    .datagrid('beginEdit', index);
		            editIndex = index;
		        } else {
		            $('#voucherDg').datagrid('selectRow', editIndex);
		        }
		        Voucher.changeMoneyColor('d',editIndex);
		        Voucher.changeMoneyColor('cr',editIndex);
		    }
		},
		changeMoneyColor:function(direction,editIndex){
			if($('#voucherDg').datagrid('getRows')[editIndex][direction+'pn']=='-'){
    			$("."+direction+"textMax").css("color","red");
    		}else{//金额为负时，变红色
    			$("."+direction+"textMax").css("color","black");
    		}
		},
		//插入行
		append:function(){
		    if (Voucher.endEditing()){
		        $('#voucherDg').datagrid('appendRow',{});
		        editIndex = $('#voucherDg').datagrid('getRows').length-1;
		        $('#voucherDg').datagrid('selectRow', editIndex)
		                .datagrid('beginEdit', editIndex);
		    }
		},
		//删除行
		removeit:function(){
		    if (editIndex == undefined){return}
		    $('#voucherDg').datagrid('cancelEdit', editIndex)
		            .datagrid('deleteRow', editIndex);
		    editIndex = undefined;
		},
		//取消修改
		reject:function(){
            $('#voucherDg').datagrid('rejectChanges');
            editIndex = undefined;
        },
        //保存
		save:function(isAdd){
			if (Voucher.endEditing()){
				if(!balanceFlag){// 借贷平衡
					$.messager.alert("提示信息", "借贷不平衡!");
					return;
				}
				if(!$('#fm').form('validate')){// 凭证验证
					return;
				}
				// 凭证分录数据
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
	                        if(!$("#id").val()){
	                        	$.messager.alert("提示信息", "已生成了一张记账凭证，凭证字号为："+data.result);
	                        }
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
			var rows = $('#voucherDg').datagrid('getRows');
			for(i=0;i<rows.length;++i){
				if(rows[i].subjectCode){
					//修改会计科目
					rows[i].subjectCode=rows[i].subjectCode.split(" ")[0];
					params = Voucher.jsonToString(params, rows[i]);
					//借方金额
					params += "&newDebit="+Voucher.getMoney('d',rows[i]);
					//贷方金额
					params += "&newCrebit="+Voucher.getMoney('cr',rows[i]);
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
		//单行金额合计
		getMoney:function(direction,row){
			var pval='';
			pval+=Voucher.getParamValue(pval,row[direction+'HundredMillion']);
			pval+=Voucher.getParamValue(pval,row[direction+'TenMillions']);
			pval+=Voucher.getParamValue(pval,row[direction+'Millions']);
			pval+=Voucher.getParamValue(pval,row[direction+'HundredThousand']);
			pval+=Voucher.getParamValue(pval,row[direction+'TenThousand']);
			pval+=Voucher.getParamValue(pval,row[direction+'Thousand']);
			pval+=Voucher.getParamValue(pval,row[direction+'Hundred']);
			pval+=Voucher.getParamValue(pval,row[direction+'Ten']);
			pval+=Voucher.getParamValue(pval,row[direction+'Yuan']);
			if(Voucher.cellSetValue(row[direction+'Angle'])!=0||Voucher.cellSetValue(row[direction+'Cent'])!=0){
				if(!pval)pval+='0';
				pval+='.';
				pval+=Voucher.cellSetValue(row[direction+'Angle']);
				pval+=Voucher.cellSetValue(row[direction+'Cent']);
			}
			if(row[direction+'pn']=='-'&&pval){
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
        /*金额editor事件*/
        cellkeyUp:function(event,tthis,num,direction,field){
        	if(tthis.value&&tthis.value.length>1){
        		tthis.value=tthis.value.charAt(1);
        	}
        	var rows=$('#voucherDg').datagrid('getRows');
        	//189：按键'-'负号，金额为负
        	if(event.keyCode==189){
        		if(rows[editIndex][direction+'pn']=='-'){
        			rows[editIndex][direction+'pn']='';
        			$("."+direction+"textMax").css("color","black");
        		}else{//金额为负时，变红色
        			rows[editIndex][direction+'pn']='-';
        			$("."+direction+"textMax").css("color","red");
        		}
        	}
        	//合计
        	rows[editIndex][field]=Voucher.cellSetValue(tthis.value);
			var money=0;
			for(i=0;i<rows.length;++i){
				money=parseInt(money)+Voucher.getMoney(direction,rows[i])*100;
			}
			if(direction=='d'){
				debit=money;
				Voucher.changeFooter(debit,direction);
    		}else if(direction=='cr'){
    			crebit=money;
    			Voucher.changeFooter(crebit,direction);
    		}else{
    			console.log("editor direction error");
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
        	$('#voucherDg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
        },
        //修改表格footer总计金额
        changeFooter:function(value,direction){
        	var valueStr = value<0?(value+'').substr(1):value+'';
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
        	if(value<0){//金额为负时，变红色
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
        	$('#voucherDg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
        	Voucher.footerDX();
        },
        // 阿拉伯数字转中文大写
        DX:function(n){
        	if (!n||isNaN(n))
	            return "数据非法";
        	var flag=n<0;
	        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	            n += "00";
			var n=flag?n.substr(1):n;
	        var p = n.indexOf('.');
	        if (p >= 0)
	            n = n.substring(0, p) + n.substr(p+1, 2);
	            unit = unit.substr(unit.length - n.length);
	        for (var i=0; i < n.length; i++)
	            str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	        return (flag?'负':'')+(str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整"));
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
            var input = $('<input type="text" class="'+options.direction+'textMax" maxlength="2" onkeyup="Voucher.cellkeyUp(event,this,'+options.num+',\''+options.direction+'\',\''+options.field+'\')" class="datagrid-editable-input" style="-webkit-ime-mode:disabled;">').appendTo(container);
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
