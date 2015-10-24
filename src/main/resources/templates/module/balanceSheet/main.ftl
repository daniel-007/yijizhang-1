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
            yearBegin_fmt: function (value, row, index) {
                if (row.level == 2) {
                    value += '<a class="calculate" level="' + row.level + '" href="#"><i class="fa fa-calculator"></i></a>';
                    value += '<div class="menu-content" style="padding:10px;text-align:left;display: none;width:250px;">计算公式：<br/><span>' + row.yearBeginExp;
                    value += '</span> <i class="fa fa-pencil-square-o fa-lg" onclick="Balance_Sheet.toEdit(this)"></i></div>';
                    return  value;
                }
            },
            periodEnd_fmt: function (value, row, index) {
                if (row.level == 2) {
                    value += '<a class="calculate" level="' + row.level + '" href="#"><i class="fa fa-calculator"></i></a>';
                    value += '<div class="menu-content" style="padding:10px;text-align:left;display: none;width:250px;">计算公式：<br/><span>' + row.periodEndExp;
                    value += '</span> <i class="fa fa-pencil-square-o fa-lg" onclick="Balance_Sheet.toEdit(this)"></i></div>';
                    return  value;
                }
            },
            toEdit: function (obj) {

                var $span = $(obj).prev();

                if ($(obj).hasClass("fa-check")) {
                    var exp = $span.find("input").val();
                    $span.html(exp);
                    $(obj).removeClass("fa-check").addClass("fa-pencil-square-o");
                } else {
                    var exp = $span.html();

                    var re1 = new RegExp("&lt;", "g");
                    var re2 = new RegExp("&gt;", "g");

                    exp = exp.replace(re2, ">");
                    exp = exp.replace(re1, "<");

                    $span.html($('<input type="text">').val(exp));
                    $(obj).removeClass("fa-pencil-square-o").addClass("fa-check");
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

                    $(this).datagrid({
                        url: 'balance/sheet/balanceSheets?code=' + $(this).attr("code"),
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

                            Balance_Sheet.container.find("div.datagrid-body").css("overflow", "hidden");
                        }

                    });


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