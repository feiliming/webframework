
/**
 * 当页面加载完毕关闭加载进度
 */
$(window).load(function(){
	$("#loading").fadeOut();
});

/**
 * 防止退格键导致页面回退
 */
$(document).keydown(function (e) { 
	var doPrevent; 
	if (e.keyCode == 8) { 
		var d = e.srcElement || e.target; 
		if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
			doPrevent = d.readOnly || d.disabled; 
		}else{
			doPrevent = true; 
		}
	}else{ 
		doPrevent = false; 
	}
	if (doPrevent) 
	e.preventDefault(); 
});

/**
 * show ok or nok messager
 */
function showok(message){
	$.messager.show({
		title:'消息',
		msg:message == null ? '操作成功！' : message,
		showType:'show',
		timeout:3000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}
function shownok(message){
	$.messager.show({
		title:'消息',
		msg:message == null ? '操作失败！请重试，或联系管理员！' : message,
		showType:'show',
		timeout:3000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}

$.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

function progressLoad(){  
    $("<div class=\"datagrid-mask\" style=\"position:absolute;z-index: 9999;\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" style=\"position:absolute;z-index: 9999;\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
}

function progressClose(){
	$(".datagrid-mask").remove();  
	$(".datagrid-mask-msg").remove();
}