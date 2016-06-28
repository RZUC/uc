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

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uc.system.model.User;

/**
 * @Description: 用户登录的拦截器
 * @ClassName: SessionInterceptor
 * @author 落花流水
 * @date 2016年1月27日 下午4:44:54
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
	private String LOGINURL = "http://localhost:8088/m/login.do";

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("this is afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("this is postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println(arg0.getRequestURL());
		boolean result = false;
		User user;
		try {
			user = (User) arg0.getSession().getAttribute("user");
			if (null != user) {
				System.out.println(user);
				result = true;
			}
			System.out.println("访问的连接：" + arg0.getRequestURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Writer t = arg1.getWriter();
		
		//todo:验证，如果当前用户没有登录，提示未登录，跳转回首页
		return result;
	}

}
