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
package com.uc.system.util;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: BeanUtils
 * @author 落花流水
 * @date 2016年1月20日 上午10:07:46
 */
@SuppressWarnings("unchecked")
public class BeanUtils
{
    
    // 公共部分
    private static final String RT_1 = "\r\n";
    
    private static final String RT_2 = RT_1 + RT_1;
    
    private static final String BLANK_1 = " ";
    
    private static final String BLANK_4 = "    ";
    
    private static final String BLANK_8 = BLANK_4 + BLANK_4;
    
    // 注释部分
    
    private static final String ANNOTATION_AUTHOR_File =
        "/**\r* ***************************************************\r * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *\r *****************************************************\r * \r *\r*/";
        
    private static final String ANNOTATION_AUTHOR_PARAMTER = "@author ";
    
    private static final String ANNOTATION_AUTHOR_NAME = "cwt";
    
    private static final String ANNOTATION_AUTHOR = ANNOTATION_AUTHOR_PARAMTER + ANNOTATION_AUTHOR_NAME;
    
    private static final String ANNOTATION_DATE = "@date ";
    
    private static final String ANNOTATION = "/**" + RT_1 + BLANK_1 + "*" + BLANK_1 + ANNOTATION_AUTHOR + RT_1 + BLANK_1
        + "*" + BLANK_1 + ANNOTATION_DATE + getDate() + RT_1 + BLANK_1 + "*/" + RT_1;
        
    // 文件 地址
    // private static final String BEAN_PATH = "com/b510/base/bean";
    private static final String DAO_PATH = "com/zhiwei/wjgg/dao";
    
    private static final String DAO_IMPL_PATH = "com/zhiwei/wjgg/dao/impl";
    
    private static final String SERVICE_PATH = "com/zhiwei/wjgg/service";
    
    private static final String SERVICE_IMPL_PATH = "com/zhiwei/wjgg/service/impl";
    
    // 包名
    private static final String DAO_URL = "com.zhiwei.wjgg.dao";
    
    private static final String DAO_IMPL_URL = "com.zhiwei.wjgg.dao.impl";
    
    private static final String SERVICE_URL = "com.zhiwei.wjgg.service";
    
    private static final String SERVICE_IMPL_URL = "com.zhiwei.wjgg.service.impl";
    
    // 基本类名称
    private static final String BASE_DAO_NAME = "CommonDao";
    
    //
    private static final String DAO = "Dao";
    
    private static final String DAOIMPL = "DaoImpl";
    
    private static final String SERVICE = "Service";
    
    private static final String SERVICEIMPL = "ServiceImpl";
    
    private static final String IMPORT = "import" + BLANK_1; // 导入包
    
    private static final String PACKAGE = "package" + BLANK_1; // 导入包
    
    private static final String INTERFACE = "public interface" + BLANK_1; // 导入包
    
    private static final String CLASS = "public class" + BLANK_1; // 导入包
    
    private static final String DAOEXTENDS = DAO + " extends" + BLANK_1; // 导入包
    
    private static final String DAOImpl = DAOIMPL + " implements" + BLANK_1; // 导入包
    
    private static final String FENHAO = ";"; // 分号
    //
    
    private static final String SPRINGANNOTATION = "javax.annotation.Resource";
    private static final String MONGOTEMP = "org.springframework.data.mongodb.core.MongoTemplate";
    
    /**
     * 创建bean的Dao<br>
     * 
     * @param c
     * @throws Exception
     */
    public void createBeanDao(Class c)
        throws Exception
    {
        String cName = c.getName();
        String fileName = System.getProperty("user.dir") + "/src/" + DAO_PATH + "/" + getClassName(cName) + "Dao.java";
        
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        
        fw.write(ANNOTATION_AUTHOR_File + RT_2);
        fw.write(PACKAGE + DAO_URL + FENHAO + RT_2);
        fw.write(IMPORT + DAO_URL + "." + BASE_DAO_NAME + FENHAO + RT_1);// 基类的包
        fw.write(IMPORT + cName + FENHAO + RT_1);// 实体的包
        fw.write(ANNOTATION + INTERFACE + getClassName(cName) + DAOEXTENDS + BASE_DAO_NAME + "<" + getClassName(cName)
            + ">" + RT_1 + "{" + RT_1 + "}");
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    /**
     * 创建bean的Dao的实现类
     * 
     * @param c
     * @throws Exception
     */
    public void createBeanDaoImpl(Class c)
        throws Exception
    {
        String cName = c.getName();
        String fileName =
            System.getProperty("user.dir") + "/src/" + DAO_IMPL_PATH + "/" + getClassName(cName) + "DaoImpl.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        
        fw.write(ANNOTATION_AUTHOR_File + RT_2);
        fw.write(PACKAGE + DAO_IMPL_URL + FENHAO + RT_2);
        fw.write(IMPORT + SPRINGANNOTATION +FENHAO+RT_1);// 基类的包
        fw.write(IMPORT + MONGOTEMP +FENHAO+ RT_2);// Mongo的包
        fw.write(IMPORT + DAO_URL + "." + getClassName(cName) + DAO + FENHAO + RT_2);// 基类的包
        fw.write(
            ANNOTATION + CLASS + getClassName(cName) + DAOImpl + getClassName(cName) + DAO + RT_1 + "{" );
        fw.write(RT_1+BLANK_4+"@Resource"+RT_1+BLANK_4+"private MongoTemplate mongoTemp"+FENHAO);
        fw.write(RT_2 + "}");
        fw.flush();
        fw.close();
        // TODO:反射实现OverWriter
        showInfo(fileName);
    }
    
    /**
     * 创建bean的service
     * 
     * @param c
     * @throws Exception
     */
    public void createBeanService(Class c)
        throws Exception
    {
        String cName = c.getName();
        String fileName =
            System.getProperty("user.dir") + "/src/" + SERVICE_PATH + "/" + getClassName(cName) + "Service.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(ANNOTATION_AUTHOR_File + RT_2);
        fw.write(PACKAGE + BLANK_1 + SERVICE_URL + FENHAO + RT_2);
        fw.write(ANNOTATION + INTERFACE + getClassName(cName) + SERVICE + RT_1 + "{" + RT_1 + "}");
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    /**
     * 创建bean的service的实现类
     * 
     * @param c
     * @throws Exception
     */
    public void createBeanServiceImpl(Class c)
        throws Exception
    {
        String cName = c.getName();
        String fileName = System.getProperty("user.dir") + "/src/" + SERVICE_IMPL_PATH + "/" + getClassName(cName)
            + "ServiceImpl.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write("package " + SERVICE_IMPL_URL + ";" + RT_2);
        fw.write(IMPORT + SPRINGANNOTATION + FENHAO + RT_2);
        fw.write(IMPORT + DAO_URL + "." + getClassName(cName) + "Dao " + FENHAO + RT_1);
        fw.write(IMPORT + SERVICE_URL + "." + getClassName(cName) + "Service " + FENHAO + RT_1);
        fw.write(ANNOTATION + RT_2);
        fw.write("public class " + getClassName(cName) + "ServiceImpl " + "implements " + getClassName(cName)
            + "Service{" + RT_2 + BLANK_4 + "@Resource" + RT_1 + BLANK_4 + "private " + getClassName(cName) + "Dao "
            + getLowercaseChar(getClassName(cName)) + "Dao;" + RT_2 + "}");
        fw.flush();
        fw.close();
        showInfo(fileName);
    }
    
    /**
     * 获取路径的最后面字符串<br>
     * 其实就是获取类名 如：<br>
     * com.zhiwei.manager.model.User<br>
     * User
     * 
     * @param str
     * @return
     */
    public String getClassName(String str)
    {
        if ((str != null) && (str.length() > 0))
        {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1)))
            {
                return str.substring(dot + 1);
            }
        }
        return str;
    }
    
    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     * <code>str = "UserDao";</code><br>
     * <code>return "userDao";</code>
     * 
     * @param str
     * @return
     */
    public String getLowercaseChar(String str)
    {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
    
    /**
     * 显示信息
     * 
     * @param info
     */
    public void showInfo(String info)
    {
        System.out.println("创建文件：" + info + "成功！");
    }
    
    /**
     * 获取系统时间
     * 
     * @return
     */
    public static String getDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
