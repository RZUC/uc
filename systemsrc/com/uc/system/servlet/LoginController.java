/** 
 * @Title: HelloWorldController.java 
 * @Package com.zhiwei.mvcDemo.servlet 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Administrator 
 * @date 2016年1月13日 下午5:04:27 
 * @version V1.0 
 */
/**
 * 
 */
package com.uc.system.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uc.system.model.AdminUser;

/**
 * @ClassName: LoginController
 * @Description: 登录，主页面,用户个性化登录界面
 * @author Administrator
 * @date 2016年1月13日 下午5:04:27
 */
@Controller
@RequestMapping(value = "/admin")
public class LoginController extends GeneralController {

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest req) {
		AdminUser user = new AdminUser();
		user.setName(username);
		user.setPassword(password);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user != null) {
			resultMap.put("message", user.getName());
			resultMap.put("state", true);
			resultMap.put("data", user);
			setSession(user, req);
		} else {
			resultMap.put("state", false);
			resultMap.put("message", "用户名或者密码错误");
			resultMap.put("data", null);
		}
		return JSONObject.fromObject(resultMap).toString();
	}

	private void setSession(AdminUser user, HttpServletRequest req) {
		req.getSession().setAttribute("admin", user);
	}

	@ResponseBody
	@RequestMapping(value = "/loginout", produces = "text/html;charset=UTF-8")
	public String loginOut(HttpSession session, HttpServletResponse resp)
			throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("state", true);
			resultMap.put("message", "退出成功");
			resultMap.put("data", null);
			session.setAttribute("user", null);
		} catch (Exception e) {
			resultMap.put("state", false);
			resultMap.put("message", "退出失败");
			resultMap.put("data", null);
		}
		return JSONObject.fromObject(resultMap).toString();
	}
}
