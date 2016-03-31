/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年3月22日
    * @version 1.00 
*/
package com.zhiwei.wjgg.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

 
public class MongoConfig
{
    
 
    private MongoDbFactory mongoFactory;
    
    private MongoMappingContext mongoMappingContext;
    
    public MappingMongoConverter mongoConverter()
        throws Exception
    {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        // this is my customization
        mongoConverter.setMapKeyDotReplacement("_");
        mongoConverter.afterPropertiesSet();
        return mongoConverter;
    }
}