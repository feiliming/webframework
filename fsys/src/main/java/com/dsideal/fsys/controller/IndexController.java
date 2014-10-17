package com.dsideal.fsys.controller;

import com.dsideal.fsys.model.Config;
import com.dsideal.fsys.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NoUrlPara;

/**
 * 
 * @author feilm220
 *
 */
public class IndexController extends Controller{

	@Before(NoUrlPara.class)
	public void index(){
		if(getCookie("userId") != null && !"".equals(getCookie("userId"))){
			//TODO 系统全局配置应该放在哪?
			setAttr("config", Config.dao.getConfig());
			//如果/index.html则从根目录查询,否则相对基目录
			render("index.html");
		} else {
			render("login.html");
		}
	}
	
	public void login(){
		User userModel = getModel(User.class);
		User user = User.dao.checkLoginNameAndPassword(userModel);
		if(user != null){
			//-1关闭浏览器cookie失效
			setCookie("userId", user.getStr("id").toString(), -1, "/");
			renderJson("{\"success\" : true}");
		}else{
			renderJson("{\"success\" : false}");
		}
	}
	
	public void logout(){
		removeCookie("userId", "/");
		render("login.html");
	}
}
