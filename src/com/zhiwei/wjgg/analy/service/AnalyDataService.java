/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月2日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.service;

import java.util.List;
import java.util.Map;

import com.zhiwei.wjgg.model.Event;

/**
 * @Description: 分析接口
 * @ClassName: AnalyDataService
 * @author 落花流水
 * @date 2016年3月2日 下午4:45:58
 */
public interface AnalyDataService
{
    /**
     * @param event 
     * @Title: analyData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型<br>
     *         <b>map中的字段</b>
     *         <ul>
     *         <li>timeLine---时间线</li>
     *         <li>spredSource----来源</li>
     *         <li>hotword----时间热词</li>
     *         <li>similarTitle---相似标题</li>
     *         <li>dataCount---数据量</li>
     *         </ul>
     */
    public Map<String, Object> analyData(List list, Event event);
}
