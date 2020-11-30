package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;


    @AddLog("(user)(后台)查询总条数")
    //后台：查询总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public long findTotalCounts() {
        User user = new User();
        return userMapper.selectCount(user);
    }

    @AddLog("(user)(后台)分页查询所有")
    //后台：分页查询所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllfy(Integer page, Integer rows) {
        int start = (page-1)*rows;
        UserExample userExample = new UserExample();
        RowBounds rowBounds = new RowBounds(start,rows);
        return userMapper.selectByExampleAndRowBounds(userExample,rowBounds);
    }

    @DelCahe
    @AddLog("(user)(后台)修改状态")
    //后台：改状态
    @Override
    public void updateByPrimaryKeySelective(User user) {
        if(user.getStatus()==0){
            user.setStatus(1);
        }else {
            user.setStatus(0);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void findAllDC() {
        List<User> user = userMapper.selectAll();

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

    //查询月份注册人数
    @Override
    public Integer queryTJ(String sex, String mins) {
        return userMapper.queryTJ(sex,mins);
    }

    //查询注册人数的地区statuss
    @Override
    public String queryStatus(String statuss,String sex) {
        return userMapper.queryStatus(statuss,sex);
    }

}
