package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("logController")
public class LogController {

    private static final Logger log1 = LoggerFactory.getLogger(LogController.class);

    @Autowired
    LogService logService;

    //后台：用户查所有（分页）
    @RequestMapping("findAllfy")
    @ResponseBody
    public HashMap<Object, Object> findAllfy(Integer page, Integer rows){
        log1.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Log> log;
        Long totalCounts;
        //分页查询的结果
        log = logService.findAll(page,rows);
        //总条数
        totalCounts = logService.findTotalCount();
        //总页数
        Long totalPage = totalCounts%rows==0?totalCounts/rows:(totalCounts/rows)+1;
        hashMap.put("page",page);
        hashMap.put("total",totalPage);
        hashMap.put("records",totalCounts);
        hashMap.put("rows",log);
        return hashMap;

    }



    //后台
    @RequestMapping("edit")
    @ResponseBody
    public void edit(Log log, String oper){

        log1.info("值{}", log);

        if (StringUtils.equals("del",oper)){
            logService.delete(log);
        }
    }

}
