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

	@RequestMapping(value = "/showAllPolicyType")
	public void showLocationByfatherID(@RequestBody Page page,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("showAllPolicyType");
		List<PolicyType> list = service.findByPage(page);
		getJsonStrDataByList(list, response);
	}

	@RequestMapping(value = "/add")
	public void addLocations(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO:新增数据：返回message
		System.out.println("PolicyType add");
		service.addPolicyType(policyType);
	}

	@RequestMapping(value = "/del")
	public void deleteLocations(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("PolicyType del");
		// TODO:根据id删除数据，返回message
		service.delPolicyType(id);
	}

	@RequestMapping(value = "/modify")
	public void modifyLocations(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("PolicyType modify");
		// TODO: 修改数据
		service.modiyfPolicyType(policyType);
	}
}
