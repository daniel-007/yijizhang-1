	<h2>记账凭证</h2>
	<div style="margin:20px 0;"></div>
    <table id="tg" class="easyui-treegrid" title="Editable TreeGrid" style="width:700px;height:250px"
            data-options="
                iconCls: 'icon-ok',
                rownumbers: true,
                animate: true,
                collapsible: true,
                fitColumns: true,
                url: 'voucher/kjkm.json',
                method: 'get',
                idField: 'id',
                treeField: 'name',
                showFooter: true
            ">
        <thead>
            <tr>
                <th data-options="field:'name',width:180,editor:'text'">摘要</th>
                <th data-options="field:'persons',width:60,align:'right',editor:'numberbox'">科目代码</th>
                <th data-options="field:'begin',width:80,editor:'datebox'">借方金额</th>
                <th data-options="field:'end',width:80,editor:'datebox'">贷方金额</th>
            </tr>
        </thead>
    </table>
    <script type="text/javascript">
        function formatProgress(value){
            if (value){
                var s = '<div style="width:100%;border:1px solid #ccc">' +
                        '<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
                        '</div>';
                return s;
            } else {
                return '';
            }
        }
        var editingId;
        function edit(){
            if (editingId != undefined){
                $('#tg').treegrid('select', editingId);
                return;
            }
            var row = $('#tg').treegrid('getSelected');
            if (row){
                editingId = row.id
                $('#tg').treegrid('beginEdit', editingId);
            }
        }
        function save(){
            if (editingId != undefined){
                var t = $('#tg');
                t.treegrid('endEdit', editingId);
                editingId = undefined;
                var persons = 0;
                var rows = t.treegrid('getChildren');
                for(var i=0; i<rows.length; i++){
                    var p = parseInt(rows[i].persons);
                    if (!isNaN(p)){
                        persons += p;
                    }
                }
                var frow = t.treegrid('getFooterRows')[0];
                frow.persons = persons;
                t.treegrid('reloadFooter');
            }
        }
        function cancel(){
            if (editingId != undefined){
                $('#tg').treegrid('cancelEdit', editingId);
                editingId = undefined;
            }
        }
    </script>