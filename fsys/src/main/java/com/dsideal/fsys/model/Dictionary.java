package com.dsideal.fsys.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * 数据字典
 * @author feilm220
 *
 */
public class Dictionary extends Model<Dictionary>{

	private static final long serialVersionUID = 1L;
	
	public static final Dictionary dao = new Dictionary();

	/**
	 * 根据字典类型查询
	 * @param dictionaryType
	 * @return
	 */
	public List<Dictionary> getByDictionaryType(String dictionaryType){
		return dao.find("SELECT * FROM sys_dictionary WHERE TYPE = ? ORDER BY sequence", dictionaryType);
	}
}
