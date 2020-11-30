package com.baizhi.test;


import com.baizhi.YingxeuMashuaiApplication;
import com.baizhi.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = YingxeuMashuaiApplication.class)
@RunWith(SpringRunner.class)
public class TestCategoryService {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testfindAll(){
        System.out.println(categoryService.findTotalCount("2"));
    }

    @Test
    public void testfindAlls(){
        System.out.println(categoryService.findAllfys(0,1,"2"));
    }


}
