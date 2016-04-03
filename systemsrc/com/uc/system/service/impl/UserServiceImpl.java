package com.uc.system.service.impl;

import javax.annotation.Resource;

import com.uc.system.dao.UserDao ;
import com.uc.system.service.UserService ;
/**
 * @author cwt
 * @date 2016-04-03
 */


public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

}