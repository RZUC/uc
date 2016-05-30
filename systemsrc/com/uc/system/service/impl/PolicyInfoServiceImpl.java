package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.uc.system.dao.PolicyInfoDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.PolicyInfoView;
import com.uc.system.model.Query;
import com.uc.system.service.PolicyService;

/**
 * @author Simple
 * 
 */
@Component
public class PolicyInfoServiceImpl extends GeneralServiceImpl implements
		PolicyService {

	@Resource
	private PolicyInfoDao policyInfoDao;

	private Map<String, String> departmentMap = getDepartmentMap();

	@Override
	public List<PolicyInfo> findList(Query query, Page page) {
		List<String> ids = null;
		List<PolicyInfo> list = new ArrayList<PolicyInfo>();
		list = policyInfoDao.findAllByIds(ids);
		return list;
	}

	@Override
	public PolicyInfo add(PolicyInfo info) {

		Message message = new Message();
		try {
			PolicyInfo result = policyInfoDao.insert(info);
			message.setMessage("添加成功");
			return result;
		} catch (ZhiWeiException e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("添加政策信息失败：{}", e.getMessage());
		}
		return null;
	}

	@Override
	public Message del(String id) {
		// TODO:删除的时候同时删除Solrz中的数据
		Message message = new Message();
		try {
			message.setMessage("添加成功");
			message.setState(policyInfoDao.removeOneById(id));
		} catch (ZhiWeiException e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("删除政策信息失败：{}", e.getMessage());
		}
		return message;
	}

	@Override
	public Message update(PolicyInfo info) {
		// TODO:更新的时候更新Solr
		Message message = new Message();
		try {
			policyInfoDao.findAndModify(info);
			message.setMessage("更新成功");
			message.setState(true);
		} catch (ZhiWeiException e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("删除政策信息失败：{}", e.getMessage());
		}
		return message;
	}

	@Override
	public Message top(String id) {
		Message message = new Message();
		try {
			policyInfoDao.modifyTop(id, 1);
			message.setMessage("更新成功");
			message.setState(true);
		} catch (Exception e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("删除政策信息失败：{}", e.getMessage());
		}
		return message;
	}

	@Override
	public Message unTop(String id) {
		Message message = new Message();
		try {
			policyInfoDao.modifyTop(id, 0);
			message.setMessage("更新成功");
			message.setState(true);
		} catch (Exception e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("删除政策信息失败：{}", e.getMessage());
		}
		return message;
	}

	@Override
	public List<PolicyInfo> findByContent(Query query, Page page) {

		return null;
	}

	@Override
	public List<PolicyInfo> findListByTop(int top) {
		List<PolicyInfo> list = policyInfoDao.findTop(10);
		return list;
	}

	@Override
	public List<PolicyInfoView> getViewList(List<PolicyInfo> list) {
		List<PolicyInfoView> view = new ArrayList<PolicyInfoView>();
		if (null != list && list.size() > 0) {
			for (PolicyInfo info : list) {
				view.add(new PolicyInfoView(info, departmentMap));
			}
		}

		return view;
	}

	private Map<String, String> getDepartmentMap() {
		Map<String, String> map = new HashMap<String, String>();
		Mongo m = new Mongo();

		List<DBObject> list = m.getDB("uc").getCollection("department").find()
				.toArray();
		for (DBObject obj : list) {
			map.put(obj.get("_id").toString(), obj.get("name").toString());
		}
		return map;
	}

	@Override
	public List<PolicyInfo> findList(int type, Page page) {
		List<PolicyInfo> list = policyInfoDao.findByType(type, page);
		return list;
	}

	@Override
	public PolicyInfo findById(String id) {
		try {
			PolicyInfo info  = policyInfoDao.findOne(id);
			return info;
		} catch (ZhiWeiException e) {
		}
		return null;
	}

	@Override
	public int getTotalCount(int type, Page page) {
		int total = policyInfoDao.getTotalCount(type, page);
		return total;
	}
}