package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Message;
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
		try {
			List<PolicyType> list = service.findAll();
			getJsonStrDataByList(list, "查询政策类型成功",1,1, true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "查询政策类型失败",1,1, false, response);
		}
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PolicyType ob = service.addPolicyType(policyType);
			getJsonStrDataByObject(ob, "添加政策类别成功", true, response);
		} catch (Exception e) {
			getJsonStrDataByObject(null, "添加政策类别失败", false, response);
		}
	}

	@RequestMapping(value = "/del")
	public void delete(
			@RequestParam(value = "id", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Message message = service.delPolicyType(id);

		try {
			getJsonStrDataByObject(null, message.getMessage(),
					message.isState(), response);
		} catch (Exception e) {
			getJsonStrDataByObject(null, message.getMessage(),
					message.isState(), response);
		}
	}

	@RequestMapping(value = "/modify")
	public void modify(@RequestBody PolicyType policyType,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			PolicyType ob = service.modiyfPolicyType(policyType);
			getJsonStrDataByObject(ob, "修改政策类别成功", true, response);
		} catch (Exception e) {
			getJsonStrDataByObject(null, "修改政策类别失败", false, response);
		}
	}
}
