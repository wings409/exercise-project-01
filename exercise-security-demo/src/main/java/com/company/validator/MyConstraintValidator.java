package com.company.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName MyConstraintValidator
 * @Description: TODO: 自定义验证器：自定义注解名 ，自定义的注解放在什么类型的数据上
 * @Author qiqinbo
 * @Date 2020/5/21 18:50
 * @Version V1.0
 **/
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,String> {

	//可以注入service接口，实现校验逻辑

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		log.info("my validator init");
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext context) {
		log.info(str);
		return true;
	}
}
