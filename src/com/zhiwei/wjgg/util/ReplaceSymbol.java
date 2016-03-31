package com.zhiwei.wjgg.util;

/** 
 * @Package com.zhiwei.wc.util
 * @ClassName: ReplaceSymbol
 * @Description: TODO(替换连接中的&符)
 * @author 志伟
 * @date 2015-5-13 下午6:49:49

 */
public class ReplaceSymbol {
	
	/**
	 * @deprecated:处理百度新闻链接中的字符转码
	 * 
	 * **/
	public static String ReplaceSymbol(String link)
	{
		String result = link.replaceAll("amp;", "").replaceAll("%", "");
		return result;
	}
}
