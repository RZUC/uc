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
 *         
 *         后台管理模块
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController extends GeneralController {
	@Resource
	LocationService service;

	@RequestMapping(value = "/showLocationByFatherId")
	public void showLocationByfatherID(
			@RequestParam(value = "fatherID", required = false, defaultValue = "") int fatherID,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			List<Location> list = service.findLocationByFatherId(fatherID);
			getJsonStrDataByList(list, "获取地域信息成功",1,1, true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "获取地域信息失败",1,1, false, response);
		}
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
		try {
			List<Location> list = service.findProvince();
			getJsonStrDataByList(list, "获取省份信息成功",1,1, true, response);
		} catch (Exception e) {
			getJsonStrDataByList(null, "获取省份信息失败",1,1, false, response);
		}
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

		try {
			Location ob = service.add(newLocation);
			getJsonStrDataByObject(ob, "添加地域信息成功", true, response);
		} catch (Exception e) {
			getJsonStrDataByObject(null, "添加地域信息失败", false, response);
		}
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

		try {
			Location ob = service.updata(updataLocation);
			getJsonStrDataByObject(ob, "修改地域信息成功", true, response);
		} catch (Exception e) {
			getJsonStrDataByObject(null, "修改地域信息失败", false, response);
		}
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
			getJsonStrDataByObject(null, "ID为空", false, response);
		} else {
			getJsonStrDataByObject(null, "删除地域信息", service.deleteById(id),
					response);
		}
	}
}
