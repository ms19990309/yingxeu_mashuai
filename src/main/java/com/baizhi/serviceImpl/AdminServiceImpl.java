package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    AdminMapper adminMapper;

    @Resource
    HttpSession session;

    //后台
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String, Object> login(Admin admin, String clientCode) {
        HashMap<String, Object> map = new HashMap<>();

        //获取验证码
        String imageCode = (String) session.getAttribute("ServerCode");

        //判断验证码
        if(imageCode.equals(clientCode)){

            Admin admin1 = new Admin();
            admin1.setUsername(admin.getUsername());
            Admin admins = adminMapper.selectOne(admin1);

            if(admins!=null){
                if(admins.getPassword().equals(admin.getPassword())){
                    session.setAttribute("admin",admins);
                    map.put("message","登陆成功");
                    map.put("status","200");
                }else{
                    map.put("message","密码错误");
                    map.put("status","201");
                }
            }else{
                map.put("message","用户不存在");
                map.put("status","201");
            }
        }else{
            map.put("message","验证码不正确");
            map.put("status","201");
        }
        return map;
    }
}
