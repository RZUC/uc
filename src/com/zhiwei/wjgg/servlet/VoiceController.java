package com.zhiwei.wjgg.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.zhiwei.wjgg.service.EventKeyPointService;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.HUserInfoBDService;
import com.zhiwei.wjgg.service.HUserInfoWBService;
import com.zhiwei.wjgg.service.HUserInfoWXService;
import com.zhiwei.wjgg.service.NatureKeyPointService;
import com.zhiwei.wjgg.service.NatureTrendService;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.service.SolrMediaDataService;
import com.zhiwei.wjgg.service.SolrWeiboDataService;
import com.zhiwei.wjgg.service.SolrWeixinDataService;
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

    private static final int TOP = 9999;//返回上限

    @Resource
    EventService eventService;

    @Resource
    EventKeyPointService eventKeyPointService;

    @Resource
    EventTrendService eventTrendService;

    @Resource
    NatureKeyPointService natureKeyPointService;

    @Resource
    NatureTrendService natureTrendService;

    @Resource
    HUserInfoBDService hUserInfoBDService;

    @Resource
    HUserInfoWBService hUserInfoWBService;

    @Resource
    HUserInfoWXService hUserInfoWXService;

    @Resource
    SolrMediaDataService solrMediaDataService;

    @Resource
    SolrWeiboDataService solrWeiboDataService;

    @Resource
    SolrWeixinDataService solrWeixinDataService;

    @Resource
    SimilarityService similarityService;

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
    public void showEvent(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            HttpServletResponse arg1) throws Exception
    {

        Map<String, Object> map = new HashMap();
        
        map.put("Trend", eventTrendService.findByEventIdInTime(eventId, type));
        map.put("Point",
                eventKeyPointService.findByEventIdInTime(eventId, type));
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
    public void showNature(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            HttpServletResponse arg1) throws Exception
    {
        Event event = eventService.findOneById(eventId);

        String ZHENGtype = "1";
        String ZHONGtype = "3";
        String FUtype = "2";
        if (event.isCorrelation())
        {
            Map map = new HashMap();

            Map trend = new HashMap();
            trend.put("ZHENGtype",
                    natureTrendService.findByEventIdInTime(eventId, ZHENGtype));
            trend.put("ZHONGtype",
                    natureTrendService.findByEventIdInTime(eventId, ZHONGtype));
            trend.put("FUtype",
                    natureTrendService.findByEventIdInTime(eventId, FUtype));
            map.put("Trend", trend);

            Map point = new HashMap();
            point.put("ZHENGtype", natureKeyPointService.findByEventIdInTime(
                    eventId, ZHENGtype));
            point.put("ZHONGtype", natureKeyPointService.findByEventIdInTime(
                    eventId, ZHONGtype));
            point.put("FUtype",
                    natureKeyPointService.findByEventIdInTime(eventId, FUtype));

            map.put("Point", point);
            getJsonStrByObject(map, arg1);
        }
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
    public void addEventPoint(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "time", required = false, defaultValue = "") String time,
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            HttpServletResponse arg1) throws Exception
    {
        boolean flag = false;

        EventKeyPoint old_ob = eventKeyPointService.findOneById(eventId + type);
        List<String> contentList = new ArrayList<String>();
        contentList.add(content);
        Map<String, List<String>> time_content = new HashMap<String, List<String>>();
        time_content.put(time, contentList);

        EventKeyPoint ob = new EventKeyPoint();
        ob.setEventId(eventId);
        ob.setId(eventId + type);
        ob.setTime_content(time_content);
        ob.setType(type);

        if (old_ob != null)
        {
            flag = eventKeyPointService.addInfoByOb(ob);
        }
        else
        {
            flag = eventKeyPointService.add(ob);
        }
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
    public void addNaturePoint(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "time", required = false, defaultValue = "") String time,
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            HttpServletResponse arg1) throws Exception
    {
        boolean flag = false;
        NatureKeyPoint old_ob = natureKeyPointService.findOneById(eventId
                + type);
        List<String> contentList = new ArrayList<String>();
        contentList.add(content);
        Map<String, List<String>> time_content = new HashMap<String, List<String>>();
        time_content.put(time, contentList);

        NatureKeyPoint ob = new NatureKeyPoint();
        ob.setEventId(eventId);
        ob.setId(eventId + type);
        ob.setTime_content(time_content);
        ob.setType(type);

        if (old_ob != null)
        {
            flag = natureKeyPointService.addInfoByOb(ob);
        }
        else
        {
            flag = natureKeyPointService.add(ob);
        }
        getBooleanJSon(flag, REQUESTStrOK, arg1);
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
    public void deleteEventPoint(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "time", required = false, defaultValue = "") String time,
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            HttpServletResponse arg1) throws Exception
    {
        EventKeyPoint old_ob = eventKeyPointService.findOneById(eventId + type);

        Map<String, List<String>> time_content = new HashMap<String, List<String>>();
        List<String> contentList = new ArrayList<String>();
        contentList.add(content);

        time_content.put(time, contentList);
        old_ob.setTime_content(time_content);

        getBooleanJSon(eventKeyPointService.deleteInfoByOb(old_ob),
                REQUESTStrOK, arg1);
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
    public void deleteNaturePoint(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "time", required = false, defaultValue = "") String time,
            @RequestParam(value = "content", required = false, defaultValue = "") String content,
            HttpServletResponse arg1) throws Exception
    {
        NatureKeyPoint old_ob = natureKeyPointService.findOneById(eventId
                + type);
        Map<String, List<String>> time_content = new HashMap<String, List<String>>();
        List<String> contentList = new ArrayList<String>();
        contentList.add(content);

        time_content.put(time, contentList);
        old_ob.setTime_content(time_content);
        getBooleanJSon(natureKeyPointService.deleteInfoByOb(old_ob),
                REQUESTStrOK, arg1);
    }

    /**
     * @Decription:TODO(查询该事件下的趋势)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return void
     */
    @RequestMapping(value = "/showImpactTop10")
    public void showImpactTop(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            @RequestParam(value = "time", required = false, defaultValue = "") String time,
            HttpServletResponse arg1) throws Exception
    {
        // 取出该事件的相似传播
        Similarity similarity = similarityService.findOneById(eventId);

        // ------------ 网媒-------------
        EventTrend mediaTrend = eventTrendService.findByEventIdInTime(eventId,
                MEDIA_TYPE);
        List<String> mediaUrls = mediaTrend.getTime_num().get(time);

        List mediaList = new ArrayList();
        if (mediaUrls != null)
        {
            List<SolrMedia> media = solrMediaDataService
                    .findObByIdList(mediaUrls);
            mediaList = getMediaInfo(media, similarity);
        }

        // ------------ 微信-------------
        EventTrend weixinTrend = eventTrendService.findByEventIdInTime(eventId,
                WEIXIN_TYPE);
        List<String> weixinUrls = weixinTrend.getTime_num().get(time);

        List weixinList = new ArrayList();
        if (weixinUrls != null)
        {
            List<SolrWeixin> weixin = solrWeixinDataService
                    .findObByIdList(weixinUrls);
            weixinList = getWeixinInfo(weixin, similarity);
        }

        // ------------ 微博-------------
        EventTrend weiboTrend = eventTrendService.findByEventIdInTime(eventId,
                WEIBO_TYPE);
        List<String> weiboUrls = weiboTrend.getTime_num().get(time);

        List weiboList = new ArrayList();
        if (weiboUrls != null)
        {
            List<SolrWeibo> weibo = solrWeiboDataService
                    .findObByIdList(weiboUrls);
            weiboList = getWeiboInfo(weibo);
        }

        Map map = new HashMap();
        map.put("media", mediaList);
        map.put("weixin", weixinList);
        map.put("weibo", weiboList);

        getJsonStrByObject(map, arg1);

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

        Map<String, String> media_url_mainUrl = new HashMap<String, String>();
        Map<String, String> media_url_title = new HashMap<String, String>();
        Map<String, String> media_url_time = new HashMap<String, String>();
        Map<String, String> media_url_source = new HashMap<String, String>();

        List<String> mediaMainUrl = new ArrayList<String>();

        for (SolrMedia solrMedia : media)
        {
            // 切出主域名
            media_url_mainUrl.put(solrMedia.getId(),
                    UrlUtil.getMainUrl(solrMedia.getId()));
            // 取出标题
            media_url_title.put(solrMedia.getId(), solrMedia.getTitle());

            // 取出时间
            media_url_time.put(solrMedia.getId(),
                    sdf.format(solrMedia.getTime()));

            // 取出来源
            media_url_source.put(solrMedia.getId(), solrMedia.getSource());
        }
        mediaMainUrl.addAll(media_url_mainUrl.values());

        // 网媒的相似传播
        Map<String, Integer> media_similarity = similarity.getMedia();

        // 用域名取出TOP10的传播途径的H因子
        List<HUserInfoBD> Hmedia = hUserInfoBDService.findByIdList(
                mediaMainUrl, TOP);

        // H因子的id列表
        List<String> hid = new ArrayList<String>();
        for (HUserInfoBD hm : Hmedia)
        {
            hid.add(hm.getId());
        }

        // 封装成输出列表
        List mediaList = new ArrayList();

        List<String> noHUrls = new ArrayList<String>();// 无H因子数据的url
        for (HUserInfoBD Hm : Hmedia)
        {
            // 获取该传播源的数据url
            List<String> urlList = new ArrayList<String>();

            for (Entry<String, String> en : media_url_mainUrl.entrySet())
            {
                // 若无h因子的记录
                if (!hid.contains(en.getValue())
                        && !noHUrls.contains(en.getKey()))
                {
                    noHUrls.add(en.getKey());
                }

                if (Hm.getId().equals(en.getValue()))
                {
                    urlList.add(en.getKey());
                }

            }
            // 将数据添加到输出列表
            for (String url : urlList)
            {
                Map mediaMap = new HashMap();
                mediaMap.put("url", url);
                mediaMap.put("title", media_url_title.get(url));
                mediaMap.put("time", media_url_time.get(url));
                mediaMap.put("impact", Hm.getChannelIndex());
                mediaMap.put("source", media_url_source.get(url));
                mediaMap.put("similarity",
                        media_similarity.get(media_url_title.get(url)));
                mediaList.add(mediaMap);
            }
        }

        //预防若无H因子时   无数据
        if (0==Hmedia.size())
        {
            for (SolrMedia solrMedia : media)
            {
                Map mediaMap = new HashMap();
                mediaMap.put("url", solrMedia.getId());
                mediaMap.put("title", solrMedia.getTitle());
                mediaMap.put("time", sdf.format(solrMedia.getTime()));
                mediaMap.put("impact", 0);
                mediaMap.put("source", solrMedia.getSource());
                mediaMap.put("similarity",
                        media_similarity.get(solrMedia.getTitle()));
                mediaList.add(mediaMap);
            
            }
        }
        
        // 将无H数据添加到输出列表
        for (String url : noHUrls)
        {
            Map mediaMap = new HashMap();
            mediaMap.put("url", url);
            mediaMap.put("title", media_url_title.get(url));
            mediaMap.put("time", media_url_time.get(url));
            mediaMap.put("impact", 0);
            mediaMap.put("source", media_url_source.get(url));
            mediaMap.put("similarity",
                    media_similarity.get(media_url_title.get(url)));
            mediaList.add(mediaMap);
        }

        return mediaList;
    }

    /**
     * @Decription:TODO(获取与处理微信数据)
     * @param arg1
     * @throws ExceptionTODO
     * @Exception
     * @return List
     */
    public List getWeixinInfo(List<SolrWeixin> weixin, Similarity similarity)
    {

        Map<String, String> weixin_url_mainUrl = new HashMap<String, String>();
        Map<String, String> weixin_url_title = new HashMap<String, String>();
        Map<String, String> weixin_url_time = new HashMap<String, String>();
        Map<String, String> weixin_url_source = new HashMap<String, String>();
        List<String> weixinMainUrl = new ArrayList<String>();

        for (SolrWeixin solrWeixin : weixin)
        {
            if (!solrWeixin.getType().contains(","))
            {
                // 切出主域名
                weixin_url_mainUrl
                        .put(solrWeixin.getId(), solrWeixin.getType());
            }
            else
            {
                // 切出主域名
                weixin_url_mainUrl.put(solrWeixin.getId(),
                        UrlUtil.getOpenid(solrWeixin.getType()));
            }

            // 取出标题
            weixin_url_title.put(solrWeixin.getId(), solrWeixin.getTitle());

            // 取出时间
            weixin_url_time.put(solrWeixin.getId(),
                    sdf.format(solrWeixin.getTime()));

            // 取出来源
            weixin_url_source.put(solrWeixin.getId(), solrWeixin.getSource());
        }
        weixinMainUrl.addAll(weixin_url_mainUrl.values());

        // 相似传播
        Map<String, Integer> weixin_similarity = similarity.getWeixin();

        // 用域名取出TOP10的传播途径的H因子
        List<HUserInfoWX> Hweixin = hUserInfoWXService.findByIdList(
                weixinMainUrl, TOP);

        // H因子的id列表
        List<String> hid = new ArrayList<String>();
        for (HUserInfoWX hm : Hweixin)
        {
            hid.add(hm.getId());
        }

        List<String> noHUrls = new ArrayList<String>();// 无H因子数据的url
        // 封装成输出列表
        List weixinList = new ArrayList();
        for (HUserInfoWX Hwx : Hweixin)
        {
            // 获取该传播源的数据url
            List<String> urlList = new ArrayList<String>();
            for (Entry<String, String> en : weixin_url_mainUrl.entrySet())
            {
                // 若无h因子的记录
                if (!hid.contains(en.getValue())
                        && !noHUrls.contains(en.getKey()))
                {
                    noHUrls.add(en.getKey());
                }
                //若有h因子的记录
                if (Hwx.getId().equals(en.getValue()))
                {
                    urlList.add(en.getKey());
                }
            }
            // 将数据添加到输出列表
            for (String url : urlList)
            {
                Map weixinMap = new HashMap();
                weixinMap.put("url", url);
                weixinMap.put("title", weixin_url_title.get(url));
                weixinMap.put("time", weixin_url_time.get(url));
                weixinMap.put("impact", Hwx.getChannelIndex());
                weixinMap.put("Username", weixin_url_source.get(url));
                weixinMap.put("similarity",
                        weixin_similarity.get(weixin_url_title.get(url)));
                weixinList.add(weixinMap);
            }
        }
        //预防若无H因子时   无数据
        if (0==Hweixin.size())
        {
            for (SolrWeixin solrWeixin : weixin)
            {
                Map weixinMap = new HashMap();
                weixinMap.put("url", solrWeixin.getId());
                weixinMap.put("title", solrWeixin.getTitle());
                weixinMap.put("time", sdf.format(solrWeixin.getTime()));
                weixinMap.put("impact", 0);
                weixinMap.put("Username", solrWeixin.getSource());
                weixinMap.put("similarity",
                        weixin_similarity.get(solrWeixin.getTitle()));
                weixinList.add(weixinMap);
            
            }
        }
        
        // 将无H数据添加到输出列表
        for (String url : noHUrls)
        {
            Map weixinMap = new HashMap();
            weixinMap.put("url", url);
            weixinMap.put("title", weixin_url_title.get(url));
            weixinMap.put("time", weixin_url_time.get(url));
            weixinMap.put("impact", 0);
            weixinMap.put("Username", weixin_url_source.get(url));
            weixinMap.put("similarity",
                    weixin_similarity.get(weixin_url_title.get(url)));
            weixinList.add(weixinMap);
        }

        return weixinList;
    }

    /**
     * @Decription:TODO(获取与处理微博数据)
     * @param weibo
     * @param similarity
     * @throws ExceptionTODO
     * @Exception
     * @return List
     */
    public List getWeiboInfo(List<SolrWeibo> weibo)
    {

        Map<String, String> weibo_Id_Userid = new HashMap<String, String>();
        Map<String, String> weibo_Id_Text = new HashMap<String, String>();
        Map<String, String> weibo_Id_user = new HashMap<String, String>();
        Map<String, String> weibo_Id_time = new HashMap<String, String>();
        List<String> weiboUserid = new ArrayList<String>();

        // 取出再存是为了方便根据H因子排序
        for (SolrWeibo solrWeibo : weibo)
        {
            // 取出userid
            weibo_Id_Userid.put(solrWeibo.get_id(), solrWeibo.getUser_id());

            // 取出标题
            weibo_Id_Text.put(solrWeibo.get_id(), solrWeibo.getText());

            // 取出用户名
            weibo_Id_user.put(solrWeibo.get_id(), solrWeibo.getUsername());

            // 取出时间
            weibo_Id_time.put(solrWeibo.get_id(), solrWeibo.getTime());
        }
        weiboUserid.addAll(weibo_Id_Userid.values());

        // 用域名取出TOP10的传播途径的H因子
        List<HUserInfoWB> Hweibo = hUserInfoWBService.findByIdList(weiboUserid,
                TOP);

        // H因子的id列表
        List<String> hid = new ArrayList<String>();
        for (HUserInfoWB hm : Hweibo)
        {
            hid.add(hm.getId());
        }
        // 无H因子数据的url
        List<String> noHUrls = new ArrayList<String>();
        // 封装成输出列表
        List weiboList = new ArrayList();
        for (HUserInfoWB Hw : Hweibo)
        {
            // 获取该传播源的数据id
            List<String> urlList = new ArrayList<String>();
            for (Entry<String, String> en : weibo_Id_Userid.entrySet())
            {

                // 若无h因子的记录
                if (!hid.contains(en.getValue())
                        && !noHUrls.contains(en.getKey()))
                {
                    noHUrls.add(en.getKey());
                }

                if (Hw.getId().equals(en.getValue()))
                {
                    urlList.add(en.getKey());
                }
            }
            // 将数据添加到输出列表
            for (String id : urlList)
            {
                Map weiboMap = new HashMap();
                weiboMap.put("url",
                        "http://weibo.com/" + weibo_Id_Userid.get(id) + "/"
                                + MidAurl.mid2url(id));
                weiboMap.put("title", weibo_Id_Text.get(id));
                weiboMap.put("time", weibo_Id_time.get(id));
                weiboMap.put("impact", Hw.getChannelIndex());
                weiboMap.put("username", weibo_Id_user.get(id));
                weiboList.add(weiboMap);
            }
        }
        //预防若无H因子时   无数据
        if (0==Hweibo.size())
        {
            for (SolrWeibo solrWeibo : weibo)
            {
                Map weiboMap = new HashMap();
                weiboMap.put("url", "http://weibo.com/" + weibo_Id_Userid.get(solrWeibo.get_id()) + "/"
                        + MidAurl.mid2url(solrWeibo.get_id()));
                weiboMap.put("title", solrWeibo.getText());
                weiboMap.put("time", solrWeibo.getTime());
                weiboMap.put("impact", 0);
                weiboMap.put("username", solrWeibo.getUsername());
                weiboList.add(weiboMap);
            
            }
        }
        
        
        // 将无H数据添加到输出列表
        for (String id : noHUrls)
        {
            Map weiboMap = new HashMap();
            weiboMap.put("url", "http://weibo.com/" + weibo_Id_Userid.get(id)
                    + "/" + MidAurl.mid2url(id));
            weiboMap.put("title", weibo_Id_Text.get(id));
            weiboMap.put("time", weibo_Id_time.get(id));
            weiboMap.put("impact", 0);
            weiboMap.put("username", weibo_Id_user.get(id));
            weiboList.add(weiboMap);
        }
        return weiboList;
    }

}
