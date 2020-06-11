package com.company.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateCodeException
 * @Description: TODO: AuthenticationException是权限校验中抛出的异常的基类
 * @Author qiqinbo
 * @Date 2020/6/10 23:04
 * @Version V1.0
 **/
public class ValidateCodeException extends AuthenticationException {
	public ValidateCodeException(String msg){
		super(msg);
	}
}
