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
package com.zhiwei.wjgg.service;

import org.springframework.stereotype.Service;

import com.zhiwei.wjgg.model.User;

/** 
 * @Description: TODO(登录操作)  
 * @ClassName: LoginService 
 * @author 落花流水 
 * @date 2016年1月19日 下午2:17:34  
 */
@Service
public interface LoginService
{
    /** 
     * @Title: login 
     * @Description: TODO(验证用户登录) 
     * @param @param name
     * @param @param password
     * @param @return 设定文件  登录成功返回用户，否则返回空
     * @return boolean 返回类型 
     */ 
    public User login(String name,String password);
    
}
