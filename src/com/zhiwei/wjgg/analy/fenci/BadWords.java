package com.zhiwei.wjgg.analy.fenci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @deprecated: 获取负面关键词库
  * @author zhiweizhang
 * @version 2.0
 * @date 2015-07-22 
 * 
 * **/
public class BadWords {
	
	public BadWords() 
	{
		if(null==badWords||badWords.isEmpty())
		{
			badWords = getBadWords();
		}
	}
	
	/***
	 * @deprecated：获取停用词列表
	 * 
	 * @return  List<String>
	 * **/
	public static List<String> getBadWords()
	{
		List<String> badList = new ArrayList<String>();
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("bad.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				badList.add(line);
			}
			reader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return badList;
	}
	
	static List<String> badWords = new ArrayList<String>();
}
