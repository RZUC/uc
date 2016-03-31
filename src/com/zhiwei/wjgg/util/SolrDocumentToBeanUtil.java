/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月2日
    * @version 1.00 
*/
package com.zhiwei.wjgg.util;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SolrDocumentToBeanUtil
 * @author 落花流水
 * @date 2016年3月2日 上午10:04:40
 */
public class SolrDocumentToBeanUtil
{
    private final static DocumentObjectBinder binder = new DocumentObjectBinder();
    
    public static DocumentObjectBinder getDocumentObjectBinder()
    {
        return binder;
    }
    
}
