
/**
 * @author 鸵鸟
 * 
 * @requires jQuery
 * 
 * 当页面加载完毕关闭加载进度
 * **/
$(window).load(function(){
	$("#loading").fadeOut();
});

/**
 * @author 鸵鸟
 * 
 * @requires jQuery
 * 
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
function showok(){
	$.messager.show({
		title:'消息',
		msg:'操作成功!',
		showType:'show',
		timeout:2000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}
function shownok(){
	$.messager.show({
		title:'消息',
		msg:'操作失败!请重试，或联系管理员!',
		showType:'show',
		timeout:2000,
		style:{
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}