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
public class TestAdminService {

    @Autowired
    private UserService userService;
    @Test
    public void testfindAll(){

    }




}
