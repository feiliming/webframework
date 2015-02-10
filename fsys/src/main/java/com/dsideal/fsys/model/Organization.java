package com.dsideal.fsys.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

/**
 * 组织机构Model
 */
public class Organization extends Model<Organization>{

	private static final long serialVersionUID = -4132317050698195537L;

	private static final Integer maxId = Db.queryInt("SELECT MAX(id) FROM sys_organization");
	private static final AtomicInteger ai = new AtomicInteger(maxId == null ? 1 : maxId);

	public int generatorId() {
		return ai.incrementAndGet();
	}
	
	public static Organization dao = new Organization();
	
	public List<Organization> getOrganizations() {
		String sql = "select * from sys_organization order by sequence asc";
		return find(sql);
	}
}
