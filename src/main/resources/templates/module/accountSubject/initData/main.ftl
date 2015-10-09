<style>

    #init_data_table_container .datagrid-row-selected {
        background: #FFFFFF;
        color: #333;
    }

    #init_data_table_container .init-data-tip-text {
        position: absolute;
        z-index: 999;
        right: 18px;
        top: 30px;
        height: 28px;
        line-height: 28px;
        background: #F5F5F5;
    }

</style>

<div id="init_data_table_container">

    <div class="init-data-tip-text">
        <span style="color: red;"><i class="fa fa-meh-o fa-lg"></i> 当期期末结账，自动结束初始化。</span>
    </div>

    <div id="init_data_toolbar" style="padding:5px;background: #F5F5F5;display: none;">

        <input id="search_subject_code" class="easyui-textbox" style="width: 300px;" data-options="
            prompt: '科目代码或者科目名称',
            icons: [{
                iconCls:'icon-remove',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            },{
                iconCls:'icon-search',
                handler: function(e){
                    Account_Subject_Init_Data.search();
                }
            }]"
                >

    </div>

    <table id="init_data_table"></table>

</div>

<script src="resources/public/easyui/src/datagrid-cellediting.js"></script>
<script src="resources/js/accountSubject/initData/main.js"></script>
<script>
    $(function () {
        Account_Subject_Init_Data.init();
    })
</script>