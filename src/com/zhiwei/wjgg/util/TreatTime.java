package com.zhiwei.wjgg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @Package com.zhiwei.event.tool
 * @ClassName: TreatTime
 * @Description: TODO(时间处理类)
 * @author 志伟
 * @date 2015-5-23 上午9:14:55
 */
public class TreatTime {
	
	private static Logger logger =  LoggerFactory.getLogger(TreatTime.class); //日志文件
	
	/**
	 * @deprecated:
	 * 			根据年和月处理日期
	 * @param int year
	 * 			要处理的年
	 * @param int month
	 * 			要处理的月
	 * @return List<Date>
	 * 
	 * **/
	public static List<Date> treatTimeByMonth(int year, int month)
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //精确到秒的时间处理方法
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //精确到天的时间处理方法
		List<Date> list = new ArrayList<Date>();
		Date startDate = null;
		Date endDate = null;
		
		String startTime = null;
		String endTime = null;
		
		if(month !=0 )   //判断是否含有月份（按月份查询事件的时间）
		{
			//判断每月最后一天为31号	
			if(month == 1 || month == 3 || month == 5 || month == 7 || 
					month == 8 || month == 10 || month == 12 )
			{
				startTime = year+"-"+month+"-01";
				endTime = year+"-"+month+"-31";
			}
			//判断每月最后一天为30号	
			else if(month == 4 || month == 6 || month == 9 || month == 11 )
			{
				startTime = year+"-"+month+"-01";
				endTime = year+"-"+month+"-30";
			}else 
			{
				//判断2月是否为闰月
				if(year%4 == 0) //此年二月为闰月
				{
					startTime = year+"-"+month+"-01";
					endTime = year+"-"+month+"-29";
				}else  //此年二月非闰月
				{
					startTime = year+"-"+month+"-01";
					endTime = year+"-"+month+"-28";
				}
			}
		}else    //查询全年事件的时间
		{
			startTime = year+"-01-01";
			endTime = year+"-12-31";
		}
		
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
			list.add(startDate);
			list.add(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("处理时间格式时出现错误", e.getMessage());
			return null;
		}
		return list;
	}
	
	
	/**
	 * @Deprecated 
	 * 			计算事件开始时间到当前时间的差值
	 * @param Date startTime 
	 * 				开始时间
	 * @param Date endTime
	 * 				结束时间
	 * @return List
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List calTimeDiff(Date startDate,Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List list=new ArrayList();
		Date nowDate=new Date();//当前系统时间
		if (nowDate.getTime() <= endDate.getTime()) {
			endDate = nowDate;
		}
		if(nowDate.getTime()<=startDate.getTime())
		{
			startDate = nowDate;
		}
		
		
		long daycount = TimeUtil.getSubday(TimeUtil.getDatePart(endDate), TimeUtil.getDatePart(startDate));
		System.out.println("事件开始与结束时间相差天数"+daycount);
		
		for (int i = 0; i <= daycount; i++) 
		{
			String time = sdf.format(new Date(endDate.getTime()- (i * (24 * 60 * 60 * 1000L))));
			String sTime = time+" 00:00:01";
			String eTime = time +" 23:59:59";
			list.add(sTime+","+eTime);
		}
		return list;
	}
	
	/**
	 * @deprecated：
	 * 			根据天计算小时
	 * @param String startTime 
	 * 				开始时间
	 * @param String endTime
	 * 				结束时间
	 * @return List<String> tmLine
	 * **/
	@SuppressWarnings("deprecation")
	public static List<String> calTimeToHour(Date startDate,Date endDate)
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");  //精确到秒的时间处理方法
		List<String> tmLine = new ArrayList<String>();
		endDate = TimeUtil.getDatePartToHour(endDate);
		
		long hourCount = TimeUtil.getSubhour(endDate, TimeUtil.getDatePartToHour(startDate));
		for(int i=0; i<=hourCount; i++)
		{
			Date time = new Date(endDate.getTime()- (i *  60 * 60 * 1000L));
			tmLine.add(sdf.format(time));
		}
		return tmLine;
	}
	
	
	/**
	 * @deprecated：
	 * 				将String的时间转换成Date类型
	 * @param String startTime 
	 * 				开始时间
	 * @param String endTime
	 * 				结束时间
	 * @return String time
	 * **/
	public static String changeDateFromString(String startTime,String endTime)
	{
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //精确到秒的时间处理方法
		String time = "";
		try {
			Date startDate = sf.parse(startTime);
			Date endDate = sf.parse(endTime);
			time = startDate.getTime()/1000+","+endDate.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("将时间转换成百度新闻采集需要的时间类型时出错",e.getMessage());
			return time;
		}
		return time;
	}
	
	
	/**
	 * @deprecated:
	 * 				解析的时间处理方法
	 * @param String date
	 * 				要处理的时间
	 * @return Date
	 * 
	 * */
	public static Date conversionTime(String time)
	{
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  //精确到秒的时间处理方法
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //精确到天的时间处理方法
		int t = 0;
		if(time.contains("小时"))
		{
			t = Integer.valueOf(time.split("小时")[0])*60*60*1000;
		}if(time.contains("分"))
		{
			t = Integer.valueOf(time.split("分")[0])*60*1000;
		}if(time.contains("秒"))
		{
			t = Integer.valueOf(time.split("秒")[0])*1000;
		}
		
		Date date = null;
		if(time.contains("年"))
		{
			time = time.replace("年", "-").replace("月", "-").replace("日", "");
			try {
				date = sf.parse(time);
			} catch (Exception e) {
				date = new Date();
			}
		}else
		{
			date =new Date( new Date().getTime()-t);
		}
		return date;
	}
	
	
	
	/**
	 * @deprecated:将事件的时间脉络补全
	 * @param HashMap<String,Integer> timeLine
	 * 						时间脉络数据
	 * @param String startTime
	 * 					开始时间
	 * @param String endTime
	 * 					结束时间
	 * @return HashMap
	 * ***/
	public static HashMap<String,Integer> getTimeLine(HashMap<String,Integer> timeLine,Date startTime,Date endTime)
	{
		
		List<String> hourList = calTimeToHour(startTime,endTime); //计算总的小时
		for(String time : hourList)
		{
			if(!timeLine.containsKey(time))
			{
				timeLine.put(time, 0);
			}
		}
		return timeLine;
	}
	
	
	/**
	 * @deprecated:将事件的时间脉络补全
	 * @param HashMap<String,Integer> timeLine
	 * 						时间脉络数据
	 * @param String startTime
	 * 					开始时间
	 * @param String endTime
	 * 					结束时间
	 * @return HashMap
	 * ***/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getTimeList(HashMap<String,Integer> timeLine)
	{
		List list = new ArrayList();
		List contextTime=new ArrayList();//x轴时间
		List contextValue=new ArrayList();//y轴值
//		HashMap<String,Integer> tmLine = getTimeLine(timeLine,startTime,endTime);
		List<Map.Entry<String, Integer>> tmMap = HashMapSort.ComparatorMapASC(timeLine);
		
		for(Map.Entry<String, Integer> map : tmMap)
		{
			contextTime.add(map.getKey());
			contextValue.add(map.getValue());
		}
		list.add(contextTime);
		list.add(contextValue);
		return list;
	}
	
	
}
