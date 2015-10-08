<#import "./lib/spring.ftl" as spring/>
<#assign security=JspTaglibs["/WEB-INF/tld/security.tld"] />

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title><@spring.message code="app.title" /></title>

        <#include "common/css.ftl"/>
        <#include "common/jslib.ftl"/>

        <#include "common/jsbiz.ftl"/>
    </head>
    <body class="easyui-layout">˝
        <div data-options="region:'north',border:false" style="height:130px;overflow: hidden;">
            <#include "common/header.ftl" />
        </div>
        <div data-options="region:'south',border:false" style="height:30px;overflow: hidden;">
            <#include "common/copyright.ftl" />
        </div>
        <div data-options="region:'east',border:false, split:true" style="width:25%;overflow: hidden;">
            <#include "common/east.ftl" />
        </div>
        <div data-options="region:'center',border:false">
            <#include "common/center.ftl" />
        </div>

    <script>
        $(function(){
            App.init();
        });
    </script>
    </body>
</html>