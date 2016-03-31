/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月19日
    * @version 1.00 
*/
package com.zhiwei.wjgg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mongodb.DBCollection;
import com.zhiwei.wjgg.dao.PermissionDao;
import com.zhiwei.wjgg.dao.UserDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.Permission;
import com.zhiwei.wjgg.model.User;
import com.zhiwei.wjgg.service.LoginService;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: LoginServiceImpl
 * @author 落花流水
 * @date 2016年1月19日 下午2:38:36
 */
@Service
public class LoginServiceImpl implements LoginService
{
    @Resource
    private UserDao dao;
    @Resource
    PermissionDao permissionDao;
    
    @Override
    public User login(String name, String password)
    {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        try
        {
            user = dao.findOne(user);
            if (null != user && null != user.getPermissionid() && !"".equals(user.getPermissionid()))
            {
                Permission p = permissionDao.findOne(user.getPermissionid());
                if (null != p)
                {
                    user.setPermission(p);
                }
            }
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    
}
