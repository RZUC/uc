package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.solr.common.util.Hash;
import org.springframework.stereotype.Component;

import com.uc.system.dao.DepartmentDao;
import com.uc.system.dao.LocationDao;
import com.uc.system.dao.PolicyInfoDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Department;
import com.uc.system.model.Location;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;
import com.uc.system.model.view.PolicyInfoView;
import com.uc.system.service.PolicyService;
import com.uc.system.util.TimeUtil;

/**
 * @author Simple
 * 
 */
@Component
public class PolicyInfoServiceImpl extends GeneralServiceImpl implements
		PolicyService {

	@Resource
	private PolicyInfoDao policyInfoDao;

	@Resource
	private LocationDao locaiton;
	@Resource
	private DepartmentDao department;

	private Map<Integer, String> departmentMap;
	private static Map<Long, String> locationMap;

	private Map<Integer, String> getDepartmentMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			for (Department d : department.findAll()) {
				map.put(d.getId(), d.getName());
			}
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}
		return map;
	}

	private Map<Long, String> getLocationMap(List<PolicyInfo> list) {
		Map<Long, String> map = new HashMap<Long, String>();
		Set<Integer> set = new HashSet<Integer>();
		for (PolicyInfo p : list) {
			set.add(p.getCity());
			set.add(p.getProvince());
		}
		set.add(0);
		try {
			for (Location l : locaiton.findOneByFiled("_id", set)) {
				map.put(l.getId(), l.getLocationName());
			}
		} catch (ZhiWeiException e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public List<PolicyInfo> findList(Query query, Page page) {
		List<String> ids = null;
		List<PolicyInfo> list = new ArrayList<PolicyInfo>();
		list = policyInfoDao.findAllByIds(ids);
		return list;
	}

	@Override
	public PolicyInfo add(PolicyInfo info) {
		info.setCreateTime(TimeUtil.getCurrentTimeStr());
		info.setLastUpdateTime(TimeUtil.getCurrentTimeStr());
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
		info.setLastUpdateTime(TimeUtil.getCurrentTimeStr());
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

		locationMap = getLocationMap(list);
		if (departmentMap == null) {
			departmentMap = getDepartmentMap();
		}
		if (null != list && list.size() > 0) {
			for (PolicyInfo info : list) {
				view.add(new PolicyInfoView(info, departmentMap, locationMap));
			}
		}
		return view;
	}

	@Override
	public List<PolicyInfo> findList(int type, Page page) {
		List<PolicyInfo> list = policyInfoDao.findByType(type, page);
		return list;
	}

	@Override
	public PolicyInfo findById(String id) {
		try {
			PolicyInfo info = policyInfoDao.findOne(id);
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