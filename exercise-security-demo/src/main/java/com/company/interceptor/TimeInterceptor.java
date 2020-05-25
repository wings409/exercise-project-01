package com.company.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName TimeInterceptor
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/21 20:41
 * @Version V1.0
 **/
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

	/*controller方法前*/

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle");

		if(handler instanceof HandlerMethod){
			log.info("待处理的的类名："+((HandlerMethod)handler).getBean().getClass().getName());
			log.info("待处理方法名："+((HandlerMethod)handler).getMethod().getName());
		}

		request.setAttribute("startTime",System.currentTimeMillis());
		//如果返回false，后面的controller方法就不执行。
		return true;
	}

	/*controller方法后，如果方法异常，不会被调用*/

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		log.info("postHandle");

		Long start = (Long) request.getAttribute("startTime");
		log.info("time interceptor 耗时："+(System.currentTimeMillis()-start));

	}

	/*controller方法后，始终会被调用*/

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		log.info("afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
		log.info("time interceptor 耗时："+(System.currentTimeMillis()-start));
		//全局异常处理类是在afterCompletion之前执行的，所以UserNotExistException在这里为空，因为已经被全局异常处理了。
		log.info("ex is "+ ex);
	}
}
