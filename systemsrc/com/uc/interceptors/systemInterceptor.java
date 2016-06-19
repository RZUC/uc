package com.uc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.uc.system.model.User;

/**
 * @ClassName: SessionInterceptor
 * @Description: 过滤所有的数据
 * @author Administrator
 * @date 2016年6月19日 下午7:18:07
 */
public class systemInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse rep,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		// User u = (User) req.getSession().getAttribute("user");
		// System.out.println(u);
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep,
			Object arg2) throws Exception {
		User u = (User) req.getSession().getAttribute("user");
		System.out.println(u);
		if (u == null) {
			System.out.println("用户为空");
			return false;
		}
		return true;
	}

}
