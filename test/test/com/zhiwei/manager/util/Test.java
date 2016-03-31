/** 
　　* Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. 
　　* FileName: Test.java 
　　* 类的详细说明 
　　* 
　　* @author 东临碣石
　　* @Date 2016年1月15日
　　* @version 1.00 
　　*/
package test.com.zhiwei.manager.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: Test
 * @author 落花流水
 * @date 2016年1月15日 上午9:50:43
 */
public class Test
{
    public static void main(String[] args)
        throws InterruptedException
    {
        final Test t =new Test();
        Runnable runnable1 = new Runnable()
        {
            @Override
            public void run()
            {
                t .method1();
                t .method2();
                System.out.println(Thread.currentThread().getName() + "\t运行完毕*****************");
            }
        };
        
        Thread thread1 = new Thread(runnable1, "大線程");
        
        try
        {
            thread1.start();
            thread1.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("=================主线程运行完毕=================");
    }
    
    
    public void method1()
    {
        Runnable runnable1 = new Runnable()
        {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                for (int i = 0; i < 10; i++)
                {
                    System.out.println(Thread.currentThread().getName() + "\tFOR----" + i);
                    Thread.yield();// 让出cpu
                }
                System.out.println(Thread.currentThread().getName() + "\t运行完毕*****************");
            }
            
        };
        
        Thread thread1 = new Thread(runnable1, "FoR循环线程");
        thread1.start();
        try
        {
            thread1.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void method2()
    {
        Runnable runnable2 = new Runnable()
        {
            @Override
            public void run()
            {
                int i = 0;
                while (true)
                {
                    System.out.println(Thread.currentThread().getName() + "\tWHILE----" + i);
                    Thread.yield();// 让出cpu
                    i++;
                    if (i > 10)
                    {
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t运行完毕*****************");
                
            }
        };
        Thread thread2 = new Thread(runnable2, "whil循环线程");
        thread2.start();
        try
        {
            thread2.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };
    
    @org.junit.Test
    public void test(){
        List<String> list = new ArrayList<String>();
        list.add("测试");
        list.add("测试2");
        list.add("测试3");
        list.remove("测试");
        System.out.println(list);
    }
}
