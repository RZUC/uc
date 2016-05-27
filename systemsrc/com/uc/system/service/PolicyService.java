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
package com.uc.system.service;

import java.util.List;

import com.uc.system.model.Location;
import com.uc.system.model.Message;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;

/** 
 * @Description: 政策信息服务 
 * @ClassName: PolicyService 
 * @author 落花流水 
 * @date 2016年4月19日 下午9:20:22  
 */
public interface PolicyService
{

    /** 
     * @Title: findList 
     * @Description: 根据查询条件和分页调节来查询信息
     * @param @param query
     * @param @param page
     * @param @return 设定文件 
     * @return List<PolicyInfo> 返回类型 
     */ 
    List<PolicyInfo> findList(Query query, Page page);

    /** 
     * @Title: findByContent 
     * @Description: TODO(通过内容意见查找) 
     * @param @param query
     * @param @param page
     * @param @return 设定文件 
     * @return List<PolicyInfo> 返回类型 
     */ 
    List<PolicyInfo> findByContent(Query query, Page page);
    
    /** 
     * @Title: add 
     * @Description: 添加数据 
     * @param @param info
     * @param @return 设定文件 
     * @return Message 返回类型 
     */ 
    Message add(PolicyInfo info);

    /** 
     * @Title: del 
     * @Description: 根据ID删除数据
     * @param @param id
     * @param @return 设定文件 
     * @return Message 返回类型 
     */ 
    Message del(String id);

    /** 
     * @Title: update 
     * @Description: 更新数据 
     * @param @param info
     * @param @return 设定文件 
     * @return List<Location> 返回类型 
     */ 
    Message update(PolicyInfo info);

    /** 
     * @Title: top 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param id
     * @param @return 设定文件 
     * @return Message 返回类型 
     */ 
    Message top(String id);

    /** 
     * @Title: unTop 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param id
     * @param @return 设定文件 
     * @return Message 返回类型 
     */ 
    Message unTop(String id);

	List<PolicyInfo> findListByTyep(String type);

	List<PolicyInfo> findListByTop(int top);

}
