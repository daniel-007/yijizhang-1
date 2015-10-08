Cashier=function(){
	return {
		init:function(){
			$('#carryOver').click(function(){Cashier.carryOver();});
			$('#cashier').click(function(){Cashier.cashier();});
		},
		carryOver:function(){
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
		},
		cashier:function(){
			
		}
	}
}();