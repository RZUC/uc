/**
 * 
 */
package test.com.uc.system.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uc.system.servlet.PolicyTypeController;

import test.object.ObjectWebTest;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: PolicInfoControllerTest
 * @author 落花流水
 * @date 2016年4月30日 上午11:53:34
 */
public class PolicyTypeControllerTest extends ObjectWebTest {

	@Before
	public void setUp() {
		PolicyTypeController colltroller = new PolicyTypeController();
		mockMvc = MockMvcBuilders.standaloneSetup(colltroller).build();
	}

	@Test
	public void mocMveTest() {
		MvcResult result;
		try {
			result = mockMvc
					.perform(
							MockMvcRequestBuilders
									.get("/policyType/showAllPolicyType.do"))
					// .andExpect(MockMvcResultMatchers.view().name("user/view"))
					// .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			// Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
