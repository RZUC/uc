/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月19日
    * @version 1.00 
*/ 
package com.uc.system.dao;

import java.util.List;

import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;

/** 
 * @Description: 政策信息编辑类  
 * @ClassName: PolicyInfoDao 
 * @author 落花流水 
 * @date 2016年4月19日 下午9:22:27  
 */
public interface PolicyInfoDao extends CommonDao<PolicyInfo>
{

    /** 
     * @Title: modifyTop 
     * @Description: 更新是否置顶
     * @param @param id
     * @param @param b 设定文件 
     * @return void 返回类型 
     */ 
    void modifyTop(String id, int b);

    /** 
     * @Title: findAll 
     * @Description: 根据查询语句查询数据
     * @param @param query
     * @param @param page
     * @param @return 设定文件 
     * @return List<PolicyInfo> 返回类型 
     */ 
    List<PolicyInfo> findAll(Query query, Page page);
    
    /** 
     * @Title: findAllByIds 
     * @Description: 根据ID数组查询
     * @param @param query
     * @param @param page
     * @param @return 设定文件 
     * @return List<PolicyInfo> 返回类型 
     */ 
    List<PolicyInfo> findAllByIds(List<String> ids);
    
    List<PolicyInfo> findAllByTITLE();

	List<PolicyInfo> findByType(String type);
	List<PolicyInfo> findTop(int top);
}
