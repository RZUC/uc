/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月15日
    * @version 1.00 
*/
package com.uc.system.timmer;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: ContextListener
 * @author 落花流水
 * @date 2016年3月15日 下午5:14:47
 */
public class ContextListener implements ServletContextListener
{
    
    private static Timer timer = null;
    
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        timer = new java.util.Timer(true);
        
        int minute = 5;// 分钟
        
        int second = 60;// 秒
        
        timer.schedule(new MyTask(), 0, minute * second * 1000);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        timer.cancel();
        System.out.println("定时器销毁");
        event.getServletContext().log("定时器销毁");
    }
    
}
