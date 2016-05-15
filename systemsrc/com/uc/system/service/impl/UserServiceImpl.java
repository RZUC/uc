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
	public Message add(User user) {

		Message message = new Message();
		try {
			userDao.insert(user);
			message.setState(true);
			message.setMessage("添加成功");
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("添加失败，原因[" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Message del(String id) {
		// TODO:1.判断是否还有其他的数据在使用
		// TODO:2.
		Message message = new Message();

		try {
			message.setState(userDao.removeOneById(id));
			message.setMessage("删除成功");
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("删除失败，原因[" + e.getMessage() + "]");
		}
		return message;
	}

	@Override
	public Message modify(User user) {
		Message message = new Message();
		try {
			message.setState(userDao.findAndModify(user));
			message.setMessage("修改成功");
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("修改失败，原因[" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<User> findAllUsers(Query query, Page page) {
		List<User> list = new ArrayList<User>();
		try {
			if (null == page) {
				return list;
			}
			list = userDao.findListWithLimitAndSkip((page.getPageNum() - 1)
					* page.getPageSize(), page.getPageSize());
		} catch (ZhiWeiException e) {
			log.error("查询出错：{}", e.getMessage());
		}
		return list;
	}

}