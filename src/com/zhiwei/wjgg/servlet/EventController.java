package com.zhiwei.wjgg.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.model.User;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.LoginService;
import com.zhiwei.wjgg.solr.model.DataMedia;
import com.zhiwei.wjgg.solr.model.DataWeiBo;
import com.zhiwei.wjgg.solr.model.DataWeiXin;
import com.zhiwei.wjgg.solr.service.impl.SolrDataCommonService;
import com.zhiwei.wjgg.util.MapUtil;
import com.zhiwei.wjgg.util.SolrDocumentToBeanUtil;
import com.zhiwei.wjgg.util.TreatOrder;

@Controller
@RequestMapping(value = "/event")
public class EventController extends GeneralController
{

    private static final Integer TOP = 10;

    @Resource
    EventService eventService;

    @Resource
    LoginService loginService;

    @Resource(name = "solrMediaService")
    SolrDataCommonService<HUserInfoBD> serviceMedia;

    @Resource(name = "solrWeiboService")
    SolrDataCommonService<HUserInfoWB> serviceWeibo;

    @Resource(name = "solrWeiXinService")
    SolrDataCommonService<HUserInfoWX> serviceWeixin;

    /**
     * 
     * @Decription:TODO(查询该事件)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/show")
    public void showEvent(
            @RequestParam(value = "id", required = false, defaultValue = "") String id,
            HttpServletResponse arg1) throws Exception
    {
        Event ob = eventService.findOneById(id);

        getJsonStrByObject(ob, arg1);
    }

    /**
     * 
     * @Decription:TODO(查询该事件)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/than")
    public void thanEvent(
            @RequestParam(value = "eventType", required = false, defaultValue = "") String eventType,
            @RequestParam(value = "eventLevel", required = false, defaultValue = "") String eventLevel,
            HttpServletResponse arg1, HttpServletRequest arg2) throws Exception
    {
        User user = (User) arg2.getSession().getAttribute("user");
        List<Event> list = null;
        if ((!"".equals(eventType) && null != eventType)
                && ("".equals(eventLevel) || null == eventLevel))
        {
            list = eventService.findEventsByType(eventType, user.getId());
        }
        else if (("".equals(eventType) || null == eventType)
                && (!"".equals(eventLevel) && null != eventLevel))
        {
            list = eventService.findEventsByLevel(eventLevel, user.getId());
        }
        else if (!"".equals(eventType) && null != eventType
                && !"".equals(eventLevel) && null != eventLevel)
        {
            list = eventService.findEventsByTypeAndLevel(eventType, eventLevel,
                    user.getId());
        }
        else
        {
            list = eventService.findEventsByUser(user.getId());
        }

        getJsonStrByList(list, arg1);
    }

    @RequestMapping(value = "/search")
    public void searchInfo(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "word", required = false, defaultValue = "") String word,
            HttpServletResponse arg1, HttpServletRequest arg2) throws Exception
    {
        Event event = eventService.findOneById(eventId);
        event.setWord(word);
        // 网媒
        List<Map<String,Object>> media = serviceMedia.getTopByH(event, TOP);

        // 微信
        List<Map<String,Object>> weixin = serviceWeixin.getTopByH(event, TOP);

        // 微博
        List<Map<String,Object>> weibo = serviceWeibo.getTopByH(event, TOP);

        Map<String,List> result = new HashMap();
        
        result.put("media", media);
        result.put("weixin", weixin);
        result.put("weibo", weibo);
        
        getJsonStrByObject(result, arg1);
    }

}
