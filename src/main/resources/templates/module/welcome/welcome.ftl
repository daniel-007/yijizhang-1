<div class="welcome-panel">

	<div class="jtk-surface welcome-tb" id="canvas" style="height:400px;">
		<div class="jtk-node" id="flowchartWindow1">
			<button id="btn_kjkm" class="button button-action button-box button-jumbo">
				<div><i class="fa fa-list-alt"></i></div>
				<div><small style="font-size:12px;"><@spring.message code="welcome.kjkm"/></small></div>
			</button>
		</div>
		<div class="jtk-node" id="flowchartWindow2">
			<button id="btn_jiz" onClick="App.addTab('记账凭证-新增','voucher/main',true);" class="button button-action button-box button-jumbo">
				<div><i class="fa fa-pencil-square-o"></i></div>
				<div><small style="font-size:12px;"><@spring.message code="welcome.jiz"/></small></div>
			</button>
		</div>
		<div class="jtk-node" id="flowchartWindow3">
			<button id="btn_jzsy" class="button button-action button-box button-jumbo">
				<div><i class="fa fa-area-chart"></i></div>
				<div><small style="font-size:12px;"><@spring.message code="welcome.jzsy"/></small></div>
			</button>
		</div>
		<div class="jtk-node" id="flowchartWindow4">
			<button id="btn_cssj" onClick="App.addTab('初始化数据','account/subject/initData/main',true);" class="button button-action button-box button-jumbo">
				<div><i class="fa fa-laptop"></i></div>
				<div><small style="font-size:12px;"><@spring.message code="welcome.cssj"/></small></div>
			</button>
		</div>
		<div class="jtk-node" id="flowchartWindow5">
			<button id="btn_jiez" class="button button-action button-box button-jumbo">
				<div><i class="fa fa-calendar"></i></div>
				<div><small style="font-size:12px;"><@spring.message code="welcome.jiez"/></small></div>
			</button>
		</div>
	</div>

	<table class="chart-btn-tb">
		<tr>
			<td>
				<button class="button button-glow button-rounded button-primary"><i class="fa fa-table"></i>&#8194;<@spring.message code="welcome.zcfz"/></button>
			</td>
			<td>
				<button class="button button-glow button-rounded button-primary"><i class="fa fa-table"></i>&#8194;<@spring.message code="welcome.lirb"/></button>
			</td>
			<td>
				<button class="button button-glow button-rounded button-primary"><i class="fa fa-table"></i>&#8194;<@spring.message code="welcome.xjll"/></button>
			</td>
		</tr>
	</table>
</div>

<div id="default_win"></div>

<script>
	$(function(){
		Welcome.bindEvent();
        Welcome.initJsPlumb();
	});

</script>




