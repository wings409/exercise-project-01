package com.company.exception;

import lombok.Data;

/**
 * @ClassName UserNotExistException
 * @Description: TODO: 自定义异常， 可以通过throw new UserNotExistException来抛该异常。
 * @Author qiqinbo
 * @Date 2020/5/21 19:39
 * @Version V1.0
 **/
@Data
public class UserNotExistException extends RuntimeException {

	private Long id;

	public UserNotExistException(Long id) {
		super("user not exist");
		this.id = id;
	}
}
