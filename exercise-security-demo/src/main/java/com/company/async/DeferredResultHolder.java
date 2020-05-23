package com.company.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DeferredResultHolder
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/23 15:33
 * @Version V1.0
 **/
@Component
@Data
public class DeferredResultHolder {

	private Map<String, DeferredResult<String>> map = new HashMap<>();


}
