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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uc.system.dao.LocationDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Location;

/**
 * @ClassName: LoginController
 * @Description: 欢迎页面，跳转用
 * @author Administrator
 * @date 2016年1月13日 下午5:04:27
 */
@Controller
public class WelcomeController extends GeneralController {
	@Resource
	LocationDao location;

	@RequestMapping(value = "/manager")
	public String manager(HttpServletResponse arg1, HttpServletRequest arg2)
			throws Exception {
		Map<String, String> locationMap = getLocationMap();
		if (arg2.getSession().getAttribute("location") == null) {
			arg2.getSession().setAttribute("location", locationMap);
		}
		return "redirect:/app/www/manager-policy.html";
	}

	private Map<String, String> getLocationMap() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (Location l : location.findAll()) {
				map.put(l.getId() + "", l.getAbbreviation());
			}
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletResponse arg1, HttpServletRequest arg2)
			throws Exception {
//		if (arg2.getSession().getAttribute("location") == null) {
//			Map<String, String> locationMap = getLocationMap();
//			arg2.getSession().getServletContext().setAttribute("location", locationMap);
//		}
		return "redirect:/app/www/index.html";
	}
}
