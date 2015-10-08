<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'north'" style="height:50px">
<div style="padding:10px;">
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

</div>

<div id="center" data-options="region:'center',title:'记账凭证'">
<form id="fm" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<input type="hidden" id="id" name="id" value="${voucher.id?default('')}"/>
<input type="hidden" id="periodId" name="periodId" value="${voucher.periodId?default('')}"/>

<div style="padding:10px;">
	<select id="voucherWord" name="voucherWord" style="width:100px;"></select>字
	<input class="easyui-numberspinner" id="voucherNo" name="voucherNo" style="width:80px;" value="${voucher.voucherNo?default(voucherNo)}" data-options="min:1">号
	日期：<input class="easyui-datebox" id="voucherTime " name="voucherTime" value="${(voucher.voucherTime?string("yyyy-MM-dd"))?default(voucherTime)}" data-options="formatter:Voucher.myformatter,parser:Voucher.myparser,editable:false"></input>
	第${voucher.period?default(period)}期
	附单据<input class="easyui-numberspinner" id="billNum" name="billNum" style="width:80px;" value="${voucher.billNum?default('0')}" data-options="min:0">张
</div>

<table id="dg"></table>
</form>
</div>

<script type="text/javascript">
	$(function () {
		Voucher.init('${voucher.id?default('')}');
	});
</script>