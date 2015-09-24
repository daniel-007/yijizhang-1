/**
 * 新建账套的JS脚本文件。
 */
$(function () {

    var $btn_xjzt = $("#btn_xjzt");
    alert(1);
    $btn_xjzt.click(function () {
        $("#default_win").window({
            title: '<i class="fa fa-list-alt"></i>新建账套',
            width: 700,
            height: 500,
            modal: true,
            collapsible: false,
            shadow: true,
            href: 'account/book/main',
            onLoad: function () {
            }
        });
    })

});