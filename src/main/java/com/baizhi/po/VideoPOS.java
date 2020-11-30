package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPOS {

    private String id;          //视频id
    private String videoTitle;  //视频标题
    private String cover;       //视频封面
    private String path;        //视频路径
    private String uploadTime;  //视频上传时间
    private String description; //视频描述
    private Integer likeCount;  //点赞数
    private String cateName;    //类别名
    private String categoryId;  //类别名
    private String userId;      //用户id
    private String userName;
}
