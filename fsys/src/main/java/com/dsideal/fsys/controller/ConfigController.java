package com.dsideal.fsys.controller;

import com.dsideal.fsys.model.Config;
import com.jfinal.core.Controller;

/**
 * 系统配置
 * @author feilm220
 *
 */
public class ConfigController extends Controller{
	
	public void config(){
		setAttr("config", Config.dao.getConfig());
		render("config.html");
	}

	public void saveConfig(){
		Config config = getModel(Config.class);
		boolean b = config.update();
		if(b){
			renderJson("{\"success\":true}");
		}else{
			renderJson("{\"success\":false}");
		}
	}
}
