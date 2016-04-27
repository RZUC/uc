package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.PolicyInfoDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;
import com.uc.system.service.PolicyService;

/**
 * @author Simple
 *        
 */
@Component
public class PolicyInfoServiceImpl extends GeneralServiceImpl implements PolicyService
{
    
    @Resource
    private PolicyInfoDao policyInfoDao;
    
    @Override
    public List<PolicyInfo> findList(Query query, Page page)
    {   
     // TODO:1.通过Solr查询数据，2.通过返回的ID，反查Mongo中数据库的内容
        List<String> ids = null;
        List<PolicyInfo> list = new ArrayList<PolicyInfo>();
        list = policyInfoDao.findAllByIds(ids);
        return list;
    }
    
    @Override
    public Message add(PolicyInfo info)
    {
        // TODO:添加数据的时候同时添加到Solr添加全部
        
        Message message = new Message();
        try
        {
            PolicyInfo result = policyInfoDao.insert(info);
            message.setMessage("添加成功");
            message.setState(true);
        }
        catch (ZhiWeiException e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("添加政策信息失败：{}", e.getMessage());
        }
        return message;
    }
    
    @Override
    public Message del(String id)
    {   
     // TODO:删除的时候同时删除Solrz中的数据
        Message message = new Message();
        try
        {
            message.setMessage("添加成功");
            message.setState(policyInfoDao.removeOneById(id));
        }
        catch (ZhiWeiException e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}", e.getMessage());
        }
        return message;
    }
    
    @Override
    public Message update(PolicyInfo info)
    {   
     // TODO:更新的时候更新Solr
        Message message = new Message();
        try
        {
            policyInfoDao.findAndModify(info);
            message.setMessage("更新成功");
            message.setState(true);
        }
        catch (ZhiWeiException e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}", e.getMessage());
        }
        return message;
    }
    
    @Override
    public Message top(String id)
    {   
        Message message = new Message();
        try
        {
            policyInfoDao.modifyTop(id, 1);
            message.setMessage("更新成功");
            message.setState(true);
        }
        catch (Exception e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}", e.getMessage());
        }
        return message;
    }
    
    @Override
    public Message unTop(String id)
    {
        Message message = new Message();
        try
        {
            policyInfoDao.modifyTop(id, 0);
            message.setMessage("更新成功");
            message.setState(true);
        }
        catch (Exception e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}", e.getMessage());
        }
        return message;
    }
    
}