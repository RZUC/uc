/**
 * 
 */
package test.com.uc.system.service;

import javax.annotation.Resource;

import org.junit.Test;

import test.object.ObjectTest;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyType;
import com.uc.system.service.PolicyTypeService;

/**
 * @author Administrator 政策类型修改
 */
public class PolicyTypeServiceTest extends ObjectTest {

	@Resource
	PolicyTypeService service;

	@Test
	public void add() throws ZhiWeiException {

		PolicyType type = new PolicyType();
		type.setId("1");
		type.setName("政策服务");
		type.setOrder(1);
		service.addPolicyType(type);
	}

	@Test
	public void del() throws ZhiWeiException {

		System.out.println(service.delPolicyType("1"));
	}

	@Test
	public void modif() throws ZhiWeiException {

		PolicyType type = new PolicyType();
		type.setId("1");
		type.setName("政策服务2");
		type.setOrder(2);
		service.addPolicyType(type);
	}

	@Test
	public void findAll() throws ZhiWeiException {
		Page page = new Page();
		page.setPageSize(10);
		page.setPageNum(1);
//		System.out.println(service.findByPage(page).get(0).toString());
	}
}
