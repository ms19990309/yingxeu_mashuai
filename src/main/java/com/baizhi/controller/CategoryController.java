package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("categoryController")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CategoryService categoryService;


    //后台：查所有（一类别）分页）
    @RequestMapping("findAllfy")
    @ResponseBody
    public HashMap<Object, Object> findAllfy(Integer page, Integer rows){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Category> categories;
        Long totalCounts;
        //分页查询的结果
        categories = categoryService.findAllfy(page,rows,"1");
        //总条数
        totalCounts = categoryService.findTotalCount("1");
        //总页数
        Long totalPage = totalCounts%rows==0?totalCounts/rows:(totalCounts/rows)+1;
        hashMap.put("page",page);
        hashMap.put("total",totalPage);
        hashMap.put("records",totalCounts);
        hashMap.put("rows",categories);
        return hashMap;

    }


    //后台：查所有（二类别）分页）
    @RequestMapping("findAllfys")
    @ResponseBody
    public HashMap<Object, Object> findAllfys(Integer page, Integer rows,String id){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Category> categories;
        Long totalCounts;
        //分页查询的结果
        categories = categoryService.findAllfys(page,rows,id);
        //总条数
        totalCounts = categoryService.findTotalCount("2");
        //总页数
        Long totalPage = totalCounts%rows==0?totalCounts/rows:(totalCounts/rows)+1;
        hashMap.put("page",page);
        hashMap.put("total",totalPage);
        hashMap.put("records",totalCounts);
        hashMap.put("rows",categories);
        return hashMap;

    }


    //后台：一类别
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(Category category, String oper){

        log.info("当前操作：{}",oper);
        HashMap<String, Object> map = new HashMap<>();

        try{
            if (StringUtils.equals("add",oper)){
                log.info("值{}",category);
                categoryService.save(category);
            }
            if (StringUtils.equals("edit",oper)){
                log.info("值{}",category);
                categoryService.update(category);
            }
            if (StringUtils.equals("del",oper)){
                log.info("值{}",category);
                categoryService.delete(category);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",e.getMessage());
        }
        return map;
    }


    //后台：二类别
    @RequestMapping("edits")
    @ResponseBody
    public void edits(Category category,String oper,String ids){

        log.info("当前操作：{}",oper);
        if (StringUtils.equals("add",oper)){
            log.info("值{}",category);
            category.setParentclass(ids);
            categoryService.save(category);
        }
        if (StringUtils.equals("edit",oper)){
            log.info("值{}",category);
            categoryService.update(category);
        }
        if (StringUtils.equals("del",oper)){
            log.info("值{}",category);
            categoryService.deletes(category);
        }

    }
}
