<div id="accountSubject_eidt" style="padding: 15px 0 0 15px;">

    <form method="post">
        <table cellpadding="5" border="0">
            <tr>
                <td style="width: 80px;">父级科目:</td>
                <td>
                    <input id="parent_subject" style="width: 100%;"/>
                </td>
            </tr>
            <tr>
                <td>科目代码:</td>
                <td>
                    <input id="subject_code" class="easyui-validatebox" type="text" data-options="required:true"
                           readonly
                           style="width: 200px;"/>
                    <label>&nbsp;助记码:</label>
                    <input type="text" style="width: 80px;float: right;"/>
                </td>
            </tr>
            <tr>
                <td>科目名称:</td>
                <td>
                    <input class="easyui-validatebox" type="text" data-options="required:true" style="width: 98%;"/>
                </td>
            </tr>
            <tr>
                <td>科目类别:</td>
                <td>
                    <input class="easyui-combobox"
                           data-options="url:'/accountSubject/category/detail',required:true"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <fieldset>
                        <legend>余额方向</legend>

                        <label><input type="radio" name="direction" checked> 借方</label>&nbsp;&nbsp;
                        <label><input type="radio" name="direction"> 贷方</label>
                    </fieldset>

                </td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="2" align="right">
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" href="javascript:void(0)">确定</a>
                    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                       href="javascript:$('#account_subject_eidt_win').window('close')">取消</a>
                </td>
            </tr>
        </table>
    </form>

</div>

<script src="resources/public/jqueryPlugin/jquery.maskedinput.min.js"></script>
<script>

    $(function () {

        var $parent_subject = $("#parent_subject");
        var $subject_code = $("#subject_code");

        $parent_subject.combotree({
            url: '/accountSubject/category/1/subjects',
            required: true,
            panelMaxHeight: 200,
            onChange: function (newValue, oldValue) {

                $subject_code.val("").attr("readonly", false);
                $subject_code.mask(newValue + "nn").focus();

            }
        });
    })

    $.extend($.fn.validatebox.defaults.rules, {
        maxLength: {
            validator: function (value, param) {
                return value.length == param[0];
            },
            message: '此級科目代码限制 {0} 位数。'
        }
    });
</script>
