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
import com.uc.system.model.SearchQuery;
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

	@RequestMapping(value = "/search")
	public void show(@RequestBody SearchQuery query,
			HttpServletResponse response) throws Exception {
		int totalPage = 0;

		System.out.println("关键词：" + query.getWord());
		System.out.println("开始时间：" + query.getStartTime());
		System.out.println("结束时间：" + query.getEndTime());
//		System.out.println("地域：" + query.getLocationId());
		System.out.println("类型：" + query.getPolicyTypeId());
//		System.out.println("行业：" + query.getIndustryId());
		System.out.println("页码：" + query.getPageNum());
		System.out.println("每页条数：" + query.getPageSize());
		//1.地域--如果有上级目录，那么需要添加到上级目录中去 地柜，如果有上级那么添加上级
		
		
		try {
			Page page = new Page(query.getPageSize(), query.getPageNum());
			List<PolicyInfo> list = service.findList(2, page);
			List<PolicyInfoView> view = service.getViewList(list);

			totalPage = getTotalPage(query.getPageSize(),
					service.getTotalCount(2, page));

			getJsonStrDataByList(view, "显示数据：" + query.getPolicyTypeId(),
					totalPage, query.getPageNum(), true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "数据失败：" + 2, totalPage,
					query.getPageNum(), false, response);
		}
	}

	private int getTotalPage(int pageSize, int total) {
		return (total % pageSize == 0) ? total / pageSize
				: (total / pageSize + 1);
	}

}
