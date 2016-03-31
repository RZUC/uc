/** 
 * @Title: CopyBean.java 
 * @Package com.zw.qbjc.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Administrator 
 * @date 2015年12月18日 下午4:40:20 
 * @version V1.0 
 */
/**
* 
*/
package com.zhiwei.wjgg.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 拷贝对象
 * @Description 将父类所有的属性COPY到子类中。
 * @Description 类定义中child一定要extends father；
 * @Description 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate
 * @ClassName: CopyBean
 * @author Administrator
 * @date 2015年12月18日 下午4:40:20
 */
public class FatherToChildUtils
{
    static Logger log = LoggerFactory.getLogger(FatherToChildUtils.class);
    
    public static void fatherImpartSon(Object fatherBean, Object sonBean)
    {
        if (fatherBean == null)
        {
            log.error("父类对象为空,程序结束");
            return;
        }
        if (sonBean == null)
        {
            log.error("子类对象为空,程序结束");
            return;
        }
        
        // 获取对象对应类中的所有属性域
        Field[] fields = fatherBean.getClass().getDeclaredFields();
        
        for (Field field : fields)
        {
            // 修改访问控制权限
            boolean accessFlag = field.isAccessible();
            if (!accessFlag)
            {
                field.setAccessible(true);
            }
            
            Object param;
            try
            {
                param = field.get(fatherBean);
                field.set(sonBean, param);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            
            // 恢复访问控制权限
            field.setAccessible(false);
        }
    }
    
}
