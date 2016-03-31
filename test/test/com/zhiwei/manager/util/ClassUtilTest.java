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

import java.util.List;

import com.zhiwei.uc.system.model.Permission;
import com.zhiwei.uc.system.model.User;
import com.zhiwei.uc.system.util.ClassUtil;

/**
 * @Description: TODO(获取包下面所有的Class文件)
 * @ClassName: ClassUtilTest
 * @author 落花流水
 * @date 2016年1月20日 下午1:36:40
 */
public class ClassUtilTest
{
    public static void main(String[] args)
        throws Exception
    {
        List<Class<?>> classes = ClassUtil.getClasses("com.zhiwei.manager.model");
//        for (Class clas : classes)
//        {
//            if (User.class != clas || ApplicationServer.class != clas || Permission.class != clas)
//                System.out.println(clas.getName());
//        }
    }
}
