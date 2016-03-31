/**
 * 
 */
package test.com.zhiwei.wjgg.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.zhiwei.wjgg.dao.ViewPointDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.ViewPoint;

/**@Description 测试观点数据层
 * 
 * @author 李自贤
 * @date 2016年2月29日
 */
public class ViewPointTest extends ObjectTest
{
	@Resource
	ViewPointDao dao;

	@Test
	public void saveTest()
	{
		ViewPoint ob = new ViewPoint();
		ob.setId("132");
		ob.setType("网民");
		Map<String, Double> point = new HashMap<String, Double>();
		point.put("挺好的", 50.00);
		ob.setPointMap(point);
		try
		{
			dao.insert(ob);
		} catch (ZhiWeiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findOneTest()
	{
		try
		{
			ViewPoint viewPoint = dao.findOne("123");
		} catch (ZhiWeiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAllTest()
	{
		try
		{
			System.out.println(dao.findAll().size());
		} catch (ZhiWeiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeTest()
	{
		try
		{
			System.out.println(dao.removeOneById("123"));
		} catch (ZhiWeiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeAllTest()
	{
		try
		{
			System.out.println(dao.removeAll());
		} catch (ZhiWeiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
