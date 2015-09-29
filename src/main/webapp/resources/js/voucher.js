/**
 * 记账凭证的JS脚本文件。
 */
$(document).ready(function () {
	
});

Voucher=function(){
	var editIndex = undefined;
	var oldValue = undefined;
	
	return {
		init:function(id) {
			$('#dg').datagrid({
				width:900,
				height:220,
				singleSelect:true,
				//toolbar: '#tb',
				onClickCell:Voucher.onClickCell,
				url:'voucher/voucherDetailList',
				queryParams:{voucherId:id},
				method:'get',
				showFooter:true,
				onLoadSuccess:function(data){
					if(data&&data.total>=5){
						console.log('not null');
					} else {
						var len = data&&data.total>0?5-data.total:5;
				        for(i=0;i<len;i++){
				        	$('#dg').datagrid('appendRow',{});
				        }
					}
					
					$('#dg').datagrid('mergeCells', {
						index: 0,
						field: 'summary',
						colspan: 2,
						type: 'footer'
					});
					$('#dg').datagrid('mergeCells', {
						index: 0,
						field: 'bHundredMillion',
						colspan: 11,
						type: 'footer'
					});
					$('#dg').datagrid('mergeCells', {
						index: 0,
						field: 'lHundredMillion',
						colspan: 11,
						type: 'footer'
					});
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
		append:function(){
		    if (Voucher.endEditing()){
		        $('#dg').datagrid('appendRow',{});
		        editIndex = $('#dg').datagrid('getRows').length-1;
		        $('#dg').datagrid('selectRow', editIndex)
		                .datagrid('beginEdit', editIndex);
		    }
		},
		removeit:function(){
		    if (editIndex == undefined){return}
		    $('#dg').datagrid('cancelEdit', editIndex)
		            .datagrid('deleteRow', editIndex);
		    editIndex = undefined;
		},
		reject:function(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        },
		getChanges:function(){
		    var rows = $('#dg').datagrid('getChanges');
		    alert(rows.length+' rows are changed!');
		},
		save:function(isAdd){
			if (Voucher.endEditing()){
				// 验证
				if(!$('#fm').form('validate')){
					return;
				}
				
				// 表格数据
				var rows = $('#dg').datagrid('getChanges');
				var params="&";
				for(i=0;i<rows.length;i++){
					if(rows[i].subjectCode){
						params = Voucher.extend(params, rows[i]);
					}
				}
				if(params=="&"){
					$.messager.alert("提示信息", "无凭证分录!");
				}
				return;
				// 提交保存
	            $.ajax({
	                url: "voucher/save",
	                type:'post',
	                data:$("#fm").serialize()+params,
	                success: function(data){
	                    if(data.result){
	                        $.messager.alert("提示信息", "记账凭证保存成功!");
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
		extend:function(destination, source) {
//			if(destination){
//				for (var property in source) 
//					destination[property] = destination[property]+','+source[property]; 
//			} else {
//				destination=$.extend({},source);
//			}
//		    return destination; 
			return (destination+'&'+JSON.stringify(source)).replace(/{/,'').replace(/}/,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
		},
		myformatter:function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        },
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
        add:function(){
        	var tab = $TC.tabs('getSelected');  // get selected panel
        	tab.panel('refresh', 'voucher/main'+"?&time="+new Date().getTime());
        },
        cellkeyDown:function(newValue,oldValue,p){
        	alert(this.value);
        },
        cellkeyDown:function(tthis){
        	oldValue=tthis.value;
        },
        cellkeyUp:function(tthis){
        	var rows = $('#dg').datagrid('getFooterRows');
        	rows[0]['bHundredMillion']=rows[0]['bHundredMillion']+(tthis.value-oldValue)*;
        }
	};
}();

$.extend($.fn.datagrid.defaults.editors, {
    textMax: {
        init: function(container, options){
            var input = $('<input type="text" maxlength="1" onkeydown="Voucher.cellkeyDown(this,'+options. +')" onkeydown="Voucher.cellkeyUp(this)" class="datagrid-editable-input">').appendTo(container);
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