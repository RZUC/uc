package com.uc.system.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ManagerController
 * @Description: 后台管理界面
 * @author Administrator
 * @date 2016年6月13日 下午9:26:41
 */
@Controller
public class ManagerController extends GeneralController {

	@RequestMapping(value = "/admin.do")
	public String show(HttpServletResponse arg1, HttpServletRequest arg2)
			throws Exception {
		return "redirect:/app/www/manager-login.html";
//		return "redirect:/app/www/manager-user.html";
	}
}
