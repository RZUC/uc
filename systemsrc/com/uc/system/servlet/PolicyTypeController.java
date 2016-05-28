package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Page;
import com.uc.system.model.PolicyType;
import com.uc.system.service.PolicyTypeService;

/**
 * @author Simple 政策类型 增加<br>
 *         删除<br>
 *         修改<br>
 *         排序<br>
 */
@Controller
@RequestMapping(value = "/policyType")
public class PolicyTypeController extends GeneralController {
	@Resource
	PolicyTypeService service;

	@RequestMapping(value = "/show")
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<PolicyType> list = service.findAll();
		getJsonStrDataByList(list, response);
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO:新增数据：返回message
		getJsonStrByObject(service.addPolicyType(policyType), response);
	}

	@RequestMapping(value = "/del")
	public void delete(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("PolicyType del");
		// TODO:根据id删除数据，返回message
		service.delPolicyType(id);
	}

	@RequestMapping(value = "/modify")
	public void modify(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("PolicyType modify");
		// TODO: 修改数据
		getJsonStrByObject(service.modiyfPolicyType(policyType), response);
	}
}
