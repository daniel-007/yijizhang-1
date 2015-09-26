<#import "./lib/spring.ftl" as spring/>
<#assign security=JspTaglibs["/WEB-INF/tld/security.tld"] />

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><@spring.message code="app.title" /></title>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="resources/public/js/jquery.min.js"></script>
        <script src="resources/public/easyui/jquery.easyui.min.js"></script>
        <!-- EasyUI -->
        <link rel="stylesheet" type="text/css" href="resources/public/easyui/themes/bootstrap/easyui.css">
        <link rel="stylesheet" type="text/css" href="resources/public/easyui/themes/icon.css">
        <!-- buttons -->
        <link rel="stylesheet" type="text/css" href="resources/public/css/buttons.css">
        <!-- font-awesome -->
        <link rel="stylesheet" href="resources/public/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="resources/css/base.css">
    </head>
    <body class="easyui-layout">˝
        <div data-options="region:'north',border:false" style="height:130px;overflow: hidden;">
            <#include "./common/topbar.ftl"/>
        </div>
        <div data-options="region:'south',border:false" style="height:30px;overflow: hidden;">
            <#include "./common/copyright.ftl" />
        </div>
        <div data-options="region:'center',border:false">
            <form name="loginForm" id='loginForm' action="/login" method="POST">
                <div style="width:400px;margin:0 auto;padding:30px 70px 20px 70px">
                    <div style="margin-bottom:10px">
                        <input id="username" name="username" class="easyui-textbox" style="width:100%;height:40px;padding:12px"
                               data-options="required:true,missingMessage:'请输入登录账号',prompt:'账号',iconCls:'icon-man',iconWidth:38">
                    </div>
                    <div style="margin-bottom:20px">
                        <input id="password" name="password" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px"
                               data-options="required:true,missingMessage:'请输入登录密码',prompt:'密码',iconCls:'icon-lock',iconWidth:38">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>
                    <div>
                        <a id="loginSubmit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
                            <span style="font-size:14px;">登&#8194;&#8194;录</span>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    <script src="resources/js/login.js"></script>
    </body>
</html>