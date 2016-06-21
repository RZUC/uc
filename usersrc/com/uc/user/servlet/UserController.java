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

import com.uc.system.model.Favorite;
import com.uc.system.model.PolicyType;
import com.uc.system.model.User;
import com.uc.system.service.FavoriteService;
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

	@Resource
	FavoriteService favoriteService;

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
		map1.put("url", "/collectList.do?uid=" + uid);
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
	public ResponseEntity<Map> collectList(@RequestParam(value = "uid") int uid)
			throws Exception {
		List<Favorite> dataList = favoriteService.findFavoriteByUid(uid);
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
	public ResponseEntity<Map> collect(@RequestParam(value = "uid") int uid,
			@RequestParam(value = "policyInfoId") int policyInfoId,
			@RequestParam(value = "summary") String summary) throws Exception {

		Favorite f = favoriteService.collect(new Favorite(uid, policyInfoId,
				summary));
		Map<String, Object> message = new HashMap<String, Object>();
		if (f != null) {
			message.put("message", "为用户【" + uid + "】添加：" + policyInfoId + "收藏");
			message.put("state", true);
			message.put("data", f);
			message.put("totalPage", 1);
			message.put("currentPage", 1);
		} else {
			message.put("message", "为用户【" + uid + "】添加：" + policyInfoId + "收藏");
			message.put("state", false);
			message.put("data", null);
			message.put("totalPage", 1);
			message.put("currentPage", 1);
		}

		return new ResponseEntity<Map>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/update")
	public ResponseEntity<Map> update(User user) {
		//TODO:更新用户数据，企业信息和服务机构信息未填写
		user = userService.modify(user);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message", "用户更新");
		message.put("state", true);
		message.put("data", user);
		message.put("totalPage", 1);
		message.put("currentPage", 1);
		return new ResponseEntity<Map>(message, HttpStatus.OK);
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
	@RequestMapping(value = "/uncollect")
	public ResponseEntity<Map> unCollect(
			@RequestParam(value = "favoriteId") String favoriteId)
			throws Exception {

		boolean f = favoriteService.unCollect(favoriteId);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("message", "删除收藏\t" + f);
		message.put("state", f);
		message.put("data", null);
		message.put("totalPage", 1);
		message.put("currentPage", 1);

		return new ResponseEntity<Map>(message, HttpStatus.OK);
	}
}
