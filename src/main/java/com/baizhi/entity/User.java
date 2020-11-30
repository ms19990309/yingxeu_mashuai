package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "yx_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Excel(name = "ID")
    private String id;

    @Excel(name = "昵称")
    private String name;

    @Excel(name = "密码")
    private String password;

    @Excel(name = "电话")
    private String phone;

    @Excel(name = "微信")
    private String wechat;

    @Excel(name = "头像",type = 2)
    private String src;

    @Excel(name = "简介")
    private String brief;

    @Excel(name = "生日",exportFormat="yyyy-MM-dd",importFormat="yyyy-MM-dd")
    private Date mins;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "学分")
    private Integer credit;

    @Excel(name = "粉丝数")
    private Integer fans;

    @Excel(name = "视频点赞数")
    private Integer video;

    @Excel(name = "所在地")
    private String statuss;

    @Excel(name = "性别")
    private String sex;

}