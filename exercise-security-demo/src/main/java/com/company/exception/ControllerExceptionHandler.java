package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControllerExceptionHandler
 * @Description: TODO: 全局异常处理类
 * @Author qiqinbo
 * @Date 2020/5/21 19:43
 * @Version V1.0
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
		Map<String,Object>result = new HashMap<>();
		result.put("id",ex.getId());
		result.put("message",ex.getMessage());
		return result;
	}
}
