package com.supretest.base.mapper;

import com.supretest.base.domain.UserKey;
import com.supretest.base.domain.UserKeyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserKeyMapper {
    long countByExample(UserKeyExample example);

    int deleteByExample(UserKeyExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserKey record);

    int insertSelective(UserKey record);

    List<UserKey> selectByExample(UserKeyExample example);

    UserKey selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserKey record, @Param("example") UserKeyExample example);

    int updateByExample(@Param("record") UserKey record, @Param("example") UserKeyExample example);

    int updateByPrimaryKeySelective(UserKey record);

    int updateByPrimaryKey(UserKey record);
}