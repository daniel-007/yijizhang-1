<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table id="profitDg"></table>
	</div>
</div>

<div id="profitMenu" style="padding:2px 5px;display: none;">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <a id="profitSave" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true"><i class="fa fa-save fa-lg"></i>&#8194;保存</a>
            </td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
            <td>
                <a id="profitAppend" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true"><i class="fa fa-indent fa-lg"></i>&#8194;插入行</a>
                <a id="profitRemoveit" href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true"><i class="fa fa-outdent fa-lg"></i>&#8194;删除行</a>
            </td>
            <td>
                <div class="datagrid-btn-separator"></div>
            </td>
        </tr>
    </table>
</div>

<div id="profitDgTd" style="padding:10px;">
	报表计算会计期间：<input id="searchtPeriod" style="width:80px;" value="${searchtPeriod?default(1)}">
</div>

<script type="text/javascript">
    $(function () {
        Profit.init(${searchtPeriod?default(1)});
    });
</script>