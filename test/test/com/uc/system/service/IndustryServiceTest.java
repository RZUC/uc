/**
 * 
 */
package test.com.uc.system.service;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Industry;
import com.uc.system.model.Page;
import com.uc.system.service.IndustryService;

/**
 * @author Administrator 行业维护
 */
public class IndustryServiceTest extends ObjectTest {

	@Resource
	IndustryService service;

	@Test
	public void add() throws ZhiWeiException {
		String id = "1";
		String name = "行业1";
		String fatherID = "0";
		Industry industry = new Industry(id, name, fatherID);
		service.add(industry);

	}

	@Test
	public void del() throws ZhiWeiException {

		String id = "1";
		String name = "行业1";
		String fatherID = "0";
		Industry industry = new Industry(id, name, fatherID);
		service.del(id);
	}

	@Test
	public void modif() throws ZhiWeiException {

		String id = "1";
		String name = "行业2";
		String fatherID = "0";
		Industry industry = new Industry(id, fatherID, name);
		service.add(industry);
	}

	@Test
	public void findAll() throws ZhiWeiException {
		Page page = new Page();
		page.setPageSize(10);
		page.setPageNum(1);
		System.out.println(service.findByPage(page));
	}
}
