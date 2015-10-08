<form id="passwordForm" method="post">
    <div style="padding-top: 20px; text-align: center;">
        <div id="passwordForm_msg" style="margin-bottom:10px;width: 100%;display: none;">

        </div>
        <div style="margin-bottom:10px;width: 100%;">
            <input id="old_passwd" name="old_passwd" class="easyui-textbox" type="password" style="width:80%;height:30px;padding:12px"
                   data-options="required:true,missingMessage:'请输入旧密码',validType:'minLength[5]',prompt:'旧密码'">
        </div>
        <div style="margin-bottom:10px;width: 100%;">
            <input id="new_passwd" name="new_passwd" class="easyui-textbox" type="password" style="width:80%;height:30px;padding:12px"
                   data-options="required:true,missingMessage:'请输入新密码',validType:'minLength[5]',prompt:'新密码'">
        </div>
        <div style="width: 100%;">
            <a id="savePasswdBtn" href="javascript:void(0);" class="button button-raised button-rounded" style="width:80%;"><i class="fa fa-floppy-o"></i> 保&#8194;&#8194;存</a>
        </div>
    </div>
</form>
