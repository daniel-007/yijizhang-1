<table id="init_data_table" style="overflow: hidden;"></table>


<script>

    $(function () {

        var $init_data_table = $("#init_data_table");

        $init_data_table.datagrid({
            url: 'account/subject/initData/alldata',
            fitColumns: true,
            rownumbers: true,
            singleSelect: true,
            columns: [
                [
                    {field: 'subjectCode', title: '科目代码', width: 160},
                    {field: 'subjectName', title: '科目名称', width: 180, formatter: function (value, row, index) {
                        var blank = "";
                        for (var i = 1; i < row.level; i++) {
                            blank += "&#12288;";
                        }
                        return blank + value;
                    }},
                    {field: 'totalDebit', title: '累计借方', width: 160, align: 'right'},
                    {field: 'totalCredit', title: '累计贷方', width: 160, align: 'right'},
                    {field: 'direction', title: '方向', width: 60, align: 'center', formatter: function (value, row, index) {
                        if (row.direction == 1) {
                            return "<span style='color: green;'>借</span>";
                        } else {
                            return "<span style='color: red;'>贷</span>";
                        }
                    }},
                    {field: 'initialLeft', title: '期初余额', width: 160, align: 'right'}
                ]
            ],
            rowStyler: function (index, row) {
                if (row.level == -1) {
                    return 'background-color:#FEFFB4;'; // return inline style
                }
            },
            loadFilter: function (data) {

                var len = data.length;
                for (var i = 0; i < len; i++) {




                }

            }
        });

    })

</script>
