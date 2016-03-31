/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年1月15日
    * @version 1.00 
*/
package com.zhiwei.wjgg.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.zhiwei.wjgg.dao.UserDao;
import com.zhiwei.wjgg.exception.ZhiWeiException;
import com.zhiwei.wjgg.model.User;
import com.zhiwei.wjgg.util.RenH_Util;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: UserDaoImpl
 * @author 落花流水
 * @date 2016年1月15日 下午1:51:45
 */
@Component
public class UserDaoImpl extends GeneralDaoImpl implements UserDao
{
    @Override
    public User insert(User user)
        throws ZhiWeiException
    {
        try
        {   
            user.setPassword(RenH_Util.MD5(user.getPassword()));
            mongoTemp.save(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return user;
    }
    
    @Override
    public User findOne(String id)
        throws ZhiWeiException
    {
        User user;
        try
        {
            user = mongoTemp.findOne(new Query(Criteria.where("_id").is(id)), User.class);
            return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public User findOne(User ob)
        throws ZhiWeiException
    {
        User user;
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(ob.getName()));
        query.addCriteria(Criteria.where("password").is(RenH_Util.MD5(ob.getPassword())));
        try
        {
            user = mongoTemp.findOne(query, User.class);
            return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    	 
    }
    
    @Override
    public List<User> findAll()
        throws ZhiWeiException
    {
    	List<User> userlist ;
        
        try
        {   
            userlist = mongoTemp.find(null, User.class);
            return userlist;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean removeOneById(String id)
        throws ZhiWeiException
    {
        
        try
        {   
            int result = mongoTemp.remove(new Query(Criteria.where("_id").is(id)), User.class).getN();
            if(1 == result){
				return true;
			}
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean removeAll()
        throws ZhiWeiException
    {
    	 try
         {   
    		 List<User> result =mongoTemp.findAllAndRemove(new Query(), User.class);
    		 if (0 != result.size()) {
    			 return true;
    		 }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         return false;
    }
    
    @Override
    public boolean findAndModify(User ob)
        throws ZhiWeiException
    {
        
        try
        {   
        	Update update = new Update();
        	update.addToSet("_id", ob.getId());
        	update.addToSet("name", ob.getName());
        	update.addToSet("password", ob.getPassword());
        	update.addToSet("permissionid", ob.getPermissionid());
        	mongoTemp.findAndModify(new Query(Criteria.where("_id").is(ob.getId())),update,User.class);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public long findCont() throws ZhiWeiException {
		// TODO Auto-generated method stub
		return 0;
	}
}
