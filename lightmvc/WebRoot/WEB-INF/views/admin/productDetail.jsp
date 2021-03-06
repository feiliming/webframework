<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="padding: 3px;">
		<table class="grid">
			<tr>
				<td>组织机构代码</td>
				<td>${productinfo.code_id}</td>
			</tr>
			<tr>
				<td>组织机构名称</td>
				<td>${productinfo.code_name}</td>
			</tr>
			<tr>
				<td>产品名称</td>
				<td>${productinfo.product_name}</td>
			</tr>
			<tr>
				<td>产品类别</td>
				<td>${productinfo.product_class}</td>
			</tr>
			<tr>
				<td>常用名</td>
				<td>${productinfo.product_commonname}</td>
			</tr>
			<tr>
				<td>二维码</td>
				<td><img src="${ctx}${productinfo.twodimension}"/></td>
			</tr>
			<tr>
				<td>产品图片</td>
				<td>
				<c:if test="${productinfo.pimages != null and productinfo.pimages != '' and fn:length(productinfo.pimages) > 0}">
					<c:forEach var="pi" items="${productinfo.pimages}">
					<c:choose>
						<c:when test="${fn:contains(pi,'/UPLOADIMAGE/220300')}">
							<a href="${ctx}${fn:substring(pi,0,fn:length(pi)-7)}${fn:substring(pi,fn:length(pi)-4,fn:length(pi))}" target="_blank"><img src="${ctx}${pi}"></a>
						</c:when>
						<c:when test="${fn:contains(pi,'/UPLOADIMAGE/220381')}">
							<a href="${ctx}${fn:substring(pi,0,fn:length(pi)-7)}${fn:substring(pi,fn:length(pi)-4,fn:length(pi))}" target="_blank"><img src="${ctx}${pi}"></a>
						</c:when>
						<c:otherwise>
							暂无图片
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</c:if>
				<c:if test="${productinfo.pimages == null or productinfo.pimages == '' or fn:length(productinfo.pimages) == 0}">
					暂无图片
				</c:if>
				</td>
			</tr>
		</table>
		
	</div>
</div>