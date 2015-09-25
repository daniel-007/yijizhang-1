<div id="accountSubject_eidt" style="padding: 15px 0 0 15px;">

    <form method="post">
        <table cellpadding="5" border="0">
            <tr>
                <td style="width: 80px;">父级科目:</td>
                <td>
                    <input id="parent_subject" style="width: 100%;" value="${subjectId}"/>
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
                    <input id="category_detail" class="easyui-combobox"
                           data-options="url:'/account/subject/category/detail', valueField: 'id',textField: 'subjectName',required:true"/>
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
        var $category_detail = $("#category_detail");
        var subjectId = "${subjectId}";


        var checkCategoryDetail = function (subjectId, category_detail_id, subject_code, next_level_lenght) {

            $category_detail.val(12);
            var is_readonly = true;

            if (subjectId == -1) {
                is_readonly = false;
                category_detail_id = "";
                next_level_lenght = 3;
                subject_code = 1;
            }

            var mask_len = "";
            for (var i = 0; i < next_level_lenght; i++) {
                mask_len += "n";
            }

            $subject_code.val("").attr("readonly", false);
            $subject_code.mask(subject_code + mask_len).focus();
            $category_detail.val(category_detail_id);
            $category_detail.attr("readonly", is_readonly);
        }

        checkCategoryDetail(subjectId);

        $parent_subject.combotree({
            url: '/account/subject/category/${categoryId}/subjects',
            required: true,
            panelMaxHeight: 200,
            onClick: function (node) {
                var code = node.subject_code;
                var next_level_lenght = node.next_level_length;
                var subject_id = node.subject_id;
                var category_detail_id = node.category_detail_id;

                checkCategoryDetail(subject_id, category_detail_id, code, next_level_lenght);
            }
        });
    })

</script>
