package com.company.config;

import com.company.utils.JasyptUtils;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @ClassName EncryptionPropertyConfig
 * @Description: TODO: 注册加解密bean
 * @Author qiqinbo
 * @Date 2020/6/5 13:50
 * @Version V1.0
 **/
@Configuration
@Slf4j
public class EncryptionPropertyConfig {

	@Bean(name = "encryptablePropertyResolver")
	public EncryptablePropertyResolver encryptablePropertyResolver() {
		return new EncryptionPropertyResolver();
	}

	class EncryptionPropertyResolver implements EncryptablePropertyResolver {

		@Override
		public String resolvePropertyValue(String value) {
			if (StringUtils.isEmpty(value)) {
				return value;
			}
//			// 值以DES@开头的均为DES加密,需要解密
//			if (value.startsWith("DES@")) {
//				return resolveDESValue(value.substring(4));
//			}
			if(value.startsWith("ENC(")){
				try {
					return JasyptUtils.decrypt(value.substring(value.indexOf("(") + 1, value.lastIndexOf(")")));
				} catch (Exception e) {
					log.info("数据库密码解密失败：{}",e.getMessage());
				}
			}
			// 不需要解密的值直接返回
			return value;
		}

//		private String resolveDESValue(String value) {
//			// 自定义DES密文解密
//			return DesUtil.decrypt(value, DesUtil.KEY);
//		}

	}
}
