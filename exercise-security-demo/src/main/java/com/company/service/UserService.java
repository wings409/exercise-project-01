package com.company.service;

import com.company.model.BaseUser;
import com.company.vo.PageParam;

import java.util.List;

/**
 * @Author qiqinbo
 * @Version V1.0
 **/
public interface UserService {
	int add(BaseUser baseUser);

	int invalid(Long id);

	int edit(BaseUser baseUser);

	List<BaseUser> list(PageParam pageParam);
}
