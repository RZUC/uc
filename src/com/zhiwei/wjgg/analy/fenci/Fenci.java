package com.zhiwei.wjgg.analy.fenci;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.SimpleSeg;
import com.chenlb.mmseg4j.Word;
import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.zhiwei.wjgg.conf.MyDic;
import com.zhiwei.wjgg.conf.StaticParam;
import com.zhiwei.wjgg.util.RenH_Util;
import com.zhiwei.wjgg.util.TreatOrder;

public class Fenci
{
    
    private static Logger logger = LoggerFactory.getLogger(Fenci.class);
    
    private static List<String> stopList; // 停用词集合
    
    // private static List<String> goodList; // 正面词集合
    //
    // private static List<String> badList; // 负面词集合
    
    public Fenci()
    {
        stopList = StopWords.getStopWords();
        // goodList = GoodWords.getGoodWords();
        // badList = BadWords.getBadWords();
    }
    
    /**
     * @deprecated:去除停用词的分词列表
     * @param List<String> fcList 需要被分词的数据集合
     * @return HashMap
     **/
    public Map<String, Integer> getFenCiWithStorp(List<String> dataList)
    {
        List<String> wordList = mmseg4j(dataList);
        
        Map<String, Integer> hash = statisticsWordList(wordList);
        
        return hash;
    }
    
    /**
     * @Title: statisticsWordList
     * @Description: 对数据分词
     * @param @param hash
     * @param @param wordList 设定文件
     * @return void 返回类型
     */
    private Map<String, Integer> statisticsWordList(List<String> wordList)
    {
        Map<String, Integer> map = new HashMap<String, Integer>(); // 分词总结果
        for (String key : wordList)
        {
            RenH_Util.plusKey(map, key);
        }
        
        List<Entry<String, Integer>> list = TreatOrder.treatOrderByCountDesc(map);
        int toIndex = list.size() > 200 ? 200 : list.size();
        list = list.subList(0, toIndex);
        Map<String, Integer> treeMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> ent : list)
        {
            treeMap.put(ent.getKey(), ent.getValue());
        }
        return treeMap;
    }
    
    /**
     * @Title: mmseg4j
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param dataList
     * @param @param hash 设定文件
     * @return void 返回类型
     */
    private List<String> mmseg4j(List<String> dataList)
    {
        Dictionary dic = Dictionary.getInstance(MyDic.DICPATH);
        Seg seg = new SimpleSeg(dic);
        List<String> wrodList = new ArrayList<String>();
        // 统计分词
        for (String txt : dataList)
        {
            MMSeg mmSeg = new MMSeg(new StringReader(txt), seg);
            Word word = null;
            try
            {
                while ((word = mmSeg.next()) != null)
                {
                    String words = word.toString();
                    if (!stopList.contains(words) && words.length() > 1)
                    {
                        wrodList.add(words);
                    }
                }
            }
            catch (IOException e)
            {
                logger.error("分词出现错误.....", e.getMessage());
                continue;
            }
        }
        return wrodList;
    }
    
}