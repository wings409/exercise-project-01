package com.company.config;

import com.company.filter.TimeFilter;
import com.company.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfig
 * @Description: TODO: 配置类，为第三方过滤器引入
 * @Author qiqinbo
 * @Date 2020/5/21 20:35
 * @Version V1.0
 **/
/*配置类，等效于以前的xml配置文件*/

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/*拦截器除了@Component外，还要注册到interceptor中*/

	@Autowired
	private TimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}

	@Bean
	public FilterRegistrationBean timeFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		//所有的url都会经过这个过滤器
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);

		return registrationBean;
	}
}
