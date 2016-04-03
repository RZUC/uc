package com.uc.system.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzw
 * @version 创建时间：2014-2-28 下午04:02:09 类说明 分割关键字
 */
public class WordsCount
{
    
    public static List<String> wordsCount(String word)
    {
        word = word.replaceAll("（", "(").replaceAll("）", ")").replaceAll(" ", "+");
        String a = word.trim();
        String b = a.replace("+", " + ");
        String c = b.replace("|", " | ");
        String d = c.replace("(", " ( ");
        String f = d.replace(")", " ) ");
        String g = f.replace("{", " { ");
        String h = g.replace("}", " } ");
        // 关键词组中的关键词
        List<String> keywordList = new ArrayList<String>();
        String[] key = h.split(" ");
        for (int i = 0; i < key.length; i++)
        {
            String key1 = key[i].trim();
            if (!"".equals(key1))
            {
                keywordList.add(key1);
            }
        }
        return keywordList;
    }
    
    /**
     * @Title: getWordListNOsymbol
     * @Description: 提取所有的关键字
     * @param @param word
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    public static List<String> getWordListNOsymbol(String word)
    {
        word = word.trim().replaceAll("[（）+|(){}]", " ");
        List<String> list = new ArrayList<String>();
        for (String key : word.split(" "))
        {
            if (!"".equals(key))
            {
                list.add(key);
            }
        }
        return list;
    }
    
    public static void main(String[] args)
    {
        
        List<String> list = getWordListNOsymbol("(微信+提现+收费)|(微信+提现+手续费)");
        for (String word : list)
        {
            System.out.println(word);
        }
    }
}
