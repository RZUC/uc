package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

/**
 * @author Simple
 * 政策信息的维护
 * 增加<br>
 * 删除<br>
 * 修改<br>
 * 查询：多个查询条件，类型，时间，区域，行业，关键词<br>
 */
@Controller
@RequestMapping(value = "/policyInfo")
public class PolicyInfoController extends GeneralController
{
    @Resource
    LocationService service;
    
    @RequestMapping(value = "/showLocationByFatherId")
    public void showLocationByfatherID(
        @RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List<Location> list = service.findLocationByFatherId(fatherID);
        
        getJsonStrDataByList(list, response);
    }
    
    /**
     * @Title: showProvince
     * @Description: 显示所有的省
     * @param @param fatherID
     * @param @param request
     * @param @param response
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/showProvince")
    public void showProvince(@RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
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
    public void addLocations(@RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
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
    @RequestMapping(value = "/del")
    public void deleteLocations(@RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
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
    @RequestMapping(value = "/modify")
    public void modifyLocations(@RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        List<Location> list = service.findProvince();
        
        getJsonStrDataByList(list, response);
    }
}
