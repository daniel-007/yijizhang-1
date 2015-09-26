/**
 * 记账凭证的JS脚本文件。
 */
$(document).ready(function () {
	
});

Voucher=function(){
	var editIndex = undefined;
	
	return {
		init:function(id) {
			$('#dg').datagrid({
				width:900,
				height:300,
				singleSelect:true,
				//toolbar: '#tb',
				onClickCell:Voucher.onClickCell,
				url:'voucher/voucherDetailList',
				queryParams:{voucherId:id},
				method:'get',
				onLoadSuccess:function(data){
					if(data&&data.total>=5){
						console.log('not null');
					} else {
						var len = data&&data.total>0?5-data.total:5;
				        for(i=0;i<len;i++){
				        	$('#dg').datagrid('appendRow',{});
				        }
					}
				}
			});
			
			$('#voucherWord').combobox({
			    url:'voucher/voucherWordList',
			    valueField:'showValue',
			    textField:'showValue',
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
		save:function(){
			if (Voucher.endEditing()){
				var rows = $('#dg').datagrid('getChanges');
				var params;
				for(i=0;i<rows.length;i++){
					params = Voucher.extend(params, rows[i]);
				}
		        $('#fm').form('submit', {
		        	url:'voucher/save',
		        	queryParams:params,
		        	onSubmit: function(){
		        		var isValid = $(this).form('validate');
		        		if (isValid){
		        			console.log("on submit valid true");
		        		}
		        		return isValid;
		        	},
		            success: function(data){
		            	var data = eval('(' + data + ')');  // change the JSON string to javascript object
		                if (data.success){
		                    alert(data.message);
		                } else {
		                	alert(data.message);
		                }
		            }
		        });
		    }
		},
		extend:function(destination, source) {
			if(destination){
				for (var property in source) 
					destination[property] = destination[property]+','+source[property]; 
			} else {
				destination=$.extend({},source);
			}
		    return destination; 
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
        }
	};
}();