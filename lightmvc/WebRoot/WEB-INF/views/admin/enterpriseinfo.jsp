<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/enterpriseinfo/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/enterpriseinfo/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>角色管理</title>
	<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/enterpriseinfo/dataGrid',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'code_id',
			sortName : 'code_id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '机构代码',
				field : 'code_id',
				sortable : true
			}, {
				width : '200',
				title : '名称',
				field : 'code_cn',
				sortable : true
			} , {
				width : '80',
				title : '经济行业',
				field : 'industry_id',
				sortable : true
			}, {
				width : '200',
				title : '地址',
				field : 'addressname',
				sortable : true
			}, {
				width : '80',
				title : '邮编',
				field : 'postcode',
				sortable : true
			}, {
				width : '100',
				title : '电话',
				field : 'tel',
				sortable : true
			}, {
				width : '80',
				title : '状态',
				field : 'status',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '00':
						return '未审核';
					case '01':
						return '审核未通过';	
					case '10':
						return '未上报';	
					case '12':
						return '已上报';	
					case '13':
						return '已删除';	
					}
				}
			}, {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					//if(row.isdefault!=0){
						//str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						if ($.canEdit) {
							str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.code_id);
						}
						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
						if ($.canDelete) {
							str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>', row.code_id);
						}
					///}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${ctx}/enterpriseinfo/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#enterpriseinfoAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(code_id) {
		if (code_id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			code_id = rows[0].code_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前机构？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != code_id) {
					progressLoad();
					$.post('${ctx}/enterpriseinfo/delete', {
						code_id : code_id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}
	
	function editFun(code_id) {
		if (code_id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			code_id = rows[0].code_id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${ctx}/enterpriseinfo/editPage?code_id=' + code_id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#enterpriseinfoEditForm');
					f.submit();
				}
			} ]
		});
	}
	
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/enterpriseinfo/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	</div>
</body>
</html>