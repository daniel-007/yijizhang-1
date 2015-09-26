
<div id="accountBook_layout" class="easyui-layout"
	data-options="fit:true">
	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west'"
				style="width: 180px; background-image: url('resources/public/img/jianzhang.png')"></div>
			<div data-options="region:'center'" style="padding: 15px;">
				<form id="create_book" action="#" method="post">
					<div id="firstJsp">
						<br /> <font size="3"><b>请在建账向导的指引下，建议一个最合适你公司实际情况的账套。</b></font>
						<br /> <br /> <font size="2">请输入账套名称:</font> <input type="text"
							id="book_name" name="bookName" style="width: 80%;" /> <br /> <br />
						<br /> <font size="2">请输入公司名称:</font> <input type="text"
							id="company_name" name="companyName" style="width: 80%;" />
					</div>
					<div id="sencondJsp" style="display: none;">
						<fieldset>
							<legend>
								<font size="2" color="blue">请选择科目体系</font>
							</legend>
							<#list categories as category> <br />
								<input name="dictValueId" value="${category.id}" type="radio" />${category.showValue?default('')}
							</#list>
						</fieldset>
						<br />
						<fieldset>
							<legend>
								<font size="2" color="blue">记账本位币</font>
							</legend>
							<br /> 代码：<font size="0.1"><input type="text"
								id="money_code" name="moneyCode" value="RMB" style="width: 20%;" /></font>
							名称：<input type="text" id="money_name" name="moneyName"
								value="人民币" style="width: 20%;" />
						</fieldset>
						<br />
						<fieldset>
							<legend>
								<font size="2" color="blue">定义会计期间的界定方式</font>
							</legend>
							<br /> 账套会计启用期间：<input id="init_year" name="initYear"
								class="easyui-numberspinner"
								data-options="onChange: function(value){$('#vv').text(value);
			   		  	$('#start_time').text(value+'年'+$('#init_period').val()+'月1号');}"
								style="width: 70px;" /> 年<input id="init_period"
								name="initPeriod" class="easyui-numberspinner"
								data-options="onChange: function(value){$('#vv').text(value);
			   		  	$('#start_time').text($('#init_year').val()+'年'+value+'月1号');}"
								style="width: 50px;" />期 <br /> <br />
							账套启用日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								id="start_time" style="white-space: nowrap;"></label>
						</fieldset>
					</div>
					<div id="thirdJsp" style="display: none; padding: 15px;">
						<br /> <br /> <font size="2">请定义会计科目长度：</font> <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>一级科目代码长度：</label><input
							id="level1" name="level1" class="easyui-numberspinner" value="4"
							data-options="onChange: function(value){$('#vv').text(value);}"
							style="width: 45px;" disabled="disabled" /> <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>二级科目代码长度：</label><input
							id="level2" name="level2" class="easyui-numberspinner" value="2"
							data-options="onChange: function(value){$('#vv').text(value);}"
							style="width: 45px;" /> <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>三级科目代码长度：</label><input
							id="level3" name="level3" class="easyui-numberspinner" value="2"
							data-options="onChange: function(value){$('#vv').text(value);}"
							style="width: 45px;" /> <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>四级科目代码长度：</label><input
							id="level4" name="level4" class="easyui-numberspinner" value="2"
							data-options="onChange: function(value){$('#vv').text(value);}"
							style="width: 45px;" /> <br /> <br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>五级科目代码长度：</label><input
							id="level5" name="level5" class="easyui-numberspinner" value="2"
							data-options="onChange: function(value){$('#vv').text(value);}"
							style="width: 45px;" />
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>


		</div>
		<input type="hidden" name="status" value="firstJsp" />
	</div>
	<div data-options="region:'south'"
		style="height: 50px; text-align: right;">
		<a id="bookInfo" href="javascript:void(0)"
			class="button button-primary button-small" style="display: none;">
			<i class="fa fa-info-circle"></i>制度说明
		</a> <a id="beforeLink" href="javascript:void(0)"
			class="button button-primary button-small" style="display: none;">
			<i class="fa fa-arrow-circle-left"></i>上一步
		</a> <a id="nextLink" href="javascript:void(0)"
			class="button button-primary button-small"> <i
			class="fa fa-arrow-circle-right"></i>下一步
		</a> <a id="completeLink" href="javascript:void(0)"
			class="button button-primary button-small" style="display: none;">
			<i class="fa fa-check-circle"></i>完成
		</a> <a id="cancelLink" href="javascript:void(0)"
			class="button button-primary button-small"> <i
			class="fa fa-power-off"></i>取消
		</a>
	</div>
</div>
<div id="account_book_info_win"></div>

<script>
	var nowdate = new Date();
	var year = nowdate.getFullYear();
	var month = nowdate.getMonth() + 1;

	$(function() {
		//初始化年月
		$('#init_year').numberbox({
			value : year
		});
		$('#init_period').numberbox({
			value : month
		});
		$('#start_time').text(year + '年' + month + '月1号');
		$('#start_time').css('white-space', 'nowrap');
		//取消按钮操作
		$('#cancelLink').bind('click', function() {
			$("#default_win").window("close");
		});
		//上一步按钮操作
		$('#beforeLink').bind('click', function() {
			if ($('input[name=status]').val() == 'sencondJsp') {
				//显示对应页面与按钮
				$('#firstJsp').css('display', '');
				$('#sencondJsp').css('display', 'none');
				$('#thirdJsp').css('display', 'none');
				$('#bookInfo').css('display', 'none');
				$('#beforeLink').css('display', 'none');
				//设置标志
				$('input[name=status]').val('firstJsp');
			} else {
				//显示对应页面与按钮
				$('#firstJsp').css('display', 'none');
				$('#sencondJsp').css('display', '');
				$('#thirdJsp').css('display', 'none');
				$('#bookInfo').css('display', '');
				$('#nextLink').css('display', '');
				$('#completeLink').css('display', 'none');
				//设置标志
				$('input[name=status]').val('sencondJsp');
			}
		});
		//下一步按钮操作
		$('#nextLink')
				.bind(
						'click',
						function() {
							if ($('input[name=status]').val() == 'firstJsp') {
								//firstJsp页面要素要填写
								if ($('#book_name').val() == ''
										|| $('#company_name').val() == '') {
									$.messager.alert("提示信息", "账套名称与公司名称均不能为空!");
									return;
								}
                                //检查账套名称是否已经存在
                                $.ajax({
                                    url: "account/book/is/exist",
                                    async:false,
                                    data:{
                                        'name':$('#book_name').val().trim(),
                                        'companyName':$('#company_name').val().trim()
                                    },
                                    success: function(result){
                                        if(result){
                                            $.messager.alert("提示信息", "账套名称已经存在，请重新命名账套!");
                                            return;
                                        }else{
                                            //显示对应页面与按钮
                                            $('#firstJsp').css('display', 'none');
                                            $('#sencondJsp').css('display', '');
                                            $('#thirdJsp').css('display', 'none');
                                            $('#bookInfo').css('display', '');
                                            $('#beforeLink').css('display', '');
                                            //设置标志
                                            $('input[name=status]').val('sencondJsp');
                                        }
                                    }
                                });
							} else {
								//secondJsp页面需选择科目体系
								if ($('input:radio[name="dictValueId"]:checked')
										.val() == undefined) {
									$.messager.alert("提示信息", "请选择科目体系!");
									return;
								}
								//显示对应页面与按钮
								$('#firstJsp').css('display', 'none');
								$('#sencondJsp').css('display', 'none');
								$('#thirdJsp').css('display', '');
								$('#bookInfo').css('display', 'none');
								$('#nextLink').css('display', 'none');
								$('#completeLink').css('display', '');
								//设置标志
								$('input[name=status]').val('thirdJsp');
							}
						});
		//制度说明
		$('#bookInfo').bind('click', function() {
			$("#account_book_info_win").window({
				title : '<i class="fa fa-info-circle"></i>制度说明',
				width : 650,
				height : 500,
				modal : true,
				collapsible : false,
				shadow : true,
				href : 'account/book/info'
			});
		});
		//完成按钮
		$('#completeLink').bind('click', function() {

            $.ajax({
                url: "account/book/complete",
                data:$("#create_book").serialize(),
                success: function(data){
                    if(data.result){
                        $.messager.alert("提示信息", "新建账套成功!");
                        $("#default_win").window('close');
                    }else{
                        $.messager.alert("提示信息", "操作失败，请联系管理员!");
                    }
                }
            });
		});
	});
</script>