package com.dsideal.fsys.controller;

import com.dsideal.fsys.model.Resource;
import com.dsideal.fsys.util.StringUtil;
import com.jfinal.core.Controller;

/**
 * 
 * @author feilm220
 *
 */
public class ResourceController extends Controller{

	public void getByPidAndResourceType(){
		String pid = getPara("pid");
		String resourceType = getPara("resource_type");
		renderJson(Resource.dao.getByPidAndResourceType(pid, resourceType));
	}
	
	/**
	 * tree一次性加载
	 */
	public void getTreeByResourceType(){
		String resourceType = getPara("resource_type");
		renderJson(Resource.dao.getTreeByResourceType(resourceType));
	}
	
	/**
	 * easyui的tree异步加载
	 * 子节点的加载依赖于父节点的状态。当展开一个封闭的节点，如果节点没有加载子节点，它将会把节点id的值作为http请求参数并命名为'id'，通过URL发送到服务器上面检索子节点。
	 */
	public void getTreeByPidAndResourceType(){
		String pid = getPara("pid");
		String id = getPara("id");
		if(!StringUtil.isNullOrEmpty(id)){
			pid = id;
		}
		String resourceType = getPara("resource_type");
		renderJson(Resource.dao.getTreeByPidAndResourceType(pid, resourceType));
	}
	
	public void index(){
		render("resource.html");
	}
}
