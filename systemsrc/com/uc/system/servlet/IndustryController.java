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
import com.uc.system.model.Page;
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
	public void show(@RequestBody Page page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("show Industry");
		List<Industry> list = service.findByPage(page);

		getJsonStrDataByList(list, response);
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody Industry industry, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("add Industry");
		Message message = service.add(industry);
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
		Message message = service.modify(industry);
	}

}
