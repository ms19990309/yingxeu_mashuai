package com.baizhi.tests;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest

public class EasyPoiTests {


    @Autowired
    private UserService userService;

    @Test
    public void Poiexport(){

        List<User> user = userService.findAll();

        //导出设置的参数  参数:大标题,工作表名
        ExportParams exportParams = new ExportParams("用户表", "用户");

        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class,user);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D:\\后期项目\\User.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {

        //导入设置的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题所占行
        params.setHeadRows(1);   //表头所占行

        //导入
        List<User> teachers = ExcelImportUtil.importExcel(new File("D:\\后期项目\\User.xls"),User.class, params);

        teachers.forEach(teacher -> System.out.println(teacher));
    }


}
