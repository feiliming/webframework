package com.dsideal.fsys.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dsideal.fsys.bean.ZTree;
import com.dsideal.fsys.model.Organization;
import com.jfinal.core.Controller;

public class OrganizationController extends Controller{

	/**
	 * org
	 */
	public void index() {
		render("org.html");
	}
	
	/**
	 * org/getOrgs
	 */
	public void getOrgs() {
		List<Organization> olist = Organization.dao.getOrganizations();
		List<ZTree> tlist = new ArrayList<ZTree>();
		for(Organization org : olist) {
			ZTree zt = new ZTree();
			zt.setId(org.getInt("id"));
			zt.setPId(org.getInt("pid"));
			zt.setName(org.getStr("name"));
			tlist.add(zt);
		}
		renderJson(tlist);
	}
	
	/**
	 * org/addPage
	 */
	public void addPage() {
		setAttr("pId", getPara("pId"));
		render("orgAdd.html");
	}
	
	/**
	 * org/add
	 */
	public void add() {
		Organization org = getModel(Organization.class);
		org.set("id", Organization.dao.generatorId());
		
		boolean b = org.save();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		if(b) {
			ZTree zt = new ZTree();
			zt.setId(org.getInt("id"));
			zt.setName(org.getStr("name"));
			zt.setPId(org.getInt("pid"));
			result.put("treeNode", zt);
		}
		renderJson(result);
	}
	
	/**
	 * org/editPage
	 */
	public void editPage() {
		String id = getPara("id");
		setAttr("organization", Organization.dao.findById(id));
		render("orgEdit.html");
	}
	
	/**
	 * org/edit
	 */
	public void edit() {
		Organization org = getModel(Organization.class);
		
		boolean b = org.update();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		result.put("name", org.getStr("name"));
		
		renderJson(result);
	}
	
}
