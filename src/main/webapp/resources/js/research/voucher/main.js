Search_Voucher = function () {

    return {
        init_data_table: function () {

            $("#search_voucher_container").find("#data_table").datagrid({
                url: 'search/voucher/vouchers',
                border: false,
                fit: true,
                singleSelect: true,
                fitColumns: true,
                rownumbers: true,
                toolbar: '#search_voucher_container #tb',
                columns: [
                    [
                        {field: 'current_period', title: '期间', hidden: true},
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
                        {field: 'subject_code', title: '科目代码', formatter: this.keywordHighlight, width: 100},
                        {field: 'subject_name', title: '科目名称', formatter: this.keywordHighlight, width: 100},
                        {field: 'debit', title: '借方金额', width: 100},
                        {field: 'credit', title: '贷方金额', width: 100}
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

        keywordHighlight: function (val) {
            var keyword = $("#search_voucher_container").find("#keyword_input").textbox("getValue");
            return val.toString().replace(keyword, "<span style='color: red;'>" + keyword + "</span>");
        },

        init_period_select: function () {

            $("#search_voucher_container").find("#period").combobox({
                url: 'search/voucher/periods',
                valueField: 'id',
                textField: 'currentPeriod',
                editable: false,
                panelHeight: 150,
                onLoadSuccess: function () {
                    $(this).combobox("setValue", $("#currentPeriod_hidden").val());
                },
                onSelect: function (record) {
                    $("#search_voucher_container").find("#data_table").datagrid('reload', {
                        periodId: record.id
                    });
                }
            });

        },

        init_search: function () {
            $("#search_voucher_container").find("#search").click(function () {
                var search_div = $("#search_voucher_container").find("#search_div");
                if (search_div.css("display") == 'none') {
                    search_div.css("display", "inline");
                    search_div.find("input.textbox-text").select().focus();
                } else {
                    search_div.hide();
                }
            })

            $("#search_voucher_container").keypress(function (e) {
                if (e.which == 13) {
                    Search_Voucher.search();
                }
            });
        },

        search: function () {
            //关键字
            var keyword = $("#search_voucher_container").find("#keyword_input").textbox("getValue");
            //期间id
            var record = $("#search_voucher_container").find("#period").combobox("getValue");

            $("#search_voucher_container").find("#data_table").datagrid('reload', {
                periodId: record,
                keyword: keyword
            });
        },

        init: function () {

            this.init_search();
            this.init_period_select();
            this.init_data_table();

        }
    }

}();