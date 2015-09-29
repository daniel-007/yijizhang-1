<div class="easyui-panel" style="padding:5px;">
	<a href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-edit',plain:true" onclick="javascript:Voucher.add()">新增</a>
    <a href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm2',iconCls:'icon-save',plain:true" onclick="javascript:Voucher.save(1)">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="javascript:Voucher.append()">插入行</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:Voucher.removeit()">删除行</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="javascript:Voucher.reject()">取消修改</a>
</div>

<div id="mm1" style="width:100px;">
    <div onclick="javascript:Voucher.add()">新增</div>
    <div>从模式凭证新增</div>
</div>

<div id="mm2" style="width:100px;">
    <div onclick="javascript:Voucher.save()">保存</div>
    <div onclick="javascript:Voucher.save(1)">保存并新增</div>
    <div>保存为模式凭证</div>
</div>

<h2>记账凭证</h2>

<form id="fm" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="hidden" id="id" name="id" value="${voucher.id?default('')}"/>
<input type="hidden" id="periodId" name="periodId" value="${voucher.periodId?default('')}"/>

<div style="padding:10px;">
	<select id="voucherWord" name="voucherWord" style="width:100px;"></select>字
	<input class="easyui-numberspinner" id="voucherNo" name="voucherNo" style="width:80px;" value="${voucher.voucherNo?default(voucherNo)}" data-options="min:1">号
	日期：<input class="easyui-datebox" id="voucherTime " name="voucherTime" value="${voucher.voucherTime?default(voucherTime)}" data-options="formatter:Voucher.myformatter,parser:Voucher.myparser,editable:false"></input>
	第${voucher.period?default(period)}期
	附单据<input class="easyui-numberspinner" id="billNum" name="billNum" style="width:80px;" value="${voucher.billNum?default('0')}" data-options="min:0">张
</div>

<table id="dg">
    <thead>
        <tr>
            <th data-options="field:'summary',width:200,editor:'textbox'" rowspan="2">摘要</th>
            <th data-options="field:'subjectCode',width:200,editor:{
                            type:'combobox',
                            options:{
                                valueField:'subjectCode',
                                textField:'subjectCode'+'subjectName',
                                method:'get',
                                url:'/voucher/accountSubjectList',
                                onChange:function(newValue,oldValue){}
                            }}" rowspan="2">科目代码</th>
            <th colspan="11">借方金额</th>
            <th colspan="11">贷方金额</th>
        </tr>
        <tr>
            <th data-options="field:'bHundredMillion',width:18,align:'center',editor:{type:'textMax',options:{}}">亿</th>
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

<script type="text/javascript">
	$(function () {
		Voucher.init('${id?default('')}');
	});
</script>