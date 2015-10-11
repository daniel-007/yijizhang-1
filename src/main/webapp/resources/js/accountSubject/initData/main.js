/**
 * 初始化数据.
 */
Account_Subject_Init_Data = function () {

    return {
        _init_data_table_: $("#init_data_table"),
        _keyword_: $("#search_subject_code"),
        _editor_: {type: 'numberbox', options: {precision: 2, prompt: '0.00'}},
        _keyword_val_: '',
        search: function () {
            var keyword = this._keyword_.val().toString();
            this._keyword_val_ = keyword;

            this._init_data_table_.datagrid("disableCellEditing");
            this._init_data_table_.datagrid('reload', {
                keyword: keyword
            });
        },
        keyword_search_bind_event: function () {
            $("#init_data_toolbar").keypress(function (e) {
                if (e.which == 13) {
                    Account_Subject_Init_Data.search();
                }
            });
        },
        moneyFormatter: function (value, row, index) {
            if (value) {
                var val = value.toString();
                var idx = val.indexOf(".");
                if (idx < 0) {
                    val += ".00";
                } else {
                    if (val.substring(idx + 1, val.length).length < 2) {
                        val += "0";
                    }
                }
                return val;
            } else {
                return value == 0 ? "" : value;
            }
        },
        directionFormatter: function (value, row, index) {
            if (row.direction == 1) {
                return "<span style='color: green;'>借</span>";
            } else {
                return "<span style='color: red;'>贷</span>";
            }
        },
        yearOccurAmountStyler: function (value, row, index) {
            if (!row.isCrease) {
                return 'background-color:#ccc;color:#fff;';
            }
        },
        subjectCodeFormatter: function (value, row, index) {
            return Account_Subject_Init_Data.highlight_keyword(value.toString());
        },
        subjectNameFormatter: function (value, row, index) {
            var blank = "";
            for (var i = 1; i < row.level; i++) {
                blank += "&#12288;";
            }

            return Account_Subject_Init_Data.highlight_keyword(blank + value);
        },
        highlight_keyword: function (val) {
            var keyword = Account_Subject_Init_Data._keyword_val_;
            return val.replace(keyword, "<span style='color: red;'>" + keyword + "</span>");
        },
        init_data_table: function () {
            this._init_data_table_.datagrid({
                url: 'account/subject/initData/alldata',
                fitColumns: true,
                rownumbers: true,
                singleSelect: true,
                toolbar: '#init_data_toolbar',
                border: false,
                columns: [
                    [
                        {field: 'subjectCode', title: '科目代码', width: 100, formatter: this.subjectCodeFormatter},
                        {field: 'subjectName', title: '科目名称', width: 180, formatter: this.subjectNameFormatter},
                        {field: 'totalDebit', title: '累计借方', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter, styler: function () {
                            return 'font-weight: 700;color:red;';
                        }},
                        {field: 'totalCredit', title: '累计贷方', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter, styler: function () {
                            return 'font-weight: 700;color:green;';
                        }},
                        {field: 'direction', title: '方向', width: 30, align: 'center', formatter: this.directionFormatter},
                        {field: 'initialLeft', title: '期初余额', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter, styler: function () {
                            return 'font-weight: 700';
                        }},
                        {field: 'yearOccurAmount', title: '本年累计损益<br/>实际发生额', width: 80, align: 'right', editor: this._editor_, formatter: this.moneyFormatter, styler: this.yearOccurAmountStyler}
                    ]
                ],
                toolbar: [
                    {
                        text: '<i class="fa fa-filter fa-lg"></i> 过滤',
                        handler: function () {
                            $("#init_data_toolbar").slideToggle();
                        }
                    },
                    '-',
                    {
                        text: '<i class="fa fa-calculator fa-lg"></i> 汇总',
                        handler: function () {
                            var thiss = $(this);
                            thiss.linkbutton('disable');

                            $.ajax({
                                url: 'account/subject/initData/calculate',
                                success: function (data) {
                                    thiss.linkbutton('enable');
                                    if (data.success) {
                                        Account_Subject_Init_Data._init_data_table_.datagrid("disableCellEditing");
                                        Account_Subject_Init_Data._init_data_table_.datagrid('reload', {});
                                    } else {
                                        $.messager.alert("错误", data.msg, "error");
                                    }
                                }
                            })
                        }
                    },
                    {
                        text: '<i class="fa fa-balance-scale fa-lg"></i> 平衡',
                        handler: function () {

                            var balanceDialog = $("<div></div>").dialog({
                                title: '<i class="fa fa-balance-scale fa-lg"></i> 查看试算平衡',
                                href: 'account/subject/initData/balance/page',
                                width: 500,
                                height: 200,
                                modal: true,
                                collapsible: false,
                                shadow: true,
                                buttons: [
                                    {
                                        text: '退出',
                                        iconCls: 'icon-cancel',
                                        handler: function () {
                                            balanceDialog.dialog("destroy");
                                        }
                                    }
                                ]
                            });

                        }
                    }
                ],
                rowStyler: function (index, row) {
                    if (row.isParent) {
                        return 'background-color:#FEFFB4;';
                    }
                },
                loadFilter: function (data) {

                    var accountSubjects = data.accountSubjects;
                    var creaseCode = data.creaseCode; //损益类科目第一个代码.
                    var len = accountSubjects.length;
                    var isFirstPeriod = data.isFirstPeriod; //是否第一期.

                    for (var i = 0; i < len; i++) {
                        var cur_subject_code = accountSubjects[i].subjectCode.toString();

                        var _data = accountSubjects[i + 1];
                        if (_data) {
                            var next_subject_code = _data.subjectCode.toString();
                            if (next_subject_code.indexOf(cur_subject_code) > -1) {
                                accountSubjects[i].isParent = true;  //会计科目父节点
                            }
                        }
                        //是否损益校验.
                        if (cur_subject_code.substring(0, 1) == creaseCode) {
                            accountSubjects[i].isCrease = true; //是否损益类.
                        } else {
                            accountSubjects[i].isCrease = false;
                        }
                    }

                    return {total: len, rows: accountSubjects, isFirstPeriod: isFirstPeriod};
                },
                onLoadSuccess: function (data) {

                    //如果第一期则不需修改.
                    if (!data.isFirstPeriod) {
                        $('td[field="totalDebit"],td[field="totalCredit"],td[field="yearOccurAmount"],td[field="initialLeft"]').css({
                            "background-color": "#f2f2f2",
                            "border-color": "#ccc"
                        });
                        return;
                    }

                    $(this).datagrid("enableCellEditing", function (cur_idx, pre_idx, field) {
                        var rows = data.rows;

                        //异步保存数据.
                        if (pre_idx != -1 && (rows[pre_idx][field] == null || rows[pre_idx][field])) {
                            $.ajax({
                                url: 'account/subject/initData/edit',
                                data: rows[pre_idx],
                                type: 'POST',
                                async: true,
                                success: function (data) {
                                    if (!data.success) {
                                        $.messager.alert("错误", data.msg, "error");
                                    }
                                }
                            })
                        }

                        //校验是否可以修改.
                        if (!$.isEmptyObject(rows)) {
                            //父级几点.
                            if (rows[cur_idx].isParent) {
                                return false;
                            }

                            //非损益类会计科目校验.
                            if (field == "yearOccurAmount") {
                                if (!(rows[cur_idx].isCrease)) {
                                    return false
                                }
                            }
                        }

                        return true;
                    });
                }
            });
        },
        //初始数据表格.
        init: function () {
            this.init_data_table();
            this.keyword_search_bind_event();
        }
    }

}()

