/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月26日
    * @version 1.00 
*/ 
package test.com.zhiwei.manager.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.zhiwei.uc.system.service.IGeneralService;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @ClassName: ReflectTest 
 * @author 落花流水 
 * @date 2016年1月26日 下午2:33:59  
 */
public class ReflectTest
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Class<?> cless = IGeneralService.class;
        
        System.out.println(cless.getName());
        System.out.println(cless.getSimpleName());
        System.out.println("地址："+cless.getClassLoader().getResource("").toString());
        for(Method m :cless.getDeclaredMethods()){
            System.out.print("访问级别："+Modifier.toString(m.getModifiers()));
            System.out.println("\t\t方法名："+m.getName());
            Class<?>[] p = m.getParameterTypes();
            for(Class c:p){
                System.out.println("参数："+c.getName());
            }
        }
    }
}
