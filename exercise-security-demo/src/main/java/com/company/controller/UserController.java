package com.company.controller;

import com.company.model.BaseUser;
import com.company.service.UserService;
import com.company.http.Result;
import com.company.vo.UserConditionPageParam;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author qiqinbo
 * @Version V1.0
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/me/authentication")
	//节省代码，在方法上可以直接写Authentication，如下
//	public Object getCurrentUser(){
//		return SecurityContextHolder.getContext().getAuthentication();
//	}
	public Object getCurrentUserAuthentication(Authentication authentication){
		//SpringMVC 会自动给Authentication对象 在SecurityContextHolder里面找。
		return authentication;
	}

	//还可以直接拿到UserDetails
	@GetMapping("/me/details")
	public Object getCurrentUserDetails(@AuthenticationPrincipal UserDetails user){
		//SpringMVC 会自动给Authentication对象 在SecurityContextHolder里面找。
		return user;
	}

	@PostMapping("/add")
	@ApiOperation(value = "新增用户")
	public Result add(@Valid @RequestBody BaseUser baseUser, BindingResult errors) {
		//打印valid规则校验中的错误信息。
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				log.error("valid校验不通过", fieldError.getField(), fieldError.getDefaultMessage());
			});
			return Result.error("参数不合法");
		}
		try {
			int data = userService.add(baseUser);
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("查询失败：" + e.getMessage());
		}
	}

	@GetMapping("/invalid/{id}")
	@ApiOperation(value = "删除用户")
	public Result invalid(@ApiParam("用户id") @PathVariable("id") Long id) {
		try {
			int data = userService.invalid(id);
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("删除失败：" + e.getMessage());
		}
	}

	@PostMapping("/edit")
	@ApiOperation(value = "编辑用户")
	public Result edit(@Valid @RequestBody BaseUser baseUser, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				log.error("valid校验不通过", fieldError.getField(), fieldError.getDefaultMessage());
			});
			return Result.error("参数不合法");
		}
		try {
			int data = userService.edit(baseUser);
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("修改失败：" + e.getMessage());
		}
	}

	@PostMapping("/list/condition")
	@ApiOperation(value = "查询用户")
	public Result list(@RequestBody UserConditionPageParam pageParam) {
		if (pageParam.getNum() == null || pageParam.getSize() == null) {
			return Result.error("查询失败,分页参数有误");
		}
		try {
			List<BaseUser> userList = userService.list(pageParam);
			PageInfo<BaseUser> data = new PageInfo<>(userList);
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("查询失败：" + e.getMessage());
		}
	}

	@GetMapping("/list/all")
	@ApiOperation(value = "查询所有用户")
	public Result listAll() {
		try {
			List<BaseUser> data = userService.listAll();
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("查询失败：" + e.getMessage());
		}
	}
}
