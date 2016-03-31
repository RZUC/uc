package test.com.zhiwei.wjgg.reptile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import com.zhiwei.wjgg.analy.service.reptile.WeiXinReptile;
import com.zhiwei.wjgg.dao.BaiDuDao;

import test.object.ObjectTest;

public class reptileTest extends ObjectTest
{
    @Resource
    BaiDuDao bdDao;
    
    @org.junit.Test
    public void Test()
    {
        
        long start = System.currentTimeMillis();
        List<String> wordList = new ArrayList<String>();
        wordList.add("六小龄童");
        CountDownLatch countDown =  new CountDownLatch(0);
        WeiXinReptile w = new WeiXinReptile(wordList,bdDao,countDown);
        Thread weixinThread = new Thread(w, "微信");
        weixinThread.start();
        
        try
        {
            weixinThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("主线程分析完成：分析完成-----------------------------");
        
        long end = System.currentTimeMillis();
        
        System.out.println("运行时间：" + (end - start) / 1000 + "\t毫秒");
        
    }
}
