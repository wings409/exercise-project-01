package com.company.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName UserCotrollerTest
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/21 14:39
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCotrollerTest {
	/*伪造的mvc环境*/
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

}
