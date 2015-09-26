/**
 * login.js
 * @author:sanlai_lee@qq.com
 * @date: 15/9/25
 */

Login = function(){

    return {

        /**
         * 登录表单提交.
         */
        submit:function(){
            if ($('#username').val() == '' || $('#password').val() == '') {
                return;
            }
            $('#loginForm').submit();
        }

    }

}();

$(function(){

    $('#loginSubmit').click(function(){
        Login.submit();
    });

    $('#loginForm').keypress(function(e){
        if(e.which==13) {
            Login.submit();
        }
    });

});