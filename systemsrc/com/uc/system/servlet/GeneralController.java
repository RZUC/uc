/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月22日
 * @version 1.00 
 */
package com.uc.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: GeneralController
 * @author 落花流水
 * @date 2016年1月22日 上午11:40:32
 */
public abstract class GeneralController {
	final static String REQUESTStrOK = "OK";

	final static String REQUESTStrFAIL = "FAIL";

	protected Logger log = LoggerFactory.getLogger(GeneralController.class);

	protected void getJsonStrByString(String json, HttpServletResponse arg1) {
		arg1.setContentType("text/html;charset=utf-8");
		PrintWriter p;
		try {
			p = arg1.getWriter();
			p.write(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void getBooleanJSon(boolean state, String message,
			HttpServletResponse arg1) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("message", message);
		resultJson.put("state", state);
		getJsonStrByString(resultJson.toString(), arg1);
	}

	protected void getJsonStrByObject(Object obj, HttpServletResponse arg1) {
		if (obj != null) {
			getJsonStrByString(JSONObject.fromObject(obj).toString(), arg1);
		} else {
			getJsonStrByString("", arg1);
		}
	}

	protected void getJsonStrByList(List list, HttpServletResponse arg1) {
		if (list != null) {
			getJsonStrByString(JSONArray.fromObject(list).toString(), arg1);
		} else {
			getJsonStrByString("", arg1);
		}
	}

	/**
	 * @Title: getJsonStrDataByList
	 * @Description: 返回Data数据
	 * @param @param list
	 * @param @param arg1 设定文件
	 * @return void 返回类型
	 */
	protected void getJsonStrDataByList(List list, String message,
			int totalPage, int currentPage, boolean state,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("message", message);
		map.put("state", state);
		map.put("data", list);
		map.put("totalPage", totalPage);
		map.put("currentPage", currentPage);
		getJsonStrByString(JSONObject.fromObject(map).toString(), response);
	}

	/**
	 * @Title: getJsonStrDataByObject
	 * @Description: 返回单个数据
	 * @param @param list
	 * @param @param arg1 设定文件
	 * @return void 返回类型
	 */
	protected void getJsonStrDataByObject(Object obj, String message,
			boolean state, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("message", message);
		map.put("state", state);
		map.put("data", obj);
		getJsonStrByString(JSONObject.fromObject(map).toString(), response);
	}
}
