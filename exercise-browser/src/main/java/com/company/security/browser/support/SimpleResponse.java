package com.company.security.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SimpleResponse
 * @Description: TODO: controller方法返回json数据，内容content。
 * @Author qiqinbo
 * @Date 2020/5/25 19:16
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {
	private Object content;
}
