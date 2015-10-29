<style>
    #balance_sheet_container .datagrid-row-selected {
        background: #FFFFFF;
        color: #333333;
    }
</style>
<div id="balance_sheet_container" class="easyui-panel" data-options="fit:true,border:false">

    <input id="currentPeriod_hidden" type="hidden" value="${period.id?default('')}">

    <div style="padding:2px 5px;background: #F5F5F5;">
        <i class="fa fa-hand-o-right fa-lg"></i> 当前页面会计期间：
        <input id="period" name="period" style="width: 60px;">
    </div>



<#list balanceSheets as balanceSheet>
    <div style="float: left;width: 50%;">
        <table id="data_table_${balanceSheet.id?default()}" code="${balanceSheet.code?default()}"></table>
    </div>
</#list>

</div>

<script>

    $(function () {
        Balance_Sheet.init();
    })

</script>