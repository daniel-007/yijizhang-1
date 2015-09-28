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


$(function () {

    var $accountSubject_edit_from = $("#accountSubject_edit form");
    var $parent_subject = $("#accountSubject_edit #parent_subject");
    var $subject_code = $("#accountSubject_edit #subject_code");
    var $category_detail = $("#accountSubject_edit #category_detail");
    var $subject_form_submit_button = $("#subject_form_submit_button");
    var $subject_form_close_win_button = $("#subject_form_close_win_button");
    var $subject_level_hidden = $("#accountSubject_edit input[name='level']");
    var opt = $("#accountSubject_edit #opt").val();
    var category_id = $("#accountSubject_edit #category_id").val();
    var account_subject_direction = $("#accountSubject_edit #account_subject_direction").val();


    /**
     * 关闭当前窗口并更新现在table.
     */
    var closeAndRefreshCurTab = function () {
        $('#account_subject_eidt_win').window('close');
    }

    /**
     * 初始化会计科目编码.
     */
    var checkCategoryDetail = function (record, fromParent) {

        var id = record.id;
        var subject_len = record.next_level_length;
        var subject_code = record.subject_code;
        var category_detail_id = record.category_datail_parent_subject_code;
        var is_readonly = true;
        var total_len = (subject_code + "").length + parseInt(subject_len);

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
    }

    /**
     * 初始化父级科目选项.
     */
    $parent_subject.combotree({
        url: '/account/subject/category/' + category_id + '/subjects',
        required: true,
        panelMaxHeight: 200,
        onClick: function (node) {
            if (opt == 'add') {
                $subject_level_hidden.val(parseInt(node.level) + 1);
            } else {
                $subject_level_hidden.val(parseInt(node.level));
            }
            checkCategoryDetail(node, true);
        }
    });

    /**
     * init.
     */
    checkCategoryDetail(account_subject_selected_row, false);

    /**
     * 初始化借贷方向.
     */
    $("input[value=" + account_subject_direction + "]").attr("checked", true);

    /**
     * 注册取消按钮.
     */
    $subject_form_close_win_button.click(function () {
        closeAndRefreshCurTab();
    })

    /**
     * subject表单提交.
     */
    $subject_form_submit_button.click(function () {
        $accountSubject_edit_from.form('submit', {
            url: "account/subject/edit",
            success: function (data) {
                try {
                    var data = $.toJSON(data);
                    if (data.success) {
                        closeAndRefreshCurTab();
                        account_subject_current_table.treegrid("reload");
                    } else {
                        $.messager.alert("操作失败", data.msg, "error");
                    }
                } catch (e) {
                    if (data.indexOf(true) < 0) {
                        $.messager.alert("操作失败", "请求失败，稍后重试。", "warning");
                    } else {
                        closeAndRefreshCurTab();
                        account_subject_current_table.treegrid("reload");
                    }
                }

            }
        });
    })

})


