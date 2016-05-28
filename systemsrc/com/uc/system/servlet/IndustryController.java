package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Industry;
import com.uc.system.model.Message;
import com.uc.system.service.IndustryService;

/**
 * @author Simple 行业的操作 增加<br>
 *         删除<br>
 *         修改<br>
 *         排序<br>
 */
@Controller
@RequestMapping(value = "/industry")
public class IndustryController extends GeneralController {
	@Resource
	IndustryService service;

	@RequestMapping(value = "/show")
	public void show(
			@RequestParam(value = "fatherId", required = false, defaultValue = "") int fatherId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Industry> list = service.findByFatherID(fatherId);
		getJsonStrDataByList(list, response);
	}

	@RequestMapping(value = "/showLeveOne")
	public void showLeveOne(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Industry> list = service.findLevelOne();
		getJsonStrDataByList(list, response);
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody Industry industry, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("add Industry");
		// 返回一個实体
		industry = service.add(industry);
		getJsonStrByObject(industry, response);
	}

	@RequestMapping(value = "/del")
	public void delete(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("del Industry");
		if (!"".equals(id)) {
			Message message = service.del(id);
		}

	}

	@RequestMapping(value = "/modify")
	public void modify(@RequestBody Industry industry,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("modify Industry");
		industry = service.modify(industry);
		getJsonStrByObject(industry, response);
	}

}
