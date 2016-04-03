/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月1日
    * @version 1.00 
*/
package com.uc.system.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: GeneralDaoImpl
 * @author 落花流水
 * @date 2016年3月1日 下午2:46:43
 */
public abstract class GeneralDaoImpl
{   
    public final static Logger log = LoggerFactory.getLogger(GeneralDaoImpl.class);
    
    
    @Resource(name = "mongoTemplate")
    protected MongoTemplate mongoTemp;
    
    @Resource(name = "mongoTemplateWB")
    protected MongoTemplate mongoTempWB;
    
//    @Resource(name = "mongoTemplateWeixinMedia")
//    protected MongoTemplate mongoTempWeixinMedia;
    
    protected void queryDebug(Query query)
    {   
        log.debug("查询参数：【{}】",query.getQueryObject());
    }
}
