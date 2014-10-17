package com.dsideal.fsys.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

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
	public List<Record> getByDictionaryType(String dictionaryType){
		return Db.find("SELECT * FROM sys_dictionary WHERE TYPE = ? ORDER BY sequence", dictionaryType);
	}
}
