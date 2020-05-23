package com.company.vo;

import lombok.Data;

/**
 * @ClassName UserConditionPageParam
 * @Description: TODO:
 * @Author qiqinbo
 * @Date 2020/5/21 17:29
 * @Version V1.0
 **/
@Data
public class UserConditionPageParam extends PageParam {
	private String userName;
	private String sex;
}
