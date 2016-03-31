package com.zhiwei.wjgg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LinkedMap;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @ClassName: Util
 * @Description: TODO(一些常用的工具类代码)
 * @author 任浩
 * @date 2015年6月16日 下午3:06:25
 */
public class RenH_Util
{
    public final static Logger log = LoggerFactory.getLogger(RenH_Util.class);
    
    /**
     * @Title: getTimeMap
     * @Description: TODO(通过开始时间和结束时间来返回一个Map<begTiem,EndTime>)
     * @param @param start
     * @param @param end
     * @param @param type dd-天，hh-小时，mm-分
     * @param @param hour 偏移量一小时
     * @param @return 设定文件
     * @return Map<String,String> 返回类型
     */
    public static Map<String, String> getTimeMap(String start, String end, String type, int time)
    {
        Map<String, String> map = new LinkedMap();
        Date baseTime = TimeUtil.parseTime(start);
        
        Date endTime = TimeUtil.parseTime(end);
        
        String startStr = "";
        String endStr = "";
        while (true)
        {
            if (map.size() == 0)
            {
                startStr = start;
            }
            else
            {
                startStr = TimeUtil.formatTime(baseTime);
            }
            
            if ("dd".equals(type))
            {
                endStr = TimeUtil.formatTime(TimeUtil.getSkipTime(baseTime, time, 0, 0, -1));
            }
            else if ("hh".equals(type))
            {
                endStr = TimeUtil.formatTime(TimeUtil.getSkipTime(baseTime, 0, time, 0, -1));
            }
            else if ("mm".equals(type))
            {
                endStr = TimeUtil.formatTime(TimeUtil.getSkipTime(baseTime, 0, 0, time, -1));
            }
            
            // endStr = TimeUtil.formatTime(TimeUtil.getSkipTime(baseTime, 0, hour, 0, -1));
            
            if ("dd".equals(type))
            {
                endStr = TimeUtil.formatTime(TimeUtil.getSkipTime(baseTime, time, 0, 0, -1));
                if (TimeUtil.getSkipTime(baseTime, time, 0, 0, -1).getTime() - endTime.getTime() >= 0)
                {
                    endStr = end;
                    map.put(startStr, endStr);
                    break;
                }
                
            }
            else if ("hh".equals(type))
            {
                if (TimeUtil.getSkipTime(baseTime, 0, time, 0, -1).getTime() - endTime.getTime() >= 0)
                {
                    endStr = end;
                    map.put(startStr, endStr);
                    break;
                }
                
            }
            else if ("mm".equals(type))
            {
                if (TimeUtil.getSkipTime(baseTime, 0, 0, time, -1).getTime() - endTime.getTime() >= 0)
                {
                    endStr = end;
                    map.put(startStr, endStr);
                    break;
                }
                
            }
            
            // baseTime = TimeUtil.getSkipTime(baseTime, 0, hour, 0, 0);
            if ("dd".equals(type))
            {
                baseTime = TimeUtil.getSkipTime(baseTime, time, 0, 0, 0);
            }
            else if ("hh".equals(type))
            {
                baseTime = TimeUtil.getSkipTime(baseTime, 0, time, 0, 0);
            }
            else if ("mm".equals(type))
            {
                baseTime = TimeUtil.getSkipTime(baseTime, 0, 0, time, 0);
            }
            map.put(startStr, endStr);
        }
        return map;
    }
    
    @Test()
    public void test()
    {
        Map<String, String> map = getTimeMap("2015-10-01 00:00:00", "2015-10-02 23:59:59", "mm", 10);
        for (String key : map.keySet())
        {
            System.out.println("开始时间：" + key + "结束时间：" + map.get(key));
        }
    }
    
    /**
     * 
     * replaceBlank(去除一些特殊符号)
     */
    public static String replaceBlank(Object str)
    {
        String dest = "";
        if (str != null)
        {
            Pattern p = Pattern.compile("=|,|\"|“|\\s*|\t|\r|\n");
            Matcher m = p.matcher(str.toString());
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    /**
     * @Title: plusKey
     * @Description: TODO(对目标的维度的map进行累加操作)
     * @param @param map
     * @param @param key 设定文件
     * @return void 返回类型
     */
    public static void plusKey(Map<String, Integer> map, String key)
    {
        if (map.containsKey(key))
        {
            map.put(key, map.get(key) + 1);
        }
        else
        {
            map.put(key, 1);
        }
    }
    
    /**
     * 
     * @declaration 返回翻页的次数
     * @param Totalcount 数据总条数
     * @param pageRecord 每页条数
     *            
     */
    public static long PageCount(long Totalcount, long pageRecord)
    {
        long count = 1;
        if (Totalcount > pageRecord)
        {
            
            count = (Totalcount % pageRecord == 0) ? (Totalcount / pageRecord) : (Totalcount / pageRecord + 1);
        }
        return count;
    }
    
    /**
     * unicode 转换成 中文
     * 
     * @author fanhui 2007-3-15
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString)
    {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;)
        {
            aChar = theString.charAt(x++);
            if (aChar == '\\')
            {
                aChar = theString.charAt(x++);
                if (aChar == 'u')
                {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++)
                    {
                        aChar = theString.charAt(x++);
                        switch (aChar)
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                        
                    }
                    outBuffer.append((char)value);
                }
                else
                {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
                
            }
            else
                outBuffer.append(aChar);
                
        }
        return outBuffer.toString();
    }
    
    /**
     * @Title: distinct
     * @Description: TODO(对列去重)
     * @param @param coll 表
     * @param @param collName 列名
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> distinct(DBCollection coll, String collName)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> dis = new ArrayList<String>();
        List<DBObject> list = coll.find(new BasicDBObject(), new BasicDBObject(collName, 1)).toArray();
        for (DBObject obj : list)
        {
            plusKey(map, obj.get(collName).toString());
        }
        
        // 返回数据
        for (String str : map.keySet())
        {
            dis.add(str);
        }
        
        return dis;
    }
    
    /**
     * @Title: distinctElement
     * @Description: TODO(对List中的数据去重)
     * @param @param list 原始数据
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> distinctElement(List<String> list)
    {
        Map<String, Integer> distinctMap = new HashMap<String, Integer>();
        List<String> dis = new ArrayList<String>();
        
        for (String str : list)
        {
            plusKey(distinctMap, str);
        }
        
        for (String str : distinctMap.keySet())
        {
            dis.add(str);
        }
        
        return dis;
    }
    
    // 计算百分比
    /**
     * @Title: percentMap
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param map <String,Integer>
     * @return Map<String,String> 返回类型
     */
    public static Map<String, String> percentMap(Map<String, Integer> map)
    {
        
        Map<String, String> newMap = new HashMap<String, String>();
        Integer total = 0;
        for (Entry e : map.entrySet())
        {
            total += Integer.valueOf(e.getValue().toString());
        }
        
        for (String str : map.keySet())
        {
            double f = map.get(str).doubleValue() / total * 100;
            
            // 保留两位小数
            BigDecimal b = new BigDecimal(f);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            
            newMap.put(str, f1 + "%");
        }
        return newMap;
    }
    
    public static Double getDouble(double d1, double d2, int num)
    {
        double f = d1 / d2;
        // 保留两位小数
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        
        return f1;
    }
    
    public static List<Integer> StringToIntegerLst(List<String> inList)
    {
        List<Integer> list = new ArrayList<Integer>(inList.size());
        try
        {
            for (int i = 0, j = inList.size(); i < j; i++)
            {
                list.add(Integer.parseInt(inList.get(i)));
            }
        }
        catch (Exception e)
        {
        }
        return list;
    }
    
    public static List<Long> StringToLongLst(List<String> inList)
    {
        List<Long> list = new ArrayList<Long>(inList.size());
        try
        {
            for (int i = 0, j = inList.size(); i < j; i++)
            {
                list.add(Long.parseLong(inList.get(i)));
            }
        }
        catch (Exception e)
        {
        }
        return list;
    }
    
    /**
     * 字符串转换到时间格式
     * 
     * @param str 需要转换的字符串
     * @return sourceFormatStr 需要转换的字符串的时间格式
     * @param TagformatStr 需要格式的目标字符串 举例 yyyyMMdd
     * @return String 返回转换后的时间字符串
     * @throws ParseException 转换异常
     */
    public static String StringToDate(String str, String sourceFormatStr, String TagformatStr)
    {
        // 根据原目标来转换
        DateFormat sdf = new SimpleDateFormat(sourceFormatStr);
        Date date = null;
        try
        {
            date = sdf.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(TagformatStr);
        return s.format(date);
    }
    
    public static Map<String, Integer> fcZoom(List<DBObject> list)
    {
        Map<String, Integer> dj = new HashMap<String, Integer>();
        dj.put(">2000", 0);
        dj.put("1600~2000", 0);
        dj.put("1000~1600", 0);
        dj.put("800~1000", 0);
        dj.put("500~800", 0);
        dj.put("200~500", 0);
        dj.put("50~200", 0);
        dj.put("<50", 0);
        
        for (DBObject obj : list)
        {
            Integer count = (Integer)obj.get("followersCount");
            if (count != null)
            {
                
                if (count > 2000)
                {
                    dj.put(">2000", dj.get(">2000") + 1);
                }
                else if (count > 1600 && count < 2000)
                {
                    dj.put("1600~2000", dj.get("1600~2000") + 1);
                    
                }
                else if (count > 1000 && count < 1600)
                {
                    dj.put("1000~1600", dj.get("1000~1600") + 1);
                    
                }
                else if (count > 800 && count < 1000)
                {
                    dj.put("800~1000", dj.get("800~1000") + 1);
                    
                }
                else if (count > 500 && count < 800)
                {
                    dj.put("500~800", dj.get("500~800") + 1);
                    
                }
                else if (count > 200 && count < 500)
                {
                    dj.put("200~500", dj.get("200~500") + 1);
                    
                }
                else if (count > 50 && count < 200)
                {
                    dj.put("50~200", dj.get("50~200") + 1);
                    
                }
                else if (count < 50)
                {
                    dj.put("<50", dj.get("<50") + 1);
                }
                
            }
        }
        return dj;
    }
    
    /**
     * @Title: getDateListWithBegAndEnd
     * @Description: TODO(根据开始时间和结束时间，计算时间的列表)
     * @param @param begDate 2012-01-01
     * @param @param endDate 2012-12-31
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> getDateListWithBegAndEnd(String begDate, String endDate)
    {
        Date beg = TimeUtil.parseDate(begDate);
        Date end = TimeUtil.parseDate(endDate);
        
        List<String> dateStrings = new ArrayList<String>();
        for (int i = 0; i <= TimeUtil.getSubday(end, beg); i++)
        {
            dateStrings.add(TimeUtil.formatDate(TimeUtil.getSkipTime(beg, i, 0, 0, 0)));
        }
        return dateStrings;
    }
    
    // 正则表达式
    /**
     * #([^\\#|.]+)# 话题的表达式
     * 
     * @用户的表达式 @[\u4e00-\u9fa5a-zA-Z0-9_-]{4,30}
     *         
     */
    public static String getString(String text, String expression)
    {
        StringBuffer buffer = new StringBuffer();
        Pattern TAG_PATTERN = Pattern.compile(expression);
        Matcher m = TAG_PATTERN.matcher(text);
        while (m.find())
        {
            buffer.append(m.group());
            buffer.append(" ");
        }
        if (!" ".equals(buffer.toString()))
        {
            System.out.println("原始内容：" + text + "\t获取的数据：" + buffer.toString());
            return buffer.toString();
        }
        else
        {
            System.out.println("原始内容：" + text.substring(0, 10) + "\t没找到@用户：");
        }
        return "";
    }
    
    /**
     * @Title: toStringList
     * @Description: TODO(根据KEY获取字符串List)
     * @param @param list
     * @param @param key 关键字
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> toStringList(List<DBObject> list, String key)
    {
        List<String> ids = new ArrayList<String>();
        
        for (DBObject obj : list)
        {
            ids.add(obj.get(key).toString());
        }
        return ids;
    }
    
    /**
     * @Title: getUserCount
     * @Description: TODO(返回用逗号分隔的key字符串)
     * @param @param ids
     * @param @param num 多少个为一组
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> getUserCount(List<String> ids, int num)
    {
        List<String> midList = new ArrayList<String>();
        
        StringBuffer idsStr = new StringBuffer();
        int count = 0;
        boolean flag = true;
        while (flag)
        {
            idsStr.append(ids.get(count) + ",");
            
            if (count != 0 && (count + 1) % num == 0)
            {
                midList.add(idsStr.toString().substring(0, idsStr.toString().length() - 1));
                System.out.println("下标Count:" + count);
                System.out.println(idsStr.toString().substring(0, idsStr.toString().length() - 1));
                idsStr = new StringBuffer();
            }
            
            if (count == ids.size() - 1)
            {
                System.out.println("最后一组下标的位置：" + count);
                if (idsStr.length() > 0)
                {
                    String id = idsStr.toString().substring(0, idsStr.toString().length() - 1);
                    midList.add(id);
                    System.out.println(id);
                }
                flag = false;
            }
            count++;
        }
        System.out.println("IDS组数：" + midList.size());
        return midList;
    }
    
    public static String getGenderString(String str)
    {
        String string = str.equals("n") ? "未知" : str.equals("m") ? "男" : "女";
        return string;
    }
    
    public static File getFile(String path, String fileName)
    {
        // 判断目录，不存在那么创建目录
        path.replace("\\", File.pathSeparator);
        
        File dir = new File(path);
        
        System.out.println("文件目录是否存在：" + dir.exists() + "\t是否是目录:" + dir.isDirectory());
        if (!dir.exists())
        {
            dir.mkdirs();
            System.out.println("[" + dir + "]文件目录不存在,创建新的目录");
        }
        
        // 判断文件是否存在
        File file = new File(path, fileName);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }
    
    public static List<DBObject> MapToDBobject(Map<String, Integer> map)
    {
        List<DBObject> list = new ArrayList<DBObject>();
        for (String key : map.keySet())
        {
            BasicDBObject obj = new BasicDBObject();
            obj.put(key, map.get(key));
            list.add(obj);
        }
        
        return list;
    }
    
    public static Map<String, String> getMapStr(Map<String, Integer> dataMap)
    {
        Map<String, String> tagMapStr = new HashMap<String, String>();
        for (String key : dataMap.keySet())
        {
            tagMapStr.put(key, dataMap.get(key).toString());
        }
        return tagMapStr;
    }
    
    public static List<String> basicDBList2StringList(BasicDBList list)
    {
        List<String> ids = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++)
        {
            Object o = list.get(i);
            ids.add(o.toString());
        }
        return ids;
    }
    
    /**
     * @Title: quChong
     * @Description: 对数组去重
     * @param @param list
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> quChong(List<String> list)
    {
        synchronized (list)
        {
            List<String> ids = new ArrayList<String>();
            Map<String, String> users = new HashMap<String, String>();
            for (String id : list)
            {
                users.put(id, id);
            }
            
            for (String id : users.keySet())
            {
                ids.add(id.trim());
            }
            log.debug("总数据：{}\t去重后：{}", list.size(), ids.size());
            return ids;
        }
        
    }
    
    private static String String2Json(String s)
    {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
        {
            char c = chars[i];
            switch (c)
            {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if ((c >= 0 && c <= 31) || c == 127) // 在ASCⅡ码中，第0～31号及第127号(共33个)是控制字符或通讯专用字符
                    {
                    
                    }
                    else
                    {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
    
    /**
     * 提取数字
     */
    public static Integer pareser(String str)
    {
        String i = "";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find())
        {
            Pattern pattern2 = Pattern.compile("\\d+");
            Matcher matcher2 = pattern2.matcher(matcher.group());
            while (matcher2.find())
            {
                i += matcher2.group();
            }
        }
        
        try
        {
            return "".equals(i) ? 0 : Integer.valueOf(i);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("原始数据：" + str + "\t转换后的数据:" + i);
            
            return 0;
        }
    }
    
    public static String getTypeByObject(Object obj)
    {
        String type = "object";
        if (obj instanceof ObjectId)
        {
            type = "ObjectId";
        }
        if (obj instanceof Date)
        {
            type = "date";
        }
        if (obj instanceof Integer)
        {
            type = "int";
        }
        if (obj instanceof Long)
        {
            type = "long";
        }
        if (obj instanceof Boolean)
        {
            type = "boolean";
        }
        if (obj instanceof String)
        {
            type = "string";
        }
        return type;
    }
    
    public static List<String> getWorksByFile(File file)
    {
        List<String> list = new ArrayList<String>();
        FileInputStream fi = null;
        try
        {
            fi = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fi));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
            }
            reader.close();
            fi.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fi != null)
                {
                    fi.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    /**
     * @Title: MD5
     * @Description: MD5后的字符串
     * @param @param pwd
     * @param @return 设定文件
     * @return String 返回类型
     */
    public final static String MD5(String pwd)
    {
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try
        {
            // 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();
            
            // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            
            // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);
            
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();
            
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            { // i = 0
                byte byte0 = md[i]; // 95
                str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
                str[k++] = md5String[byte0 & 0xf]; // F
            }
            // 返回经过加密后的字符串
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
