package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uc.system.dao.UserDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.Query;
import com.uc.system.model.User;
import com.uc.system.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	public final static Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao userDao;

	@Override
	public User add(User user) {

		try {
			user = userDao.insert(user);
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Message del(String id) {
		// TODO:1.判断是否还有其他的数据在使用
		Message message = new Message();
		message.setState(false);
		message.setMessage("删除失败");
		try {
			if (userDao.removeOneById(id)) {
				message.setState(true);
				message.setMessage("删除成功");
			}
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("删除失败，原因[" + e.getMessage() + "]");
		}
		return message;
	}

	@Override
	public User modify(User user) {
		 try {
			if(userDao.findAndModify(user)){
				 return null;
			 }
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findAllUsers(Query query, Page page) {
		List<User> list = new ArrayList<User>();
		try {
			if (null == page) {
				return userDao.findAll();
			}
			list = userDao.findListWithLimitAndSkip((page.getPageNum() - 1)
					* page.getPageSize(), page.getPageSize());
		} catch (ZhiWeiException e) {
			log.error("查询出错：{}", e.getMessage());
		}
		return list;
	}

	@Override
	public User findByUid(String uid) {
		try {
			User user = userDao.findOne(uid);
			return user;
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByusernameAndPassword(String username, String password) {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		try {
			user = userDao.findOne(user);
		} catch (ZhiWeiException e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User register(User user) {
		if (null != findByusernameAndPassword(user.getName(), null)) {
			return null;
		}
		try {
			user = userDao.insert(user);
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int totalCount() {
		try {
			return userDao.findAll().size();
		} catch (ZhiWeiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}