package com.uc.system.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Department;
import com.uc.system.model.Industry;
import com.uc.system.model.Location;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.PolicyInfoView;
import com.uc.system.service.DeparmentService;
import com.uc.system.service.IndustryService;
import com.uc.system.service.LocationService;
import com.uc.system.service.PolicyService;

/**
 * @author Simple
 * 
 */
/**
 * @Description: 政策信息的维护<br>
 *               增加<br>
 *               删除<br>
 *               修改<br>
 *               推到头条<br>
 *               取消头条<br>
 *               查询：多个查询条件，类型，时间，区域，行业，关键词<br>
 * @ClassName: PolicyInfoController
 * @author 落花流水
 * @date 2016年4月19日 下午9:17:14
 */
@Controller
@RequestMapping(value = "/parameter")
public class SystemParameterController extends GeneralController {
	@Resource
	LocationService locationService;

	@Resource
	IndustryService industryService;

	@Resource
	DeparmentService departmentService;

	@RequestMapping(value = "/location")
	public void showLocation(
			@RequestParam(value = "fatherId", required = false, defaultValue = "") int fatherId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Location> list = locationService.findLocationByFatherId(fatherId);
		getJsonStrDataByList(list, "返回地域信息", 1, 1, true, response);
	}

	@RequestMapping(value = "/industry")
	public void showIndustry(
			@RequestParam(value = "fatherId", required = false, defaultValue = "") int fatherId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Industry> list = industryService.findByFatherID(fatherId);
		getJsonStrDataByList(list, "返回行业信息", 1, 1, true, response);
	}

	@RequestMapping(value = "/department")
	public void showDepartment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Department> list = departmentService.getDeparmentList();
		getJsonStrDataByList(list, "返回行业信息", 1, 1, true, response);
	}

}
