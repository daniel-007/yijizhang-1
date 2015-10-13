<div id="search_voucher_container" class="easyui-panel" data-options="fit:true,border:false">


    <input id="currentPeriod_hidden" type="hidden" value="${period.id?default()}">

    <div id="tb" style="height: 28px;line-height: 28px;padding: 0 5px;">

        <a id="search" href="#" class="easyui-linkbutton" plain="true"><i class="fa fa-filter fa-lg"></i> 查询</a>

        <div id="search_div" style="display: none;">
            <input id="keyword_input" class="easyui-textbox" style="width: 220px;height: 26px;" data-options="
                prompt: '关键字',
                icons: [{
                    iconCls:'icon-remove',
                    handler: function(e){
                        $(e.data.target).textbox('clear');
                    }
                },{
                    iconCls:'icon-search',
                    handler: function(e){
                        Search_Voucher.search();
                    }
                }]"
                    >
        </div>

        <div style="float: right;color: #ff0000;font-weight: 700;">
            <i class="fa fa-hand-o-right fa-lg"></i> 当前页面会计期间：
            <input id="period" name="period" style="width: 60px;">
        </div>
    </div>

    <table id="data_table"></table>

</div>

<script>

    $(function () {
        Search_Voucher.init();
    })

</script>