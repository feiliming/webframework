<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>吉林省组织机构代码电子商务交易信用服务信息平台</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/demo.js"></script>
</head>
<body class="easyui-layout">
    <!-- noscript -->
	<noscript>
		<div style="position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="${pageContext.request.contextPath}/resources/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<!-- loading -->
	<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
		<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
		    <img src="${pageContext.request.contextPath}/resources/images/loading.gif" align="middle" /> 正在加载中,请稍候...
		</div>
	</div>

    <!-- north -->
	<div data-options="region:'north',border:false,split:true" style="height:90px;padding:10px;background:url(${pageContext.request.contextPath}/resources/images/header.jpg)">
	    <span style="float:right; padding-right:20px;" class="head">欢迎 管理员 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
	</div>
	
	
	<div data-options="region:'west',split:true,title:'功能导航',iconCls:'icon-nav'" style="width:200px;">
		<div id="nav">
		</div>
	</div>
	
	<div data-options="region:'center'" style="overflow-y:hidden">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="欢迎使用"  data-options="iconCls:'icon-home'" style="padding:20px;overflow:hidden;">
				<h1 style="font-size:24px;">吉林省组织机构代码电子商务交易信用服务信息平台 V1.0</h1>
				<p style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;背景：以吉林省组织机构代码电子商务交易信用服务信息平台有利于促进供应链关联企业、物流企业产品质量和服务质量，促进网上安全认证体系等环境建设，推动电子商务各环节的全面应用与发展；有利于电子商务征信服务体系建设; 有利于促进电子商务服务体系的建设与完善，探索具有良好发展能力的社会化技术服务体系发展模式。</p>
				<p style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;目的：吉林省组织机构代码电子商务交易信用服务信息平台，主要是以资源共享为重点，面向企业、行业或区域服务的电子商务领域，建立以组织机构代码为“唯一身份”的所有生产企业的基本信息、产品质量信息（包括产品外型、标签、型号、规格、产品执行标准等产品相关信息），为电子商务行业政府有效监管，电子商务企业产品采购、产品销售和消费者购买产品提供准确、实时、快捷的真实性认证服务，成为有利于电子商务在企业信用、产品质量标准、认证方面良性发展的技术支撑体系,为电子商务活动形成社会化的信用体系提供优质、高效的信息资源，以提升电子商务技术和服务水平，提高电子商务信用管理水平。</p>
				<p style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;内容：组织机构管理、产品管理、用户管理、访问权限管理等功能。 </p>
				<p style="font-size:16px;">&nbsp;&nbsp;&nbsp;&nbsp;目标用户群：吉林省企事业单位。</p>
			</div>
		</div>
    </div>
	
	<div data-options="region:'east',split:true,collapsed:true,title:'日历'" style="width:270px;padding:10px;">
		<div class="easyui-calendar" style="width:250px;height:250px;"></div>
	</div>
	
	
	<div data-options="region:'south',border:false,split:true" style="height:40px;padding:10px;text-align: center;">
		@2014 吉林省组织机构代码电子商务交易信用服务信息平台 V1.0
	</div>
	
</body>
</html>