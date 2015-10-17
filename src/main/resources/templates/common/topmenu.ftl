<div class="top-menu">
    <button onClick="App.selectTab(0);"
            class="button button-glow button-primary button-box button-giant button-longshadow-left">
        <i class="fa fa-home"></i><br/>
        <small style="font-size:12px;">首页</small>
    </button>
    <button onClick="App.addVoucherTab('记账','voucher/main',true);"
            class="button button-glow button-primary button-box button-giant button-longshadow-left">
        <i class="fa fa-pencil-square-o"></i><br/>
        <small style="font-size:12px;">记账</small>
    </button>
    <button id="cz_bubble" class="button button-glow button-primary button-box button-giant button-longshadow-right">
        <i class="fa fa-search-plus"></i><br/>
        <small style="font-size:12px;">查账 <i class="fa fa-caret-down"></i></small>
    </button>

    <div id="cz_menu" style="display: none;">
        <ul class="fc_menu_ul">
            <li><a href="javascript:App.addTab('凭证查询','search/voucher/page/main',true)">凭证查询</a></li>
            <li><a href="#">总账</a></li>
            <li><a href="javascript:App.addTab('明细账','search/detail/main',true)">明细账</a></li>
            <li><a href="#">多栏账</a></li>
            <li><a href="#">科目余额表</a></li>
            <li><a href="#">凭证汇总表</a></li>
            <li><a href="#">试算平衡表</a></li>
        </ul>
    </div>

    <button onClick="App.addTab('结账','account/cashier/main',true);"
            class="button button-glow button-primary button-box button-giant button-longshadow-left">
        <i class="fa fa-calendar"></i><br/>
        <small style="font-size:12px;">结账</small>
    </button>

    <button id="bb_bubble" class="button button-glow button-primary button-box button-giant button-longshadow-right">
        <i class="fa fa-line-chart"></i><br/>
        <small style="font-size:12px;">报表 <i class="fa fa-caret-down"></i></small>
    </button>
    <div id="bb_menu" style="display: none;">
        <ul class="fc_menu_ul">
            <li><a id="zcfz" href="#">资产负债表</a></li>
            <li><a href="#">利润表</a></li>
            <li><a href="#">现金流量表</a></li>
        </ul>
    </div>

	<#--<button onClick="App.addTab('设置','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-left">-->
		<#--<i class="fa fa-cog"></i><br/><small style="font-size:12px;">设置</small>-->
	<#--</button>-->

	<button onClick="App.addTab('日志','action/log/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-right">
		<i class="fa fa-file-text-o"></i><br/><small style="font-size:12px;">日志</small>
	</button>
</div>
<script>
    $(function () {
        $('#cz_bubble').click(function () {
            var d = dialog({
                content: $('#cz_menu').html(),
                quickClose: true
            });
            d.show(document.getElementById('cz_bubble'));
        });

        $('#bb_bubble').click(function () {
            var d = dialog({
                content: $('#bb_menu').html(),
                quickClose: true
            });
            d.show(document.getElementById('bb_bubble'));
        });

    });
</script>
