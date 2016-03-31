package com.zhiwei.uc.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiwei.uc.system.dao.LogDao;
import com.zhiwei.uc.system.exception.ZhiWeiException;
import com.zhiwei.uc.system.model.Log;
import com.zhiwei.uc.system.service.LogService;

/**
 * @author 落花流水
 * @date 2016-02-23
 */
@Service
public class LogServiceImpl implements LogService
{
    @Resource
    private LogDao logDao;
    
    @Override
    public void addLog(String type,String content)
    {   
        Log log = new Log(type,content);
        try
        {
            logDao.addLog(log);
        }
        catch (ZhiWeiException e)
        {
            
        }
    }
    
}