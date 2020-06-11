package com.company.security.core.properties;

import lombok.Data;

/**
 * @ClassName ValidateCodeProperties
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 21:08
 * @Version V1.0
 **/
@Data
public class ValidateCodeProperties {
	private ImageCodeProperties image = new ImageCodeProperties();
}
