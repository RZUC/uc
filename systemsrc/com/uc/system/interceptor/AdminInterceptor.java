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
package com.uc.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uc.system.model.AdminUser;

/**
 * @Description: 后台管理登录拦截器
 * @ClassName: SessionInterceptor
 * @author 落花流水
 * @date 2016年1月27日 下午4:44:54
 */
public class AdminInterceptor extends HandlerInterceptorAdapter
{
    private String LOGINURL = "/admin.do";
    
    private String UNLOGINURL = "/adminLogin.do";
    
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
        String baseContenxtParth = arg0.getSession().getServletContext().getContextPath();
        System.out.println("--"+arg0.getSession().getServletContext().getRequestDispatcher("/adc.do").toString());
        boolean result = true;
        
        HttpSession session = arg0.getSession();
        AdminUser user = null;
        try
        {
            user = (AdminUser)session.getAttribute("admin");
            
            System.out.println(arg0.getSession().getServletContext().getContextPath());
        }
        catch (NullPointerException e)
        {
            System.out.println("请先登录");
            
//            e.printStackTrace();
        }
//        if (null == user)
//        {
//            arg1.sendRedirect(baseContenxtParth + "/app/www/manager-login.html");
//        }
        return result;
    }
    
}
