package com.company.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @Author qiqinbo
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
	private int code;
	private String message;
	private Object data;

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static Result ok(Object data) {
		return new Result(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
	}

	public static Result ok() {
		return new Result();
	}

	public static Result error(String message) {
		return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
}
