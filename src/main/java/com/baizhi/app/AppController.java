package com.baizhi.app;

import com.baizhi.common.CommonResult;
import com.baizhi.po.CategoryPO;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPOS;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    VideoService videoService;

    @Resource
    CategoryService categoryService;


    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone) {

        //生成验证码随机数
        String randomCode = ImageCodeUtil.getSecurityCode();
        System.out.println("手机验证码: " + randomCode);

        String message = null;
        try {
            //发送手机验证码
            message = AliyunUtil.sendPhoneMsg(phone, randomCode);

            return new CommonResult().success(message, phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }

    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }


    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory(){
        try {
            List<CategoryPO> categoryPO = categoryService.findAllms();
            return new CommonResult().success(categoryPO);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content){
        try {
            List<VideoPOS> videoPOS = videoService.queryname(content);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    @RequestMapping("queryCateVideoList")
    public CommonResult queryCateVideoList(String cateId){
        try {
            List<VideoPOS> videoPOS = videoService.queryid(cateId);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

}
