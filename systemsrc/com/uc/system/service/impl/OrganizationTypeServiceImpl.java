package com.uc.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uc.system.model.OrganizationType;
import com.uc.system.service.OrganizationTypeService;
@Component
public class OrganizationTypeServiceImpl implements OrganizationTypeService{

	@Override
	public List<OrganizationType> getServiceTypeList(){
		List<OrganizationType>list=new ArrayList<OrganizationType>();
		list.add(new OrganizationType(1, "政策咨询"));
		list.add(new OrganizationType(2, "管理咨询"));
		list.add(new OrganizationType(3, "知识产权"));
		list.add(new OrganizationType(4, "认证评估"));
		list.add(new OrganizationType(5, "科技服务"));
		list.add(new OrganizationType(6, "人力资源服务"));
		list.add(new OrganizationType(7, "会计/审计/税务服务"));
		list.add(new OrganizationType(8, "法律服务"));
		list.add(new OrganizationType(9, "金融服务"));
		list.add(new OrganizationType(10, "广告/媒体/会展"));
		list.add(new OrganizationType(11, "研发设计"));
		list.add(new OrganizationType(12,"IT服务"));

		return list;
	}
}
