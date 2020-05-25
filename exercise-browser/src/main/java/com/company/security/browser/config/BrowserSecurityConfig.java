package com.company.security.browser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName BrowserSecurityConfig
 * @Description: TODO: web应用安全配置
 * @Author qiqinbo
 * @Date 2020/5/23 18:07
 * @Version V1.0
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//使用表单login
//		http.formLogin()
		//使用basic页面弹窗登录
		http.httpBasic()
			.and()
				//请求
			.authorizeRequests()
				//任何请求
			.anyRequest()
				//安全认证
			.authenticated();
	}
}
