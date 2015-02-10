package com.dsideal.fsys.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.util.MD5Util;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 用户管理
 */
public class User extends Model<User>{

	private static final long serialVersionUID = -6215788653570590020L;
	
	private static final Logger log = LoggerFactory.getLogger(User.class);
	
	public static final User dao = new User();
	
	private static final Integer maxId = Db.queryInt("SELECT MAX(id) FROM sys_user");
	private static final AtomicInteger ai = new AtomicInteger(maxId == null ? 1 : maxId);

	public int getId() {
		return ai.incrementAndGet();
	}
	
	/**
	 * 校验用户名和密码.
	 */
	public User checkLoginNameAndPassword(User user){
		log.info("校验用户名 {} 和密码 {}", user.getStr("login_name"), user.getStr("password") );
		return dao.findFirst("select * from sys_user where login_name = '" + user.getStr("login_name") + "' and password = '" + MD5Util.md5(user.getStr("password")) + "'");
	}
}
