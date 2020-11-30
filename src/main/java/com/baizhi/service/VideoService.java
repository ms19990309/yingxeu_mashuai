package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPOS;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    //后台增
    String save(Video video);

    //后台删
    void delete(Video video);

    //后台改
    void update(Video video);

    //后台分页查询所有
    List<Video> findAll(Integer page, Integer rows);

    //后台查询总条数
    long findTotalCounts();

    //后台添加
    void headUpload(MultipartFile picImg, String id);

    //前台查询
    List<VideoPO> queryByReleaseTime();

    //前台模糊查询
    List<VideoPOS> queryname(String content);

    //前台二级类别id查询
    List<VideoPOS> queryid(String cateId);

}
