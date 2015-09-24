<div class="easyui-panel" style="padding:5px;">
    <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-save',plain:true">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="Voucher.append()">插入行</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="Voucher.removeit()">删除行</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="Voucher.reject()">取消修改</a>
</>
<h2>记账凭证</h2>
<form id="fm" method="post">
<div style="margin:20px 0;">

</div>
<table id="dg">
    <thead>
        <tr>
            <th data-options="field:'summary',width:200,editor:'textbox'" rowspan="2">摘要</th>
            <th data-options="field:'subjectCode',width:200,editor:'textbox'" rowspan="2">科目代码</th>
            <th colspan="11">借方金额</th>
            <th colspan="11">贷方金额</th>
        </tr>
        <tr>
            <th data-options="field:'bHundredMillion',width:18,align:'center',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">亿</th>
            <th data-options="field:'bTenMillions',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">千</th>
            <th data-options="field:'bMillions',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">百</th>
            <th data-options="field:'bHundredThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">十</th>
            <th data-options="field:'bTenThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">万</th>
            <th data-options="field:'bThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">千</th>
            <th data-options="field:'bHundred',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">百</th>
            <th data-options="field:'bTen',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">十</th>
            <th data-options="field:'bYuan',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">元</th>
            <th data-options="field:'bAngle',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">角</th>
            <th data-options="field:'bCent',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">分</th>
            <th data-options="field:'lHundredMillion',width:18,align:'center',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">亿</th>
            <th data-options="field:'lTenMillions',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">千</th>
            <th data-options="field:'lMillions',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">百</th>
            <th data-options="field:'lHundredThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">十</th>
            <th data-options="field:'lTenThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">万</th>
            <th data-options="field:'lThousand',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">千</th>
            <th data-options="field:'lHundred',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">百</th>
            <th data-options="field:'lTen',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">十</th>
            <th data-options="field:'lYuan',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">元</th>
            <th data-options="field:'lAngle',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">角</th>
            <th data-options="field:'lCent',width:18,align:'center',editor:'textbox',editor:{type:'numberbox',options:{min:0,max:9,precision:0}}">分</th>
        </tr>
    </thead>
</table>

</form>

<div id="tb" style="height:auto">
</div>

<div id="mm" style="width:100px;">
    <div onclick="Voucher.save()">保存</div>
    <div>保存并新增</div>
    <div>保存为模式凭证</div>
</div>

<script type="text/javascript">
	$(function () {
		Voucher.init();
	});
</script>