package com.company.code;

import com.company.security.core.validate.code.ImageCode;
import com.company.security.core.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName DemoImageCodeGenerator
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 23:56
 * @Version V1.0
 **/
@Component("imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		log.info("更高级的图形验证码生成代码，没有代码的时候会报空指针");
		return null;
	}
}
