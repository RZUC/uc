package com.zhiwei.uc.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;

/**
 * 
 * @ClassName: MapAddUtil
 * @Description: TODO(计算Map的工具类)
 * @author chenweitao
 * @date 2016年3月9日 下午4:12:19
 */
public class MapUtil
{

    /**
     * 
     * @Title: treatOrderMpa
     * @Description: TODO(取前num)
     * @param @param map
     * @param @return 设定文件
     * @return Map<String,List> 返回类型
     */
    public static Map<String, List> treatOrderMapTop(Map<String, List> map,int num)
    {
        List<Entry<String, List>> list = new ArrayList<Entry<String, List>>();
        list.addAll(map.entrySet());

        Map<String, Integer> new_map = new LinkedHashMap<String, Integer>();
        for (Entry<String, List> entry : list)
        {
            new_map.put(entry.getKey(), entry.getValue().size());
        }

        List<Entry<String, Integer>> list1 = TreatOrder
                .treatOrderByCountDesc(new_map);
        List<Entry<String, Integer>> list2 = null;
        if (list1.size() > num)
        {
            list2 = list1.subList(0, num);
        }
        else
        {
            list2 = list1;
        }

        Map<String, List> result_map = new LinkedHashMap<String, List>();
        for (Entry<String, Integer> entry : list2)
        {
            result_map.put(entry.getKey(), map.get(entry.getKey()));
        }

        return result_map;
    }

    /**
     * 
     * @Title: treatOrderMpa
     * @Description: TODO(取前1000)
     * @param @param map
     * @param @return 设定文件
     * @return Map<String,List> 返回类型
     */
    public static Map<String, List> treatOrderMapTop1000(Map<String, List> map)
    {
        List<Entry<String, List>> list = new ArrayList<Entry<String, List>>();
        try
        {
            list.addAll(map.entrySet());
        }
        catch (Exception e)
        {
            System.out.println("Map为空，排序异常");
        }

        Map<String, Integer> new_map = new HashMap<String, Integer>();
        for (Entry<String, List> entry : list)
        {
            new_map.put(entry.getKey(), entry.getValue().size());
        }
        List<Entry<String, Integer>> list1 = TreatOrder
                .treatOrderByCountDesc(new_map);
        List<Entry<String, Integer>> list2 = null;
        if (list1.size() > 1000)
        {
            list2 = list1.subList(0, 1000);
        }
        else
        {
            list2 = list1;
        }

        Map<String, List> result_map = new LinkedHashMap<String, List>();
        for (Entry<String, Integer> entry : list2)
        {
            result_map.put(entry.getKey(), map.get(entry.getKey()));
        }

        return result_map;
    }

    /**
     * 
     * @Title: addMapByKey
     * @Description: TODO(将各Map中相同key的value值相加)
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,Integer> 返回类型
     */
    public static Map<String, Integer> addMapByKeyForInteger(
            List<Map<String, Integer>> list)
    {
        Map<String, Integer> sum = new HashMap<String, Integer>();

        // 遍历所有的map
        for (Map<String, Integer> map : list)
        {

            if (null == map)
            {
                continue;
            }
            for (String key : map.keySet())
            {
                Integer num;
                if (sum.containsKey(key))
                {

                    num = sum.get(key) + map.get(key);

                    sum.put(key, num);
                }
                else
                {
                    sum.put(key, map.get(key));
                }
            }
        }
        return sum;
    }

    /**
     * 
     * @Title: addMapByKey
     * @Description: TODO(将各Map中相同key的value值相加)
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,Integer> 返回类型
     */
    public static Map<String, Double> addMapByKeyForDouble(
            List<Map<String, Double>> list)
    {
        Map<String, Double> sum = new HashMap<String, Double>();

        // 遍历所有的map
        for (Map<String, Double> map : list)
        {

            if (null == map)
            {
                continue;
            }
            for (String key : map.keySet())
            {
                Double num;
                if (sum.containsKey(key))
                {

                    num = sum.get(key) + map.get(key);

                    sum.put(key, num);
                }
                else
                {
                    sum.put(key, map.get(key));
                }
            }
        }
        return sum;
    }

    /**
     * 
     * @Title: addMapByKey
     * @Description: TODO(将各Map中相同key的List值相加)
     * @param @param list
     * @param @return 设定文件
     * @return Map<String,List> 返回类型
     */
    public static Map<String, List> addMapByKeyByList(
            List<Map<String, List>> list)
    {
        Map<String, List> sum = new HashMap<String, List>();

        // 遍历所有的map
        for (Map<String, List> map : list)
        {
            if (null == map)
            {
                continue;
            }
            for (String key : map.keySet())
            {
                List<String> num = new ArrayList<String>();
                if (sum.containsKey(key))
                {
                    num.addAll(sum.get(key));
                    num.addAll(map.get(key));
                    sum.put(key, num);
                }
                else
                {
                    sum.put(key, map.get(key));
                }
            }
        }
        return sum;
    }

    /**
     * 
     * @param <T>
     * @Title: treatOrderByKey
     * @Description: TODO(依据key值将map排序降序)
     * @param @param map
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型
     */
    public static <T> Map<String, T> treatOrderByKeyDescInStr(Map<String, T> map)
    {

        List<String> keyList = new ArrayList<String>();
        keyList.addAll(map.keySet());
        Collections.sort(keyList);
        Map<String, T> rsult = new LinkedMap();
        for (String key : keyList)
        {
            rsult.put(key, map.get(key));
        }
        return rsult;

    }
    
    /**
     * 
     * @param <T>
     * @Title: treatOrderByKey
     * @Description: TODO(依据key值将map排序降序)
     * @param @param map
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型
     */
    public static <T> Map<Float, T> treatOrderByKeyDescInFloat(Map<Float, T> map)
    {

        List<Float> keyList = new ArrayList<Float>();
        keyList.addAll(map.keySet());
        Collections.sort(keyList);
        Map<Float, T> rsult = new LinkedMap();
        for (Float key : keyList)
        {
            rsult.put(key, map.get(key));
        }
        return rsult;

    }
    
    /**
     * 
     * @param <T>
     * @Title: treatOrderByKey
     * @Description: TODO(依据key值将map排序升序)
     * @param @param map
     * @param @return 设定文件
     * @return Map<String,Object> 返回类型
     */
    public static <T> Map<String, T> treatOrderByKeyAsc(Map<String, T> map)
    {

        List<String> keyList = new ArrayList<String>();
        keyList.addAll(map.keySet());
        Collections.sort(keyList);
        Collections.reverse(keyList);//倒转
        Map<String, T> rsult = new LinkedMap();
        for (String key : keyList)
        {
            rsult.put(key, map.get(key));
        }
        return rsult;

    }

    /**
     * 
     * @Title: treatOrderByListTime 
     * @Description: TODO(根据map中的time字段排序) 
     * @param @param objsList
     * @param @return 设定文件 
     * @return List<Map<String,?>> 返回类型
     */
    public static List<Map<String,?>> treatOrderByListTime(List<Map<String,?>> objsList)
    {
        List<Map<String,?>> result = new ArrayList<Map<String,?>>();
        
        List<String> timeList = new ArrayList<String>();
        
        for (Map<String,?> map : objsList)
        {
            timeList.add(map.get("time").toString());
        }
        Collections.sort(timeList);
//        Collections.reverse(timeList);//倒转
        for (String time : timeList)
        {
            for (Map<String,?> map : objsList)
            {
                if (time.equals(map.get("time").toString())&&!result.contains(map))
                {
                    result.add(map);
                }
            }
        }
        return result;
    }
    
    
    /**
     * 
     * @Title: treatOrderByStr 
     * @Description: TODO(复制专用,将T改成该类) 
     * @param @param objslist
     * @param @param strList 目录集合
     * @param @return 设定文件 
     * @return List<T> 返回类型
     */
    public static <T> List<T> treatOrderByStr(List<T> objslist,List<String> strList)
    {
        List<T> result = new ArrayList<T>();
        for (String time : strList)
        {
            
            for (T t : objslist)
            {
                //复制后将t换为需比较的属性
                if (time.equals(t))
                {
                    result.add(t);
                }
            }
            
        }
        
        return result;
    }
}
