<div class="easyui-layout" data-options="fit:true" style="height: 100%;">
    <div data-options="region:'north',border:false">
        <button id="btn_xjzt" class="button button-highlight button-large button-longshadow-right" style="width:100%;">
            <i class="fa fa-plus"></i>&#8194;新建帐套</button>
    </div>
    <div data-options="region:'center',border:false" style="width:100%;height: 46%;">
        <div class="easyui-panel" title="最新余额" style="padding: 10px;"
             data-options="iconCls:'fa fa-money',fit:true,closable:false,border:false,tools:'#tt1'"
                >
            <table id="latestBalanceTB">
                <tr>
                    <td>库存现金</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>银行存款</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>应收账款</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>应付账款</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>其他应收款</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>原材料</td>
                    <td style="text-align: right;"></td>
                </tr>
                <tr>
                    <td>库存商品</td>
                    <td style="text-align: right;"></td>
                </tr>
            </table>

        </div>
        <div id="tt1">
            <#--<button title="查看" class="button button-circle button-tiny-18"><i class="fa fa-search"></i></button>-->
            <button title="刷新" onclick="East.refresh();" class="button button-circle button-tiny-18"><i class="fa fa-refresh fa-spin"></i></button>
        </div>
    </div>
    <div data-options="region:'south',border:false" style="height: 46%;">
        <div class="easyui-panel" title="最近凭证" style="padding: 10px;"
             data-options="iconCls:'fa fa-history',fit:true,closable:false,border:false,tools:'#tt2'"
                >
            <table id="latestVoucherTB">
                <tr>
                    <td style="color:#009966">日期</td>
                    <td style="color:#009966">凭证字号</td>
                    <td style="color:#009966">摘要</td>
                    <td style="text-align: right;color:#009966;">合计数</td>
                </tr>
            </table>
        </div>
        <div id="tt2">
            <#--<button title="查看" class="button button-circle button-tiny-18"><i class="fa fa-search"></i></button>-->
            <button title="刷新" onclick="East.refresh();" class="button button-circle button-tiny-18"><i class="fa fa-refresh fa-spin"></i></button>
        </div>
    </div>
</div>
<script>
    $(function(){
        East.init();
    });
</script>