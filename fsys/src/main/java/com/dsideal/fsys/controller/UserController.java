package com.dsideal.fsys.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 
 * @author feilm220
 *
 */
public class UserController extends Controller {

	public void userList(){
		List<Record> userList = CacheKit.get("user", "userList");
		if(userList == null){
			userList = Db.find("select * from sys_user");
			CacheKit.put("user", "userList", userList);
		}
		renderJson(userList);
	}
}
