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
package com.zhiwei.wjgg.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.conf.MyDic;
import com.zhiwei.wjgg.model.Event;
import com.zhiwei.wjgg.service.DicService;
import com.zhiwei.wjgg.util.WordsCount;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: DicServiceImpl
 * @author 落花流水
 * @date 2016年3月28日 下午2:43:23
 */
@Component
public class DicEvetnServiceImpl implements DicService
{
    
    @Override
    public boolean addWrodList(String str)
    {
        List<String> wordList = WordsCount.getWordListNOsymbol(str);
        return MyDic.addWords(wordList);
    }
    
}
