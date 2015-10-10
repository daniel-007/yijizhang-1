/**
 *
 * Created by Joey Yan on 15-9-26.
 */


/**
 * 扩展属性,会计科目长度验证.
 */
$.extend($.fn.validatebox.defaults.rules, {
    maxLength: {
        validator: function (value, param) {
            value = value.replace("_", "");
            return value.length == param[1];
        },
        message: '此级科目代码限制 {0} 位数。'
    }
});


Account_Subject_Edit = function () {

    return {

        closeAndRefreshCurTab: function () {
            $('#account_subject_eidt_win').window('close');
        },
        button_event_bind: function () {
            $("#subject_form_close_win_button").click(function () {
                Account_Subject_Edit.closeAndRefreshCurTab();
            })
        },
        /**
         * subject表单提交.
         */
        form_submit_bind: function () {

            $("#subject_form_submit_button").unbind().click(function () {

                $.ajax({
                    url: "account/subject/edit",
                    data: $("#accountSubject_edit").find("form").serialize(),
                    success: function (data) {
                        if (data.success) {
                            Account_Subject_Edit.closeAndRefreshCurTab();
                            Account_Subject.account_subject_current_table.treegrid("reload");
                            Account_Subject.account_subject_current_table.treegrid("unselectAll");
                        } else {
                            $.messager.alert("操作失败", data.msg, "error");
                        }
                    }
                });

            })
        },
        checkCategoryDetail: function (record, fromParent) {

            var $subject_code = $("#accountSubject_edit").find("#subject_code");
            var $category_detail = $("#accountSubject_edit").find("#category_detail");
            var opt = $("#accountSubject_edit").find("#opt").val();
            var category_id = $("#accountSubject_edit").find("#category_id").val();

            var id = record.id;
            var is_readonly = true;
            var subject_len = record.next_level_length;
            var subject_code = record.subject_code;
            var category_detail_id = record.category_datail_parent_subject_code;
            var total_len = (subject_code.toString()).length + parseInt(subject_len);

            if (opt == 'add' || fromParent) {
                var mask_len = "";
                for (var i = 0; i < subject_len; i++) {
                    mask_len += "n";
                }
                $subject_code.show().next("span.numberbox").remove();
                $subject_code.validatebox({validType: 'maxLength[' + subject_len + ',' + total_len + ']'});
                $subject_code.val("");
                $subject_code.mask(subject_code + mask_len).focus();
            } else {
                total_len = (subject_code + "").length;
                subject_len = total_len;
                $subject_code.val(subject_code);
                $subject_code.numberbox({required: true, validType: 'maxLength[' + subject_len + ',' + total_len + ']'});

                //父级科目不许修改.
                $("#accountSubject_edit").find("#parent_subject").combotree('readonly', true);
            }

            if (id == -1) {
                is_readonly = false;
                category_detail_id = "";
            }

            $category_detail.combobox({
                    url: '/account/subject/category/detail?category_id=' + category_id,
                    valueField: 'subjectCode',
                    textField: 'subjectName',
                    required: true,
                    editable: false,
                    readonly: is_readonly,
                    onLoadSuccess: function () {
                        $category_detail.combobox('setValue', category_detail_id);
                    }
                }
            );
        },
        initFormEle: function () {

            var account_subject_direction = $("#accountSubject_edit").find("#account_subject_direction").val();

            var $parent_subject = $("#accountSubject_edit").find("#parent_subject");
            var $subject_level_hidden = $("#accountSubject_edit").find("input[name='level']");
            var opt = $("#accountSubject_edit").find("#opt").val();
            var category_id = $("#accountSubject_edit").find("#category_id").val();


            /**
             * 初始化借贷方向.
             */
            $("input[value=" + account_subject_direction + "]").attr("checked", true);

            /**
             * 初始化父级科目选项.
             */
            $parent_subject.combotree({
                url: '/account/subject/category/' + category_id + '/subjects',
                required: true,
                panelMaxHeight: 200,
                onClick: function (node) {
                    var level = parseInt(node.level);
                    level = (opt == 'add') ? (level + 1) : level;
                    $subject_level_hidden.val(level);
                    Account_Subject_Edit.checkCategoryDetail(node, true);
                }
            });
        },
        init: function () {

            this.initFormEle();
            this.checkCategoryDetail(Account_Subject.account_subject_selected_row, false);
            this.button_event_bind();
            this.form_submit_bind();

        }

    }

}();


