package com.uc.system.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uc.system.model.User;

/**
 * 
 * @ClassName: ManagerController
 * @Description: TODO(后台管理界面)
 * @author chenweitao
 * @date 2016年2月29日 下午5:30:34
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController extends GeneralController
{
    private static final String FALSE_STATE = "0";
    
    /**
     * 
     * @Decription:TODO(查询该用户下正在分析的事件、事件分类、等级)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/show")
    public String show(HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
    	return "redirect:/app/www/manager-user.html";
    }
    
    /**
     * 
     * @Decription:TODO(查询当前用已经分析完成的事件)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     * @author 落花流水
     */
    @RequestMapping(value = "/showCompleteEvent")
    public void showCompleteEvent(HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
        User user = (User)arg2.getSession().getAttribute("user");
    }
    
    /**
     * @Decription:TODO(查询该用户下正在分析的事件)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/showEvent")
    public void showEvent(HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
        User user = (User)arg2.getSession().getAttribute("user");
    }
    
    /**
     * 
     * @Decription:TODO(查询事件等级)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/showLevel")
    public void showEventLevel(HttpServletResponse arg1)
        throws Exception
    {
    }
    
    /**
     * 
     * @Decription:TODO(查询事件分类)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/showType")
    public void showEventType(HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
        User user = (User)arg2.getSession().getAttribute("user");
        // getJsonStrByList(eventTypeService.findByUser(user.getId()), arg1);
    }
    
    /**
     * @Decription:TODO(修改事件状态)
     * @param ob
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/updataEvent")
    public void updataEvent(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        HttpServletResponse arg1)
        throws Exception
    {
    }
    
    /**
     * @Decription:TODO(删除事件)
     * @param eventId
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/deleteEvent")
    public void deleteEvent(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        HttpServletResponse arg1)
        throws Exception
    {
    }
    
    /**
     * @Decription:TODO(删除事件)
     * @param ob
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/deleteType")
    public void deleteEventType(@RequestParam(value = "typeId", required = false, defaultValue = "") String typeId,
        HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
        User user = (User)arg2.getSession().getAttribute("user");
    }
    
    /**
     * 
     * @Decription:TODO(添加新的事件)
     * @param ob
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    // @RequestMapping(value = "/addEvent")
    // public void addEvent(@RequestBody Event ob, HttpServletResponse arg1, HttpServletRequest arg2)
    // throws Exception
    // {
    // User user;
    // String userId = null;
    //
    // try
    // {
    // user = (User)arg2.getSession().getAttribute("user");
    // userId = user.getId();
    // }
    // catch (Exception e)
    // {
    // userId = "系统测试员";
    // }
    //
    // }
    
    /**
     * 
     * @Decription:TODO(添加新的事件等级)
     * @param ob
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/addLevel")
    public void addEventLevel(@RequestParam(value = "level", required = false, defaultValue = "") String level,
        HttpServletResponse arg1)
        throws Exception
    {
        
        // ob.setEventLevel(level);
    }
    
    /**
     * 
     * @Decription:TODO(添加新的事件分类)
     * @param ob
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/addType")
    public void addEventType(@RequestParam(value = "type", required = false, defaultValue = "") String type,
        HttpServletResponse arg1, HttpServletRequest arg2)
        throws Exception
    {
        User user = (User)arg2.getSession().getAttribute("user");
        // ob.setEventType(type);
        // ob.setUserId(user.getId());
    }
}
