package com.uc.system.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.User;
import com.uc.system.service.UserService;

/**
 * @author Simple
 * 
 */
/**
 * @Description: 用户维护<br>
 *               增加<br>
 *               删除<br>
 *               修改<br>
 */
@Controller
@RequestMapping(value = "/userManager")
public class UserManagerController extends GeneralController {

	@Resource
	UserService userService;

	// 返回用户列表，创建时间排序，分页
	@RequestMapping(value = "/userList")
	public ResponseEntity<Map> showType(Page page) {
		if (page.getPageSize() == 0) {
			page.setPageSize(5);
		}
		List<User> userList = userService.findAllUsers(null, page);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message", "用户列表");
		message.put("state", true);
		message.put("data", userList);
		message.put("totalPage", userService.totalCount());
		message.put("currentPage", page.getPageNum());
		return new ResponseEntity<Map>(message, HttpStatus.OK);
	}

	// 查询单个用户
	@RequestMapping(value = "/detail")
	public ModelAndView showDetail(User user) {
		User userFind = userService.findByUid(user.getId());
		ModelAndView model = new ModelAndView();
//		model.addObject("managerUserAdd", userFind);
		model.addObject("user",userFind);
		model.setViewName("managerUserAdd");
		// Map<String, Object> messageMap = new HashMap<String, Object>();
		// messageMap.put("message", "用户详细信息");
		// messageMap.put("state", userFind != null ? true : false);
		// messageMap.put("data", userFind);
		// messageMap.put("totalPage", 1);
		// messageMap.put("currentPage", 1);
		return model;
	}

	// 查询单个用户
	@RequestMapping(value = "/delete")
	public ResponseEntity<Map> deleteUser(User user) {
		Message message = userService.del(user.getId());
		Map<String, Object> messageMap = new HashMap<String, Object>();
		messageMap.put("message", message.getMessage());
		messageMap.put("state", message.isState());
		messageMap.put("data", null);
		messageMap.put("totalPage", 1);
		messageMap.put("currentPage", 1);
		return new ResponseEntity<Map>(messageMap, HttpStatus.OK);
	}

	// 查询单个用户
	@RequestMapping(value = "/update")
	public ResponseEntity<Map> updateUser(User user) {
		User userNew = userService.modify(user);
		Map<String, Object> messageMap = new HashMap<String, Object>();
		messageMap.put("message", "用户更新");
		messageMap.put("state", userNew != null ? true : false);
		messageMap.put("data", userNew);
		messageMap.put("totalPage", 1);
		messageMap.put("currentPage", 1);
		return new ResponseEntity<Map>(messageMap, HttpStatus.OK);
	}
}
