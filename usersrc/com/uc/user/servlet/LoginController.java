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
package com.uc.user.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.LogType;
import com.uc.system.model.User;
import com.uc.system.service.LogService;
import com.uc.system.servlet.GeneralController;

import net.sf.json.JSONObject;

/**
 * @Description: 前端用户登录
 * @ClassName: LoginController
 * @author 落花流水
 * @date 2016年4月21日 上午7:00:16
 */
@Controller(value = "userLogin")
@RequestMapping(value = "/user")
public class LoginController extends GeneralController {
	// @Resource
	// LoginService loginService;

	@Resource
	LogService logService;

	@RequestMapping(value = "/login")
	public void login(
			@RequestParam(value = "username", required = false, defaultValue = "") String username,
			@RequestParam(value = "password", required = false, defaultValue = "") String password,
			HttpServletResponse resp, HttpServletRequest req) {
		// User user = loginService.login(username, password);
		User user = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user != null) {
			// resultMap.put("state", true);
			// resultMap.put("message", user.getName());
			//
			// logService.addLog(LogType.LOGIN, user.getName()
			// + "\t 登入系统\t\t [IP:" + req.getRemoteAddr() + "]");
		} else {
			resultMap.put("state", false);
			resultMap.put("message", "用户名或者密码错误");
		}

		JSONObject json = JSONObject.fromObject(resultMap);
		getJsonStrByString(json.toString(), resp);
		setSession(user, req);
	}

	private void setSession(User user, HttpServletRequest req) {
		req.getSession().setAttribute("user", user);
	}

	@RequestMapping(value = "/loginout")
	protected void loginOut(HttpSession session, HttpServletResponse resp)
			throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("state", true);
			resultMap.put("message", "退出成功");

			session.setAttribute("user", null);
			logService.addLog(LogType.LOGOUT, "\t 登出系统\t\t [IP:" + "]");
		} catch (Exception e) {
			resultMap.put("state", false);
			resultMap.put("message", "退出失败");
		}

		JSONObject json = JSONObject.fromObject(resultMap);
		getJsonStrByString(json.toString(), resp);
	}

}
