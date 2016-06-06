/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年2月27日
 * @version 1.00 
 */
package com.uc.system.solr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.uc.system.DBTemp.HttpSolrServerUtil;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.util.SolrTool;

/**
 * @ClassName: SolrPolicyInfoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2016年6月2日 下午9:32:07
 */
@Component
public class SolrPolicyInfoImpl extends CommonDao
{
    
    @Resource(name = "policySolor")
    HttpSolrServerUtil policySolr;
    
    @Override
    protected String getParam(List<String> wordList)
    {
        String param = SolrTool.processWord(wordList);
        return param;
    }
    
    @Override
    protected String getTimeType()
    {
        return TIMETYEP_STRING;
    }
    
    @Override
    protected HttpSolrServer getSolrServer()
    {
        return policySolr.getHttpSolrServer();
    }
    
    @Override
    protected String getFilterField()
    {
        return "_id,releaseTime,industry,department,title";
    }
    
    
    //高亮
    public HashMap<String, Object> getFromSolr(List<String> keywords, int page, String type, long start, long end,
        List<String> filter, String company)
    {
        
        if (keywords.get(0).contains("serach"))
        {
            return getSearchFromSolr(keywords, page, type, start, end, filter, company);
        }
        
        SolrQuery params = new SolrQuery();
        
        String content = "";
        
        for (int i = 0; i < keywords.size(); i++)
        {
            String keyword = keywords.get(i);
            content = content + "content:\"" + keyword + "\"";
            if (i != keywords.size() - 1)
            {
                content = content + " OR ";
            }
        }
        params.setQuery(content);
        params.setHighlight(true);
        params.addHighlightField("question_title,question_content,answer_content");
        params.setHighlightFragsize(300);
        params.setHighlightSnippets(1);
        params.setHighlightSimplePre("<font style='font-weight:bold;font-size:15px' color='#ff330D'>");// 设置开头
        params.setHighlightSimplePost("</font>");// 设置结尾
        params.setFilterQueries("rsid:[ " + start + " TO " + end + " ]");
        params.setSortField("rsid", ORDER.desc);
        params.setStart((page - 1) * 20);
        params.setRows(20);
        
        QueryResponse response = getQuery(params);
        
        SolrDocumentList sdl = response.getResults();// 查询结果集\
        
        Map<String, Map<String, List<String>>> highlight = response.getHighlighting(); // 获取高亮数据
        
        long numFound = sdl.getNumFound();
        
        SolrDocumentList idlist = assembleSolrDocumentWithHightLight(sdl, highlight);
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        
//        try
//        {
//            result.put("id", zhihuDao.PeggingMongo(idlist));
//        }
//        catch (ZhiWeiException e)
//        {
//            result.put("id", idlist);
//            log.error("知乎DAO反查Mongo出错：", idlist);
//        }
//        result.put("pages", Integer.valueOf(String.valueOf(numFound)));
//        result.put("es", end);
//        
        return result;
    }
    
    public int getUpdateFromSolr(String old_rsid, List<String> keywords, String type, List<String> filter)
        throws SolrServerException
    {
        SolrQuery params = new SolrQuery();
        String content = "";
        for (int i = 0; i < keywords.size(); i++)
        {
            String keyword = keywords.get(i);
            content = content + "content:\"" + keyword + "\"";
            if (i != keywords.size() - 1)
            {
                content = content + " OR ";
            }
        }
        
        params.setQuery(content);
        params.setStart(0);
        params.setRows(2000);
        params.setFilterQueries("rsid:[ " + old_rsid + " TO *]");
        params.setSortField("rsid", ORDER.desc);
        params.set("fl", "rsid");
        long numFound = getQuery(params).getResults().getNumFound();
        log.debug("查询结果：{}", numFound);
        if (numFound == 1)
        {
            numFound = numFound - 1;
        }
        
        return Integer.valueOf(String.valueOf(numFound));
    }
    
    public String getStartAndEnd(List<String> keywords, String type, List<String> filter)
    {
        SolrQuery params = new SolrQuery();
        String content = "";
        for (int i = 0; i < keywords.size(); i++)
        {
            String keyword = keywords.get(i);
            content = content + "content:\"" + keyword + "\"";
            if (i != keywords.size() - 1)
            {
                content = content + " OR ";
            }
        }
        
        params.setQuery(content);
        params.setStart(0);
        params.setRows(2000);
        params.set("fl", "rsid");
        params.setSortField("rsid", ORDER.desc);
        SolrDocumentList sdl = getQuery(params).getResults();
        String typeStartAndEnd = type + "," + String.valueOf(sdl.get(sdl.size() - 1).get("rsid")) + ","
            + String.valueOf(sdl.get(0).get("rsid"));
        
        return typeStartAndEnd;
    }
    
    private QueryResponse getQuery(SolrQuery params)
    {
//        try
//        {
////            QueryResponse response = solrzhihu.query(params, METHOD.POST);
//            return response;
//        }
//        catch (SolrServerException e)
//        {
//            e.printStackTrace();
//        }
        return null;
    }
    
    // 组织高亮数据
    private SolrDocumentList assembleSolrDocumentWithHightLight(SolrDocumentList sdl,
        Map<String, Map<String, List<String>>> highlight)
    {
        for (SolrDocument solrDocument : sdl)
        {
            String questionTitle = "";
            String questionContent = "";
            String answerContent = "";
            String img = "";
            
            String content1 =
                solrDocument.get("question_content") == null ? "" : solrDocument.get("question_content").toString();
            String content2 =
                solrDocument.get("answer_content") == null ? "" : solrDocument.get("answer_content").toString();
            log.debug("***********原始内容***********\nQ:{}\nA:{}", content1, content2);
            
            if (!"".equals(content1))
            {
                img = regxString(content1);
                
            }
            
            if (!"".equals(content2))
            {
                img = regxString(content2);
            }
            try
            {
                List<String> titlelist = highlight.get(solrDocument.get("_id")).get("question_title");
                List<String> questionContentlist = highlight.get(solrDocument.get("_id")).get("question_content");
                List<String> answerContentList = highlight.get(solrDocument.get("_id")).get("answer_content");
                
                // if (titlelist == null && questionContentlist == null && null == answerContentList)
                // {
                // continue;
                // }
                
                if (titlelist != null && titlelist.size() > 0)
                {
                    for (String string : titlelist)
                    {
                        questionTitle = questionTitle + string;
                    }
                }
                
                if (questionContentlist != null && questionContentlist.size() > 0)
                {
                    for (String string : questionContentlist)
                    {
                        questionContent = questionContent + string;
                    }
                }
                
                if (answerContentList != null && answerContentList.size() > 0)
                {
                    for (String string : answerContentList)
                    {
                        answerContent = answerContent + string;
                    }
                }
                
                if (questionTitle.isEmpty())
                {
                    questionTitle = String.valueOf(solrDocument.get("question_title"));
                    if (questionTitle.length() > 300)
                    {
                        questionTitle = questionTitle.substring(0, 300);
                    }
                }
                
                if (questionContent.isEmpty())
                {
                    questionContent = String.valueOf(solrDocument.get("question_content"));
                    
                    if (questionContent.length() > 300)
                    {
                        questionContent = questionContent.substring(0, 300);
                    }
                }
                
                if (answerContent.isEmpty())
                {
                    answerContent = String.valueOf(solrDocument.get("answer_content"));
                    if (answerContent.length() > 300)
                    {
                        answerContent = answerContent.substring(0, 300);
                    }
                }
                
                solrDocument.put("question_title", questionTitle.replaceAll("<img[^>]*>", ""));
                solrDocument.put("question_content", questionContent.replaceAll("<img[^>]*>", ""));
                solrDocument.put("answer_content", answerContent.replaceAll("<img[^>]*>", ""));
                solrDocument.put("img", img);
                
                if (solrDocument.containsKey("img_url"))
                {
                    String imgUrl = solrDocument.get("img_url").toString();
                    
                    if (null != imgUrl && "".equals(imgUrl))
                    {
                        log.debug("图片地址:{}", imgUrl);
                        solrDocument.put("img", imgUrl);
                    }
                }
                
                log.debug("***********高亮内容***********\nT:{}\nQ:{}\nA:{}",
                    new Object[] {solrDocument.get("question_title").toString(),
                        solrDocument.get("question_content").toString(),
                        solrDocument.get("answer_content").toString()});
                log.debug("***********这条结束***********");
            }
            catch (Exception e)
            {
                log.error("匹配高亮出错：{}", solrDocument);
            }
            
        }
        return sdl;
    }
    
    private HashMap<String, Object> getSearchFromSolr(List<String> keywords, int page, String type, long start,
        long end, List<String> filter, String company)
    {
        String title = "";
        String question = "";
        String answer = "";
        String searchkey = "";
        
        String keyword = keywords.get(0);
        keyword = keyword.replaceAll("serach---", "");
        String words[] = keyword.split(" ");
        
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            title = title + "content:\"" + word + "\"";
            // question = question + "question_content:\"" + word + "\"";
            // answer = answer + "answer_count:\"" + word + "\"";
            
            if (i != words.length - 1)
            {
                title = title + " AND ";
                // question = question + " AND ";
                // answer = answer + " AND ";
            }
        }
        
        // searchkey = "(" + title + ") OR (" + question + ") OR (" + answer + ")";
        SolrQuery params = new SolrQuery();
        params.setQuery(title);
        params.addHighlightField("question_title,question_content,answer_content");
        params.setHighlightFragsize(300);
        params.setHighlightSnippets(1);
        params.setHighlightSimplePre("<font style='font-weight:bold;font-size:15px' color='#ff330D'>");// 设置开头
        params.setHighlightSimplePost("</font>");// 设置结尾
        params.setSortField("insert_at", ORDER.desc);
        params.setStart((page - 1) * 20);
        params.setRows(20);
        
        QueryResponse response = getQuery(params);
        
        SolrDocumentList sdl = response.getResults();// 查询结果集\
        Map<String, Map<String, List<String>>> highlight = response.getHighlighting(); // 获取高亮数据
        long numFound = sdl.getNumFound();
        
        SolrDocumentList idlist = assembleSolrDocumentWithHightLight(sdl, highlight);
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        
//        try
//        {
//            result.put("id", zhihuDao.PeggingMongo(idlist));
//        }
//        catch (ZhiWeiException e)
//        {
//            result.put("id", idlist);
//            log.error("知乎DAO反查Mongo出错：", idlist);
//        }
        result.put("pages", Integer.valueOf(String.valueOf(numFound)));
        result.put("es", end);
        
        return result;
    }
    
    /**
     * 找出内容中含有的图片
     * 
     * @Title: regxString
     * @param @param str
     * @return String 返回类型
     */
    private String regxString(String str)
    {
        Pattern p = Pattern.compile("[http]{4}[:0-9a-zA-Z_/.]+.png");
        Matcher m = p.matcher(str);
        while (m.find())
        {
            return m.group();
        }
        return "";
    }
}
