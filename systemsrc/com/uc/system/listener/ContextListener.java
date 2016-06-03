package com.uc.system.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.uc.system.dao.LocationDao;

/**
 * 
 * 项目名称：WeiXinSiXin 类名称：CodeFilter 类描述：过滤器用于过滤乱码问题 创建人：littleFAT 创建时间：2015年5月21日
 * 下午2:34:26 修改人：littleFAT 修改时间：2015年5月21日 下午2:34:26 修改备注：
 * 
 * @version
 * 
 */
public class ContextListener implements ServletContextListener {
	@Resource
	LocationDao location;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(location == null);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}