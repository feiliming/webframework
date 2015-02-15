package com.dsideal.fsys.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dsideal.fsys.bean.ResourceBean;
import com.dsideal.fsys.model.Resource;
import com.dsideal.fsys.util.StringUtil;
import com.jfinal.core.Controller;

public class ResourceController extends Controller{
	
	/**
	 * resource
	 */
	public void index(){
		render("resource.html");
	}
	
	/**
	 * resource/getResources
	 */
	public void getResources(){
		List<ResourceBean> listb = new ArrayList<ResourceBean>();
		List<Resource> list = Resource.dao.getResources();
		for(Resource r : list){
			ResourceBean rb = new ResourceBean();
			rb.setId(r.getInt("id"));
			rb.setName(r.getStr("name"));
			rb.setUrl(r.getStr("url"));
			rb.setIconCls(r.getStr("icon_class"));
			rb.setSequence(r.getInt("sequence"));
			rb.setType(r.getStr("type"));
			rb.setPid(r.getInt("pid") == null ? -1 : r.getInt("pid"));
			listb.add(rb);
		}
		renderJson(listb);
	}
	
	/**
	 * resource/addPage
	 */
	public void addPage(){
		render("resourceAdd.html");
	}
	
	/**
	 * resource/add
	 */
	public void add(){
		Resource resource = getModel(Resource.class);
		resource.set("id", Resource.dao.generatorId());
		if(resource.getInt("pid") == null){
			resource.set("pid", -1);
		}
		
		boolean b = resource.save();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}
	
	/**
	 * resource/editPage
	 */
	public void editPage(){
		String id = getPara("id");
		setAttr("resource", Resource.dao.findById(id));
		render("resourceEdit.html");
	}
	
	/**
	 * resource/edit
	 */
	public void edit(){
		Resource resource = getModel(Resource.class);
		if(resource.getInt("pid") == null){
			resource.set("pid", -1);
		}
		
		boolean b = resource.update();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}
	
	/**
	 * resource/delete
	 */
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
	
	public void getMenus() {
		String pid = getPara("pid");
		renderJson(Resource.dao.getResources("menu", pid));
	}

//	public void getResourceList(){
//		String pid = getPara("pid");
//		String resourceType = getPara("resource_type");
//		renderJson(Resource.dao.getResourceList2(pid, resourceType));
//	}
	
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
		renderJson(Resource.dao.getResourceTreeList(pid, resourceType));
	}
}
