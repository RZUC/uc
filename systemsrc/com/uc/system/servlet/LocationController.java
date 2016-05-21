package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

/**
 * @author Simple 这个类主要做地域的 增加<br>
 *         删除<br>
 *         修改<br>
 *         合并<br>
 *         排序<br>
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController extends GeneralController {
	@Resource
	LocationService service;

	@RequestMapping(value = "/showLocationByFatherId")
	public void showLocationByfatherID(
			@RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Location> list = service.findLocationByFatherId(fatherID);

		getJsonStrDataByList(list, response);
	}

	/**
	 * @Title: showProvince
	 * @Description: 查询所有的省
	 * @param @param fatherID
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/showProvince")
	public void showProvince(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Location> list = service.findProvince();
		getJsonStrDataByList(list, response);
	}

	/**
	 * @Title: addLocations
	 * @Description: 添加多个地点
	 * @param @param fatherID
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/add")
	public void addLocations(@RequestBody Location newLocation,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Location ob = service.add(newLocation);
		getJsonStrByObject(ob, response);
	}

	/**
	 * @Title: updateLocations
	 * @Description: 更新地域数据信息
	 * @param @param fatherID
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/update")
	public void updateLocations(@RequestBody Location updataLocation,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Location ob = service.updata(updataLocation);
		getJsonStrByObject(ob, response);
	}

	/**
	 * @Title: deleteLocations
	 * @Description: 删除地域点
	 * @param @param fatherID
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/del")
	public void deleteLocations(
			@RequestParam(value = "locationId", required = false, defaultValue = "") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == id && "".equals(id)) {
			getBooleanJSon(false, "can not find id [" + id + "]", response);
		}

		boolean state = service.deleteById(id);

		getBooleanJSon(state, "", response);
	}
}
