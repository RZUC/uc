/**
 * 
 */
package com.zhiwei.uc.system.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
 * @Description: 读取配置文件的工具类 
 * @author Tou Tang
 * @date 2014-11-14 下午3:06:33  
 */
public class PropertiesUtil {

	/**
	 * 
	* @Description: 读取配置文件
	* @param 
	* @return Properties
	 */
	public static Properties getProperties(String configName) {
		Properties pro = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(configName);
			pro = new Properties();
			pro.load(is);
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
		return pro;
	}

}
