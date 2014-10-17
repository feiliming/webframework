<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/productinfo/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/productinfo/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>组织机构查询</title>
	<script type="text/javascript">
	var dataGrid;
	var dghead;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/productinfo/dataGrid',
			striped : true,
			//rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'pid',
			sortName : 'pid',
			sortOrder : 'asc',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '编号',
				field : 'pid',
				sortable : true
			},{
				width : '100',
				title : '机构代码',
				field : 'code_id',
				sortable : true
			}, {
				width : '200',
				title : '机构名称',
				field : 'code_name',
				sortable : true
			} , {
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
			}, {
				width : '200',
				title : '产品名称',
				field : 'product_name',
				sortable : true
			} , {
				width : '80',
				title : '产品类别',
				field : 'product_class',
				sortable : true
			}, {
				width : '120',
				title : '常用名',
				field : 'product_commonname',
				sortable : true
			}, {
				width : '120',
				title : '执行标准ID',
				field : 'standard_id',
				sortable : true
			}, {
				width : '150',
				title : '执行标准名称',
				field : 'standard_name',
				sortable : true
			}/**, {
				width : '80',
				title : '产品状态',
				field : 'product_status',
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
			}*/, {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					str += $.formatString('<a href="javascript:void(0)" onclick="compareFun(\'{0}\');" >加入对比</a>', row.pid);
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
		
		$('#dishi').combobox({ 
		    url:'${ctx}' + '/productinfo/getLevel2',
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
					url: '${ctx}' + '/productinfo/getLevel3?dishiid='+dishiid,
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
		
		dghead = $('#dghead').datagrid({    
		    url:'', 
		    //rownumbers: true,
		    idField : 'pid',
		    columns:[[    
		        {field:'pid',title:'编号',width:50},    
		        {field:'code_id',title:'机构代码',width:100},    
		        {field:'code_name',title:'机构名称',width:200},    
		        {field:'creditlevel',title:'信用等级',width:80,
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
				}},
		        {field:'product_name',title:'产品名称',width:120},    
		        {field:'product_class',title:'产品类别',width:80},    
		        {field:'product_commonname',title:'常用名',width:120},    
		        {field:'standard_id',title:'执行标准ID',width:120},    
		        {field:'standard_name',title:'执行标准名称',width:150},
		        {field:'pimages',title:'产品图片',width:220},
		        {field:'twodimension',title:'二维码',width:110},
		        {field:'action',title:'操作',width:100}
		    ]]    
		});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${ctx}/productinfo/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#productinfoAddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(pid) {
		if (pid == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			pid = rows[0].pid;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前机构？', function(b) {
			if (b) {
				var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
				if (currentUserId != pid) {
					progressLoad();
					$.post('${ctx}/productinfo/delete', {
						pid : pid
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
	
	function editFun(pid) {
		if (pid == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			pid = rows[0].pid;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${ctx}/productinfo/editPage?pid=' + pid,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#productinfoEditForm');
					f.submit();
				}
			} ]
		});
	}
	
	function compareFun(pid){
		if (pid == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			pid = rows[0].pid;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		//parent.$.messager.confirm('询问', '您确认要加入对比？', function(b) {
		//	if (b) {
				$.ajax({
					type: "POST",
					url: '${ctx}' + '/productinfo/getImgs?pid='+pid,
					async: false,
					success: function(result){
						var jdata = $.parseJSON(result);
						var twod = '<img src=${ctx}'+jdata.twodimension+'>';
						var temp = jdata.pimages + "";
						var pimgstemp = "";
						if(temp != null && temp != ""){
							var pimgs = temp.split(",");
							for(var i=0;i<pimgs.length;i++){
								pimgstemp = pimgstemp + '<a href=${ctx}'+pimgs[i].substr(0,pimgs[i].length-7)+pimgs[i].substr(-4)+' target=blank><img src=${ctx}'+pimgs[i]+'></a>';
							}
						}
						$('#dghead').datagrid('appendRow',{
							pid:jdata.pid,
							code_id: jdata.code_id,
							code_name: jdata.code_name,
							creditlevel: jdata.creditlevel,
							product_name: jdata.product_name,
							product_class: jdata.product_class,
							product_commonname: jdata.product_commonname,
							standard_id: jdata.standard_id,
							standard_name: jdata.standard_name,
							pimages: $.formatString(pimgstemp),
							twodimension: $.formatString(twod),
							action: $.formatString('<a href="javascript:void(0)" onclick="delcompareFun(\'{0}\');" >删除对比</a>', pid)
						});
					},
					error: function(){alert('出错了!');}
				});
		//	}
		//});
	}
	function delcompareFun(pid){
		if (pid == undefined) {//点击右键菜单才会触发这个
			var rows = dghead.datagrid('getSelections');
			pid = rows[0].pid;
		} else {//点击操作里面的删除图标会触发这个
			dghead.datagrid('unselectAll').datagrid('uncheckAll');
		}
		var index = $('#dghead').datagrid('getRowIndex',pid);
		$('#dghead').datagrid('deleteRow',index);
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	
	function cleanFun() {
		$('#searchForm input').val('');
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
					<th>产品名称:</th>
					<td><input name="product_name" placeholder="请输入组织机构名称"/>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_cancel',plain:true" onclick="cleanFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>	
	
	<div data-options="region:'center',border:false" style="height:400px;">
		<table id="dataGrid"></table>
	</div>
	
	<div id="south" data-options="region:'south',border:true" style="height:400px;">
		<table id="dghead">   
		</table> 
	</div>
	<!-- 
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/productinfo/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon_add'">添加</a>
		</c:if>
	</div> -->
</body>
</html>