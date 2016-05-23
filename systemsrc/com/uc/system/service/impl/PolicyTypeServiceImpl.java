package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.PolicyTypeDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Message;
import com.uc.system.model.PolicyType;
import com.uc.system.service.PolicyTypeService;

/**
 * @author Simple
 *
 */
@Component
public class PolicyTypeServiceImpl extends GeneralServiceImpl implements
		PolicyTypeService {

	@Resource
	private PolicyTypeDao policyTypeDao;

	@Override
	public Message addPolicyType(PolicyType policyType) {
		Message message = new Message();
		try {
			policyTypeDao.insert(policyType);
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
	public Message delPolicyType(String id) {
		// TODO:1.判断是否还有其他的数据在使用
		// TODO:2.
		Message message = new Message();

		try {
			message.setState(policyTypeDao.removeOneById(id));
			message.setMessage("删除成功");
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("删除失败，原因[" + e.getMessage() + "]");
		}
		return message;
	}

	@Override
	public Message modiyfPolicyType(PolicyType policyType) {
		Message message = new Message();
		try {
			message.setState(policyTypeDao.findAndModify(policyType));
			message.setMessage("修改成功");
		} catch (ZhiWeiException e) {
			message.setState(false);
			message.setMessage("修改失败，原因[" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<PolicyType> findAll() {
		List<PolicyType> list = new ArrayList<PolicyType>();
		try {
			list = policyTypeDao.findListWithLimitAndSkip(0, 0);
		} catch (ZhiWeiException e) {
			log.error("查询出错：{}", e.getMessage());
		}
		return list;
	}

}