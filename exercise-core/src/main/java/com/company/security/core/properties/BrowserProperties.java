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
	/**
	 *  默认的登录页面为browser上的登录页面
	 **/
	private String loginPage = "/sign-in.html";
}
