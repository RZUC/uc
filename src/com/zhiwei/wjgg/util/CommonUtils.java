package com.zhiwei.wjgg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CommonUtils
{
    private final static Log log = LogFactory.getLog(CommonUtils.class);
    
    /**
     * 字符串转化为整形，若字符串非法，则返回0
     * 
     * @param str 字符串
     * @return 整形结果
     */
    public static int strToInt(String str)
    {
        return strToIntDef(str, 0);
    }
    
    /**
     * 字符串转化为整形
     * 
     * @param str 字符串
     * @param def 默认值(若为非法字符串是提供的返回值)
     * @return 整形结果
     */
    public static int strToIntDef(String str, int def)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            if (log.isDebugEnabled())
                log.error("[CommonUtils][strToIntDef(String, int)] NumberFormatException: " + str, e);
            return def;
        }
    }
    
    /**
     * 字符串转化为长整形，若字符串非法，则返回0
     * 
     * @param str 字符串
     * @return 长整形结果
     */
    public static long strToLong(String str)
    {
        return strToLongDef(str, 0);
    }
    
    /**
     * 字符串转化为长整形，若字符串非法，则返回0
     * 
     * @param str 字符串
     * @param def 默认值(若为非法字符串是提供的返回值)
     * @return 长整形结果
     */
    public static long strToLongDef(String str, long def)
    {
        try
        {
            return Long.parseLong(str);
        }
        catch (NumberFormatException e)
        {
            if (log.isDebugEnabled())
                log.error("[CommonUtils][strToLong(String, long)] NumberFormatException: " + str, e);
            return def;
        }
    }
    
    /**
     * 判断是否是大整形格式(@see ava.math.BigDecimal)
     * 
     * @param input 数字格式字符串
     * @return
     */
    public static boolean isBigDecimal(String input)
    {
        try
        {
            new java.math.BigDecimal(input);
            return true;
        }
        catch (NumberFormatException e)
        {
            if (log.isDebugEnabled())
                log.error("[CommonUtils][isBigDecimal(String)] Exception: " + input, e);
            return false;
        }
    }
    
    /**
     * 判断是否是整形格式
     * 
     * @param input 数字字符
     * @return
     */
    public static boolean isInteger(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e)
        {
            if (log.isDebugEnabled())
                log.error("[CommonUtils][strToLong(String)] NumberFormatException: " + input, e);
                
            return false;
        }
    }
    
    public static void main(String[] args)
    {
    }
    
}
