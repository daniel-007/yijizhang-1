<div class="top-menu">
    <button onClick="App.selectTab(0);" class="button button-glow button-primary button-box button-giant button-longshadow-left">
        <i class="fa fa-home"></i><br/><small style="font-size:12px;">首页</small>
    </button>
	<button onClick="App.addTab('记账','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-left">
		<i class="fa fa-pencil-square-o"></i><br/><small style="font-size:12px;">记账</small>
	</button>
	<button onClick="App.addTab('查账','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-right">
		<i class="fa fa-search-plus"></i><br/><small style="font-size:12px;">查账</small>
	</button>

	<button onClick="App.addTab('结账','account/cashier/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-left">
		<i class="fa fa-calendar"></i><br/><small style="font-size:12px;">结账</small>
	</button>

	<button onClick="App.addTab('报表','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-right">
		<i class="fa fa-line-chart"></i><br/><small style="font-size:12px;">报表</small>
	</button>

	<button onClick="App.addTab('设置','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-left">
		<i class="fa fa-cog"></i><br/><small style="font-size:12px;">设置</small>
	</button>

	<button onClick="App.addTab('日志','voucher/main',true);" class="button button-glow button-primary button-box button-giant button-longshadow-right">
		<i class="fa fa-file-text-o"></i><br/><small style="font-size:12px;">日志</small>
	</button>
</div>