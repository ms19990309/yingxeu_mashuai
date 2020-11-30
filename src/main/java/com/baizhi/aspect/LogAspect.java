package com.baizhi.aspect;

import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
@Service
@Transactional
public class LogAspect {

    @Resource
    HttpServletRequest request;

    @Resource
    LogMapper logMapper;

    //@Around("execution(* com.baizhi.serviceImpl.*.*(..)) && !execution(* com.baizhi.serviceImpl.*.query*(..))")
    @Around("@annotation(com.baizhi.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){


        //谁  时间  操作  成功

        //获取用户数据
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String message= null;
        Object result =null;
        //放行方法
        try {
            result = proceedingJoinPoint.proceed();

            String s = result.toString();

            message="success";
        } catch (Throwable throwable) {
            message="error";
        }

        Log log = new Log(UUID.randomUUID().toString(), admin.getUsername(),new Date(),methodName+" ("+value+")",message,admin.getId());

        System.out.println("数据入库"+log);
        logMapper.insertSelective(log);


        return result;
    }


}
