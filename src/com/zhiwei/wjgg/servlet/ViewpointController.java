package com.zhiwei.wjgg.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiwei.wjgg.model.EventKeySource;
import com.zhiwei.wjgg.model.EventTrend;
import com.zhiwei.wjgg.model.HUserInfoBD;
import com.zhiwei.wjgg.model.HUserInfoWB;
import com.zhiwei.wjgg.model.HUserInfoWX;
import com.zhiwei.wjgg.model.Similarity;
import com.zhiwei.wjgg.model.SolrMedia;
import com.zhiwei.wjgg.model.SolrWeibo;
import com.zhiwei.wjgg.model.SolrWeixin;
import com.zhiwei.wjgg.model.ViewPoint;
import com.zhiwei.wjgg.service.EventService;
import com.zhiwei.wjgg.service.EventTrendService;
import com.zhiwei.wjgg.service.HUserInfoBDService;
import com.zhiwei.wjgg.service.HUserInfoWBService;
import com.zhiwei.wjgg.service.HUserInfoWXService;
import com.zhiwei.wjgg.service.SimilarityService;
import com.zhiwei.wjgg.service.SolrMediaDataService;
import com.zhiwei.wjgg.service.SolrWeiboDataService;
import com.zhiwei.wjgg.service.SolrWeixinDataService;
import com.zhiwei.wjgg.service.ViewPointService;
import com.zhiwei.wjgg.util.MapUtil;
import com.zhiwei.wjgg.util.MidAurl;
import com.zhiwei.wjgg.util.UrlUtil;

/**
 * 
 * @ClassName: ViewpointController
 * @Description: TODO(观点分布)
 * @author chenweitao
 * @date 2016年3月9日 上午10:13:47
 */
@Controller
@RequestMapping(value = "/point")
public class ViewpointController extends GeneralController
{
    private static final String LEADER = "意见领袖";

    private static final String NETIZEN = "网民";

    private static final String MEDIA_TYPE = "media";

    private static final String WEIBO_TYPE = "weibo";

    private static final String WEIXIN_TYPE = "weixin";

    private static final int Top = 20;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Resource
    EventService eventService;

    @Resource
    ViewPointService viewPointService;

    @Resource
    EventTrendService eventTrendService;

    @Resource
    SolrMediaDataService solrMediaDataService;

    @Resource
    SolrWeiboDataService solrWeiboDataService;

    @Resource
    SolrWeixinDataService solrWeixinDataService;

    @Resource
    SimilarityService similarityService;
    
    @Resource
    HUserInfoBDService hUserInfoBDService;

    @Resource
    HUserInfoWBService hUserInfoWBService;

    @Resource
    HUserInfoWXService hUserInfoWXService;

    /**
     * 
     * @Title: showViewPointAndKeySource 
     * @Description: TODO(观点分布页面初始化加载值) 
     * @param @param eventId
     * @param @param arg1
     * @param @throws Exception 设定文件 
     * @return void 返回类型
     */
    @RequestMapping(value = "/show")
    public void showViewPointAndKeySource(
            @RequestParam(value = "eventId", required = false, defaultValue = "") String eventId,
            HttpServletResponse arg1) throws Exception
    {
        List list = new ArrayList();
        // 意见领袖观点
        list.add(viewPointService.findOneByEventIdAndType(eventId, LEADER));

        // 网民观点
        list.add(viewPointService.findOneByEventIdAndType(eventId, NETIZEN));

        // 关键传播源
        Map<String, Map<String,Map<String,?>>> keySource = new HashMap<String, Map<String,Map<String,?>>>();

        EventTrend weixinT = eventTrendService.findByEventIdInTime(eventId,
                WEIXIN_TYPE);
        if (null != weixinT)
        {
            //获取关键传播源并截取前TOP数
            Map<String, List> infoMap = MapUtil.treatOrderMapTop(weixinT.getSource(), Top);
            
            //封装输出
            Map<String,Map<String,?>> info_num = new HashMap<String, Map<String,?>>();
            for (Entry<String, List> en : infoMap.entrySet())
            {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("list", en.getValue());
                if (en.getValue().size()>0)
                {
                    HUserInfoWX h = hUserInfoWXService.findByName(en.getKey());
                    //因为需要相乘计算，当没有记录的h因子时，设置为1
                    if (null!=h)
                    {
                        map.put("H", h.getChannelIndex());
                    }else {
                        map.put("H", 1.0);
                    }
                    info_num.put(en.getKey(), map);
                }
                
            }
            
            keySource.put(WEIXIN_TYPE,
                    info_num);
        }

        EventTrend weiboT = eventTrendService.findByEventIdInTime(eventId,
                WEIBO_TYPE);
        if (null != weiboT)
        {
          //获取关键传播源并截取前TOP数
            Map<String, List> infoMap = MapUtil.treatOrderMapTop(weiboT.getSource(), Top);
            
          //封装输出
            Map<String,Map<String,?>> info_num = new HashMap<String, Map<String,?>>();
            for (Entry<String, List> en : infoMap.entrySet())
            {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("list", en.getValue());
                HUserInfoWB h = hUserInfoWBService.findByName(en.getKey());
                //因为需要相乘计算，当没有记录的h因子时，设置为1
                if (null!=h)
                {
                    map.put("H", h.getChannelIndex());
                }else {
                    map.put("H", 1.0);
                }
                info_num.put(en.getKey(), map);
            }
            
            keySource.put(WEIBO_TYPE,
                    info_num);
        }

        EventTrend mediaT = eventTrendService.findByEventIdInTime(eventId,
                MEDIA_TYPE);
        if (null != mediaT)
        {
            //获取关键传播源并截取前TOP数
            Map<String, List> infoMap = MapUtil.treatOrderMapTop(mediaT.getSource(), Top);
            //封装输出
            Map<String,Map<String,?>> info_num = new HashMap<String, Map<String,?>>();
            
            for (Entry<String, List> en : infoMap.entrySet())
            {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("list", en.getValue());
                HUserInfoBD h = hUserInfoBDService.findByName(en.getKey());
                //因为需要相乘计算，当没有记录的h因子时，设置为1
                if (null!=h)
                {
                    map.put("H", h.getChannelIndex());
                }else {
                    map.put("H", 1.0);
                }
                info_num.put(en.getKey(), map);
            }
            
            keySource.put(MEDIA_TYPE,
                    info_num);
        }
        list.add(keySource);

        getJsonStrByList(list, arg1);
    }

    /**
     * 
     * @Title: showViewPoint 
     * @Description: TODO(关键传播源的数据) 
     * @param @param eventKeySource
     * @param @param arg1
     * @param @throws Exception 设定文件 
     * @return void 返回类型
     */
    @RequestMapping(value = "/showInfo")
    public void showViewPoint(@RequestBody EventKeySource eventKeySource,
            HttpServletResponse arg1) throws Exception
    {
        
        String type = eventKeySource.getType();
        String eventId = eventKeySource.getEventId();
        List<String> ids = eventKeySource.getIds();
        List<Map<String, ?>> result = new ArrayList();
        // 获取到该事件的相似传播
        Similarity similarity = similarityService.findOneById(eventId);
        
        if (MEDIA_TYPE.equals(eventKeySource.getType()))
        {
            List<SolrMedia> list = solrMediaDataService
                    .findObByIdList(eventKeySource.getIds());
            for (SolrMedia media : list)
            {
                Map mediaInfo = new HashMap();
                String time = sdf.format(media.getTime());
                mediaInfo.put("time", time);
                String url = media.getId();
                mediaInfo.put("url", url);
                String title = media.getTitle();
                mediaInfo.put("title", title);
                Integer num = similarity.getMedia().get(title);
                mediaInfo.put("num", num);
                result.add(mediaInfo);
            }
        }
        if (WEIBO_TYPE.equals(type))
        {
            List<SolrWeibo> list = solrWeiboDataService.findObByIdList(ids);
            for (SolrWeibo weibo : list)
            {
                Map weiboInfo = new HashMap();
                String url = "http://weibo.com/" + weibo.getUser_id() + "/"
                        + MidAurl.mid2url(weibo.get_id());
                weiboInfo.put("url", url);
                String time = weibo.getTime();
                weiboInfo.put("time", time);
                String title = weibo.getText();
                weiboInfo.put("title", title);
                result.add(weiboInfo);
            }

        }
        if (WEIXIN_TYPE.equals(type))
        {
            List<SolrWeixin> list = solrWeixinDataService.findObByIdList(ids);
            for (SolrWeixin weixin : list)
            {
                Map weixinInfo = new HashMap();
                String time = sdf.format(weixin.getTime());
                weixinInfo.put("time", time);
                String url = weixin.getId();
                weixinInfo.put("url", url);
                String title = weixin.getTitle();
                weixinInfo.put("title", title);
                Integer num = similarity.getWeixin().get(title);
                weixinInfo.put("num", num);
                result.add(weixinInfo);
            }
        }
        List list = MapUtil.treatOrderByListTime(result);
        getJsonStrByList(list, arg1);

    }

    /**
     * @Title: updataViewPoint
     * @Description: TODO(编辑观点)
     * @param @param ob
     * @param @param arg1
     * @param @throws Exception 设定文件
     * @return void 返回类型
     */
    @RequestMapping(value = "/updataPoint")
    public void updataViewPoint(@RequestBody ViewPoint ob,
            HttpServletResponse arg1) throws Exception
    {
        boolean flag = false;
        ob.setId(ob.getEventId() + ob.getType());
        
        Double sum = 0.00;
        for (Double num : ob.getPointMap().values())
        {
            sum += num;
        }
        
        //内容不能为空，且总数不能大于100%
        if (!ob.getPointMap().keySet().contains("")&&sum<=1)
        {
            ViewPoint old_ob = viewPointService.findOneById(ob.getId());
            // 判断源数据有无观点,若为空则添加,若不为空则修改
            if (null != old_ob)
            {
                flag = viewPointService.updata(ob);
            }
            else
            {
                flag = viewPointService.add(ob);
            }
        }
        
        getBooleanJSon(flag, REQUESTStrOK, arg1);
    }

}
