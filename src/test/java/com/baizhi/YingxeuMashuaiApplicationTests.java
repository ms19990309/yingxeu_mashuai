package com.baizhi;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.dao.UserMapper;
import com.baizhi.dao.VideoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YingxeuMashuaiApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private VideoMapper videoMapper;

    @Test
    public void conts() {
        System.out.println(categoryMapper.findAllms());
    }


    @Test
    public void contss() {
        System.out.println(videoMapper.queryByReleaseTime());
    }



    @Test
    public void contsss() {
        System.out.println(videoMapper.queryname("大"));
    }

    @Test
    public void contssss() {
        System.out.println(videoMapper.queryid("5"));
    }







}
