package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.DepartmentDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Department;
import com.uc.system.service.DeparmentService;

/**
 * @author Simple
 *
 */
@Component
public class DepartmentServiceImpl extends GeneralServiceImpl implements
		DeparmentService {

	@Resource
	private DepartmentDao dao;

	@Override
	public List<Department> getDeparmentList() {
		List<Department> list = new ArrayList<Department>();
		try {
			list = dao.findAll();
		} catch (ZhiWeiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}