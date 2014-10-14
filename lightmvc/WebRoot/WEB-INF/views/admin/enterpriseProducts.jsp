<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
var epGrid;
$(function() {
	var eid = '<%=(String)request.getAttribute("eid") %>';
	epGrid = $('#epGrid').datagrid({
		url : '${ctx}' + '/enterpriseinfo/getByCodeId?eid=' + eid,
		columns : [ [ {
			width : '100',
			title : '产品名称',
			height : 220,
			field : 'product_name'
		} , {
			width : '80',
			title : '产品类别',
			height : 220,
			field : 'product_class'
		}, {
			width : '120',
			title : '常用名',
			height : 220,
			field : 'product_commonname'
		}, {
			width : '120',
			title : '执行标准ID',
			height : 220,
			field : 'standard_id'
		}, {
			width : '150',
			title : '执行标准名称',
			height : 220,
			field : 'standard_name'
		}, {
			width : '220',
			title : '二维码(扫描看产品详情)',
			field : 'twodimension',
			height : 220,
			formatter: function(value, row, index){
				return "<img src='${pageContext.request.contextPath}"+value+"'/>";
			}
		} ] ]
	});
		
});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false" title="" style="overflow: hidden;padding: 3px;">
		<table id="epGrid" data-options="fit:true"></table>
	</div>
</div>