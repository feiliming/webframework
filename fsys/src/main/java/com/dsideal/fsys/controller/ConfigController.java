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
		render("config.html");
	}

	public void getConfig(){
		renderJson("config", Config.dao.getConfig());
	}
	
	public void updateConfig(){
		//getModel自动收集,modelName默认为Model类名小写,即config.system_name
		Config config = getModel(Config.class);
		boolean result = config.update();
		if(result){
			//ajax时IE提示下载,使用forIE
			//renderJson("{\"success\":true}");
			//render(new JsonRender("{\"success\":true}").forIE());
			renderText("ok");
		}else{
			renderText("nok");
		}
	}
}
