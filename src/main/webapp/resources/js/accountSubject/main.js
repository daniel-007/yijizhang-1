/**
 * Created by Joey Yan on 15-9-26.
 */


Account_Subject = function () {

    return {
        account_subject_current_table: null,
        account_subject_selected_row: null,
        specific_subject: {
            category_id: null,
            account_subject_id: -1
        },
        openEditWin: function (opt, title, icon) {
            var category_id = Account_Subject.specific_subject.category_id;
            var subject_id = Account_Subject.specific_subject.account_subject_id;
            var href = 'account/subject/opt/' + opt + '/category/' + category_id + "?subjectId=" + subject_id;

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
        checkNullAndBaseData: function () {
            var subject_id = Account_Subject.specific_subject.account_subject_id;
            if (subject_id == -1) {
                $.messager.alert('警告', '请选择一条记录。', 'warning');
                return false;
            }

            var base_flag = Account_Subject.account_subject_selected_row.base_flag;
            if (base_flag == 0) {
                $.messager.alert('警告', '基础数据不允许操作。', 'warning');
                return false;
            }

            return true;
        },
        remove: function () {
            if (!Account_Subject.checkNullAndBaseData()) {
                return false;
            }

            //判断是否已经挂件记账凭证
            var haveVoucher = Account_Subject.account_subject_selected_row.haveVoucher;
            if (haveVoucher > 0) {
                $.messager.alert('警告', '您选择的会计科目已经关联凭证，不允许删除。', 'warning');
                return false;
            }

            if (Account_Subject.account_subject_selected_row.children) {
                $.messager.alert('警告', '你所选择记录有子级，不允许删除。', 'warning');
                return false;
            }

            $.messager.confirm('确认', '你确定删除选中记录吗?', function (r) {
                if (r) {
                    var subject_id = Account_Subject.specific_subject.account_subject_id;
                    $.post("account/subject/delete/" + subject_id, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", "删除成功。", "info", function () {
                                Account_Subject.specific_subject.account_subject_id = -1;
                                Account_Subject.account_subject_current_table.treegrid("reload");
                            });
                        } else {
                            $.messager.alert("错误", data.msg, "error");
                        }
                    });
                }
            });

        },
        edit: function () {
            if (!Account_Subject.checkNullAndBaseData()) {
                return false;
            }
            Account_Subject.openEditWin("edit", "修改会计科目", "icon-edit");
        },
        button_bind_event: function () {

            var container = $("#accountSubject_layout");
            //关闭当前window.
            container.find(".close").click(function () {
                $("#default_win").window("close");
            });
            //新增会计科目.
            container.find(".add").click(function () {
                Account_Subject.openEditWin("add", "新增会计科目", "icon-add");
            });
            //修改会计科目.
            container.find(".edit").click(function () {
                Account_Subject.edit();
            });
            //删除会计科目.
            container.find(".remove").click(function () {
                return Account_Subject.remove();
            });
            //科目说明。
            container.find(".tip").click(function () {
                $("#account_subject_eidt_win").window({
                    title: "科目说明",
                    iconCls: "icon-tip",
                    width: 560,
                    height: 400,
                    modal: true,
                    href: "account/subject/tip"
                });
            });
        },
        /**
         * 会计大科目tab页初始化。
         */
        init_tab: function () {

            var $accountSubject_tabs = $("#accountSubject_tabs");

            $accountSubject_tabs.tabs({
                border: false,
                cache: true,
                onSelect: function (title) {

                    var id = $accountSubject_tabs.tabs('getSelected').panel('options').id;
                    var category_id = id.replace("category_", "").replace(",", "");

                    Account_Subject.specific_subject.category_id = category_id;
                    Account_Subject.specific_subject.account_subject_id = -1;

                    //判断是否已经加载数据.
                    if ($("#" + id).html()) {
                        return;
                    }

                    /**
                     * 以下内容加载树形数据.
                     */
                    var table_id = category_id + "_table";
                    $accountSubject_tabs.tabs('getSelected').html("<table id='" + table_id + "' style='width: 100%;'></table>");
                    Account_Subject.account_subject_current_table = $("#" + table_id).treegrid({
                        url: 'account/subject/category/' + category_id + '/subjects',
                        idField: 'subject_code',
                        treeField: 'subject_code',
                        fitColumns: true,
                        border: false,
                        columns: [
                            [
                                {title: '编码', field: 'subject_code', width: 200},
                                {title: '名称', field: 'subject_name', width: 300},
                                {title: '分类', field: 'category_datail_subject_name', width: 300}
                            ]
                        ],
                        onClickRow: function (row) {
                            Account_Subject.specific_subject.account_subject_id = row.id_back;
                            Account_Subject.account_subject_selected_row = row;
                        },
                        onDblClickRow: function (row) {
                            Account_Subject.edit();
                        },
                        loadFilter: function (data) {
                            var d = [];

                            for (var i = 0; i < data.length; i++) {
                                if (data[i].id == -1) {
                                    Account_Subject.account_subject_selected_row = data[i];
                                } else {
                                    d.push(data[i]);
                                }
                            }
                            return d;
                        },
                        onLoadSuccess: function () {
                            Account_Subject.button_bind_event();
                        }
                    });

                }
            });

        },
        init: function () {

            this.init_tab();

            //解决加载不同步问题。
            $('#accountSubject_layout').show();

        }

    }

}();