/**
 * Created by Joey Yan on 15-9-26.
 */

$(function () {

    var $accountSubject_layout = $("#accountSubject_layout");
    var $accountSubject_tabs = $("#accountSubject_tabs");

    var account_subject = {
        specific_subject: {
            category_id: null,
            account_subject_id: -1
        },
        init: function (container) {
            container.find(".close").click(function () {
                return account_subject.close();
            })
            container.find(".add").click(function () {
                return account_subject.add();
            })
            container.find(".edit").click(function () {
                return account_subject.edit();
            })
            container.find(".remove").click(function () {
                return account_subject.remove();
            })
            container.find(".tip").click(function () {
                $("#account_subject_eidt_win").window({
                    title: "科目说明",
                    iconCls: "icon-tip",
                    width: 550,
                    height: 400,
                    modal: true,
                    href: "account/subject/tip"
                });
            })
        },
        add: function () {
            account_subject.openEditWin("add", "新增会计科目", "icon-add");
        },
        edit: function () {
            if (!account_subject.checkNullAndBaseData()) {
                return false;
            }
            account_subject.openEditWin("edit", "修改会计科目", "icon-edit");
        },
        remove: function () {
            if (!account_subject.checkNullAndBaseData()) {
                return false;
            }

            if (account_subject_selected_row.children) {
                $.messager.alert('警告', '你所选择记录有子级，不允许删除。', 'warning');
                return false;
            }

            $.messager.confirm('确认', '你确定删除选中记录吗?', function (r) {
                if (r) {
                    var subject_id = account_subject.specific_subject.account_subject_id;
                    $.post("account/subject/delete/" + subject_id, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", "删除成功。", "info", function () {
                                account_subject_current_table.treegrid("reload");
                            });
                        } else {
                            $.messager.alert("错误", data.msg, "error");
                        }
                    });
                }
            });

        },
        checkNullAndBaseData: function () {
            var subject_id = account_subject.specific_subject.account_subject_id;
            if (subject_id == -1) {
                $.messager.alert('警告', '请选择一条记录。', 'warning');
                return false;
            }

            var base_flag = account_subject_selected_row.base_flag;
            if (base_flag == 0) {
                $.messager.alert('警告', '基础数据不允许操作。', 'warning');
                return false;
            }
            return true;
        },
        openEditWin: function (opt, title, icon) {
            var category_id = account_subject.specific_subject.category_id;
            var subject_id = account_subject.specific_subject.account_subject_id;
            var href = 'account/subject/edit/' + opt + '/category/' + category_id + "?subjectId=" + subject_id;

            $("#account_subject_eidt_win").window({
                title: title,
                iconCls: icon,
                width: 500,
                height: 320,
                modal: true,
                collapsible: false,
                shadow: true,
                href: href
            });
        },
        close: function () {
            $("#default_win").window("close");
        }
    };


    /*会计大科目tab页初始化。*/
    $accountSubject_tabs.tabs({
        border: false,
        onSelect: function (title) {
            var id = $accountSubject_tabs.tabs('getSelected').panel('options').id;
            var category_id = id.replace("category_", "");

            account_subject.specific_subject.category_id = category_id;
            account_subject.specific_subject.account_subject_id = -1;

            $("#" + id).html("<table style='width: 100%;'></table>");
            account_subject_current_table = $("#" + id + " table").treegrid({
                url: 'account/subject/category/' + category_id + '/subjects',
                idField: 'subject_code',
                treeField: 'subject_code',
                fitColumns: true,
                columns: [
                    [
                        {title: '编码', field: 'subject_code', width: 200},
                        {title: '名称', field: 'subject_name', width: 300}
                    ]
                ],
                onClickRow: function (row) {
                    account_subject.specific_subject.account_subject_id = row.id;
                    account_subject_selected_row = row;
                },
                onDblClickRow: function (row) {
                    account_subject.edit();
                },
                loadFilter: function (data) {
                    var d = [];

                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == -1) {
                            account_subject_selected_row = data[i];
                        } else {
                            d.push(data[i]);
                        }
                    }
                    return d;
                }
            });

        }
    });

    /*初始化按钮事件*/
    account_subject.init($accountSubject_layout);

})
