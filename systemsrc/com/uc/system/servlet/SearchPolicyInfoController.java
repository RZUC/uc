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

import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.PolicyInfoView;
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
@RequestMapping(value = "/searchpolicyInfo")
public class SearchPolicyInfoController extends GeneralController {
	@Resource
	PolicyService service;

	@RequestMapping(value = "/showType")
	public void showType(
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<PolicyInfoView> view;
		try {
			List<PolicyInfo> list = new ArrayList<PolicyInfo>();
			if ("top".equals(type)) {
				list = service.findListByTop(10);
			} else {
				Page page = new Page();
				page.setPageNum(1);
				page.setPageSize(10);
				list = service.findList(Integer.valueOf(type), page);
			}
			view = service.getViewList(list);
			getJsonStrDataByList(view, "显示数据：" + type, 1, 1, true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "显示数据失败：" + type, 1, 1, false, response);
		}

	}

	@RequestMapping(value = "/show")
	public void show(
			@RequestParam(value = "type", required = false, defaultValue = "") int type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "") int pageSize,
			HttpServletResponse response) throws Exception {
		int totalPage = 0;
		try {
			Page page = new Page(pageSize, pageNum);
			List<PolicyInfo> list = service.findList(type, page);
			List<PolicyInfoView> view = service.getViewList(list);

			totalPage = getTotalPage(pageSize,
					service.getTotalCount(type, page));

			getJsonStrDataByList(view, "显示数据：" + type, totalPage, pageNum,
					true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "数据失败：" + type, totalPage, pageNum,
					false, response);
		}
	}

	private int getTotalPage(int pageSize, int total) {
		return (total % pageSize == 0) ? total / pageSize
				: (total / pageSize + 1);
	}

}
