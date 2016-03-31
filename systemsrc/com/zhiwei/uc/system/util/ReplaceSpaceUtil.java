package com.zhiwei.uc.system.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

/** 
 * @Package com.zhiwei.wc.util

 * @ClassName: ReplaceSpaceUtil

 * @Description: TODO(去除全角空格)

 * @author 志伟

 * @date 2015-2-11 下午8:04:52

 */
public class ReplaceSpaceUtil {
	
	@SuppressWarnings("deprecation")
	/**
	 * 
	 * @Title: replaceSpace
	
	 * @Description: TODO(去除疑难的空格)
	
	 * @param text
	 * @return String
	 */
	public static String replaceSpace(String text)
	{
		String result = URLDecoder.decode(URLEncoder.encode(text)
				.replaceAll("%90", "")
				.replaceAll("%8a", "")).trim()
		.replaceAll("�", "");
		return result;
	}

}
