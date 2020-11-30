package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    //查询月份注册人数
    Integer queryTJ(@Param("sex") String sex,@Param("mins") String mins);


    //查询注册人数的地区statuss
    String queryStatus(@Param("statuss") String statuss,@Param("sex") String sex);

}