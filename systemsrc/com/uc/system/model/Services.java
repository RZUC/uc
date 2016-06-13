package com.uc.system.model;

import java.util.List;

/**
 * @ClassName: Services
 * @Description: 服务机构
 * @author Administrator
 * @date 2016年6月12日 下午10:54:11
 *       政策咨询、管理咨询、知识产权、认证评估、科技服务、人力资源服务、会计/审计/税务服务、法律服务、金融服务、广告/媒体/会展、研发设计、IT服务
 */
public class Services {
	private List<Integer> attentionIndustry;
	private String name;// 机构名称
	private String site;// 网址
	private String title;// 服务主题词
	private String summary;// 机构介绍
	private String content;// 服务内容描述；
	private String RegisteredAddress;// 注册地址;
	private String service;// 可提供服务
	private String contactsName;// 联系人姓名
	private String mobilePhoneNum;// 手机
	private String telephone;// 固定电话
	private String department;// 部门
	private String post;// 职务
}
