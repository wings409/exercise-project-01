package com.company.security.browser;

import com.company.security.core.authentication.AbstractChannelSecurityConfig;
import com.company.security.core.properties.SecurityProperties;
import com.company.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName BrowserSecurityConfig
 * @Description: TODO: web应用安全配置
 * @Author qiqinbo
 * @Date 2020/5/23 18:07
 * @Version V1.0
 **/
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;

	private AuthenticationFailureHandler authenticationFailureHandler;
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();

		//将自定义的图形验证码过滤器加到过滤器链中
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
				//使用表单login,对应过滤器：Username Password Authentication Filter
				.formLogin()
				//
				.loginPage("/authentication/require")
//				.loginPage("/signIn.html")
				//登录表单跳转的路径，用上述定义的页面signIn替换掉SpringSecurity默认的表单action：/login post
				.loginProcessingUrl("/authentication/form")

		//使用basic页面弹窗登录,对应过滤器：Basic Authentication Filter
//		http.httpBasic()
			.and()
				//请求
			.authorizeRequests()
				//添加匹配器，该网页不需要认证
//				.antMatchers("/signIn.html").permitAll()
				.antMatchers("/authentication/require",
						securityProperties.getBrowser().getLoginPage(),
						"/code/image").permitAll()
				//任何请求
			.anyRequest()
				//安全认证
			.authenticated()
			.and()
				//关掉跨站请求防护（token校验功能）
			.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		//也可以返回自定义PasswordEncoder接口的实现类。
		return new BCryptPasswordEncoder();
	}

}
