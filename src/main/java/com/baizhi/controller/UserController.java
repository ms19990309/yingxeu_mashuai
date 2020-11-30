package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.entity.Sc;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("userController")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    //后台：用户查所有（分页）
    @RequestMapping("findAllfy")
    @ResponseBody
    public HashMap<Object, Object> findAllfy(Integer page, Integer rows){
        log.info("当前页: {},每页显示记录数: {}",page,rows);
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<User> users;
        Long totalCounts;
        //分页查询的结果
        users = userService.findAllfy(page,rows);
        //总条数
        totalCounts = userService.findTotalCounts();
        //总页数
        Long totalPage = totalCounts%rows==0?totalCounts/rows:(totalCounts/rows)+1;
        hashMap.put("page",page);
        hashMap.put("total",totalPage);
        hashMap.put("records",totalCounts);
        hashMap.put("rows",users);
        return hashMap;

    }


    //后台：修改状态
    @RequestMapping("status")
    @ResponseBody
    public Map<String,String> status(User user){
        log.info("用户信息：{}",user);
        HashMap<String,String> map = new HashMap<>();
        try{
            userService.updateByPrimaryKeySelective(user);
            map.put("message","修改成功");
            map.put("status","200");
        }catch (Exception e){
            map.put("message","修改失败");
            map.put("status","404");
        }
        return map;
    }



    //后台：发送手机验证码
    @RequestMapping("sendyzm")
    @ResponseBody
    public HashMap<String, Object> sendyzm(String phoneNumbers){
        String message = null;
        HashMap<String, Object> map = new HashMap<>();
        try {
            //先调用工具类获取随机数字
            String securityCode = ImageCodeUtil.getSecurityCode();
            //发送验证码
            message = AliyunUtil.sendPhoneMsg(phoneNumbers, securityCode);
            map.put("message", message);
            map.put("status", "200");
            System.out.println(map.get("message"));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", message);
            map.put("status", "201");
        }
        return map;
    }


    //后台：导出到表中
    @RequestMapping("findAllDC")
    @ResponseBody
    public Map<String,String> findAllDC() {
        HashMap<String,String> map = new HashMap<>();
        try{
            userService.findAllDC();
            map.put("message","导出成功");
            map.put("status","200");
        }catch (Exception e){
            map.put("message","导出失败");
            map.put("status","404");
        }
        return map;

    }

    @RequestMapping("getUserData")
    @ResponseBody
    public HashMap<String, Object> getUserData(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"));
        map.put("boys", Arrays.asList(userService.queryTJ("男","1"),
                userService.queryTJ("男","2"),
                userService.queryTJ("男","3"),
                userService.queryTJ("男","4"),
                userService.queryTJ("男","5"),
                userService.queryTJ("男","6"),
                userService.queryTJ("男","7"),
                userService.queryTJ("男","8"),
                userService.queryTJ("男","9"),
                userService.queryTJ("男","10"),
                userService.queryTJ("男","11"),
                userService.queryTJ("男","12")));

        map.put("girls", Arrays.asList(userService.queryTJ("女","1"),
                userService.queryTJ("女","2"),
                userService.queryTJ("女","3"),
                userService.queryTJ("女","4"),
                userService.queryTJ("女","5"),
                userService.queryTJ("女","6"),
                userService.queryTJ("女","7"),
                userService.queryTJ("女","8"),
                userService.queryTJ("女","9"),
                userService.queryTJ("女","10"),
                userService.queryTJ("女","11"),
                userService.queryTJ("女","12")));
        System.out.println(map);

        return map;
    }


    @RequestMapping("getChinaData")
    @ResponseBody
    public ArrayList<Sc> getChinaData(){

        ArrayList<Sc> scs = new ArrayList<>();

        ArrayList<City> boyCitys = new ArrayList<>();
        boyCitys.add(new City("河北",userService.queryStatus("河北","男")));
        boyCitys.add(new City("山西",userService.queryStatus("山西","男")));
        boyCitys.add(new City("辽宁",userService.queryStatus("辽宁","男")));
        boyCitys.add(new City("吉林",userService.queryStatus("吉林","男")));
        boyCitys.add(new City("黑龙江",userService.queryStatus("黑龙江","男")));
        boyCitys.add(new City("江苏",userService.queryStatus("江苏","男")));
        boyCitys.add(new City("浙江",userService.queryStatus("浙江","男")));
        boyCitys.add(new City("安徽",userService.queryStatus("安徽","男")));
        boyCitys.add(new City("福建",userService.queryStatus("福建","男")));
        boyCitys.add(new City("江西",userService.queryStatus("江西","男")));
        boyCitys.add(new City("山东",userService.queryStatus("山东","男")));
        boyCitys.add(new City("河南",userService.queryStatus("河南","男")));
        boyCitys.add(new City("湖北",userService.queryStatus("湖北","男")));
        boyCitys.add(new City("湖南",userService.queryStatus("湖南","男")));
        boyCitys.add(new City("广东",userService.queryStatus("广东","男")));
        boyCitys.add(new City("四川",userService.queryStatus("四川","男")));
        boyCitys.add(new City("贵州",userService.queryStatus("贵州","男")));
        boyCitys.add(new City("云南",userService.queryStatus("云南","男")));
        boyCitys.add(new City("海南",userService.queryStatus("海南","男")));
        boyCitys.add(new City("陕西",userService.queryStatus("陕西","男")));
        boyCitys.add(new City("甘肃",userService.queryStatus("甘肃","男")));
        boyCitys.add(new City("青海",userService.queryStatus("青海","男")));
        boyCitys.add(new City("台湾",userService.queryStatus("台湾","男")));
        boyCitys.add(new City("北京",userService.queryStatus("北京","男")));
        boyCitys.add(new City("上海",userService.queryStatus("上海","男")));
        boyCitys.add(new City("天津",userService.queryStatus("天津","男")));
        boyCitys.add(new City("重庆",userService.queryStatus("重庆","男")));
        boyCitys.add(new City("广西",userService.queryStatus("广西","男")));
        boyCitys.add(new City("内蒙古",userService.queryStatus("内蒙古","男")));
        boyCitys.add(new City("西藏",userService.queryStatus("西藏","男")));
        boyCitys.add(new City("宁夏",userService.queryStatus("宁夏","男")));
        boyCitys.add(new City("新疆",userService.queryStatus("新疆","男")));
        boyCitys.add(new City("香港",userService.queryStatus("香港","男")));
        boyCitys.add(new City("澳门",userService.queryStatus("澳门","男")));


        ArrayList<City> gitlCitys = new ArrayList<>();

        gitlCitys.add(new City("河北",userService.queryStatus("河北","女")));
        gitlCitys.add(new City("山西",userService.queryStatus("山西","女")));
        gitlCitys.add(new City("辽宁",userService.queryStatus("辽宁","女")));
        gitlCitys.add(new City("吉林",userService.queryStatus("吉林","女")));
        gitlCitys.add(new City("黑龙江",userService.queryStatus("黑龙江","女")));
        gitlCitys.add(new City("江苏",userService.queryStatus("江苏","女")));
        gitlCitys.add(new City("浙江",userService.queryStatus("浙江","女")));
        gitlCitys.add(new City("安徽",userService.queryStatus("安徽","女")));
        gitlCitys.add(new City("福建",userService.queryStatus("福建","女")));
        gitlCitys.add(new City("江西",userService.queryStatus("江西","女")));
        gitlCitys.add(new City("山东",userService.queryStatus("山东","女")));
        gitlCitys.add(new City("河南",userService.queryStatus("河南","女")));
        gitlCitys.add(new City("湖北",userService.queryStatus("湖北","女")));
        gitlCitys.add(new City("湖南",userService.queryStatus("湖南","女")));
        gitlCitys.add(new City("广东",userService.queryStatus("广东","女")));
        gitlCitys.add(new City("四川",userService.queryStatus("四川","女")));
        gitlCitys.add(new City("贵州",userService.queryStatus("贵州","女")));
        gitlCitys.add(new City("云南",userService.queryStatus("云南","女")));
        gitlCitys.add(new City("海南",userService.queryStatus("海南","女")));
        gitlCitys.add(new City("陕西",userService.queryStatus("陕西","女")));
        gitlCitys.add(new City("甘肃",userService.queryStatus("甘肃","女")));
        gitlCitys.add(new City("青海",userService.queryStatus("青海","女")));
        gitlCitys.add(new City("台湾",userService.queryStatus("台湾","女")));
        gitlCitys.add(new City("北京",userService.queryStatus("北京","女")));
        gitlCitys.add(new City("上海",userService.queryStatus("上海","女")));
        gitlCitys.add(new City("天津",userService.queryStatus("天津","女")));
        gitlCitys.add(new City("重庆",userService.queryStatus("重庆","女")));
        gitlCitys.add(new City("广西",userService.queryStatus("广西","女")));
        gitlCitys.add(new City("内蒙古",userService.queryStatus("内蒙古","女")));
        gitlCitys.add(new City("西藏",userService.queryStatus("西藏","女")));
        gitlCitys.add(new City("宁夏",userService.queryStatus("宁夏","女")));
        gitlCitys.add(new City("新疆",userService.queryStatus("新疆","女")));
        gitlCitys.add(new City("香港",userService.queryStatus("香港","女")));
        gitlCitys.add(new City("澳门",userService.queryStatus("澳门","女")));


        scs.add(new Sc("小男孩",boyCitys));
        scs.add(new Sc("小姑娘",gitlCitys));

        System.out.println(scs);

        return scs;
    }



}
