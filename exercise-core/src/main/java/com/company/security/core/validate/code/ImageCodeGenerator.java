package com.company.security.core.validate.code;

import com.company.security.core.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName ImageCodeGenerator
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/6/11 22:33
 * @Version V1.0
 **/
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ImageCode generate(ServletWebRequest request) {
		//生成图片对象，图片宽高(请求级配置)
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();
		Random random = new Random();

		//在图片对象上生成随机条纹
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		//生成4位的随机数
		String sRand = "";
		for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			//将4位随机数写到图片上
			g.drawString(sRand, 13 * i + 6, 16);
		}

		g.dispose();
		//60是图形验证码的有效期
		return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());

	}

	/**
	 * @param fc:
	 * @param bc:
	 * @Description: 生成随机背景条纹
	 * @Author: qiqinbo
	 * @Date: 2020/6/10 22:18
	 * @return: java.awt.Color
	 **/
	private Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		Random random = new Random();
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
