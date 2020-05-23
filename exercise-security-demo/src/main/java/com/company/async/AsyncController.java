package com.company.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @ClassName AsyncController
 * @Description: TODO: 异步处理controller
 * @Author qiqinbo
 * @Date 2020/5/23 14:29
 * @Version V1.0
 **/
@RestController
public class AsyncController {
	/*写日志*/

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*同步示例*/

	@RequestMapping("/order/sync")
	public String orderSync() throws InterruptedException {
		logger.info("主线程开始");
		Thread.sleep(1000);
		logger.info("主线程返回");
		return "success";
	}

	/*runnable异步示例:副线程代码是写在主线程里面*/

	@RequestMapping("/order/async")
	public Callable<String> orderAsync() {
		logger.info("主线程开始");
		//单开一个线程
		Callable<String> result = () -> {
			logger.info("副线程开始");
			Thread.sleep(1000);
			logger.info("副线程返回");
			return "success";
		};
		logger.info("主线程返回");
		return result;
	}

	/*DeferredResult异步示例: 线程彼此独立*/

	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/order/async/deferred/result")
	public DeferredResult<String> orderAsyncDefRes() throws InterruptedException {
		/*主线程1*/
		logger.info("主线程开始");

		//生成随机订单号，放到消息队列
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);

		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);

		logger.info("主线程返回");
		return result;
	}
}
