/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月11日
    * @version 1.00 
*/
package com.zhiwei.wjgg.analy.eventInfulence;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO(计算事件影响力)
 * @ClassName: EventInfulence
 * @author 落花流水
 * @date 2016年3月11日 下午2:12:00
 */
@Component
public class EventInfulence
{
    private static double EVENTRATIO;// 事件相关性比例
    
    private static double PLATFORMRATIO;// 媒介渠道比例
    
    private static double DATACOUNTRATIO;// 传播数量比例
    
    private static final int DECIMLPLACES = 2;// 最后结果保留几位小数
    
    private static final int DATACOUNTUPLIMIT = 10000;// 数据总数的打分上限
    
    private static final int PLATFORMRATUPLIMIT = 200000;// 平台媒介渠道的总数的打分上限
    
    public static Double calculateEventinfulence(int correlationNum, Double weixinInfulence, Double weiboInfulence,
        Double mediaInfulence, Integer totalDataCount)
    {
        
        return integrated(correlationNum, weixinInfulence + weiboInfulence + mediaInfulence, totalDataCount);
    }
    
    /**
     * @Title: Nomel
     * @Description: 媒介指数标准化
     * @param @param weixinInfulence
     * @param @return 设定文件
     * @return double 返回类型
     */
    public static double nomelInfulence(Double infulence)
    {
        double nomelDataCount = 0.0;
        
        if (infulence >= PLATFORMRATUPLIMIT)
        {
            nomelDataCount = 100.0;
        }
        else
        {
            nomelDataCount = Math.sqrt(infulence / 20);
        }
        return nomelDataCount;
    }
    
    /**
     * @Title: nomelDataCount
     * @Description: 传播数量标准化
     * @param @param count
     * @param @return 设定文件
     * @return double 返回类型
     */
    public static double nomelDataCount(double count)
    {
        // TODO:1.超过1W就直接100分，否则直接开根号
        double nomelDataCount = 0.0;
        
        if (count >= DATACOUNTUPLIMIT)
        {
            nomelDataCount = 100.0;
        }
        else
        {
            nomelDataCount = Math.sqrt(count);
        }
        return nomelDataCount;
    }
    
    /**
     * @Title: integrated
     * @Description: 平台综合计算
     * @param @param correlationNum
     * @param @param Infulence
     * @param @param totalDataCount
     * @param @return 设定文件
     * @return double 返回类型
     */
    public static double integrated(double correlationNum, double infulence, double totalDataCount)
    {
        Double eventInfulence = correlationNum * EVENTRATIO + nomelInfulence(infulence) * PLATFORMRATIO
            + nomelDataCount(totalDataCount) * DATACOUNTRATIO;
            
        BigDecimal b = new BigDecimal(eventInfulence);
        
        return b.setScale(DECIMLPLACES, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    @Value("${eventratio}")
    public void setEVENTRATIO(double eventratio)
    {
        EventInfulence.EVENTRATIO = eventratio;
    }
    
    @Value("${platformration}")
    public void setPLATFORMRATIO(double platformration)
    {
        EventInfulence.PLATFORMRATIO = platformration;
    }
    
    @Value("${datacountratio}")
    public void setDATACOUNTRATIO(double datacountratio)
    {
        EventInfulence.DATACOUNTRATIO = datacountratio;
    }
}
