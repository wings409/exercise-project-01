package com.company.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeGenerator
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 22:31
 * @Version V1.0
 **/
public interface ValidateCodeGenerator {

	ImageCode generate(ServletWebRequest request);
}
