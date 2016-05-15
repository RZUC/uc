package com.uc.system.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Industry;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.Query;
import com.uc.system.model.User;
import com.uc.system.service.UserService;

/**
 * @Description: TODO(维护用户（非系统操作员）系统) <br>
 *               用户信息维护 增加<br>
 *               删除<br>
 *               修改<br>
 *               查询：用户类型 区域 行业时间 名称<br>
 * @ClassName: UserController
 * @author 落花流水
 * @date 2016年4月21日 上午6:59:24
 */
@Controller
@RequestMapping(value = "/systemUser")
public class UserController extends GeneralController {
	@Resource
	UserService userService;

	@RequestMapping(value = "/show")
	public void show(@RequestBody Query query, @RequestBody Page page,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		System.out.println("展示用户信息");
		getJsonStrByList(userService.findAllUsers(query, page), response);
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("add user");
		Message message = userService.add(user);
		getBooleanJSon(message.isState(), message.getMessage(), response);
	}

	@RequestMapping(value = "/del")
	public void delete(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("del Industry");
		if (!"".equals(id)) {
			Message message = userService.del(id);
			getBooleanJSon(message.isState(), message.getMessage(), response);
		}

	}

	@RequestMapping(value = "/modify")
	public void modify(@RequestBody User user, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("modify Industry");
		Message message = userService.modify(user);
		getBooleanJSon(message.isState(), message.getMessage(), response);
	}
}
