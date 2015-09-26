<div class="top-bar">
	<div class="info">
		<b>易记账财务系统 V1.0 - 【欢迎: &#8194;<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER"><@security.authentication property="name"/></@security.authorize>】</b>
	</div>
	<div class="action">
		<ul>
			<li><a href="#"><i class="fa fa-fax"></i>&#8194;客服</a></li>
			<li><a href="#"><i class="fa fa-question-circle"></i>&#8194;帮助</a></li>
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
				<li><a href="/logout"><i class="fa fa-user"></i>&#8194;注销</a></li>
			</@security.authorize>
		</ul>
	</div>
</div>