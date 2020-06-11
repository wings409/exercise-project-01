package com.company.security.core.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ImageCodeProperties
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 21:04
 * @Version V1.0
 **/
@Data
@ApiModel("图片验证码配置参数")
public class ImageCodeProperties {

	@ApiModelProperty("图片宽度")
	private int width = 67;

	@ApiModelProperty("图片高度")
	private int height = 23;

	@ApiModelProperty("验证码长度")
	private int length = 4;

	@ApiModelProperty("超时时间")
	private int expireIn = 60;

	@ApiModelProperty("可配置拦截路径")
	private String url ;

}
