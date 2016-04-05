package com.uc.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uc.system.dao.UserDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.User;
import com.uc.system.service.UserService;

/**
 * @author cwt
 * @date 2016-04-03
 */

public class UserServiceImpl implements UserService
{
    public final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Resource
    private UserDao userDao;
    
    @Override
    public List<User> findAllUsers()
    {
        List<User> list = null;
        try
        {
            list = userDao.findAll();
        }
        catch (ZhiWeiException e)
        {
            log.error("查询所有用户出错");
        }
        
        return list;
    }
    
}