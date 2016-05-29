package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.IndustryDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Industry;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.Query;
import com.uc.system.service.IndustryService;

/**
 * @author Simple
 *
 */
@Component
public class IndustryServiceImpl extends GeneralServiceImpl implements
		IndustryService {

	@Resource
	private IndustryDao dao;

	@Override
	public List<Industry> findList(Query query, Page page) {
		List<Industry> list = new ArrayList<Industry>();
		// list = dao.findAll(query, page);
		return list;
	}

	@Override
	public Industry add(Industry info) {
		Message message = new Message();
		Industry result = null;
		try {
			result = dao.insert(info);
		} catch (ZhiWeiException e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("添加政策信息失败：{}", e.getMessage());
		}
		return result;
	}

	@Override
	public Message del(String id) {
		Message message = new Message();
		try {
			message.setMessage("添加成功");
			message.setState(dao.removeOneById(id));
		} catch (ZhiWeiException e) {
			message.setMessage("添加失败");
			message.setState(false);
			log.error("删除政策信息失败：{}", e.getMessage());
		}
		return message;
	}

	@Override
	public Industry modify(Industry info) {
		try {
			dao.findAndModify(info);
		} catch (ZhiWeiException e) {
			try {
				info = dao.findOne(String.valueOf(info.getId()));
			} catch (ZhiWeiException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	@Override
	public List<Industry> findByPage(Page page) {
		List<Industry> list = null;
		try {
			list = dao.findListWithLimitAndSkip(0, 0);
			if (null == page) {
				return list;
			}
			list = dao.findListWithLimitAndSkip(
					(page.getPageNum() - 1) * page.getPageSize(),
					page.getPageSize());
		} catch (ZhiWeiException e) {
			log.error("查询出错：{}", e.getMessage());
		}
		return list;
	}

	@Override
	public List<Industry> findLevelOne() {
		List<Industry> list = dao.findByFatherID(0);
		return list;
	}

	@Override
	public List<Industry> findByFatherID(int fatherid) {
		List<Industry> list = dao.findByFatherID(fatherid);
		return list;
	}
}