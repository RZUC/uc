package com.zhiwei.uc.system.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StaticParam
{
    /**
     * @Fields provinceList : (静态类，省份列表)
     */
    public static List<String> provinceList = new ArrayList<String>();
    
    /**
     * @Fields verTypeMap : (认证类型对照表)
     */
    public static Map<Integer, String> verTypeMap = new HashMap<Integer, String>();
    
    public static Integer ptyh = -1; // 普通用户
    
    public static Integer cjdr = 200; // 初级达人
    
    public static Integer gjdr = 220;// 高级达人
    
    public static Integer mrrz = 0;// 名人认证
    
    public static Integer zfrz = 1;// 政府认证
    
    public static Integer qyrz = 2;// 企业认证
    
    public static Integer mtrz = 3;// 媒体认证
    
    public static Integer xyrz = 4;// 校园认证
    
    public static Integer wzrz = 5;// 网站认证
    
    public static Integer yyrz = 6;// 应用认证
    
    public static Integer ttrz = 7;// 团体认证
    
    public static Integer dshqyrz = 8;// 待审核企业认证
    
    public static Integer wbnl = 10;// 微博女郎
    
    /** 账号对应中文名,Intel账号对应的微博名称 */
    public static Map<String, String> userMap = new HashMap<String, String>();
    
    public static String intel = "2295615873";// 微博女郎
    
    public static String langchao = "2281214427";// 浪潮服务器官方微博
    
    public static String VMware = "1747413277";// VMware中国
    
    public static String huawei = "1930559805";// 华为云计算官方微博
    
    public static String SAP = "1759418035";// SAP中国
    
    public static String csdn = "1741045432";// CSDN云计算
    
    /** 账号对应中文名,Intel账号对应的微博名称 */
    public final static Map<String, Integer> emotionType = new HashMap<String, Integer>();
    
    public final static Integer ZHENGMIAN = 1;// 正面
    
    public final static Integer FUMIAN = 2;// 负面
    
    public final static Integer ZHONGXIN = 3;// 中信
    
    public final static Integer JINGPING = 4;// 竞品
    
    /**
     * @Fields dataType : 事件趋势的所属的类型，微博，网媒，微信
     */
    public final static Map<String, String> eventDataType = new HashMap<String, String>();
    
    public final static String WEIBO = "weibo";// 微博
    
    public final static String MEDIA = "media";// 网媒
    
    public final static String WEIXIN = "weixin";// 微信
    
    static
    {
        /** 添加省份列表 */
        provinceList.add("北京");
        provinceList.add("上海");
        provinceList.add("天津");
        provinceList.add("重庆");
        provinceList.add("内蒙");
        provinceList.add("宁夏");
        provinceList.add("新疆");
        provinceList.add("西藏");
        provinceList.add("广西");
        provinceList.add("香港");
        provinceList.add("澳门");
        provinceList.add("黑龙江");
        provinceList.add("辽宁");
        provinceList.add("吉林");
        provinceList.add("河北");
        provinceList.add("河南");
        provinceList.add("湖北");
        provinceList.add("湖南");
        provinceList.add("山东");
        provinceList.add("山西");
        provinceList.add("陕西");
        provinceList.add("安徽");
        provinceList.add("浙江");
        provinceList.add("江苏");
        provinceList.add("福建");
        provinceList.add("广东");
        provinceList.add("海南");
        provinceList.add("四川");
        provinceList.add("云南");
        provinceList.add("贵州");
        provinceList.add("青海");
        provinceList.add("甘肃");
        provinceList.add("江西");
        provinceList.add("台湾");
        provinceList.add("其他");
        
        /** 添加认证类型 */
        verTypeMap.put(ptyh, "普通用户");
        verTypeMap.put(cjdr, "初级达人");
        verTypeMap.put(gjdr, "高级达人");
        verTypeMap.put(gjdr, "名人认证");
        verTypeMap.put(zfrz, "政府认证");
        verTypeMap.put(qyrz, "企业认证");
        verTypeMap.put(mtrz, "媒体认证");
        verTypeMap.put(xyrz, "校园认证");
        verTypeMap.put(wzrz, "网站认证");
        verTypeMap.put(yyrz, "应用认证");
        verTypeMap.put(ttrz, "团体认证");
        verTypeMap.put(dshqyrz, "待审核企业认证");
        verTypeMap.put(wbnl, "微博女郎");
        
        /** 用户对比账号 */
        userMap.put(intel, "英特尔");
        userMap.put(langchao, "浪潮服务器官方微博");
        userMap.put(VMware, "VMware中国");
        userMap.put(SAP, "SAP中国");
        userMap.put(csdn, "CSDN云计算");
        userMap.put(huawei, "华为云计算官方微博");
        
        /** 用户对比账号 */
        
        emotionType.put("正面", ZHENGMIAN);
        emotionType.put("负面", FUMIAN);
        emotionType.put("中性", ZHONGXIN);
        
        eventDataType.put("weibo", WEIBO);
        eventDataType.put("media", MEDIA);
        eventDataType.put("weixin", WEIXIN);
        
    }
    
    @Test
    public void test()
    {
        for (Integer id : verTypeMap.keySet())
        {
            System.out.println(id + verTypeMap.get(id));
        }
    }
}
