<div id="voucherLayout" class="easyui-layout" data-options="fit:true">
	<div id="center" data-options="region:'center'">
		<form id="voucherFm" method="post">
			<input type="hidden" id="id" name="id" value="${voucher.id?default('')}"/>
			<input type="hidden" id="periodId" name="periodId" value="${voucher.periodId?default('')}"/>
			<table id="voucherDg"></table>
		</form>
	</div>
</div>

<div id="voucherMenu" style="padding:2px 5px;">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<a id="voucherAdd" href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm1',iconCls:'icon-edit',plain:true">新增</a>
				<#if !(currentPeriod??&&currentPeriod!=sessionPeriod)>
			    <a id="voucherSave" href="javascript:void(0)" class="easyui-splitbutton" data-options="menu:'#mm2',iconCls:'icon-save',plain:true">保存</a>
			    </#if>
			    <a id="voucherReject" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消修改</a>
			</td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
		    <td>
			    <a id="voucherTempAdd" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">从模式凭证新增</a>
			    <a id="voucherTempSave" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">保存为模式凭证</a>
		    </td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
		    <td>
			    <a id="voucherAppend" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">插入行</a>
			    <a id="voucherRemoveit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除行</a>
		    </td>
			<td>
				<div class="datagrid-btn-separator"></div>
			</td>
		    <td>
			    <a id="voucherSubjectDetail" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">明细</a>
			    <a id="voucherSubjectBalance" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">科目余额</a>
			    <a id="voucherHelp" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">凭证制作说明</a>
			</td>
		</tr>
	</table>    
</div>
<div id="mm1" style="width:100px;">
    <div id="voucherMm1Add" data-options="iconCls:'icon-edit'">新增</div>
    <div id="voucherTempMm1Add">从模式凭证新增</div>
</div>
<div id="mm2" style="width:100px;">
    <div id="voucherMm2Save1">保存</div>
    <div id="voucherMm2Save2">保存并新增</div>
    <div id="voucherTempMm2Save">保存为模式凭证</div>
</div>

<div id="voucherDgTd" style="padding:10px;">
	<select id="voucherWord" name="voucherWord" style="width:100px;"></select>字
	<input class="easyui-numberspinner" id="voucherNo" name="voucherNo" style="width:80px;" value="${voucher.voucherNo?default(voucherNo)}" data-options="required:true,min:1">号
	日期：<input type="text" id="voucherTime" name="voucherTime" value="${(voucher.voucherTime?string("yyyy-MM-dd"))?default(voucherTime)}"></input>
	第${currentPeriod?default(sessionPeriod)}期
	附单据<input class="easyui-numberspinner" id="billNum" name="billNum" style="width:80px;" value="${voucher.billNum?default('0')}" data-options="required:true,min:0">张
</div>

<script type="text/javascript">
	$(function () {
		Voucher.init('${voucher.id?default('')}','${sessionPeriod}','${sessionBook}','${voucher.voucherWord?default('')}');
	});
</script>