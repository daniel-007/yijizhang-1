<style>
    #cashflow_container .datagrid-row-selected {
        background: #FFFFFF;
        color: #333333;
    }
</style>
<div id="cashflow_container" class="easyui-panel" data-options="fit:true,border:false">

    <div style="height: 32px;line-height: 32px;background: #F5F5F5;padding-left: 5px;">

        本账套期间从
        <input id="start_period" class="easyui-numberspinner" style="width: 30px;"
               data-options="min:1,max:12,editable:false">
        到
        <input id="end_period" class="easyui-numberspinner" style="width: 30px;"
               data-options="min:1,max:12,editable:false">

    </div>

    <div style="float: left;width: 50%;height: 880px;">
        <table id="cashflow_table1"></table>
    </div>
    <div style="float: left;width: 50%;height: 880px;">
        <table id="cashflow_table2"></table>
    </div>

</div>

<script>

    Cash_Flow = function () {
        return {

            container: null,

            init_table: function () {

                var rownum = 1;

                $.get("cash/flow/cashflows?startPeriod=1&endPeriod=10", function (data) {
                    var data1 = [];
                    var data2 = [];
                    var totalname = "合计";
                    var total = 0, total1 = 0;
                    var num = 1;

                    for (var i = 0; i < data.length; i++) {
                        var d = data[i];
                        var staticCode = d.static_code;
                        if (staticCode.substr(0, 1) == "1") {
                            data1.push(d);

                            //合计统计
                            var suff = staticCode.substr(2, staticCode.length);
                            if ($.isEmptyObject(suff)) {
                                totalname = d['name'];
                                totalname = totalname.substr(2, (totalname.length - 3));
                            } else {
                                total += parseInt(d['cash'] == null ? 0 : d['cash']);
                                if (i < (data.length - 1)) {
                                    var suff_next = data[i + 1]["static_code"].substr(2, staticCode.length);
                                    if ((parseInt(suff_next) - 1) != parseInt(suff)) {
                                        if (num == 1) {
                                            num = 2;
                                            total1 = total;
                                            data1.push({sum: true, name: "&#12288;&#12288;现金流入小计", rownum: rownum++, cash: total});
                                        } else {
                                            num = 1;
                                            data1.push({sum: true, name: "&#12288;&#12288;现金流出小计", rownum: rownum++, cash: total});
                                            data1.push({sum: true, name: "&#12288;" + totalname + "净额", rownum: rownum++, cash: total1 - total});
                                        }
                                        total = 0;
                                    }
                                }

                            }

                        } else {
                            data2.push(d);
                        }

                        //添加行次
                        d["rownum"] = rownum++;

                    }

                    Cash_Flow.container.find("#cashflow_table1").datagrid({
                        data: data1,
                        fit: true,
                        fitColumns: true,
                        border: false,
                        singleSelect: true,
                        columns: [
                            [
                                {field: 'name', title: '项目', width: 400},
                                {field: 'rownum', title: '行次', width: 30, align: 'center'},
                                {field: 'cash', title: '金额', width: 100, align: 'right'}
                            ]
                        ],
                        rowStyler: function (index, row) {
                            var code = row.static_code + "";
                            if (code.length == 2) {
                                return 'font-weight:700;';
                            }
                            if (row['sum']) {
                                return 'background-color:#6293BB;color:#fff;';
                            }
                        }
                    });

                    Cash_Flow.container.find("#cashflow_table2").datagrid({
                        data: data2,
                        fit: true,
                        fitColumns: true,
                        border: false,
                        singleSelect: true,
                        columns: [
                            [
                                {field: 'name', title: '项目', width: 400},
                                {field: 'rownum', title: '行次', width: 30, align: 'center'},
                                {field: 'cash', title: '金额', width: 100, align: 'right'}
                            ]
                        ],
                        rowStyler: function (index, row) {
                            var code = row.static_code + "";
                            if (code.length == 2) {
                                return 'font-weight:700;';
                            }
                        },
                        onLoadSuccess: function (data) {

                        }
                    });
                })
            },

            init: function () {
                this.container = $("#cashflow_container");
                this.init_table();
            }
        }
    }();

    $(function () {
        Cash_Flow.init();
    })

</script>