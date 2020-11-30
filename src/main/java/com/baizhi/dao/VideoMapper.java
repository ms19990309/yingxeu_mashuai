package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPOS;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video> {

    //前台查询
    List<VideoPO> queryByReleaseTime();

    //前台模糊查询
    List<VideoPOS> queryname(@Param("content") String content);

    //前台二级类别id查询
    List<VideoPOS> queryid(@Param("cateId") String cateId);

}