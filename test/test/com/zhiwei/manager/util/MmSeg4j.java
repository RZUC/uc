/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月28日
    * @version 1.00 
*/
package test.com.zhiwei.manager.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhiwei.wjgg.analy.fenci.Fenci;
import com.zhiwei.wjgg.conf.MyDic;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: MmSeg4j
 * @author 落花流水
 * @date 2016年3月28日 上午10:18:13
 */
public class MmSeg4j
{
    public static void main(String[] args)
        throws IOException
    {
        List<String> dataList2 = new ArrayList<String>();
        dataList2.add("鹿晗");
        dataList2.add("盗墓笔记");
        dataList2.add("王八蛋");
        dataList2.add("angelBaby");
        dataList2.add("angelBaby");
        dataList2.add("angelBaby");
        
        MyDic.addWords(dataList2);
        Fenci fenci = new Fenci();
        List<String> dataList = new ArrayList<String>();
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("李宇春 李宇春吧 李宇春中性");
        dataList.add("鹿晗 鹿晗 鹿晗 盗墓笔记 盗墓笔记");
        dataList.add("鹿晗 鹿晗 鹿晗 盗墓笔记 盗墓笔记");
        dataList.add("鹿晗 王八蛋");
        dataList.add("鹿晗 王八蛋");
        dataList.add("鹿晗 王八蛋");
        dataList.add("鹿晗 王八蛋");
        dataList.add("鹿晗 王八蛋");
        dataList.add("鹿晗 王八蛋");
        
        Map<String, Integer> my = fenci.getFenCiWithStorp(dataList);
        for (String key : my.keySet())
        {
            System.out.println("Key:" + key + "----" + my.get(key));
        }
        
    }
}
