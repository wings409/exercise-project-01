package com.company.service.impl;

import com.company.dao.BaseUserMapper;
import com.company.model.BaseUser;
import com.company.service.UserService;
import com.company.vo.PageParam;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qiqinbo
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BaseUserMapper userMapper;

	@Override
	public int add(BaseUser baseUser) {
		return userMapper.insertSelective(baseUser);
	}

	@Override
	public int invalid(Long id) {
		return userMapper.updateInvalidById(id);
	}

	@Override
	public int edit(BaseUser baseUser) {
		return userMapper.updateByPrimaryKeySelective(baseUser);
	}

	@Override
	public List<BaseUser> list(PageParam pageParam) {
		PageHelper.startPage(pageParam.getNum(),pageParam.getSize());
		return userMapper.selectListByParam(pageParam);
	}

	@Override
	public List<BaseUser> listAll() {
		return userMapper.selectAll();
	}
}
