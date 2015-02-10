package com.dsideal.fsys.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 角色Model
 */
public class Role extends Model<Role>{

	private static final long serialVersionUID = -8511214619720791276L;
	
	public static final Role dao = new Role();
	
	//AtomicInteger线程安全, 以原子方式加减1, 类第一次加载的时候执行初始化
	private static final Integer maxId = Db.queryInt("SELECT MAX(id) FROM sys_role");
	private static final AtomicInteger ai = new AtomicInteger(maxId == null ? 1 : maxId);

	public int getId() {
		return ai.incrementAndGet();
	}
	
	public Page<Role> getRoles(int pageNumber, int pageSize, String sortOrder) {
		return dao.paginate(pageNumber, pageSize, "select *", "from sys_role order by sequence " + sortOrder);
	}
	
}
