package com.zhiwei.wjgg.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.model.EventKeyPoint;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.model.NatureKeyPoint;
import com.zhiwei.wjgg.model.Similarity;
import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.model.SolrWeibo;
import com.zhiwei.wjgg.model.SolrWeixin;
import com.zhiwei.wjgg.util.MidAurl;
import com.zhiwei.wjgg.util.UrlUtil;

@Controller
@RequestMapping(value = "/voice")
public class VoiceController extends GeneralController
{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static final String MEDIA_TYPE = "media";
    
    private static final String WEIBO_TYPE = "weibo";
    
    private static final String WEIXIN_TYPE = "weixin";
    
    private static final int TOP = 9999;// 返回上限
    
    /**
     * 
     * @Title: showEvent
     * @Description: TODO(查询事件下的趋势和关键点)
     * @param @param eventId
     * @param @param type
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/showEvent")
    public void showEvent(@RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
        @RequestParam(value = "type", required = false, defaultValue = "") String type, HttpServletResponse arg1)
        throws Exception
    {
        
        Map<String, Object> map = new HashMap();
        
        getJsonStrByObject(map, arg1);
    }
    
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
    public List getMediaInfo(List<SolrMedia> media, Similarity similarity)
    {
        return null;
    }
    
    /**
     * @Decription:TODO(获取与处理微博数据)
     * @param weibo
     * @param similarity
     * @throws ExceptionTODO
     * @Exception
     * @return List
     */
}
