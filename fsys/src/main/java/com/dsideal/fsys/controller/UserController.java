package com.dsideal.fsys.controller;

import java.util.Date;
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
