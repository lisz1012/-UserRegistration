package com.lisz.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserMapper继承基类
 */
@Repository
@Mapper
public interface UserMapper extends MyBatisBaseDao<User, Integer, UserExample> {
}