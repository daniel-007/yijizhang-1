/**
 * 记账凭证的JS脚本文件。
 */
$(function () {
	
});

Voucher=function(){
	var editIndex = undefined;
	
	return {
		init : function() {
			$('#dg').datagrid({
				width:900,
				height:300,
				singleSelect:true,
				//toolbar: '#tb',
				onClickCell:Voucher.onClickCell,
				url:'voucher/voucher.json',
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
				var params = rows[0];
				for(i=1;i<rows.length;i++){
					params = Voucher.extend(params, rows[i]);
					console.log(rows[i]);
				}
				console.log(params);
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
		                    alert(data.message)
		                } else {
		                	alert("error");
		                }
		            }
		        });
		    }
		},
		extend:function(destination, source) { 
		    for (var property in source) 
	    		destination[property] = destination[property]+','+source[property]; 
		    return destination; 
		},
        setTextName : function(){
            var texts = $(':text', document.fm);
            for (i=1;i<texts.length;) {
                texts.get(i).name="name";
                i++;
                texts.get(i).name="unit";
                i++;
                texts.get(i).name="amount";
                i++;
                texts.get(i).name="unit_price";
                i++;
                texts.get(i).name="money";
                i++;                    
                texts.get(i).name="uses";
                i++;
            }
        }
	};
}();