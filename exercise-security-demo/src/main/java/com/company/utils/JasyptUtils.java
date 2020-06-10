package com.company.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

/**
 * @ClassName JasyptUtils
 * @Description: TODO: 数据库密码加解密
 * @Author qiqinbo
 * @Date 2020/6/5 11:30
 * @Version V1.0
 **/
public class JasyptUtils {

	/*加密密钥*/
	private static final String PWD = "test";

	public static String encrypt(String password) throws Exception {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();
		// 加密的算法，这个算法是默认的
		config.setAlgorithm("PBEWithMD5AndDES");
		// 加密的密钥
		config.setPassword(PWD);
		standardPBEStringEncryptor.setConfig(config);
		String encryptedText = standardPBEStringEncryptor.encrypt(password);
		return encryptedText;
	}

	public static String decrypt(String encrypted) throws Exception {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();

		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword(PWD);
		standardPBEStringEncryptor.setConfig(config);
		return standardPBEStringEncryptor.decrypt(encrypted);
	}
}
