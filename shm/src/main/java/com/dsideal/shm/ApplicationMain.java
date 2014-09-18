package com.dsideal.shm;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 自动配置, 扫描当前包及子包.
 * @author feilm220
 *
 */
@EnableAutoConfiguration
@ComponentScan
public class ApplicationMain extends SpringBootServletInitializer{

	//内嵌tomcat
	/**
    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(ApplicationConfiguration.class);
    	app.setShowBanner(false);
    	app.run(args);
    }
    */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationMain.class).showBanner(false);
	}

}