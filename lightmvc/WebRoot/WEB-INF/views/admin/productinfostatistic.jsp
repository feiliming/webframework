<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/fusionchart/FusionCharts.js" charset="utf-8"></script>
<meta http-equiv="X-UA-Compatible" content="edge" />

<title>组织机构查询</title>
	<script type="text/javascript">
	$(function() {
		//时间段内总体情况统计，查询条件为开始时间和结束时间
	   	var totalxml = "<graph caption='安全信息总体情况统计(时间段内)' xAxisName='统计类型' yAxisName='次数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='检查数'/><category name='隐患数'/>"+
	   	"<category name='事故数'/></categories>"+
	   	"<dataset><set color='588526' value='100'/><set color='F6BD0F' value='11'/><set color='9D080D' value='2'/>"+
	   	"</dataset>"+
	   	"</graph>";
		var chart = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "700", "265"); 
		chart.setDataXML(totalxml);		   
		chart.render("typediv"); 	
		//时间段内按检查项统计，查询条件为开始时间和结束时间
		var checkStandardxml = "<graph caption='安全信息按检查项统计(时间段内)' xAxisName='检查项' yAxisName='次数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='建筑安全'/><category name='教学设施'/><category name='消防安全'/><category name='水电气安全'/>"+
	   	"<category name='公共卫生安全'/><category name='食品卫生安全'/><category name='校舍安全'/><category name='自然灾害安全'/></categories>"+
	   	"<dataset seriesName='检查数' color='588526'><set value='110'/><set value='150'/><set value='110'/><set value='130'/><set value='119'/><set value='120'/><set value='115'/><set value='100'/></dataset>"+
	   	"<dataset seriesName='隐患数' color='B3AA00'><set value='20'/><set value='12'/><set value='15'/><set value='10'/><set value='8'/><set value='16'/><set value='14'/><set value='12'/></dataset>"+
	   	"<dataset seriesName='事故数' color='9D080D'><set value='8'/><set value='1'/><set value='3'/><set value='2'/><set value='1'/><set value='3'/><set value='1'/><set value='2'/></dataset>"+
	   	"</graph>";
		var chart1 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "700", "265"); 
		chart1.setDataXML(checkStandardxml);		   
		chart1.render("typediv1"); 
		//时间段内按地点统计，查询条件为开始时间和结束时间
		var positionxml = "<graph caption='安全信息按地点统计(时间段内)' xAxisName='地点' yAxisName='次数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='教学楼'/><category name='体育馆'/><category name='办公楼'/><category name='图书馆'/>"+
	   	"<category name='游泳馆'/><category name='食堂'/><category name='宿舍'/><category name='音乐厅'/><category name='实验楼'/><category name='车库'/></categories>"+
	   	"<dataset seriesName='检查数' color='588526'><set value='110'/><set value='150'/><set value='110'/><set value='130'/><set value='100'/><set value='90'/><set value='110'/><set value='120'/><set value='110'/><set value='125'/></dataset>"+
	   	"<dataset seriesName='隐患数' color='B3AA00'><set value='20'/><set value='12'/><set value='15'/><set value='10'/><set value='8'/><set value='16'/><set value='14'/><set value='12'/><set value='15'/><set value='10'/></dataset>"+
	   	"<dataset seriesName='事故数' color='9D080D'><set value='8'/><set value='1'/><set value='3'/><set value='2'/><set value='1'/><set value='3'/><set value='1'/><set value='2'/><set value='4'/><set value='2'/></dataset>"+
	   	"</graph>";
		var chart2 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "700", "265"); 
		chart2.setDataXML(positionxml);		   
		chart2.render("typediv2"); 
		//时间段内按检查员统计，查询条件为开始时间和结束时间
		var checkerxml = "<graph caption='安全信息按检查员统计(时间段内)' xAxisName='检查员' yAxisName='次数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='刘春香'/>"+
	   	"<category name='范少魁'/><category name='刘一年'/><category name='萧炎'/><category name='毕爽'/></categories>"+
	   	"<dataset seriesName='检查数' color='588526'><set value='130'/><set value='100'/><set value='90'/><set value='110'/><set value='120'/></dataset>"+
	   	"<dataset seriesName='隐患数' color='B3AA00'><set value='10'/><set value='8'/><set value='16'/><set value='14'/><set value='12'/></dataset>"+
	   	"<dataset seriesName='事故数' color='9D080D'><set value='2'/><set value='1'/><set value='3'/><set value='1'/><set value='2'/></dataset>"+
	   	"</graph>";
		var chart3 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "700", "265"); 
		chart3.setDataXML(checkerxml);		   
		chart3.render("typediv3"); 
	});

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
<body class="easyui-layout" data-options="fit:true,border:false" style="overflow: hidden;">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #f4f4f4">
		<form id="searchForm">
			<table>
				<tr>
					<th>组织机构代码:</th>
					<td><input name="code_id" placeholder="请输入组织机构代码"/></td>
					<th>组织机构名称:</th>
					<td><input name="code_cn" placeholder="请输入组织机构名称"/></td>
					<th>信用等级:</th>
					<td>
						<select name="creditlevel">
							<option value="ALL" selected="selected">--所有--</option>
							<option value="AAA">AAA</option>
							<option value="AA">AA</option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
						</select>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_cancel',plain:true" onclick="cleanFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div data-options="region:'center',border:false">
		<div id="typediv" align="center" style="display:" ></div>	
		<div id="typediv1" align="center" style="display:" ></div>	
		<div id="typediv2" align="center" style="display:" ></div>	
		<div id="typediv3" align="center" style="display:" ></div>	
	</div>
</body>
</html>