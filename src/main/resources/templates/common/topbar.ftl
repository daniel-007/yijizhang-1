<div class="top-bar">
	<div class="info">
		<b>易记账财务系统 V1.0 - 【欢迎】</b>
	</div>
	<div class="action">
		<ul>
			<li><a href="#"><i class="fa fa-fax"></i>&#8194;客服</a></li>
			<li><a href="#"><i class="fa fa-question-circle"></i>&#8194;帮助</a></li>
			<@security.authorize ifAnyGranted="ROLE_ADMIN">
				<li><a href="#"><i class="fa fa-user"></i>&#8194;登录</a></li>
			</@security.authorize>
		</ul>
	</div>
</div>