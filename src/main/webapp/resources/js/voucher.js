/**
 * 记账凭证的JS脚本文件。
 */
Voucher=function(){
	var editIndex = undefined;//表格编辑index
	var oldValue = undefined;//金额单元格的旧的值
	var row = undefined;//footer的第一行数据
	var debit = undefined;//总计借方金额
	var crebit = undefined;//总计贷方金额
	
	return {
		//凭证页面初始化
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
				        for(i=0;i<len;++i){
				        	$('#dg').datagrid('appendRow',{});
				        }
					}
					Voucher.mergeFooterCells();
					Voucher.initFooterCells();
					editIndex = undefined;
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
			debit = row['debit'];
			crebit = row['crebit'];
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
				// 验证
				if(!$('#fm').form('validate')){
					return;
				}
				
				// 表格数据
				var rows = $('#dg').datagrid('getChanges');
				var params="&";
				for(i=0;i<rows.length;++i){
					if(rows[i].subjectCode){
						rows[i].subjectCode=rows[i].subjectCode.split(" ")[0];
						params = Voucher.jsonToString(params, rows[i]);
						//借方金额
						params += "&debit="+rows[i].dHundredMillion+rows[i].dTenMillions+rows[i].dMillions
						+rows[i].dHundredThousand+rows[i].dTenThousand+rows[i].dThousand
						+rows[i].dHundred+rows[i].dTen+rows[i].dYuan
						+rows[i].dAngle+rows[i].dCent;
						//贷方金额
						params += "&crebit="+rows[i].crHundredMillion+rows[i].crTenMillions+rows[i].crMillions
						+rows[i].crHundredThousand+rows[i].crTenThousand+rows[i].crThousand
						+rows[i].crHundred+rows[i].crTen+rows[i].crYuan
						+rows[i].crAngle+rows[i].crCent;
					}
				}
				if(params=="&"){
					$.messager.alert("提示信息", "无凭证分录!");
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
		//组织表格输入的提交数据
		jsonToString:function(destination, source) {
			return (destination+'&'+JSON.stringify(source)).replace(/{/,'').replace(/}/,'').replace(/","/g,'&').replace(/"/g,'').replace(/:/g,'=');
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
        cellkeyDown:function(tthis){
        	oldValue=Voucher.cellSetValue(tthis.value);
        },
        //金额editor事件
        cellkeyUp:function(tthis,num,direction){
        	var newValue=Voucher.cellSetValue(tthis.value);
        	var value=newValue-oldValue;
        	console.log(debit+":"+crebit+":"+oldValue);
        	if(direction=='d'&&(value!=0)){
        		debit=parseInt(Voucher.cellSetValue(debit))+value*num;
        		Voucher.footerDX();
        		Voucher.changeFooter(debit,direction);
        	}else if(direction=='cr'&&(value!=0)){
        		crebit=parseInt(Voucher.cellSetValue(crebit))+value*num;
        		Voucher.footerDX();
        		Voucher.changeFooter(crebit,direction);
        	}else{
        		console.log("no change");
        	}
        },
        noNumbers:function(e,num,direction){
        	var keynum;
        	if(window.event) // IE
    		{
        	  keynum = e.keyCode;
    		}
        	else if(e.which) // Netscape/Firefox/Opera
        	{
        	  keynum = e.which;
        	}
        	newValue=Voucher.cellSetValue(keynum);
        	oldValue=Voucher.cellSetValue(oldValue);
        	var value=newValue-oldValue;
        	console.log(debit+":"+crebit+":"+oldValue);
        	if(direction=='d'&&(value!=0)){
        		debit=parseInt(Voucher.cellSetValue(debit))+value*num;
        		Voucher.footerDX();
        		Voucher.changeFooter(debit,direction);
        	}else if(direction=='cr'&&(value!=0)){
        		crebit=parseInt(Voucher.cellSetValue(crebit))+value*num;
        		Voucher.footerDX();
        		Voucher.changeFooter(crebit,direction);
        	}else{
        		console.log("no change");
        	}
        	oldValue=newValue;
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
        	if(debit==crebit){
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
        	row[direction+'Yuan']=length>=3?valueStr.charAt(i++):'';
        	row[direction+'Angle']=length>=2?valueStr.charAt(i++):'';
        	row[direction+'Cent']=length>=1?valueStr.charAt(i++):'';
        	$('#dg').datagrid('reloadFooter');
        	Voucher.mergeFooterCells();
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
        }
	};
}();

//扩展的金额editor
$.extend($.fn.datagrid.defaults.editors, {
    textMax: {
        init: function(container, options){
            //var input = $('<input type="text" maxlength="1" onkeyup="Voucher.cellkeyUp(this,'+options.num+',\''+options.direction+'\')" onkeydown="Voucher.cellkeyDown(this)" class="datagrid-editable-input">').appendTo(container);
            var input = $('<input type="text" maxlength="1" onkeydown="Voucher.noNumbers(this,'+options.num+',\''+options.direction+'\')" class="datagrid-editable-input">').appendTo(container);
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