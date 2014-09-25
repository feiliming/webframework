<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(function() {
		
		$('#productinfoEditForm').form({
			url : '${ctx}/productinfo/edit',
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
		
		
		$("#memo").val('${productinfo.memo}');
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="productinfoEditForm" method="post">
			<table class="grid">
				<tr>
					<td>组织机构代码</td>
					<td><input name="pid" type="hidden"  value="${productinfo.pid}">
					<td><input name="code_id" type="hidden"  value="${productinfo.code_id}">
					<input name="product_name" type="text" placeholder="请输入产品名称" class="easyui-validatebox" data-options="required:true" size="40" value="${productinfo.product_name}"></td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="3"><textarea id="memo" name="memo" rows="" cols="" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>