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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.User;
import com.uc.system.service.UserService;
import com.uc.system.servlet.GeneralController;
import com.uc.system.util.MD5Util;

/**
 * @Description: 前端用户登录
 * @ClassName: LoginController
 * @author 落花流水
 * @date 2016年4月21日 上午7:00:16
 */
@Controller(value = "userLogin")
@RequestMapping(value = "/user")
public class LoginController extends GeneralController {
	@Resource
	UserService userService;

	@RequestMapping(value = "/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpSession session) {
		User user = userService.findByusernameAndPassword(username,
				MD5Util.getMd5(password));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (user != null) {
			resultMap.put("state", true);
			resultMap.put("message", "登录成功");
			resultMap.put("data", user);
		} else {
			resultMap.put("state", false);
			resultMap.put("message", "用户名或者密码错误");
		}

		setSession(user, session);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private void setSession(User user, HttpSession session) {
		session.setAttribute("user", user);
	}

	@RequestMapping(value = "/loginout")
	protected ResponseEntity<Map<String, Object>> loginOut(HttpSession session,
			HttpServletResponse resp) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("message", "退出成功");
			resultMap.put("state", true);
			session.setAttribute("user", null);
		} catch (Exception e) {
			resultMap.put("message", "退出失败");
			resultMap.put("state", false);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

}
