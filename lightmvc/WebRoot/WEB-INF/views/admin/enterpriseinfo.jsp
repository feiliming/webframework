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
<title>组织机构查询</title>
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
			pageSize : 30,
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
			} ,{
				width : '80',
				title : '准入行政区Id',
				field : 'zrxzqh_id',
				sortable : false
			}, {
				width : '200',
				title : '准入行政区',
				field : 'zrxzqh_name',
				sortable : false
			}, {
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
				title : '信用等级',
				field : 'creditlevel',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 'AAA':
						return '★★★★★';
					case 'AA':
						return '★★★★';	
					case 'A':
						return '★★★';	
					case 'B':
						return '★★';	
					case 'C':
						return '★';	
					}
				}
			}] ],
			toolbar : '#toolbar'
		});
		
		$('#dishi').combobox({ 
		    url:'${ctx}' + '/enterpriseinfo/getLevel2',
		    editable:false, //不可编辑状态
		    cache: false,
		    panelHeight: 'auto',//自动高度适合
		    valueField:'zrxzqh_id',
		    textField:'zrxzqh_name',
		    
		    onSelect: function(re){
			    $("#quxian").combobox("setValue",'');
				var dishiid = $('#dishi').combobox('getValue');		
				$.ajax({
					type: "POST",
					url: '${ctx}' + '/enterpriseinfo/getLevel3?dishiid='+dishiid,
					cache: false,
					dataType : "json",
					success: function(data){
						$("#quxian").combobox("loadData",data);
					}
				}); 	
			}
		}); 
		
		$('#quxian').combobox({ 
			//url:'itemManage!categorytbl', 
			editable:false, //不可编辑状态
			cache: false,
			panelHeight: 'auto',//自动高度适合
			valueField:'zrxzqh_id',   
			textField:'zrxzqh_name'
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

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	
	function cleanFun() {
		$('#searchForm input').val('');
		$('#searchForm select').val('ALL');
		dataGrid.datagrid('load', {});
	}
	</script>
</head>
<body  class="easyui-layout" data-options="fit:true,border:false" style="overflow: hidden;">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #f4f4f4">
		<form id="searchForm">
			<table>
				<tr>
					<th>地市:</th>
					<td><select id="dishi" name="dishi" style="width:160px; border: 1px solid #ccc"> </select></td>
					<th>区县:</th>
					<td><select id="quxian" name="quxian" style="width:160px; border: 1px solid #ccc"> </select></td>
					<th>机构代码:</th>
					<td><input name="code_id" placeholder="请输入组织机构代码"/></td>
					<th>机构名称:</th>
					<td><input name="code_cn" placeholder="请输入组织机构名称"/></td>
					<th>信用等级:</th>
					<td>
						<select name="creditlevel">
							<option value="ALL" selected="selected">--所有--</option>
							<option value="AAA">AAA(★★★★★)</option>
							<option value="AA">AA(★★★★)</option>
							<option value="A">A(★★★)</option>
							<option value="B">B(★★)</option>
							<option value="C">C(★)</option>
						</select>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_cancel',plain:true" onclick="cleanFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
	</div>
	
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/enterpriseinfo/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	</div>
</body>
</html>