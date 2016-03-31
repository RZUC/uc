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
package test.com.zhiwei.manager.util;

import org.apache.solr.common.SolrDocument;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: SolrDocumentUtil
 * @author 落花流水
 * @date 2016年3月2日 下午2:10:07
 */
public class SolrDocumentToBeanUtilTest
{
    public static void main(String[] args)
    {
        // SolrDocument doc = new SolrDocument();
        // doc.put("pt", "网媒");
        // doc.put("title", "视频网站面临巨大利空 网剧将与电视台统一播放标准");
        // doc.put("source", "百度百家");
        // doc.put("_id", "http://weiwen.baijia.baidu.com/article/334447");
        // SolrDocumentToBeanUtil.SolrDocuemnt2Bean(doc, DataMedia.class);
        SolrDocument doc2 = new SolrDocument();
        doc2.put("isForward", 2);
        // System.out.println(SolrDocumentToBeanUtil.getDocumentObjectBinder().getBean(DataWeiBo.class, doc2));
        // SolrDocumentToBeanUtil.SolrDocuemnt2Bean(doc2, DataWeiBo.class);
    }
}
