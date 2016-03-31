package com.zhiwei.wjgg.analy.fenci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** 
 * @Package com.zhiwei.event.fenci
 * @ClassName: StopWords
 * @Description: TODO(获取停用词列表)
 * @author 志伟
 * @date 2015-5-28 上午10:01:25
 */
public class StopWords {
	
	public StopWords()
	{
		if(null==stopWords||stopWords.isEmpty())
		{
			stopWords = getStopWords();
		}
	}
	
	
	/***
	 * @deprecated：获取停用词列表
	 * 
	 * @return  List<String>
	 * **/
	public static List<String> getStopWords()
	{
		List<String> stopList = new ArrayList<String>();
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("StopWords.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				stopList.add(line);
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
		return stopList;
	}
	
	static List<String> stopWords = new ArrayList<String>();
	
}
