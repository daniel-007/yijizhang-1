Balance_Sheet = function () {

    return {
        container: null,
        datagrid_tables: [],
        yearBegin_fmt: function (value, row, index) {
            if (row.level == 2) {
                value += '<a class="calculate" level="' + row.level + '" href="#"><i class="fa fa-calculator"></i></a>';
                value += '<div class="menu-content" style="padding:10px;text-align:left;display: none;width:250px;">计算公式：<br/><span>' + row.yearBeginExp;
                value += '</span> <i class="fa fa-pencil-square-o fa-lg" onclick="Balance_Sheet.toEdit(this,\'yearBegin\',' + row.id + ')"></i></div>';
                return  value;
            } else if (row.level == -1) {
                return value;
            }
        },
        periodEnd_fmt: function (value, row, index) {
            var field = "";
            if (row.level == 2) {
                value += '<a class="calculate" level="' + row.level + '" href="#"><i class="fa fa-calculator"></i></a>';
                value += '<div class="menu-content" style="padding:10px;text-align:left;display: none;width:250px;">计算公式：<br/><span>' + row.periodEndExp;
                value += '</span> <i class="fa fa-pencil-square-o fa-lg" onclick="Balance_Sheet.toEdit(this,\'periodEnd\',' + row.id + ')"></i></div>';
                return  value;
            } else if (row.level == -1) {
                return value;
            }
        },

        refresh: function () {

            this.init_calculate_dropdow.isinit = 0;

            var periodId = this.container.find("#currentPeriod_hidden").val();

            var ts = Balance_Sheet.datagrid_tables;
            for (var i = 0; i < ts.length; i++) {
                ts[i].unbind().datagrid("reload", {periodId: periodId});
            }
        },

        toEdit: function (obj, field, id) {
            var $span = $(obj).prev();
            var $this_obj = $(obj);
            if ($this_obj.hasClass("fa-check")) {
                var exp = $span.find("input").val();

                //保存公式
                $.messager.confirm('确认', '此操作不可逆，继续修改？', function (r) {
                    if (r) {
                        var _data = {};
                        if (field == 'yearBegin') {
                            _data = {yearBeginExp: exp, id: id};
                        } else {
                            _data = {periodEndExp: exp, id: id};
                        }

                        $.ajax({
                            url: '/balance/sheet/save/exp',
                            data: _data,
                            success: function (data) {
                                if (data.success) {
                                    $span.html(exp);
                                    $this_obj.removeClass("fa-check").addClass("fa-pencil-square-o");

                                    //刷新页面.
                                    Balance_Sheet.refresh();

                                }
                            }
                        })
                    }
                });

            } else {
                var exp = $span.html();

                var re1 = new RegExp("&lt;", "g");
                var re2 = new RegExp("&gt;", "g");

                exp = exp.replace(re2, ">");
                exp = exp.replace(re1, "<");

                $span.html($('<input type="text">').val(exp));
                $this_obj.removeClass("fa-pencil-square-o").addClass("fa-check");
            }
        },
        init_calculate_dropdow: {
            isinit: 0,
            init: function () {
                if (this.isinit == 2) {
                    $("a.calculate").each(function () {
                        $(this).menubutton({
                            hasDownArrow: false,
                            hideOnUnhover: false,
                            menu: $(this).next()
                        });

                    });
                }
            }
        },
        init_data_table: function () {

            var num = 1;

            this.container.find("table").each(function (index) {

                var idx = index;

                var datagrid_table = $(this).datagrid({
                    url: 'balance/sheet/balancesheets?code=' + $(this).attr("code"),
                    border: false,
                    fitColumns: true,
                    singleSelect: true,
//                    rownumbers: true,
                    columns: [
                        [
                            {field: 'num', title: '编号', align: "center"},
                            {field: 'name', title: '名称', width: 120, formatter: function (value, row, index) {
                                var level = row.level;
                                var pre = '<i class="tree-icon tree-folder tree-folder-open"></i> ';
                                if (level == 2) {
                                    pre = '&#12288;&#12288;<i class="tree-icon tree-file"></i> ';
                                } else if (level == 1) {
                                    pre = '&#12288;' + pre;
                                } else if (level == -1) {
                                    pre = '<i class="fa fa-jpy"></i> ';
                                }
                                return pre + value;
                            }},
                            {field: 'yearBegin', title: '年初数', width: 100, align: 'right', formatter: Balance_Sheet.yearBegin_fmt},
                            {field: 'periodEnd', title: '期末数', width: 100, align: 'right', formatter: Balance_Sheet.periodEnd_fmt}
                        ]
                    ],
                    rowStyler: function (index, row) {
                        if (row.level == -1) {
                            return 'background-color:#6293BB;color:#fff;';
                        }
                    },
                    onLoadSuccess: function (data) {
                        Balance_Sheet.init_calculate_dropdow.isinit++;
                        Balance_Sheet.init_calculate_dropdow.init();

                        Balance_Sheet.container.find("div.datagrid-view,div.datagrid-body").height("932px");
                    },
                    loadFilter: function (data) {
                        if (data) {
                            var new_data = [];
                            var length = data.length;
                            var sum_name = "总计";

                            var _periodEnd = 0, twoLev_periodEnd = 0;
                            var _yearBegin = 0, twoLev_yearBegin = 0;
                            for (var i = 0; i < length; i++) {
                                var record = data[i];
                                var periodEnd = record.periodEnd;
                                var yearBegin = record.yearBegin;
                                var need_num = record.needSum;

                                _periodEnd = need_num == 0 ? (_periodEnd + periodEnd) : need_num == 1 ? (_periodEnd - periodEnd) : _periodEnd;
                                _yearBegin = need_num == 0 ? (_yearBegin + yearBegin) : need_num == 1 ? (_yearBegin - yearBegin) : _yearBegin;


                                record["num"] = num++;
                                //组装新的数据.
                                new_data.push(record);

                                //二级类别合计
                                if (record.level == 2) {

                                    if (i > 0 && data[i - 1].level == 1) {
                                        twoLev_periodEnd = 0;
                                        twoLev_yearBegin = 0;
                                        sum_name = data[i - 1].name + "合计";
                                    }
                                    twoLev_periodEnd = need_num == 0 ? (twoLev_periodEnd + periodEnd) : need_num == 1 ? (twoLev_periodEnd - periodEnd) : twoLev_periodEnd;
                                    twoLev_yearBegin = need_num == 0 ? (twoLev_yearBegin + yearBegin) : need_num == 1 ? (twoLev_yearBegin - yearBegin) : twoLev_yearBegin;

                                    if ((i < (length - 1) && data[i + 1].level == 1) || ( i == (length - 1))) {
                                        new_data.push({num: num++, level: -1, name: sum_name, periodEnd: twoLev_periodEnd, yearBegin: twoLev_yearBegin});
                                    }
                                }

                            }

                            sum_name = data[0].name + "合计";
                            var sum = {num: num++, level: -1, name: sum_name, periodEnd: _periodEnd, yearBegin: _yearBegin};
                            new_data.push(sum);
                        }

                        return {total: new_data.length, rows: new_data};

                    }

                });

                Balance_Sheet.datagrid_tables.push(datagrid_table);
            })


        },
        //期间可选.
        init_period: function () {

            this.container.find("#period").combobox({
                url: 'search/voucher/periods',
                valueField: 'id',
                textField: 'currentPeriod',
                editable: false,
                panelHeight: 150,
                onLoadSuccess: function () {
                    $(this).combobox("setValue", Balance_Sheet.container.find("#currentPeriod_hidden").val());
                },
                onSelect: function (record) {
                    Balance_Sheet.refresh();
                }
            });

        },

        init: function () {
            this.container = $("#balance_sheet_container");

            this.init_period();
            this.init_data_table();

        }
    }

}();