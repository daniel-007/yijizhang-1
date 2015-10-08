TopBar = function(){

    return {

        bindEvent: function () {
            //绑定事件
            $('#switchBtn').click(function(){
                $('#accountBookList').combobox({
                    url:'account/book/list',
                    valueField:'id',
                    textField:'bookName',
                    editable:false,
                    onSelect: function(rec){
                        if(rec.id == $('#currentAccountBookId').val()){
                            $('#confirmSwitchBtn').attr('disabled','disabled');
                        }
                    },
                    onLoadSuccess:function(){
                        $('#accountBookList').combobox('setValue', $('#currentAccountBookId').val());
                        $('#busyIcon').hide();
                        $('#confirmSwitchBtn').show();
                        $('#cancelSwitchBtn').show();
                    }
                });
                $('.top-bar .info .textbox-text').css({'padding-top':'3px'});
                $("#accountBookList + .combo").show();
                $('#switchBtn').hide();
                $('#busyIcon').show();
            });

            $('#confirmSwitchBtn').click(function(){
                var id = $('#accountBookList').combobox('getValue');
                if(id != $('#currentAccountBookId').val()){
                    $(this).hide();
                    $('#cancelSwitchBtn').hide();
                    $('#busyIcon').show();
                    $.ajax({
                        url: "switch/to/book/" + id,
                        context: document.body,
                        success:function(data){
                            if(data){
                                document.location.reload();
                            }else{
                                $('#busyIcon').hide();
                                $(this).show();
                                $('#cancelSwitchBtn').show();
                                $.messager.alert("提示信息", "切换账套失败，请稍候重试！","error");
                            }
                        }
                    });
                }
            });

            $('#cancelSwitchBtn').click(function(){
                $("#accountBookList + .combo").hide();
                $('#confirmSwitchBtn').hide();
                $('#cancelSwitchBtn').hide();
                $('#switchBtn').show();
            });
        }
    };

}();