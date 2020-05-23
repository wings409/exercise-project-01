package com.company.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: TODO: 自定义valid注解
 * @Author qiqinbo
 * @Version V1.0
 **/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class) //由自定义验证器来执行校验逻辑。
public @interface MyConstraint {
	/*校验不过时候发送的信息*/
	String message() ;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
