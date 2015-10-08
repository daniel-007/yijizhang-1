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


<div id="init_data_toolbar" style="padding:2px 5px;background: #F5F5F5;display: none;">
    <input id="search_subject_code" class="easyui-textbox" style="width: 200px;" data-options="prompt:'科目代码或者科目名称'">
    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a>
</div>

<table id="init_data_table"></table>

<script src="resources/public/easyui/src/datagrid-cellediting.js"></script>
<script src="resources/js/accountSubject/initData/main.js"></script>
