/** 
 * @Title: CopyBean.java 
 * @Package com.zw.qbjc.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Administrator 
 * @date 2015年12月18日 下午4:40:20 
 * @version V1.0 
 */
/**
 * 
 */
package com.uc.system.util;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.uc.system.model.PolicyType;

/**
 * 把Bean转换为数据
 * 
 * @ClassName: MongoQuerBeanTO
 * @Description: TODO(把实体类转换成Mongo查询语句)
 * @author Administrator
 * @date 2016年7月6日 下午9:45:15
 */
public class MongoQuerBeanTO {
	static Logger log = LoggerFactory.getLogger(MongoQuerBeanTO.class);

	public static <T> DBObject change(T t) {
		DBObject obj = new BasicDBObject();
		// 获取对象对应类中的所有属性域
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 修改访问控制权限
			boolean accessFlag = field.isAccessible();
			if (!accessFlag) {
				field.setAccessible(true);
			}

			Object param;
			String key;
			try {
				key = field.getName().equals("id") ? "_id" : field.getName();
				param = field.get(t);
				if (param == null) {
					continue;
				} else if (param instanceof String && !"".equals(param)) {
					obj.put(key, param);
				} else if (param instanceof Integer
						&& Integer.valueOf(param.toString()) != 0) {
					obj.put(key, param);
				} else if (param instanceof Long
						&& Long.valueOf(param.toString()) != 0) {
					obj.put(key, param);
				} else if (param instanceof List && ((List) param).size() > 0) {
					obj.put(key, ((List) param).toArray());
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// 恢复访问控制权限
			field.setAccessible(false);
		}
		return obj;
	}

	private static boolean AssertInteger(Object param) {
		boolean flag = false;
		if (param instanceof Integer) {

		}
		return flag;
	}

	public static void main(String[] args) {
		PolicyType type = new PolicyType();
		type.setId(1);
		type.setName("哈哈");
		type.setOrder(10);

		System.out.println(change(type));
	}
}
