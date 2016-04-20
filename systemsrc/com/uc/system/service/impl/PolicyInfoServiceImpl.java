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
public class PolicyInfoServiceImpl extends GeneralServiceImpl implements PolicyService {

	@Resource
	private PolicyInfoDao policyInfoDao;

    @Override
    public List<PolicyInfo> findList(Query query, Page page)
    {
        List<PolicyInfo> list = new ArrayList<PolicyInfo>();
        list = policyInfoDao.findAll(query,page);
        return list;
    }

    @Override
    public Message add(PolicyInfo info)
    {   
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
            log.error("添加政策信息失败：{}",e.getMessage());
        }
        return message;
    }

    @Override
    public Message del(String id)
    {
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
            log.error("删除政策信息失败：{}",e.getMessage());
        }
        return message;
    }

    @Override
    public Message update(PolicyInfo info)
    {
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
            log.error("删除政策信息失败：{}",e.getMessage());
        }
        return message;
    }

    @Override
    public Message top(String id)
    {
        Message message = new Message();
        try
        {   
            policyInfoDao.modifyTop(id,1);
            message.setMessage("更新成功");
            message.setState(true);
        }
        catch (Exception e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}",e.getMessage());
        }
        return message;
    }

    @Override
    public Message unTop(String id)
    {
        Message message = new Message();
        try
        {   
            policyInfoDao.modifyTop(id,0);
            message.setMessage("更新成功");
            message.setState(true);
        }
        catch (Exception e)
        {
            message.setMessage("添加失败");
            message.setState(false);
            log.error("删除政策信息失败：{}",e.getMessage());
        }
        return message;
    }

}