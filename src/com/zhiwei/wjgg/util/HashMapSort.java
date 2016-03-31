package com.zhiwei.wjgg.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @Deprecated 计算HashMap数据的排序
 * @author ZZW
 * @创建时间 2014-05-04
 * */
public class HashMapSort{
	
	
	/**
	 * @deprecated:统计微博数据根据粉丝排序
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List ComparatorFromWeibo(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int fensi1=Integer.parseInt(o1.get("fensi").toString());
				int fensi2=Integer.parseInt(o2.get("fensi").toString());
				return fensi2-fensi1;
			}
		});
		return list;
	}
	
	
	/**
	 * @deprecated:统计微信数据根据平均阅读量排序
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List ComparatorFromWeiXin(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int fensi1=Integer.parseInt(o1.get("H").toString());
				int fensi2=Integer.parseInt(o2.get("H").toString());
				return fensi2-fensi1;
			}
		});
		return list;
	}
	
	/**
	 * @deprecated:统计百度数据根据aleax排序
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List ComparatorFromBaidu(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int fensi1=Integer.parseInt(o1.get("H").toString());
				int fensi2=Integer.parseInt(o2.get("H").toString());
				return fensi2-fensi1;
			}
		});
		return list;
	}
	
	
	//计算每小时按降序排列
	@SuppressWarnings("unchecked")
	public List ComparatorDateDESC(List list){
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				int time1 = 0;
				int time2 = 0;
				try {
					time1 = Long.valueOf(sf.parse((String) o1.get("time")).getTime()).intValue();
					time2=Long.valueOf(sf.parse((String) o2.get("time")).getTime()).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return time2-time1;
			}
		});
		return list;
	}
	
	//按时间升序排序
	@SuppressWarnings("unchecked")
	public List ComparatorDateASC(List list){
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				int time1 = 0;
				int time2 = 0;
				try {
					time1 = Long.valueOf(sf.parse((String) o1.get("time")).getTime()).intValue();
					time2=Long.valueOf(sf.parse((String) o2.get("time")).getTime()).intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return time1-time2;
			}
		});
		return list;
	}
	
	/**
	 * @deprecated:根据标题出现的次数排序
	 * @param HashMap<String,Integer> titleMap
	 * 					标题集合数据（百度新闻）
	 * @return List
	 * ***/
	@SuppressWarnings({ "rawtypes" })
	public static List<Entry<String,Integer>> ComparatorMapDesc(HashMap<String,Integer> titlelmap) {
		Map<String,Integer> map = titlelmap;
		List<Map.Entry<String, Integer>> list= new ArrayList<Map.Entry<String, Integer>>();
		list.addAll(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		});
		return list;
	}
	
	//时间曲线升序
	@SuppressWarnings("unchecked")
	public static List<Map.Entry<String, Integer>> ComparatorMapASC(
			HashMap<String, Integer> addTimeLine) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				addTimeLine.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
				int time1 = 0;
				int time2 = 0;
				try {
					time1 = Long.valueOf(
							sf.parse( o1.getKey()).getTime())
							.intValue();
					time2 = Long.valueOf(
							sf.parse( o2.getKey()).getTime())
							.intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return time1 - time2;
			}
		});
		return list;
	}
	
	
	//时间曲线降序
	@SuppressWarnings("unchecked")
	public static List<Map.Entry<String, Integer>> ComparatorMapDESC(
							HashMap<String, Integer> addTimeLine) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				addTimeLine.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				int time1 = 0;
				int time2 = 0;
				try {
					time1 = Long.valueOf(
							sf.parse((String) o1.getKey()).getTime())
							.intValue();
					time2 = Long.valueOf(
							sf.parse((String) o2.getKey()).getTime())
							.intValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return time2 - time1;
			}
		});
		return list;
	}
	
	
	
	/**
	 * @deprecated:计算男女比例
	 * @param HashMap<String,Integer> sexMap
	 * 					数据值
	 * @return HashMap
	 * **/
	@SuppressWarnings("unchecked")
	public static HashMap getSexBili(HashMap<String,Integer> sexMap)
	{
		double count = 0;
		double fCount = 0;
		double mCount = 0;
		
		HashMap map = new HashMap();
		for(Entry<String,Integer> entry : sexMap.entrySet())
		{
			if(entry.getKey().equals("m"))
			{
				mCount = Double.valueOf(entry.getValue());
			}else if(entry.getKey().equals("f"))
			{
				fCount = Double.valueOf(entry.getValue());
			}
		}
		count = mCount + fCount;
		double m = mCount/count;
		double f = fCount/count;
		//精确到小数点后两位
		double male =  new BigDecimal(m*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double female = new BigDecimal(f*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		map.put("male", male);
		map.put("female", female);
		return map;
	}
	
	
	
	/**
	 * @deprecated:计算地域比例
	 * @param HashMap<String,Integer> locationMap
	 * 					数据值
	 * @return HashMap
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getLocationBili(HashMap<String,Integer> locationMap)
	{
		HashMap areaMap = new HashMap();
		List list= HashMapSort.ComparatorMapDesc(locationMap); //对地域降序排列
		List nameList=new ArrayList();
		List valueList=new ArrayList();
		double total=0;
		//计算总量
		for(int i=0;i<list.size();i++)
		{
			Map.Entry<String, Integer> mMap = (Entry<String, Integer>) list.get(i);
			total += mMap.getValue();
		}
		
		//计算地域比例TOP10
		if(list.size()>=10){
			for(int i=0;i<10;i++){
				Map.Entry<String, Integer> mMap=(Entry<String, Integer>) list.get(i);
				String location = mMap.getKey();
				double key=mMap.getValue();
				double value=new BigDecimal((key/(total)*100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				nameList.add(location);
				valueList.add(value);
			}
			
			
		}else{
			for(int i=0;i<list.size();i++){
				Map.Entry<String, Integer> mMap=(Entry<String, Integer>) list.get(i);
				String location = mMap.getKey();
				double key=mMap.getValue();
				double value=new BigDecimal((key/(total)*100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				nameList.add(location);
				valueList.add(value);
			}
		}
		
		areaMap.put("name", nameList);//地域名称
		areaMap.put("value", valueList);//值
		
		return areaMap;
	}
	
	
	
	
	
	/**
	 * @deprecated:根据标题出现的次数排序
	 * @param HashMap<String,Integer> titleMap
	 * 					标题集合数据（百度新闻）
	 * @return List
	 * ***/
	@SuppressWarnings({ "rawtypes" })
	public static List<Entry<HashMap,Integer>> ComparatorInfulentAsc(Map<HashMap, Integer> infMap) {
		Map<HashMap,Integer> map = infMap;
		List<Map.Entry<HashMap, Integer>> list= new ArrayList<Map.Entry<HashMap, Integer>>();
		list.addAll(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<HashMap, Integer>>() {
			@Override
			public int compare(Entry<HashMap, Integer> o1,
					Entry<HashMap, Integer> o2) {
				return o1.getValue()-o2.getValue();
			}
		});
		return list;
	}
	
	
	
	/**
	 * @deprecated:统计百度数据根据aleax排序
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getCompareEvent(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int effect1 = Integer.parseInt(o1.get("index").toString());
				int effect2 = Integer.parseInt(o2.get("index").toString());
				return effect2 - effect1;
			}
		});
		return list;
	}
}
