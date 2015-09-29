<div class="top-bar">
	<div class="info">
		<b><@spring.message code="app.title" /> - 【欢迎<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">&#8194;:&#8194;<@security.authentication property="name"/></@security.authorize>】</b>
		<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
        	-
			【
				<span style="font-size: 12px;">
					<i class="fa fa-circle-o-notch fa-spin"></i> 活动账套：
					<#if CURRENT_ACCOUNT_BOOK??>${CURRENT_ACCOUNT_BOOK.bookName}<input id="currentAccountBookId" type="hidden" value="${CURRENT_ACCOUNT_BOOK.id}"/></#if>
                    &#8194;<#if CURRENT_PERIOD??>第${CURRENT_PERIOD.currentPeriod}期<input id="currentPeriodId" type="hidden" value="${CURRENT_PERIOD.id}"/></#if>
                    &#8194;&#8194;&#8194;
					<input id="accountBookList" style="border-radius: 0px;display: none;"/>
                    <a id="switchBtn" href="javascript:void(0);">切换</a>
					<a id="confirmSwitchBtn" style="display: none; margin-left: 5px;" href="javascript:void(0);">保存</a>
					<a id="cancelSwitchBtn" style="display: none; margin-left: 5px;" href="javascript:void(0);">取消</a>
				</span>
			】
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