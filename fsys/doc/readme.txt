1.jfinal默认使用freemarker模板，还可以使用Jsp、velocity，如果要使用其他模板则需要扩展Render，可参考jfinal-bbs的BeetlRender

2.自动热部署
在Config中设置开发模式为true

3.在页面模板获得ContextPath，需要加在Config的configHandler方法中加上ContextPathHandler
如me.add(new ContextPathHandler("contextPath"));则模板中可以${contextPath}

4.render时如果没有以“/”开头，那么就基于基路径查找view显示，如果以“/”开头就默认从根开始查找
基路径在Config中setBaseViewPath设置

5.判断用户是否登录可以使用一个全局的Interceptor，在Config的configInterceptor中加，不需要过滤的路径在Interceptor中判断，参看IsLoginInterceptor
如果只是某些路径需要判断，也可以在Controller上加，使用@Before(SessionInterceptor.class)

6.如果要在程序启动后初始化一些东西，比如数据可以在afterJFinalStart方法中实现

7.连接数据库并使用连接池
	loadPropertyFile("jdbc.properties");
	//使用druid连接池
	DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
	me.add(druidPlugin);
	//Record模式
	ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
	me.add(activeRecordPlugin);
	//数据库表和Model对应
	activeRecordPlugin.addMapping("sys_user", User.class);
	
8.Controller收集请求参数
如果使用getModel(User.class)，则请求参数写user.login_name,user.password,这时使用getPara("login_name")接不到值
请求参数写login_name时，可使用getPara("login_name")

9."/"默认访问index方法，如果/aaaaa,/bbbbb即不存在的方法，此时当成参数，正常应该跳到404，可在index方法上加@NoUrlPara.class
这样就会跳到404，但IE跳不到自定义的404页面，其他浏览器可以
	me.setError404View("/html/404.html");
	me.setError500View("/html/500.html");

10.使用缓存
(1)
	<dependency>
		<groupId>net.sf.ehcache</groupId>
		<artifactId>ehcache-core</artifactId>
		<version>2.6.9</version>
	</dependency>	
(2)
	me.add(new EhCachePlugin());
(3)ehcache.xml
	<cache name="user"
        maxElementsInMemory="3000"
        eternal="false"
        timeToIdleSeconds="3600"
        timeToLiveSeconds="3600"
        overflowToDisk="true"
        diskSpoolBufferSizeMB="30"
        memoryStoreEvictionPolicy="LFU"
     />
(4)	public void userList(){
		List<Record> userList = CacheKit.get("user", "userList");
		if(userList == null){
			userList = Db.find("select * from sys_user");
			CacheKit.put("user", "userList", userList);
		}
		renderJson(userList);
	}
	
11.引用日志
slf4j是门面，log4j、logback是api实现
pom引用log4j则使用log4j.properties
pom引用logback-classic则使用logback.xml

12.ajax时IE提示下载,使用forIE
renderJson("{\"success\":true}");
render(new JsonRender("{\"success\":true}").forIE());

13.