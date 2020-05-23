package com.company.controller;

import com.company.model.BaseUser;
import com.company.service.UserService;
import com.company.http.Result;
import com.company.vo.UserConditionPageParam;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/add")
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
	public Result invalid(@PathVariable("id") Long id) {
		try {
			int data = userService.invalid(id);
			return Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("删除失败：" + e.getMessage());
		}
	}

	@PostMapping("/edit")
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
}
