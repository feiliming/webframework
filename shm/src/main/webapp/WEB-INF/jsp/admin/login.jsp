<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>吉林省组织机构代码电子商务交易信用服务信息平台</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div align="center" style="padding-top: 200px;">
		<div id="loginPanel" class="easyui-panel" title="登录" style="width:500px;height:200px;">
			<div style="padding:40px 0 10px 125px" >
			    <form id="loginform"  method="post">
			    	<table>
			    		<tr>
			    			<td>用户名:</td>
			    			<td><input class="easyui-validatebox" type="text" name="loginName" data-options="required:true" value="admin" size="24"></input></td>
			    		</tr>
			    		<tr>
			    			<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
			    			<td><input class="easyui-validatebox" type="password" name="password" data-options="required:true" value="123456" size="25"></input></td>
			    		</tr>
			    	</table>
			    </form>
			</div>
			<div style="text-align:center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="padding: 0px 10px 0px 10px;margin-right: 10px;">登录</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="padding: 0px 10px 0px 10px;">清除</a>
			</div>
		</div>
	</div>
</body>
</html>