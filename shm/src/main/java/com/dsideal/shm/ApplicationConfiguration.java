package com.dsideal.shm;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author feilm220
 * 
 * 配置spring-mvc.xml
 * 1.继承WebMvcConfigurerAdapter或WebMvcConfigurationSupport, 重写方法
 * 2.使用@Bean声明 
 * 
 */
@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;
	
	//设置MessageConverter的媒体类型, 否则返回Json时IE出现下载
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
		jackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes );
		
		converters.add(jackson2HttpMessageConverter);
	}
	
	/**
	@Bean
    public HttpMessageConverters customConverters() {
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();   
        supportedMediaTypes.add(new MediaType("text", "html", Charset.forName("UTF-8")));
        supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return new HttpMessageConverters(jsonMessageConverter);
    }
    */

	//添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}

	//配置mvc:resources, 并设置浏览器过期时间
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/", "classpath:/META-INF/web-resources/")
				.setCachePeriod(31536000);
	}

}
