package com.uc.system.servlet;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.Industry;
import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

/**
 * @author Simple
 * 行业的操作
 * 增加<br>
 * 删除<br>
 * 修改<br>
 * 合并<br>
 * 排序<br>
 */
@Controller
@RequestMapping(value = "/industry")
public class IndustryController extends GeneralController
{
    @Resource
    LocationService service;
    
    @RequestMapping(value = "/showLocationByFatherId")
    public void showIndustryByfatherID(
        @RequestParam(value = "fatherID", required = false, defaultValue = "") String fatherID,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
//        List<Industry> list = service.findLocationByFatherId(fatherID);
        
//        getJsonStrDataByList(list, response);
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
