package com.company.security.core.properties;

import lombok.Data;

/**
 * @ClassName BrowserProperties
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/25 19:26
 * @Version V1.0
 **/
@Data
public class BrowserProperties {

	private SessionProperties session = new SessionProperties();


	private String signUpUrl = "/signUp.html";

	/**
	 *  默认的登录页面为browser上的登录页面
	 **/
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	/**
	 *  默认的登录响应类型为json
	 **/

	private LoginResponseType loginType = LoginResponseType.JSON;

	private int rememberMeSeconds = 60*60;

}
