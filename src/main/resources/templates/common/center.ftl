<div id="tabContainer">
    <div title='<i class="fa fa-home"></i> <@spring.message code="welcome.text"/>' class="welcome-panel easyui-layout">
        <div data-options="region:'center',border:false" style="width: 75%;">
            <#include "../module/welcome/welcome.ftl"/>
        </div>

        <div id="east" data-options="region:'east',border:false,split:true" style="width:25%;">
            <#include "../module/welcome/east.ftl" />
        </div>
    </div>
</div>