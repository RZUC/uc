package com.zhiwei.wjgg.analy.fenci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @deprecated：获取正面关键词词库
 * @author zhiweizhang
 * @version 2.0
 * @date 2015-07-22 
 * **/
public class GoodWords {

	public GoodWords() 
	{
		if(null==goodWords||goodWords.isEmpty())
		{
			goodWords = getGoodWords();
		}
	}
	
	
	/***
	 * @deprecated：正面关键词
	 * 
	 * @return  List<String>
	 * **/
	public static List<String> getGoodWords()
	{
		List<String> goodList = new ArrayList<String>();
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("good.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				goodList.add(line);
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
		return goodList;
	}
	
	static List<String> goodWords = new ArrayList<String>();
	
	
	
}
