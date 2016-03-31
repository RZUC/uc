package com.zhiwei.uc.system.util;

import java.text.SimpleDateFormat;
import java.util.List;

/** 
 * @Package com.zhiwei.event.tool
 * @ClassName: SolrTool
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 志伟
 * @date 2015-5-23 下午4:17:42
 */
public class SolrTool {
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @deprecated:处理关键词
	 * @param List<String> words
	 * 			关键词
	 * @reutrn String
	 * **/
	public static String processWordWB(List<String> words)
	{
		String param = "";
		for(String word : words)
		{
			if(word.equals("(")||word.equals(")"))
			{
				param = param + word;
			}else if(word.equals("+"))
			{
				param = param + " AND ";
			}else if(word.equals("|"))
			{
				param = param + " OR ";
			}else
			{
				param = param + "content:\"" + word + "\"";
			}
		}
		return param;
	}
	
	
	
	
	/**
	 * @deprecated:处理关键词
	 * @param List<String> words
	 * 			关键词
	 * @reutrn String
	 * **/
	public static String processWordWX87(List<String> words)
	{
		String param = "";
		String contentParam = "";
		String titleParam = "";
		for(String word : words)
		{
			if(word.equals("(")||word.equals(")"))
			{
				contentParam = contentParam + word;
			}else if(word.equals("+"))
			{
				contentParam = contentParam + " AND ";
			}else if(word.equals("|"))
			{
				contentParam = contentParam + " OR ";
			}else
			{
				contentParam = contentParam + "content:\"" + word + "\"";
			}
		}
		for(String word : words)
		{
			if(word.equals("(")||word.equals(")"))
			{
				titleParam = titleParam + word;
			}else if(word.equals("+"))
			{
				titleParam = titleParam + " AND ";
			}else if(word.equals("|"))
			{
				titleParam = titleParam + " OR ";
			}else
			{
				titleParam = titleParam + "title:\"" + word + "\"";
			}
		}
		param = "("+contentParam +") OR (" + titleParam +")";
		return param;
	}
	
	
	
	
	
	/**
	 * @deprecated:处理关键词
	 * @param List<String> words
	 * 			关键词
	 * @reutrn String
	 * **/
	public static String processWordWX(List<String> words)
	{
		String param = "";
		for(String word : words)
		{
			if(word.equals("(")||word.equals(")"))
			{
				param = param + word;
			}else if(word.equals("+"))
			{
				param = param + " AND ";
			}else if(word.equals("|"))
			{
				param = param + " OR ";
			}else
			{
				param = param + "messages:\"" + word + "\"";
			}
		}
		return param;
	}
	
	
	
	/**
	 * @deprecated:处理时间
	 * @param String start
	 * 				开始时间
	 * @param String end
	 * 				结束时间
	 * @reutrn String
	 * **/
	public static String processTimeStr(String start,String end)
	{
		 String	time = "time:[ \"" + start + " \"TO \"" + end + "\" ]";
		 return time;
	}
	
	/**
	 * @deprecated:处理时间
	 * @param String start
	 * 				开始时间
	 * @param String end
	 * 				结束时间
	 * @reutrn String
	 * **/
	public static String processTimeDate(String start,String end)
	{
		String time = "";
		try {
			//判断传进来的时间是否为1小时更新时间
			// 处理时间条件(时间推至格林威治时间)
			start = sf.format(sf.parse(start).getTime() - 8
					* 60 * 60 * 1000);
			end = sf.format(sf.parse(end).getTime() - 8 * 60 * 60 * 1000);
			// 写入时间条件
			start = (sf.format((sf.parse(start).getTime())));
			start = start.substring(0, 10) + "T" + start.substring(11, 16)
					+ ":00.999Z";
			end = (sf.format((sf.parse(end).getTime())));
			end = end.substring(0, 10) + "T" + end.substring(11, 16)
					+ ":00.999Z";
			time = "time:[ " + start + " TO " + end + " ]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
}
