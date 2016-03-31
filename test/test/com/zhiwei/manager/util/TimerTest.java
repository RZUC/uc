/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月8日
    * @version 1.00 
*/
package test.com.zhiwei.manager.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.zhiwei.wjgg.util.TimeUtil;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: TimerTest
 * @author 落花流水
 * @date 2016年3月8日 上午9:00:04
 */
public class TimerTest
{
    public static void main(String args[])
    {
        // Timer timer = new Timer("定时器");
        // Date endTime = TimeUtil.parseTime("2016-03-08 10:17:00");
        // Reminder r = new Reminder(timer,endTime);
        // timer.schedule(r, 0,1000);
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(TimeUtil.formatTime(date));
        System.out.println(new Date(date.getTime()));
        System.out.println(TimeUtil.parseTime("2016-03-16 20:20:33").getTime());
    }
    
}

class Reminder extends TimerTask
{
    private Timer myBoss;
    
    private Date endTime;
    
    public Reminder(Timer timer, Date endTime)
    {
        this.myBoss = timer;
        this.endTime = endTime;
    }
    
    @Override
    public void run()
    {
        
        if (TimeUtil.getCurrentTime().getTime() - endTime.getTime() > 0)
        {
            myBoss.cancel();
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + "\t我是打击手\t" + TimeUtil.getCurrentTimeStr());
        }
    }
}