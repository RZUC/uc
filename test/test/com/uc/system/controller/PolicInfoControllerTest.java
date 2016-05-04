/**
 * 
 */
package test.com.uc.system.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import test.object.ObjectWebTest;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @ClassName: PolicInfoControllerTest 
 * @author 落花流水 
 * @date 2016年4月30日 上午11:53:34  
 */
public class PolicInfoControllerTest extends ObjectWebTest {
	
	
	@Test
    public void mocMveTest(){
	    MvcResult result;
        try
        {
            result = mockMvc.perform(MockMvcRequestBuilders.get("/login.do?username=renhao&password=123456"))  
//                .andExpect(MockMvcResultMatchers.view().name("user/view"))  
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))  
                .andDo(MockMvcResultHandlers.print())  
                .andReturn();
//            Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }  
      
    
    }
	
}
