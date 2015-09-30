<style>

    .datagrid-row-selected {
        background: #FFFFFF;
        color: #333;
    }

    .datagrid-cell-c1-totalDebit, .datagrid-cell-c1-totalCredit, .datagrid-cell-c1-initialLeft {
        font-weight: 700;
    }

    .datagrid-cell-c1-totalDebit {
        color: red;
    }

    .datagrid-cell-c1-totalCredit {
        color: green;
    }

    .init-data-tip-text {
        position: absolute;
        z-index: 9999;
        right: 18px;
        top: 30px;
        height: 28px;
        line-height: 28px;
        background: #F5F5F5;
    }

</style>

<div class="init-data-tip-text">
    <span><i class="fa fa-meh-o fa-lg"></i> 当期期末结账，自动结束初始化！！！</span>
</div>

<table id="init_data_table"></table>

<script src="resources/public/easyui/src/datagrid-cellediting.js"></script>
<script>

    $(function () {

        var $init_data_table = $("#init_data_table");


        /**
         * 金钱处理.
         */
        var editor = {type: 'numberbox', options: {precision: 2, prompt: '0.00'}};

        var moneyFormatter = function (value, row, index) {
            if (value) {
                var val = value.toString();
                return (val.indexOf(".") < 0) ? (val + ".00") : val;
            } else {
                return value;
            }
        }

        /**
         * direction渲染处理.
         */
        var directionFormatter = function (value, row, index) {
            if (row.direction == 1) {
                return "<span style='color: green;'>借</span>";
            } else {
                return "<span style='color: red;'>贷</span>";
            }
        }

        /**
         * subjectname显示处理.
         * @param value
         * @param row
         * @param index
         * @returns {string}
         */
        var subjectNameFormatter = function (value, row, index) {
            var blank = "";
            for (var i = 1; i < row.level; i++) {
                blank += "&#12288;";
            }
            return blank + value;
        }


        /**
         * 操作按钮.
         */
        var toolbar = [
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
                    alert('help')
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
        ];

        /**
         * datagrid显示处理.
         * @type {*}
         */
        var data_table = $init_data_table.datagrid({
            url: 'account/subject/initData/alldata',
            fitColumns: true,
            rownumbers: true,
            singleSelect: true,
            columns: [
                [
                    {field: 'subjectCode', title: '科目代码', width: 100},
                    {field: 'subjectName', title: '科目名称', width: 180, formatter: subjectNameFormatter},
                    {field: 'totalDebit', title: '累计借方', width: 100, align: 'right', editor: editor, formatter: moneyFormatter},
                    {field: 'totalCredit', title: '累计贷方', width: 100, align: 'right', editor: editor, formatter: moneyFormatter},
                    {field: 'direction', title: '方向', width: 30, align: 'center', formatter: directionFormatter},
                    {field: 'initialLeft', title: '期初余额', width: 100, align: 'right', editor: editor, formatter: moneyFormatter},
                    {field: 'yearOccurAmount', title: '本年累计损益实际发生额', width: 100, align: 'right', editor: editor, formatter: moneyFormatter}
                ]
            ],
            toolbar: toolbar,
            rowStyler: function (index, row) {
                if (row.level == -1) {
                    return 'background-color:#FEFFB4;';
                }
            },
            loadFilter: function (data) {
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    var cur_subject_code = data[i].subjectCode.toString();

                    var _data = data[i + 1];
                    if (_data) {
                        var next_subject_code = _data.subjectCode.toString();
                        if (next_subject_code.indexOf(cur_subject_code) > -1) {
                            data[i].level = -1;
                        }
                    }
                }

                return {total: len, rows: data};
            },
            onLoadSuccess: function () {
                $(this).datagrid("enableCellEditing", function (cur_idx, pre_idx) {
                    var rows = data_table.datagrid('getRows');

                    if (pre_idx != -1) {
                        //异步保存数据.
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

                    if (!$.isEmptyObject(rows)) {
                        if (rows[cur_idx].level == -1) {
                            return false;
                        }
                    }

                    return true;
                });
            }
        });

    })

</script>
