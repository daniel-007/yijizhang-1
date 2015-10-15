<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north'" style="height:50px;overflow: hidden;">
        <div style="padding:10px;">
			<a id="templateAddAdd" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">新增</a>
		    <a id="templateSave" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
		    <a id="templateReject" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消修改</a>
		    <a id="templateAppend" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">插入行</a>
		    <a id="templateRemoveit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除行</a>
		</div>
    </div>
	<div data-options="region:'center',border:false">
		<form id="voucherTemplateFm" method="post">
			<input type="hidden" id="voucherTemplateId" name="id" value="${voucherTemplate.id?default('')}"/>
			<table id="voucherTemplateDetailDg"></table>
		</form>
	</div>
</div>

<div id="voucherTemplateTb" style="padding:10px;">
	名称：<input id="voucherTemplateName" class="easyui-textbox" type="text" name="name" value="${voucherTemplate.name?default('')}" data-options="required:true,validType:'voucherMaxLength[20]'"></input>
	类别：<select id="typeName" name="typeName" style="width:100px;"></select>
	凭证字：<select id="voucherTemplateWord" name="voucherWord" style="width:100px;"></select>
	附单据：<input class="easyui-numberspinner" id="billNum" name="billNum" style="width:80px;" value="${voucherTemplate.billNum?default('0')}" data-options="required:true,min:0">
</div>

<div id="voucherTemAdd_win"></div>

<script type="text/javascript">
    $(function(){
        VoucherTemplate.addInit('${voucherTemplate.id?default('')}','${voucherTemplate.typeName?default('')}','${voucherTemplate.voucherWord?default('')}');
    });
</script>