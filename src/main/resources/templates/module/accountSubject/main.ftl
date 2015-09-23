<div id="accountSubject_layout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'east',title:'操作'" style="width:120px;text-align: center;">

        <br/>
        <a href="javascript:void(0)" class="easyui-linkbutton close" data-options="iconCls:'icon-cancel'"
           style="width: 80%;">关闭</a>

        <br/>
        <br/>
        <br/>
        <br/>
        <a href="javascript:void(0)" class="easyui-linkbutton add" data-options="iconCls:'icon-add'"
           style="width: 80%">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton edit" data-options="iconCls:'icon-edit'"
           style="width: 80%">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton remove" data-options="iconCls:'icon-remove'"
           style="width: 80%">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton tip" data-options="iconCls:'icon-tip'"
           style="width: 80%">科目说明</a>

    </div>
    <div data-options="region:'center',border:false">
        <div id="accountSubject_tabs" class="easyui-tabs" data-options="fit:true,border:false">
        <#list categories as category>
            <div id="category_${category.id}" title="${category.title?default('')}"></div>
        </#list>
        </div>
    </div>
</div>

<script>

    $(function () {

        var $accountSubject_layout = $("#accountSubject_layout");
        var $accountSubject_tabs = $("#accountSubject_tabs");

        var account_subject = {
            specific_subject: {
                category_id: null,
                account_subject_id: null
            },
            init: function (container) {
                container.find(".close").click(function () {
                    return account_subject.close();
                })
                container.find(".add").click(function () {
                    return account_subject.add();
                })
            },
            add: function () {

                var category_id = account_subject.specific_subject.category_id;
                var subject_id = account_subject.specific_subject.account_subject_id;

                $("<div></div>").window({
                    title: '新增会计科目',
                    iconCls: 'icon-add',
                    width: 500,
                    height: 320,
                    modal: true,
                    collapsible: false,
                    shadow: true,
                    href: 'accountSubject/edit/category/' + category_id + "?subjectId=" + subject_id
                });

            },
            close: function () {
                $("#default_win").window("close");
            }
        }


        $accountSubject_tabs.tabs({
            border: false,
            onSelect: function (title) {
                var id = $accountSubject_tabs.tabs('getSelected').panel('options').id;
                var category_id = id.replace("category_", "");

                account_subject.specific_subject.category_id = category_id;
                account_subject.specific_subject.account_subject_id = null;

                $("#" + id).html("<table style='width: 100%;'></table>");
                $("#" + id + " table").treegrid({
                    url: 'accountSubject/category/' + category_id + '/subjects',
                    idField: 'id',
                    treeField: 'id',
                    lines: false,
                    fitColumns: true,
                    columns: [
                        [
                            {title: '编码', field: 'id', width: 200},
                            {title: '名称', field: 'text', width: 300}
                        ]
                    ],
                    onClickRow: function (row) {
                        account_subject.specific_subject.account_subject_id = row.code;
                    },
                    onDblClickRow: function (row) {
                    }
                });

            }
        });

        /*初始化按钮事件*/
        account_subject.init($accountSubject_layout);

    })

</script>