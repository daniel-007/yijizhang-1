<style>
    #balance_sheet_container .datagrid-row-selected {
        background: #FFFFFF;
        color: #333333;
    }
</style>
<div id="balance_sheet_container" class="easyui-panel" data-options="fit:true,border:false">

<#list balanceSheets as balanceSheet>
    <div style="float: left;width: 50%;">
        <table id="data_table_${balanceSheet.id?default()}" code="${balanceSheet.code?default()}"></table>
    </div>
</#list>

</div>

<script>

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

                var ts = Balance_Sheet.datagrid_tables;
                for (var i = 0; i < ts.length; i++) {
                    ts[i].unbind().datagrid("reload");
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

                this.container.find("table").each(function () {

                    var datagrid_table = $(this).datagrid({
                        url: 'balance/sheet/balancesheets?code=' + $(this).attr("code"),
                        border: false,
                        fitColumns: true,
                        singleSelect: true,
                        rownumbers: true,
                        columns: [
                            [
                                {field: 'id', title: 'id', hidden: true},
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
                        onLoadSuccess: function (data) {
                            Balance_Sheet.init_calculate_dropdow.isinit++;
                            Balance_Sheet.init_calculate_dropdow.init();

//                            Balance_Sheet.container.find("div.datagrid-body").css("overflow", "hidden");
                        },
                        loadFilter: function (data) {
                            if (data) {
                                var length = data.length;
                                var sum_name = data[0].name + "总计";

                                var _periodEnd = 0;
                                var _yearBegin = 0;
                                for (var i = 0; i < data.length; i++) {
                                    var record = data[i];
                                    if (record.needSum == 0) {
                                        _periodEnd += record.periodEnd;
                                        _yearBegin += record.yearBegin;
                                    }
                                }

                                var sum = {id: 9999, level: -1, name: sum_name, periodEnd: _periodEnd, yearBegin: _yearBegin};
                                data.push(sum);
                            }

                            return {total: data.length, rows: data};

                        }

                    });


                    Balance_Sheet.datagrid_tables.push(datagrid_table);


                })


            },
            init: function () {
                this.container = $("#balance_sheet_container");

                this.init_data_table();

            }

        }

    }();

    $(function () {
        Balance_Sheet.init();
    })

</script>