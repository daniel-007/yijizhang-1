/**
 * 记账凭证的JS脚本文件。
 */
Voucher=function(){
	var editIndex = undefined;//表格编辑index
	var footerdebit = undefined;//初始化总计借方金额
	var footercrebit = undefined;//初始化总计贷方金额
	var debit = undefined;//总计借方金额
	var crebit = undefined;//总计贷方金额
	var balanceFlag = false;//借贷平衡标识符
	var subjectData;//会计科目代码下来框数据
	var period;//会计期间月
	var book;//账套年
	var cellFocusFlag=true;//金额编辑器是否第一次focus标志
	
	return {
		//凭证页面初始化
		init:function(id,period,book) {
			period=period;
			book=book.replace(',','');
			//自定义的金额editor
			$.extend($.fn.datagrid.defaults.editors, {
			    textMax: {
			        init: function(container, options){
			            var input = $('<input type="text" class="'+options.direction+'textMax" maxlength="2" onfocus="Voucher.cellFocus(\''+options.direction+'\')" onkeyup="Voucher.cellkeyUp(event,this,'+options.num+',\''+options.direction+'\',\''+options.field+'\')" class="datagrid-editable-input" style="-webkit-ime-mode:disabled;">').appendTo(container);
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
			//扩展maxlength规则
			$.extend($.fn.validatebox.defaults.rules, {     
                maxLength: {     
                    validator: function(value, param){     
                        return param[0] >= value.length;     
                    },     
                    message: '请输入最大{0}位字符.'    
                }     
            });
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
			//科目余额
			$('#voucherSubjectBalance').on('click', function() {
				var subjectCode;
				if(editIndex>=0){
					var ed = $('#voucherDg').datagrid('getEditor', {index:editIndex,field:'subjectCode'});
					if(Voucher.validateSubjectCode(ed)){
						$.messager.alert('警告', "科目代码'"+$(ed.target).combobox('getValue')+"'不存在!", 'warning');
						return;
					}
					if(Voucher.checkNum($(ed.target).combobox('getText'))){
	                	$(ed.target).combobox('setValue',$(ed.target).combobox('getText'));
	                }else{
	                	$(ed.target).combobox('setValue',$(ed.target).combobox('getValue'));
	                }
					subjectCode = $(ed.target).combobox('getText');
				}else {
					subjectCode =$('#voucherDg').datagrid('getRows')[0]['subjectCode'];
				}
				if(!subjectCode){
					$.messager.alert('警告', "科目代码不能为空!", 'warning');
					return;
				}
				$("#default_win").window({
					title : '<i class="fa fa-info-circle"></i>科目余额',
					width : 1000,
					height : 500,
					modal : true,
					collapsible : false,
					shadow : true,
					href : 'voucher/subjectBalance',
					queryParams:{
						subjectCode:subjectCode,
						voucherId:id
					}
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
			    required:true,
			    onLoadSuccess:function(){
			    	$('#voucherWord').combobox('setValue', '记');
			    }
			});
			//日期
			$('#voucherTime').datebox({
				formatter:Voucher.myformatter,
				parser:Voucher.myparser,
				editable:false
			}).datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d1 = new Date(book, period-1, 1);
                    var d2 = new Date(book, period, 1);
                    return d1<=date && date<d2;
                }
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
						{field:'summary',title:'摘要',width:60,halign:'center',editor:{type:'textbox',options:{multiline:true,validType:'maxLength[80]'}},rowspan:2},
						{field:'subjectCode',title:'科目代码',width:60,halign:'center',
							formatter:function(value,row){
	                            return row.subjectTextName;
	                        },
							editor:{
								type:'combobox',
								options:{
							    valueField:'subjectCode',
							    textField:'subjectTextName',
							    method:'get',
							    url:'/voucher/accountSubjectList',
							    onBeforeLoad:function(){
							    	if(subjectData){
							    		$(this).combobox('loadData',subjectData); 
							    		return false;
							    	}
							    },
							    onLoadSuccess:function(){
							    	if(!subjectData){
							    		subjectData=$(this).combobox('getData');
							    	}
							    }
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
					//$('#voucherLayout').find('.datagrid-row').css('height', '50px');
					//$('#voucherDg').datagrid('fixRowHeight');
				}
			});
        },
        //验证科目代码不合法
        validateSubjectCode:function(ed){
        	return JSON.stringify(subjectData).indexOf('"subjectCode":'+$(ed.target).combobox('getValue')+',')<0;
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
			var row = $('#voucherDg').datagrid('getFooterRows')[0];
			footerdebit = Voucher.cellSetValue(row['debit']);
			footercrebit = Voucher.cellSetValue(row['credit']);
			debit = Voucher.cellSetValue(row['debit']);
			crebit = Voucher.cellSetValue(row['credit']);
			Voucher.changeRowMoney(debit,"d");
			Voucher.changeRowMoney(crebit,"cr");
			Voucher.footerDX();
        },
        //结束编辑
		endEditing:function(){
		    if (editIndex == undefined){return true}
		    if ($('#voucherDg').datagrid('validateRow', editIndex)){
		    	var summary = $($('#voucherDg').datagrid('getEditor', {index:editIndex,field:'summary'}).target).textbox('getText');
		    	var ed = $('#voucherDg').datagrid('getEditor', {index:editIndex,field:'subjectCode'});
		    	if((summary||$('#voucherDg').datagrid('getRows')[editIndex]['newdebit']||$('#voucherDg').datagrid('getRows')[editIndex]['newcrebit'])&&!$(ed.target).combobox('getValue')) {
		    		$.messager.alert('警告', "科目代码不能为空!", 'warning' ,function(){$('#voucherDg').datagrid('selectRow', editIndex);});
					return false;
		    	}
		    	if(!$('#voucherDg').datagrid('getRows')[editIndex]['newdebit']&&!$('#voucherDg').datagrid('getRows')[editIndex]['newcrebit']&&$(ed.target).combobox('getValue')) {
		    		$.messager.alert('警告', "凭证分录金额不能为零!", 'warning' ,function(){$('#voucherDg').datagrid('selectRow', editIndex);});
					return false;
		    	}
                if($(ed.target).combobox('getValue')&&Voucher.validateSubjectCode(ed)){
					$.messager.alert('警告', "科目代码'"+$(ed.target).combobox('getValue')+"'不存在!", 'warning' ,function(){$('#voucherDg').datagrid('selectRow', editIndex);});
					return false;
				}
                if(Voucher.checkNum($(ed.target).combobox('getText'))){
                	$(ed.target).combobox('setValue',$(ed.target).combobox('getText'));
                }else{
                	$(ed.target).combobox('setValue',$(ed.target).combobox('getValue'));
                }
                var subjectCode = $(ed.target).combobox('getText');
                $('#voucherDg').datagrid('getRows')[editIndex]['subjectTextName'] = subjectCode;
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
		        	if(index>0&&!$('#voucherDg').datagrid('getRows')[index-1]['subjectCode']){
		        		return;
		        	}
		            $('#voucherDg').datagrid('selectRow', index)
		                    .datagrid('beginEdit', index);
		            cellFocusFlag=true;
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
            Voucher.changeRowMoney(footerdebit,"d");
            Voucher.changeRowMoney(footercrebit,"cr");
            Voucher.footerDX();
        },
        //保存
		save:function(isAdd){
			if (Voucher.endEditing()){
				if(!$('#fm').form('validate')){// 表单验证
					return;
				}
				// 凭证分录数据
				var params=Voucher.getChanges();
				if(!params){
					$.messager.alert('警告', "无凭证分录!", 'warning');
					return;
				}
				console.log(params);
				if(!balanceFlag){// 借贷平衡
					$.messager.alert('警告', "借贷不平衡!", 'warning');
					return;
				}
				// 提交保存
	            $.ajax({
	                url: "voucher/save",
	                type:'post',
	                data:$("#fm").serialize()+params,
	                success: function(data){
	                    if(data.result){
	                        if(!$("#id").val()){
	                        	$.messager.alert('警告', "已生成了一张记账凭证，凭证字号为："+data.result, 'warning');
	                        }
	                        if(isAdd){//新增
	                        	Voucher.add();
	                        }
	                    }else{
	                        $.messager.alert('警告', "操作失败，请联系管理员!", 'warning');
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
					params+='&'+JSON.stringify(rows[i]);
				}
			}
			params=params.replace(/{/g,'').replace(/}/g,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
			params=params.replace(/,/g,'&').replace(/undefined/g,'');
			return params;
		},
		//时间格式化
		myformatter:function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        },
        //时间格式化
        myparser:function(s,period,book){
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
		getParamValue:function(pval,value){
        	if(value&&Voucher.checkNum(value)){
        		return value;
        	}else if(pval){
        		return 0;
        	}else{
        		return '';
        	}
		},
		//单行ROW金额合计
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
			if(pval){
				row['newdebit']='';
				row['newcrebit']='';
				row['new'+direction+'ebit']=pval;
			}
			return pval;
		},
		//单行Editor金额合计
		getEditorMoney:function(direction){
			var editors = $('#voucherDg').datagrid('getEditors', editIndex);
			var pval='';
			var j = 2;
        	if(direction=='cr'){
        		j = 13;
        	}
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			pval+=editors[j++].target.val();
			if(pval&&Voucher.startWith(pval,'0')){
				pval=pval.substr(1,pval.length);
			}
			if(editors[j+1].target.val()||editors[j+2].target.val()){
				if(!pval)pval+='0';
				var row=$('#voucherDg').datagrid('getRows')[editIndex];
				var v1 = editors[j].target.val()?editors[j++].target.val():'0';
				if(v1&&v1.length==2){
					pval+=v1.replace(row[direction+'Angle'],'');
					$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:direction+'Cent'}).target).focus();
				}else{
					pval+=v1;
				}
				var v2 = editors[j].target.val()?editors[j++].target.val():'0';
				if(v2&&v2.length==2){
					pval+=v2.replace(row[direction+'Cent'],'');
				}else{
					pval+=v2;
				}
			}
			return pval;
		},
		cellFocus:function(direction){
			console.log("sds");
			if(cellFocusFlag){
				cellFocusFlag=false;
				$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:(direction+'Yuan')}).target).focus();
				var rows=$('#voucherDg').datagrid('getRows');
				if(!rows[editIndex]['new'+direction+'ebit']){
					$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:(direction+'Yuan')}).target).val('0');
					$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:(direction+'Angle')}).target).val('0');
					$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:(direction+'Cent')}).target).val('0');
				}
			}
		},
        /*金额editor事件*/
        cellkeyUp:function(event,tthis,num,direction,field){
        	var rows=$('#voucherDg').datagrid('getRows');
        	//189：按键'-'负号，金额为负
        	if(Voucher.endWith(tthis.value,'-')||Voucher.startWith(tthis.value,'-')){
        		if(rows[editIndex][direction+'pn']=='-'){
        			rows[editIndex][direction+'pn']='';
        			$("."+direction+"textMax").css("color","black");
        		}else{
        			rows[editIndex][direction+'pn']='-';
        			$("."+direction+"textMax").css("color","red");
        		}
        	}
        	if(direction=='d'&&rows[editIndex]['newcrebit']&&rows[editIndex]['newcrebit']!=0){
        		tthis.value='';
        		$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:'crYuan'}).target).focus();
        		return;
        	}
        	if(direction=='cr'&&rows[editIndex]['newdebit']&&rows[editIndex]['newdebit']!=0){
        		tthis.value='';
        		$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:'dYuan'}).target).focus();
        		return;
        	}
        	//过滤空或非数字，金额只能录入数字
        	if(Voucher.endWith(tthis.value,'.')||Voucher.startWith(tthis.value,'.')){
        		$($('#voucherDg').datagrid('getEditor', {index:editIndex,field:direction+'Angle'}).target).focus();
        		if(tthis.value&&tthis.value.length>1){
        			tthis.value=tthis.value.charAt(Voucher.startWith(tthis.value,'.')?1:0);
        		}else {
        			tthis.value=rows[editIndex][field]?rows[editIndex][field]:'';
        		}
        		return;
        	}
        	if(!tthis.value){
        		return;
        	}if(tthis.value&&!Voucher.checkNum(tthis.value)){
    			tthis.value=rows[editIndex][field]?rows[editIndex][field]:'';
        	}
        	//重新组合金额
        	var rowmoney=Voucher.getEditorMoney(direction);
        	console.log(rowmoney);
        	Voucher.changeEditorMoney(rowmoney,direction);
        	//合计
			var money=0;
			for(i=0;i<rows.length;++i){
				money=parseInt(money)+Voucher.getMoney(direction,rows[i])*100;
			}
			if(direction=='d'){
				debit=money;
				Voucher.changeRowMoney(debit,direction);
				Voucher.footerDX();
    		}else if(direction=='cr'){
    			crebit=money;
    			Voucher.changeRowMoney(crebit,direction);
    			Voucher.footerDX();
    		}else{
    			console.log("editor direction error");
    		}
        	console.log(debit+":"+crebit);
        },
        //检查value为空或不是数字时置为0
        cellSetValue:function(value){
        	if(value&&Voucher.checkNum(value)){
        		return value;
        	}else {
        		return 0;
        	}
        },
        //合计中文
        footerDX:function(){
        	var row = $('#voucherDg').datagrid('getFooterRows')[0];
        	row['summary']='合计：';
        	if(debit==crebit&&debit&&crebit){//借贷平衡
        		balanceFlag=true;
        		row['summary']+=Voucher.DX(debit/100);
        	}
        	$('#voucherDg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
        },
        //修改表格Editor总计金额
        changeEditorMoney:function(value,direction){
        	if(!value||value==0||value=='0'){
        		value='';
        	}
        	var rows=$('#voucherDg').datagrid('getRows');
        	var editors = $('#voucherDg').datagrid('getEditors', editIndex);
        	var valueStr = value<0?(value+'').substr(1):value+'';
        	var length = valueStr.length;
        	var i = 0;
        	if(length>11){
        		i=length-11;
        	}
        	var j = 2;
        	if(direction=='cr'){
        		j = 13;
        	}
        	editors[j].target.val(length>=11?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'HundredMillion']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=10?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'TenMillions']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=9?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'Millions']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=8?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'HundredThousand']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=7?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'TenThousand']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=6?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'Thousand']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=5?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'Hundred']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=4?valueStr.charAt(i++):'');
        	rows[editIndex][direction+'Ten']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=3?valueStr.charAt(i++):'0');
        	rows[editIndex][direction+'Yuan']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=2?valueStr.charAt(i++):'0');
        	rows[editIndex][direction+'Angle']=editors[j].target.val();
        	j++;
        	editors[j].target.val(length>=1?valueStr.charAt(i++):'0');
        	rows[editIndex][direction+'Cent']=editors[j].target.val();
        },
        //修改表格footer总计金额
        changeRowMoney:function(value,direction){
        	if(!value||value==0||value=='0'){
        		value='';
        	}
        	var changerow = $('#voucherDg').datagrid('getFooterRows')[0];
        	var valueStr = value<0?(value+'').substr(1):value+'';
        	var length = valueStr.length;
        	var i = 0;
        	if(length>11){
        		i=length-11;
        	}
        	changerow[direction+'HundredMillion']=length>=11?valueStr.charAt(i++):'';
        	changerow[direction+'TenMillions']=length>=10?valueStr.charAt(i++):'';
        	changerow[direction+'Millions']=length>=9?valueStr.charAt(i++):'';
        	changerow[direction+'HundredThousand']=length>=8?valueStr.charAt(i++):'';
        	changerow[direction+'TenThousand']=length>=7?valueStr.charAt(i++):'';
        	changerow[direction+'Thousand']=length>=6?valueStr.charAt(i++):'';
        	changerow[direction+'Hundred']=length>=5?valueStr.charAt(i++):'';
        	changerow[direction+'Ten']=length>=4?valueStr.charAt(i++):'';
        	changerow[direction+'Yuan']=length>=3?valueStr.charAt(i++):'';
        	changerow[direction+'Angle']=length>=2?valueStr.charAt(i++):'';
        	changerow[direction+'Cent']=length>=1?valueStr.charAt(i++):'';
        	if(value<0){//金额为负时，变红色
        		changerow[direction+'HundredMillion']='<span style="color:red;">'+changerow[direction+'HundredMillion']+'</span>';
        		changerow[direction+'TenMillions']='<span style="color:red;">'+changerow[direction+'TenMillions']+'</span>';
        		changerow[direction+'Millions']='<span style="color:red;">'+changerow[direction+'Millions']+'</span>';
        		changerow[direction+'HundredThousand']='<span style="color:red;">'+changerow[direction+'HundredThousand']+'</span>';
        		changerow[direction+'TenThousand']='<span style="color:red;">'+changerow[direction+'TenThousand']+'</span>';
        		changerow[direction+'Thousand']='<span style="color:red;">'+changerow[direction+'Thousand']+'</span>';
        		changerow[direction+'Hundred']='<span style="color:red;">'+changerow[direction+'Hundred']+'</span>';
        		changerow[direction+'Ten']='<span style="color:red;">'+changerow[direction+'Ten']+'</span>';
        		changerow[direction+'Yuan']='<span style="color:red;">'+changerow[direction+'Yuan']+'</span>';
        		changerow[direction+'Angle']='<span style="color:red;">'+changerow[direction+'Angle']+'</span>';
        		changerow[direction+'Cent']='<span style="color:red;">'+changerow[direction+'Cent']+'</span>';
        	}
        },
        // 阿拉伯数字转中文大写
        DX:function(n){
        	if (!n||!Voucher.isRealNum(n))
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
        },
        //是否为数字
        checkNum:function(value){
        	return /^[0-9]*$/.test(value);
        },
        //是否为实数
        isRealNum:function(value){
        	return /^(\-)?\d+($|\.\d+$)/.test(value);
        },
        endWith:function(s,str){
        	if(str==null||str==""||!s||s.length==0||str.length>s.length)
        	  return false;
        	if(s.substring(s.length-str.length)==str)
        	  return true;
        	else
        	  return false;
        	return true;
    	},
    	startWith:function(s,str){
    		if(str==null||str==""||s.length==0||str.length>s.length)
    		  return false;
    		if(s.substr(0,str.length)==str)
    		  return true;
    		else
    		  return false;
    		return true;
		}
	};
}();
