package com.company.security.core.validate.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName ImageCode
 * @Description: TODO: 封装验证码信息
 * @Author qiqinbo
 * @Date 2020/6/10 21:51
 * @Version V1.0
 **/
@Data
@ApiModel("图形验证码，封装验证码信息对象")
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {

	@ApiModelProperty("图片")
	private BufferedImage image;
	@ApiModelProperty("验证码")
	private String code;
	@ApiModelProperty("过期时间")
	private LocalDateTime expireTime;


	public ImageCode(BufferedImage image, String code, int expireIn) {
		this.image = image;
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}


	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
