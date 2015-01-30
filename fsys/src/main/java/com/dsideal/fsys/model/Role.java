package com.dsideal.fsys.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 角色Model
 */
public class Role extends Model<Role>{

	private static final long serialVersionUID = -8511214619720791276L;
	
	public static final Role dao = new Role();

	/**
	 * 角色分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param sortOrder 排序方式, asc或desc
	 * @return Page对象
	 */
	public Page<Role> getRoles(int pageNumber, int pageSize, String sortOrder) {
		return dao.paginate(pageNumber, pageSize, "select *", "from sys_role order by sequence " + sortOrder);
	}
	
}
