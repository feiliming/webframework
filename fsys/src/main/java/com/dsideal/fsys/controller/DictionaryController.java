package com.dsideal.fsys.controller;

import com.dsideal.fsys.model.Dictionary;
import com.jfinal.core.Controller;

/**
 * 
 * @author feilm220
 *
 */
public class DictionaryController extends Controller{

	public void getByDictionaryType(){
		String dicType = getPara("dictionary_type");
		renderJson(Dictionary.dao.getByDictionaryType(dicType));
	}
}
