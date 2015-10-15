Search_Detail = function (){
	 return {
		 init_data_table: function () {
			  $("#search_detail_container").find("#detail_data_table").datagrid({
	                url: 'search/voucher/vouchers',
	                border: false,
	                fit: true,
	                singleSelect: true,
	                fitColumns: true,
	                rownumbers: true,
	                columns: [
	                    [
	                        {field: 'voucher_time', title: '日期', hidden: true},
	                        {field: 'voucher_word', title: '凭证字号', hidden: true},
	                        {field: 'summary', title: '摘要', width: 100, formatter: function (value) {
	                            var keyword = $("#search_voucher_container").find("#keyword_input").textbox("getValue");
	                            if (value) {
	                                return value.replace(keyword, "<span style='color: red;'>" + keyword + "</span>");
	                            } else {
	                                return '无';
	                            }
	                        }},
	                        {field: 'subject_name', title: '对方科目', formatter: this.keywordHighlight, width: 100},
	                        {field: 'debit', title: '借方金额', align: 'right', formatter: this.moneyFormatter, width: 100, styler: function () {
	                            return "font-weight:700;color:red;";
	                        }},
	                        {field: 'credit', title: '贷方金额', align: 'right', formatter: this.moneyFormatter, width: 100, styler: function () {
	                            return "font-weight:700;color:green;";
	                        }},
	                        {field: 'subject_name', title: '余额', width: 100},
	                    ]
	                ],
	                view: groupview,
	                groupField: 'voucher',
	                groupFormatter: function (value, rows) {
	                    var keyword = $("#search_voucher_container").find("#keyword_input").textbox("getValue");
	                    var voucher_arr = value.split(" ");

	                    var voucherWord = voucher_arr[0].replace(keyword, "<span style='color: red;'>" + keyword + "</span>");
	                    var time = voucher_arr[1].replace(keyword, "<span style='color: red;'>" + keyword + "</span>");

	                    return voucherWord + " ~ " + time;
	                }
	            });

		 },
	     init_period_select: function () {
	    	 $('#startPeriod').val($("#currentPeriod_hidden").val());
	    	 $('#endPeriod').val($("#currentPeriod_hidden").val());
	     },
	     init_SubjectCode: function () {
	    	 $('#startSubjectCode').searchbox({
    		    searcher:function(value,name){
    		        Account_Subject.open_subject_search_win(function (record) {
    		            alert(record.subjectName);
    		        })
    		    }
	    	 });
	    	 $('#endSubjectCode').searchbox({
    		    searcher:function(value,name){
    		        alert(value + "," + name);
    		    }
	    	});
	     },
	     init: function () {
            this.init_period_select();
            this.init_SubjectCode();
	     }
	 }
}();