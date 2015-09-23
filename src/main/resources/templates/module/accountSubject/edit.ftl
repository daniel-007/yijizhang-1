<div id="accountSubject_eidt" style="padding: 15px;">

    <table cellpadding="5">
        <tr>
            <td>父级科目:</td>
            <td colspan="3">
                <input class="easyui-combotree" data-options="url:'/accountSubject/category/1/subjects',required:true"
                       style="width: 99%;"/>
            </td>
        </tr>
        <tr>
            <td>科目代码:</td>
            <td>
                <input class="easyui-numberbox" type="text" data-options="required:true,validType:'maxLength[2]'"/>
            </td>
            <td>助记码:</td>
            <td>
                <input type="text" style="width: 95%;"/>
            </td>
        </tr>
        <tr>
            <td>科目名称:</td>
            <td colspan="3">
                <input class="easyui-validatebox" type="text" data-options="required:true" style="width: 98%;"/>
            </td>
        </tr>
        <tr>
            <td>科目类别:</td>
            <td colspan="3">
                <input class="easyui-validatebox" type="text" data-options="required:true" style="width: 98%;"/>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend>余额方向</legend>

                    <input type="radio" name="direction" checked> <label>借方</label>&nbsp;&nbsp;
                    <input type="radio" name="direction"> <label>贷方</label>
                </fieldset>

            </td>
        </tr>
        <tr>
            <td colspan="4" align="right">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" href="javascript:void(0)"
                   onclick="javascript:alert('ok')">确定</a>
            </td>
        </tr>
    </table>

</div>
<script>

    $.extend($.fn.validatebox.defaults.rules, {
        maxLength: {
            validator: function(value, param){
                return value.length <= param[0];
            },
            message: '此級科目代码限制 {0} 位数。'
        }
    });
</script>
