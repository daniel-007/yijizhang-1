<div id="voucherLayout" class="easyui-layout" data-options="fit:true">
<div data-options="region:'north'" style="height:50px">
<div style="padding:10px;">
	<#if !currentPeriod??>
	<a id="voucherAdd" href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-edit',plain:true">新增</a>
    </#if>
	<#if !(currentPeriod??&&currentPeriod!=sessionPeriod)>
    <a id="voucherSave" href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm2',iconCls:'icon-save',plain:true">保存</a>
    </#if>
    <a id="voucherReject" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消修改</a>
    <a id="voucherAppend" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">插入行</a>
    <a id="voucherRemoveit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除行</a>
    <a id="voucherSubjectDetail" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">明细</a>
    <a id="voucherSubjectBalance" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">科目余额</a>
    <a id="voucherHelp" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">凭证编制说明</a>
</div>

<div id="mm1" style="width:100px;">
    <div id="voucherMm1Add" data-options="iconCls:'icon-edit'">新增</div>
    <div>从模式凭证新增</div>
</div>

<div id="mm2" style="width:100px;">
    <div id="voucherMm2Save1">保存</div>
    <div id="voucherMm2Save2">保存并新增</div>
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
	<input class="easyui-numberspinner" id="voucherNo" name="voucherNo" style="width:80px;" value="${voucher.voucherNo?default(voucherNo)}" data-options="required:true,min:1">号
	日期：<input type="text" id="voucherTime" name="voucherTime" value="${(voucher.voucherTime?string("yyyy-MM-dd"))?default(voucherTime)}"></input>
	第${currentPeriod?default(sessionPeriod)}期
	附单据<input class="easyui-numberspinner" id="billNum" name="billNum" style="width:80px;" value="${voucher.billNum?default('0')}" data-options="required:true,min:0">张
</div>

<table id="voucherDg"></table>
</form>
</div>

<script type="text/javascript">
	$(function () {
		Voucher.init('${voucher.id?default('')}','${sessionPeriod}','${sessionBook}');
	});
</script>