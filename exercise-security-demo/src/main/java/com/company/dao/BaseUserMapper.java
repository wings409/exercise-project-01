package com.company.dao;

import com.company.model.BaseUser;
import com.company.vo.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BaseUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);

    int updateInvalidById(Long id);

    List<BaseUser> selectListByParam(PageParam pageParam);

	List<BaseUser> selectAll();

}
