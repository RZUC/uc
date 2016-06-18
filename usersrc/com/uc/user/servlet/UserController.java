package com.uc.user.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uc.system.model.PolicyInfo;
import com.uc.system.model.PolicyType;
import com.uc.system.model.User;
import com.uc.system.service.PolicyTypeService;
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
	// TDOD:1.政策服务 --1.通知，要闻，文件，专题，收藏
	// TODO:2.用户管理--基本信息（用户注册基本字段），详细信息，如果没有就先选择类型，有了之后就只能修改原来的类型
	// TODO:3.左侧列表自动生成，点击链接显示信息
	// TODO:4.收藏收藏列表
	@Resource
	UserService userService;
	@Resource
	PolicyTypeService service;

	@RequestMapping(value = "/show")
	public void show(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		// getJsonStrByList(userService.findAllUsers(), response);
	}

	@RequestMapping(value = "/register")
	public @ResponseBody Map<String, Object> addPolicyInfo(User user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		user = userService.register(user);
		if (null != user) {
			map.put("message", "注册成功");
			map.put("state", true);
			map.put("data", user);
		} else {
			map.put("message", "注册失败");
			map.put("state", false);
			map.put("data", user);
		}
		return map;
	}

	/**
	 * @Title: navigation
	 * @Description: 左边的导航数据
	 * @param @param uid
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return ResponseEntity<Map> 返回类型
	 */
	@RequestMapping(value = "/navigation")
	public ResponseEntity<Map> navigation(
			@RequestParam(value = "uid") String uid) throws Exception {
		// TODO:g根据用户查询用户ID， 通过用户ID获取查询关键词，添加到链接
		User user = userService.findByusernameAndPassword("宁波优策", "123456");
		List<Map> dataList = new ArrayList<Map>();
		Map map1 = new HashMap();
		map1.put("url", "/collect.do?uid=" + uid);
		map1.put("name", "我的收藏");
		dataList.add(map1);
		List<PolicyType> list = service.findAll();
		for (PolicyType type : list) {
			if (!type.getName().equals("政策头条")) {
				Map map = new HashMap();
				map.put("url",
						"search/province=" + user.getProvince() + "&city="
								+ user.getCity() + "&downtown="
								+ user.getDowntown());
				map.put("name", type.getName());
				dataList.add(map);
			}
		}

		Map map2 = new HashMap();
		map2.put("基本信息", "base");
		dataList.add(map2);
		Map map3 = new HashMap();
		map3.put("详细信息", "详细信息");
		dataList.add(map3);

		Map<String, Object> usertype = new HashMap<String, Object>();
		usertype.put("message", "返回导航列表");
		usertype.put("state", true);
		usertype.put("data", dataList);
		usertype.put("totalPage", 1);
		usertype.put("currentPage", 1);
		return new ResponseEntity<Map>(usertype, HttpStatus.OK);
	}

	@RequestMapping(value = "/collectList")
	public ResponseEntity<Map> collectList(
			@RequestParam(value = "uid") String uid) throws Exception {

		List<Map> dataList = new ArrayList<Map>();
		Map map;
		for (int i = 0; i < 10; i++) {
			map = new HashMap();
			map.put("title", "这里个标题\t" + i);
			map.put("url", "www.baidu.com\t" + i);
			dataList.add(map);
		}

		Map<String, Object> usertype = new HashMap<String, Object>();
		usertype.put("message", "返回" + uid + "的收藏");
		usertype.put("state", true);
		usertype.put("data", dataList);
		usertype.put("totalPage", 1);
		usertype.put("currentPage", 1);
		return new ResponseEntity<Map>(usertype, HttpStatus.OK);
	}

	/**
	 * @Title: collect
	 * @Description: 添加收藏
	 * @param @param uid
	 * @param @param policyInfoId
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return ResponseEntity<Map> 返回类型
	 */
	@RequestMapping(value = "/collect")
	public ResponseEntity<Map> collect(@RequestParam(value = "uid") String uid,
			@RequestParam(value = "policyInfoId") String policyInfoId)
			throws Exception {

		// 从session中获取用户数据，User,
		// 2.从用户数据获取查询参数
		// TODO:这里需要一个collect的Service,用来处理收藏

		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message", "为用户【" + uid + "】添加：" + policyInfoId + "收藏");
		message.put("state", true);
		message.put("data", null);
		message.put("totalPage", 1);
		message.put("currentPage", 1);
		return new ResponseEntity<Map>(message, HttpStatus.OK);
	}
}
