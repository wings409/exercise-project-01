package com.company.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName TimeFilter
 * @Description: TODO: 过滤器
 * @Author qiqinbo
 * @Date 2020/5/21 19:49
 * @Version V1.0
 **/
@Slf4j
//@Component
public class TimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("time filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("time filter start");
		long start = System.currentTimeMillis();
		chain.doFilter(request,response);
		log.info("time filter 耗时："+(System.currentTimeMillis()-start));
		log.info("time filter finish");
	}

	@Override
	public void destroy() {
		log.info("time filter destroy");
	}
}
