package com.company.security.core;

import com.company.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SecurityCoreConfig
 * @Description: TODO: 让注解中的类：SecurityProperties.class生效
 * @Author qiqinbo
 * @Date 2020/5/25 19:30
 * @Version V1.0
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
