package com.company.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName MockQueue
 * @Description: TODO: 模拟消息队列 应用2
 * @Author qiqinbo
 * @Date 2020/5/23 15:29
 * @Version V1.0
 **/

@Slf4j
@Component
@Data
public class MockQueue {
	/*下单消息*/
	private String placeOrder;
	/*订单完成的消息*/
	private String completeOrder;


	public void setPlaceOrder(String placeOrder) {
		//应用2
		new Thread(()->{
			log.info("接到下单请求," + placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			log.info("下单请求处理完毕，" + placeOrder);
		}).start();

	}
}
