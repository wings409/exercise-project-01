package com.company.security.core.validate.code;

import com.company.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;

/**
 * @ClassName ValidateCodeBeanConfig
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 22:40
 * @Version V1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	/*@ConditionalOnMissingBean注解含义：如果在spring启动时，bean容器里面含有imageCodeGenerator的bean
	* 就不会再通过该方法生成bean对象*/

	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}

}
