package com.company.security.browser.controller;

import com.company.security.browser.support.SimpleResponse;
import com.company.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName BrowserSecurityController
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/25 19:04
 * @Version V1.0
 **/
@RestController
@Slf4j
public class BrowserSecurityController {

	/*获取跳转前的request用*/

	private RequestCache requestCache = new HttpSessionRequestCache();

	/*重定向用*/

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/*配置属性*/

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * @Description: 当需要身份认证时，就跳转到这里
	 * @Author: qiqinbo
	 * @Date: 2020/5/25 19:06
	 * @param request:
	 * @param response:
	 * @return: java.lang.String
	 **/
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.拿到之前跳转前的request请求，方便判断之前是html还是数据请求。
		SavedRequest savedRequest = requestCache.getRequest(request,response);
		if(savedRequest != null){
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("引发跳转的请求是："+targetUrl);
			//如果是html请求
			if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
				//跳转到可配置的登录页面
				redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
			}
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}
}
