package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {

    //查询总条数
    long findTotalCounts();

    //分页查询所有
    List<User> findAllfy(Integer page, Integer rows);

    //(后台)修改状态
    void updateByPrimaryKeySelective(User user);

    List<User> findAll();

    //导出查询所有
    void findAllDC();

    //查询月份注册人数
    Integer queryTJ(String sex,String mins);

    //查询注册人数的地区statuss
    String queryStatus(String statuss,String sex);

}
