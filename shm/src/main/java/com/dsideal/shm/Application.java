package com.dsideal.shm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置, 扫描当前包及子包.
 * @author feilm220
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) throws Exception {
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setShowBanner(false);
    	app.run(args);
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class).showBanner(false);
	}

}