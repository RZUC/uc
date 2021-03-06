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
	public PolicyType addPolicyType(PolicyType policyType) {
		try {
			policyType = policyTypeDao.insert(policyType);
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return policyType;
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
	public PolicyType modiyfPolicyType(PolicyType policyType) {

		try {
			policyTypeDao.findAndModify(policyType);
		} catch (ZhiWeiException e) {
			try {
				policyType = policyTypeDao.findOne(policyType);
			} catch (ZhiWeiException e1) {
				e1.printStackTrace();
			}
		}
		return policyType;
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