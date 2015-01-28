package com.dsideal.fsys;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.controller.ConfigController;
import com.dsideal.fsys.controller.IndexController;
import com.dsideal.fsys.controller.ResourceController;
import com.dsideal.fsys.controller.UserController;
import com.dsideal.fsys.interceptor.IsLoginInterceptor;
import com.dsideal.fsys.model.Config;
import com.dsideal.fsys.model.Resource;
import com.dsideal.fsys.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;

/**
 * 
 * @author feilm220
 *
 */
public class AppConfig extends JFinalConfig
{
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Override
	public void configConstant(Constants me) {
		//加载properties文件
		loadPropertyFile("config.properties");
		//设置开发模式可自动热部署
		me.setDevMode(getPropertyToBoolean("devMode"));
		//render时如果没有以“/”开头,那么就基于基路径查找view显示,如果以“/”开头就默认从根开始查找
		//me.setBaseViewPath("/html");
		//IE不显示自定义404
		me.setError404View("/html/404.html");
		me.setError500View("/html/500.html");
	}

	@Override
	public void configRoute(Routes me) {
		//setBaseViewPath是全局设置, 在这里可以单个设置
		me.add("/", IndexController.class, "/html");
		me.add("/config", ConfigController.class, "/html/sys");
		me.add("/resource", ResourceController.class, "/html/sys");
		me.add("/user", UserController.class, "/html/sys");
	}

	@Override
	public void configPlugin(Plugins me) {
		//使用druid连接池
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(druidPlugin);
		//Record模式
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		activeRecordPlugin.setShowSql(getPropertyToBoolean("showSql"));
		//配置数据库列名大写不敏感
		activeRecordPlugin.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		me.add(activeRecordPlugin);
		//数据库表和Model对应
		activeRecordPlugin.addMapping("sys_config", Config.class);
		activeRecordPlugin.addMapping("sys_resource", Resource.class);
		activeRecordPlugin.addMapping("sys_user", User.class);
		
		me.add(new EhCachePlugin());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//全局拦截器,如果不全局可以使用@Before加在Controller上
		me.add(new IsLoginInterceptor());
		
	}

	@Override
	public void configHandler(Handlers me) {
		//页面上可以${contextPath}取到ContextPath值
		me.add(new ContextPathHandler("contextPath"));
	}

	@Override
	public void afterJFinalStart() {
		//JFinal启动后初始化数据
		if(getPropertyToBoolean("initData")){
			try {
				Db.batch(loadSql("/data.sql"), 100); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//TODO 初始化系统配置
	}
	//加载.sql文件
	public List<String> loadSql(String sqlFile) throws IOException{
		InputStream inputStream = AppConfig.class.getResourceAsStream(sqlFile);
		StringBuilder sqlsb = new StringBuilder();
		byte[] buff = new byte[1024];
		int size = 0;
		while((size = inputStream.read(buff)) != -1){
			sqlsb.append(new String(buff, 0, size));
		}
		//Windows下换行是\r\n,Linux下是\n?
		String[] sqlArr = sqlsb.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");
		List<String> sqlList = new ArrayList<String>();
		for(String sql : sqlArr){
			if(!"".equals(sql)){
				//sql = sql.replaceAll("`", "");
				sqlList.add(sql);
				log.debug("在JFinalStart启动后初始化数据 {}" , sql);
			}
		}
		return sqlList;
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/f", 3);
	}
}
