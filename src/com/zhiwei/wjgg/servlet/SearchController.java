package com.zhiwei.wjgg.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventType;
import com.zhiwei.wjgg.model.User;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTypeService;

/**
 * 
 * @ClassName: SearchController
 * @Description: TODO(搜索首页)
 * @author chenweitao
 * @date 2016年3月9日 上午10:05:07
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends GeneralController
{

    @Resource
    EventService eventService;

    @Resource
    EventTypeService eventTypeService;

    /**
     * 
     * @Title: show 
     * @Description: TODO(查询推荐搜索，近期热点，事件分类，第一个事件分类的事件列表) 
     * @param @param arg1
     * @param @throws Exception 设定文件 
     * @return void 返回类型
     */
    @RequestMapping(value = "/show")
    public void show(HttpServletResponse arg1,HttpServletRequest arg2) throws Exception
    {
        User user = (User) arg2.getSession().getAttribute("user");
        List all = new ArrayList();
        //推荐搜索
        List<String> word = new ArrayList<String>();
        word.add("微信");
        word.add("红包");
        word.add("收费");
        word.add("腾讯");
        word.add("马化腾");
        
        //近期热点
        List<Event> newEvents = eventService.findEventsNearTime(user.getId());
        
        //事件分类
        List<EventType> eventTypes = eventTypeService.findByUser(user.getId());
        
        //第一个事件分类的事件列表
        List<Event> eventByType = null;
        if (0<eventTypes.size())
        {
            eventByType = eventService.findEventsByType(eventTypes.get(0).getId(),user.getId());
        }
        all.add(word);
        all.add(newEvents);
        all.add(eventTypes);
        all.add(eventByType);
        
        getJsonStrByList(all, arg1);
    }

    /**
     * 
     * @Title: showRecommend
     * @Description: TODO(推荐词)
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
//    @RequestMapping(value = "/showRecommend")
//    public void showRecommend(HttpServletResponse arg1) throws Exception
//    {
//        List<String> word = new ArrayList<String>();
//        word.add("微信");
//        word.add("红包");
//        word.add("收费");
//        word.add("腾讯");
//        word.add("马化腾");
//        getJsonStrByList(word, arg1);
//    }

    /**
     * @Title: showEvents
     * @Description: TODO(最新的事件排序)
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
//    @RequestMapping(value = "/showNearEvents")
//    public void showEvents(HttpServletResponse arg1) throws Exception
//    {
//        getJsonStrByList(eventService.findEventsNearTime(), arg1);
//    }

    /**
     * 
     * @Title: searchEvents
     * @Description: TODO(搜索)
     * @param @param word
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/searchEvents")
    public void searchEvents(
            @RequestParam(value = "word", required = false, defaultValue = "") String word,
            HttpServletResponse arg1,HttpServletRequest arg2) throws Exception
    {
        User user = (User) arg2.getSession().getAttribute("user");
        getJsonStrByList(eventService.fuzzyQueryByWord(word,user.getId()), arg1);
    }

    /**
     * 
     * @Title: showEventsByType
     * @Description: TODO(根据分类查询事件)
     * @param @param eventType
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/showEventsByType")
    public void showEventsByType(
            @RequestParam(value = "eventType", required = false, defaultValue = "") String eventType,
            HttpServletResponse arg1,HttpServletRequest arg2) throws Exception
    {
        User user = (User) arg2.getSession().getAttribute("user");
        getJsonStrByList(eventService.findEventsByType(eventType,user.getId()), arg1);
    }

    /**
     * 
     * @Title: showEventType
     * @Description: TODO(查询所有的事件分类)
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
//    @RequestMapping(value = "/showEventType")
//    public void showEventType(HttpServletResponse arg1) throws Exception
//    {
//        getJsonStrByList(eventTypeService.findAll(), arg1);
//    }
}
