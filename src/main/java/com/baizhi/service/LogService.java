package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.List;

public interface LogService {

    //删
    void delete(Log log);

    //查询总条数
    long findTotalCount();

    //分页查询所有
    List<Log> findAll(Integer page, Integer rows);

}
