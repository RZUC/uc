/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月20日
    * @version 1.00 
*/
package test.com.zhiwei.manager.util;

import com.uc.system.util.BeanUtils;
import com.uc.user.model.User;

/**
 * @Description: TODO()
 * @ClassName: BeanUtils
 * @author 落花流水
 * @date 2016年1月20日 上午10:10:12
 */
public class BeanUtilsTest
{
    
    public static void main(String[] args)
        throws Exception
    {
        BeanUtilsTest beanUtilTest = new BeanUtilsTest();
        BeanUtils beanUtils = new BeanUtils("systemsrc");
//        List<Class<?>> classes = ClassUtil.getClasses("com.zhiwei.manager.model");
//        for (Class clas : classes)
//        {
//            if (User.class != clas)
//            {   
//                if (User.class != clas || ApplicationServer.class != clas || Permission.class != clas)
//                beanUtilTest.beanTool(beanUtils, clas);
//            }
//        }
        
        beanUtilTest.beanTool(beanUtils, User.class);
    }
    
    /**
     * 根据bean生成相应的文件
     * 
     * @param beanUtils
     * @param c
     * @throws Exception
     */
    public void beanTool(BeanUtils beanUtils, Class c)
        throws Exception
    {
        beanUtils.createBeanDao(c);
        beanUtils.createBeanDaoImpl(c);
        beanUtils.createBeanService(c);
        beanUtils.createBeanServiceImpl(c);
    }
}
