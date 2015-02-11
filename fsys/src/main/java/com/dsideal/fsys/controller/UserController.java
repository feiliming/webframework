package com.dsideal.fsys.controller;

import java.util.Date;
import java.util.List;

import com.dsideal.fsys.bean.DataGrid;
import com.dsideal.fsys.model.Role;
import com.dsideal.fsys.model.User;
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
		int pageNumber = getParaToInt("page");
		int pageSize = getParaToInt("rows");
		String sortOrder = getPara("order");
		
		Page<User> users = User.dao.getUsers(pageNumber, pageSize, sortOrder);
		
		DataGrid<User> dataGrid = new DataGrid<User>();
		dataGrid.setTotal(users.getTotalRow());
		dataGrid.setRows(users.getList());
		
		renderJson(dataGrid);
	}
	
	/**
	 * user/addPage
	 */
	public void addPage() {
		setAttr("orgId", getPara("orgId"));
		render("orgAdd.html");
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
