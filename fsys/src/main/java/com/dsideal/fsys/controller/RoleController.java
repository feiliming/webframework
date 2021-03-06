package com.dsideal.fsys.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsideal.fsys.bean.DataGrid;
import com.dsideal.fsys.model.Role;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * 角色Controller
 */
public class RoleController extends Controller{
	
	/**
	 * role
	 */
	public void index() {
		render("role.html");
	}

	/**
	 * role/getRole
	 */
	public void getRoles() {
		int pageNumber = getParaToInt("page");
		int pageSize = getParaToInt("rows");
		String sortOrder = getPara("order");
		
		Page<Role> roles = Role.dao.getRoles(pageNumber, pageSize, sortOrder);
		
		DataGrid<Role> dataGrid = new DataGrid<Role>();
		dataGrid.setTotal(roles.getTotalRow());
		dataGrid.setRows(roles.getList());
		
		renderJson(dataGrid);
	}
	
	/**
	 * role/addPage
	 */
	public void addPage() {
		render("roleAdd.html");
	}
	
	/**
	 * role/add
	 */
	public void add() {
		Role role = getModel(Role.class);
		role.set("id", Role.dao.generatorId());
		
		boolean b = role.save();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}
	
	/**
	 * role/editPage
	 */
	public void editPage() {
		String id = getPara("id");
		setAttr("role", Role.dao.findById(id));
		render("roleEdit.html");
	}
	
	/**
	 * role/edit
	 */
	public void edit() {
		Role role = getModel(Role.class);
		
		boolean b = role.update();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}
	
	/**
	 * TODO 删除角色时判断人和角色关系、角色和权限关系
	 * role/delete
	 */
	public void delete() {
		String id = getPara("id");
		
		boolean b = Role.dao.deleteById(id);
		
		renderText(String.valueOf(b));
	}
	
}
