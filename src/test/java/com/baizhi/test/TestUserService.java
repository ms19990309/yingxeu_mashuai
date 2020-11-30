package com.baizhi.test;


import com.baizhi.YingxeuMashuaiApplication;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = YingxeuMashuaiApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testfindAllms(){
        System.out.println(userService.queryTJ("帅帅","12"));
    }


    @Test
    public void testfindAllmsy(){
        System.out.println(userService.queryStatus("山西","女"));
    }




}
