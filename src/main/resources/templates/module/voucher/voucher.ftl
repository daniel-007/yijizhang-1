<h2>记账凭证</h2>
<div style="margin:20px 0;"></div>
<table id="dg">
    <thead>
        <tr>
            <th data-options="field:'productid',width:200,editor:'textbox'" rowspan="2">摘要</th>
            <th data-options="field:'productid',width:200,editor:'textbox'" rowspan="2">科目代码</th>
            <th colspan="11">借方金额</th>
            <th colspan="11">贷方金额</th>
        </tr>
        <tr>
            <th data-options="field:'listprice',width:18,align:'center',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">亿</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">千</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">百</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">十</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">万</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">千</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">百</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">十</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">元</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">角</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">分</th>
            <th data-options="field:'listprice1',width:18,align:'center',editor:'textbox'">亿</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">千</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">百</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">十</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">万</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">千</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">百</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">十</th>
            <th data-options="field:'unitcost',width:18,align:'center',editor:'textbox'">元</th>
            <th data-options="field:'attr1',width:18,align:'center',editor:'textbox'">角</th>
            <th data-options="field:'status',width:18,align:'center',editor:'textbox'">分</th>
        </tr>
    </thead>
</table>

<div id="tb" style="height:auto">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
</div>

<script type="text/javascript">
	$(function () {
		$('#dg').datagrid({
			width:900,
			height:250,
			singleSelect:true,
			toolbar: '#tb',
			onClickCell:onClickCell,
			url:'voucher/voucher.json',
			method:'get',
			onLoadSuccess:function(data){
				if(data&&data.total>=4){
					console.log('not null');
				} else {
					var len = data&&data.total>0?4-data.total:4;
					for (i=0;i<len;i++){
						appendFirst();
					}
				}
			}
		});
	});
</script>