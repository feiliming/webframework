package com.dsideal.fsys.model;

import com.jfinal.plugin.activerecord.Model;

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
	public Config getConfig(){
		return dao.findFirst("select * from sys_config");
	}
}
