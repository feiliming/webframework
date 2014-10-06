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

<title>组织机构统计</title>
	<script type="text/javascript">
	$(function() {
		//////////////////
	   	//var totalxml = "<graph caption='组织机构按类型统计' xAxisName='宽城区' yAxisName='次数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	//"<categories><category name='企业'/><category name='事业单位'/><category name='社团'/><category name='机关法人'/><category name='民办非企业单位'/>"+
	   	//"<category name='个体'/><category name='工会'/></categories>"+
	   	//"<dataset><set color='C6EDFF' value='440'/><set color='FFD42C' value='840'/><set color='9DCA14' value='650'/><set color='D37A43' value='490'/><set color='21A9A9' value='655'/><set color='EE6464' value='980'/><set color='A25DA2' value='930'/>"+
	    //"</dataset>"+
	   	//"</graph>";
		//var chart = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "900", "350"); 
		//chart.setDataXML(totalxml);		   
		//chart.render("typediv"); 	
		///////////////////
		/**
		var participantPiexml = "<graph  caption='组织机构各区分布情况' baseFontSize='12' showNames='1' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<set name='宽城区' value='180' isSliced='1'/><set name='南关区' value='120'/><set name='朝阳区' value='170'/><set name='德惠市' value='80'/>"+
	   	"<set name='榆树市' value='120'/><set name='九台市' value='50'/><set name='农安县' value='50'/><set name='双阳区' value='100'/>"+
	   	"<set name='绿园区' value='80'/>"+
	   	"</graph>";
		var chart1 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/Pie3D.swf", "ChartId", "900", "350");
		chart1.setDataXML(participantPiexml);		   
		chart1.render("typediv1"); 
		///////////////////////
		var positionxml = "<graph caption='组织机构信用度排名前十' xAxisName='组织机构' yAxisName='分数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='吉林省组织机构代码管理中心'/><category name='吉林省工商行政管理局'/><category name='吉林省事业单位登记管理局'/><category name='扶余县双春纯净水有限公司'/><category name='吉林市棋盘粮油有限公司'/>"+
	   	"<category name='吉林兴龙薯业有限公司'/><category name='集安市恒益纺织有限公司'/><category name='白城市宏达化工产品有限公司'/><category name='吉林市汇缘包装制品有限公司'/><category name='吉林兆丰农业开发有限公司'/></categories>"+
	   	"<dataset><set color='C6EDFF' value='99'/><set color='FFD42C' value='95'/><set color='9DCA14' value='92'/><set color='D37A43' value='88'/><set color='21A9A9' value='83'/><set color='EE6464' value='80'/><set color='A25DA2' value='75'/><set color='A25DA2' value='72'/><set color='A25DA2' value='71'/><set color='A25DA2' value='65'/>"+
	   	"</dataset>"+
	   	"</graph>";
		var chart2 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSBar3D.swf", "ChartId", "900", "350"); 
		chart2.setDataXML(positionxml);		   
		chart2.render("typediv2");
		///////////////////////////
		var OrgRiskMonthLinexml = "<graph  caption='组织机构历史变化情况' xAxisName='年份' yAxisName='分数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>"+
	   	"<categories><category name='2008'/><category name='2009'/><category name='2010'/><category name='2011'/><category name='2012'/><category name='2013'/>"+
	   	"<category name='2014'/></categories>"+
	   	"<dataset seriesName='吉林省工商行政管理局'color='588526'><set value='80'/><set value='85'/><set value='90'/><set value='82'/><set value='80'/><set value='89'/><set value='98'/></dataset>"+
	   	"</graph>";
		var chart3 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSLine.swf", "ChartId", "900", "350");
		chart3.setDataXML(OrgRiskMonthLinexml);		   
		chart3.render("typediv3");
		**/

		$.ajax({
			type: "POST",
			url: '${ctx}' + '/enterpriseinfo/statisticByCreditlevel',
			async: false,
			success: function(result){
				var jdata = $.parseJSON(result);
				
				var chart = new FusionCharts("${pageContext.request.contextPath}/fusionchart/MSColumn3D.swf", "ChartId", "900", "350"); 
				chart.setDataXML(jdata.obj);		   
				chart.render("typediv"); 
				
				var chart1 = new FusionCharts("${pageContext.request.contextPath}/fusionchart/Pie3D.swf", "ChartId", "900", "350");
				chart1.setDataXML(jdata.msg);		   
				chart1.render("typediv1"); 
			},
			error: function(){alert('出错了!');}
		});
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
<!-- 
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #f4f4f4" align="center">
		<form id="searchForm">
			<table>
				<tr>
					<th>地市:</th>
					<td>
						<select id="dishi" name="dishi" style="width:160px; border: 1px solid #ccc"> </select>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_search',plain:true" onclick="searchFun();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon_cancel',plain:true" onclick="cleanFun();">清空</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
 -->	
	<div data-options="region:'center',border:false">
		<div id="typediv" align="center" style="display:" ></div>	
		<div id="typediv1" align="center" style="display:" ></div>	
		<div id="typediv2" align="center" style="display:" ></div>	
		<div id="typediv3" align="center" style="display:" ></div>	
	</div>
</body>
</html>