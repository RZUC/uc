/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月21日
    * @version 1.00 
*/ 
package com.uc.system.dao;

import java.util.List;

import com.uc.system.model.Industry;
import com.uc.system.model.Page;
import com.uc.system.model.PolicyInfo;
import com.uc.system.model.Query;

/** 
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @ClassName: IndustryDao 
 * @author 落花流水 
 * @date 2016年4月21日 上午6:41:55  
 */
public interface IndustryDao extends CommonDao<Industry>
{

    /** 
     * @Title: findAll 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param query
     * @param @param page
     * @param @return 设定文件 
     * @return List<PolicyInfo> 返回类型 
     */ 
    List<Industry> findAll(Query query, Page page);

}
