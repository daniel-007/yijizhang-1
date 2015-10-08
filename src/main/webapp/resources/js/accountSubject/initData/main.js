/**
 * 初始化数据.
 */
Account_Subject_Init_Data = function () {

    return {
        _init_data_table_: $("#init_data_table"),
        _keyword_: $("#search_subject_code"),
        _editor_: {type: 'numberbox', options: {precision: 2, prompt: '0.00'}},
        search: function () {
            var keyword = this._keyword_.val().toString();

            this._init_data_table_.datagrid("disableCellEditing");

            this._init_data_table_.datagrid('reload', {
                keyword: keyword
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
                return value;
            }
        },
        subjectNameFormatter: function (value, row, index) {
            var blank = "";
            for (var i = 1; i < row.level; i++) {
                blank += "&#12288;";
            }
            return blank + value;
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
        init_data_table: function () {
            this._init_data_table_.datagrid({
                url: 'account/subject/initData/alldata',
                fitColumns: true,
                rownumbers: true,
                singleSelect: true,
                toolbar: '#init_data_toolbar',
                columns: [
                    [
                        {field: 'subjectCode', title: '科目代码', width: 100},
                        {field: 'subjectName', title: '科目名称', width: 180, formatter: this.subjectNameFormatter},
                        {field: 'totalDebit', title: '累计借方', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter},
                        {field: 'totalCredit', title: '累计贷方', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter},
                        {field: 'direction', title: '方向', width: 30, align: 'center', formatter: this.directionFormatter},
                        {field: 'initialLeft', title: '期初余额', width: 100, align: 'right', editor: this._editor_, formatter: this.moneyFormatter},
                        {field: 'yearOccurAmount', title: '本年累计损益<br/>实际发生额', width: 80, align: 'right', editor: this._editor_, formatter: this.moneyFormatter, styler: this.yearOccurAmountStyler}
                    ]
                ],
                toolbar: [
                    {
                        text: '<i class="fa fa-floppy-o fa-lg"></i> 保存',
                        handler: function () {
                            alert('edit')
                        }
                    },
                    '-',
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
                            alert('help')
                        }
                    },
                    {
                        text: '<i class="fa fa-balance-scale fa-lg"></i> 平衡',
                        handler: function () {
                            alert('help')
                        }
                    }
                ],
                rowStyler: function (index, row) {
                    if (row.level == -1) {
                        return 'background-color:#FEFFB4;';
                    }
                },
                loadFilter: function (data) {

                    var accountSubjects = data.accountSubjects;
                    var creaseCode = data.creaseCode; //损益类科目第一个代码.
                    var len = accountSubjects.length;

                    for (var i = 0; i < len; i++) {
                        var cur_subject_code = accountSubjects[i].subjectCode.toString();

                        var _data = accountSubjects[i + 1];
                        if (_data) {
                            var next_subject_code = _data.subjectCode.toString();
                            if (next_subject_code.indexOf(cur_subject_code) > -1) {
                                accountSubjects[i].level = -1;  //会计科目父节点
                            }
                        }
                        //是否损益校验.
                        if (cur_subject_code.substring(0, 1) == creaseCode) {
                            accountSubjects[i].isCrease = true;
                        } else {
                            accountSubjects[i].isCrease = false;
                        }
                    }

                    return {total: len, rows: accountSubjects};
                },
                onLoadSuccess: function (data) {

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
                                        $.messager.alert("错误", e.msg, "error");
                                    }
                                }
                            })
                        }

                        //是否修改校验.
                        if (!$.isEmptyObject(rows)) {
                            if (rows[cur_idx].level == -1) {
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
        bind_event: function () {
            $("#init_data_toolbar").keypress(function (e) {
                if (e.which == 13) {
                    Account_Subject_Init_Data.search();
                }
            });

            $("#init_data_toolbar a").click(function () {
                Account_Subject_Init_Data.search();
            })

        },
        //初始数据表格.
        init: function () {
            this.init_data_table();
            this.bind_event();
        }
    }

}()


$(function () {

    Account_Subject_Init_Data.init();

})
