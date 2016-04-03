package com.uc.system.util;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
/**
 * 
 * @Description:TODO(Mongo链接生产工具类)
 * @ClassName: CWT_DBUtil
 * @author 陈炜涛
 * @Date 2016年2月18日 下午4:01:03
 *
 */
public class CWT_DBUtil {
	private static Mongo m;
	/**
	 * 
	 * @Decription:TODO(获得链接)
	 * @param MongoIp
	 * @param dbName
	 * @param collName
	 * @returnTODO
	 * @Exception 
	 * @return DBCollection
	 */
	public static DBCollection getDBCollection(String MongoIp,String dbName,String collName)
	{
		try {
			m = new Mongo(MongoIp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m.getDB(dbName).getCollection(collName);
	}
	
	/**
	 * 
	 * @Decription:TODO(关闭链接)TODO
	 * @Exception 
	 * @return void
	 */
	public static void close(){
		if (m!=null) {
			try {
				m.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
