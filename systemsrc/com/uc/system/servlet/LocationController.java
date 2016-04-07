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

@Controller
@RequestMapping(value = "/location")
public class LocationController extends GeneralController
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
    
    // @RequestMapping(value = "/showAllLocation")
    // public void showAllLocation(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
    // @RequestParam(value = "type", required = false, defaultValue = "") String type, HttpServletResponse arg1)
    // throws Exception
    // {
    //
    // Map<String, Object> map = new HashMap();
    //
    // getJsonStrByObject(map, arg1);
    // }
    
    /**
     * 
     * @Title: showNature
     * @Description: TODO(查询事件下性质的趋势和关键点)
     * @param @param eventId
     * @param @param type
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/showNature")
    public void showNature(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        HttpServletResponse arg1)
        throws Exception
    {
        getJsonStrByObject(null, arg1);
    }
    
    /**
     * 
     * @Title: updataEventPoint
     * @Description: TODO(添加事件关键点)
     * @param @param ob
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/addEventPoint")
    public void addEventPoint(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "type", required = false, defaultValue = "") String type,
        @RequestParam(value = "time", required = false, defaultValue = "") String time,
        @RequestParam(value = "content", required = false, defaultValue = "") String content, HttpServletResponse arg1)
        throws Exception
    {
        boolean flag = false;
        
        getBooleanJSon(flag, REQUESTStrOK, arg1);
    }
    
    /**
     * 
     * @Title: updataNaturePoint
     * @Description: TODO(添加编辑性质关键点)
     * @param @param ob
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/addNaturePoint")
    public void addNaturePoint(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "type", required = false, defaultValue = "") String type,
        @RequestParam(value = "time", required = false, defaultValue = "") String time,
        @RequestParam(value = "content", required = false, defaultValue = "") String content, HttpServletResponse arg1)
        throws Exception
    {
        
    }
    
    /**
     * 
     * @Title: deleteEventPoint
     * @Description: TODO(删除事件转折点)
     * @param @param eventId
     * @param @param type
     * @param @param time
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/deleteEventPoint")
    public void deleteEventPoint(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "type", required = false, defaultValue = "") String type,
        @RequestParam(value = "time", required = false, defaultValue = "") String time,
        @RequestParam(value = "content", required = false, defaultValue = "") String content, HttpServletResponse arg1)
        throws Exception
    {
    }
    
    /**
     * 
     * @Title: deleteNaturePoint
     * @Description: TODO(删除舆情转折点)
     * @param @param eventId
     * @param @param type
     * @param @param time
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/deleteNaturePoint")
    public void deleteNaturePoint(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "type", required = false, defaultValue = "") String type,
        @RequestParam(value = "time", required = false, defaultValue = "") String time,
        @RequestParam(value = "content", required = false, defaultValue = "") String content, HttpServletResponse arg1)
        throws Exception
    {
    }
    
    /**
     * @Decription:TODO(查询该事件下的趋势)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/showImpactTop10")
    public void showImpactTop(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "time", required = false, defaultValue = "") String time, HttpServletResponse arg1)
        throws Exception
    {
        
    }
    
    /**
     * @Decription:TODO(获取与处理网媒数据)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return List
     */
    // public List getMediaInfo(List<SolrMedia> media, Similarity similarity)
    // {
    // return null;
    // }
    
    /**
     * @Decription:TODO(获取与处理微博数据)
     * @param weibo
     * @param similarity
     * @throws ExceptionTODO
     * @Exception
     * @return List
     */
}
