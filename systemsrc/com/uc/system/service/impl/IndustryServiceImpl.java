package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.IndustryDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Industry;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;
import com.uc.system.service.IndustryService;

/**
 * @author Simple
 *
 */
@Component
public class IndustryServiceImpl extends GeneralServiceImpl implements IndustryService {

	@Resource
	private IndustryDao dao;

    @Override
    public List<Industry> findList(Query query, Page page)
    {
        List<Industry> list = new ArrayList<Industry>();
        list = dao.findAll(query,page);
        return list;
    }

    @Override
    public Message add(Industry info)
    {   
        Message message = new Message();
        try
        {
            Industry result = dao.insert(info);
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
            message.setState(dao.removeOneById(id));
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
    public Message update(Industry info)
    {
        Message message = new Message();
        try
        {   
            dao.findAndModify(info);
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
}