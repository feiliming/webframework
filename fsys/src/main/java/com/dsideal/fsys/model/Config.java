package com.dsideal.fsys.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 系统配置
 * @author feilm220
 *
 */
public class Config extends Model<Config>{

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Config dao = new Config();

	/**
	 * 查询系统配置
	 * @return
	 */
	public Record getConfig(){
		return Db.findFirst("select * from sys_config");
	}
}
