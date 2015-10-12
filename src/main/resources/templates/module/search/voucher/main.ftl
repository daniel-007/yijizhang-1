<div id="search_voucher_container" class="easyui-panel" data-options="fit:true">

    <table id="data_table"></table>

</div>

<script>

    $(function () {
        Search_Voucher = function () {

            return {
                init_data_table: function () {

                    $("#search_voucher_container").find("#data_table").datagrid({
                        url: 'search/voucher/vouchers',
                        fit: true,
                        singleSelect: true,
                        columns: [
                            [
                                {field: 'code', title: 'Code', width: 100},
                                {field: 'name', title: 'Name', width: 100},
                                {field: 'price', title: 'Price', width: 100, align: 'right'}
                            ]
                        ]
                    });

                },
                init: function () {

                    this.init_data_table();

                }
            }

        }();


        Search_Voucher.init();
    })

</script>