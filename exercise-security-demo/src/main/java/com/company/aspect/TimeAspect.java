package com.company.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @ClassName TimeAspect
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/23 10:50
 * @Version V1.0
 **/
@Aspect
@Component
@Slf4j
public class TimeAspect {

	@Around("execution(* com.company.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		log.info("time aspect start");
		//获取切入点方法的参数
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			log.info("arg is " + arg);
		}
		long start = System.currentTimeMillis();
		Object proceed = pjp.proceed();
		log.info("time aspect 耗时：" + (System.currentTimeMillis() - start));
		log.info("time aspect finish");
		return proceed;
	}
}
