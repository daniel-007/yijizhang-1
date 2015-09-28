<div class="top-bar">
	<div class="info">
		<b><@spring.message code="app.title" /> - 【欢迎<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">&#8194;:&#8194;<@security.authentication property="name"/></@security.authorize>】</b>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
			<input id="accountBookList" style="border-radius: 0px;" class="easyui-combobox" data-options="
			valueField: 'id',
			textField: 'bookName',
			url: 'account/book/list',
			method:'GET',
			onSelect: function(rec){
				alert(rec.id+rec.bookName);
			}" />
		</@security.authorize>
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