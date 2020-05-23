package com.company.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName QueueListener
 * @Description: TODO: 用来监听消息队列
 * @Author qiqinbo
 * @Date 2020/5/23 15:44
 * @Version V1.0
 **/
@Component
@Slf4j
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	/*监听事件*/

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		//单开线程，主线程2
		new Thread(() -> {
			while (true) {
				//监听completeOrder是否有值
				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNumber = mockQueue.getCompleteOrder();
					log.info("返回订单处理结果：" + orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					//清除completerOrder，避免一直处理。
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();


	}
}
