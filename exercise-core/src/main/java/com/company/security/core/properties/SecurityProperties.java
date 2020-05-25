package com.company.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SecurityProperties
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/25 19:26
 * @Version V1.0
 **/
@ConfigurationProperties(prefix = "cp.security")
@Data
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();
}
