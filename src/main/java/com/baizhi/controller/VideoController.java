package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("videoController")
public class VideoController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private VideoService videoService;

    //后台：用户查所有（分页）
    @RequestMapping("findAllfy")
    @ResponseBody
    public HashMap<Object, Object> findAllfy(Integer page, Integer rows){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Video> video;
        Long totalCounts;
        //分页查询的结果
        video = videoService.findAll(page,rows);
        //总条数
        totalCounts = videoService.findTotalCounts();
        //总页数
        Long totalPage = totalCounts%rows==0?totalCounts/rows:(totalCounts/rows)+1;
        hashMap.put("page",page);
        hashMap.put("total",totalPage);
        hashMap.put("records",totalCounts);
        hashMap.put("rows",video);
        return hashMap;

    }

    //后台
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Video video, String oper){

        log.info("值{}", video);

        String result =null;

        if (StringUtils.equals("add",oper)) {
            log.info("值{}", video);
            result = videoService.save(video);
        }
        if (StringUtils.equals("edit",oper)){
            log.info("值{}",video);

        }
        if (StringUtils.equals("del",oper)){
            log.info("值{}",video);
            videoService.delete(video);
        }
        return result;
    }

    //后台：添加
    @RequestMapping("headUpload")
    public void headUpload(MultipartFile videoPath, String id) {
        log.info("值{}",id);
        videoService.headUpload(videoPath, id);
    }

}
