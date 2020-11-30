package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("adminController")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    //后台：用户登陆
    @ResponseBody
    @RequestMapping("login")
    public HashMap<String, Object> login(Admin admin, String clientCode){

        log.info("接收到的信息:{}",admin);
        log.info("接收到的信息:{}",clientCode);

        return adminService.login(admin,clientCode);


    }

    //后台：退出
    @RequestMapping("logout")
    public String logout(){
        return "redirect:/login/login.jsp";
    }


    //后台：生成验证码
    @RequestMapping("code")
    public String code(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1 调验证码插件中的方法，生成验证码图片
        String code = SecurityCode.getSecurityCode();

        log.info("前台台验证码：{}",code);

        // 3 把随机验证码，存入session作用域
        request.getSession().setAttribute("ServerCode", code);

        BufferedImage bufferedImage = SecurityImage.createImage(code);

        ServletOutputStream out = response.getOutputStream();

        log.info("-----------------------:{}",out);

        // 2 使用响应输出流 把bufferedImage 输出到 client
        ImageIO.write(bufferedImage, "gif", out);

        return null;
    }

}
