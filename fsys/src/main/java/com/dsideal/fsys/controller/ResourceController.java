package com.dsideal.fsys.controller;

import java.util.ArrayList;
import java.util.List;

import com.dsideal.fsys.model.Resource;
import com.dsideal.fsys.util.StringUtil;
import com.jfinal.core.Controller;

/**
 * 
 * @author feilm220
 *
 */
public class ResourceController extends Controller{

	public void getResourceList(){
		String pid = getPara("pid");
		String resourceType = getPara("resource_type");
		String disable = getPara("disable");
		renderJson(Resource.dao.getResourceList(pid, resourceType, disable));
	}
	
	/**
	 * tree异步加载
	 * 子节点的加载依赖于父节点的状态。当展开一个封闭的节点，如果节点没有加载子节点，它将会把节点id的值作为http请求参数并命名为'id'，通过URL发送到服务器上面检索子节点。
	 */
	public void getResourceTreeList(){
		String pid = getPara("pid");
		String id = getPara("id");
		if(!StringUtil.isNullOrEmpty(id)){
			pid = id;
		}
		String resourceType = getPara("resource_type");
		String disable = getPara("disable");
		renderJson(Resource.dao.getResourceTreeList(pid, resourceType, disable));
	}
	
	public void index(){
		render("resource.html");
	}
	
	public void addPage(){
		render("resourceAdd.html");
	}
	
	public void add(){
		if(Resource.dao.addResource(getModel(Resource.class))){
			renderText("ok");
		}else{
			renderText("nok");
		}
	}
	
	public void editPage(){
		setAttr("id", getPara("id"));
		render("resourceEdit.html");
	}
	
	public void getResource(){
		String id = getPara("id");
		renderJson("resource", Resource.dao.findById(id));
	}
	
	public void edit(){
		if(Resource.dao.editResource(getModel(Resource.class))){
			renderText("ok");
		}else{
			renderText("nok");
		}
	}
	
	public void delete(){
		String ids = getPara("ids");
		List<String> idlist = new ArrayList<String>();
		for(String id : ids.split(",")){
			idlist.add(id);
		}
		if(Resource.dao.deleteResource(idlist)){
			renderText("ok");
		}else{
			renderText("nok");
		}
	}
}
