/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月27日
    * @version 1.00 
*/
package com.zhiwei.uc.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Description: Session 拦截器
 * @ClassName: SessionInterceptor
 * @author 落花流水
 * @date 2016年1月27日 下午4:44:54
 */
public class SessionInterceptor extends HandlerInterceptorAdapter
{
    private String LOGINURL = "http://localhost:8088/m/login.do";
    
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception
    {
        System.out.println("this is afterCompletion");
    }
    
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
        throws Exception
    {
        System.out.println("this is postHandle");
    }
    
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2)
        throws Exception
    {   
        System.out.println(arg0.getRequestURL());
        boolean result = true;
//        if (LOGINURL.equals(arg0.getRequestURL().toString()))
//        {
//            System.out.println("允许访问的连接：" + LOGINURL);
//            result =  true;
//        }
//        else
//        {   
//            User user;
//            try
//            {
//                user = (User)arg0.getSession().getAttribute("user");
//                if(null!=user){
//                    result = true;
//                }
//                System.out.println("访问的连接：" + arg0.getRequestURL());
//                System.out.println("用户：" +user.toString());
//            }
//            catch (Exception e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        return result;
    }
        
    
}
