package com.dsideal.fsys.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dsideal.fsys.bean.DataGrid;
import com.dsideal.fsys.model.User;
import com.dsideal.fsys.util.MD5Util;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 用户Controller
 */
public class UserController extends Controller {
	
	/**
	 * user
	 */
	public void index() {
		render("user.html");
	}
	
	/**
	 * user/getUsers
	 */
	public void getUsers() {
		String org_id = getPara("org_id");
		int pageNumber = getParaToInt("page");
		int pageSize = getParaToInt("rows");
		String sortOrder = getPara("order");
		String searchValue = getPara("searchValue");
		
		Page<User> users = User.dao.getUsers(pageNumber, pageSize, sortOrder, org_id, searchValue);
		
		DataGrid<User> dataGrid = new DataGrid<User>();
		dataGrid.setTotal(users.getTotalRow());
		dataGrid.setRows(users.getList());
		
		renderJson(dataGrid);
	}
	
	/**
	 * user/addPage
	 */
	public void addPage() {
		render("userAdd.html");
	}
	
	/**
	 * user/add
	 */
	public void add() {
		User user = getModel(User.class);
		user.set("id", User.dao.generatorId());
		user.set("password", MD5Util.md5("123456"));
		user.set("create_time", new Date());
		
		boolean b = user.save();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}
	
	/**
	 * user/editPage
	 */
	public void editPage() {
		String id = getPara("id");
		setAttr("user", User.dao.findById(id));
		render("userEdit.html");
	}
	
	/**
	 * user/edit
	 */
	public void edit() {
		User user = getModel(User.class);
		
		boolean b = user.update();
		
		JSONObject result = new JSONObject();
		result.put("success", b);
		
		renderJson(result);
	}

	public void userList(){
		List<Record> userList = CacheKit.get("user", "userList");
		if(userList == null){
			userList = Db.find("select * from sys_user");
			CacheKit.put("user", "userList", userList);
		}
		
		List<Record> tl = Db.find("select * from sys_user");
		for(Record r : tl){
			String id = String.valueOf(r.getInt("id"));
			Date ct = r.getTimestamp("create_time");
			System.out.println(id);
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(ct);
		}
		renderJson(userList);
	}
}
