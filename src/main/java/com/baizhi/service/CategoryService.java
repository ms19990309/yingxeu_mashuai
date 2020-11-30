package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPO;

import java.util.List;

public interface CategoryService {

    //查询总条数（一级）(二级）
    long findTotalCount(String grade);

    //分页查询所有(一级)
    List<Category> findAllfy(Integer page, Integer rows,String grade);

    //分页查询所有(二级）
    List<Category> findAllfys(Integer page, Integer rows,String id);

    //增(一级)(二级）
    void save(Category category);

    //删(一级)
    void delete(Category category);

    //删(二级）
    void deletes(Category category);

    //改（一级）(二级）
    void update(Category category);

    //前台查询
    List<CategoryPO> findAllms();

}
