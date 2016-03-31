package com.zhiwei.uc.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @Deprecated 计算HashMap数据的排序
 * @author ZZW
 * @创建时间 2014-05-04
 * */
public class TreatOrder{
	
	
	/**
	 * @deprecated:统计微博数据根据粉丝排序
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "rawtypes" })
	public static List treatOrderByFensi(List<HashMap> list) {
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
	 * @deprecated:根据H因子降序排列
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "rawtypes" })
	public static List treatOrderByH(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int h1 = Integer.parseInt(o1.get("H").toString());
				int h2 = Integer.parseInt(o2.get("H").toString());
				return h2 - h1;
			}
		});
		return list;
	}
	
	/**
	 * @deprecated:根据平均阅读数降序排列
	 * @param List<HashMap> list
	 * 				需要排序的集合
	 * @reutrn List<HashMap>
	 * **/
	@SuppressWarnings({ "rawtypes" })
	public static List treatOrderByRedNum(List<HashMap> list) {
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				int h1 = Integer.parseInt(o1.get("avg_read").toString());
				int h2 = Integer.parseInt(o2.get("avg_read").toString());
				return h2 - h1;
			}
		});
		return list;
	}
	
	/**
	 * @deprecated:按照时间升序排列
	 * 							时间格式为yyyy-MM-dd HH:mm
	 * @param List<HashMap> list
	 * 							要排序的事件
	 * @return list
	 * 
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List treatOrderByDateAsc(List list){
		Collections.sort(list, new Comparator<HashMap>() {
			public int compare(HashMap o1, HashMap o2) {
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				int time1 = 0;
				int time2 = 0;
				try {
					time1 = Long.valueOf(sf.parse(o1.get("time").toString()).getTime()) .intValue();
					time2 = Long.valueOf(sf.parse( o2.get("time").toString()).getTime()).intValue();
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
				return time1-time2;
			}
		});
		return list;
	}
	
	/**
	 * @deprecated:按照时间升序排列
	 * 							时间格式为yyyy-MM-dd HH
	 * @param HashMap<String, Integer> timeLine
	 * 							要排序的事件
	 * @return list
	 * 
	 * **/
	@SuppressWarnings("unchecked")
	public static List<Map.Entry<String, Integer>> treatOrderByDateAsc(
			HashMap<String, Integer> timeLine) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				timeLine.entrySet());

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
	
	/**
	 * @deprecated:按照时间降序排列
	 * 							时间格式为yyyy-MM-dd
	 * @param HashMap<String, Integer> timeLine
	 * 							要排序的事件
	 * @return list
	 * 
	 * **/
	@SuppressWarnings("unchecked")
	public static List<Map.Entry<String, Integer>> treatOrderByDateDesc(
								HashMap<String, Integer> timeLine) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				timeLine.entrySet());

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
	 * @deprecated:按照数量降序排序
	 * @param HashMap<String,Integer> dataMap
	 * 								数据集合
	 * @return List
	 * ***/
	@SuppressWarnings({ "rawtypes" })
	public static List<Entry<String,Integer>> treatOrderByCountDesc(Map<String,Integer> dataMap) {
		Map<String,Integer> map = dataMap;
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
	
}
