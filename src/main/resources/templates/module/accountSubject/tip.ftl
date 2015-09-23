<div id="accountSubject_layout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'east',title:'操作'" style="width:120px;">

        <br/>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>

        <br/>
        <br/>
        <br/>
        <br/>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">科目说明</a>

    </div>
    <div data-options="region:'center',border:false">
        <div class="easyui-tabs" data-options="fit:true,border:false">
            <div title="资产">
                <table id="ziChan_table" style="width: 100%;"></table>
            </div>
            <div title="负债" style="padding: 10px;">
                tab2
            </div>
            <div title="共同" style="padding: 10px;">
                tab3
            </div>
            <div title="权益" style="padding: 10px;">
                tab3
            </div>
            <div title="成本" style="padding: 10px;">
                tab3
            </div>
            <div title="损益" style="padding: 10px;">
                tab3
            </div>
        </div>
    </div>
</div>

<script>

    $(function () {

        $("a").click(function () {
            var opt = $(this).text();
            alert(opt);


        })


        $("#ziChan_table").treegrid({
            url: 'accountSubject/subject/1',
            idField: 'code',
            treeField: 'code',
            lines: false,
            fitColumns: true,
            columns: [
                [
                    {title: '编码', field: 'code', width: 200},
                    {title: '名称', field: 'title', width: 300}
                ]
            ]
        });
    })

</script>