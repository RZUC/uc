/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月28日
    * @version 1.00 
*/
package com.uc.system.conf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uc.system.util.RenH_Util;

/**
 * @Description: 用于自定义词库
 * @ClassName: MyDic
 * @author 落花流水
 * @date 2016年3月28日 上午11:36:01
 */
public class MyDic
{
    public final static Logger log = LoggerFactory.getLogger(MyDic.class);
    
    public static String FILENAME = "words-my.dic";
    
    public static String DICPATH =
        new MyDic().getClass().getResource("/").getFile().toString().split("classes")[0] + "dic";
    
    public static String FILEPATH = DICPATH + File.separator + FILENAME;
    
    /**
     * @Title: addWords
     * @Description: 添加词库
     * @param @param words
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws IOException
     */
    public static boolean addWords(List<String> words)
    {
        boolean flag = false;
        
        File file = getDicFile(FILEPATH);
        
        List<String> newWordList = getAddWords(words, getreadyWordList(file));
        
        flag = writerWords(file, newWordList);
        
        return flag;
    }
    
    /**
     * @Title: getreadyWordList
     * @Description: 根据文件获取关键词列表
     * @param @param file
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    private static List<String> getreadyWordList(File file)
    {
        
        try
        {
            List<String> list = RenH_Util.getWorksByFile(file);
            return list;
        }
        catch (Exception e)
        {
            log.error("获取已存在的分词列表出错：{}", e.getMessage());
        }
        
        return null;
    }
    
    /**
     * @Title: getWordListFile
     * @Description: 获取字典文件
     * @param @param filepath
     * @param @return 设定文件
     * @return File 返回类型
     */
    private static File getDicFile(String filepath)
    {
        File file = null;
        try
        {
            file = new File(filepath);
        }
        catch (Exception e)
        {
            log.error("获取字典文件出错：{}", e.getMessage());
        }
        return file;
    }
    
    /**
     * @Title: writerWords
     * @Description: 把新的关键词存入文件
     * @param @param file
     * @param @param newWordList
     * @param @return
     * @param @throws IOException 设定文件
     * @return BufferedWriter 返回类型
     */
    private static boolean writerWords(File file, List<String> newWordList)
    {
        boolean flag = false;
        BufferedWriter br3 = null;
        try
        {
            br3 = new BufferedWriter(new FileWriter(file, true));
            
            for (String word : newWordList)
            {
                br3.newLine();
                br3.write(word);
            }
            flag = true;
        }
        catch (Exception e)
        {
            log.error("写入数据出错：{}", e.getMessage());
        }
        finally
        {
            try
            {
                br3.close();
            }
            catch (IOException e)
            {
                log.error("关闭输出流出错：{}", e.getMessage());
            }
        }
        return flag;
    }
    
    /**
     * @Title: getAddWords
     * @Description: 通过比对，筛选出需要新增的关键词数据
     * @param @param words
     * @param @param list
     * @param @return 设定文件
     * @return List<String> 返回类型
     */
    private static List<String> getAddWords(List<String> words, List<String> list)
    {
        List<String> newWordList = new ArrayList<String>();
        for (String newWord : words)
        {
            if (!list.contains(newWord))
            {
                newWordList.add(newWord);
            }
        }
        return RenH_Util.quChong(newWordList);
    }
}
