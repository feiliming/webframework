<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>吉林省组织机构代码电子商务交易信用服务信息平台</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var login = function(){
		//$.messager.progress({
		//	title : '提示',
		//	text : '数据处理中，请稍后....'
		//});
		$('#loginForm').form('submit', {
			url: '${pageContext.request.contextPath}/admin/login',
			onSubmit: function(){
				//param.loginName = $;
				//param.password = ;
				var isValid = $('#loginForm').form('validate');
				if (!isValid){
					///$.messager.progress('close');
				}
				return isValid;
			},
			success: function(data){
				//$.messager.progress('close');
				alert($.parseJSON(data));
				alert(data.success);
				if(data.success){
					alert(11);
					self.location = '${pageContext.request.contextPath}/admin';
				} else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	};
</script>		
</head>
<body>
	<div align="center" style="padding-top: 200px;">
		<div id="loginPanel" class="easyui-panel" title="登录" style="width:500px;height:200px;">
			<div style="padding:40px 0 10px 125px" >
			    <form id="loginForm" method="post">
			    	<table>
			    		<tr>
			    			<td>用户名:</td>
			    			<td><input class="easyui-validatebox" type="text" name="loginName" id="loginName" data-options="required:true" value="admin" size="24"></input></td>
			    		</tr>
			    		<tr>
			    			<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
			    			<td><input class="easyui-validatebox" type="password" name="password" id="password" data-options="required:true" value="123456" size="25"></input></td>
			    		</tr>
			    	</table>
			    </form>
			</div>
			<div style="text-align:center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="login()" style="padding: 0px 10px 0px 10px;margin-right: 10px;">登录</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="padding: 0px 10px 0px 10px;">清除</a>
			</div>
		</div>
	</div>
</body>
</html>