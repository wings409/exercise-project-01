package com.company.jasypt;


import com.company.DemoApplicationTest;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

public class JasyptTest extends DemoApplicationTest {
	@Test
	public void testEncrypt() throws Exception {

		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();

		config.setAlgorithm("PBEWithMD5AndDES");     // 加密的算法，这个算法是默认的
		config.setPassword("test");            // 加密的密钥
		standardPBEStringEncryptor.setConfig(config);
//		String plainText = "88888888"; //LQou8iCCzy/U8neM5lE1dSi7EDL+BZIe
//		String plainText = "root"; //EwHMQ5z/q1oVkK8y0nJEHQ==
		String plainText = "ZZTC_chaos@2020"; //EwHMQ5z/q1oVkK8y0nJEHQ==
		String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
		System.out.println(encryptedText);
	}

	@Test
	public void testDe() throws Exception {
		String databasePwd = "ENC(yQiUcFSUi6bOEwugDzQf1V7IkL65AKeQ)";
		String encrypted = databasePwd.substring(databasePwd.indexOf("(")+1,databasePwd.lastIndexOf(")"));
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		EnvironmentPBEConfig config = new EnvironmentPBEConfig();

		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword("test");
		standardPBEStringEncryptor.setConfig(config);
//		String encryptedText = "ip10XNIEfAMTGQLdqt87XnLRsshu0rf0";   //88888888
		String encryptedText = "EwHMQ5z/q1oVkK8y0nJEHQ==";  //root
		String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
		System.out.println(plainText);
	}
}
