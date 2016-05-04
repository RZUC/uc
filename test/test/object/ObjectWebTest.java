/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月16日
    * @version 1.00 
*/
package test.object;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({@ContextConfiguration(name = "parent", locations = "classpath:application-config.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:servlet-config.xml")})
public abstract class ObjectWebTest extends AbstractJUnit4SpringContextTests
{
    protected MockMvc mockMvc;
    }
