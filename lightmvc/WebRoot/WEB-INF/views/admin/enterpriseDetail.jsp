<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(function() {
		
		$('#enterpriseinfoEditForm').form({
			url : '${ctx}/enterpriseinfo/edit',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		
		$("#description").val('${enterpriseinfo.code_cn}');
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<table class="grid">
			<tr>
				<td>组织机构代码</td>
				<td>${enterprise.code_id}</td>
			</tr>
			<tr>
				<td>组织机构名称</td>
				<td>${enterprise.code_cn}</td>
			</tr>
			<tr>
				<td>所属行政区ID</td>
				<td>${enterprise.zrxzqh_id}</td>
			</tr>
			<tr>
				<td>所属行政区名称</td>
				<td>${enterprise.zrxzqh_name}</td>
			</tr>
			<tr>
				<td>行业ID</td>
				<td>${enterprise.industry_id}</td>
			</tr>
			<tr>
				<td>地址</td>
				<td>${enterprise.addressname}</td>
			</tr>
			<tr>
				<td>邮编</td>
				<td>${enterprise.postcode}</td>
			</tr>
			<tr>
				<td>电话</td>
				<td>${enterprise.tel}</td>
			</tr>
			<tr>
				<td>联系人</td>
				<td>${enterprise.linkman}</td>
			</tr>
			<tr>
				<td>信用等级</td>
				<td>${enterprise.creditlevel}</td>
			</tr>
		</table>
	</div>
</div>