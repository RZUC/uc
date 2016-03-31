//package com.zhiwei.wjgg.dao.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.solr.common.SolrInputDocument;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.mongodb.DBObject;
//import com.zhiwei.wjgg.DBTemp.HttpSolrServerUtil;
//import com.zhiwei.wjgg.dao.BaiDuDao;
//import com.zhiwei.wjgg.dao.SeqDao;
//
///**
// * @Package com.zhiwei.event.dao.impl
// * @ClassName: BaiDuDaoImpl
// * @Description: TODO(将百度新闻采集回来的数据存入数据库实现类)
// * @author 志伟
// * @date 2015-5-27 上午10:25:28
// */
//public class BaiDuDaoImpl extends GeneralDaoImpl implements BaiDuDao
//{
//    
//    @Resource
//    SeqDao seqDao;
//    
//    private String collName = "mediadata";
//    
//    @Resource(name = "eventSolr")
//    HttpSolrServerUtil mediaEventSolr;
//    
//    private static Logger logger = LoggerFactory.getLogger(BaiDuDaoImpl.class); // 日志文件
//    
//    @Override
//    public void addBaiduDataToMongo(List<DBObject> datalist)
//    {
//        for (DBObject doc : datalist)
//        {
//            for (int i = 1; i <= 3; i++)
//            {
//                try
//                {
//                    doc.put("rsid", Long.valueOf(seqDao.getNextId("mc")));
//                    mongoTempWeixinMedia.save(doc, collName);
//                    // System.out.println("Info is ins!" + "\t"+ wr.getError());
//                    addBaiduDataToSolr(doc);
//                    break;
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                    continue;
//                }
//            }
//        }
//    }
//    
//    // 向solr中添加数据
//    @Override
//    public void addBaiduDataToSolr(DBObject doc)
//    {
//        String content = "";
//        String title = "";
//        if (doc.get("content") != null)
//        {
//            content = doc.get("content").toString();
//        }
//        if (doc.get("title") != null)
//        {
//            title = doc.get("title").toString();
//        }
//        SolrInputDocument docs = new SolrInputDocument();
//        docs.addField("id", doc.get("_id"));
//        docs.addField("title", title);
//        docs.addField("time", (Date)doc.get("time"));
//        docs.addField("source", doc.get("source"));
//        docs.addField("type", doc.get("type"));
//        docs.addField("content", content);
//        docs.addField("savetime", (Long.valueOf(doc.get("savetime").toString())));
//        docs.addField("rsid", Long.valueOf(doc.get("rsid").toString()));
//        docs.addField("pt", doc.get("pt"));
//        try
//        {
//            mediaEventSolr.getHttpSolrServer().add(docs);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            // System.out.println("Info is ins!");
//        }
//        
//    }
//    
//}
