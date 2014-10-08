<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script>

	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/admin/index';
	}
		
	$(function() {
		
		$('#loginform').form({
		    url:'${ctx}/admin/login',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/admin/index';
		    	}else{
		    		$.messager.alert(
		    			'提示',
		    			'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			'error'
		    		);
		    	}
		    }
		});
	});
	function submitForm(){
		$('#loginform').submit();
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
</script>
</head>
<body>
<form id="loginform"  method="post">
<table id="__01" width="925" border="0" cellpadding="0" cellspacing="0" style="margin: 100px auto 0px;">
	<tr>
		<td>
			<img src="${ctx}/images/login_01.png" width="506" height="78" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_02.png" width="137" height="78" alt=""></td>
		<td>
			<img src="${ctx}/images/login_03.png" width="282" height="78" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_04.png" width="506" height="155" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_05.png" width="137" height="155" alt=""></td>
		<td>
			<img src="${ctx}/images/login_06.png" width="282" height="155" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_07.png" width="506" height="19" alt=""></td>
		<td colspan="2">
			<input class="easyui-validatebox" style="width: 137px;height: 19px;" type="text" name="loginname" data-options="required:true" value="admin"></input>
			</td>
		<td>
			<img src="${ctx}/images/login_09.png" width="282" height="19" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_10.png" width="506" height="14" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_11.png" width="137" height="14" alt=""></td>
		<td>
			<img src="${ctx}/images/login_12.png" width="282" height="14" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_13.png" width="506" height="19" alt=""></td>
		<td colspan="2">
			<input class="easyui-validatebox" style="width: 137px;height: 19px;" type="password" name="password" data-options="required:true" value="admin"></input></td>
		<td>
			<img src="${ctx}/images/login_15.png" width="282" height="19" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_16.png" width="506" height="13" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_17.png" width="137" height="13" alt=""></td>
		<td>
			<img src="${ctx}/images/login_18.png" width="282" height="13" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_19.png" width="506" height="20" alt=""></td>
		<td>
			<img src="${ctx}/images/login_20.png" width="73" height="20" onclick="submitForm();"></td>
		<td>
			<img src="${ctx}/images/login_21.png" width="64" height="20" alt=""></td>
		<td>
			<img src="${ctx}/images/login_22.png" width="282" height="20" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_23.png" width="506" height="180" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_24.png" width="137" height="180" alt=""></td>
		<td>
			<img src="${ctx}/images/login_25.png" width="282" height="180" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="${ctx}/images/login_26.png" width="506" height="24" alt=""></td>
		<td colspan="2">
			<img src="${ctx}/images/login_27.png" width="137" height="24" alt=""></td>
		<td>
			<img src="${ctx}/images/login_28.png" width="282" height="24" alt=""></td>
	</tr>
</table>
</form>
	</body>
</html>