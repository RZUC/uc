/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年2月2日
    * @version 1.00 
*/
package com.zhiwei.wjgg.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.model.LogType;
import com.zhiwei.wjgg.service.impl.LogServiceImpl;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SleepHelper
 * @author 落花流水
 * @date 2016年2月2日 上午10:50:22
 */
@Component
@Aspect
public class LogAop
{
    @Resource
    private LogServiceImpl logService;
    
    public LogAop()
    {
    }
    
    @Pointcut("execution(* com.zhiwei.manager.dao..*.insert*(..))")
    public void insert()
    {
    }
    
    @Around(value = "insert()")
    public Object insertAround(ProceedingJoinPoint point)
    {
        logService.addLog(LogType.ADD, "" + getLogContent(point));
        try
        {
            return point.proceed();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @Pointcut("execution(* com.zhiwei.manager.dao..*.find*(..))")
    public void find()
    {
    }
    
    public Object findAround(ProceedingJoinPoint point)
    {
        logService.addLog("查询", "" + getLogContent(point));
        try
        {
            return point.proceed();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @Pointcut("execution(* com.zhiwei.wjgg.analy.ThreadRun..*.analysis(..))")
    public void analysis()
    {
    }
    
    public Object analysisAround(ProceedingJoinPoint point)
    {
        Object obj = null;
        try
        {
            long start = System.currentTimeMillis();
            
            obj = point.proceed();
            
            long end = System.currentTimeMillis();
            
            System.out.println("运行时间：" + (end - start) / 1000 + "\t秒");
            
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    
    @Pointcut("execution(* com.zhiwei.manager.dao..*.remove*(..)) and !execution(* com.zhiwei.manager.dao.LogDao..*.remove*(..))")
    public void remove()
    {
    }
    
    @Around(value = "remove()")
    public Object removeAround(ProceedingJoinPoint point)
    {
        logService.addLog(LogType.DELETE, "" + getLogContent(point));
        try
        {
            return point.proceed();
        }
        catch (Throwable e)
        {
            logService.addLog(LogType.ERROR, "" + getLogContent(point));
            e.printStackTrace();
        }
        return null;
    }
    
    @Pointcut("execution(* com.zhiwei.manager.servlet.LoginController*.*(..))")
    public void login()
    {
    }
    
    @Around(value = "login()")
    public Object loginAround(ProceedingJoinPoint point)
    {
        logService.addLog(LogType.LOGIN, "" + getLogContent(point));
        try
        {
            return point.proceed();
        }
        catch (Throwable e)
        {
            logService.addLog(LogType.ERROR, "" + getLogContent(point));
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @Title: getLogContent
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param point 设定文件
     * @return void 返回类型
     */
    private Map getLogContent(ProceedingJoinPoint point)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        MethodSignature signature = (MethodSignature)point.getSignature();
        
        Object[] objs = point.getArgs();
        
        map.put("执行的类", point.getTarget().getClass().getSimpleName());
        map.put("执行的方法", signature.getMethod().getName());
        
        // 具体参数
        Map<String, Object> argsMap = new HashMap<String, Object>();
        for (int i = 0; i < objs.length; i++)
        {
            Object obj = objs[i];
            Map<String, Object> argValueMap = new HashMap<String, Object>();
            if (obj instanceof String || obj instanceof Integer || obj instanceof Boolean || obj instanceof Character
                || obj instanceof Long || obj instanceof Double || obj instanceof Byte || obj instanceof Short
                || obj instanceof Float)
            {
                argValueMap.put("参数", obj.toString());
            }
            else
            {
                Method[] methods = obj.getClass().getMethods();
                for (Method m : methods)
                {
                    if (m.getName().contains("get"))
                    {
                        try
                        {
                            argValueMap.put(m.getName().replaceAll("get", "").toLowerCase(), m.invoke(obj, null));
                        }
                        catch (IllegalArgumentException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            argsMap.put("参数类型：" + obj.getClass().getSimpleName(), argValueMap);
        }
        map.put("具体参数", argsMap);
        
        return map;
    }
}
