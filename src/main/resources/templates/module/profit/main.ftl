<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table id="profitDg"></table>
	</div>
</div>

<div id="profitDgTd" style="padding:10px;">
	报表计算会计期间：<input id="searchtPeriod" style="width:80px;" value="${searchtPeriod?default(1)}">
</div>

<script type="text/javascript">
    $(function () {
        Profit.init(${searchtPeriod?default(1)});
    });
</script>