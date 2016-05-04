/**
 * 
 */
package test.com.uc.system.controller;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uc.system.exception.ZhiWeiException;
import com.uc.system.servlet.LoginController;
import com.uc.system.servlet.PolicyInfoController;

import test.object.ObjectWebTest;

/**
 * @author Simple
 *
 */
public class LoginControllerTest extends ObjectWebTest
{
    @Resource
    LoginController login;
    
    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(login).build();
    }
    @Test
    public void loginTest()
        throws ZhiWeiException
    {
        MvcResult result;
        try
        {
            result = mockMvc.perform(MockMvcRequestBuilders.get("/login.do?username=renhao&password=123456"))  
              .andDo(MockMvcResultHandlers.print())  
              .andReturn();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//      Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }
    
}
