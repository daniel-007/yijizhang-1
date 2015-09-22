<#assign spring=JspTaglibs["/WEB-INF/tld/spring.tld"] />
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@spring.message code="app.title"/></title>

    <#include "common/css.ftl"/>

	<#include "common/jslib.ftl"/>

  </head>
  <body class="easyui-layout">˝
	<div data-options="region:'north',border:false" style="height:130px;overflow: hidden;">
		<#include "common/header.ftl" />
	</div>
	<div data-options="region:'south',border:false" style="height:30px;overflow: hidden;">
		<#include "common/copyright.ftl" />
	</div>
	<div data-options="region:'east',border:false,split:true" style="width:20%;">
		<#include "common/east.ftl" />
	</div>
	<div data-options="region:'center',border:false,iconCls:'icon-ok'">
		<#include "common/center.ftl" />
	</div>

	<#include "common/jsbiz.ftl"/>

  </body>
</html>