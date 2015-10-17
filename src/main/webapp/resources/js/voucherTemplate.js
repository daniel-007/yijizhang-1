/**
 * 记账模式凭证的JS脚本文件。
 */
VoucherTemplate=function(){
	var templateEditIndex = undefined;
	var templateSubjectData;//会计科目代码下来框数据
	
	return {
		//模式凭证页面初始化
		init:function() {
			//关闭
			$('#templateClose').click(function(){
				$('#default_win').window('close');
			});
			//新增
			$('#templateAdd').click(function(){
				VoucherTemplate.templateAdd();
			});
			//修改
			$('#templateEdit').click(function(){
				var row = $('#voucherTemplateDg').datagrid('getSelected');
				if (!row){
					$.messager.alert('警告', "请选择一个模式凭证!", 'warning');
					return;
				}
				VoucherTemplate.templateAdd(row.id,row.name);
			});
			//删除
			$('#templateRemove').click(function(){
				var row = $('#voucherTemplateDg').datagrid('getSelected');
				if (!row){
					$.messager.alert('警告', "请选择一个模式凭证!", 'warning');
					return;
				}
				VoucherTemplate.templateRemove(row.id);
			});
			//类别编辑
			$('#templateTypeEdit').click(function(){
				CompanyCommonValue.openWindow($("#voucherTem_win"),'模式凭证类别','2');
			});
			//显示模式
			$('#templateShow').click(function(){
				
			});
			
			$('#voucherTemplateDg').datagrid({
				singleSelect:true,
				fitColumns: true,
				fit:true,
				url:'voucher/voucherTemplateList',
				method:'get',
				columns:[[
							{field:'name',title:'名称',width:60,halign:'center'},
							{field:'voucherWord',title:'凭证字',width:60,halign:'center'},
							{field:'typeName',title:'类别',width:60,halign:'center'}
						]]
			});
        },
        //模式凭证编辑页面初始化
		addInit:function(id,typeName,voucherWord) {
			$('#templateAddAdd').click(function(){
				VoucherTemplate.templateAdd();
			});
			$('#templateSave').click(function(){
				VoucherTemplate.save();
			});
			$('#templateReject').click(function(){
				$('#voucherTem_win').panel('refresh');
			});
			//插入行
			$('#templateAppend').click(function() {
				VoucherTemplate.append();
			});
			//删除行
			$('#templateRemoveit').click(function() {
				VoucherTemplate.removeit();
			});
			
			$('#voucherTemplateDetailDg').datagrid({
				singleSelect:true,
				fitColumns: true,
				//fit:true,
				toolbar: '#voucherTemplateMenu,#voucherTemplateTb',
				onClickCell:VoucherTemplate.onClickCell,
				onDblClickRow:VoucherTemplate.onDblClickRow,
				url:'voucher/voucherTemplateDetailList',
				queryParams:{voucherTemplateId:id},
				method:'get',
				showFooter:true,
				columns:[[
						{field:'summary',title:'摘要',width:60,halign:'center',
							editor:{
								type:'textbox',
								options:{
									multiline:true,
									validType:'voucherMaxLength[80]'
								}}},
						{field:'subjectCode',title:'会计科目',width:40,halign:'center',
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
								    	if(templateSubjectData){
								    		$(this).combobox('loadData',templateSubjectData); 
								    		return false;
								    	}
								    },
								    onLoadSuccess:function(){
								    	if(!templateSubjectData){
								    		templateSubjectData=$(this).combobox('getData');
								    	}
								    }
						        }}},
						{field:'debit',title:'借方金额',width:20,editor:{
							type:'numberbox',
							options:{
								precision:2,
								min:-999999999.99,
								max:999999999.99
							}}},
						{field:'credit',title:'贷方金额',width:20,editor:{
							type:'numberbox',
							options:{
								precision:2,
								min:-999999999.99,
								max:999999999.99
							}}}
				      ]],
				onLoadSuccess:function(data){
					if(!(data&&data.total>=1)){
			        	$('#voucherTemplateDetailDg').datagrid('appendRow',{});
					}
					templateEditIndex = undefined;
					$('#voucherTemplateDetailDg').datagrid('fitColumns');
				}
			});
			
			//类别
			$('#typeName').combobox({
			    url:'companyCommonValue/voucherTemplateTypeList',
			    method:'get',
			    valueField:'showValue',
			    textField:'showValue',
			    editable:false,
			    required:true,
			    onLoadSuccess:function(){
			    	$('#typeName').combobox('setValue', typeName);
			    }
			});
			//模式类别
			$('#voucherTemplateWord').combobox({
			    url:'companyCommonValue/voucherWordList',
			    method:'get',
			    valueField:'showValue',
			    textField:'showValue',
			    editable:false,
			    required:true,
			    onLoadSuccess:function(){
			    	$('#voucherTemplateWord').combobox('setValue', voucherWord);
			    }
			});
		},
		templateAdd:function(id,name){
			$("#voucherTem_win").window({
				title : '<i class="fa fa-info-circle"></i>'+(name?'模式凭证 - '+name:'新增凭证模式'),
				width : 768,
				height : 483,
				modal : true,
				collapsible : false,
				shadow : true,
				href : 'voucher/voucherTemplateAdd',
				queryParams:{voucherTemplateId:id}
			});
		},
		templateRemove:function(id){
			$('#templateRemove').linkbutton('mydisable');
			$.messager.confirm('确认', '确定删除选中模式凭证?', function(r){
				if (r){
					$.ajax({
		                url: "voucher/deleteTemplate",
		                type:'get',
		                data:{id:id},
		                success: function(data){
		                	$('#templateRemove').linkbutton('myenable');
		                	if(data.result){
		                    	$.messager.alert('提示', "删除成功!", 'info',function(){
		                    		$('#voucherTemplateDg').datagrid('reload');
		                    	});
		                    }else{
		                        $.messager.alert('警告', "操作失败，请联系管理员!", 'warning');
		                    }
		                },
		                error:function(){
		                	$('#templateRemove').linkbutton('myenable');
		            	}
		            });
				}else {
					$('#templateRemove').linkbutton('myenable');
				}
			});
		},
		endEditing:function(){
            if (templateEditIndex == undefined){return true}
            if ($('#voucherTemplateDetailDg').datagrid('validateRow', templateEditIndex)){
		    	var ed = $('#voucherTemplateDetailDg').datagrid('getEditor', {index:templateEditIndex,field:'subjectCode'});
		    	if($(ed.target).combobox('getValue')&&Voucher.validateSubjectCode(ed,templateSubjectData)){
		    		$.messager.alert('警告', "科目代码'"+$(ed.target).combobox('getValue')+"'不存在!", 'warning' ,function(){$('#voucherTemplateDetailDg').datagrid('selectRow', templateEditIndex);});
		    		return false;
		    	}
		    	if(Voucher.checkNum($(ed.target).combobox('getText'))){
		    		$(ed.target).combobox('setValue',$(ed.target).combobox('getText'));
		    	}else{
		    		$(ed.target).combobox('setValue',$(ed.target).combobox('getValue'));
		    	}
		    	var subjectCode = $(ed.target).combobox('getText');
		    	$('#voucherTemplateDetailDg').datagrid('getRows')[templateEditIndex]['subjectTextName'] = subjectCode;
                $('#voucherTemplateDetailDg').datagrid('endEdit', templateEditIndex);
                templateEditIndex = undefined;
                return true;
            } else {
                return false;
            }
        },
		onClickCell:function(index, field){
            if (templateEditIndex != index){
                if (VoucherTemplate.endEditing()){
                    $('#voucherTemplateDetailDg').datagrid('selectRow', index).datagrid('beginEdit', index);
                    var ed = $('#voucherTemplateDetailDg').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    templateEditIndex = index;
                } else {
                    $('#voucherTemplateDetailDg').datagrid('selectRow', templateEditIndex);
                }
            }
        },
        append:function(){
            if (VoucherTemplate.endEditing()){
                $('#voucherTemplateDetailDg').datagrid('appendRow',{});
                //templateEditIndex = $('#voucherTemplateDetailDg').datagrid('getRows').length-1;
            }
        },
        removeit:function(){
            if (templateEditIndex == undefined){return}
            $('#voucherTemplateDetailDg').datagrid('cancelEdit', templateEditIndex).datagrid('deleteRow', templateEditIndex);
            templateEditIndex = undefined;
        },
        save:function(){
        	$('#templateSave').linkbutton('mydisable');
			if (!VoucherTemplate.endEditing()){
				$('#templateSave').linkbutton('myenable');
				return;
			}
			if(!$('#voucherTemplateFm').form('validate')){// 表单验证
				$('#templateSave').linkbutton('myenable');
				return;
			}
			// 凭证分录数据
			var params=VoucherTemplate.getChanges();
			if(params=='moneyerror'){
				$('#templateSave').linkbutton('myenable');
				$.messager.alert('警告', "借贷双方不能同时有金额!", 'warning');
        		return;
			}
			var voucherTemplateName = $('#voucherTemplateName').textbox('getValue');
			var voucherTemplateId = $('#voucherTemplateId').val();
			$.ajax({
                url: "voucher/checkTemplateName",
                type:'get',
                data:{name:voucherTemplateName,id:voucherTemplateId},
                success: function(data){
                	if(!data.result){
                		$('#templateSave').linkbutton('myenable');
                		$.messager.alert('警告', "模式凭证名称不能重复!", 'warning');
                		return;
                	}
    				// 提交保存
    	            $.ajax({
    	                url: "voucher/saveTemplate",
    	                type:'post',
    	                data:$("#voucherTemplateFm").serialize()+params,
    	                success: function(data){
    	                	$('#templateSave').linkbutton('myenable');
    	                    if(data.result){
                            	$.messager.alert('提示', "保存成功!", 'info',function(){
                            		$('#voucherTemplateDg').datagrid('reload');
                            	});
    	                    }else{
    	                        $.messager.alert('警告', "操作失败，请联系管理员!", 'warning');
    	                    }
    	                },
    	                error: function(XMLHttpRequest, textStatus, errorThrown) {
    	                	$('#templateSave').linkbutton('myenable');
                        }
    	            });
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                	$('#templateSave').linkbutton('myenable');
                }
			});
        },
		//表格修改数据
		getChanges:function(){
			var params='';
			var rows = $('#voucherTemplateDetailDg').datagrid('getRows');
			for(i=0;i<rows.length;++i){
				if(rows[i].debit&&rows[i].credit&&!(rows[i].debit==0||rows[i].credit==0)){
					return "moneyerror";
				}
				if(!rows[i].debit||(rows[i].debit&&rows[i].debit==0)){
					rows[i].debit='';
				}
				if(!rows[i].credit||(rows[i].credit&&rows[i].credit==0)){
					rows[i].credit='';
				}
				if(!rows[i].summary){
					rows[i].summary='';
				}
				if(!rows[i].subjectCode){
					rows[i].subjectCode='';
				}
				params+='&'+JSON.stringify(rows[i]);
			}
			params=params.replace(/{/g,'').replace(/}/g,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
			params=params.replace(/,/g,'&').replace(/undefined/g,'');
			return params;
		},
		onDblClickRow:function(index,row){
			
		}
	};
}();

/**
 * linkbutton方法扩展
 * @param {Object} jq
 */
$.extend($.fn.linkbutton.methods, {
    /**
     * 激活选项（覆盖重写）
     * @param {Object} jq
     */
    myenable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if ($(this).hasClass('l-btn-disabled')) {
                var itemData = state._eventsStore;
                //恢复超链接
                if (itemData.href) {
                    $(this).attr("href", itemData.href);
                }
                //回复点击事件
                if (itemData.onclicks) {
                    for (var j = 0; j < itemData.onclicks.length; j++) {
                        $(this).bind('click', itemData.onclicks[j]);
                    }
                }
                //设置target为null，清空存储的事件处理程序
                itemData.target = null;
                itemData.onclicks = [];
                $(this).removeClass('l-btn-disabled');
            }
        });
    },
    /**
     * 禁用选项（覆盖重写）
     * @param {Object} jq
     */
    mydisable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if (!state._eventsStore)
                state._eventsStore = {};
            if (!$(this).hasClass('l-btn-disabled')) {
                var eventsStore = {};
                eventsStore.target = this;
                eventsStore.onclicks = [];
                //处理超链接
                var strHref = $(this).attr("href");
                if (strHref) {
                    eventsStore.href = strHref;
                    $(this).attr("href", "javascript:void(0)");
                }
                //处理直接耦合绑定到onclick属性上的事件
                var onclickStr = $(this).attr("onclick");
                if (onclickStr && onclickStr != "") {
                    eventsStore.onclicks[eventsStore.onclicks.length] = new Function(onclickStr);
                    $(this).attr("onclick", "");
                }
                //处理使用jquery绑定的事件
                var eventDatas = $(this).data("events") || $._data(this, 'events');
                if (eventDatas["click"]) {
                    var eventData = eventDatas["click"];
                    for (var i = 0; i < eventData.length; i++) {
                        if (eventData[i].namespace != "menu") {
                            eventsStore.onclicks[eventsStore.onclicks.length] = eventData[i]["handler"];
                            $(this).unbind('click', eventData[i]["handler"]);
                            i--;
                        }
                    }
                }
                state._eventsStore = eventsStore;
                $(this).addClass('l-btn-disabled');
            }
        });
    }
});
