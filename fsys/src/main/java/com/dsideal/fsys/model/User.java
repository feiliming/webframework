package com.dsideal.fsys.model;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.util.MD5Util;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 用户管理
 */
public class User extends Model<User>{

	private static final long serialVersionUID = -6215788653570590020L;
	
	private static final Logger log = LoggerFactory.getLogger(User.class);
	
	public static final User dao = new User();
	
	private static final Integer maxId = Db.queryInt("SELECT MAX(id) FROM sys_user");
	private static final AtomicInteger ai = new AtomicInteger(maxId == null ? 1 : maxId);

	public int generatorId() {
		return ai.incrementAndGet();
	}
	
	public Page<User> getUsers(int pageNumber, int pageSize, String sortOrder, String orgId, String searchValue) {
		StringBuilder sqlExceptSelect = new StringBuilder("FROM sys_user u,sys_organization o WHERE u.org_id = o.id ");
		if(StrKit.notBlank(orgId)){
			sqlExceptSelect.append(" AND org_id = " + orgId);
		}
		if(StrKit.notBlank(searchValue)){
			sqlExceptSelect.append(" AND (u.login_name LIKE '%" + searchValue + "%' OR u.real_name LIKE '%" + searchValue + "%') ");
		}
		sqlExceptSelect.append(" ORDER BY create_time " + sortOrder);
		
		return dao.paginate(pageNumber, pageSize, "SELECT u.id,u.login_name,u.real_name,u.gender,u.age,u.create_time,u.org_id,o.name AS org_name,u.disabled", sqlExceptSelect.toString());
	}
	
	/**
	 * 校验用户名和密码.
	 */
	public User checkLoginNameAndPassword(User user){
		log.info("校验用户名 {} 和密码 {}", user.getStr("login_name"), user.getStr("password") );
		return dao.findFirst("select * from sys_user where login_name = '" + user.getStr("login_name") + "' and password = '" + MD5Util.md5(user.getStr("password")) + "'");
	}
}
