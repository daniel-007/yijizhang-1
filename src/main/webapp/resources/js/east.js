/**
 * 首页新建账套的JS脚本文件。
 */
East=function(){

	var websocket = null;

	return {
		//初始化
		init : function(){
			East.bindEvent();
			East.connect();
		},

		//绑定事件
		bindEvent : function(){
			var $btn_xjzt = $("#btn_xjzt");
		    $btn_xjzt.click(function () {
				App.openWin('<i class="fa fa-list-alt"></i>新建账套', 550,400,'account/book/main',function(){
					$('#book_name').next('span').find('input').focus();
				});
		    });
		},

		/**
		 * 新建websocket连接
		 */
		connect : function(){
			var target = '/latest/info/list';
			websocket = new SockJS(target);
			websocket.onopen = function () {
				//如果websocket 连接已经打开，通知后台。后台开始推送数据。
				var msg = "{ready:true,currentPeriod:"+$('#currentPeriodId').val()+",bookId:"+$('#currentAccountBookId').val()+"}";
				East.sendMessage(msg);
				setInterval(function(){East.sendMessage(msg)},5000);
			};
			websocket.onmessage = function (event) {
				//console.log('Received: ' + event.data);
				var data  = event.data;
				var m = $.parseJSON(data);
				//最新余额列表
				var array = m['latestBalance'];
				var html='';
				for(var i in array){
					var c = array[i]['subject_code'];
					var n = array[i]['subject_name'];
					var b = array[i]['balance'];
					html += '<tr>'+
						'<td><a title="点击查看明细" href="javascript:void(0);" onclick="East.balanceDetail('+c+')">' + n + '</a></td>'+
						'<td style="text-align: right;">' + b + '</td>'+
						'</tr>';
					$('#latestBalanceTB').html(html);
				}

				//最新凭证列表
				array = m['latestVoucher'];
				html='<tr>'+
						'<td style="color:#009966">日期</td>'+
						'<td style="color:#009966">凭证字号</td>'+
						'<td style="color:#009966">摘要</td>'+
						'<td style="text-align: right;color:#009966;">合计数</td>'+
					'</tr>';
				for(var i in array){
					var a = array[i]['voucher_id'];
					var b = array[i]['voucher_time'];
					var c = array[i]['voucher_word'];
					var d = array[i]['summary'];
					var e = array[i]['count_sum'];
					html += '<tr>'+
						'<td><a title="点击查看明细" href="javascript:void(0);" onclick="East.voucherDetail('+a+')">' + b + '</a></td>'+
						'<td>' + c + '</td>'+
						'<td>' + d + '</td>'+
						'<td style="text-align: right;">' + e + '</td>'+
						'</tr>';
					$('#latestVoucherTB').html(html);
				}

				//移除fa-spin样式
				$('.fa-refresh').removeClass('fa-spin');
			};
			websocket.onclose = function () {
				//console.log('Info: WebSocket connection closed.');
			};
		},

		/**
		 * 断开websocket连接
		 */
		disconnect : function() {
			if (websocket != null) {
				websocket.close();
				websocket = null;
			}
		},

		/**
		 * 发送消息.
		 */
		sendMessage : function(message) {
			if (websocket != null) {
				//console.log('Sent: ' + message);
				websocket.send(message);
			} else {
				//alert('WebSocket connection not established, please connect.');
			}
		},

		/**
		 * 刷新最新余额列表.
		 */
		refresh:function(){
			$('.fa-refresh').addClass('fa-spin');
			var msg = "{ready:true,currentPeriod:"+$('#currentPeriodId').val()+",bookId:"+$('#currentAccountBookId').val()+"}";
			East.sendMessage(msg);
		},

		/**
		 * 查看明细账.
		 * @param subjectCode
		 */
		balanceDetail:function(subjectCode){
			App.addVoucherTab('明细账','search/detail/main?subjectCode='+subjectCode,true);
		},

		/**
		 * 查看凭证明细.
		 * @param voucherId
		 */
		voucherDetail:function(voucherId){
			App.addVoucherTab('记账', 'voucher/main?voucherId='+voucherId, true);
		}

	};
}();
