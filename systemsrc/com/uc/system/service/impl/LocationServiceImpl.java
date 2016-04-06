package com.uc.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.uc.system.dao.LocationDao;
import com.uc.system.exception.ZhiWeiException;
import com.uc.system.model.Location;
import com.uc.system.service.LocationService;

/**
 * @author cwt
 * @date 2016-04-06
 */
@Component
public class LocationServiceImpl extends GeneralServiceImpl implements LocationService
{
    
    @Resource
    private LocationDao locationDao;
    
    @Override
    public List<Location> findLocationByFatherId(String fatherID)
    {
        List<Location> list;
        try
        {
            list = locationDao.findOneByFiled("fatherID", fatherID);
            return list;
        }
        catch (ZhiWeiException e)
        {
            e.printStackTrace();
        }
        return null;
        
    }
    
    @Override
    public List<Location> findProvince()
    {
        List<Location> list;
        try
        {
            list = locationDao.findOneByFiled("fatherID", "0");
            return list;
        }
        catch (ZhiWeiException e)
        {
            log.error("查询省份信息出错：{}", e.getCause());
        }
        return null;
    }
    
}