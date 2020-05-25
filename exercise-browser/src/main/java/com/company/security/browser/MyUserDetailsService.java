package com.company.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyUserDetailsService
 * @Description: TODO: springsecurity用户信息封装对象
 * @Author qiqinbo
 * @Date 2020/5/25 17:31
 * @Version V1.0
 **/
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;


	/**
	 * @Description: 正确的用户名和密码，登录信息里面的数据和这个用户信息进行比对
	 * @Author: qiqinbo
	 * @Date: 2020/5/25 17:50
	 * @param userName:
	 * @return: org.springframework.security.core.userdetails.UserDetails
	 **/
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("登录用户："+userName);
		//根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String encodedPassword = passwordEncoder.encode("123456");
		log.info("数据库密码是："+encodedPassword);
		return new User(userName, encodedPassword,
				true,true,true,true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
