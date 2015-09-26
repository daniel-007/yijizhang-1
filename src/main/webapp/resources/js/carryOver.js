/**
 * 结转损益的JS脚本文件。
 */
$(function () {
    var $btn_jzsy = $("#btn_jzsy");
    $btn_jzsy.click(function () {
        $("#default_win").window({
            title: '<i class="fa fa-list-alt"></i>结转本期损益',
            width: 600,
            height: 400,
            modal: true,
            collapsible: false,
            shadow: true,
            href: 'account/carryOver/main',
            onLoad: function () {
            }
        });
    });
});