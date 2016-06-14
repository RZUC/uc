package com.uc.user.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uc.system.service.UserService;
import com.uc.system.servlet.GeneralController;

/**
 * 
 * @ClassName: ManagerController 用户信息维护 增加<br>
 *             删除<br>
 *             修改<br>
 *             查询：用户类型 区域 行业时间 名称<br>
 */
@Controller
@RequestMapping(value = "/usercustom")
public class UserController extends GeneralController {
	//TDOD:1.政策服务 --1.通知，要闻，文件，专题，收藏
	//TODO:2.用户管理--基本信息（用户注册基本字段），详细信息，如果没有就先选择类型，有了之后就只能修改原来的类型
	//TODO:3.左侧列表自动生成，点击链接显示信息
	@Resource
	UserService userService;

	@RequestMapping(value = "/show")
	public void show(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		// getJsonStrByList(userService.findAllUsers(), response);
	}
}
