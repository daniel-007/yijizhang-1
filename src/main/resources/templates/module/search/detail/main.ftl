<div id="search_detail_container" class="easyui-panel" data-options="fit:true,border:false">
    <div id="tb" class="tabs-header tabs-header-noborder" style="height: 28px;line-height: 28px;padding: 0 5px;">
     <input id="currentPeriod_hidden" type="hidden" value="${period.currentPeriod?default()}">
        <div class="datagrid-btn-separator"></div>
        <a id="search" href="#" class="easyui-linkbutton" plain="true"><i class="fa fa-filter fa-lg"></i> 查询</a>
        <div style="float: left;font-weight: 700;">
            <i class="fa fa-hand-o-right fa-lg"></i> 会计期间：
            <input id="startPeriod" name="startPeriod" class="easyui-numberspinner" data-options="onChange: function(value){$('#vv').text(value);}"	style="width: 60px;" />
            	至
            <input id="endPeriod" name="endPeriod"     class="easyui-numberspinner" data-options="onChange: function(value){$('#vv').text(value);}"	style="width: 60px;" />
        </div>
         <div style="float: left;font-weight: 700;">
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i class="fa fa-hand-o-right fa-lg"></i>科目代码：
            <input id="startSubjectCode" name="startSubjectCode"  	style="width: 60px;" />
            	至
            <input id="endSubjectCode" name="endSubjectCode"    	style="width: 60px;" />
        </div>
    </div>

    <table id="detail_data_table"></table>

</div>

<script>

    $(function () {
    	Search_Detail.init();
    })

</script>